package br.com.VeiculosPajeu.Util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class Backup {

	private static Backup instance;

	private Backup() {
	}

	public static Backup getInstance() {
		if (instance == null)
			instance = new Backup();
		return instance;
	}

	public void backup(String diretorio) throws IOException {
		ProcessBuilder pb = null;
		Process p;
		BufferedReader br = null;
		if (System.getProperty("os.name").equalsIgnoreCase("Linux")) {
			pb = new ProcessBuilder("/usr/bin/pg_dump", "--file", diretorio, "--host", "localhost", "--port", "5432",
					"--username", "postgres", "--no-password", "--verbose", "--format=t", "--blobs", "Veiculos Pajeu");
		} else if (System.getProperty("os.name").equalsIgnoreCase("Windows"))
			pb = new ProcessBuilder("C:\\Program Files\\PostgreSQL\\10\\bin\\pg_dump.exe", "-i", "-h", "localhost",
					"-p", "5432", "-U", "postgres", "-F", "c", "-b", "-v", "-f", diretorio, "Veiculos Pajeu");

		pb.environment().put("PGPASSWORD", "admin");
		pb.redirectErrorStream(true);
		p = pb.start();
		final InputStreamReader isr = new InputStreamReader(p.getInputStream());
		br = new BufferedReader(isr);

		String line;
		String temp = null;
		int i = 0;
		while ((line = br.readLine()) != null) {
			if (i == 0) {
				temp = line;
			}
			System.out.println(temp);
		}

	}

	public static void main(String[] args) {

//		try {
//			backup("/home/mael/Área de Trabalho/backup");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}

		try {
			System.out.println("/  -> " + new File("/").getCanonicalPath());
			System.out.println(".. -> " + new File("..").getCanonicalPath());
			System.out.println(".  -> " + new File(".").getCanonicalPath());

			System.out.println(System.getProperty("user.home"));
			System.out.println(System.getProperty("user.desktop"));

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
