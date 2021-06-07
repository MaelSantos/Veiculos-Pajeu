package br.com.VeiculosPajeu.Controle;

import java.time.LocalDate;
import java.util.List;

import com.jfoenix.controls.JFXTimePicker;

import br.com.VeiculosPajeu.Entidade.Categoria;
import br.com.VeiculosPajeu.Entidade.Cliente;
import br.com.VeiculosPajeu.Entidade.Entidade;
import br.com.VeiculosPajeu.Entidade.Funcionario;
import br.com.VeiculosPajeu.Entidade.Reserva;
import br.com.VeiculosPajeu.Entidade.SuperUsuario;
import br.com.VeiculosPajeu.Entidade.Usuario;
import br.com.VeiculosPajeu.Entidade.Enum.Tela;
import br.com.VeiculosPajeu.Entidade.Enum.TipoFuncionario;
import br.com.VeiculosPajeu.Entidade.Enum.TipoLocacao;
import br.com.VeiculosPajeu.Exception.BusinessException;
import br.com.VeiculosPajeu.Exception.ValidationException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;

public class ControleReserva extends Controle {

	@FXML
    private Button btnReservar;

    @FXML
    private Label lblTitulo;

    @FXML
    private ComboBox<TipoLocacao> cbxTipoLocacao;

    @FXML
    private TextField tfdCliente;

    @FXML
    private Button btnBuscarCliente;

    @FXML
    private ComboBox<Categoria> cbxCategoria;

    @FXML
    private Button btnMaisCliente;

    @FXML
    private Button btnMaisCategoria;

    @FXML
    private DatePicker dtpReserva;

    @FXML
    private DatePicker dtpAlocacao;

    @FXML
    private DatePicker dtpDevolucao;

    @FXML
    private JFXTimePicker tpcHoraLocacao;
	
	private Reserva reserva;
	private Cliente cliente;
	private Usuario usuario;

	@Override
	public void update(Tela tela, Entidade entidade) {

		if(tela == Tela.MENU)
		{
			if (entidade instanceof Usuario) 
			{
				usuario = (Usuario) entidade;
				if (usuario instanceof Funcionario) {
					Funcionario funcionario = (Funcionario) usuario;

					if(funcionario.getTipoFuncionario() == TipoFuncionario.ATENDENTE)
					{
						btnMaisCategoria.setVisible(false);
						btnMaisCliente.setVisible(true);
						btnBuscarCliente.setVisible(true);
						cliente = null;
					}
					else
					{
						btnMaisCategoria.setVisible(true);
						btnMaisCliente.setVisible(true);
						btnBuscarCliente.setVisible(true);
						cliente = null;
					}
				}else if (usuario instanceof SuperUsuario) {
					btnMaisCategoria.setVisible(true);
					btnMaisCliente.setVisible(true);
					btnBuscarCliente.setVisible(true);
					cliente = null;
				}
				else if (usuario instanceof Cliente) {
					cliente = (Cliente) usuario;
					btnMaisCategoria.setVisible(false);
					btnMaisCliente.setVisible(false);
					tfdCliente.setText(cliente+"");
					btnBuscarCliente.setVisible(false);			
				}
			}
		}
		if (entidade instanceof Categoria) {
			Categoria categoria = (Categoria) entidade;
			cbxCategoria.getItems().add(categoria);
		}
		if (tela == Tela.RESERVA) {

			lblTitulo.setText(tela + "");
			limparCampos();

		} else if (tela == Tela.EDITAR_RESERVA) {
			if (entidade instanceof Reserva) {
				reserva = (Reserva) entidade;
				carregarCampos();
			}

			lblTitulo.setText(tela + "");
		}
	}

