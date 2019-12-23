/**
 * 
 */
package com.lrgoncalves.registroin.rotulagem.data;


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
public class RotuloDAO {

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
			rotulo.append("informacao_nutricional", buildInformacaoNutricional(model.getInformacaoNutricional()));
			rotulo.append("cliente", buildCliente(model.getClient()));
			rotulo.append("denominacao_produto", buildSimpleObject(model.getDenominacaoProduto()));
			rotulo.append("conservacao_produto", buildConservacaoProduto(model.getConservacaoProduto()));
			rotulo.append("distribuidor", buildSimpleObject(model.getDistribuidor()));
			rotulo.append("tartrazina", buildSimpleObject(model.getTartrazina()));
			rotulo.append("aromatizante", buildSimpleObject(model.getAromatizante()));
			rotulo.append("derivados_lacteos", buildSimpleObject(model.getDerivadosLacteos()));
			rotulo.append("importador", buildSimpleObject(model.getImportador()));
			rotulo.append("aspartame_fenilalanina", buildSimpleObject(model.getAspartameFenilalanina()));
			rotulo.append("peso_liquido", buildPesoLiquido(model.getPesoLiquido()));
			rotulo.append("alergicos", buildSimpleObject(model.getAlergicos()));
			rotulo.append("data_fabricacao", buildSimpleObject(model.getDataFabricacao()));
			rotulo.append("sac", buildSimpleObject(model.getSac()));
			rotulo.append("gluten", buildSimpleObject(model.getGluten()));
			rotulo.append("gluten_alergenos", buildSimpleObject(model.getGlutenAlergenos()));
			rotulo.append("prazo_validade", buildSimpleObject(model.getPrazoValidade()));
			rotulo.append("transgenico", buildSimpleObject(model.getTransgenico()));
			rotulo.append("industria_origem", buildSimpleObject(model.getIndustriaOrigem()));
			rotulo.append("uso_produto", buildSimpleObject(model.getUsoProduto()));
			rotulo.append("lote", buildSimpleObject(model.getLote()));
			rotulo.append("registro_mapa", buildSimpleObject(model.getRegistroMAPA()));
			rotulo.append("ingredientes", buildSimpleObject(model.getIngredientes()));
			rotulo.append("produtor", buildSimpleObject(model.getProdutor()));
			
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
	
		Document document = new Document();
		document.append("descricao", model.getDescricao());
		
		return document;
	}
	
	public Document buildPesoLiquido(final PesoLiquido model) {
		
		Document document = new Document();
		document.append("descricao", model.getDescricao());
		document.append("peso", model.getPeso());
		
		return document;
	}
	
	public Document buildConservacaoProduto(final ConservacaoProduto model) {
		
		Document document = new Document();
		document.append("validade_produto", model.getValidadeProduto());
		
		return document;
	}
	
	public Document buildCliente(final Client model) {
		
		Document document = new Document();
		document.append("nome", model.getNome());
		document.append("produto", model.getProduto());
		document.append("email", model.getEmail());
		
		return document;
	}
	
	
	private Document buildQuantidadeNutricional(final QuantidadeNutricional model) {
		
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
		rotulo.setInformacaoNutricional(buildInformacaoNutricional((Document) document.get("informacao_nutricional")));
		rotulo.setClient(buildCliente((Document) document.get("cliente")));
		rotulo.setDenominacaoProduto(buildSimpleObject((Document) document.get("denominacao_produto")));
		rotulo.setConservacaoProduto(buildConservacaoProduto((Document) document.get("conservacao_produto")));
		rotulo.setDistribuidor(buildSimpleObject((Document) document.get("distribuidor")));
		rotulo.setTartrazina(buildSimpleObject((Document) document.get("tartrazina")));
		rotulo.setAromatizante(buildSimpleObject((Document) document.get("aromatizante")));
		rotulo.setDerivadosLacteos(buildSimpleObject((Document) document.get("derivados_lacteos")));
		rotulo.setImportador( buildSimpleObject((Document) document.get("importador")));
		rotulo.setAspartameFenilalanina(buildSimpleObject((Document) document.get("aspartame_fenilalanina")));
		rotulo.setPesoLiquido(buildPesoLiquido((Document) document.get("peso_liquido")));
		rotulo.setAlergicos(buildSimpleObject((Document) document.get("alergicos")));
		rotulo.setDataFabricacao(buildSimpleObject((Document) document.get("data_fabricacao")));
		rotulo.setSac(buildSimpleObject((Document) document.get("sac")));
		rotulo.setGluten(buildSimpleObject((Document) document.get("gluten")));
		rotulo.setGlutenAlergenos(buildSimpleObject((Document) document.get("gluten_alergenos")));
		rotulo.setPrazoValidade(buildSimpleObject((Document) document.get("prazo_validade")));
		rotulo.setTransgenico(buildSimpleObject((Document) document.get("transgenico")));
		rotulo.setIndustriaOrigem(buildSimpleObject((Document) document.get("industria_origem")));
		rotulo.setUsoProduto( buildSimpleObject((Document) document.get("uso_produto")));
		rotulo.setLote( buildSimpleObject((Document) document.get("lote")));
		rotulo.setRegistroMAPA(buildSimpleObject((Document) document.get("registro_mapa")));
		rotulo.setIngredientes(buildSimpleObject((Document) document.get("ingredientes")));
		rotulo.setProdutor(buildSimpleObject((Document) document.get("produtor")));
		
		return rotulo;
	}
	
	
	private  PesoLiquido buildPesoLiquido(Document document) {
		
		PesoLiquido model = new PesoLiquido();
		model.setDescricao(document.getString("descricao"));
		model.setPeso(document.getString("peso"));
		
		return model;
	}
	
	@SuppressWarnings("unchecked")
	private  ConservacaoProduto buildConservacaoProduto(Document document) {
		
		ConservacaoProduto model = new ConservacaoProduto();
		model.setValidadeProduto(((Map<String, String>) document.get("validade_produto")));
		
		return model;
	}
	
	private  SimpleObject buildSimpleObject(Document document) {
		
		SimpleObject model = new SimpleObject();
		model.setDescricao(document.getString("descricao"));
		
		return model;
	}
	
	private  Client buildCliente(Document document) {
		
		Client model = new Client();
		model.setNome(document.getString("nome"));
		model.setProduto(document.getString("produto"));
		model.setEmail(document.getString("email"));
		
		return model;
	}
	
	
	private  InformacaoNutricional buildInformacaoNutricional(Document document) {

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