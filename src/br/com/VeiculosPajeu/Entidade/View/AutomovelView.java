package br.com.VeiculosPajeu.Entidade.View;

import javax.persistence.Entity;

import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

@Entity
@Immutable
@Subselect("select * from AutomovelView")
public class AutomovelView extends VeiculoView{

	private static final long serialVersionUID = 1L;

}
