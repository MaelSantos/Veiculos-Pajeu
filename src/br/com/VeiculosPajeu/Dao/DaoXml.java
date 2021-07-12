package br.com.VeiculosPajeu.Dao;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.Dom4JDriver;

import br.com.VeiculosPajeu.Dao.Interface.IDaoXml;

public class DaoXml implements IDaoXml {

	private static DaoXml instance;
	private XStream xStream;
	private File file;

	private String aparencia;

	private DaoXml() {

		xStream = new XStream(new Dom4JDriver());
		xStream.alias("string", String.class);
		xStream.autodetectAnnotations(true);
		XStream.setupDefaultSecurity(xStream);
		xStream.allowTypes(new Class[] { String.class });

//		try {
//			file = new File(getClass().getClassLoader().getResource("aparencia.xml").getFile());
//		} catch (Exception e) {
			file = new File("res/aparencia.xml");
//		}		
		
		aparencia = buscarAparencia();
	}

	public static synchronized DaoXml getInstance() {
		if (instance == null)
			instance = new DaoXml();
		return instance;
	}

	@Override
	public synchronized String salvarAparencia(String aparencia) {

		try {
			if (!file.exists())
				file.createNewFile();
			else {
				file.delete();
				file.createNewFile();
			}

			OutputStream stream = new FileOutputStream(file);
			xStream.toXML(aparencia, stream);
			this.aparencia = aparencia;

		} catch (IOException e) {
			e.printStackTrace();
		}

		return aparencia;
	}

	@Override
	public synchronized String buscarAparencia() {

		String aparencia = null;

		try {
			if (!file.exists())
				salvarAparencia("#696969");
			else
				aparencia = (String) xStream.fromXML(file);
			this.aparencia = aparencia;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return aparencia;
	}

	public String getAparencia() {

		if (aparencia != null)
			if (!aparencia.isEmpty())
				return aparencia;
		return buscarAparencia();
	}

	public void setAparencia(String aparencia) {
		this.aparencia = aparencia;
		salvarAparencia(aparencia);
	}
	
}
