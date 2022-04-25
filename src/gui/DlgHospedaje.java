package gui;

import libreria.*;

import clases.*;
import arreglos.*;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class DlgHospedaje extends JInternalFrame implements ActionListener, KeyListener, MouseListener {
	
	private JLabel lblImgHospedaje;
	private JLabel lblCodigoHospedaje;
	private JLabel lblCodigoCliente;
	private JLabel lblNumeroHabitacion;
	private JTextField txtCodigoHospedaje;
	private JTextField txtCodigoCliente;
	private JTextField txtNumeroHabi;
	private JComboBox <String> cboCodigoCliente;
	private JComboBox <String> cboNumeroHabi;
	private JScrollPane scrollPane;
	private JButton btnHospedaje;
	private JButton btnProceder;
	private JTable tblHospedaje;
	private DefaultTableModel modelo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DlgHospedaje dialog = new DlgHospedaje();
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
	public DlgHospedaje() {
		setIconifiable(true);
		setClosable(true);
		setResizable(false);
		setTitle("Registro | Hospedaje");
		setBounds(100, 100, 915, 305);
		getContentPane().setLayout(null);

		lblImgHospedaje = new JLabel();
		lblImgHospedaje.setIcon(new ImageIcon("src/img/cama2.jpg"));
		lblImgHospedaje.setBounds(124, 139, 89, 88);
		getContentPane().add(lblImgHospedaje);
		
		lblCodigoHospedaje = new JLabel("C\u00F3digo");
		lblCodigoHospedaje.setBounds(10, 10, 50, 23);
		getContentPane().add(lblCodigoHospedaje);
		
		lblCodigoCliente = new JLabel("Cliente");
		lblCodigoCliente.addMouseListener(this);
		lblCodigoCliente.setForeground(Color.BLACK);
		lblCodigoCliente.setBounds(10, 40, 50, 23);
		getContentPane().add(lblCodigoCliente);
	
		lblNumeroHabitacion = new JLabel("Habitaci\u00F3n");
		lblNumeroHabitacion.addMouseListener(this);
		lblNumeroHabitacion.setForeground(Color.BLACK);
		lblNumeroHabitacion.setBounds(10, 74, 82, 23);
		getContentPane().add(lblNumeroHabitacion);
		
		txtCodigoHospedaje = new JTextField();
		txtCodigoHospedaje.setBounds(70, 10, 85, 23);
		getContentPane().add(txtCodigoHospedaje);
		txtCodigoHospedaje.setColumns(10);
		
		txtCodigoCliente = new JTextField();
		txtCodigoCliente.setBounds(70, 40, 85, 23);
		getContentPane().add(txtCodigoCliente);
		txtCodigoCliente.setColumns(10);
	
		txtNumeroHabi = new JTextField();
		txtNumeroHabi.setBounds(70, 74, 85, 23);
		getContentPane().add(txtNumeroHabi);
		txtNumeroHabi.setColumns(10);
		
		cboCodigoCliente = new JComboBox <String> ();
		cboCodigoCliente.addActionListener(this);
		cboCodigoCliente.addMouseListener(this);
		cboCodigoCliente.setBounds(175, 40, 100, 23);
		getContentPane().add(cboCodigoCliente);
		colocarCodigosClientes();

		cboNumeroHabi = new JComboBox <String> ();
		cboNumeroHabi.addActionListener(this);
		cboNumeroHabi.addMouseListener(this);
		cboNumeroHabi.setBounds(175, 74, 100, 23);
		getContentPane().add(cboNumeroHabi);
		colocarNumerosHabi();

		btnHospedaje = new JButton("Hospedar");
		btnHospedaje.addActionListener(this);
		btnHospedaje.addMouseListener(this);
		btnHospedaje.setForeground(Color.RED);
		btnHospedaje.setBounds(102, 238, 126, 23);
		getContentPane().add(btnHospedaje);
		
		btnProceder = new JButton("Proceder");
		btnProceder.addActionListener(this);
		btnProceder.addMouseListener(this);
		btnProceder.setForeground(Color.BLUE);
		btnProceder.setBounds(175, 10, 102, 23);
		getContentPane().add(btnProceder);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(285, 10, 599, 251);
		getContentPane().add(scrollPane);

		tblHospedaje = new JTable();
		tblHospedaje.addKeyListener(this);
		tblHospedaje.addMouseListener(this);
		tblHospedaje.setFillsViewportHeight(true);
		scrollPane.setViewportView(tblHospedaje);

		modelo = new DefaultTableModel();
		modelo.addColumn("CÓDIGO");
		modelo.addColumn("CLIENTE");
		modelo.addColumn("HABITACIÓN");
		modelo.addColumn("FECHA INGRESO");
		modelo.addColumn("HORA");
		modelo.addColumn("FECHA SALIDA");
		modelo.addColumn("HORA");
		modelo.addColumn("TOTAL");
		modelo.addColumn("ESTADO");
		
		tblHospedaje.setModel(modelo);
		
		txtCodigoHospedaje.setEditable(false);
		txtCodigoCliente.setEditable(false);
		txtNumeroHabi.setEditable(false);
		
		btnProceder.setEnabled(false);
		ajustarAnchoColumnas();
		listar();
		editarFila();
	}
	ArregloHospedajes ah = new ArregloHospedajes();
	
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == cboNumeroHabi) {
			actionPerformedCboNumeroHabi(arg0);
		}
		if (arg0.getSource() == cboCodigoCliente) {
			actionPerformedCboCodigoCliente(arg0);
		}
		if (arg0.getSource() == btnProceder) {
			actionPerformedBtnProceder(arg0);
		}
		if (arg0.getSource() == btnHospedaje) {
			actionPerformedBtnHospedar(arg0);
		}
	}
	protected void actionPerformedBtnHospedar(ActionEvent arg0) {
		if (cboCodigoCliente.getSelectedIndex() < 0)
			mensaje("Todos los clientes están en su habitación");
		else
			if (cboNumeroHabi.getSelectedIndex() < 0)
				mensaje("Todas las habitaciones están ocupadas");
			else {
				btnHospedaje.setEnabled(false);
				btnProceder.setEnabled(true);
				txtCodigoHospedaje.setText("" + ah.codigoCorrelativo());
				txtCodigoCliente.setText("" + cboCodigoCliente.getSelectedItem());
				txtNumeroHabi.setText("" + cboNumeroHabi.getSelectedItem());
				btnProceder.setEnabled(true);
			}
	}
	protected void actionPerformedBtnProceder(ActionEvent arg0) {
		int codigoHospedaje = leerCodigoHospedaje();
		int codigoCliente = leerCodigoCliente();
		int numeroHabi = leerNumeroHabi();
		int vale = confirmar(obtenerDatosClientes() + "\n\n" + obtenerDatosHabi(), "¿ Desea hospedar?");
		if (vale == 0) {
			Hospedaje nuevo = new Hospedaje(codigoHospedaje, codigoCliente, numeroHabi,
                                                    Fechas.fechaActual(), Fechas.horaActual(), "", "", 200.0, 0);
			ah.adicionar(nuevo);
			ah.grabarHospedaje();
			ArregloHabitacion ah = new ArregloHabitacion();
			Habitacion h = ah.buscar(numeroHabi);
			h.setEstado(1);
			ah.grabarhabi();
			cboCodigoCliente.removeItem(cboCodigoCliente.getSelectedItem());
			cboNumeroHabi.removeItem(cboNumeroHabi.getSelectedItem());
		}
		btnHospedaje.setEnabled(true);
		btnProceder.setEnabled(false);	
		listar();
		editarFila();	
	}
	protected void actionPerformedCboCodigoCliente(ActionEvent arg0) {
		txtCodigoCliente.setText("" + cboCodigoCliente.getSelectedItem());
	}
	protected void actionPerformedCboNumeroHabi(ActionEvent arg0) {
		txtNumeroHabi.setText("" + cboNumeroHabi.getSelectedItem());
	}
	public void keyPressed(KeyEvent arg0) {
	}
	public void keyReleased(KeyEvent arg0) {
		arg0.consume();
		editarFila();		
	}
	public void keyTyped(KeyEvent arg0) {
	}
	public void mouseClicked(MouseEvent arg0) {
		if (arg0.getSource() == lblNumeroHabitacion) {
			mouseClickedLblNumeroHabi(arg0);
		}
		if (arg0.getSource() == lblCodigoCliente) {
			mouseClickedLblCodigoCliente(arg0);
		}
		if (arg0.getSource() == tblHospedaje) {
			mouseClickedTblHospedaje(arg0);
		}
	}
	public void mouseEntered(MouseEvent arg0) {
		if (arg0.getSource() == cboNumeroHabi) {
			mouseEnteredCboNumeroHabi(arg0);
		}	
		if (arg0.getSource() == cboCodigoCliente) {
			mouseEnteredCboCodigoCliente(arg0);
		}	
		if (arg0.getSource() == btnProceder) {
			mouseEnteredBtnProceder(arg0);
		}
		if (arg0.getSource() == btnHospedaje) {
			mouseEnteredBtnHospedar(arg0);
		}
		if (arg0.getSource() == lblNumeroHabitacion) {
			mouseEnteredLblNumeroHabi(arg0);
		}
		if (arg0.getSource() == lblCodigoCliente) {
			mouseEnteredLblCodigoCliente(arg0);
		}	
		if (arg0.getSource() == tblHospedaje) {
			mouseEnteredTblHospedaje(arg0);
		}
	}
	public void mouseExited(MouseEvent arg0) {
	}
	public void mousePressed(MouseEvent arg0) {
	}
	public void mouseReleased(MouseEvent arg0) {
	}
	protected void mouseClickedTblHospedaje(MouseEvent arg0) {
		btnProceder.setEnabled(false);
		btnHospedaje.setEnabled(true);
		editarFila();
	}
	protected void mouseClickedLblCodigoCliente(MouseEvent arg0) {
		mensaje(obtenerDatosClientes());
	}
	protected void mouseClickedLblNumeroHabi(MouseEvent arg0) {
		mensaje(obtenerDatosHabi());
	}
	protected void mouseEnteredTblHospedaje(MouseEvent arg0) {
		tblHospedaje.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}
	protected void mouseEnteredLblCodigoCliente(MouseEvent arg0) {
		lblCodigoCliente.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}
	protected void mouseEnteredLblNumeroHabi(MouseEvent arg0) {
		lblNumeroHabitacion.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}
	protected void mouseEnteredBtnHospedar(MouseEvent arg0) {
		btnHospedaje.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}
	protected void mouseEnteredBtnProceder(MouseEvent arg0) {
		btnProceder.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}
	protected void mouseEnteredCboCodigoCliente(MouseEvent arg0) {
		cboCodigoCliente.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}	
	protected void mouseEnteredCboNumeroHabi(MouseEvent arg0) {
		cboNumeroHabi.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}
	
	
	void ajustarAnchoColumnas() {
		TableColumnModel tcm = tblHospedaje.getColumnModel();
		tcm.getColumn(0).setPreferredWidth(anchoColumna( 9));  // CodigoHospedaje
		tcm.getColumn(1).setPreferredWidth(anchoColumna(10));  // Cliente codigo
		tcm.getColumn(2).setPreferredWidth(anchoColumna(13));  // numero Habi
		tcm.getColumn(3).setPreferredWidth(anchoColumna(20));  // FechaIngreso
		tcm.getColumn(4).setPreferredWidth(anchoColumna( 8));  // HoraIngreso
		tcm.getColumn(5).setPreferredWidth(anchoColumna(20));  // FechaSalida
		tcm.getColumn(6).setPreferredWidth(anchoColumna( 8));  // HoraSalida
		tcm.getColumn(7).setPreferredWidth(anchoColumna( 9));  // TotalPagar
		tcm.getColumn(8).setPreferredWidth(anchoColumna( 9));  // Estado
	}
	void colocarCodigosClientes() {	
		ArregloCliente ap = new ArregloCliente();
		Cliente p;
		for (int i=0; i<ap.tamaño(); i++) {
			p = ap.obtener(i);
			if (ah.procedeCodigoCliente(p.getCodigoCliente()))
				cboCodigoCliente.addItem("" + p.getCodigoCliente());
		}
	}
	void colocarNumerosHabi() {
		ArregloHabitacion ah = new ArregloHabitacion();
		Habitacion c;
		for (int i=0; i<ah.tamaño(); i++) {
			c = ah.obtener(i);
			if (c.getEstado() == 0)
				cboNumeroHabi.addItem("" + c.getNumerohabi());
		}
	}
	void listar() {
		int posFila = 0;
		if (modelo.getRowCount() > 0)
			posFila = tblHospedaje.getSelectedRow();
		if (modelo.getRowCount() == ah.tamaño() - 1)
			posFila = ah.tamaño() - 1;
		if (posFila == ah.tamaño())
			posFila --;
		modelo.setRowCount(0);
		Hospedaje i;
		for (int j=0; j<ah.tamaño(); j++) {
			i = ah.obtener(j);
			Object[] fila = { i.getCodigoHospedaje(),
					          i.getCodigoCliente(),
					          i.getNumeroHabi(),
					          Fechas.enTextoFecha(i.getFechaIngreso()),
					          i.getHoraIngreso(),
					          Fechas.enTextoFecha(i.getFechaSalida()),
					          i.getHoraSalida(),
					          i.getTotalPagar(),
					          rec.estadosHospedaje[i.getEstado()] };
			modelo.addRow(fila);
		}
		if (ah.tamaño() > 0)
			tblHospedaje.getSelectionModel().setSelectionInterval(posFila, posFila);
	}
	void editarFila() {
		if (ah.tamaño() == 0)
			txtCodigoHospedaje.setText("" + ah.codigoCorrelativo());
		else {
			Hospedaje i = ah.obtener(tblHospedaje.getSelectedRow());
			txtCodigoHospedaje.setText("" + i.getCodigoHospedaje());
			txtCodigoCliente.setText("" + i.getCodigoCliente());
			txtNumeroHabi.setText("" + i.getNumeroHabi());
		}
	}
	
	
	void mensaje(String s) {
		JOptionPane.showMessageDialog(this, s, "Información", 1);
	}
	void mensaje(String s1, String s2) {
		JOptionPane.showMessageDialog(this, s1, s2, 1);
	}
	void error(String s, JTextField txt) {
		mensaje(s);
		txt.setText("");
		txt.requestFocus();
	}
	//  Métodos que retornan valor (sin parámetros)
	String obtenerDatosClientes() {
		ArregloCliente ap = new ArregloCliente();
	    Cliente p = ap.buscar(leerCodigoCliente());
	    return "Nombres :  " + p.getNombres() + "\n" +
		       "Apellidos :  " + p.getApellidos();
	}
	String obtenerDatosHabi() {
		ArregloHabitacion ac = new ArregloHabitacion();
	    Habitacion c = ac.buscar(leerNumeroHabi());
	    return "Categoría :  " + rec.categoriasHabi[c.getCategoria()] + "\n" +
		       "Precio por día :  " + c.getPrecioDia();
	}
	int leerCodigoHospedaje() {
		return Integer.parseInt(txtCodigoHospedaje.getText().trim());
	}
	int leerCodigoCliente() {
		return Integer.parseInt(txtCodigoCliente.getText().trim());
	}
	int leerNumeroHabi() {
		return Integer.parseInt(txtNumeroHabi.getText().trim());
	}
	//  Métodos que retornan valor (con parámetros)
	int anchoColumna(int porcentaje) {
		return porcentaje * scrollPane.getWidth() / 100;
	}
	int confirmar(String s) {
		return JOptionPane.showConfirmDialog(this, s, "Alerta", 0, 1, null);
	}
	int confirmar(String s1, String s2) {
		return JOptionPane.showConfirmDialog(this, s1, s2, 0, 1, null);
	}
	
}