package br.com.VeiculosPajeu.Controle;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import br.com.VeiculosPajeu.Entidade.Entidade;
import br.com.VeiculosPajeu.Entidade.Log;
import br.com.VeiculosPajeu.Entidade.Enum.Tela;
import br.com.VeiculosPajeu.Entidade.Enum.TipoHistorico;
import br.com.VeiculosPajeu.Exception.BusinessException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ControleHistorico extends ControleAdapter {

	@FXML
	private Label lblTitulo;

	@FXML
	private DatePicker dtpData;

	@FXML
	private Button btnBuscar;

	@FXML
	private ComboBox<TipoHistorico> cbxTipo;

	@FXML
	private TableView<Log> tblLog;

	@FXML
	private TableColumn<Log, LocalDate> colData;

	@FXML
	private TableColumn<Log, String> colAlteracao;

	@FXML
	private TableColumn<Log, String> colAutor;

	@FXML
	private TableColumn<Log, String> colEntidade;

	@FXML
	private TableColumn<Log, String> colAnterior;

	@Override
	public void update(Tela tela, Entidade entidade) {
	}

	@Override
	protected void init() {
		cbxTipo.getItems().setAll(TipoHistorico.values());

		colAlteracao.setCellValueFactory(new PropertyValueFactory<>("alteracao"));
		colAutor.setCellValueFactory(new PropertyValueFactory<>("autor"));
		colData.setCellValueFactory(new PropertyValueFactory<>("data"));
		colEntidade.setCellValueFactory(new PropertyValueFactory<>("tabela"));
		colAnterior.setCellValueFactory(new PropertyValueFactory<>("anterior"));

		colData.setCellFactory(coluna -> {

			return new TableCell<Log, LocalDate>() {
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

				if (cbxTipo.getValue() != null && dtpData.getValue() != null)
					tblLog.getItems().setAll(fachada.searchLog(dtpData.getValue(), cbxTipo.getValue()));
				else
					notificacao.mensagemAtencao();
			} catch (BusinessException e) {
				notificacao.mensagemErro("Buscar Histórico", e.getMessage());
				e.printStackTrace();
			}
		}

	}

}
