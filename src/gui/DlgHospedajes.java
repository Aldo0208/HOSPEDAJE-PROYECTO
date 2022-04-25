package gui;

import libreria.*;

import clases.*;
import arreglos.*;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
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

public class DlgHospedajes extends JInternalFrame implements ActionListener, MouseListener {
	
	private JLabel lblCodigoHospedajes;
	private JComboBox <String> cboCodigoHospedajes;
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
					DlgHospedajes dialog = new DlgHospedajes();
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
	public DlgHospedajes() {
		setIconifiable(true);
		setClosable(true);
		setResizable(false);
		setTitle("Pago | Hospedajes");
		setBounds(100, 100, 700, 400);
		getContentPane().setLayout(null);
		
		lblCodigoHospedajes = new JLabel("Hospedajes");
		lblCodigoHospedajes.setBounds(10, 10, 110, 23);
		getContentPane().add(lblCodigoHospedajes);
		
		btnPagar = new JButton("Pagar");
		btnPagar.addActionListener(this);
		btnPagar.addMouseListener(this);
		btnPagar.setForeground(Color.RED);
		btnPagar.setBounds(535, 10, 150, 23);
		getContentPane().add(btnPagar);

		cboCodigoHospedajes = new JComboBox <String> ();
		cboCodigoHospedajes.addActionListener(this);
		cboCodigoHospedajes.addMouseListener(this);
		cboCodigoHospedajes.setBounds(100, 10, 100, 23);
		getContentPane().add(cboCodigoHospedajes);
	
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 50, 675, 310);
		getContentPane().add(scrollPane);
		
		txtS = new JTextArea();
		txtS.setFont(new Font("Monospaced", Font.PLAIN, 15));
		scrollPane.setViewportView(txtS);
		
		colocarCodigosInternamientos();
	}
	
	//  Declaración global
	ArregloHospedajes ah = new ArregloHospedajes();
	ArregloCliente ac = new ArregloCliente();
	String fechaSalida, horaSalida;
	double totalPagar;
	
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnPagar) {
			actionPerformedBtnPagar(arg0);
		}
		if (arg0.getSource() == cboCodigoHospedajes) {
			actionPerformedCboCodigoHospedajes(arg0);
		}
	}
	protected void actionPerformedCboCodigoHospedajes(ActionEvent arg0) {
		txtS.setText("");
		if (cboCodigoHospedajes.getSelectedIndex() >= 0)
			listar();
	}
	protected void actionPerformedBtnPagar(ActionEvent arg0) {
		if (cboCodigoHospedajes.getSelectedIndex() >= 0) { 
			int vale = confirmar("¿ Desea pagar Hospedaje ?");
			if (vale == 0) {
				Hospedaje h = ah.buscar(leerCodigoHospedaje());
				h.setEstado(1);
				h.setFechaSalida(fechaSalida);
				h.setHoraSalida(horaSalida);
				h.setTotalPagar(totalPagar);
				ah.grabarHospedaje();
				ArregloHabitacion ac = new ArregloHabitacion();
				Habitacion c = ac.buscar(h.getNumeroHabi());
				c.setEstado(0);
				ac.grabarhabi();
				cboCodigoHospedajes.removeItem(cboCodigoHospedajes.getSelectedItem());
			}
		}
		else
			mensaje("No existen hospedajes pendientes de pago");
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
	void colocarCodigosInternamientos() {
		Hospedaje h;
		for (int j=0; j<ah.tamaño(); j++) {
			h = ah.obtener(j);
			if (h.getEstado() == 0)
				cboCodigoHospedajes.addItem("" + h.getCodigoHospedaje());
		}	
	}
	void imprimir() {
		imprimir("");
	}
	void listar() {
		Hospedaje h = ah.buscar(leerCodigoHospedaje());
		Cliente c = ac.buscar(h.getCodigoCliente());
		imprimir("Cliente  :  " + c.getCodigoCliente());
		imprimir("Nombres   :  " + c.getNombres());
		imprimir("Apellidos :  " + c.getApellidos());
		imprimir();
		imprimir("Habitación   :  " + h.getNumeroHabi());
		ArregloHabitacion ac = new ArregloHabitacion();
		Habitacion a = ac.buscar(h.getNumeroHabi());
		imprimir("Categoría    :  " + rec.categoriasHabi[a.getCategoria()]);
		imprimir();
		imprimir("Ingreso   :  " + h.getFechaIngreso()
		                         + " - " + h.getHoraIngreso());
		fechaSalida = Fechas.fechaActual();
		horaSalida = Fechas.horaActual();
		imprimir("Salida    :  " + fechaSalida
                                 + " - " + horaSalida);
		int dias = Fechas.diasTranscurridos(h.getFechaIngreso(), fechaSalida);
		totalPagar = h.getTotalPagar() + dias * a.getPrecioDia();
		imprimir();
		imprimir("1) COSTO DE ALOJAMIENTO S/ " + formato(h.getTotalPagar()));
		imprimir();
		imprimir("2) PRECIO POR DÍA S/ " + formato(a.getPrecioDia()));
		imprimir();
		imprimir("3) DÍAS TRANSCURRIDOS " + dias);
		imprimir();
		imprimir("4) TOTAL A PAGAR S/ " + formato(totalPagar));
	}
	
	//  Métodos tipo void (con parámetros)
	void imprimir(String s) {
		txtS.append(s + "\n");
	}
	void mensaje(String s) {
		JOptionPane.showMessageDialog(this, s, "Información", 1);
	}
	
	
	int leerCodigoHospedaje() {
		return Integer.parseInt(cboCodigoHospedajes.getSelectedItem().toString());
	}
	
	
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