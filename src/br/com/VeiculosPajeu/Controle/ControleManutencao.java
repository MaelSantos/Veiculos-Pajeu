package br.com.VeiculosPajeu.Controle;

import br.com.VeiculosPajeu.App.App;
import br.com.VeiculosPajeu.Entidade.Categoria;
import br.com.VeiculosPajeu.Entidade.Locacao;
import br.com.VeiculosPajeu.Entidade.Enum.Tela;
import br.com.VeiculosPajeu.Entidade.View.VeiculoView;
import br.com.VeiculosPajeu.Exception.BusinessException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ControleManutencao extends ControleAdapter {

	@FXML
	private TableView<VeiculoView> tblVeiculos;

	@FXML
	private TableColumn<VeiculoView, String> colFabricante;

	@FXML
	private TableColumn<VeiculoView, String> colModelo;

	@FXML
	private TableColumn<VeiculoView, Categoria> colCategoria;

	@FXML
	private TableColumn<VeiculoView, Integer> colAnoModelo;

	@FXML
	private TableColumn<VeiculoView, String> colPlaca;

	@FXML
	private Button btnAtualizar;

	@Override
	protected void init() {

		colAnoModelo.setCellValueFactory(new PropertyValueFactory<>("ano_modelo"));
		colCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
		colFabricante.setCellValueFactory(new PropertyValueFactory<>("fabricante"));
		colModelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));
		colPlaca.setCellValueFactory(new PropertyValueFactory<>("placa"));

		try {
			tblVeiculos.getItems().setAll(fachada.searchAllManutencao());
		} catch (BusinessException e) {
			e.printStackTrace();
		}

		tblVeiculos.setOnMouseClicked(e -> {
			if (e.getClickCount() > 1)
				if (tblVeiculos.getSelectionModel().getSelectedItem() != null) {
					Locacao locacao;
					try {
						locacao = fachada.searchLocacao(tblVeiculos.getSelectionModel().getSelectedItem().getId());
						App.notificarOuvintes(Tela.DETALHES, locacao);
					} catch (BusinessException e1) {
						e1.printStackTrace();
						notificacao.mensagemErro("Buscar Veículo", e1.getMessage());
					}
				}
		});

	}

	@Override
	public void action(ActionEvent event) {

		Object obj = event.getSource();

		if (obj == btnAtualizar) {
			try {
				tblVeiculos.getItems().setAll(fachada.searchAllManutencao());
			} catch (BusinessException e) {
				e.printStackTrace();
				notificacao.mensagemErro("Buscar Reservas Atrasadas", e.getMessage());
			}
		}

	}

}
