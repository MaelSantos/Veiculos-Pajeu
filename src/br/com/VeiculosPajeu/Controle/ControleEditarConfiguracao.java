package br.com.VeiculosPajeu.Controle;

import com.jfoenix.controls.JFXTimePicker;

import br.com.VeiculosPajeu.App.App;
import br.com.VeiculosPajeu.Entidade.Configuracao;
import br.com.VeiculosPajeu.Entidade.Entidade;
import br.com.VeiculosPajeu.Entidade.Usuario;
import br.com.VeiculosPajeu.Entidade.Enum.Tela;
import br.com.VeiculosPajeu.Exception.BusinessException;
import br.com.VeiculosPajeu.Util.MaskFieldUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ControleEditarConfiguracao extends Controle {

	@FXML
	private TextField tfdKmControle;

	@FXML
	private TextField tfdKmLivre;

	@FXML
	private JFXTimePicker tpcHorasLimpeza;

	@FXML
	private Button btnSalvar;

	private Configuracao configuracao;

	private MaskFieldUtil maskFieldUtil;

	@Override
	public void update(Tela tela, Entidade entidade) {

		if (entidade instanceof Configuracao) {
			configuracao = (Configuracao) entidade;
			modificarCampos();
		}
		if (tela == Tela.MENU) {
			if (entidade instanceof Usuario) {
				Usuario usuario = (Usuario) entidade;
				configuracao.setUsuario(usuario.getNome());
				try {
					fachada.createOrUpdateConfiguracao(configuracao);
				} catch (BusinessException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	protected void init() {
		maskFieldUtil = MaskFieldUtil.getInstance();

		try {
			configuracao = fachada.searchConfiguracao(1);
			modificarCampos();
		} catch (BusinessException e) {
			e.printStackTrace();
		}

		maskFieldUtil.numericField(tfdKmControle);
		maskFieldUtil.numericField(tfdKmLivre);

	}

	@Override
	public void action(ActionEvent event) {

		Object obj = event.getSource();

		if (obj == btnSalvar) {
			try {
				carregarConfiguracao();
				fachada.createOrUpdateConfiguracao(configuracao);
				notificacao.mensagemSucesso("Configurações Editadas com Sucesso");
				App.notificarOuvintes(Tela.EDITAR_CONFIGURACAO, configuracao);
			} catch (BusinessException e) {
				e.printStackTrace();
				notificacao.mensagemErro("Editar Configurações", e.getMessage());
			}
		}

	}

	@Override
	protected void limparCampos() {
		// TODO Stub de m�todo gerado automaticamente

	}

	private void modificarCampos() {

		tpcHorasLimpeza.setValue(configuracao.getHoras_limpeza());
		tpcHorasLimpeza.getEditor().setText(configuracao.getHoras_limpeza() + "");
		tfdKmControle.setText(configuracao.getValorKmControle() + "");
		tfdKmLivre.setText(configuracao.getValorKmLivre() + "");
	}

	private void carregarConfiguracao() {

		configuracao.setHoras_limpeza(tpcHorasLimpeza.getValue());
		configuracao.setValorKmControle(Float.parseFloat(tfdKmControle.getText().trim()));
		configuracao.setValorKmLivre(Float.parseFloat(tfdKmLivre.getText().trim()));

	}
}
