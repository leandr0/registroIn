/**
 * 
 */
package com.lrgoncalves.registroin.rotulagem.data;


import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.bson.Document;

import com.lrgoncalves.registroin.rotulagem.data.entity.Client;
import com.lrgoncalves.registroin.rotulagem.data.entity.ConservacaoProduto;
import com.lrgoncalves.registroin.rotulagem.data.entity.InformacaoNutricional;
import com.lrgoncalves.registroin.rotulagem.data.entity.PesoLiquido;
import com.lrgoncalves.registroin.rotulagem.data.entity.QuantidadeNutricional;
import com.lrgoncalves.registroin.rotulagem.data.entity.Rotulo;
import com.lrgoncalves.registroin.rotulagem.data.entity.SimpleObject;
import com.lrgoncalves.registroin.rotulagem.data.exception.PersistRotuloException;
import com.lrgoncalves.registroin.rotulagem.data.exception.SearchRotuloException;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

/**
 * @author digitallam
 *
 */
@Model
public class RotuloDAO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8806186280696615791L;

	@Inject
	transient MongoClient mongoClient;
	
	private static final Logger LOGGER = Logger.getLogger(RotuloDAO.class.getName());

	public List<Rotulo> searchByDescription(String query) throws SearchRotuloException{

		List<Rotulo> rotulos = new LinkedList<Rotulo>();
		
		try {

			MongoDatabase database = mongoClient.getDatabase("registroin");

			MongoCollection<Document> collection = database.getCollection("rotulos");
			
			FindIterable<Document> iterable = collection.find(Filters.regex("cliente.produto", query, "i"));

			MongoCursor<Document> cursor = iterable.cursor();
			
			while (cursor.hasNext()) {
				Document rotulo = cursor.next();
				rotulos.add(builRotulo(rotulo));
			}

		}catch (Throwable t) {
			LOGGER.log(Level.SEVERE, t.getMessage());
			throw new SearchRotuloException(t);
		} finally {
			mongoClient.close();
		}
		
		return rotulos;
	}
	
	public void persistRotulo(Rotulo model) throws PersistRotuloException{


		try {

			MongoDatabase database = mongoClient.getDatabase("registroin");

			MongoCollection<Document> collection = database.getCollection("rotulos");

			
			Document rotulo = new Document();
			
			rotulo.append("_id", model.getId());
			rotulo.append("data", model.getData());
			
			try {
				rotulo.append("informacao_nutricional", buildInformacaoNutricional(model.getInformacaoNutricional()));
			}catch (IllegalArgumentException e) {
				LOGGER.info(e.getMessage());
			}
			
			try {
				rotulo.append("cliente", buildCliente(model.getClient()));
			}catch (IllegalArgumentException e) {
				LOGGER.info(e.getMessage());
			}
			
			try {
				rotulo.append("denominacao_produto", buildSimpleObject(model.getDenominacaoProduto()));
			}catch (IllegalArgumentException e) {
				LOGGER.info(e.getMessage());
			}
			
			try {
				rotulo.append("conservacao_produto", buildConservacaoProduto(model.getConservacaoProduto()));
			}catch (IllegalArgumentException e) {
				LOGGER.info(e.getMessage());
			}
			
			try {
				rotulo.append("distribuidor", buildSimpleObject(model.getDistribuidor()));
			}catch (IllegalArgumentException e) {
				LOGGER.info(e.getMessage());
			}
			
			try {
				rotulo.append("tartrazina", buildSimpleObject(model.getTartrazina()));
			}catch (IllegalArgumentException e) {
				LOGGER.info(e.getMessage());
			}
			
			try {
				rotulo.append("aromatizante", buildSimpleObject(model.getAromatizante()));
			}catch (IllegalArgumentException e) {
				LOGGER.info(e.getMessage());
			}
			
			try {
				rotulo.append("derivados_lacteos", buildSimpleObject(model.getDerivadosLacteos()));
			}catch (IllegalArgumentException e) {
				LOGGER.info(e.getMessage());
			}
			try {
				rotulo.append("importador", buildSimpleObject(model.getImportador()));
			}catch (IllegalArgumentException e) {
				LOGGER.info(e.getMessage());
			}
			try {
				rotulo.append("aspartame_fenilalanina", buildSimpleObject(model.getAspartameFenilalanina()));
			}catch (IllegalArgumentException e) {
				LOGGER.info(e.getMessage());
			}
			try {
				rotulo.append("peso_liquido", buildPesoLiquido(model.getPesoLiquido()));
			}catch (IllegalArgumentException e) {
				LOGGER.info(e.getMessage());
			}
			try {
				rotulo.append("alergicos", buildSimpleObject(model.getAlergicos()));
			}catch (IllegalArgumentException e) {
				LOGGER.info(e.getMessage());
			}
			try {
				rotulo.append("data_fabricacao", buildSimpleObject(model.getDataFabricacao()));
			}catch (IllegalArgumentException e) {
				LOGGER.info(e.getMessage());
			}
			try {
				rotulo.append("sac", buildSimpleObject(model.getSac()));
			}catch (IllegalArgumentException e) {
				LOGGER.info(e.getMessage());
			}
			try {
				rotulo.append("gluten", buildSimpleObject(model.getGluten()));
			}catch (IllegalArgumentException e) {
				LOGGER.info(e.getMessage());
			}
			try {
				rotulo.append("gluten_alergenos", buildSimpleObject(model.getGlutenAlergenos()));
			}catch (IllegalArgumentException e) {
				LOGGER.info(e.getMessage());
			}
			try {
				rotulo.append("prazo_validade", buildSimpleObject(model.getPrazoValidade()));
			}catch (IllegalArgumentException e) {
				LOGGER.info(e.getMessage());
			}
			try {
				rotulo.append("transgenico", buildSimpleObject(model.getTransgenico()));
			}catch (IllegalArgumentException e) {
				LOGGER.info(e.getMessage());
			}
			try {
				rotulo.append("industria_origem", buildSimpleObject(model.getIndustriaOrigem()));
			}catch (IllegalArgumentException e) {
				LOGGER.info(e.getMessage());
			}
			try {
				rotulo.append("uso_produto", buildSimpleObject(model.getUsoProduto()));
			}catch (IllegalArgumentException e) {
				LOGGER.info(e.getMessage());
			}
			try {
				rotulo.append("lote", buildSimpleObject(model.getLote()));
			}catch (IllegalArgumentException e) {
				LOGGER.info(e.getMessage());
			}
			try {
				rotulo.append("registro_mapa", buildSimpleObject(model.getRegistroMAPA()));
			}catch (IllegalArgumentException e) {
				LOGGER.info(e.getMessage());
			}
			try {
				rotulo.append("ingredientes", buildSimpleObject(model.getIngredientes()));
			}catch (IllegalArgumentException e) {
				LOGGER.info(e.getMessage());
			}
			try {
				rotulo.append("produtor", buildSimpleObject(model.getProdutor()));
			}catch (IllegalArgumentException e) {
				LOGGER.info(e.getMessage());
			}
			
			collection.insertOne(rotulo);
			
			Thread.sleep(2000);
			
		} catch (Throwable t) {
			LOGGER.log(Level.SEVERE, t.getMessage());
			throw new PersistRotuloException(t);
		} finally {
			mongoClient.close();
		}
	}

	
	public Document buildSimpleObject(final SimpleObject model) {
		
		if(model == null)
			throw new IllegalArgumentException("Invalid object!");
	
		Document document = new Document();
		document.append("descricao", model.getDescricao());
		
		return document;
	}
	
	public Document buildPesoLiquido(final PesoLiquido model) {
		
		if(model == null)
			throw new IllegalArgumentException("Invalid object!");
		
		Document document = new Document();
		document.append("descricao", model.getDescricao());
		document.append("peso", model.getPeso());
		
		return document;
	}
	
	public Document buildConservacaoProduto(final ConservacaoProduto model) {
		
		if(model == null)
			throw new IllegalArgumentException("Invalid object!");
		
		Document document = new Document();
		document.append("validade_produto", model.getValidadeProduto());
		
		return document;
	}
	
	public Document buildCliente(final Client model) {
		
		if(model == null)
			throw new IllegalArgumentException("Invalid object!");
		
		Document document = new Document();
		document.append("nome", model.getNome());
		document.append("produto", model.getProduto());
		document.append("email", model.getEmail());
		
		return document;
	}
	
	
	private Document buildQuantidadeNutricional(final QuantidadeNutricional model) {
		
		if(model == null)
			throw new IllegalArgumentException("Invalid object!");
		
		Document document = new Document();
		
		document.append("valor_energetico", model.getValorEnergetico());
		document.append("carboidratos", model.getCarboidratos());
		document.append("proteinas", model.getProteinas());
		document.append("gorduras_totais", model.getGordurasTotais());
		document.append("gorduras_saturadas", model.getGordurasSaturadas());
		
		if(!StringUtils.isAllBlank(model.getGordurasTrans())) {
			document.append("gorduras_trans", model.getGordurasTrans());
		}else {
			document.append("gorduras_trans", "**");
		}
		
		
		document.append("fibra_alimentar", model.getFibraAlimentar());
		document.append("sodio", model.getSodio());
		
		if(model.getVlrEnergeticoKcal() > 0.0) {
			document.append("valor_energetico_kcal", model.getVlrEnergeticoKcal());
		}
		if(model.getVlrEnergeticoKj() > 0.0) {
			document.append("valor_energetico_kj", model.getVlrEnergeticoKj());
		}
		
		return document;
	}
	
	public Document buildInformacaoNutricional(final InformacaoNutricional model) {
		
		if(model == null)
			throw new IllegalArgumentException("Invalid object!");
		
		Document document = new Document();
		
		document.append("descricao", model.getDescricao());
		document.append("peso_total_gramas", String.valueOf(model.getDadosBase().getPeso()));
		document.append("valor_porcao", String.valueOf(model.getDadosBase().getPesoPorcao()));
		document.append("legislacao", model.getLegislacao());
		document.append("descritivo_porcao", model.getPorcao());
		
		document.append("quantidade_porcao", buildQuantidadeNutricional(model.getQtdPorcao()));
		document.append("percentual_valor_diario", buildQuantidadeNutricional(model.getPercVlrDiario()));
		
		return document;
	}
	
	
	private  Rotulo builRotulo(Document document) {
		
		Rotulo rotulo = new Rotulo();
		
		rotulo.setId(document.getString("_id"));
		rotulo.setData(document.getString("data"));
		
		try {
			rotulo.setInformacaoNutricional(buildInformacaoNutricional((Document) document.get("informacao_nutricional")));
		}catch (IllegalArgumentException e) {
			LOGGER.info(e.getMessage());
		}
		try {
			rotulo.setClient(buildCliente((Document) document.get("cliente")));
		}catch (IllegalArgumentException e) {
			LOGGER.info(e.getMessage());
		}
		try {
			rotulo.setDenominacaoProduto(buildSimpleObject((Document) document.get("denominacao_produto")));
		}catch (IllegalArgumentException e) {
			LOGGER.info(e.getMessage());
		}
		try {
			rotulo.setConservacaoProduto(buildConservacaoProduto((Document) document.get("conservacao_produto")));
		}catch (IllegalArgumentException e) {
			LOGGER.info(e.getMessage());
		}
		try {
			rotulo.setDistribuidor(buildSimpleObject((Document) document.get("distribuidor")));
		}catch (IllegalArgumentException e) {
			LOGGER.info(e.getMessage());
		}
		try {
			rotulo.setTartrazina(buildSimpleObject((Document) document.get("tartrazina")));
		}catch (IllegalArgumentException e) {
			LOGGER.info(e.getMessage());
		}
		try {
			rotulo.setAromatizante(buildSimpleObject((Document) document.get("aromatizante")));
		}catch (IllegalArgumentException e) {
			LOGGER.info(e.getMessage());
		}
		try {
			rotulo.setDerivadosLacteos(buildSimpleObject((Document) document.get("derivados_lacteos")));
		}catch (IllegalArgumentException e) {
			LOGGER.info(e.getMessage());
		}
		try {
			rotulo.setImportador( buildSimpleObject((Document) document.get("importador")));
		}catch (IllegalArgumentException e) {
			LOGGER.info(e.getMessage());
		}
		try {
			rotulo.setAspartameFenilalanina(buildSimpleObject((Document) document.get("aspartame_fenilalanina")));
		}catch (IllegalArgumentException e) {
			LOGGER.info(e.getMessage());
		}
		try {
			rotulo.setPesoLiquido(buildPesoLiquido((Document) document.get("peso_liquido")));
		}catch (IllegalArgumentException e) {
			LOGGER.info(e.getMessage());
		}
		try {
			rotulo.setAlergicos(buildSimpleObject((Document) document.get("alergicos")));
		}catch (IllegalArgumentException e) {
			LOGGER.info(e.getMessage());
		}
		try {
			rotulo.setDataFabricacao(buildSimpleObject((Document) document.get("data_fabricacao")));
		}catch (IllegalArgumentException e) {
			LOGGER.info(e.getMessage());
		}
		try {
			rotulo.setSac(buildSimpleObject((Document) document.get("sac")));
		}catch (IllegalArgumentException e) {
			LOGGER.info(e.getMessage());
		}
		try {
			rotulo.setGluten(buildSimpleObject((Document) document.get("gluten")));
		}catch (IllegalArgumentException e) {
			LOGGER.info(e.getMessage());
		}
		try {
			rotulo.setGlutenAlergenos(buildSimpleObject((Document) document.get("gluten_alergenos")));
		}catch (IllegalArgumentException e) {
			LOGGER.info(e.getMessage());
		}
		try {
			rotulo.setPrazoValidade(buildSimpleObject((Document) document.get("prazo_validade")));
		}catch (IllegalArgumentException e) {
			LOGGER.info(e.getMessage());
		}
		try {
			rotulo.setTransgenico(buildSimpleObject((Document) document.get("transgenico")));
		}catch (IllegalArgumentException e) {
			LOGGER.info(e.getMessage());
		}
		try {
			rotulo.setIndustriaOrigem(buildSimpleObject((Document) document.get("industria_origem")));
		}catch (IllegalArgumentException e) {
			LOGGER.info(e.getMessage());
		}
		try {
			rotulo.setUsoProduto( buildSimpleObject((Document) document.get("uso_produto")));
		}catch (IllegalArgumentException e) {
			LOGGER.info(e.getMessage());
		}
		try {
			rotulo.setLote( buildSimpleObject((Document) document.get("lote")));
		}catch (IllegalArgumentException e) {
			LOGGER.info(e.getMessage());
		}
		try {
			rotulo.setRegistroMAPA(buildSimpleObject((Document) document.get("registro_mapa")));
		}catch (IllegalArgumentException e) {
			LOGGER.info(e.getMessage());
		}
		try {
			rotulo.setIngredientes(buildSimpleObject((Document) document.get("ingredientes")));
		}catch (IllegalArgumentException e) {
			LOGGER.info(e.getMessage());
		}
		try {
			rotulo.setProdutor(buildSimpleObject((Document) document.get("produtor")));
		}catch (IllegalArgumentException e) {
			LOGGER.info(e.getMessage());
		}
		
		return rotulo;
	}
	
	
	private  PesoLiquido buildPesoLiquido(Document document) {
		
		if(document == null)
			throw new IllegalArgumentException("Invalid document!");
		
		PesoLiquido model = new PesoLiquido();
		model.setDescricao(document.getString("descricao"));
		model.setPeso(document.getString("peso"));
		
		return model;
	}
	
	@SuppressWarnings("unchecked")
	private  ConservacaoProduto buildConservacaoProduto(Document document) {
		
		if(document == null)
			throw new IllegalArgumentException("Invalid document!");
		
		ConservacaoProduto model = new ConservacaoProduto();
		model.setValidadeProduto(((Map<String, String>) document.get("validade_produto")));
		
		return model;
	}
	
	private  SimpleObject buildSimpleObject(Document document) {
		
		if(document == null)
			throw new IllegalArgumentException("Invalid document!");
		
		SimpleObject model = new SimpleObject();
		model.setDescricao(document.getString("descricao"));
		
		return model;
	}
	
	private  Client buildCliente(Document document) {
		
		if(document == null)
			throw new IllegalArgumentException("Invalid document!");
		
		Client model = new Client();
		model.setNome(document.getString("nome"));
		model.setProduto(document.getString("produto"));
		model.setEmail(document.getString("email"));
		
		return model;
	}
	
	
	private  InformacaoNutricional buildInformacaoNutricional(Document document) {

		if(document == null)
			throw new IllegalArgumentException("Invalid document!");
		
		InformacaoNutricional model = new InformacaoNutricional();

		model.setDescricao(document.getString("descricao"));
		model.setPesoTotalGramas( (String) document.get("peso_total_gramas"));
		model.setValorPorcao(document.getString("valor_porcao"));
		
		model.getDadosBase().setPeso(Double.parseDouble(model.getPesoTotalGramas()));
		model.getDadosBase().setPesoPorcao(Double.parseDouble(model.getValorPorcao()));
		
		model.setLegislacao(document.getString("legislacao"));
		model.setPorcao(document.getString("descritivo_porcao"));

		model.setQtdPorcao(buildQuantidadeNutricional((Document) document.get("quantidade_porcao")));
		model.setPercVlrDiario(buildQuantidadeNutricional((Document) document.get("percentual_valor_diario")));

		return model;
	}

	private  QuantidadeNutricional buildQuantidadeNutricional(Document document) {
		
		if(document == null)
			throw new IllegalArgumentException("Invalid document!");
		
		QuantidadeNutricional model = new QuantidadeNutricional();
		
		model.setValorEnergetico(document.getString("valor_energetico"));
		model.setCarboidratos(document.getString("carboidratos"));
		model.setProteinas(document.getString("proteinas"));
		model.setGordurasTotais(document.getString("gorduras_totais"));
		model.setGordurasSaturadas(document.getString("gorduras_saturadas"));
		
		if(!StringUtils.isAllBlank(document.getString("gorduras_trans"))) {
			model.setGordurasTrans(document.getString("gorduras_trans"));
		}else {
			model.setGordurasTrans("**");
		}
		
		model.setFibraAlimentar(document.getString("fibra_alimentar"));
		model.setSodio(document.getString("sodio"));
		
		if(document.getDouble("valor_energetico_kcal") != null && document.getDouble("valor_energetico_kcal") > 0.0) {
			model.setVlrEnergeticoKcal(document.getDouble("valor_energetico_kcal"));
		}
		if(document.getDouble("valor_energetico_kj") != null && document.getDouble("valor_energetico_kj") > 0.0) {
			model.setVlrEnergeticoKj(document.getDouble("valor_energetico_kj"));
		}
		
		return model;
	}

}