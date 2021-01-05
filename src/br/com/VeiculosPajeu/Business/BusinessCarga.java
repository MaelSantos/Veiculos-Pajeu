package br.com.VeiculosPajeu.Business;

import br.com.VeiculosPajeu.Business.Interface.IBusinessCarga;
import br.com.VeiculosPajeu.Dao.DaoCarga;
import br.com.VeiculosPajeu.Dao.Interface.IDaoCarga;
import br.com.VeiculosPajeu.Entidade.Carga;
import br.com.VeiculosPajeu.Exception.ValidationException;

public class BusinessCarga extends Business<Carga> implements IBusinessCarga {

	private IDaoCarga daoCarga;

	public BusinessCarga() {
		daoCarga = new DaoCarga();
		init(daoCarga);
	}

	@Override
	public void validation(Carga entidade) throws ValidationException {

		if (entidade.getAcionamento() == null)
			throw new ValidationException("INFORME O ACIONAMENTO");
		if (entidade.getAno_fabricacao() == null || entidade.getAno_fabricacao() == 0)
			throw new ValidationException("INFORME O ANO DE FABRICAÇÃO");
		if (entidade.getAno_modelo() == null || entidade.getAno_modelo() == 0)
			throw new ValidationException("INFORME O ANO DO MODELO");
		if (entidade.getCapacidade_carga() == null || entidade.getCapacidade_carga() == 0)
			throw new ValidationException("INFORME A CAPACIDADE DA CARGA");
		if (entidade.getCategoria() == null)
			throw new ValidationException("INFORME UMA CATEGORIA");
		if (entidade.getCor().trim().isEmpty())
			throw new ValidationException("INFORME UMA COR");
		if (entidade.getDesempenho().trim().isEmpty())
			throw new ValidationException("INFORME O DESEMPENHO");
		if (entidade.getDistancia().trim().isEmpty())
			throw new ValidationException("INFORME A DISTÂNCIA");
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
		if (entidade.getPotencia().trim().isEmpty())
			throw new ValidationException("INFORME A POTENCIA");
		if (entidade.getTipo_combustivel() == null)
			throw new ValidationException("INFORME O TIPO DE COMBUSTÍVEL");
		if (entidade.getTorque_motor().trim().isEmpty())
			throw new ValidationException("INFORME O TORQUE DO MOTOR");
		if (entidade.getVolume_combustivel().trim().isEmpty())
			throw new ValidationException("INFORME O VOLUME DO COMBUSTÍVEL");
		if(entidade.getFilial() == null)
			throw new ValidationException("INFORME UMA FILIAL");
	}

}
