/**
 * 
 */
package com.lrgoncalves.registroin.rotulagem.data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.bson.Document;

import com.lrgoncalves.registroin.rotulagem.data.entity.Azeite;
import com.lrgoncalves.registroin.rotulagem.data.entity.ClassificacaoAzeite;
import com.lrgoncalves.registroin.rotulagem.data.entity.ClassificacaoAzeiteItem;
import com.lrgoncalves.registroin.rotulagem.data.entity.Client;
import com.lrgoncalves.registroin.rotulagem.data.entity.ConservacaoProduto;
import com.lrgoncalves.registroin.rotulagem.data.entity.InformacaoNutricional;
import com.lrgoncalves.registroin.rotulagem.data.entity.PesoLiquido;
import com.lrgoncalves.registroin.rotulagem.data.entity.QuantidadeNutricional;
import com.lrgoncalves.registroin.rotulagem.data.entity.Rotulo;
import com.lrgoncalves.registroin.rotulagem.data.entity.SimpleObject;
import com.lrgoncalves.registroin.rotulagem.data.entity.StatusType;
import com.lrgoncalves.registroin.rotulagem.data.exception.PersistClientException;
import com.lrgoncalves.registroin.rotulagem.data.exception.PersistRotuloException;
import com.lrgoncalves.registroin.rotulagem.data.exception.SearchRotuloException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.ReplaceOptions;
import com.mongodb.client.model.Updates;

/**
 * @author digitallam
 *
 */
