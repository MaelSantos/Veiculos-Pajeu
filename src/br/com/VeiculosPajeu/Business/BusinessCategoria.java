package br.com.VeiculosPajeu.Business;

import java.util.Collections;
import java.util.List;

import br.com.VeiculosPajeu.Business.Interface.IBusinessCategoria;
import br.com.VeiculosPajeu.Dao.DaoCategoria;
import br.com.VeiculosPajeu.Dao.Interface.IDao;
import br.com.VeiculosPajeu.Dao.Interface.IDaoCategoria;
import br.com.VeiculosPajeu.Entidade.Categoria;
import br.com.VeiculosPajeu.Exception.BusinessException;
import br.com.VeiculosPajeu.Exception.DaoException;
import br.com.VeiculosPajeu.Exception.ValidationException;
import br.com.VeiculosPajeu.Util.Comparar;

public class BusinessCategoria extends Business<Categoria> implements IBusinessCategoria {

	private IDaoCategoria daoCategoria;

	@Override
	public Categoria nextCategoria(Categoria categoria) throws BusinessException {

		try {
			List<Categoria> categorias = daoCategoria.searchAll();
			System.out.println("Antes: " + categorias);
			Collections.sort(categorias, new Comparar());
			System.out.println("Depois: " + categorias);

			int index = categorias.indexOf(categoria);
			System.out.println(index);

			System.out.println("Categoria Escolhida: " + categoria);

			if (index + 1 >= categorias.size())
				throw new BusinessException("Não Existe Categorias Superiores");
			else
				return categorias.get(index + 1);

		} catch (DaoException e) {
			throw new BusinessException(e.getMessage());
		}

	}

	@Override
	public void validation(Categoria entidade) throws ValidationException {

		if (entidade.getFilial() == null)
			throw new ValidationException("INFORME UMA FILIAL");
		if (entidade.getNome().trim().isEmpty())
			throw new ValidationException("INFORME UM NOME");
		if (entidade.getTamanho() == null)
			throw new ValidationException("INFORME O TAMANHO");
		if (entidade.getTipo() == null)
			throw new ValidationException("INFORME O TIPO DE CATEGORIA");
		if (entidade.getValor() == null || entidade.getValor() == 0)
			throw new ValidationException("INFORME O VALOR");

	}

	@Override
	public IDao<Categoria> createDao() throws ValidationException {
		if (daoCategoria == null)
			daoCategoria = new DaoCategoria();
		return daoCategoria;
	}

}
