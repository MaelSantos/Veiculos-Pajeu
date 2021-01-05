package br.com.VeiculosPajeu.Controle;

import br.com.VeiculosPajeu.App.App;
import br.com.VeiculosPajeu.Dao.DaoXml;
import br.com.VeiculosPajeu.Dao.Interface.IDaoXml;
import br.com.VeiculosPajeu.Entidade.Entidade;
import br.com.VeiculosPajeu.Entidade.Enum.Tela;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;

public class ControleAparencia extends Controle{

    @FXML
    private ColorPicker cpkFundo;
    
    @FXML
    private Button btnRedefinirPadrao;    
    
    private static String color = DaoXml.getInstance().getAparencia();
    private IDaoXml daoXml;

	@Override
	public void update(Tela tela, Entidade entidade) {	
	}

	@Override
	protected void init() {
		
		daoXml = DaoXml.getInstance();
		
		cpkFundo.setValue(Color.valueOf(color.replace("#", "0x")));
	}

	@Override
	public void action(ActionEvent event) {
		
		Object obj = event.getSource();
		
		if(obj == cpkFundo)
		{			
			color = cpkFundo.getValue()+"";
			color = color.replace("0x", "#");
			updateCorlor();
		}
		else if(obj == btnRedefinirPadrao)
		{
			color = "#696969";
			limparCampos();
			updateCorlor();
		}
		
	}

	private void updateCorlor() {
		App.updateColor(color);
		notificacao.setColor(color);
		daoXml.salvarAparencia(color);
	}

	@Override
	protected void limparCampos() {
		
		cpkFundo.setValue(Color.valueOf(color));

	}
	
	public static String getColor() {
		return color;
	}
	
}