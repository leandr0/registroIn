/**
 * 
 */
package com.lrgoncalves.registroin.rotulagem;

import static java.util.logging.Level.INFO;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.apache.commons.lang3.StringUtils;
import org.bson.Document;

import com.lrgoncalves.registroin.rotulagem.data.RotuloDAO;
import com.lrgoncalves.registroin.rotulagem.data.entity.Client;
import com.lrgoncalves.registroin.rotulagem.data.entity.ConservacaoProduto;
import com.lrgoncalves.registroin.rotulagem.data.entity.InformacaoNutricional;
import com.lrgoncalves.registroin.rotulagem.data.entity.PesoLiquido;
import com.lrgoncalves.registroin.rotulagem.data.entity.QuantidadeNutricional;
import com.lrgoncalves.registroin.rotulagem.data.entity.Rotulo;
import com.lrgoncalves.registroin.rotulagem.data.entity.SimpleObject;
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
public class RotulagemApp {

	private static final Logger LOGGER = Logger.getLogger(RotulagemApp.class.getName());

	private static MongoClient mongoDbClient = null;

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		persistRotulo(calculoNutricional());
		//queryRotulo();

	}

	private static MongoCollection<Document> getCollection() {

		MongoCollection<Document> collection = null;

		try {

			if (mongoDbClient == null) {
				mongoDbClient = new MongoClient("localhost", 27017);
			}

			MongoDatabase database = mongoDbClient.getDatabase("registroin");

			collection = database.getCollection("rotulos");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return collection;
	}

	private static void queryRotulo() {

		try {

			FindIterable<Document> iterable = getCollection().find(Filters.regex("informacao_nutricional.descricao", "itálic", "i"));

			MongoCursor<Document> cursor = iterable.cursor();

			while (cursor.hasNext()) {
				Document rotulo = cursor.next();
				Rotulo model = builRotulo(rotulo);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			if (mongoDbClient != null)
				mongoDbClient.close();

		}
	}

	
	private static Rotulo builRotulo(Document model) {
		
		Rotulo rotulo = new Rotulo();
		
		rotulo.setId(model.getString("_id"));
		rotulo.setData(model.getString("data"));
		rotulo.setInformacaoNutricional(buildInformacaoNutricional((Document) model.get("informacao_nutricional")));
		rotulo.setClient(buildCliente((Document) model.get("cliente")));
		rotulo.setDenominacaoProduto(buildSimpleObject((Document) model.get("denominacao_produto")));
		rotulo.setConservacaoProduto(buildConservacaoProduto((Document) model.get("conservacao_produto")));
		rotulo.setDistribuidor(buildSimpleObject((Document) model.get("distribuidor")));
		rotulo.setTartrazina(buildSimpleObject((Document) model.get("tartrazina")));
		rotulo.setAromatizante(buildSimpleObject((Document) model.get("aromatizante")));
		rotulo.setDerivadosLacteos(buildSimpleObject((Document) model.get("derivados_lacteos")));
		rotulo.setImportador( buildSimpleObject((Document) model.get("importador")));
		rotulo.setAspartameFenilalanina(buildSimpleObject((Document) model.get("aspartame_fenilalanina")));
		rotulo.setPesoLiquido(buildPesoLiquido((Document) model.get("peso_liquido")));
		rotulo.setAlergicos(buildSimpleObject((Document) model.get("alergicos")));
		rotulo.setDataFabricacao(buildSimpleObject((Document) model.get("data_fabricacao")));
		rotulo.setSac(buildSimpleObject((Document) model.get("sac")));
		rotulo.setGluten(buildSimpleObject((Document) model.get("gluten")));
		rotulo.setGlutenAlergenos(buildSimpleObject((Document) model.get("gluten_alergenos")));
		rotulo.setPrazoValidade(buildSimpleObject((Document) model.get("prazo_validade")));
		rotulo.setTransgenico(buildSimpleObject((Document) model.get("transgenico")));
		rotulo.setIndustriaOrigem(buildSimpleObject((Document) model.get("industria_origem")));
		rotulo.setUsoProduto( buildSimpleObject((Document) model.get("uso_produto")));
		rotulo.setLote( buildSimpleObject((Document) model.get("lote")));
		rotulo.setRegistroMAPA(buildSimpleObject((Document) model.get("registro_mapa")));
		rotulo.setIngredientes(buildSimpleObject((Document) model.get("ingredientes")));
		rotulo.setProdutor(buildSimpleObject((Document) model.get("produtor")));
		
		return rotulo;
	}
	
	
	private static PesoLiquido buildPesoLiquido(Document model) {
		
		PesoLiquido document = new PesoLiquido();
		document.setDescricao(model.getString("descricao"));
		document.setPeso(model.getString("peso"));
		
		return document;
	}
	
	@SuppressWarnings("unchecked")
	private static ConservacaoProduto buildConservacaoProduto(Document model) {
		
		ConservacaoProduto document = new ConservacaoProduto();
		document.setValidadeProduto(((Map<String, String>) model.get("validade_produto")));
		
		return document;
	}
	
	private static SimpleObject buildSimpleObject(Document model) {
		
		SimpleObject document = new SimpleObject();
		document.setDescricao(model.getString("descricao"));
		
		return document;
	}
	
	private static Client buildCliente(Document model) {
		
		Client document = new Client();
		document.setNome(model.getString("nome"));
		document.setProduto(model.getString("produto"));
		document.setEmail(model.getString("email"));
		
		return document;
	}
	
	
	private static InformacaoNutricional buildInformacaoNutricional(Document document) {

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

	private static QuantidadeNutricional buildQuantidadeNutricional(Document model) {
		
		QuantidadeNutricional document = new QuantidadeNutricional();
		
		document.setValorEnergetico(model.getString("valor_energetico"));
		document.setCarboidratos(model.getString("carboidratos"));
		document.setProteinas(model.getString("proteinas"));
		document.setGordurasTotais(model.getString("gorduras_totais"));
		document.setGordurasSaturadas(model.getString("gorduras_saturadas"));
		
		if(!StringUtils.isAllBlank(model.getString("gorduras_trans"))) {
			document.setGordurasTrans(model.getString("gorduras_trans"));
		}else {
			document.setGordurasTrans("**");
		}
		
		document.setFibraAlimentar(model.getString("fibra_alimentar"));
		document.setSodio(model.getString("sodio"));
		
		if(model.getDouble("valor_energetico_kcal") != null && model.getDouble("valor_energetico_kcal") > 0.0) {
			document.setVlrEnergeticoKcal(model.getDouble("valor_energetico_kcal"));
		}
		if(model.getDouble("valor_energetico_kj") != null && model.getDouble("valor_energetico_kj") > 0.0) {
			document.setVlrEnergeticoKj(model.getDouble("valor_energetico_kj"));
		}
		
		return document;
	}
	
	
	private static void persistRotulo(InformacaoNutricional inf) {

		try {

			RotuloDAO instance = new RotuloDAO();
			Rotulo model = new Rotulo();

			SimpleObject produtor = new SimpleObject();
			SimpleObject ingredientes = new SimpleObject();
			SimpleObject registroMAPA = new SimpleObject();
			SimpleObject lote = new SimpleObject();
			SimpleObject usoProduto = new SimpleObject();
			SimpleObject industriaOrigem = new SimpleObject();
			SimpleObject transgenico = new SimpleObject();
			SimpleObject prazoValidade = new SimpleObject();
			SimpleObject glutenAlergenos = new SimpleObject();
			SimpleObject gluten = new SimpleObject();
			SimpleObject sac = new SimpleObject();
			SimpleObject dataFabricacao = new SimpleObject();
			SimpleObject alergicos = new SimpleObject();

			PesoLiquido pesoLiquido = new PesoLiquido();

			SimpleObject aspartameFenilalanina = new SimpleObject();
			SimpleObject importador = new SimpleObject();
			SimpleObject derivadosLacteos = new SimpleObject();
			SimpleObject aromatizante = new SimpleObject();
			SimpleObject tartrazina = new SimpleObject();
			SimpleObject distribuidor = new SimpleObject();

			ConservacaoProduto conservacaoProduto = new ConservacaoProduto();
			Map<String, String> validadeProduto = new HashMap<String, String>();

			SimpleObject denominacaoProduto = new SimpleObject();

			produtor.setDescricao("Inserir informações do produtor .....");
			ingredientes.setDescricao("Corrigir os ingredientes para .....");
			registroMAPA
					.setDescricao("Inserir a informação: \"Registro no Ministério da Agricultura sob nº XXXX/XXXXX.\"");
			lote.setDescricao("Inserir número do Lote.");
			usoProduto.setDescricao("Intruções de Uso do Produto / Modo de preparo");
			industriaOrigem.setDescricao("Acrescentar os termos : - \"Indústria <País de Origem>\"");
			transgenico
					.setDescricao("Se presente em quantidade superior a 1%, inserir o logo de TRANSGENICO no rótulo.");
			prazoValidade.setDescricao("Inserir Data de Validade no formato DD/MM/AA");
			glutenAlergenos.setDescricao(
					"Todos os caracteres devem apresentar altura mínima de 1mm, com exceção da informação de GLÚTEN e ALERGENOS, que devem ter altura mínima de 2mm, e do peso líquido que deve ter a altura mínima indicada anteriormente. ");
			gluten.setDescricao(
					"Inserir a inscrição \"NÃO CONTÉM GLÚTEN\" logo após a lista de ingredientes. Em caixa alta e negrito.");
			sac.setDescricao("Inserir informação para SAC.");
			dataFabricacao.setDescricao("Inserir Data de Fabricação no formato DD/MM/AA");
			alergicos.setDescricao(
					"Inserir a inscrição \"ALÉRGICOS: CONTÉM XXXXX E DERIVADOS DE XXXXX\", em caixa alta e negrito, logo após a inscrição \"NÃO CONTÉM GLÚTEN\"");

			pesoLiquido.setPeso("300");
			pesoLiquido.setDescricao(
					"Os números devem ter altura mínima de 4 mm, e a unidade \"g\", altura mínima de 2/3 o tamanho do numeral.");

			aspartameFenilalanina
					.setDescricao("Inserir a informação: \"CONTÉM FENILALANINA\", em caixa alta e negrito");
			importador.setDescricao("Inserir informações do Importador.");
			derivadosLacteos.setDescricao(
					"Inserir a inscrição \"CONTÉM LACTOSE\" logo após a inscrição \"NÃO CONTÉM GLÚTEN\". Em negrito e caixa alta.");
			aromatizante.setDescricao("Inserir o termo \"AROMATIZADO ..\"");
			tartrazina.setDescricao(
					"A lista de ingredientes deve apresentar o nome do corante tartrazina INS 102 por extenso, em caixa alta e negrito.");
			distribuidor.setDescricao("Inserir informações do Distribuídor ....");

			validadeProduto.put("Validade a -18˚C(freezer):", "DD/MM/AA");
			validadeProduto.put("Validade a -4˚C (congelador):", "DD/MM/AA");
			validadeProduto.put("Validade a 4˚C (refrigerador):", "DD/MM/AA");
			conservacaoProduto.setValidadeProduto(validadeProduto);

			denominacaoProduto.setDescricao("Sugerimos adequar a denominação do produto para... ");

			Client client = new Client();
			client.setNome("Parmalat");
			client.setProduto("Leite de Texugo 1L");
			client.setEmail("qualidade@parmalat.com.br");

			QuantidadeNutricional quantidadePorcao = new QuantidadeNutricional();
			quantidadePorcao.setValorEnergetico("278.0kcal/1156.0kJ g");
			quantidadePorcao.setCarboidratos("8.5 g");
			quantidadePorcao.setProteinas("16 g");
			quantidadePorcao.setGordurasTotais("20 g");
			quantidadePorcao.setGordurasSaturadas("8.8 g");
			quantidadePorcao.setGordurasTrans("8.2 g");
			quantidadePorcao.setFibraAlimentar("3.0 g");
			quantidadePorcao.setSodio("60 mg");

			QuantidadeNutricional percentualValorDiario = new QuantidadeNutricional();
			percentualValorDiario.setValorEnergetico("14");
			percentualValorDiario.setCarboidratos("3");
			percentualValorDiario.setProteinas("21");
			percentualValorDiario.setGordurasTotais("36");
			percentualValorDiario.setGordurasSaturadas("40");
			percentualValorDiario.setGordurasTrans("**");
			percentualValorDiario.setFibraAlimentar("12");
			percentualValorDiario.setSodio("2.0");

			InformacaoNutricional informacaoNutricional = new InformacaoNutricional();
			informacaoNutricional.setDescricao(
					"A Informação Nutricional deve seguir EXATAMENTE o modelo permitido pela ANVISA, Com Trans em itálico, e seus valores devem ser ajustados para:");
			informacaoNutricional.setPesoTotalGramas("1.000");
			informacaoNutricional.setValorPorcao("250 ml");
			informacaoNutricional.setLegislacao(
					"(*) Valores Diários de referência com base em uma dieta de 2000kcal ou 8400kJ. Seus valores diários podem ser maiores ou menores dependendo das necessidades energéticas.(**) VD não estabelecido.");

			informacaoNutricional.setQtdPorcao(quantidadePorcao);
			informacaoNutricional.setPercVlrDiario(percentualValorDiario);

			if (inf != null) {
				informacaoNutricional = inf;
			}

			model.setInformacaoNutricional(informacaoNutricional);
			model.setClient(client);
			model.setDenominacaoProduto(denominacaoProduto);
			model.setConservacaoProduto(conservacaoProduto);
			model.setDistribuidor(distribuidor);
			model.setTartrazina(tartrazina);
			model.setAromatizante(aromatizante);
			model.setDerivadosLacteos(derivadosLacteos);
			model.setImportador(importador);
			model.setAspartameFenilalanina(aspartameFenilalanina);
			model.setPesoLiquido(pesoLiquido);
			model.setAlergicos(alergicos);
			model.setDataFabricacao(dataFabricacao);
			model.setSac(sac);
			model.setGluten(gluten);
			model.setGlutenAlergenos(glutenAlergenos);
			model.setPrazoValidade(prazoValidade);
			model.setTransgenico(transgenico);
			model.setIndustriaOrigem(industriaOrigem);
			model.setUsoProduto(usoProduto);
			model.setLote(lote);
			model.setRegistroMAPA(registroMAPA);
			model.setProdutor(produtor);
			model.setIngredientes(ingredientes);
			model.setId(java.util.UUID.randomUUID().toString());

			String dataPattern = "dd/MM/yyyy";
			DateTimeFormatter dTF = DateTimeFormatter.ofPattern(dataPattern);

			Document rotulo = new Document();

			rotulo.append("_id", model.getId());
			rotulo.append("data", dTF.format(LocalDateTime.now()));
			rotulo.append("informacao_nutricional",
					instance.buildInformacaoNutricional(model.getInformacaoNutricional()));
			rotulo.append("cliente", instance.buildCliente(model.getClient()));
			rotulo.append("denominacao_produto", instance.buildSimpleObject(model.getDenominacaoProduto()));
			rotulo.append("conservacao_produto", instance.buildConservacaoProduto(model.getConservacaoProduto()));
			rotulo.append("distribuidor", instance.buildSimpleObject(model.getDistribuidor()));
			rotulo.append("tartrazina", instance.buildSimpleObject(model.getTartrazina()));
			rotulo.append("aromatizante", instance.buildSimpleObject(model.getAromatizante()));
			rotulo.append("derivados_lacteos", instance.buildSimpleObject(model.getDerivadosLacteos()));
			rotulo.append("importador", instance.buildSimpleObject(model.getImportador()));
			rotulo.append("aspartame_fenilalanina", instance.buildSimpleObject(model.getAspartameFenilalanina()));
			rotulo.append("peso_liquido", instance.buildPesoLiquido(model.getPesoLiquido()));
			rotulo.append("alergicos", instance.buildSimpleObject(model.getAlergicos()));
			rotulo.append("data_fabricacao", instance.buildSimpleObject(model.getDataFabricacao()));
			rotulo.append("sac", instance.buildSimpleObject(model.getSac()));
			rotulo.append("gluten", instance.buildSimpleObject(model.getGluten()));
			rotulo.append("gluten_alergenos", instance.buildSimpleObject(model.getGlutenAlergenos()));
			rotulo.append("prazo_validade", instance.buildSimpleObject(model.getPrazoValidade()));
			rotulo.append("transgenico", instance.buildSimpleObject(model.getTransgenico()));
			rotulo.append("industria_origem", instance.buildSimpleObject(model.getIndustriaOrigem()));
			rotulo.append("uso_produto", instance.buildSimpleObject(model.getUsoProduto()));
			rotulo.append("lote", instance.buildSimpleObject(model.getLote()));
			rotulo.append("registro_mapa", instance.buildSimpleObject(model.getRegistroMAPA()));
			rotulo.append("ingredientes", instance.buildSimpleObject(model.getIngredientes()));
			rotulo.append("produtor", instance.buildSimpleObject(model.getProdutor()));

			getCollection().insertOne(rotulo);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			if (mongoDbClient != null)
				mongoDbClient.close();

		}
	}

	private static InformacaoNutricional calculoNutricional() {

		CalculoNutricional calculoNutricional = new CalculoNutricional();

		InformacaoNutricional informacaoNutricional = new InformacaoNutricional();

		informacaoNutricional.getDadosBase().setPeso(50);
		informacaoNutricional.getDadosBase().setCarboidratos(17);
		informacaoNutricional.getDadosBase().setFibraAlimentar(3.8);
		informacaoNutricional.getDadosBase().setGordurasSaturadas(0.4);
		informacaoNutricional.getDadosBase().setGordurasTrans(0.0);
		informacaoNutricional.getDadosBase().setGorduraTotais(1.8);
		informacaoNutricional.getDadosBase().setProteinas(7.3);
		informacaoNutricional.getDadosBase().setSodio(186);
		informacaoNutricional.getDadosBase().setValorEnergetico(0);
		informacaoNutricional.getDadosBase().setPesoPorcao(50);
		/***
		 * (Carboidratos * 4) + (proteina * 4 ) + (gord totais * 9)
		 */

		LOGGER.log(INFO, "IN Peso :" + informacaoNutricional.getDadosBase().getPeso());
		LOGGER.log(INFO, "IN Valor Energéticos :" + informacaoNutricional.getDadosBase().getValorEnergetico());
		LOGGER.log(INFO, "IN Carboidratos :" + informacaoNutricional.getDadosBase().getCarboidratos());
		LOGGER.log(INFO, "IN Proteínas :" + informacaoNutricional.getDadosBase().getProteinas());
		LOGGER.log(INFO, "IN Gorduras Totais :" + informacaoNutricional.getDadosBase().getGorduraTotais());
		LOGGER.log(INFO, "IN Gorduras Saturadas :" + informacaoNutricional.getDadosBase().getGordurasSaturadas());
		LOGGER.log(INFO, "IN Gorduras Trans :" + informacaoNutricional.getDadosBase().getGordurasTrans());
		LOGGER.log(INFO, "IN Proteínas :" + informacaoNutricional.getDadosBase().getProteinas());
		LOGGER.log(INFO, "IN Fibra Alimentar :" + informacaoNutricional.getDadosBase().getFibraAlimentar());
		LOGGER.log(INFO, "IN Sódio :" + informacaoNutricional.getDadosBase().getSodio());

		final double pesoPorcao = informacaoNutricional.getDadosBase().getPesoPorcao();

		// informacaoNutricional.getQtdPorcao().setPeso(25);
		informacaoNutricional.getQtdPorcao().setCarboidratos(
				calculoNutricional.calculoBaseDesc(informacaoNutricional.getDadosBase().getCarboidratos(), pesoPorcao,
						informacaoNutricional.getDadosBase().getPeso()));
		informacaoNutricional.getQtdPorcao().setFibraAlimentar(
				calculoNutricional.calculoBaseDesc(informacaoNutricional.getDadosBase().getFibraAlimentar(), pesoPorcao,
						informacaoNutricional.getDadosBase().getPeso()));
		informacaoNutricional.getQtdPorcao().setGordurasSaturadas(
				calculoNutricional.calculoBaseDesc(informacaoNutricional.getDadosBase().getGordurasSaturadas(),
						pesoPorcao, informacaoNutricional.getDadosBase().getPeso()));
		informacaoNutricional.getQtdPorcao().setGordurasTrans(
				calculoNutricional.calculoBaseDesc(informacaoNutricional.getDadosBase().getGordurasTrans(), pesoPorcao,
						informacaoNutricional.getDadosBase().getPeso()));
		informacaoNutricional.getQtdPorcao().setGordurasTotais(
				calculoNutricional.calculoBaseDesc(informacaoNutricional.getDadosBase().getGorduraTotais(), pesoPorcao,
						informacaoNutricional.getDadosBase().getPeso()));
		informacaoNutricional.getQtdPorcao()
				.setProteinas(calculoNutricional.calculoBaseDesc(informacaoNutricional.getDadosBase().getProteinas(),
						pesoPorcao, informacaoNutricional.getDadosBase().getPeso()));
		informacaoNutricional.getQtdPorcao()
				.setSodio(calculoNutricional.calculoBaseDesc(informacaoNutricional.getDadosBase().getSodio(),
						pesoPorcao, informacaoNutricional.getDadosBase().getPeso()));
		informacaoNutricional.getQtdPorcao().setValorEnergetico(
				calculoNutricional.calculoBaseDesc(informacaoNutricional.getDadosBase().getValorEnergetico(),
						pesoPorcao, informacaoNutricional.getDadosBase().getPeso()));

		LOGGER.log(INFO, "####################################################");
		LOGGER.log(INFO, "Peso : " + informacaoNutricional.getDadosBase().getPesoPorcao());

		LOGGER.log(INFO, "Valor Energético : " + informacaoNutricional.getQtdPorcao().getValorEnergetico());
		LOGGER.log(INFO, "Carboidratos (g) : " + informacaoNutricional.getQtdPorcao().getCarboidratos());
		LOGGER.log(INFO, "Proteínas (g) : " + informacaoNutricional.getQtdPorcao().getProteinas());
		LOGGER.log(INFO, "Gorduras Totais (g) : " + informacaoNutricional.getQtdPorcao().getGordurasTotais());
		LOGGER.log(INFO, "Gorduras Saturadas (g) : " + informacaoNutricional.getQtdPorcao().getGordurasSaturadas());
		LOGGER.log(INFO, "GordurasTrans (g) : " + informacaoNutricional.getQtdPorcao().getGordurasTrans());
		LOGGER.log(INFO, "Fibra Alimentar (g) : " + informacaoNutricional.getQtdPorcao().getFibraAlimentar());
		LOGGER.log(INFO, "Sódio (mg) : " + informacaoNutricional.getQtdPorcao().getSodio());

		/**
		 * carboidratos * 17 proteinas * 17 gorduras totais * 37 = kJ
		 * 
		 * carboidratos * 4 proteinas * 4 gorduras totais * 9 =kcal
		 */

		final double carboidratos = Double.valueOf(informacaoNutricional.getQtdPorcao().getCarboidratos())
				.doubleValue();
		final double proteinas = Double.valueOf(informacaoNutricional.getQtdPorcao().getProteinas()).doubleValue();
		final double gordurasTotais = Double.valueOf(informacaoNutricional.getQtdPorcao().getGordurasTotais())
				.doubleValue();

		final double vlrEnergeticoKj = calculoNutricional.calculoValoEnergeticoKj(carboidratos, proteinas,
				gordurasTotais);
		final double vlrEnergeticoKcal = calculoNutricional.calculoValoEnergeticoKcal(carboidratos, proteinas,
				gordurasTotais);

		informacaoNutricional.getQtdPorcao().setVlrEnergeticoKcal(vlrEnergeticoKcal);
		informacaoNutricional.getQtdPorcao().setVlrEnergeticoKj(vlrEnergeticoKj);
		informacaoNutricional.calculoInformacaoNutricionalPorcao();

		LOGGER.log(INFO, "Valor Energético : " + informacaoNutricional.getQtdPorcao().getValorEnergetico());

		calculoNutricional.calculoPercentualDiario(informacaoNutricional);

		return informacaoNutricional;
	}
}