	@Override
	protected void init() {

		dtpReserva.setValue(LocalDate.now());

		cbxTipoLocacao.getItems().setAll(TipoLocacao.values());
		
		cbxTipoLocacao.setButtonCell(new ListCell<TipoLocacao>() {
			@Override
			protected void updateItem(TipoLocacao item, boolean empty) {
				super.updateItem(item, empty);
				if (empty || item == null) {
					setText("Tipo de Locação");
				} else {
					setText(item.toString());
				}
			}
		});

		cbxCategoria.setButtonCell(new ListCell<Categoria>() {
			@Override
			protected void updateItem(Categoria item, boolean empty) {
				super.updateItem(item, empty);
				if (empty || item == null) {
					setText("Categoria");
				} else {
					setText(item.toString());
				}
			}
		});

		try {
			cbxCategoria.getItems().addAll(fachada.searchAllCategoria());
		} catch (BusinessException e) {
			notificacao.mensagemErro("Carregar Categorias", e.getMessage());
			e.printStackTrace();
		}

	}

	@Override
	public void action(ActionEvent event) {

		Object obj = event.getSource();

		if (obj == btnReservar) {
			try {
				
				carregarReserva();
				fachada.saveReserva(reserva);
				notificacao.mensagemSucesso("Reserva Feita Com Sucesso");
				cliente = null;
				limparCampos();					
				
			} catch (BusinessException | ValidationException e) {
				e.printStackTrace();
				notificacao.mensagemErro("Realizar Reserva", e.getMessage());
			}
		} else if (obj == btnBuscarCliente) {

			try {
				if(!tfdCliente.getText().trim().isEmpty())
				{
					List<Cliente> clientes = fachada.searchAllCliente(tfdCliente.getText().trim());
					Cliente cliente = notificacao.selecionar(clientes);
					
					if (cliente != null)
						this.cliente = cliente;
						tfdCliente.setText(cliente + "");
				}
				else 
					notificacao.mensagemAtencao();


			} catch (BusinessException e) {
				notificacao.mensagemErro("Buscar Cliente", e.getMessage());
				e.printStackTrace();
			}
		}
		else if(obj == btnMaisCliente)
		{
			notificacao.showDialogo(Tela.CADASTRO_CLIENTE);
		}
		else if(obj == btnMaisCategoria)
		{
			notificacao.showDialogo(Tela.CADASTRO_CATEGORIA);
		}
	}

	private void carregarReserva() throws ValidationException {

		if (reserva == null) {
			reserva = new Reserva();
			reserva.setAtivo(true);
			reserva.setUsuario(usuario);
		}		
		reserva.setCliente(cliente);
		reserva.setCategoria(cbxCategoria.getValue());
		reserva.setData_reserva(dtpReserva.getValue());
		reserva.setData_locacao(dtpAlocacao.getValue());
		reserva.setData_devolucao(dtpDevolucao.getValue());
		reserva.setTipo_locacao(cbxTipoLocacao.getValue());
		reserva.setHora_reserva(tpcHoraLocacao.getValue());
	}

	private void carregarCampos() {

		cliente = reserva.getCliente();
		usuario = reserva.getUsuario();
		tfdCliente.setText(cliente + "");

		cbxCategoria.setValue(reserva.getCategoria());
		cbxTipoLocacao.setValue(reserva.getTipo_locacao());
		tpcHoraLocacao.setValue(reserva.getHora_reserva());
		
		dtpReserva.getEditor().setText(getDate(reserva.getData_reserva()));
		dtpAlocacao.getEditor().setText(getDate(reserva.getData_locacao()));
		dtpDevolucao.getEditor().setText(getDate(reserva.getData_devolucao()));
		
		dtpReserva.setValue(reserva.getData_reserva());
		dtpAlocacao.setValue(reserva.getData_locacao());
		dtpDevolucao.setValue(reserva.getData_devolucao());
	}

	@Override
	protected void limparCampos() {

		if(cliente == null)
			tfdCliente.setText("");
		dtpAlocacao.getEditor().setText("");
		dtpAlocacao.setValue(null);
		dtpDevolucao.getEditor().setText("");
		dtpDevolucao.setValue(null);
		dtpReserva.setValue(LocalDate.now());

		cbxCategoria.getSelectionModel().clearSelection();
		cbxTipoLocacao.getSelectionModel().clearSelection();

		tpcHoraLocacao.setValue(null);
		tpcHoraLocacao.getEditor().setText("");
		
		reserva = null;

	}

}
