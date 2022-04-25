package arreglos;

import clases.Habitacion;

import java.io.*;
import java.util.ArrayList;

public class ArregloHabitacion {
	
	//  Atributos privados
	private ArrayList <Habitacion> habi;
	//  Constructor
	public ArregloHabitacion() {
		habi = new ArrayList <Habitacion> ();
		cargarhabi();
	}
	//  Operaciones p�blicas b�sicas
	public void adicionar(Habitacion x) {
		habi.add(x);
	}
	public int tama�o() {
		return habi.size();
	}
	public Habitacion obtener(int i) {
		return habi.get(i);
	}
	public Habitacion buscar(int numeroHabi) {
		for (int i=0; i<tama�o(); i++)
			if (obtener(i).getNumerohabi() == numeroHabi)
				return obtener(i);
		return null;
	}
	public void eliminar(Habitacion x) {
		habi.remove(x);
	}
	public void grabarhabi() {
		try {
			PrintWriter pw;
			String linea;
			Habitacion x;
			pw = new PrintWriter(new FileWriter("habitaciones.txt"));
			for(int i=0; i<tama�o(); i++) {
				x = obtener(i);
				linea = x.getNumerohabi() + ";" +
						x.getCategoria() + ";" +
						x.getPrecioDia() + ";" +
						x.getEstado();
				pw.println(linea);
			}
			pw.close();
		}
		catch (Exception e) {
		}
	}
	private void cargarhabi() {
		try {
			BufferedReader br;
			String linea;
			String[] s;
			int numerohabi, categoria, estado;
			double precioDia;
			br = new BufferedReader(new FileReader("habitaciones.txt"));
			while ((linea=br.readLine()) != null) {
				s = linea.split(";");
				numerohabi = Integer.parseInt(s[0].trim());
				categoria = Integer.parseInt(s[1].trim());
				precioDia = Double.parseDouble(s[2].trim());
				estado = Integer.parseInt(s[3].trim());
				adicionar(new Habitacion(numerohabi, categoria, precioDia, estado));
			}
			br.close();	
		}
		catch (Exception e) {
		}
	}
	//  Operaciones p�blicas complementarias
	public int numeroCorrelativo() {
		if (tama�o() == 0)
			return 10001;
		else
			return obtener(tama�o()-1).getNumerohabi() + 1;
	}

}