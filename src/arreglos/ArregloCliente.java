package arreglos;

import clases.Cliente;
import mantenimientos.GestionCliente;

import java.io.*;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class ArregloCliente {
	
	//  Atributo privado
	private ArrayList <Cliente> cli;
	//  Constructor
	public ArregloCliente() {
		cli = new ArrayList <Cliente> ();
		cargarClientes();
	}
	//  Operaciones públicas básicas
	public void adicionar(Cliente x) {
		cli.add(x);
	}
	public int tamaño() {
		return cli.size();
	}
	public Cliente obtener(int i) {
		return cli.get(i);
	}
	public Cliente buscar(int codigoCliente) {
		for (int i=0; i<tamaño(); i++)
			if (obtener(i).getCodigoCliente() == codigoCliente)
				return obtener(i);
		return null;
	}
	public void eliminar(Cliente x) {
		cli.remove(x);
	}

	
	
	public void grabarClientes() {
		try {
			PrintWriter pw;
			String linea;
			Cliente x;
			pw = new PrintWriter(new FileWriter("Clientes.txt"));
			for (int i=0; i<tamaño(); i++) {
				x = obtener(i);
				linea = x.getCodigoCliente() + ";" +
						x.getNombres() + ";" +
						x.getApellidos() + ";" +
						x.getTelefono() + ";" +
						x.getDni();
				pw.println(linea);
			}
			pw.close();	
		}
		catch (Exception e) {
		}
	}
	private void cargarClientes() {
		try {
			BufferedReader br;
			String linea;
			String[] s;
			int codigoCliente;
			String nombres, apellidos, telefono, dni;
			br = new BufferedReader(new FileReader("Clientes.txt"));
			while ((linea = br.readLine()) != null) {
				s = linea.split(";");
				codigoCliente = Integer.parseInt(s[0].trim());
				nombres = s[1].trim();
				apellidos = s[2].trim();
				telefono = s[3].trim();
				dni = s[4].trim();
				adicionar(new Cliente(codigoCliente, nombres, apellidos, telefono, dni));
			}
			br.close();
		}
		catch (Exception e) {
		}
	}
	//  Operaciones públicas complementarias
	public int codigoCorrelativo() {
		if (tamaño() == 0)
			return 20001;
		else
			return obtener(tamaño()-1).getCodigoCliente() + 1;
	}

	
}