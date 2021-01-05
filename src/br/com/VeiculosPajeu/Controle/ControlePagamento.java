package br.com.VeiculosPajeu.Controle;

import java.time.LocalDate;
import java.time.LocalTime;

import br.com.VeiculosPajeu.App.App;
import br.com.VeiculosPajeu.Entidade.Entidade;
import br.com.VeiculosPajeu.Entidade.Financeiro;
import br.com.VeiculosPajeu.Entidade.Enum.EstadoFinanceiro;
import br.com.VeiculosPajeu.Entidade.Enum.Tela;
import br.com.VeiculosPajeu.Exception.BusinessException;
import br.com.VeiculosPajeu.Exception.ValidationException;
import br.com.VeiculosPajeu.Util.DateUtil;
import br.com.VeiculosPajeu.Util.MaskFieldUtil;
import br.com.VeiculosPajeu.Util.TimeUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ControlePagamento extends Controle {

	@FXML
	private Label lblDescricao;

	@FXML
	private Label lblSolicitado;

	@FXML
	private Label lblMulta;

	@FXML
	private Label lblTotal;

	@FXML
	private TextField tfdRecebido;

	@FXML
	private Label lblTroco;

	@FXML
	private Label lblAtraso;

	@FXML
	private Label lblPago;

	@FXML
	private Label lblValor;

	@FXML
	private Button btnRealizarPagamento;

	private Financeiro financeiro;
	private static String descricao;
	private static float valor;

	@Override
	public void update(Tela tela, Entidade entidade) {

		if (entidade instanceof Financeiro) {
			financeiro = (Financeiro) entidade;
			carregarCampos();
			atualizarTroco();
		} else if (financeiro == null)
			limparCampos();

	}

	@Override
	protected void init() {
		
		MaskFieldUtil.numericField(tfdRecebido);

	}

	@Override
	public void action(ActionEvent event) {

		Object obj = event.getSource();

		if (obj == btnRealizarPagamento) {
			try {

				if (!tfdRecebido.getText().trim().isEmpty()) {

					float pago = Float.parseFloat(tfdRecebido.getText().trim());

					if (pago >= financeiro.getValor_total() - financeiro.getValor_pago()) {
						financeiro.setEstado(EstadoFinanceiro.PAGO);
						financeiro.setMulta(financeiro.getMulta() + calcularMulta());
						financeiro.setValor_pago(financeiro.getValor_total() + financeiro.getMulta());
						financeiro.setData_paga(LocalDate.now());
					} else if (valor > 0) {
						float solicitado = Float.parseFloat(lblSolicitado.getText());
						if (pago >= solicitado + calcularMulta())
							financeiro.setValor_pago(financeiro.getValor_pago() + solicitado + calcularMulta());
						else
							throw new ValidationException("Valor Insuficiente");
					} else if(valor < 0)
					{	
						if(pago >= financeiro.getValor_total() - financeiro.getValor_pago())
						{
							financeiro.setEstado(EstadoFinanceiro.PAGO);
							financeiro.setMulta(financeiro.getMulta() + calcularMulta());
							financeiro.setValor_pago(financeiro.getValor_total() + financeiro.getMulta());
							financeiro.setData_paga(LocalDate.now());
						}
						else 
							throw new ValidationException("Valor Insuficiente");
					}
					else
						financeiro.setValor_pago(financeiro.getValor_pago() + pago);

					financeiro.setHoras_atrasados(calcularHorasAtraso());
					fachada.createOrUpdateFinanceiro(financeiro);
					
					App.notificarOuvintes(Tela.FINANCEIRO, financeiro);
					notificacao.mensagemSucesso("Operação Realizada Com Sucesso");
					limparCampos();
				} else
					notificacao.mensagemAtencao();

			} catch (BusinessException | ValidationException e) {
				notificacao.mensagemErro("Realizar Pagamento", e.getMessage());
				e.printStackTrace();
			}

		} else if (obj == tfdRecebido) {
			if(!tfdRecebido.getText().trim().isEmpty())
				atualizarTroco();
		}
	}

	private void atualizarTroco() {

		float troco = 0F;
		float pagar = 0F;
		float pago = financeiro.getValor_pago();
		float total = financeiro.getValor_total();
		float multa = calcularMulta() + financeiro.getMulta();

		float recebido = Float.parseFloat(tfdRecebido.getText().trim());

		if (valor <= 0) {
			pagar = total + multa - pago;
		} else
			pagar = valor + multa - pago;

		if (recebido > pagar)
			troco = recebido - pagar;

		lblTroco.setText(troco + "");
		lblTotal.setText(pagar + "");
		lblMulta.setText(multa + "");

		if (troco < 0)
			notificacao.mensagemErro("Fornecer Valor", "Valor Insuficiente");
	}

	private float calcularMulta() {

		LocalTime horas_atrasadas = calcularHorasAtraso();

		float multa = 0F;

		if(financeiro.getData_vencimento().isBefore(LocalDate.now()))
		{
			multa = DateUtil.DiferencaDias(financeiro.getData_vencimento()) * financeiro.getLocacao().getDiaria();
		}
		else if(financeiro.getData_vencimento().isEqual(LocalDate.now()))
		{
			if(horas_atrasadas.getHour() > 1 && horas_atrasadas.getHour() < 4)
			{
				multa = financeiro.getLocacao().getDiaria()/4;
				multa *= horas_atrasadas.getHour();
			}
			else if (horas_atrasadas.getHour() >= 4)
				multa += financeiro.getLocacao().getDiaria();			
		}

		return multa;

	}

	private LocalTime calcularHorasAtraso() {
		LocalDate vencimento = financeiro.getData_vencimento();
		LocalTime devolucao = financeiro.getLocacao().getHora_devolucao();
		LocalDate atual = LocalDate.now();
		LocalTime time = null;

		if (vencimento.isEqual(atual) || vencimento.isBefore(atual)) {
			time = TimeUtil.Timer(devolucao);
		}
		if (time == null)
			time = LocalTime.of(0, 0);

		lblAtraso.setText(time + "");

		return time;
	}

	private void carregarCampos() {

		if (financeiro.getHoras_atrasados() != null)
			lblAtraso.setText(financeiro.getHoras_atrasados() + "");
		else
			lblAtraso.setText("-");

		lblMulta.setText(financeiro.getMulta() + "");
		lblValor.setText(financeiro.getValor_total() + "");
		lblPago.setText(financeiro.getValor_pago() + "");
		lblDescricao.setText(descricao);
		lblTotal.setText("-");

		if (valor > 0)
			lblSolicitado.setText(valor + "");
		else if(valor < 0)
			lblSolicitado.setText("Todo");
		else
			lblSolicitado.setText("Personalizado");
	}

	@Override
	protected void limparCampos() {

		lblAtraso.setText("-");
		lblDescricao.setText("-");
		lblMulta.setText("-");
		lblTotal.setText("-");
		lblTroco.setText("-");
		lblValor.setText("-");
		lblPago.setText("-");
		lblSolicitado.setText("-");

		tfdRecebido.setText("0");

		financeiro = null;
	}

	public static void setDescricao(String descricao, Float valor) {
		ControlePagamento.descricao = descricao;
		ControlePagamento.valor = valor;
	}

}
