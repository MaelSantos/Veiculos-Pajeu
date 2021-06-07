package br.com.VeiculosPajeu.Business;

import br.com.VeiculosPajeu.Business.Interface.IBusinessPassageiro;
import br.com.VeiculosPajeu.Dao.DaoPassageiro;
import br.com.VeiculosPajeu.Dao.Interface.IDao;
import br.com.VeiculosPajeu.Dao.Interface.IDaoPassageiro;
import br.com.VeiculosPajeu.Entidade.Passageiro;
import br.com.VeiculosPajeu.Exception.ValidationException;

public class BusinessPassageiro extends Business<Passageiro> implements IBusinessPassageiro {

	private IDaoPassageiro daoPassageiro;

	@Override
	public void validation(Passageiro entidade) throws ValidationException {

		if (entidade.getCategoria() == null)
			throw new ValidationException("INFORME UMA CATEGORIA");
		if (entidade.getAirbag() == null)
			throw new ValidationException("INFORME UM TIPO DE AIR BAG");
		if (entidade.getAno_fabricacao() == null || entidade.getAno_fabricacao() == 0)
			throw new ValidationException("INFORME UM ANO DE FABRICAÇÃO");
		if (entidade.getAno_modelo() == null || entidade.getAno_modelo() == 0)
			throw new ValidationException("INFORME O ANO DO MODELO");
		if (entidade.getCor().trim().isEmpty())
			throw new ValidationException("INFORME UMA COR");
		if (entidade.getFabricante().trim().isEmpty())
			throw new ValidationException("INFORME O FACRICANTE");
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
			throw new ValidationException("INFORME O TIPO DE COMBUSTIVEL");
		if (entidade.getTorque_motor().trim().isEmpty())
			throw new ValidationException("INFORME  TORQUE DO MOTOR");
		if (entidade.getFilial() == null)
			throw new ValidationException("INFORME UMA FILIAL");
	}

	@Override
	public IDao<Passageiro> createDao() throws ValidationException {
		if (daoPassageiro == null)
			daoPassageiro = new DaoPassageiro();
		return daoPassageiro;
	}

}
