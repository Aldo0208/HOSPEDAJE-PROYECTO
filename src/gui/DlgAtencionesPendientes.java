package gui;

import clases.*;
import arreglos.*;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JDialog;
import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class DlgAtencionesPendientes extends JInternalFrame {
	
	private JScrollPane scrollPane;
	private JTextArea txtS;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DlgAtencionesPendientes dialog = new DlgAtencionesPendientes();
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
	public DlgAtencionesPendientes() {
		setClosable(true);
		setIconifiable(true);
		setResizable(false);
		setTitle("Reporte | Atenciones pendientes");
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
	
	
	
	void imprimir() {
		imprimir("");
	}
	void listar() {
		ArregloAtenciones aa = new ArregloAtenciones();
		ArregloCliente ap = new ArregloCliente();
		Atencion a;
		Cliente c;
		txtS.setText("");
		for (int i=0; i<aa.tamaño(); i++) {
			a = aa.obtener(i);
			if (a.getEstado() == 0) {
				c = ap.buscar(a.getCodigoCliente());
				imprimir("Código de atención :  " + a.getCodigoAtencion());
				imprimir("Código de cliente :  " + a.getCodigoCliente());
				imprimir("Nombres            :  " + c.getNombres());
				imprimir("Apellidos          :  " + c.getApellidos());
				imprimir("Fecha de atención  :  " + a.getFechaAtencion()
				                                  + " - " + a.getHoraAtencion());
				imprimir();
			}
		}
	}
	//  Métodos tipo void (con parámetros)
	void imprimir(String s) {
		txtS.append(s + "\n");
	}
	
}