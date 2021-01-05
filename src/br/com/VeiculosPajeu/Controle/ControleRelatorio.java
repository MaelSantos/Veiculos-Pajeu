package br.com.VeiculosPajeu.Controle;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.VeiculosPajeu.Entidade.Cliente;
import br.com.VeiculosPajeu.Entidade.Entidade;
import br.com.VeiculosPajeu.Entidade.Enum.Tela;
import br.com.VeiculosPajeu.Entidade.Enum.TipoRelatorio;
import br.com.VeiculosPajeu.Exception.BusinessException;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

public class ControleRelatorio extends Controle {

	@FXML
	private AnchorPane pane;

	@FXML
	private FlowPane panelBusca;

	@FXML
	private TextField tfdBusca;

	@FXML
	private GridPane panelData;

	@FXML
	private DatePicker dtpInicio;

	@FXML
	private DatePicker dtpFim;

	@FXML
	private Button btnBuscar;

	@FXML
	private ComboBox<TipoRelatorio> cbxTipoRelatorio;

	@FXML
	private ProgressIndicator pgiCarregar;

	@FXML
	private Label lblCarregar;

	private double porcentagem = 0;
	private Service<?> service;
	private List<? extends Entidade> list;

	@Override
	public void update(Tela tela, Entidade entidade) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void init() {

		cbxTipoRelatorio.getItems().addAll(TipoRelatorio.values());

		cbxTipoRelatorio.setButtonCell(new ListCell<TipoRelatorio>() {
			@Override
			protected void updateItem(TipoRelatorio item, boolean empty) {
				super.updateItem(item, empty);
				if (empty || item == null) {
					setText("Tipo de Relatório");
				} else {
					setText(item.toString());
				}
			}
		});

		service = new Service<Object>() {

			@Override
			protected Task<Object> createTask() {

				return new Task<Object>() {

					public void update() {
						updateMessage("Gerando Arquivo...");
						porcentagem += 16.666666667;
						updateProgress(porcentagem, 100);
					}

					@SuppressWarnings("deprecation")
					@Override
					protected Object call() throws Exception {
						updateTitle("Preparando Arquivo...");

						// gerando o jasper design
						updateMessage("Carregando arquivo");
						InputStream inputStream = getClass().getClassLoader()
								.getResourceAsStream(cbxTipoRelatorio.getValue().getCaminho());
						update();

						updateMessage("Carregando designer");
						JasperDesign desenho = JRXmlLoader.load(inputStream);
						update();

						// compila o relatório
						updateMessage("Compilando");
						JasperReport relatorio = JasperCompileManager.compileReport(desenho);
						update();

						/* Convert List to JRBeanCollectionDataSource */
						updateMessage("Criando biblioteca para documento - bean");
						JRBeanCollectionDataSource itemsJRBean = new JRBeanCollectionDataSource(list);
						update();

						/* Map to hold Jasper report Parameters */
						updateMessage("Criando parâmetros");
						Map<String, Object> parameters = new HashMap<String, Object>();
						parameters.put("ItemDataSource", itemsJRBean);
						update();

						/* Using compiled version(.jasper) of Jasper report to generate PDF */
						updateMessage("Finalizando documento");
						JasperPrint jasperPrint = JasperFillManager.fillReport(relatorio, parameters, itemsJRBean);
						update();

						updateMessage("Abrindo...");
						JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
						jasperViewer.setZoomRatio(0.75F);
						jasperViewer.setLocationRelativeTo(null);
						jasperViewer.show();

						return null;
					}

					@Override
					protected void succeeded() {
						super.succeeded();
						porcentagem = 0;
						pgiCarregar.setVisible(false);
						updateMessage("Concluído");
						list = null;
					}

					@Override
					protected void scheduled() {
						super.scheduled();
						pgiCarregar.setVisible(true);

					}

				};
			}
		};

		pgiCarregar.progressProperty().bind(service.progressProperty());
		lblCarregar.textProperty().bind(service.messageProperty());
	}

	@Override
	public void action(ActionEvent event) {

		Object obj = event.getSource();

		if (obj == btnBuscar) {
			carregarLista();

			if (list != null)
				service.restart();
			else
				notificacao.mensagemAtencao();
		}

	}

	public void carregarLista() {
		try {

			if (cbxTipoRelatorio.getValue() != null)
				switch (cbxTipoRelatorio.getValue()) {
				case CLIENTE_PF:
					if (!tfdBusca.getText().trim().isEmpty())
						list = fachada.searchAllFisica(tfdBusca.getText().trim());
					break;
				case CLIENTE_PJ:
					if (!tfdBusca.getText().trim().isEmpty())
						list = fachada.searchAllJuridica(tfdBusca.getText().trim());
					break;
				case LOCACAO_CLIENTE:
					if (!tfdBusca.getText().trim().isEmpty()) {
						Cliente cliente = notificacao.selecionar(fachada.searchAllCliente(tfdBusca.getText().trim()));

						if (cliente != null)
							list = fachada.searchAllLocacaoPorCliente(cliente);
					}
					break;
				case LOCACAO_PERIODO:
					if (dtpInicio.getValue() != null && dtpFim.getValue() != null)
						list = fachada.searchAllLocacaoPeriodo(dtpInicio.getValue(), dtpFim.getValue());
					break;
				case RESERVA_PERIODO:
					if (dtpInicio.getValue() != null && dtpFim.getValue() != null)
						list = fachada.searchAllReservaPeriodo(dtpInicio.getValue(), dtpFim.getValue());
					break;
				case FINANCEIRO_PERIODO:
					if (dtpInicio.getValue() != null && dtpFim.getValue() != null)
						list = fachada.searchAllFinanceiroEstado(dtpInicio.getValue(), dtpFim.getValue(), null);
					break;
				default:
					notificacao.mensagemAtencao();
					break;
				}
			else
				notificacao.mensagemAtencao();
		} catch (BusinessException e) {
			notificacao.mensagemErro("Gerar Relatório", e.getMessage());
			e.printStackTrace();
		}
	}

	@FXML
	public void selection(ActionEvent event) {

		if (cbxTipoRelatorio.getValue() != null) {

			switch (cbxTipoRelatorio.getValue()) {
			case CLIENTE_PF:
				panelData.setVisible(false);
				panelBusca.setVisible(true);
				break;
			case CLIENTE_PJ:
				panelData.setVisible(false);
				panelBusca.setVisible(true);
				break;
			case LOCACAO_CLIENTE:
				panelData.setVisible(false);
				panelBusca.setVisible(true);
				break;
			case LOCACAO_PERIODO:
				panelBusca.setVisible(false);
				panelData.setVisible(true);
				break;
			case RESERVA_PERIODO:
				panelBusca.setVisible(false);
				panelData.setVisible(true);
				break;
			case FINANCEIRO_PERIODO:
				panelBusca.setVisible(false);
				panelData.setVisible(true);
				break;

			default:
				break;
			}
		} 
	}

	@Override
	protected void limparCampos() {

		cbxTipoRelatorio.getSelectionModel().clearSelection();

	}

}
