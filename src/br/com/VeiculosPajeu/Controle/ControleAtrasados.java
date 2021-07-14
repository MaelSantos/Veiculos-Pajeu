package br.com.VeiculosPajeu.Controle;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import br.com.VeiculosPajeu.App.App;
import br.com.VeiculosPajeu.Entidade.Categoria;
import br.com.VeiculosPajeu.Entidade.Cliente;
import br.com.VeiculosPajeu.Entidade.Entidade;
import br.com.VeiculosPajeu.Entidade.Locacao;
import br.com.VeiculosPajeu.Entidade.Reserva;
import br.com.VeiculosPajeu.Entidade.Enum.Tela;
import br.com.VeiculosPajeu.Entidade.Enum.TipoLocacao;
import br.com.VeiculosPajeu.Entidade.View.LocacaoView;
import br.com.VeiculosPajeu.Entidade.View.ReservaView;
import br.com.VeiculosPajeu.Exception.BusinessException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ControleAtrasados extends ControleAdapter {

	@FXML
	private TableView<ReservaView> tblReserva;

	@FXML
	private TableColumn<ReservaView, Cliente> colCliente;

	@FXML
	private TableColumn<ReservaView, Categoria> colCategoria;

	@FXML
	private TableColumn<ReservaView, LocalDate> colDataReserva;

	@FXML
	private TableColumn<ReservaView, LocalDate> colDataLocacao;

	@FXML
	private TableColumn<ReservaView, LocalTime> colHoraRetirada;

	@FXML
	private TableView<LocacaoView> tblLocacao;

	@FXML
	private TableColumn<LocacaoView, Cliente> colCliente1;

	@FXML
	private TableColumn<LocacaoView, LocalDate> colDataLocacao1;

	@FXML
	private TableColumn<LocacaoView, LocalDate> colDataDevolucao;

	@FXML
	private TableColumn<LocacaoView, Float> colValor;

	@FXML
	private TableColumn<LocacaoView, TipoLocacao> colTipo;

	@FXML
	private Button btnAtualizar;

	@Override
	public void update(Tela tela, Entidade entidade) {
		// TODO Stub de m�todo gerado automaticamente

	}

	@Override
	protected void init() {

		colCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
		colCliente.setCellValueFactory(new PropertyValueFactory<>("cliente"));
		colDataLocacao.setCellValueFactory(new PropertyValueFactory<>("data_locacao"));
		colDataReserva.setCellValueFactory(new PropertyValueFactory<>("data_reserva"));
		colHoraRetirada.setCellValueFactory(new PropertyValueFactory<>("hora_reserva"));

		colCliente1.setCellValueFactory(new PropertyValueFactory<>("cliente"));
		colDataLocacao1.setCellValueFactory(new PropertyValueFactory<>("data_locacao"));
		colDataDevolucao.setCellValueFactory(new PropertyValueFactory<>("data_devolucao"));
		colTipo.setCellValueFactory(new PropertyValueFactory<>("tipoLocacao"));
		colValor.setCellValueFactory(new PropertyValueFactory<>("valor_total"));

		colDataLocacao.setCellFactory(coluna -> {

			return new TableCell<ReservaView, LocalDate>() {
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

		colDataLocacao1.setCellFactory(coluna -> {

			return new TableCell<LocacaoView, LocalDate>() {
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

		colDataReserva.setCellFactory(coluna -> {

			return new TableCell<ReservaView, LocalDate>() {
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

		colDataDevolucao.setCellFactory(coluna -> {

			return new TableCell<LocacaoView, LocalDate>() {
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

		try {
			tblReserva.getItems().setAll(fachada.searchAllReservaVencidos());
			tblLocacao.getItems().setAll(fachada.searchAllLocacaoVencidos());
		} catch (BusinessException e) {
			e.printStackTrace();
		}

		tblReserva.setOnMouseClicked(e -> {
			if (e.getClickCount() > 1)
				if (tblReserva.getSelectionModel().getSelectedItem() != null) {
					Reserva reserva;
					try {
						reserva = fachada.searchReserva(tblReserva.getSelectionModel().getSelectedItem().getId());
						App.notificarOuvintes(Tela.DETALHES, reserva);
					} catch (BusinessException e1) {
						e1.printStackTrace();
						notificacao.mensagemErro("Ao buscar Reserva", e1.getMessage());
					}
				}
		});

		tblLocacao.setOnMouseClicked(e -> {
			if (e.getClickCount() > 1)
				if (tblLocacao.getSelectionModel().getSelectedItem() != null) {
					Locacao locacao;
					try {
						locacao = fachada.searchLocacao(tblLocacao.getSelectionModel().getSelectedItem().getId());
						App.notificarOuvintes(Tela.DETALHES, locacao);
					} catch (BusinessException e1) {
						e1.printStackTrace();
						notificacao.mensagemErro("Ao buscar Locação", e1.getMessage());
					}
				}
		});
	}

	@Override
	public void action(ActionEvent event) {

		Object obj = event.getSource();

		if (obj == btnAtualizar) {
			try {
				tblReserva.getItems().setAll(fachada.searchAllReservaVencidos());
				tblLocacao.getItems().setAll(fachada.searchAllLocacaoVencidos());
			} catch (BusinessException e) {
				e.printStackTrace();
				notificacao.mensagemErro("Buscar Reservas Atrasadas", e.getMessage());
			}
		}

	}

}
