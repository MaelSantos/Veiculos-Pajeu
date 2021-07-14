package br.com.VeiculosPajeu.Controle;

import java.util.ArrayList;
import java.util.List;

import br.com.VeiculosPajeu.App.App;
import br.com.VeiculosPajeu.Entidade.Categoria;
import br.com.VeiculosPajeu.Entidade.Filial;
import br.com.VeiculosPajeu.Entidade.Veiculo;
import br.com.VeiculosPajeu.Entidade.Enum.Tela;
import br.com.VeiculosPajeu.Exception.BusinessException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class ControleDisponiveis extends ControleAdapter {

    @FXML
    private TextField tfdFilial;

    @FXML
    private Button btnBuscarFilial;
	
	@FXML
	private DatePicker dtpData;

	@FXML
	private Button btnBuscar;

	@FXML
	private TableView<Veiculo> tblVeiculos;

	@FXML
	private TableColumn<Veiculo, String> colFabricante;

	@FXML
	private TableColumn<Veiculo, String> colModelo;

	@FXML
	private TableColumn<Veiculo, Integer> colAnoModelo;

	@FXML
	private TableColumn<Veiculo, Categoria> colCategoria;

	@FXML
	private TableColumn<Veiculo, String> colPlaca;
	
	private Filial filial;

	@Override
	protected void init() {

		colAnoModelo.setCellValueFactory(new PropertyValueFactory<>("ano_modelo"));
		colCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
		colFabricante.setCellValueFactory(new PropertyValueFactory<>("fabricante"));
		colModelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));
		colPlaca.setCellValueFactory(new PropertyValueFactory<>("placa"));

		tblVeiculos.setOnMouseClicked(e -> {
			if (e.getClickCount() > 1)
				if (tblVeiculos.getSelectionModel().getSelectedItem() != null)
					App.notificarOuvintes(Tela.DETALHES, tblVeiculos.getSelectionModel().getSelectedItem());
		});
		
	}

	@Override
	public void action(ActionEvent event) {

		Object obj = event.getSource();

		if (obj == btnBuscar) {
			List<Veiculo> list = new ArrayList<>();
			try {
				if(filial != null)
				{
					if (dtpData.getValue() != null)
						list.addAll(fachada.searchAllVeiculoDataFurtura(filial, dtpData.getValue()));
					list.addAll(fachada.searchAllVeiculoDiponivel(filial));
					
					tblVeiculos.getItems().setAll(list);
					
				}
				else
					notificacao.mensagemAtencao();
							
			} catch (BusinessException e) {
				notificacao.mensagemErro("Buscar Veículo", e.getMessage());
				e.printStackTrace();
			}

		}
		else if(obj == btnBuscarFilial)
		{
			try {
				
				if(!tfdFilial.getText().trim().isEmpty())
				{
					Filial filial = notificacao.selecionar(fachada.searchAllFilial(tfdFilial.getText().trim()));
					
					if(filial != null)
					{
						this.filial = filial;
						tfdFilial.setText(filial+"");
					}
					else
						tfdFilial.setText("");
					
				}
				else
					notificacao.mensagemAtencao();
				
			} catch (BusinessException e) {
				notificacao.mensagemErro("Buscar Filial", e.getMessage());
				e.printStackTrace();
			}
		}

	}

}
