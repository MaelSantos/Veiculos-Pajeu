package br.com.VeiculosPajeu.Controle;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.VeiculosPajeu.App.App;
import br.com.VeiculosPajeu.Entidade.Categoria;
import br.com.VeiculosPajeu.Entidade.Cliente;
import br.com.VeiculosPajeu.Entidade.Entidade;
import br.com.VeiculosPajeu.Entidade.Filial;
import br.com.VeiculosPajeu.Entidade.Funcionario;
import br.com.VeiculosPajeu.Entidade.Locacao;
import br.com.VeiculosPajeu.Entidade.Reserva;
import br.com.VeiculosPajeu.Entidade.SuperUsuario;
import br.com.VeiculosPajeu.Entidade.Usuario;
import br.com.VeiculosPajeu.Entidade.Veiculo;
import br.com.VeiculosPajeu.Entidade.Enum.Tela;
import br.com.VeiculosPajeu.Entidade.Enum.TipoBusca;
import br.com.VeiculosPajeu.Exception.BusinessException;
import br.com.VeiculosPajeu.Util.SynchronizedToken;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class ControleBusca extends Controle {

	@FXML
	private Label lblTitulo;

	@FXML
	private TextField tfdBuscar;

	@FXML
	private Button btnBuscar;

	@FXML
	private ComboBox<TipoBusca> cbxTipo;

	@FXML
	private TableView<Entidade> tblBusca;

	@FXML
	private TableColumn<? extends Entidade, ?> col1;

	@FXML
	private TableColumn<? extends Entidade, ?> col2;

	@FXML
	private TableColumn<? extends Entidade, ?> col3;

	@FXML
	private TableColumn<? extends Entidade, ?> col4;

	private SynchronizedToken synchronizedToken;
	private String chave;

	@Override
	protected void init() {

		synchronizedToken = SynchronizedToken.getInstance();
		chave = synchronizedToken.gerarToken();

		cbxTipo.getItems().setAll(TipoBusca.values());

		cbxTipo.setButtonCell(new ListCell<TipoBusca>() {
			@Override
			protected void updateItem(TipoBusca item, boolean empty) {
				super.updateItem(item, empty);
				if (empty || item == null) {
					setText("Tipo de Pesquisa");
				} else {
					setText(item.toString());
				}
			}
		});

		tblBusca.setOnMouseClicked(e -> {
			if (e.getClickCount() > 1)
				if (tblBusca.getSelectionModel().getSelectedItem() != null)
					App.notificarOuvintes(Tela.DETALHES, tblBusca.getSelectionModel().getSelectedItem());
		});

	}

	@Override
	public void action(ActionEvent event) {

		Object obj = event.getSource();

		if (obj == btnBuscar) {
			try {

				if (synchronizedToken.addToken(chave, tfdBuscar.getText().trim() + cbxTipo.getValue())) {
					System.out.println("buscando");
					
					if (!tfdBuscar.getText().trim().isEmpty() && cbxTipo.getValue() != null)
						switch (cbxTipo.getValue()) {
						case VEICULO:
							modificarColunas(Veiculo.class);
							tblBusca.getItems().setAll(fachada.searchAllAutomovel(tfdBuscar.getText().trim()));
							tblBusca.getItems().addAll(fachada.searchAllCarga(tfdBuscar.getText().trim()));
							tblBusca.getItems().addAll(fachada.searchAllPassageiro(tfdBuscar.getText().trim()));
							break;
						case CLIENTE:
							modificarColunas(Cliente.class);
							tblBusca.getItems().setAll(fachada.searchAllFisica(tfdBuscar.getText().trim()));
							tblBusca.getItems().addAll(fachada.searchAllJuridica(tfdBuscar.getText().trim()));
							break;
						case FILIAL:
							modificarColunas(Filial.class);
							tblBusca.getItems().setAll(fachada.searchAllFilial(tfdBuscar.getText().trim()));
							break;
						case USUARIO:
							modificarColunas(Usuario.class);
							tblBusca.getItems().setAll(fachada.searchAllFuncionario(tfdBuscar.getText().trim()));
							tblBusca.getItems().addAll(fachada.searchAllSuperUsuario(tfdBuscar.getText().trim()));
							break;
						case LOCACAO:
							modificarColunas(Locacao.class);
							tblBusca.getItems().setAll(fachada.searchAllLocacao(tfdBuscar.getText().trim()));
							break;
						case RESERVA:
							modificarColunas(Reserva.class);
							tblBusca.getItems().setAll(fachada.searchAllReserva(tfdBuscar.getText().trim()));
							break;
						case CATEGORIA:
							modificarColunas(Categoria.class);
							tblBusca.getItems().setAll(fachada.searchAllCategoria(tfdBuscar.getText().trim()));
						default:
							break;
						}
					else
						notificacao.mensagemAtencao();
				} else {
					System.out.println("não buscando");
				}

			} catch (BusinessException e) {
				notificacao.mensagemErro("Buscar " + cbxTipo.getValue(), e.getMessage());
				e.printStackTrace();
			}
		}

	}

	private void modificarColunas(Class<? extends Entidade> classe) {

		int diferenca = 0;
		TableColumn<?, ?> col;
		Field f;
		List<Field> fields = new ArrayList<>();

		int index = 0;

		fields.addAll(Arrays.asList(classe.getDeclaredFields()));
		if (classe.getSuperclass() != Entidade.class)
			fields.addAll(Arrays.asList(classe.getSuperclass().getDeclaredFields()));

		if (fields.size() < 4)
			index = fields.size();
		else
			index = 4;

		limparCampos();

		for (int i = 0; i < index; i++) {
			col = tblBusca.getColumns().get(i);
			f = fields.get(i);

			if (!f.getName().equalsIgnoreCase("serialVersionUID") && !f.getName().equalsIgnoreCase("sequence")
					&& !f.getName().equalsIgnoreCase("senha")) {
				String nome = f.getName().replaceAll("_", " ");
				nome = nome.replaceAll("cao", "ção");
				nome = nome.replaceAll("eco", "eço");
				col.setText(nome.toUpperCase());
				col.setCellValueFactory(new PropertyValueFactory<>(f.getName()));
			}
			if (classe.getDeclaredFields().length < 4) {
				diferenca = 4 - classe.getDeclaredFields().length;
				TableColumn<?, ?> col2 = tblBusca.getColumns().get(diferenca + i);

				col2.setText("");
				col2.setCellValueFactory(new PropertyValueFactory<>(""));
			}
		}

	}

	@Override
	protected void limparCampos() {
		col1.setText("");
		col1.setCellValueFactory(new PropertyValueFactory<>(""));

		col2.setText("");
		col2.setCellValueFactory(new PropertyValueFactory<>(""));

		col3.setText("");
		col3.setCellValueFactory(new PropertyValueFactory<>(""));

		col4.setText("");
		col4.setCellValueFactory(new PropertyValueFactory<>(""));
	}

	@Override
	public void update(Tela tela, Entidade entidade) {

		if (tela == Tela.MENU && entidade != null)
			if (entidade instanceof Usuario) {

				tblBusca.getItems().clear();
				cbxTipo.getSelectionModel().clearSelection();

				if (entidade instanceof Funcionario) {
					Funcionario funcionario = (Funcionario) entidade;

					switch (funcionario.getTipoFuncionario()) {
					case ADMINISTRADOR:
						if (!cbxTipo.getItems().contains(TipoBusca.FILIAL)) {
							cbxTipo.getItems().clear();
							cbxTipo.getItems().addAll(TipoBusca.values());
							cbxTipo.getItems().remove(TipoBusca.USUARIO);
						}
						break;
					case ATENDENTE:
						if (!cbxTipo.getItems().contains(TipoBusca.FILIAL)) {
							cbxTipo.getItems().clear();
							cbxTipo.getItems().addAll(TipoBusca.values());
							cbxTipo.getItems().remove(TipoBusca.USUARIO);
						}
						break;
					default:
						break;
					}

					if (cbxTipo.getItems().contains(TipoBusca.USUARIO)) {
						cbxTipo.getItems().remove(TipoBusca.USUARIO);
						cbxTipo.getItems().remove(TipoBusca.CATEGORIA);
					}
				} else if (entidade instanceof SuperUsuario) {
					if (!cbxTipo.getItems().contains(TipoBusca.USUARIO)) {
						cbxTipo.getItems().clear();
						cbxTipo.getItems().addAll(TipoBusca.values());
					}
				} else if (entidade instanceof Cliente) {
					if (cbxTipo.getItems().contains(TipoBusca.CLIENTE)) {
						cbxTipo.getItems().clear();
						cbxTipo.getItems().add(TipoBusca.RESERVA);
					}
				}

			}

	}

}
