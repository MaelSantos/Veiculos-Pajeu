package br.com.VeiculosPajeu.Controle;

import br.com.VeiculosPajeu.App.App;
import br.com.VeiculosPajeu.Entidade.Entidade;
import br.com.VeiculosPajeu.Entidade.Financeiro;
import br.com.VeiculosPajeu.Entidade.Enum.EstadoFinanceiro;
import br.com.VeiculosPajeu.Entidade.Enum.Tela;
import br.com.VeiculosPajeu.Exception.BusinessException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;

public class ControleFinanceiro extends Controle{

    @FXML
    private FlowPane flwBusca;

    @FXML
    private TextField tfdBusca;

    @FXML
    private Button btnBuscar;

    @FXML
    private GridPane grdFinanceiro;

    @FXML
    private Label lblValorTotal;

    @FXML
    private Label lblMulta;

    @FXML
    private Label lblValorPago;

    @FXML
    private Label lblDataAberta;

    @FXML
    private Label lblDataVencimento;

    @FXML
    private Label lblDataPaga;

    @FXML
    private Label lblEstado;

    @FXML
    private Button btnPagar;

    private Financeiro financeiro;
    
	@Override
	public void update(Tela tela, Entidade entidade) {
		
		if(tela == Tela.FINANCEIRO)
		{
			if (entidade instanceof Financeiro) {
				financeiro = (Financeiro) entidade;
				carregarCampos();
			}
			else if(financeiro == null)
				limparCampos();
		}
		
	}

	@Override
	protected void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void action(ActionEvent event) {
		
		Object obj = event.getSource();
		
		if(obj == btnBuscar)
		{
			try {
				
				if(!tfdBusca.getText().trim().isEmpty())
				{
					Financeiro financeiro = notificacao.selecionar(fachada.searchAllFinanceiro(tfdBusca.getText().trim()));
					
					if(financeiro != null)
					{
						this.financeiro = financeiro;
						tfdBusca.setText(financeiro+"");
						carregarCampos();
					}
				}
				else
					notificacao.mensagemAtencao();
				
			} catch (BusinessException e) {
				notificacao.mensagemErro("Buscar Financeiro", e.getMessage());
				e.printStackTrace();
			}
		}
		if(obj == btnPagar)
		{
			if(financeiro != null)
			{
				if(financeiro.getEstado() != EstadoFinanceiro.PAGO && financeiro.getEstado() != EstadoFinanceiro.CANCELADO)
				{
					ControlePagamento.setDescricao("Pagamento", 0F);
					App.notificarOuvintes(financeiro);
					notificacao.showDialogo(Tela.PAGAMENTO);				
				}
				else
					notificacao.mensagemPersonalisado(AlertType.WARNING, "Já Pago", "Conta Já Encerrada", "");				
			}
			else
				notificacao.mensagemAtencao();
		}
		
	}

	private void carregarCampos()
	{
		lblDataAberta.setText(getDate(financeiro.getData_aberta()));
		lblDataVencimento.setText(getDate(financeiro.getData_vencimento()));
		lblEstado.setText(financeiro.getEstado() + "");
		lblEstado.setTextFill(Paint.valueOf(financeiro.getEstado().getCor()));
		lblMulta.setText(financeiro.getMulta()+"");
		lblValorPago.setText(financeiro.getValor_pago() + "");
		lblValorTotal.setText(financeiro.getValor_total() + "");
		if (financeiro.getData_paga() != null)
			lblDataPaga.setText(getDate(financeiro.getData_paga()));
		else
			lblDataPaga.setText("-");
	}
	
	@Override
	protected void limparCampos() {
		
		lblDataAberta.setText("-");
		lblDataVencimento.setText("-");
		lblEstado.setText("-");
		lblEstado.setTextFill(Paint.valueOf("#FFFFFF"));
		lblMulta.setText("-");
		lblValorPago.setText("-");
		lblValorTotal.setText("-");
		lblDataPaga.setText("-");
		tfdBusca.setText("");
		
		financeiro = null;
		
	}

}
