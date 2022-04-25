package gui;

import libreria.rec;

import clases.*;
import arreglos.*;

import java.awt.EventQueue;
import java.awt.Font;
import java.util.Locale;

import javax.swing.JDialog;
import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class DlgHospedajesPendientes extends JInternalFrame {
	
	private JScrollPane scrollPane;
	private JTextArea txtS;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DlgHospedajesPendientes dialog = new DlgHospedajesPendientes();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public DlgHospedajesPendientes() {
		setIconifiable(true);
		setClosable(true);
		setResizable(false);
		setTitle("Reporte | Hospedajes pendientes");
		setBounds(100, 100, 700, 400);
		getContentPane().setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 15, 675, 345);
		getContentPane().add(scrollPane);
	
		txtS = new JTextArea();
		txtS.setFont(new Font("Arial", Font.PLAIN, 15));
		scrollPane.setViewportView(txtS);
		
		listar();
	}
	
	//  M�todos tipo void (sin par�metros)
	void imprimir() {
		imprimir("");
	}
	void listar() {
		ArregloHospedajes ah = new ArregloHospedajes();
		ArregloCliente ac = new ArregloCliente();
		ArregloHabitacion aa = new ArregloHabitacion();
		Hospedaje i;
		Cliente p;
		Habitacion c;
		txtS.setText("");
		for (int j=0; j<ah.tama�o(); j++) {
			i = ah.obtener(j);
			if (i.getEstado() == 0) {
				p = ac.buscar(i.getCodigoCliente());
				c = aa.buscar(i.getNumeroHabi());
				imprimir("C�digo de hospedajes :  " + i.getCodigoHospedaje());
				imprimir("C�digo de cliente     :  " + p.getCodigoCliente());
				imprimir("Nombres                 :  " + p.getNombres());
				imprimir("Apellidos               :  " + p.getApellidos());
				imprimir("N�mero de habitaci�n:  " + i.getNumeroHabi());
				imprimir("Categor�a               :  " + rec.categoriasHabi[c.getCategoria()]);
				imprimir("Precio por d�a          :  S/ " + formato(c.getPrecioDia()));
				imprimir("Fecha de ingreso        :  " + i.getFechaIngreso()
				                                       + " - " + i.getHoraIngreso());
				imprimir();
			}
		}
	}
	//  M�todos tipo void (con par�metros)
	void imprimir(String s) {
		txtS.append(s + "\n");
	}
	//  M�todos que retornan valor (con par�metros)
	String formato(double real) {
		return String.format(Locale.US, "%-10.2f", real);
	}
	
}