public class ManagerRotuloBean implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 331837027697799888L;

	@Inject
	private ManagerClientBean clientDataAccess;

	private static final Logger LOGGER = Logger.getLogger(ManagerRotuloBean.class.getName());
	
	@Inject 
	@RotuloCollection
	private MongoCollection<Document> collection;

	public List<Rotulo> searchByDescription(String query) throws SearchRotuloException {

		List<Rotulo> rotulos = new LinkedList<Rotulo>();

		try {

			
			FindIterable<Document> iterable = null;

			if (StringUtils.isBlank(query)) {
				iterable = collection.find(Filters.eq("active", true));
			} else {
				iterable = collection
						.find(Filters.and(Filters.regex("produto", query, "i"), Filters.eq("active", true)));
			}

			MongoCursor<Document> cursor = iterable.cursor();

			while (cursor.hasNext()) {
				Document rotulo = cursor.next();
				rotulos.add(builRotulo(rotulo));
			}

		} catch (Throwable t) {
			LOGGER.error(t.getMessage());
			throw new SearchRotuloException(t);
		}

		return rotulos;
	}

	public Rotulo find(String id) throws SearchRotuloException {

		Rotulo model = null;

		try {

			FindIterable<Document> iterable = collection
					.find(Filters.and(Filters.eq("_id", id), Filters.eq("active", true)));

			MongoCursor<Document> cursor = iterable.cursor();

			while (cursor.hasNext()) {
				Document rotulo = cursor.next();
				model = builRotulo(rotulo);
				model.setHistory(builHistory(rotulo));
			}

		} catch (Throwable t) {
			LOGGER.error(t.getMessage());
			throw new SearchRotuloException(t);
		}

		return model;
	}

	public void updateStatus(final String id, final StatusType status) throws PersistRotuloException {

		try {
			
			collection.updateOne(Filters.eq("_id", id), Updates.set("status", status.getValue()));

		} catch (Throwable t) {
			LOGGER.error(t.getMessage());
			throw new PersistRotuloException(t);
		}
	}

	public void delete(final String id) throws PersistRotuloException {

		try {

			collection.updateOne(Filters.eq("_id", id), Updates.set("active", false));

		} catch (Throwable t) {
			LOGGER.error(t.getMessage());
			throw new PersistRotuloException(t);
		}
	}

	public void persistRotulo(Rotulo model) throws PersistRotuloException {

		try {

			Document rotulo = buildRotulo(model, true);

			rotulo.append("history", buildHistory(model));

			collection.replaceOne(Filters.eq("_id", model.getId()), rotulo, new ReplaceOptions().upsert(true));

		} catch (Throwable t) {
			LOGGER.error(t.getMessage());
			throw new PersistRotuloException(t);
		}
	}

	public Set<Document> buildHistory(Rotulo model) throws SearchRotuloException, PersistClientException {

		Rotulo modelHistory = find(model.getId());

		if (modelHistory == null)
			return null;

		Set<Document> documentSetList = new HashSet<Document>();

		if (modelHistory.getHistory() != null) {

			for (Rotulo mh : modelHistory.getHistory()) {
				documentSetList.add(buildRotulo(mh, false));
			}
		}

		documentSetList.add(buildRotulo(modelHistory, false));

		return documentSetList;
	}

	public Document buildRotulo(Rotulo model, final boolean addClient) throws PersistClientException {

		Document rotulo = new Document();
		
		try {
			rotulo.append("informacao_nutricional", buildInformacaoNutricional(model.getInformacaoNutricional()));
		} catch (IllegalArgumentException e) {
			LOGGER.info(e.getMessage());
		}

		if (addClient) {
			
			String dataPattern = "dd/MM/yyyy HH:mm:ss";
			DateTimeFormatter dTF = DateTimeFormatter.ofPattern(dataPattern);
			model.setData(dTF.format(LocalDateTime.now()));
			
			
			try {

				String clientId = clientDataAccess.persistClient(model.getClient());
				rotulo.append("client_id", clientId);
			} catch (IllegalArgumentException e) {
				LOGGER.info(e.getMessage());
			}

		}
		try {
			rotulo.append("denominacao_produto", buildSimpleObject(model.getDenominacaoProduto()));
		} catch (IllegalArgumentException e) {
			LOGGER.info(e.getMessage());
		}

		try {
			rotulo.append("conservacao_produto", buildConservacaoProduto(model.getConservacaoProduto()));
		} catch (IllegalArgumentException e) {
			LOGGER.info(e.getMessage());
		}

		try {
			rotulo.append("distribuidor", buildSimpleObject(model.getDistribuidor()));
		} catch (IllegalArgumentException e) {
			LOGGER.info(e.getMessage());
		}

		try {
			rotulo.append("tartrazina", buildSimpleObject(model.getTartrazina()));
		} catch (IllegalArgumentException e) {
			LOGGER.info(e.getMessage());
		}

		try {
			rotulo.append("aromatizante", buildSimpleObject(model.getAromatizante()));
		} catch (IllegalArgumentException e) {
			LOGGER.info(e.getMessage());
		}

		try {
			rotulo.append("derivados_lacteos", buildSimpleObject(model.getDerivadosLacteos()));
		} catch (IllegalArgumentException e) {
			LOGGER.info(e.getMessage());
		}
		try {
			rotulo.append("importador", buildSimpleObject(model.getImportador()));
		} catch (IllegalArgumentException e) {
			LOGGER.info(e.getMessage());
		}
		try {
			rotulo.append("aspartame_fenilalanina", buildSimpleObject(model.getAspartameFenilalanina()));
		} catch (IllegalArgumentException e) {
			LOGGER.info(e.getMessage());
		}
		try {
			rotulo.append("peso_liquido", buildPesoLiquido(model.getPesoLiquido()));
		} catch (IllegalArgumentException e) {
			LOGGER.info(e.getMessage());
		}
		try {
			rotulo.append("alergicos", buildSimpleObject(model.getAlergicos()));
		} catch (IllegalArgumentException e) {
			LOGGER.info(e.getMessage());
		}
		try {
			rotulo.append("data_fabricacao", buildSimpleObject(model.getDataFabricacao()));
		} catch (IllegalArgumentException e) {
			LOGGER.info(e.getMessage());
		}
		try {
			rotulo.append("sac", buildSimpleObject(model.getSac()));
		} catch (IllegalArgumentException e) {
			LOGGER.info(e.getMessage());
		}
		try {
			rotulo.append("gluten", buildSimpleObject(model.getGluten()));
		} catch (IllegalArgumentException e) {
			LOGGER.info(e.getMessage());
		}
		try {
			rotulo.append("gluten_alergenos", buildSimpleObject(model.getGlutenAlergenos()));
		} catch (IllegalArgumentException e) {
			LOGGER.info(e.getMessage());
		}
		try {
			rotulo.append("prazo_validade", buildSimpleObject(model.getPrazoValidade()));
		} catch (IllegalArgumentException e) {
			LOGGER.info(e.getMessage());
		}
		try {
			rotulo.append("transgenico", buildSimpleObject(model.getTransgenico()));
		} catch (IllegalArgumentException e) {
			LOGGER.info(e.getMessage());
		}
		try {
			rotulo.append("industria_origem", buildSimpleObject(model.getIndustriaOrigem()));
		} catch (IllegalArgumentException e) {
			LOGGER.info(e.getMessage());
		}
		try {
			rotulo.append("outros", buildOutros(model.getOutros()));
		} catch (IllegalArgumentException e) {
			LOGGER.info(e.getMessage());
		}
		try {
			rotulo.append("lote", buildSimpleObject(model.getLote()));
		} catch (IllegalArgumentException e) {
			LOGGER.info(e.getMessage());
		}
		try {
			rotulo.append("registro_mapa", buildSimpleObject(model.getRegistroMAPA()));
		} catch (IllegalArgumentException e) {
			LOGGER.info(e.getMessage());
		}
		try {
			rotulo.append("ingredientes", buildSimpleObject(model.getIngredientes()));
		} catch (IllegalArgumentException e) {
			LOGGER.info(e.getMessage());
		}
		try {
			rotulo.append("produtor", buildSimpleObject(model.getProdutor()));
		} catch (IllegalArgumentException e) {
			LOGGER.info(e.getMessage());
		}
		try {
			rotulo.append("azeite", buildAzeite(model.getAzeite()));
		} catch (IllegalArgumentException e) {
			LOGGER.info(e.getMessage());
		}
		try {
			rotulo.append("status", model.getStatus().getValue());
		} catch (IllegalArgumentException e) {
			LOGGER.info(e.getMessage());
		}

		rotulo.append("_id", model.getId());		
		rotulo.append("data", model.getData());
		rotulo.append("produto", model.getProduto());
		rotulo.append("active", true);

		
		return rotulo;

	}

	public Document buildOutros(final List<SimpleObject> model) {

		if (model == null || model.isEmpty())
			throw new IllegalArgumentException("Invalid object!");

		List<Document> documents = new LinkedList<Document>();

		for (SimpleObject simpleObject : model) {
			documents.add(buildSimpleObject(simpleObject));
		}

		Document document = new Document();
		document.append("informacoes_adicionais", documents);

		return document;
	}

	public Document buildAzeite(final Azeite model) {

		if (model == null)
			throw new IllegalArgumentException("Invalid object!");

		Document document = new Document();
		document.append("index_report", model.getIndexReport());

		try {
			document.append("denominacao", buildSimpleObject(model.getDenominacao()));
		} catch (Throwable t) {
			LOGGER.info(t.getMessage());
		}

		try {
			document.append("classificacao", buildClassificacaoAzeite(model.getClassificacao()));
		} catch (Throwable t) {
			LOGGER.info(t.getMessage());
		}

		return document;
	}

	public Document buildClassificacaoAzeite(final ClassificacaoAzeite model) {

		if (model == null)
			throw new IllegalArgumentException("Invalid object!");

		Document document = new Document();
		document.append("index_report", model.getIndexReport());
		document.append("descricao", model.getDescricao());
		document.append("acidez-livre", buildClassificacaoAzeiteItem(model.getAcidezLivre()));
		document.append("indices-peroxidos", buildClassificacaoAzeiteItem(model.getIndicesPeroxidos()));
		document.append("ext-espec-ultravioleta-270", buildClassificacaoAzeiteItem(model.getExtEspecUltravioleta270()));
		document.append("ext-espec-ultravioleta-232", buildClassificacaoAzeiteItem(model.getExtEspecUltravioleta232()));
		document.append("ext-espec-ultravioleta-delta",
				buildClassificacaoAzeiteItem(model.getExtEspecUltravioletaDelta()));

		return document;
	}

	public Document buildClassificacaoAzeiteItem(final ClassificacaoAzeiteItem model) {

		if (model == null)
			throw new IllegalArgumentException("Invalid object!");

		Document document = new Document();
		document.append("item", model.getItem());
		document.append("extra-virgem", model.getExtraVirgem());
		document.append("virgem", model.getVirgem());

		return document;
	}

	public Document buildSimpleObject(final SimpleObject model) {

		if (model == null)
			throw new IllegalArgumentException("Invalid object!");

		Document document = new Document();
		document.append("index_report", model.getIndexReport());
		document.append("descricao", model.getDescricao());

		return document;
	}

	public Document buildPesoLiquido(final PesoLiquido model) {

		if (model == null)
			throw new IllegalArgumentException("Invalid object!");

		Document document = new Document();
		document.append("descricao", model.getDescricao());
		document.append("peso", model.getPeso());
		document.append("index_report", model.getIndexReport());

		return document;
	}

	public Document buildConservacaoProduto(final ConservacaoProduto model) {

		if (model == null)
			throw new IllegalArgumentException("Invalid object!");

		Document document = new Document();
		document.append("validade_produto", model.getValidadeProduto());
		document.append("index_report", model.getIndexReport());

		return document;
	}

	public Document buildCliente(final Client model) {

		if (model == null)
			throw new IllegalArgumentException("Invalid object!");

		return clientDataAccess.builClient(model);
	}

	private Document buildQuantidadeNutricional(final QuantidadeNutricional model) {

		if (model == null)
			throw new IllegalArgumentException("Invalid object!");

		Document document = new Document();

		document.append("valor_energetico", model.getValorEnergetico());
		document.append("carboidratos", model.getCarboidratos());
		document.append("proteinas", model.getProteinas());
		document.append("gorduras_totais", model.getGordurasTotais());
		document.append("gorduras_saturadas", model.getGordurasSaturadas());

		if (!StringUtils.isAllBlank(model.getGordurasTrans())) {
			document.append("gorduras_trans", model.getGordurasTrans());
		} else {
			document.append("gorduras_trans", "**");
		}

		document.append("fibra_alimentar", model.getFibraAlimentar());
		document.append("sodio", model.getSodio());

		if (model.getVlrEnergeticoKcal() > 0.0) {
			document.append("valor_energetico_kcal", model.getVlrEnergeticoKcal());
		}
		if (model.getVlrEnergeticoKj() > 0.0) {
			document.append("valor_energetico_kj", model.getVlrEnergeticoKj());
		}

		return document;
	}

	public Document buildInformacaoNutricional(final InformacaoNutricional model) {

		if (model == null)
			throw new IllegalArgumentException("Invalid object!");

		Document document = new Document();

		document.append("descricao", model.getDescricao());
		document.append("peso_total_gramas", String.valueOf(model.getDadosBase().getPeso()));
		document.append("valor_porcao", String.valueOf(model.getDadosBase().getPesoPorcao()));
		document.append("legislacao", model.getLegislacao());
		document.append("descritivo_porcao", model.getPorcao());

		document.append("quantidade_porcao", buildQuantidadeNutricional(model.getQtdPorcao()));
		document.append("percentual_valor_diario", buildQuantidadeNutricional(model.getPercVlrDiario()));

		document.append("index_report", model.getIndexReport());

		return document;
	}

	@SuppressWarnings("unchecked")
	public Set<Rotulo> builHistory(Document document) {

		ArrayList<Document> documentHistorySet = (ArrayList<Document>) document.get("history");

		if (documentHistorySet == null)
			return null;

		Set<Rotulo> resultSet = new HashSet<Rotulo>();

		for (Document doc : documentHistorySet) {
			resultSet.add(builRotulo(doc));
		}

		return resultSet;
	}

	public Rotulo builRotulo(Document document) {

		Rotulo rotulo = new Rotulo();

		rotulo.setId(document.getString("_id"));
		rotulo.setData(document.getString("data"));
		rotulo.setProduto(document.getString("produto"));

		final String status = document.getString("status");

		if (!StringUtils.isBlank(status)) {

			if (status.equals(StatusType.EM_ANALISE.getValue())) {
				rotulo.setStatus(StatusType.EM_ANALISE);
			} else if (status.equals(StatusType.ENVIADO.getValue())) {
				rotulo.setStatus(StatusType.ENVIADO);
			} else if (status.equals(StatusType.RE_ENVIADO.getValue())) {
				rotulo.setStatus(StatusType.RE_ENVIADO);
			} else if (status.equals(StatusType.REVISAO.getValue())) {
				rotulo.setStatus(StatusType.REVISAO);
			}
		}

		try {
			rotulo.setInformacaoNutricional(
					buildInformacaoNutricional((Document) document.get("informacao_nutricional")));
		} catch (IllegalArgumentException e) {
			LOGGER.info(e.getMessage());
		}
		try {
			rotulo.setClient(clientDataAccess.find((String) document.get("client_id")));
		} catch (IllegalArgumentException e) {
			LOGGER.info(e.getMessage());
		} catch (SearchRotuloException e) {
			LOGGER.error(e.getMessage());
		}
		try {
			rotulo.setDenominacaoProduto(buildSimpleObject((Document) document.get("denominacao_produto")));
		} catch (IllegalArgumentException e) {
			LOGGER.info(e.getMessage());
		}
		try {
			rotulo.setConservacaoProduto(buildConservacaoProduto((Document) document.get("conservacao_produto")));
		} catch (IllegalArgumentException e) {
			LOGGER.info(e.getMessage());
		}
		try {
			rotulo.setDistribuidor(buildSimpleObject((Document) document.get("distribuidor")));
		} catch (IllegalArgumentException e) {
			LOGGER.info(e.getMessage());
		}
		try {
			rotulo.setTartrazina(buildSimpleObject((Document) document.get("tartrazina")));
		} catch (IllegalArgumentException e) {
			LOGGER.info(e.getMessage());
		}
		try {
			rotulo.setAromatizante(buildSimpleObject((Document) document.get("aromatizante")));
		} catch (IllegalArgumentException e) {
			LOGGER.info(e.getMessage());
		}
		try {
			rotulo.setDerivadosLacteos(buildSimpleObject((Document) document.get("derivados_lacteos")));
		} catch (IllegalArgumentException e) {
			LOGGER.info(e.getMessage());
		}
		try {
			rotulo.setImportador(buildSimpleObject((Document) document.get("importador")));
		} catch (IllegalArgumentException e) {
			LOGGER.info(e.getMessage());
		}
		try {
			rotulo.setAspartameFenilalanina(buildSimpleObject((Document) document.get("aspartame_fenilalanina")));
		} catch (IllegalArgumentException e) {
			LOGGER.info(e.getMessage());
		}
		try {
			rotulo.setPesoLiquido(buildPesoLiquido((Document) document.get("peso_liquido")));
		} catch (IllegalArgumentException e) {
			LOGGER.info(e.getMessage());
		}
		try {
			rotulo.setAlergicos(buildSimpleObject((Document) document.get("alergicos")));
		} catch (IllegalArgumentException e) {
			LOGGER.info(e.getMessage());
		}
		try {
			rotulo.setDataFabricacao(buildSimpleObject((Document) document.get("data_fabricacao")));
		} catch (IllegalArgumentException e) {
			LOGGER.info(e.getMessage());
		}
		try {
			rotulo.setSac(buildSimpleObject((Document) document.get("sac")));
		} catch (IllegalArgumentException e) {
			LOGGER.info(e.getMessage());
		}
		try {
			rotulo.setGluten(buildSimpleObject((Document) document.get("gluten")));
		} catch (IllegalArgumentException e) {
			LOGGER.info(e.getMessage());
		}
		try {
			rotulo.setGlutenAlergenos(buildSimpleObject((Document) document.get("gluten_alergenos")));
		} catch (IllegalArgumentException e) {
			LOGGER.info(e.getMessage());
		}
		try {
			rotulo.setPrazoValidade(buildSimpleObject((Document) document.get("prazo_validade")));
		} catch (IllegalArgumentException e) {
			LOGGER.info(e.getMessage());
		}
		try {
			rotulo.setTransgenico(buildSimpleObject((Document) document.get("transgenico")));
		} catch (IllegalArgumentException e) {
			LOGGER.info(e.getMessage());
		}
		try {
			rotulo.setIndustriaOrigem(buildSimpleObject((Document) document.get("industria_origem")));
		} catch (IllegalArgumentException e) {
			LOGGER.info(e.getMessage());
		}
		try {
			rotulo.setOutros(buildOutros((Document) document.get("outros")));
		} catch (IllegalArgumentException e) {
			LOGGER.info(e.getMessage());
		}
		try {
			rotulo.setLote(buildSimpleObject((Document) document.get("lote")));
		} catch (IllegalArgumentException e) {
			LOGGER.info(e.getMessage());
		}
		try {
			rotulo.setRegistroMAPA(buildSimpleObject((Document) document.get("registro_mapa")));
		} catch (IllegalArgumentException e) {
			LOGGER.info(e.getMessage());
		}
		try {
			rotulo.setIngredientes(buildSimpleObject((Document) document.get("ingredientes")));
		} catch (IllegalArgumentException e) {
			LOGGER.info(e.getMessage());
		}
		try {
			rotulo.setProdutor(buildSimpleObject((Document) document.get("produtor")));
		} catch (IllegalArgumentException e) {
			LOGGER.info(e.getMessage());
		}
		try {
			rotulo.setAzeite(buildAzeite((Document) document.get("azeite")));
		} catch (IllegalArgumentException e) {
			LOGGER.info(e.getMessage());
		}
		try {
			rotulo.setStatus(StatusType.valueOf(document.getString("status")));
		} catch (Throwable e) {
			LOGGER.info(e.getMessage());
		}

		return rotulo;
	}

	@SuppressWarnings("unchecked")
	private List<SimpleObject> buildOutros(Document document) {

		if (document == null)
			throw new IllegalArgumentException("Invalid document!");

		List<SimpleObject> results = new LinkedList<SimpleObject>();

		List<Document> documents = (List<Document>) document.get("informacoes_adicionais");

		if (documents == null)
			throw new IllegalArgumentException("Invalid document!");

		for (Document doc : documents) {
			results.add(buildSimpleObject(doc));
		}

		return results;
	}

	private PesoLiquido buildPesoLiquido(Document document) {

		if (document == null)
			throw new IllegalArgumentException("Invalid document!");

		PesoLiquido model = new PesoLiquido();
		model.setDescricao(document.getString("descricao"));
		model.setPeso(document.getString("peso"));
		model.setIndexReport(buildIndexReport(document));

		return model;
	}

	@SuppressWarnings("unchecked")
	private ConservacaoProduto buildConservacaoProduto(Document document) {

		if (document == null)
			throw new IllegalArgumentException("Invalid document!");

		ConservacaoProduto model = new ConservacaoProduto();
		model.setValidadeProduto(((Map<String, String>) document.get("validade_produto")));
		model.setIndexReport(buildIndexReport(document));

		return model;
	}

	private SimpleObject buildSimpleObject(Document document) {

		if (document == null)
			throw new IllegalArgumentException("Invalid document!");

		SimpleObject model = new SimpleObject();
		model.setDescricao(document.getString("descricao"));
		model.setIndexReport(buildIndexReport(document));

		return model;
	}

	private InformacaoNutricional buildInformacaoNutricional(Document document) {

		if (document == null)
			throw new IllegalArgumentException("Invalid document!");

		InformacaoNutricional model = new InformacaoNutricional();

		model.setDescricao(document.getString("descricao"));
		model.setPesoTotalGramas((String) document.get("peso_total_gramas"));
		model.setValorPorcao(document.getString("valor_porcao"));

		model.getDadosBase().setPeso(Double.parseDouble(model.getPesoTotalGramas()));
		model.getDadosBase().setPesoPorcao(Double.parseDouble(model.getValorPorcao()));

		model.setLegislacao(document.getString("legislacao"));
		model.setPorcao(document.getString("descritivo_porcao"));

		model.setIndexReport(buildIndexReport(document));

		model.setQtdPorcao(buildQuantidadeNutricional((Document) document.get("quantidade_porcao")));
		model.setPercVlrDiario(buildQuantidadeNutricional((Document) document.get("percentual_valor_diario")));

		return model;
	}

	private QuantidadeNutricional buildQuantidadeNutricional(Document document) {

		if (document == null)
			throw new IllegalArgumentException("Invalid document!");

		QuantidadeNutricional model = new QuantidadeNutricional();

		model.setValorEnergetico(document.getString("valor_energetico"));
		model.setCarboidratos(document.getString("carboidratos"));
		model.setProteinas(document.getString("proteinas"));
		model.setGordurasTotais(document.getString("gorduras_totais"));
		model.setGordurasSaturadas(document.getString("gorduras_saturadas"));

		if (!StringUtils.isAllBlank(document.getString("gorduras_trans"))) {
			model.setGordurasTrans(document.getString("gorduras_trans"));
		} else {
			model.setGordurasTrans("**");
		}

		model.setFibraAlimentar(document.getString("fibra_alimentar"));
		model.setSodio(document.getString("sodio"));

		if (document.getDouble("valor_energetico_kcal") != null && document.getDouble("valor_energetico_kcal") > 0.0) {
			model.setVlrEnergeticoKcal(document.getDouble("valor_energetico_kcal"));
		}
		if (document.getDouble("valor_energetico_kj") != null && document.getDouble("valor_energetico_kj") > 0.0) {
			model.setVlrEnergeticoKj(document.getDouble("valor_energetico_kj"));
		}

		return model;
	}

	private Azeite buildAzeite(Document document) {
		if (document == null)
			throw new IllegalArgumentException("Invalid document!");

		Azeite model = new Azeite();

		model.setIndexReport(buildIndexReport(document));

		try {
			model.setClassificacao(buildClassificacaoAzeite((Document) document.get("classificacao")));
		} catch (IllegalArgumentException e) {
			LOGGER.info(e.getMessage());
		}

		try {
			model.setDenominacao(buildSimpleObject((Document) document.get("denominacao")));
		} catch (IllegalArgumentException e) {
			LOGGER.info(e.getMessage());
		}

		return model;
	}

	private ClassificacaoAzeite buildClassificacaoAzeite(Document document) {
		if (document == null)
			throw new IllegalArgumentException("Invalid document!");

		ClassificacaoAzeite model = new ClassificacaoAzeite();

		model.setIndexReport(buildIndexReport(document));

		model.setAcidezLivre(buildClassificacaoAzeiteItem((Document) document.get("acidez-livre")));
		model.setExtEspecUltravioleta232(
				buildClassificacaoAzeiteItem((Document) document.get("ext-espec-ultravioleta-232")));
		model.setExtEspecUltravioleta270(
				buildClassificacaoAzeiteItem((Document) document.get("ext-espec-ultravioleta-270")));
		model.setExtEspecUltravioletaDelta(
				buildClassificacaoAzeiteItem((Document) document.get("ext-espec-ultravioleta-delta")));
		model.setIndicesPeroxidos(buildClassificacaoAzeiteItem((Document) document.get("indices-peroxidos")));
		model.setDescricao(document.getString("descricao"));

		return model;

	}

	private ClassificacaoAzeiteItem buildClassificacaoAzeiteItem(Document document) {
		if (document == null)
			throw new IllegalArgumentException("Invalid document!");

		ClassificacaoAzeiteItem model = new ClassificacaoAzeiteItem();

		model.setExtraVirgem(document.getString("extra-virgem"));
		model.setVirgem(document.getString("virgem"));
		model.setItem(document.getString("item"));

		return model;

	}

	private int buildIndexReport(final Document document) {

		try {

			return document.getInteger("index_report");

		} catch (Throwable t) {
			LOGGER.info(t.getMessage());
		}
		return 0;
	}

	/**
	 * Just use it on Tests
	 */
	public void setClientDataAccess(ManagerClientBean clientDataAccess) {
		this.clientDataAccess = clientDataAccess;
	}
}