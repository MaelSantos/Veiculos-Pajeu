package br.com.VeiculosPajeu.Controle;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import br.com.VeiculosPajeu.App.App;
import br.com.VeiculosPajeu.Entidade.Financeiro;
import br.com.VeiculosPajeu.Entidade.Locacao;
import br.com.VeiculosPajeu.Entidade.Enum.EstadoFinanceiro;
import br.com.VeiculosPajeu.Entidade.Enum.Tela;
import br.com.VeiculosPajeu.Exception.BusinessException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ControleGerenciarFinanceiro extends ControleAdapter {

	@FXML
	private DatePicker dtpDe;

	@FXML
	private DatePicker dtpAte;

	@FXML
	private ComboBox<EstadoFinanceiro> cbxEstado;

	@FXML
	private Button btnBuscar;

	@FXML
	private TableView<Financeiro> tblFinaceiro;

	@FXML
	private TableColumn<Financeiro, Locacao> colLocacao;

	@FXML
	private TableColumn<Financeiro, LocalDate> colDataAberta;

	@FXML
	private TableColumn<Financeiro, LocalDate> colVencimento;

	@FXML
	private TableColumn<Financeiro, EstadoFinanceiro> colEstado;

	@FXML
	private TableColumn<Financeiro, Float> colValorTotal;

	@Override
	protected void init() {

		cbxEstado.getItems().setAll(EstadoFinanceiro.values());

		colDataAberta.setCellValueFactory(new PropertyValueFactory<>("data_aberta"));
		colEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
		colLocacao.setCellValueFactory(new PropertyValueFactory<>("locacao"));
		colValorTotal.setCellValueFactory(new PropertyValueFactory<>("valor_total"));
		colVencimento.setCellValueFactory(new PropertyValueFactory<>("data_vencimento"));

		colDataAberta.setCellFactory(coluna -> {

			return new TableCell<Financeiro, LocalDate>() {
				protected void updateItem(LocalDate item, boolean empty) {

					super.updateItem(item, empty);

					if (item == null || empty) {
						setText(null);
					} else {
						setText(DateTimeFormatter.ofPattern("dd/MM/yyyy").format(item));
					}
				}
			};
		});

		colVencimento.setCellFactory(coluna -> {

			return new TableCell<Financeiro, LocalDate>() {
				protected void updateItem(LocalDate item, boolean empty) {

					super.updateItem(item, empty);

					if (item == null || empty) {
						setText(null);
					} else {
						setText(DateTimeFormatter.ofPattern("dd/MM/yyyy").format(item));
					}
				}
			};
		});

	}

	@Override
	public void action(ActionEvent event) {

		Object obj = event.getSource();

		if (obj == btnBuscar) {
			try {
				if (dtpDe.getValue() != null && dtpAte.getValue() != null) {
					tblFinaceiro.getItems().setAll(fachada.searchAllFinanceiroEstado(dtpDe.getValue(),
							dtpAte.getValue(), cbxEstado.getValue()));
				} else
					notificacao.mensagemAtencao();
			} catch (BusinessException e) {
				notificacao.mensagemErro("Buscar Financeiro", e.getMessage());
				e.printStackTrace();
			}
		}

		tblFinaceiro.setOnMouseClicked(e -> {
			if (e.getClickCount() > 1)
				if (tblFinaceiro.getSelectionModel().getSelectedItem() != null)
					App.notificarOuvintes(Tela.FINANCEIRO, tblFinaceiro.getSelectionModel().getSelectedItem());
		});

	}

	@Override
	protected void limparCampos() {

		dtpDe.setValue(null);
		dtpAte.setValue(null);
		cbxEstado.getSelectionModel().clearSelection();

	}

}
