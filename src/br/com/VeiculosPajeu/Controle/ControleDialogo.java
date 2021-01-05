package br.com.VeiculosPajeu.Controle;

import java.util.ArrayList;
import java.util.List;

import br.com.VeiculosPajeu.App.App;
import br.com.VeiculosPajeu.Entidade.Entidade;
import br.com.VeiculosPajeu.Entidade.Enum.Tela;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class ControleDialogo extends Controle{
	
    @FXML
    private AnchorPane paneEdit;

    public static ControleDialogo controleDialogo;
    
    private List<Tela> telas;
    private Tela telaAnterior;
    
	@Override
	public void update(Tela tela, Entidade entidade) {
		
	}

	@Override
	protected void init() {
		
		controleDialogo = this;
		telas = new ArrayList<>();
		
	}

	@Override
	public void action(ActionEvent event) {
		// TODO Stub de m�todo gerado automaticamente
		
	}
	
	@SuppressWarnings("static-access")
	public void updateTela(Tela tela) {
		
		if(tela != Tela.MENU && tela != Tela.LOGIN)
		{
			Pane paneNovo = App.changePane(tela);
			
			paneEdit.setBottomAnchor(paneNovo, 0.0);
			paneEdit.setTopAnchor(paneNovo, 0.0);
			paneEdit.setLeftAnchor(paneNovo, 0.0);
			paneEdit.setRightAnchor(paneNovo, 0.0);
			paneEdit.getChildren().setAll(paneNovo);
			
			if(tela != telaAnterior)
				telas.add(tela);
			System.out.println(telas);
		}
	}
	
	@Override
	protected void limparCampos() {
		telas.clear();
	}

	public Tela getTelaAnterior()
	{
		if(telas.size() - 1 >= 0)
		{
			telaAnterior = telas.get(telas.size() - 1); 
//			telas.remove(telaAnterior);
			System.out.println("Retorno: "+telaAnterior);
			return telaAnterior;
		}
//		else if(telas.isEmpty())
		System.out.println("Retorno: Null");
			return null;
		
//		return getTelaAnterior();
	}
	
	public Tela getTelaPosicao(int i)
	{
		if(i > -1 && i <= telas.size()-1)
		{
			telaAnterior = telas.get(i); 
			System.out.println("Retorno: "+telaAnterior);
			return telaAnterior;
		}

		System.out.println("Retorno: Null");
			return null;
		
	}
	
	public List<Tela> getTelas() {
		return telas;
	}

	public Tela getTela() {
		return telaAnterior;
	}
	
	public static void main(String[] args) {
		
		controleDialogo = new ControleDialogo();
		controleDialogo.init();
		
		controleDialogo.telas.add(Tela.RESETAR_SENHA);
		controleDialogo.telas.add(Tela.CADASTRO_CATEGORIA);
		controleDialogo.telas.add(Tela.ATRASADOS);
		System.out.println(controleDialogo.telas);
		controleDialogo.getTelaAnterior();
		controleDialogo.getTelaPosicao(controleDialogo.getTelas().size()-2);
	}
	
}
