package com.lrgoncalves.registroin.rotulagem.data;

import java.util.logging.Logger;

import javax.enterprise.inject.Produces;

import com.mongodb.MongoClient;

/**
 * @author digitallam
 *
 */
public class Producer {
	
	private static final Logger LOGGER = Logger.getLogger(Producer.class.getName());
	
	@Produces
	public MongoClient mongoClient() {
		try {
			return new MongoClient("localhost", 27017);
		} catch (Throwable t) {
			LOGGER.severe(t.getMessage());
		}
		return null;
	}
}
