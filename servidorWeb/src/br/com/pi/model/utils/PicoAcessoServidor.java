package br.com.pi.model.utils;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import br.com.pi.model.LogAcesso;
import br.com.pi.service.ServidorService;

public class PicoAcessoServidor extends RelatorioConsulta{

	ArrayList<LogAcesso> listaPico;
	ServidorService servidor;
	String canvas;
	
	public PicoAcessoServidor() throws SQLException {
	servidor = new ServidorService();
	listaPico = servidor.horaPicoAcesso();
	
	}
	
	
	@Override
	public String consultarDB() {
		
		
		String canvas = "";
		// Gera o horario com mais requisicoes
		for(LogAcesso log: listaPico) {
			String data;
			try {
				data = formataData(log.getAtributo());
				canvas += "\n	       { x: " + data  + " , y: " +   log.getContador() + " },";
			} catch (ParseException e) {
				e.printStackTrace();
			}
			}
			
		
		return canvas;
	}
	
	public static String formataData(String data) throws ParseException {
		 
		 Date dataHora =new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(data);  
		 Calendar cal = new GregorianCalendar();
		 cal.setTime(dataHora);
		 
		 
		 int ano = cal.get(Calendar.YEAR);
		 int mes = cal.get(Calendar.MONTH);
		 int dia = cal.get(Calendar.DAY_OF_MONTH);
		 int hora = cal.get(Calendar.HOUR_OF_DAY);
		 
		 System.out.println("new Date("+ano+", "+mes+", "+dia+", "+hora+", 00, 0, 0)");
		 
		 return "new Date(20"+ano+", "+mes+", "+dia+", "+hora+", 00, 0, 0)";
	}

	
	public static void main(String[] args) throws SQLException, ParseException {
		
		ServidorService servidor = new ServidorService();
		ArrayList<LogAcesso> listaPico = servidor.horaPicoAcesso();
		String canvas = "";
		
		// Gera o top 10 valores de arquivos mais acessados pelo servidor. Gráfico esperado: Barras
				for(LogAcesso log: listaPico) {
				String data = formataData(log.getAtributo());
				canvas += "\n	       { x: " + data  + " , y: " +   log.getContador() + " },";
				}
				
				System.out.println(canvas);
				
 
	}
}