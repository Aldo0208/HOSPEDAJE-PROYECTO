package arreglos;

import clases.Hospedaje;

import java.io.*;
import java.util.ArrayList;

public class ArregloHospedajes {
	//  Atributo privado
	private ArrayList <Hospedaje> hospe;
    //  Constructor
	public ArregloHospedajes() {
		hospe = new ArrayList <Hospedaje> ();
		cargarHospedajes();
	}
	//  Operaciones públicas básicas
	public void adicionar(Hospedaje x) {
		hospe.add(x);
	}
	public int tamaño() {
		return hospe.size();
	}
	public Hospedaje obtener(int i) {
		return hospe.get(i);
	}
	public Hospedaje buscar(int codigoHospedaje) {
		for (int i=0; i<tamaño(); i++)
			if (obtener(i).getCodigoHospedaje() == codigoHospedaje)
				return obtener(i);
		return null;
	}
	public void eliminar(Hospedaje x) {
		hospe.remove(x);
	}
	public void grabarHospedaje() {
		try {
			PrintWriter pw;
			String linea;
			Hospedaje x;
			pw = new PrintWriter(new FileWriter("Hospedajes.txt"));
			for (int i=0; i<tamaño(); i++) {
				x = obtener(i);
				linea =	x.getCodigoHospedaje() + ";" +
						x.getCodigoCliente() + ";" +
						x.getNumeroHabi() + ";" +
						x.getFechaIngreso() + ";" +
						x.getHoraIngreso() + ";" +
						x.getFechaSalida() + ";" +
						x.getHoraSalida() + ";" +
						x.getTotalPagar() + ";" +
						x.getEstado();
				pw.println(linea);
			}
			pw.close();
		}
		catch (Exception e) {
		}
	}
	public void cargarHospedajes() {
		try {
			BufferedReader br;
			String linea, fechaIngreso, horaIngreso, fechaSalida, horaSalida;
			String[] s;
			int codigoHospedaje, codigoCliente, numeroHabi, estado;
			double totalPagar;
			br = new BufferedReader(new FileReader("Hospedajes.txt"));
			while ((linea=br.readLine()) != null) {
				s = linea.split(";");
				codigoHospedaje = Integer.parseInt(s[0].trim());
				codigoCliente = Integer.parseInt(s[1].trim());
				numeroHabi = Integer.parseInt(s[2].trim());
				fechaIngreso = s[3].trim();
				horaIngreso = s[4].trim();
				fechaSalida = s[5].trim();
				horaSalida = s[6].trim();
				totalPagar = Double.parseDouble(s[7].trim());
				estado = Integer.parseInt(s[8].trim());
				adicionar(new Hospedaje(codigoHospedaje, codigoCliente, numeroHabi, fechaIngreso, 
						                    horaIngreso, fechaSalida, horaSalida, totalPagar, estado));
			}
			br.close();
		}
		catch (Exception e) {
		}
	}
	//  Operaciones públicas complementarias
	public int codigoCorrelativo() {
		if (tamaño() == 0)
			return 50001;
		else
			return obtener(tamaño()-1).getCodigoHospedaje() + 1;
	}
	public boolean procedeCodigoCliente(int codigoCliente) {
		for (int i=tamaño()-1; i>=0; i--)
			if (obtener(i).getCodigoCliente() == codigoCliente)
				if (obtener(i).getEstado() == 0)
					return false;
				else
					return true;
		return true;
	}
	
}