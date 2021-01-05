package br.com.VeiculosPajeu.Controle;

import java.time.LocalTime;

import br.com.VeiculosPajeu.Exception.BusinessException;
import br.com.VeiculosPajeu.Fachada.Fachada;
import br.com.VeiculosPajeu.Util.Backup;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.scene.text.Text;

public class ControleAtualizacao {

	private static ControleAtualizacao instance;
	
	private Service<Void> service;
	private Fachada fachada;
	
	private LocalTime update;
	private LocalTime atual;
	
	private boolean backup;
	private int minutos;

	private Text text;
	
	private ControleAtualizacao() {
		fachada = Fachada.getInstance();
		
		update = LocalTime.of(12, 00);
		atual = LocalTime.now();
		backup = true;
		
		createService();		
		service.start();
		
		text = new Text();
		text.textProperty().bind(service.messageProperty());
	}

	public static ControleAtualizacao getInstace() {
		if (instance == null)
			instance = new ControleAtualizacao();
		return instance;
	}
	
	private void createService()
	{
		service = new Service<Void>() {
			
			@Override
			protected Task<Void> createTask() {
				return new Task<Void>() {
					
					@Override
					protected Void call() throws Exception {
						
						while(true)
						{
							if(minutos >= 30)
							{
								fachada.verificarAtrasadosFinanceiro();
								fachada.verificarAtrasadosReserva();
								fachada.verificarManutencaoVeiculo();
								System.out.println("Atualizado");
								
								minutos = 0;
								
								if(backup && atual.isAfter(update))
								{
									//realizar backup 
									Backup.backup(System.getProperty("user.home")+"/backup");
									backup = false;
									System.out.println("Backup Realizado");
								}
							}

							Thread.sleep(60000);// 1 minuto
							minutos++;
							System.out.println(minutos+":00 mins");
						}
					}
					
					@Override
					protected void succeeded() {
						try {
							fachada.verificarAtrasadosFinanceiro();
							fachada.verificarAtrasadosReserva();
							fachada.verificarManutencaoVeiculo();
						} catch (BusinessException e) {
						}
						super.succeeded();
					}
					
					@Override
					protected void scheduled() {
						try {
							fachada.verificarAtrasadosFinanceiro();
							fachada.verificarAtrasadosReserva();
							fachada.verificarManutencaoVeiculo();
						} catch (BusinessException e) {
						}
						super.scheduled();
					}
				};
			}
		};
		
	}
	
}
