package br.com.VeiculosPajeu.Controle;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.VeiculosPajeu.Entidade.Entidade;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ControleSelecionar extends ControleAdapter {

	@FXML
	private Label lblTitulo;

	@FXML
	private TableView<Entidade> tblBusca;

	@FXML
	private TableColumn<? extends Entidade, Object> col1;

	@FXML
	private TableColumn<? extends Entidade, ?> col2;

	@FXML
	private TableColumn<? extends Entidade, ?> col3;

	@FXML
	private TableColumn<? extends Entidade, ?> col4;

	@FXML
	private TableColumn<? extends Entidade, ?> col5;

	public static ControleSelecionar controleSelecionar;

	@Override
	protected void init() {

		controleSelecionar = this;
	}

	public void carregarLista(List<? extends Entidade> list) {
		if (list != null)
			if (!list.isEmpty())
				modificarColunas(list.get(0).getClass());
		tblBusca.getItems().setAll(list);
	}

	private void modificarColunas(Class<? extends Entidade> classe) {

		TableColumn<?, ?> col;
		Field f;

		List<Field> fields = new ArrayList<>();

		int index = 0;

		if (classe.getSuperclass() != Entidade.class)
			fields.addAll(Arrays.asList(classe.getSuperclass().getDeclaredFields()));
		fields.addAll(Arrays.asList(classe.getDeclaredFields()));

		limparCampos();
		if (fields.size() < 5) {
			index = fields.size();
		} else
			index = 5;

		for (int i = 0; i < index; i++) {
			col = tblBusca.getColumns().get(i);
			f = fields.get(i);

			if (!f.getName().equalsIgnoreCase("serialVersionUID") && !f.getName().equalsIgnoreCase("sequence")
					&& (!f.getName().equalsIgnoreCase("senha"))) {

				String nome = f.getName().replaceAll("_", " ");
				nome = nome.replaceAll("cao", "ção");
				nome = nome.replaceAll("eco", "eço");
				col.setText(nome.toUpperCase());
				col.setCellValueFactory(new PropertyValueFactory<>(f.getName()));
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

		col5.setText("");
		col5.setCellValueFactory(new PropertyValueFactory<>(""));
	}

	public TableView<Entidade> getTblBusca() {
		return tblBusca;
	}

}
