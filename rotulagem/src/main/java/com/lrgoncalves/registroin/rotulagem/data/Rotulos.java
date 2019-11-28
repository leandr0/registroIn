/**
 * 
 */
package com.lrgoncalves.registroin.rotulagem.data;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

/**
 * @author digitallam
 *
 */
@Model
public class Rotulos {

	@Inject
	MongoClient mongoClient;

	public static void main(String[] args) {

		MongoClient mongo = null;

		try {

			mongo = new MongoClient("localhost", 27017);

			MongoDatabase database = mongo.getDatabase("registroin");

			MongoCollection<Document> collection = database.getCollection("rotulos");
			
			Document rotulo = new Document();
			
			rotulo.append("_id", java.util.UUID.randomUUID().toString());
			
			
			Document client = new Document();
			client.append("nome", "Parmalat");
			client.append("produto", "Leite de Texugo 1L");
			client.append("data", "26/11/2019");
			client.append("email", "qualidade@parmalat.com.br");
			
			Document informacaoNutricional = new Document();
			informacaoNutricional.append("descricao", "A Informação Nutricional deve seguir EXATAMENTE o modelo permitido pela ANVISA, Com Trans em itálico, e seus valores devem ser ajustados para:");
			informacaoNutricional.append("peso_total_gramas", "1.000");
			informacaoNutricional.append("valor_porcao", "250 ml");
			informacaoNutricional.append("legislacao", "(*) Valores Diários de referência com base em uma dieta de 2000kcal ou 8400kJ. Seus valores diários podem ser maiores ou menores dependendo das necessidades energéticas.(**) VD não estabelecido.");
			
			Document quantidadePorcao = new Document();
			quantidadePorcao.append("valor_energetico", "278.0kcal/1156.0kJ g");
			quantidadePorcao.append("carboidratos", "8.5 g");
			quantidadePorcao.append("proteinas", "16 g");
			quantidadePorcao.append("gorduras_totais", "20 g");
			quantidadePorcao.append("gorduras_saturadas", "8.8 g");
			quantidadePorcao.append("gorduras_trans", "8.2 g");
			quantidadePorcao.append("fibra_alimentar", "3.0 g");
			quantidadePorcao.append("sodio", "60 mg");
			
			Document percentualValorDiario = new Document();
			percentualValorDiario.append("valor_energetico", "14");
			percentualValorDiario.append("carboidratos", "3");
			percentualValorDiario.append("proteinas", "21");
			percentualValorDiario.append("gorduras_totais", "36");
			percentualValorDiario.append("gorduras_saturadas", "40");
			percentualValorDiario.append("gorduras_trans", "**");
			percentualValorDiario.append("fibra_alimentar", "12");
			percentualValorDiario.append("sodio", "2.0");

			
			informacaoNutricional.append("quantidade_porcao", quantidadePorcao);
			informacaoNutricional.append("percentual_valor_diario", percentualValorDiario);
			
			rotulo.append("cliente", client);
			rotulo.append("informacao_nutricional", informacaoNutricional);
			
			
			collection.insertOne(rotulo);
			

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			mongo.close();
		}

	}

}
