package br.com.VeiculosPajeu.Business;

import br.com.VeiculosPajeu.Business.Interface.IBusinessConfiguracao;
import br.com.VeiculosPajeu.Dao.DaoConfiguracao;
import br.com.VeiculosPajeu.Dao.Interface.IDaoConfiguracao;
import br.com.VeiculosPajeu.Entidade.Configuracao;
import br.com.VeiculosPajeu.Exception.ValidationException;

public class BusinessConfiguracao extends Business<Configuracao> implements IBusinessConfiguracao{

	private IDaoConfiguracao daoConfiguracao;
	
	public BusinessConfiguracao() {
		daoConfiguracao = new DaoConfiguracao();
		init(daoConfiguracao);
	}
	
	@Override
	public void validation(Configuracao entidade) throws ValidationException {
		
		if(entidade.getValorKmControle() == null || entidade.getValorKmControle() == 0)
			throw new ValidationException("INFORME O VALOR DO MODO KM CONTROLE");
		if(entidade.getValorKmLivre() == null || entidade.getValorKmLivre() == 0)
			throw new ValidationException("INFORME O VALOR DO MODO KM LIVRE");
		
	}

}
