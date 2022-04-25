package gui;

import clases.*;
import libreria.Fechas;
import libreria.rec;
import arreglos.*;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.Font;

public class DlgAtenciones extends JInternalFrame implements ActionListener, MouseListener {
	
	private JLabel lblCodigoAtencion;
	private JComboBox <String> cboCodigoAtencion;
	private JButton btnPagar;
	private JScrollPane scrollPane;
	private JTextArea txtS;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DlgAtenciones dialog = new DlgAtenciones();
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
	public DlgAtenciones() {
		setIconifiable(true);
		setClosable(true);
		setResizable(false);
		setTitle("Pago | Atenciones");
		setBounds(100, 100, 700, 400);
		getContentPane().setLayout(null);
		
		lblCodigoAtencion = new JLabel("Atención");
		lblCodigoAtencion.setBounds(10, 10, 110, 23);
		getContentPane().add(lblCodigoAtencion);
		
		btnPagar = new JButton("Pagar");
		btnPagar.addActionListener(this);
		btnPagar.addMouseListener(this);
		btnPagar.setForeground(Color.RED);
		btnPagar.setBounds(535, 10, 150, 23);
		getContentPane().add(btnPagar);

		cboCodigoAtencion = new JComboBox <String> ();
		cboCodigoAtencion.addActionListener(this);
		cboCodigoAtencion.addMouseListener(this);
		cboCodigoAtencion.setBounds(70, 10, 100, 23);
		getContentPane().add(cboCodigoAtencion);
	
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 50, 675, 310);
		getContentPane().add(scrollPane);
		
		txtS = new JTextArea();
		txtS.setEditable(false);
		txtS.setFont(new Font("Arial", Font.PLAIN, 16));
		scrollPane.setViewportView(txtS);
		
		colocarCodigosConsultas();
	}
	
	//  Declaración global
	ArregloAtenciones aa = new ArregloAtenciones();
	ArregloCliente ap = new ArregloCliente();

	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnPagar) {
			actionPerformedBtnPagar(arg0);
		}
		if (arg0.getSource() == cboCodigoAtencion) {
			actionPerformedCboCodigoAtencion(arg0);
		}
	}
	protected void actionPerformedCboCodigoAtencion(ActionEvent arg0) {
		txtS.setText("");
		if (cboCodigoAtencion.getSelectedIndex() >= 0)
			listar();
	}
	protected void actionPerformedBtnPagar(ActionEvent arg0) {
		if (cboCodigoAtencion.getSelectedIndex() >= 0) { 
			int ok = confirmar("¿ Desea pagar Atención ?");
			if (ok == 0) {
				Atencion a = aa.buscar(leerCodigoAtencion());
				a.setEstado(1);
				aa.grabarAtenciones();
				cboCodigoAtencion.removeItem(cboCodigoAtencion.getSelectedItem());
			}
		}
		else
			mensaje("No existen atenciones pendientes de pago");
	}	
	public void mouseClicked(MouseEvent arg0) {
	}
	public void mouseEntered(MouseEvent arg0) {
		if (arg0.getSource() == btnPagar) {
			mouseEnteredBtnPagar(arg0);
		}
	}
	public void mouseExited(MouseEvent arg0) {
	}
	public void mousePressed(MouseEvent arg0) {
	}
	public void mouseReleased(MouseEvent arg0) {
	}
	protected void mouseEnteredBtnPagar(MouseEvent arg0) {
		btnPagar.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}
	//  Métodos tipo void (sin parámetros)
	void colocarCodigosConsultas() {
		Atencion a;
		for (int i=0; i<aa.tamaño(); i++) {
			a = aa.obtener(i);
			if (a.getEstado() == 0)
				cboCodigoAtencion.addItem("" + a.getCodigoAtencion());
		}	
	}
	void imprimir() {
		imprimir("");
	}
	void listar() {
		Atencion a = aa.buscar(leerCodigoAtencion());
		Cliente p = ap.buscar(a.getCodigoCliente());
		imprimir("Cliente  :  " + p.getCodigoCliente());
		imprimir("Nombres   :  " + p.getNombres());
		imprimir("Apellidos :  " + p.getApellidos());
		imprimir();
		imprimir("1) COSTO DE ATENCIÓN S/ 100.00");
		imprimir();
		ArregloBoleta ar = new ArregloBoleta("" + leerCodigoAtencion());
		Boleta r;
		imprimir("Cantidad:   Precio:    Importe:   Producto:");
		double importePago;
		for (int i=0; i<ar.tamaño(); i++) {
			r = ar.obtener(i);
			importePago = r.getCantidad() * r.getPrecioUnitario();
			ArregloProductos am = new ArregloProductos();
			Producto m = am.buscar(r.getCodigoProducto());
			imprimir("   " + formato(r.getCantidad()) + 
					         formato(r.getPrecioUnitario()) + formato(importePago)+ formato(m.getNombre()));
		
		
		}
		imprimir();
		imprimir("2) TOTAL A PAGAR S/ " + formato(a.getaPagar()));
	}
	
	
	//  Métodos tipo void (con parámetros)
	void imprimir(String s) {
		txtS.append(s + "\n");
	}
	void mensaje(String s) {
		JOptionPane.showMessageDialog(this, s, "Información", 1);
	}
	//  Métodos que retornan valor (sin parámetros)
	int leerCodigoAtencion() {
		return Integer.parseInt(cboCodigoAtencion.getSelectedItem().toString());
	}
	//  Métodos que retornan valor (con parámetros)
	int confirmar(String s) {
		return JOptionPane.showConfirmDialog(this, s, "Alerta", 0, 1, null);
	}
	String formato(String cadena) {
		return String.format("%-15s", cadena);
	}
	String formato(int entero) {
		return String.format("%-10d", entero);
	}
	String formato(double real) {
		return String.format(Locale.US, "%-10.2f", real);
	}
	
}