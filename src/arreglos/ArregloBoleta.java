package arreglos;

import clases.Boleta;

import java.io.*;
import java.util.ArrayList;

public class ArregloBoleta {
	
	//  Atributos privados
	private ArrayList <Boleta> bol;
	private String numeroReceta;
	//  Constructor
    public ArregloBoleta(String numeroReceta) {
    	this.numeroReceta = numeroReceta;
    	bol = new ArrayList <Boleta> ();
		cargarBoleta();   	
    }   
	//  Operaciones publicas basicas
	public void adicionar(Boleta x) {
		bol.add(x);
	}
    public int tamaño() {
		return bol.size();
	}
	public Boleta obtener(int i) {
		return bol.get(i);
	}
	public Boleta buscar(int codigoProducto) {
		for (int i=0; i<tamaño(); i++)
			if (obtener(i).getCodigoProducto() == codigoProducto)
				return obtener(i);
		return null;
	}
	public void eliminar(Boleta x) {
		bol.remove(x);
	}
	public void grabarBoleta() {
		try {
			PrintWriter pw;
			String linea;
			Boleta x;
			pw = new PrintWriter(new FileWriter(numeroReceta + ".txt"));
			for (int i=0; i<tamaño(); i++) {
				x = obtener(i);
				linea = x.getCodigoProducto() + ";" +
						x.getCantidad() + ";" +
						x.getPrecioUnitario();
				pw.println(linea);
			}
			pw.close();
		}
		catch (Exception e) {
		}
	}
	private void cargarBoleta() {
		try {
			BufferedReader br;
			String linea; 
			String[] s;
			int codigoProducto, cantidad;
			double precioUnitario;
			br = new BufferedReader(new FileReader(numeroReceta + ".txt"));
			while ((linea = br.readLine()) != null) {
				s = linea.split(";");
				codigoProducto = Integer.parseInt(s[0].trim());
				cantidad = Integer.parseInt(s[1].trim());
				precioUnitario = Double.parseDouble(s[2].trim());
				adicionar(new Boleta(codigoProducto, cantidad, precioUnitario));
			}
			br.close();
		}
		catch (Exception e) {
		}
	}
	
}