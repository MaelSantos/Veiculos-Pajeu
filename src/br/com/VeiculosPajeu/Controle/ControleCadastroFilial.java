package br.com.VeiculosPajeu.Controle;

import br.com.VeiculosPajeu.Entidade.Endereco;
import br.com.VeiculosPajeu.Entidade.Entidade;
import br.com.VeiculosPajeu.Entidade.Filial;
import br.com.VeiculosPajeu.Entidade.Enum.Estado;
import br.com.VeiculosPajeu.Entidade.Enum.Tela;
import br.com.VeiculosPajeu.Exception.BusinessException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;

public class ControleCadastroFilial extends Controle {

	@FXML
	private Label lblTitulo;

	@FXML
	private TextField tfdNome;

	@FXML
	private TextField tfdRua;

	@FXML
	private TextField tfdNumero;

	@FXML
	private TextField tfdBairro;

	@FXML
	private TextField tfdCep;

	@FXML
	private TextField tfdCidade;

	@FXML
	private ComboBox<Estado> cbxEstado;

	@FXML
	private TextField tfdPais;

	@FXML
	private TextField tfdComplemento;

	@FXML
	private Button btnSalvar;

	private Filial filial;

	@Override
	protected void init() {

		cbxEstado.getItems().addAll(Estado.values());

		cbxEstado.setButtonCell(new ListCell<Estado>() {
			@Override
			protected void updateItem(Estado item, boolean empty) {
				super.updateItem(item, empty);
				if (empty || item == null) {
					setText("Estado");
				} else {
					setText(item.toString());
				}
			}
		});
	}

	@Override
	public void action(ActionEvent event) {

		Object obj = event.getSource();

		if (obj == btnSalvar) {
			try {
				carregarFilial();
				fachada.saveFilial(filial);
				notificacao.mensagemSucesso("Filial Salva Com Sucesso");
				limparCampos();
			} catch (BusinessException e) {
				notificacao.mensagemErro("Salva Filial", e.getMessage());
				e.printStackTrace();
			}
		}
	}

	private void carregarFilial() {

		Endereco endereco = null;
		
		if (filial == null)
		{
			filial = new Filial();
			endereco = new Endereco();
			filial.setEndereco(endereco);
		}
		
		endereco = filial.getEndereco();
		endereco.setBairro(tfdBairro.getText().trim());
		endereco.setCidade(tfdCidade.getText().trim());
		endereco.setRua(tfdRua.getText().trim());
		endereco.setEstado(cbxEstado.getValue());
		endereco.setNumero(tfdNumero.getText().trim());
		endereco.setComplemento(tfdComplemento.getText().trim());
		endereco.setCep(tfdCep.getText().trim());
		endereco.setPais(tfdPais.getText().trim());

//		filial.setEndereco(endereco);
		filial.setNome(tfdNome.getText().trim());
	}

	private void carregarCampos() {

		tfdBairro.setText(filial.getEndereco().getBairro());
		tfdCidade.setText(filial.getEndereco().getCidade());
		tfdRua.setText(filial.getEndereco().getRua());
		cbxEstado.setValue(filial.getEndereco().getEstado());
		tfdNumero.setText(filial.getEndereco().getNumero());
		tfdComplemento.setText(filial.getEndereco().getComplemento());
		tfdCep.setText(filial.getEndereco().getCep());
		tfdPais.setText(filial.getEndereco().getPais());

		tfdNome.setText(filial.getNome());
	}

	@Override
	protected void limparCampos() {

		tfdBairro.setText("");
		tfdCidade.setText("");
		tfdRua.setText("");
		cbxEstado.getSelectionModel().clearSelection();
		tfdNumero.setText("");
		tfdComplemento.setText("");
		tfdCep.setText("");
		tfdPais.setText("");

		tfdNome.setText("");

		filial = null;

	}

	@Override
	public void update(Tela tela, Entidade entidade) {

		if (tela == Tela.EDITAR_FILIAL) {
			if (entidade instanceof Filial) {
				filial = (Filial) entidade;
				carregarCampos();
				lblTitulo.setText(tela + "");
			}
		} else if (tela == Tela.CADASTRO_FILIAL) {
			lblTitulo.setText(tela + "");
			limparCampos();

		}

	}

}
