package br.com.VeiculosPajeu.Controle;

import java.time.LocalTime;
import java.util.List;

import br.com.VeiculosPajeu.App.App;
import br.com.VeiculosPajeu.Entidade.Automovel;
import br.com.VeiculosPajeu.Entidade.Carga;
import br.com.VeiculosPajeu.Entidade.Entidade;
import br.com.VeiculosPajeu.Entidade.Filial;
import br.com.VeiculosPajeu.Entidade.Financeiro;
import br.com.VeiculosPajeu.Entidade.Locacao;
import br.com.VeiculosPajeu.Entidade.Passageiro;
import br.com.VeiculosPajeu.Entidade.Veiculo;
import br.com.VeiculosPajeu.Entidade.Enum.Tela;
import br.com.VeiculosPajeu.Entidade.Enum.TipoLocacao;
import br.com.VeiculosPajeu.Exception.BusinessException;
import br.com.VeiculosPajeu.Util.MaskFieldUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ControleDevolucao extends Controle {

	@FXML
	private Button btnDevolver;

	@FXML
	private TextField tfdBuscar;

	@FXML
	private Button btnBuscarVeiculo;

	@FXML
	private TextField tfdFilial;

	@FXML
	private Button btnBuscarFilial;

	@FXML
	private CheckBox ckbLimpeza;

	@FXML
	private CheckBox ckbCombustivel;

	@FXML
	private TextField tfdQuilometragem;

	@FXML
	private Label lblValor;
	
	private Veiculo veiculo;
	private Filial filial;
	private Locacao locacao;

	@Override
	public void update(Tela tela, Entidade entidade) {
	}

	@Override
	protected void init() {

		MaskFieldUtil.numericField(tfdQuilometragem);
		
	}

	@Override
	public void action(ActionEvent event) {

		Object obj = event.getSource();

		if (obj == btnDevolver) {
			try {

				if (veiculo != null && locacao != null && filial != null && !tfdQuilometragem.getText().trim().isEmpty()) {

					devolucao();

					if (veiculo instanceof Automovel) {
						fachada.createOrUpdateAutomovel((Automovel) veiculo);
					} else if (veiculo instanceof Carga) {
						fachada.createOrUpdateCarga((Carga) veiculo);
					} else if (veiculo instanceof Passageiro) {
						fachada.createOrUpdatePassageiro((Passageiro) veiculo);
					}

					fachada.createOrUpdateLocacao(locacao);
					
					ControlePagamento.setDescricao("Devolução do Veículo", -1F);
					
					Financeiro financeiro = fachada.searchFinanceiro(locacao.getId());
					if(financeiro.getMulta() != null)
						locacao.getFinanceiro().setMulta(locacao.getFinanceiro().getMulta() + calcularMulta());
					else 
						locacao.getFinanceiro().setMulta(calcularMulta());
					
					App.notificarOuvintes(Tela.FINANCEIRO, locacao.getFinanceiro());
					notificacao.showDialogo(Tela.PAGAMENTO);
					notificacao.mensagemSucesso("Veículo Devolvido Com Sucesso");
					
					limparCampos();
					
				} else
					notificacao.mensagemAtencao();

			} catch (BusinessException e) {
				notificacao.mensagemErro("Devolver Veículo", e.getMessage());
				e.printStackTrace();
			}

		} else if (obj == btnBuscarVeiculo) {

			try {
				if (!tfdBuscar.getText().trim().isEmpty()) {

					List<Locacao> locacaos = fachada.searchAllLocacaoAtivo(tfdBuscar.getText().trim());
					Locacao locacao = notificacao.selecionar(locacaos);

					if (locacao != null) {
						this.locacao = locacao;
						veiculo = locacao.getVeiculo();
						tfdBuscar.setText(locacao + "");
						lblValor.setText(locacao.getValor_total() + "");
					}
				} else
					notificacao.mensagemAtencao();

			} catch (BusinessException e) {
				notificacao.mensagemErro("Buscar Locação", e.getMessage());
				e.printStackTrace();
			}

		} else if (obj == btnBuscarFilial) {
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
		}

	}

	@FXML
	void valor(ActionEvent event) {

		Float valor = 0F;
		Float inicial = 0F;
		Float km = 0F;
		Float limpeza = 0F;
		Float combustivel = 0F;

		if (locacao != null) {

			inicial = locacao.getValor_total();

			if (locacao.getTipoLocacao() == TipoLocacao.KM_CONTROLE)
			{
				if (!tfdQuilometragem.getText().trim().isEmpty()) {
					
					km = Float.parseFloat(tfdQuilometragem.getText().trim());
					valor = (float) (inicial + (km * 0.1));
				}				
			}
			else if(locacao.getTipoLocacao() == TipoLocacao.KM_LIVRE)
			{
				valor = inicial;
			}
			if (ckbLimpeza.isSelected()) {// calcula taxa de higienização
				limpeza = (float) (inicial * 0.02);
				valor += limpeza;
			}
			if (ckbCombustivel.isSelected()) {// calcula taxa de combustível
				combustivel = (float) (inicial * 0.03);
				valor += combustivel;
			}

		}

		System.out.println("Valor Locação: " + inicial);
		System.out.println("Taxa de Quilometros Rodados: " + km * 0.1);
		System.out.println("Valor Total: " + valor);
		System.out.println("Taxa de Limpeza: " + limpeza);
		System.out.println("Taxa de Combustivel: " + combustivel);

		lblValor.setText(valor + "");

	}

	private float calcularMulta()
	{
		Float multa = 0F;
		Float inicial = 0F;
		Float km = 0F;
		Float limpeza = 0F;
		Float combustivel = 0F;

		if (locacao != null) {

			inicial = locacao.getValor_total();

			if (locacao.getTipoLocacao() == TipoLocacao.KM_CONTROLE)
			{
				if (!tfdQuilometragem.getText().trim().isEmpty()) {
					
					km = Float.parseFloat(tfdQuilometragem.getText().trim());
					multa = (float) (km * 0.1);
				}				
			}
			if (ckbLimpeza.isSelected()) {// calcula taxa de higienização
				limpeza = (float) (inicial * 0.02);
				multa += limpeza;
			}
			if (ckbCombustivel.isSelected()) {// calcula taxa de combustível
				combustivel = (float) (inicial * 0.03);
				multa += combustivel;
			}
		}

		return multa;
	}
	
	private void devolucao() {

		veiculo.setAlugado(false);
		veiculo.setFilial(filial);
		locacao.setFilial_devolucao(filial);
		locacao.setAtivo(false);

		//atualiza quilometragem rodada
		if(veiculo.getQuilometragem() != null)
			veiculo.setQuilometragem(veiculo.getQuilometragem() + Float.parseFloat(tfdQuilometragem.getText().trim()));
		else 
			veiculo.setQuilometragem(Float.parseFloat(tfdQuilometragem.getText().trim()));

		//verifica se é preciso enviar para manutenção 
		if (veiculo instanceof Automovel) {

			if (veiculo.getQuilometragem() >= 5000) {
				veiculo.setManutencao(true);
				notificacao.mensagemPersonalisado(AlertType.WARNING, "Manutenção",
						"Veículo Com mais de 5000 quilometros rodados", "Encaminhando para manutenção");
				veiculo.setHoras_manutencao(LocalTime.now());
			} else
				veiculo.setManutencao(false);

		} else {
			if (veiculo.getQuilometragem() >= 10000) {
				veiculo.setManutencao(true);
				notificacao.mensagemPersonalisado(AlertType.WARNING, "Manutenção",
						"Veículo Com mais de 10000 quilometros rodados", "Encaminhando para manutenção");
				veiculo.setHoras_manutencao(LocalTime.now());
			} else
				veiculo.setManutencao(false);
		}

	}

	@Override
	protected void limparCampos() {
		tfdBuscar.setText("");
		tfdFilial.setText("");
		tfdQuilometragem.setText("");
		ckbCombustivel.setSelected(false);
		ckbLimpeza.setSelected(false);
		locacao = null;
		veiculo = null;
		filial = null;
	}

}
