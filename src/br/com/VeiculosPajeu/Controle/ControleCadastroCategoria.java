package br.com.VeiculosPajeu.Controle;

import br.com.VeiculosPajeu.App.App;
import br.com.VeiculosPajeu.Entidade.Categoria;
import br.com.VeiculosPajeu.Entidade.Entidade;
import br.com.VeiculosPajeu.Entidade.Filial;
import br.com.VeiculosPajeu.Entidade.Enum.Tamanho;
import br.com.VeiculosPajeu.Entidade.Enum.Tela;
import br.com.VeiculosPajeu.Entidade.Enum.TipoCategoria;
import br.com.VeiculosPajeu.Exception.BusinessException;
import br.com.VeiculosPajeu.Util.MaskFieldUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class ControleCadastroCategoria extends Controle {

	@FXML
    private Button btnSalvar;

    @FXML
    private Label lblTitulo;

    @FXML
    private TextField tfdFilial;

    @FXML
    private Button btnBuscarFilial;

    @FXML
    private ComboBox<TipoCategoria> cbxTipoCategoria;

    @FXML
    private TextField tfdNome;

    @FXML
    private ComboBox<Tamanho> cbxTamanho;

    @FXML
    private TextField tfdValor;

    @FXML
    private Button btnMaisFilial;
    
	private Categoria categoria;
	private Filial filial;

	@Override
	protected void init() {
		cbxTamanho.getItems().setAll(Tamanho.values());
		cbxTipoCategoria.getItems().setAll(TipoCategoria.values());

		cbxTamanho.setButtonCell(new ListCell<Tamanho>() {
			@Override
			protected void updateItem(Tamanho item, boolean empty) {
				super.updateItem(item, empty);
				if (empty || item == null) {
					setText("Tamanho");
				} else {
					setText(item.toString());
				}
			}
		});

		cbxTipoCategoria.setButtonCell(new ListCell<TipoCategoria>() {
			@Override
			protected void updateItem(TipoCategoria item, boolean empty) {
				super.updateItem(item, empty);
				if (empty || item == null) {
					setText("Tipo de Categoria");
				} else {
					setText(item.toString());
				}
			}
		});
		
		MaskFieldUtil.numericField(tfdValor);
	}

	@Override
	public void action(ActionEvent event) {

		Object obj = event.getSource();

		if (obj == btnBuscarFilial) {
			try {
				if (!tfdFilial.getText().trim().isEmpty()) {
					Filial filial = notificacao.selecionar(fachada.searchAllFilial(tfdFilial.getText().trim()));
					if (filial != null)
					{
						this.filial = filial;
						tfdFilial.setText(filial + "");
					}
				} else
					notificacao.mensagemAtencao();
			} catch (BusinessException e) {
				notificacao.mensagemErro("Buscar Filial", e.getMessage());
				e.printStackTrace();
			}
		} else if (obj == btnSalvar) {
			try {
				carregarCategoria();
				fachada.createOrUpdateCategoria(categoria);
				notificacao.mensagemSucesso("Categoria Salva Com Sucesso");
				App.notificarOuvintes(categoria);
				limparCampos();
			} catch (BusinessException e) {
				notificacao.mensagemErro("Salva Categoria", e.getMessage());
				e.printStackTrace();
			}
		}
		else if(obj == btnMaisFilial)
		{
			notificacao.showDialogo(Tela.CADASTRO_FILIAL);
		}
	}

	@FXML
	protected void modify(ActionEvent event) {
		atualizarNome();
	}

	@FXML
	protected void roll(MouseEvent event) {
		atualizarNome();
	}

	private void atualizarNome() {
		if (cbxTipoCategoria.getValue() != null)
			tfdNome.setText(cbxTipoCategoria.getValue().toString().substring(0, 1));
		if (cbxTamanho.getValue() != null)
			tfdNome.setText(tfdNome.getText() + cbxTamanho.getValue().toString().substring(0, 1));
		if (!tfdValor.getText().trim().isEmpty())
			tfdNome.setText(tfdNome.getText() + tfdValor.getText().trim().substring(0, 1));
	}

	private void carregarCategoria() {

		if (categoria == null)
			categoria = new Categoria();
		else if(filial == null)
			filial = categoria.getFilial();
		categoria.setFilial(filial);			
		categoria.setNome(tfdNome.getText().trim());
		categoria.setTamanho(cbxTamanho.getValue());
		categoria.setTipo(cbxTipoCategoria.getValue());
		categoria.setValor(Float.parseFloat(tfdValor.getText().trim()));
	}

	private void carregarCampo() {

		tfdFilial.setText(categoria.getFilial()+"");
		tfdNome.setText(categoria.getNome());
		cbxTamanho.setValue(categoria.getTamanho());
		cbxTipoCategoria.setValue(categoria.getTipo());
		tfdValor.setText(categoria.getValor()+"");
	}

	@Override
	protected void limparCampos() {

		categoria = null;
		filial = null;
		cbxTipoCategoria.getSelectionModel().clearSelection();
		cbxTamanho.getSelectionModel().clearSelection();
		tfdValor.setText("");
		tfdFilial.setText("");
		tfdNome.setText("");
	}

	@Override
	public void update(Tela tela, Entidade entidade) {

		if(tela == Tela.EDITAR_CATEGORIA)
		{
			if (entidade instanceof Categoria) {
				categoria = (Categoria) entidade;
				carregarCampo();
				lblTitulo.setText(tela + "");
			}
			
		}
		else if(tela == Tela.CADASTRO_CATEGORIA)
		{
			lblTitulo.setText(tela + "");
			limparCampos();
		}
	}

}
