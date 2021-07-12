package br.com.VeiculosPajeu.Controle;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import br.com.VeiculosPajeu.Entidade.Entidade;
import br.com.VeiculosPajeu.Entidade.Locacao;
import br.com.VeiculosPajeu.Entidade.Reserva;
import br.com.VeiculosPajeu.Entidade.Enum.Tela;
import br.com.VeiculosPajeu.Entidade.Enum.TipoEstatistica;
import br.com.VeiculosPajeu.Entidade.Enum.TipoGrafico;
import br.com.VeiculosPajeu.Exception.BusinessException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;

public class ControleEstatistica extends Controle {

	@FXML
	private TextField tfdBusca;

	@FXML
	private Button btnBusca;

	@FXML
	private ComboBox<TipoEstatistica> cbxTipoPesquisa;

	@FXML
	private ComboBox<TipoGrafico> cbxTipoGrafico;

	@FXML
	private BarChart<String, Double> gfcBarra;

	@FXML
	private LineChart<String, Double> gfcLinha;

	@FXML
	private PieChart gfcPizza;

	@Override
	public void update(Tela tela, Entidade entidade) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void init() {
		cbxTipoGrafico.getItems().setAll(TipoGrafico.values());
		cbxTipoPesquisa.getItems().setAll(TipoEstatistica.values());

		cbxTipoGrafico.setButtonCell(new ListCell<TipoGrafico>() {
			@Override
			protected void updateItem(TipoGrafico item, boolean empty) {
				super.updateItem(item, empty);
				if (empty || item == null) {
					setText("Tipo de Gráfico");
				} else {
					setText(item.toString());
				}
			}
		});

		cbxTipoPesquisa.setButtonCell(new ListCell<TipoEstatistica>() {
			@Override
			protected void updateItem(TipoEstatistica item, boolean empty) {
				super.updateItem(item, empty);
				if (empty || item == null) {
					setText("Tipo de Estatística");
				} else {
					setText(item.toString());
				}
			}
		});

	}

	@Override
	public void action(ActionEvent event) {

		Object obj = event.getSource();

		if (obj == btnBusca) {

			try {

				if (!gfcBarra.getData().isEmpty()) {
					gfcBarra.getData().clear();
					gfcLinha.getData().clear();
					gfcPizza.getData().clear();
				}
				gerarGrarfico();

			} catch (Exception e) {
				notificacao.mensagemErro("Gerar Gráficos", e.getMessage());
				e.printStackTrace();
			}

		} else if (obj == cbxTipoGrafico) {
			selected();
		}

	}

	private void selected() {

		if (cbxTipoGrafico.getSelectionModel().getSelectedItem() != null)
			switch (cbxTipoGrafico.getValue()) {
			case BARRA:
				gfcLinha.setVisible(false);
				gfcPizza.setVisible(false);
				gfcBarra.setVisible(true);
				break;
			case LINHA:
				gfcBarra.setVisible(false);
				gfcPizza.setVisible(false);
				gfcLinha.setVisible(true);
				break;
			case PIZZA:
				gfcBarra.setVisible(false);
				gfcLinha.setVisible(false);
				gfcPizza.setVisible(true);
				break;

			default:
				break;
			}

	}

	private void gerarGrarfico() throws BusinessException {

		if (cbxTipoPesquisa.getValue() != null) {

			Map<String, Long> valores = new HashMap<>();

			switch (cbxTipoPesquisa.getValue()) {
			case LOCACAO_ATIVA:

				valores.put("Ativa", fachada.searchContSelect(Locacao.class, "a.ativo = true"));
				valores.put("Vencida", fachada.searchContSelect(Locacao.class, "a.data_devolucao < CURRENT_DATE"));

				break;
			case LOCACAO_RESERVA:

				valores.put("Locação", fachada.searchCont(Locacao.class));
				valores.put("Reserva", fachada.searchCont(Reserva.class));

				break;
			case RESERVA_ATIVA:

				valores.put("Ativa", fachada.searchContSelect(Reserva.class, "a.ativo = true"));
				valores.put("Vencida", fachada.searchContSelect(Reserva.class, "a.data_locacao < CURRENT_DATE"));

				break;

			default:
				break;
			}

			if (!valores.isEmpty()) {
				XYChart.Series<String, Double> series = new XYChart.Series<>();

				Iterator<Entry<String, Long>> iterator = valores.entrySet().iterator();
				while (iterator.hasNext()) {
					Entry<String, Long> e = iterator.next();
					Long valor = e.getValue();
					String tipo = e.getKey();

					series.getData().add(new Data<String, Double>(tipo, valor.doubleValue()));
					series.setName(cbxTipoPesquisa.getValue() + "");
					gfcPizza.getData().add(new PieChart.Data(tipo, valor.doubleValue()));
				}

				System.out.println(valores);
				System.out.println(series.getData());

				gfcBarra.getData().add(series);
				gfcLinha.getData().add(series);

				gfcPizza.setTitle(cbxTipoPesquisa.getValue() + "");
				gfcBarra.setTitle(cbxTipoPesquisa.getValue() + "");
				gfcLinha.setTitle(cbxTipoPesquisa.getValue() + "");

			}
		}
	}

	@Override
	protected void limparCampos() {

		gfcBarra.setVisible(false);
		gfcLinha.setVisible(false);
		gfcPizza.setVisible(false);

		cbxTipoGrafico.getSelectionModel().clearSelection();
		cbxTipoPesquisa.getSelectionModel().clearSelection();
	}

}
