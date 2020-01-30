package com.lrgoncalves.registroin.rotulagem.data;

import java.util.logging.Logger;

import javax.enterprise.inject.Produces;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

/**
 * @author digitallam
 *
 */
public class Producer {

	private static final Logger LOGGER = Logger.getLogger(Producer.class.getName());

	private static MongoClient mongoClient;

	private static MongoDatabase database;

	private static MongoCollection<Document> clientCollection;

	private static MongoCollection<Document> rotuloCollection;

	private MongoClient clientConnect() {
		try {

			if (mongoClient == null)
				mongoClient = new MongoClient("localhost", 27017);

			return mongoClient;

		} catch (Throwable t) {
			LOGGER.severe(t.getMessage());
		}
		return null;
	}

	private MongoDatabase databaseConnect() {

		if (database == null)
			database = clientConnect().getDatabase("registroin");

		return database;
	}

	@Produces 
	@ClientCollection
	public MongoCollection<Document> clientCollection() {

		if (clientCollection == null)
			clientCollection = databaseConnect().getCollection("clients");

		return clientCollection;
	}

	@Produces 
	@RotuloCollection
	public MongoCollection<Document> rotuloCollection() {

		if (rotuloCollection == null)
			rotuloCollection = databaseConnect().getCollection("rotulos");

		return rotuloCollection;
	}
}
