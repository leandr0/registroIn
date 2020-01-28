/**
 * 
 */
package com.lrgoncalves.registroin.rotulagem.data;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.bson.Document;
import org.bson.types.ObjectId;

import com.lrgoncalves.registroin.rotulagem.data.entity.Client;
import com.lrgoncalves.registroin.rotulagem.data.entity.Contact;
import com.lrgoncalves.registroin.rotulagem.data.exception.PersistClientException;
import com.lrgoncalves.registroin.rotulagem.data.exception.SearchRotuloException;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.ReplaceOptions;

/**
 * @author digitallam
 *
 */
public class ManagerClientBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8830284544781815664L;

	@Inject
	transient MongoClient mongoClient;

	private static final Logger LOGGER = Logger.getLogger(ManagerClientBean.class.getName());

	public List<Client> searchByName(String query) throws SearchRotuloException {

		List<Client> clients = new LinkedList<Client>();

		try {
			
			MongoDatabase database = mongoClient.getDatabase("registroin");

			MongoCollection<Document> collection = database.getCollection("clients");

			FindIterable<Document> iterable = collection.find(Filters.regex("name", query, "i"));

			MongoCursor<Document> cursor = iterable.cursor();

			while (cursor.hasNext()) {
				Document rotulo = cursor.next();
				clients.add(builClient(rotulo));
			}

		} catch (Throwable t) {
			LOGGER.error(t.getMessage());
			throw new SearchRotuloException(t);
		} 

		return clients;
	}

	public Client find(String id) throws SearchRotuloException {

		Client model = null;

		try {

			MongoDatabase database = mongoClient.getDatabase("registroin");

			MongoCollection<Document> collection = database.getCollection("clients");

			FindIterable<Document> iterable = collection.find(Filters.eq("_id", new ObjectId(id)));

			MongoCursor<Document> cursor = iterable.cursor();

			while (cursor.hasNext()) {
				Document client = cursor.next();
				model = builClient(client);
			}

		} catch (Throwable t) {
			LOGGER.error(t.getMessage());
			throw new SearchRotuloException(t);
		}

		return model;
	}

	public String persistClient(Client model) throws PersistClientException {

		String id = null;

		try {

			MongoDatabase database = mongoClient.getDatabase("registroin");

			MongoCollection<Document> collection = database.getCollection("clients");

			Document client = builClient(model);

			if (!StringUtils.isBlank(model.getId())) {
				client.append("_id", new ObjectId(model.getId()));
				collection.replaceOne(Filters.eq("_id", client.get("_id")), client, new ReplaceOptions().upsert(true));
				id = model.getId();
			} else {
				client.append("_id", new ObjectId());
				collection.insertOne(client);
				id = ((ObjectId) client.get("_id")).toString();
			}

			return id;

		} catch (Throwable t) {
			LOGGER.error(t.getMessage());
			throw new PersistClientException(t);
		} 
	}

	private List<Document> buildContactList(List<Contact> model) {

		if (model == null || model.isEmpty())
			return null;

		List<Document> contactList = new LinkedList<Document>();

		for (Contact contact : model) {
			contactList.add(buildContact(contact));
		}

		return contactList;
	}

	private Document buildContact(Contact model) {

		if (model == null)
			throw new IllegalArgumentException("Invalid object!");

		Document document = new Document();
		document.append("email", model.getEmail());
		document.append("name", model.getName());

		return document;
	}

	private Contact buildContact(Document document) {

		if (document == null)
			throw new IllegalArgumentException("Invalid object!");

		Contact model = new Contact();
		model.setEmail(document.getString("email"));
		model.setName(document.getString("name"));

		return model;
	}
	
	@SuppressWarnings("unchecked")
	private Client builClient(Document document) {

		Client client = new Client();

		client.setId(document.getObjectId("_id").toString());
		client.setIdentificationNumber(document.getString("identification_number"));
		client.setNome(document.getString("name"));
		client.setContactList(buildContactListFromDocument((List<Document>) document.get("contact_list")));

		return client;
	}

	private List<Contact> buildContactListFromDocument(List<Document> documents){
		
		if(documents == null)
			return null;
		
		List<Contact> contactList = new LinkedList<Contact>();
		
		for (Document document : documents) {
			contactList.add(buildContact(document));
		}
		
		return contactList;	
	}
	
	
	public Document builClient(Client model) {

		Document document = new Document();
		
		if(!StringUtils.isBlank(model.getId()))
			document.append("_id", model.getId());
		
		document.append("name", model.getNome());
		document.append("identification_number", model.getIdentificationNumber());
		document.append("contact_list", buildContactList(model.getContactList()));

		return document;
	}
	
	/**
	 * Just use it on Tests
	 */
	public void setMongoClient(MongoClient mongoClient) {
		this.mongoClient = mongoClient;
	}
}