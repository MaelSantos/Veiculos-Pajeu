package br.com.VeiculosPajeu.Controle;

import br.com.VeiculosPajeu.App.App;
import br.com.VeiculosPajeu.Entidade.Automovel;
import br.com.VeiculosPajeu.Entidade.Carga;
import br.com.VeiculosPajeu.Entidade.Categoria;
import br.com.VeiculosPajeu.Entidade.Cliente;
import br.com.VeiculosPajeu.Entidade.Entidade;
import br.com.VeiculosPajeu.Entidade.Filial;
import br.com.VeiculosPajeu.Entidade.Financeiro;
import br.com.VeiculosPajeu.Entidade.Fisica;
import br.com.VeiculosPajeu.Entidade.Funcionario;
import br.com.VeiculosPajeu.Entidade.Juridica;
import br.com.VeiculosPajeu.Entidade.Locacao;
import br.com.VeiculosPajeu.Entidade.Passageiro;
import br.com.VeiculosPajeu.Entidade.Reserva;
import br.com.VeiculosPajeu.Entidade.SuperUsuario;
import br.com.VeiculosPajeu.Entidade.Usuario;
import br.com.VeiculosPajeu.Entidade.Veiculo;
import br.com.VeiculosPajeu.Entidade.Enum.EstadoFinanceiro;
import br.com.VeiculosPajeu.Entidade.Enum.Tela;
import br.com.VeiculosPajeu.Entidade.Enum.TipoFuncionario;
import br.com.VeiculosPajeu.Exception.BusinessException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class ControleDetalhes extends Controle {

	@FXML
    private Label lblTitulo;

    @FXML
    private HBox hbxDescricao;

    @FXML
    private Text txtDescricao;

    @FXML
    private Text txtDecricao2;

    @FXML
    private Text txtDecricao3;

    @FXML
    private HBox hbxBotoes;

    @FXML
    private Button btnEditar;

    @FXML
    private Button btnDeletar;

    @FXML
    private Button btnVoltar;

	private Entidade entidade;
	
	private Usuario usuario;

	@Override
	public void update(Tela tela, Entidade entidade) {

		if (tela == Tela.DETALHES && entidade != null) {

			this.entidade = entidade;

			lblTitulo.setText("Detalhes " + entidade.getClass().getSimpleName());
			format(entidade.detalhesEntidade());
			
			if (usuario instanceof Funcionario) {
				Funcionario funcionario = (Funcionario) usuario;
				
				if(funcionario.getTipoFuncionario() == TipoFuncionario.ATENDENTE)
				{
					//atendente só phbxode modificar dados de cliente, reserva e locação
					if (entidade instanceof Cliente) {
						btnDeletar.setVisible(true);
						btnEditar.setVisible(true);
					}
					else if (entidade instanceof Reserva) {
						btnDeletar.setVisible(true);
						btnEditar.setVisible(true);	
					}
					else if (entidade instanceof Locacao) {
						btnDeletar.setVisible(true);
						btnEditar.setVisible(true);
					}
					else
					{
						btnDeletar.setVisible(false);
						btnEditar.setVisible(false);						
					}
				}
				else
				{
					btnDeletar.setVisible(true);
					btnEditar.setVisible(true);
				}
			}else if (usuario  instanceof SuperUsuario) {
				btnDeletar.setVisible(true);
				btnEditar.setVisible(true);
			}
			else
			{
				btnDeletar.setVisible(false);
				btnEditar.setVisible(false);	
			}
		}
		if(tela == Tela.MENU)
		{
			if (entidade instanceof Usuario) {
				usuario = (Usuario) entidade;
			}
		}
	}

	private void format(String texto) {
		String novo;

		novo = texto.replaceAll("true", "Sim");
		novo = novo.replaceAll("false", "Não");

		String primeiro = "";
		String segundo = "";
		String terceiro = "";
		
		int index;
		if (novo.contains("*")) {
			index = novo.indexOf("*");
			primeiro = novo.substring(0, index - 1);
			segundo = novo.substring(index + 1, novo.length());

			txtDescricao.setText(primeiro);
			txtDecricao2.setText(segundo);
			txtDecricao3.setText("");

		} 
		else
		{
			txtDescricao.setText(novo);
			txtDecricao2.setText("");
			txtDecricao3.setText("");
		}
		if(segundo.contains("*")) 
		{
			index = segundo.indexOf("*");
			novo = segundo;
			segundo = novo.substring(0, index - 1);
			terceiro = novo.substring(index + 1, novo.length());
			
			txtDescricao.setText(primeiro);
			txtDecricao2.setText(segundo);
			txtDecricao3.setText(terceiro);
		}

	}

	@Override
	protected void init() {

	}

	@Override
	public void action(ActionEvent event) {

		Object obj = event.getSource();

		if (obj == btnVoltar)
			App.notificarOuvintes(Tela.BUSCAR);
		else if (obj == btnDeletar) {
			try {
				if (notificacao.showConfirmacao("Remover?",
						"Tem Certeza que Deseja Remover/Cancelar esse(a) " + entidade.getClass().getSimpleName(),
						"Os Dados Não Poderam ser Recuperados!!!")) {

					if (entidade instanceof Filial) {
						fachada.removeFilial(entidade.getId());
					}
					else if (entidade instanceof Fisica) {
						fachada.removeFisica(entidade.getId());
					}
					else if (entidade instanceof Juridica) {
						fachada.removeJuridica(entidade.getId());
					}
					else if (entidade instanceof Carga) {
						fachada.removeCarga(entidade.getId());
					}
					else if (entidade instanceof Passageiro) {
						fachada.removePassageiro(entidade.getId());
					}
					else if (entidade instanceof Automovel) {
						fachada.removeAutomovel(entidade.getId());
					}
					else if (entidade instanceof Categoria) {
						fachada.removeCategoria(entidade.getId());
					}
					else if (entidade instanceof Funcionario) {
						fachada.removeFuncionario(entidade.getId());
					}
					else if (entidade instanceof SuperUsuario) {
						fachada.removeSuperUsuario(entidade.getId());
					}
					else if (entidade instanceof Locacao) {
						Locacao locacao = (Locacao) entidade;
						if(locacao.getAtivo())
						{
							if (notificacao.showConfirmacao("Deseja Continuar?", "Cancelar Locação?", "Tem certeza Que Quer Continuar?")) {
								
								locacao.setAtivo(false);
								Financeiro financeiro = fachada.searchFinanceiro(locacao.getId());
								float valor = financeiro.getValor_pago();
								financeiro.setEstado(EstadoFinanceiro.CANCELADO);
								fachada.saveLocacao(locacao);
								fachada.saveFinanceiro(financeiro);
								
								Veiculo veiculo = locacao.getVeiculo();
								veiculo.setAlugado(false);
								
								if (veiculo instanceof Automovel) {
									fachada.saveAutomovel((Automovel) veiculo);
								} else if (veiculo instanceof Carga) {
									fachada.saveCarga((Carga) veiculo);
								} else if (veiculo instanceof Passageiro) {
									fachada.savePassageiro((Passageiro) veiculo);
								}
								
								notificacao.mensagemSucesso("Locação Desativa - É Nescessário Reembolsar "+valor+"R$ Ao Cliente");
							}							
						}else
							notificacao.mensagemErro("Cancelar Locação", "Locação Já Desativada");
					}
					else if (entidade instanceof Reserva) {
						Reserva reserva = (Reserva) entidade;
						if(reserva.getAtivo())
						{
							if (notificacao.showConfirmacao("Deseja Continuar?", "Cancelar Reserva?", "Tem certeza Que Quer Continuar?")) {
								
								reserva.setAtivo(false);
								fachada.saveReserva(reserva);
								notificacao.mensagemSucesso("Reserva Desativa Com Sucesso");
							}							
						}
						else 
							notificacao.mensagemErro("Cancelar Reserva", "Reserva Já Desativada");
						
					}

					App.notificarOuvintes(Tela.BUSCAR);

				}
			} catch (BusinessException e) {
				notificacao.mensagemErro("Remover " + entidade.getClass().getSimpleName(), e.getMessage());
				e.printStackTrace();
			}
		} else if (obj == btnEditar) {
			System.out.println(entidade);

			if (entidade instanceof Filial) {
				App.notificarOuvintes(Tela.EDITAR_FILIAL, entidade);
			} else if (entidade instanceof Cliente) {
				App.notificarOuvintes(Tela.EDITAR_CLIENTE, entidade);
			} else if (entidade instanceof Veiculo) {
				App.notificarOuvintes(Tela.EDITAR_VEICULO, entidade);
			} else if (entidade instanceof Categoria) {
				App.notificarOuvintes(Tela.EDITAR_CATEGORIA, entidade);
			} else if (entidade instanceof Usuario) {
				App.notificarOuvintes(Tela.EDITAR_USUARIO, entidade);
			} else if (entidade instanceof Locacao) {
				App.notificarOuvintes(Tela.EDITAR_LOCACAO, entidade);
			} else if (entidade instanceof Reserva) {
				App.notificarOuvintes(Tela.EDITAR_RESERVA, entidade);
			}
		}

	}

	@Override
	protected void limparCampos() {

	}

	@Override
	public void setColor(String color) {
		hbxDescricao.setStyle("-fx-background-color:" + color + ";");
		hbxBotoes.setStyle("-fx-background-color:" + color + ";");
		super.setColor(color);
	}
	
	@Override
	protected void setColor(AnchorPane pane, String color) {
		hbxDescricao.setStyle("-fx-background-color:" + color + ";");
		hbxBotoes.setStyle("-fx-background-color:" + color + ";");
		super.setColor(pane, color);
	}
}
