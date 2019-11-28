package com.lrgoncalves.registroin.rotulagem.data;


import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import com.mongodb.MongoClient;

/**
 * @author digitallam
 *
 */

@ApplicationScoped
public class Producer {
  
@Produces
public MongoClient mongoClient() {
   try {
      return new MongoClient("localhost", 27017);
   } catch (Throwable t) {
      t.printStackTrace();
   }
   return null;
   }
}
