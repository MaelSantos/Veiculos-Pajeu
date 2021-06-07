package br.com.VeiculosPajeu.Business;

import br.com.VeiculosPajeu.Business.Interface.IBusinessAutomovel;
import br.com.VeiculosPajeu.Dao.DaoAutomovel;
import br.com.VeiculosPajeu.Dao.Interface.IDao;
import br.com.VeiculosPajeu.Dao.Interface.IDaoAutomovel;
import br.com.VeiculosPajeu.Entidade.Automovel;
import br.com.VeiculosPajeu.Exception.ValidationException;

public class BusinessAutomovel extends Business<Automovel> implements IBusinessAutomovel {

	private IDaoAutomovel daoAutomovel;

	@Override
	public void validation(Automovel entidade) throws ValidationException {

		if (entidade.getTamanho() == null)
			throw new ValidationException("INFORME O TAMANHO");
		if (entidade.getTipo_cambio() == null)
			throw new ValidationException("INFORME O TIPO DE CÂMBIO");
		if (entidade.getAno_fabricacao() == null || entidade.getAno_fabricacao() == 0)
			throw new ValidationException("INFORME O ANO DE FABRICAÇÃO");
		if (entidade.getAno_modelo() == null || entidade.getAno_modelo() == 0)
			throw new ValidationException("INFORME O ANO DO MODELO");
		if (entidade.getCategoria() == null)
			throw new ValidationException("INFORME UMA CATEGORIA");
		if (entidade.getCor().trim().isEmpty())
			throw new ValidationException("INFORME UMA COR");
		if (entidade.getFabricante().trim().isEmpty())
			throw new ValidationException("INFORME O FABICANTE");
		if (entidade.getModelo().trim().isEmpty())
			throw new ValidationException("INFORME O MODELO");
		if (entidade.getNumero_chassi().trim().isEmpty())
			throw new ValidationException("INFORME O NUMERO DO CHASSI");
		if (entidade.getNumero_motor().trim().isEmpty())
			throw new ValidationException("INFORME O NUMERO DO MOTOR");
		if (entidade.getNumero_passageiros() == null || entidade.getNumero_passageiros() == 0)
			throw new ValidationException("INFORME O NUMERO DE PASSAGEIROS");
		if (entidade.getNumero_portas() == null || entidade.getNumero_portas() == 0)
			throw new ValidationException("INFORME O NUMERO DE PORTAS");
		if (entidade.getPlaca().trim().isEmpty())
			throw new ValidationException("INFORME A PLACA");
		if (entidade.getTipo_combustivel() == null)
			throw new ValidationException("INFORME O TIPO DE COMBUSTÍVEL");
		if (entidade.getTorque_motor().trim().isEmpty())
			throw new ValidationException("INFORME O TORQUE DO MOTOR");
		if (entidade.getFilial() == null)
			throw new ValidationException("INFORME UMA FILIAL");
	}

	@Override
	public IDao<Automovel> createDao() throws ValidationException {
		if (daoAutomovel == null)
			daoAutomovel = new DaoAutomovel();
		return daoAutomovel;
	}

}
