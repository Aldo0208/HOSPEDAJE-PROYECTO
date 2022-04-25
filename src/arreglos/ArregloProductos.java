package arreglos;

import clases.Producto;

import java.io.*;
import java.util.ArrayList;

public class ArregloProductos {
	
	//	Atributos privado
	private ArrayList <Producto> prod;
	//  Constructor
	public ArregloProductos() {
		prod = new ArrayList <Producto> ();
		cargarProductos();
	}
	//  Operaciones públicas básicas
	public void adicionar(Producto x) {
		prod.add(x);
	}
	public int tamaño() {
		return prod.size();
	}
	public Producto obtener(int i) {
		return prod.get(i);
	}
	public Producto buscar(int codigoProducto) {
		for (int i=0; i<tamaño(); i++)
			if (obtener(i).getCodigoProducto() == codigoProducto)
				return obtener(i);
		return null;
	}
	public void eliminar(Producto x) {
		prod.remove(x);
	}
	public void grabarProductos() {
		try {
			PrintWriter pw;
			String linea;
			Producto x;
			pw = new PrintWriter(new FileWriter("Productos.txt"));
			for (int i=0; i<tamaño(); i++) {
				x = obtener(i);
				linea = x.getCodigoProducto() + ";" +
						x.getNombre() + ";" +
						x.getDescripcion() + ";" +
						x.getStock() + ";" +
						x.getPrecioUnitario();
				pw.println(linea);
			}
			pw.close();
		}
		catch (Exception e) {	
		}
	}
	private void cargarProductos() {
		try {
			BufferedReader br;
			String linea, nombre, descripcion;
			String[] s;
			int codigoProducto, stock;
			double precioUnitario;
			br = new BufferedReader(new FileReader("Productos.txt"));
			while ((linea = br.readLine()) != null) {
				s = linea.split(";");
				codigoProducto = Integer.parseInt(s[0].trim());
				nombre = s[1].trim();
				descripcion= s[2].trim();
				stock = Integer.parseInt(s[3].trim());
				precioUnitario = Double.parseDouble(s[4].trim());
				adicionar(new Producto( codigoProducto,  stock,  nombre,  descripcion,  precioUnitario));
			}
			br.close();
		}
		catch (Exception e) {
		}
	}
	//  Operaciones públicas complementarias
	public int codigoCorrelativo() {
		if (tamaño() == 0) {
			return 30001;
		}
		else
			return obtener(tamaño()-1).getCodigoProducto() + 1;
	}
	
	
	
}