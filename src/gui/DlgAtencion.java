package gui;

import libreria.*;

import clases.*;
import arreglos.*;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;

import java.util.Calendar;
import java.util.GregorianCalendar;

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

public class DlgAtencion extends JInternalFrame implements ActionListener, KeyListener, MouseListener {
	
	private JLabel lblImgAtencion;
	private JLabel lblCodigoAtencion;
	private JLabel lblCodigoCliente;
	private JLabel lblCodigoProducto;
	private JTextField txtCodigoAtencion;
	private JTextField txtCodigoCliente;
	private JComboBox <String> cboCodigoCliente;
	private JComboBox <String> cboCodProducto;
	private JComboBox <String> cboCodigoProducto;
	private JScrollPane scrollPane;
	private JButton btnQuitar;
	private JButton btnAgregar;
	private JButton btnAtender;
	private JButton btnProceder;
	private JTable tblConsulta;
	private DefaultTableModel modelo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DlgAtencion dialog = new DlgAtencion();
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
	public DlgAtencion() {
		setIconifiable(true);
		setClosable(true);
		setResizable(false);
		setTitle("Registro | Atención");
		setBounds(100, 100, 759, 317);
		getContentPane().setLayout(null);
	
		lblImgAtencion = new JLabel();
		lblImgAtencion.setIcon(new ImageIcon("src/img/MOZO.jpg"));
		lblImgAtencion.setBounds(122, 138, 89, 89);
		getContentPane().add(lblImgAtencion);
		
		lblCodigoAtencion = new JLabel("C\u00F3digo");
		lblCodigoAtencion.setBounds(10, 10, 60, 23);
		getContentPane().add(lblCodigoAtencion);
		
		lblCodigoCliente = new JLabel("Cliente");
		lblCodigoCliente.addMouseListener(this);
		lblCodigoCliente.setForeground(Color.BLACK);
		lblCodigoCliente.setBounds(10, 40, 60, 23);
		getContentPane().add(lblCodigoCliente);
	
		lblCodigoProducto = new JLabel("Producto");
		lblCodigoProducto.addMouseListener(this);
		lblCodigoProducto.setForeground(Color.BLACK);
		lblCodigoProducto.setBounds(10, 70, 60, 23);
		getContentPane().add(lblCodigoProducto);
		
		txtCodigoAtencion = new JTextField();
		txtCodigoAtencion.setBounds(70, 10, 85, 23);
		getContentPane().add(txtCodigoAtencion);
		txtCodigoAtencion.setColumns(10);
		
		txtCodigoCliente = new JTextField();
		txtCodigoCliente.setBounds(70, 40, 85, 23);
		getContentPane().add(txtCodigoCliente);
		txtCodigoCliente.setColumns(10);
		
		cboCodigoCliente = new JComboBox <String> ();
		cboCodigoCliente.addActionListener(this);
		cboCodigoCliente.addMouseListener(this);
		cboCodigoCliente.setBounds(190, 40, 92, 23);
		getContentPane().add(cboCodigoCliente);
		colocarCodigosCliente();

		cboCodProducto = new JComboBox <String> ();
		cboCodProducto.addMouseListener(this);
		cboCodProducto.setBounds(70, 70, 85, 23);
		getContentPane().add(cboCodProducto);
		
		cboCodigoProducto = new JComboBox <String> ();
		cboCodigoProducto.addMouseListener(this);
		cboCodigoProducto.setBounds(190, 70, 92, 23);
		getContentPane().add(cboCodigoProducto);
		
		btnQuitar = new JButton(new ImageIcon("imagenes/quitar.gif"));
		btnQuitar.setForeground(Color.BLUE);
		btnQuitar.setText("QUITAR\r\n");
		btnQuitar.setToolTipText("");
		btnQuitar.addActionListener(this);
		btnQuitar.addMouseListener(this);
		btnQuitar.setBounds(70, 104, 85, 23);
		getContentPane().add(btnQuitar);
	
		btnAgregar = new JButton(new ImageIcon("imagenes/agregar.gif"));
		btnAgregar.setForeground(Color.GREEN);
		btnAgregar.setText("AGREGAR\r\n");
		btnAgregar.addActionListener(this);
		btnAgregar.addMouseListener(this);
		btnAgregar.setBounds(188, 104, 94, 23);
		getContentPane().add(btnAgregar);
		
		btnAtender = new JButton("Atender");
		btnAtender.addActionListener(this);
		btnAtender.addMouseListener(this);
		btnAtender.setForeground(Color.RED);
		btnAtender.setBounds(91, 240, 150, 23);
		getContentPane().add(btnAtender);
		
		btnProceder = new JButton("Proceder");
		btnProceder.addActionListener(this);
		btnProceder.addMouseListener(this);
		btnProceder.setForeground(Color.ORANGE);
		btnProceder.setBounds(189, 10, 93, 23);
		getContentPane().add(btnProceder);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(294, 13, 452, 250);
		getContentPane().add(scrollPane);

		tblConsulta = new JTable();
		tblConsulta.addKeyListener(this);
		tblConsulta.addMouseListener(this);
		tblConsulta.setFillsViewportHeight(true);
		scrollPane.setViewportView(tblConsulta);

		modelo = new DefaultTableModel();
		modelo.addColumn("CÓDIGO");
		modelo.addColumn("CLIENTE");
		modelo.addColumn("FECHA CONSUMO");
		modelo.addColumn("HORA CONSUMO");
		modelo.addColumn("A PAGAR");
		modelo.addColumn("ESTADO");
		
		tblConsulta.setModel(modelo);
		
		txtCodigoAtencion.setEditable(false);
		txtCodigoCliente.setEditable(false);
		btnProceder.setEnabled(false);
		if (aa.tamaño() == 0)
			visibleInvisible(false);
		
		ajustarAnchoColumnas();
		listar();
		editarFila();
	}
	
	//  Declaración global
	ArregloAtenciones aa = new ArregloAtenciones();
	
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == cboCodigoCliente) {
			actionPerformedCboCodigoCliente(arg0);
		}
		if (arg0.getSource() == btnProceder) {
			actionPerformedBtnProceder(arg0);
		}
		if (arg0.getSource() == btnAtender) {
			actionPerformedBtnAtender(arg0);
		}
		if (arg0.getSource() == btnQuitar) {
			actionPerformedBtnQuitar(arg0);
		}
		if (arg0.getSource() == btnAgregar) {
			actionPerformedBtnAgregar(arg0);
		}
	}	
	protected void actionPerformedBtnAgregar(ActionEvent arg0) {
		Atencion a = aa.buscar(leerCodigoAtencion());
		if (a.getEstado() == 0)
			try {
				int codigoProducto = leerCodigoProducto();
				int ok = confirmar(obtenerDatosProducto(codigoProducto), "Pedidos");
				if (ok == 0)
					try {
						int cantidad = Integer.parseInt(confirmarIngreso("Cantidad"));
						ArregloProductos am = new ArregloProductos();
						Producto m = am.buscar(codigoProducto);
						if (cantidad <= m.getStock()) {
							ArregloBoleta ar = new ArregloBoleta(leerNumeroBoleta());
							ar.adicionar(new Boleta(codigoProducto, cantidad, m.getPrecioUnitario()));
							ar.grabarBoleta();
							m.setStock(m.getStock() - cantidad);
							am.grabarProductos();
							a.setaPagar(redondear(a.getaPagar() + cantidad*m.getPrecioUnitario()));
							aa.grabarAtenciones();
							cboCodProducto.addItem(cboCodigoProducto.getSelectedItem().toString());
							cboCodigoProducto.removeItem(cboCodigoProducto.getSelectedItem());
							listar();
						}
						else
							mensaje("Sólo hay " + m.getStock() + " unidades en stock");
					}
					catch (Exception e) {
						mensaje("Ingrese CANTIDAD correcta");
					}
			}
			catch (Exception e) {
				mensaje("La cuenta del cliente tiene todos los productos");
			}
		else
			mensaje("No se puede agregar productos porque la Atención está Pagada");
	}
	protected void actionPerformedBtnQuitar(ActionEvent arg0) {
		Atencion a = aa.buscar(leerCodigoAtencion());
		if (a.getEstado() == 0)
			try {
				int codProducto = leerCodProducto();
				int vale = confirmar("¿ Quitar pedido de la cuenta ?");
				if (vale == 0) {
					ArregloBoleta ar = new ArregloBoleta(leerNumeroBoleta());
					Boleta r = ar.buscar(codProducto);
					ar.eliminar(r);
					ar.grabarBoleta();
					ArregloProductos am = new ArregloProductos();
					Producto m = am.buscar(codProducto);
					m.setStock(m.getStock() + r.getCantidad());
					am.grabarProductos();
					a.setaPagar(redondear(a.getaPagar() - r.getCantidad()*r.getPrecioUnitario()));
					aa.grabarAtenciones();
					cboCodigoProducto.addItem(cboCodProducto.getSelectedItem().toString());
					cboCodProducto.removeItem(cboCodProducto.getSelectedItem());
					listar();
				}
			}
			catch (Exception e) {
				mensaje("El cliente no cuenta con pedidos");
			}
		else
			mensaje("No se puede quitar el pedido porque la Atención está Pagada");
	}
	protected void actionPerformedBtnAtender(ActionEvent arg0) {
		if (cboCodigoCliente.getSelectedIndex() < 0)
			mensaje("Todos los clientes están en Atención");
		else {
			visibleInvisible(false);
			btnAtender.setEnabled(false);
			btnProceder.setEnabled(true);
			txtCodigoAtencion.setText("" + aa.codigoCorrelativo());
			txtCodigoCliente.setText("" + cboCodigoCliente.getSelectedItem());
		}
	}
	
	protected void actionPerformedBtnProceder(ActionEvent arg0) {
		int codigoAtencion = leerCodigoAtencion();
		int codigoCliente = leerCodigoCliente();
		int ok = confirmar(obtenerDatosCliente(), "¿ Registrar Pedido ?");
		if (ok == 0) {
			Atencion nueva = new Atencion(codigoAtencion, codigoCliente, fechaActual(), horaActual(), 100.0, 0);
			aa.adicionar(nueva);
			aa.grabarAtenciones();
			cboCodigoCliente.removeItem(cboCodigoCliente.getSelectedItem());
		}
		visibleInvisible(true);
		btnAtender.setEnabled(true);
		btnProceder.setEnabled(false);
		listar();
		editarFila();				
	}
	protected void actionPerformedCboCodigoCliente(ActionEvent arg0) {
		txtCodigoCliente.setText("" + cboCodigoCliente.getSelectedItem());
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
		if (arg0.getSource() == lblCodigoProducto) {
			mouseClickedLblCodigoProducto(arg0);
		}
		if (arg0.getSource() == lblCodigoCliente) {
			mouseClickedLblCodigoCliente(arg0);
		}
		if (arg0.getSource() == tblConsulta) {
			mouseClickedTblConsulta(arg0);
		}
	}
	public void mouseEntered(MouseEvent arg0) {
		if (arg0.getSource() == cboCodProducto) {
			mouseEnteredCodProducto(arg0);
		}
		if (arg0.getSource() == cboCodigoProducto) {
			mouseEnteredCodigoProducto(arg0);
		}	
		if (arg0.getSource() == cboCodigoCliente) {
			mouseEnteredCboCodigoCliente(arg0);
		}	
		if (arg0.getSource() == btnProceder) {
			mouseEnteredBtnProceder(arg0);
		}
		if (arg0.getSource() == btnAtender) {
			mouseEnteredBtnAtender(arg0);
		}
		if (arg0.getSource() == btnAgregar) {
			mouseEnteredBtnAgregar(arg0);
		}
		if (arg0.getSource() == btnQuitar) {
			mouseEnteredBtnQuitar(arg0);
		}
		if (arg0.getSource() == lblCodigoProducto) {
			mouseEnteredLblCodigoProducto(arg0);
		}
		if (arg0.getSource() == lblCodigoCliente) {
			mouseEnteredLblCodigoCliente(arg0);
		}	
		if (arg0.getSource() == tblConsulta) {
			mouseEnteredTblConsulta(arg0);
		}
	}
	public void mouseExited(MouseEvent arg0) {
	}
	public void mousePressed(MouseEvent arg0) {
	}
	public void mouseReleased(MouseEvent arg0) {
	}
	protected void mouseClickedTblConsulta(MouseEvent arg0) {
		if (btnProceder.isEnabled())
			visibleInvisible(true);
		btnProceder.setEnabled(false);
		btnAtender.setEnabled(true);
		editarFila();
	}
	protected void mouseClickedLblCodigoCliente(MouseEvent arg0) {
		mensaje(obtenerDatosCliente());
	}
	protected void mouseClickedLblCodigoProducto(MouseEvent arg0) {
		if (cboCodProducto.getSelectedIndex() < 0)
			mensaje("El cliente no cuenta con Pedidos");
		else
			mensaje(obtenerDatosBoleta(leerCodProducto()));
	}
	protected void mouseEnteredTblConsulta(MouseEvent arg0) {
		tblConsulta.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}
	protected void mouseEnteredLblCodigoCliente(MouseEvent arg0) {
		lblCodigoCliente.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}
	protected void mouseEnteredLblCodigoProducto(MouseEvent arg0) {
		lblCodigoProducto.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}
	protected void mouseEnteredBtnQuitar(MouseEvent arg0) {
		btnQuitar.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}
	protected void mouseEnteredBtnAgregar(MouseEvent arg0) {
		btnAgregar.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}
	protected void mouseEnteredBtnAtender(MouseEvent arg0) {
		btnAtender.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}
	protected void mouseEnteredBtnProceder(MouseEvent arg0) {
		btnProceder.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}
	protected void mouseEnteredCboCodigoCliente(MouseEvent arg0) {
		cboCodigoCliente.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}	
	protected void mouseEnteredCodigoProducto(MouseEvent arg0) {
		cboCodigoProducto.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}
	protected void mouseEnteredCodProducto(MouseEvent arg0) {
		cboCodProducto.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}
	
	
	void ajustarAnchoColumnas() {
		TableColumnModel tcm = tblConsulta.getColumnModel();
		tcm.getColumn(0).setPreferredWidth(anchoColumna(16));  // codigoAtencion
		tcm.getColumn(1).setPreferredWidth(anchoColumna(16));  // codigoCliente
		tcm.getColumn(2).setPreferredWidth(anchoColumna(20));  // fechaAtencion
		tcm.getColumn(3).setPreferredWidth(anchoColumna(16));  // horaAtencion
		tcm.getColumn(4).setPreferredWidth(anchoColumna(16));  // totalPagar
		tcm.getColumn(5).setPreferredWidth(anchoColumna(16));  // estado 
	}
	void colocarCodigosCliente() {
		ArregloCliente ac = new ArregloCliente();
		Cliente c;
		for (int i=0; i<ac.tamaño(); i++) {
			c = ac.obtener(i);
			if (aa.procedeCodigoClientes(c.getCodigoCliente()))
				cboCodigoCliente.addItem("" + c.getCodigoCliente());
		}
	}
	void distribuirCodigosMedicinas() {
		ArregloBoleta ar = new ArregloBoleta(leerNumeroBoleta());
		Boleta r;
		cboCodProducto.removeAllItems();
		for (int i=0; i<ar.tamaño(); i++) {
			r = ar.obtener(i);
			cboCodProducto.addItem("" + r.getCodigoProducto());
		}
		ArregloProductos am = new ArregloProductos();
		Producto m;
		cboCodigoProducto.removeAllItems();
		for (int i=0; i<am.tamaño(); i++) {
			m = am.obtener(i);
			if (ar.buscar(m.getCodigoProducto()) == null)
				cboCodigoProducto.addItem("" + m.getCodigoProducto());
		}	
	}
	void listar() {
		int posFila = 0;
		if (modelo.getRowCount() > 0)
			posFila = tblConsulta.getSelectedRow();
		if (modelo.getRowCount() == aa.tamaño() - 1)
			posFila = aa.tamaño() - 1;
		if (posFila == aa.tamaño())
			posFila --;
		modelo.setRowCount(0);
		Atencion a;
		for (int i=0; i<aa.tamaño(); i++) {
			a = aa.obtener(i);
			Object[] fila = { a.getCodigoAtencion(),
							  a.getCodigoCliente(),
							  Fechas.enTextoFecha(a.getFechaAtencion()),
							  a.getHoraAtencion(),
							  a.getaPagar(),
							  rec.estadosConsulta[a.getEstado()] };
			modelo.addRow(fila);
		}
		if (aa.tamaño() > 0)
			tblConsulta.getSelectionModel().setSelectionInterval(posFila, posFila);
	}
	void editarFila() {
		if (aa.tamaño() == 0)
			txtCodigoAtencion.setText("" + aa.codigoCorrelativo());
		else {
			Atencion a = aa.obtener(tblConsulta.getSelectedRow());
			txtCodigoAtencion.setText("" + a.getCodigoAtencion());
			txtCodigoCliente.setText("" + a.getCodigoCliente());
			distribuirCodigosMedicinas();
		}
	}
	//  Métodos tipo void (con parámetros)
	void mensaje(String s) {
		JOptionPane.showMessageDialog(this, s, "Información", 1);
	}
	void mensaje(String s1, String s2) {
		JOptionPane.showMessageDialog(this, s1, s2, 1);
	}
	void visibleInvisible(boolean sino) {
		lblCodigoProducto.setVisible(sino);
		cboCodProducto.setVisible(sino);
		btnQuitar.setVisible(sino);
		cboCodigoProducto.setVisible(sino);
		btnAgregar.setVisible(sino);
	}
	void error(String s, JTextField txt) {
		mensaje(s);
		txt.setText("");
		txt.requestFocus();
	}
	//  Métodos que retornan valor (sin parámetros)
	String obtenerDatosCliente() {
		ArregloCliente ap = new ArregloCliente();
	    Cliente p = ap.buscar(leerCodigoCliente());
	    return "Nombres :  " + p.getNombres() + "\n" +
		       "Apellidos :  " + p.getApellidos();
	}
	int leerCodigoAtencion() {
		
		if(txtCodigoAtencion.getText().length()==0) {
			aviso("El codigo, no debe ser vacio");
		}
		return Integer.parseInt(txtCodigoAtencion.getText().trim());
	}
	private void aviso(String msg) {
		JOptionPane.showMessageDialog(this, msg,"Avisos",JOptionPane.ERROR_MESSAGE);
		
	}

	int leerCodigoCliente() {
		return Integer.parseInt(txtCodigoCliente.getText().trim());
	}
	int leerCodProducto() {
		return Integer.parseInt(cboCodProducto.getSelectedItem().toString());
	}
	int leerCodigoProducto() {
		return Integer.parseInt(cboCodigoProducto.getSelectedItem().toString());
	}
	String leerNumeroBoleta() {
		return "" + leerCodigoAtencion();
	}
	String fechaActual() {
		int dd, mm, aa;
		Calendar c = new GregorianCalendar();
		dd = c.get(Calendar.DAY_OF_MONTH);
		mm = c.get(Calendar.MONTH) + 1;
		aa = c.get(Calendar.YEAR);
		return ajustar(dd) + "/" + ajustar(mm) + "/" + aa;
	}
	String horaActual() {
		int hh, mm, ss;
		Calendar c = new GregorianCalendar();
		hh = c.get(Calendar.HOUR_OF_DAY);
		mm = c.get(Calendar.MINUTE);
		ss = c.get(Calendar.SECOND);
		return ajustar(hh) + ":" + ajustar(mm) + ":" + ajustar(ss);
	}
	
	
	int anchoColumna(int porcentaje) {
		return porcentaje * scrollPane.getWidth() / 100;
	}
	int confirmar(String s) {
		return JOptionPane.showConfirmDialog(this, s, "Alerta", 0, 1, null);
	}
	int confirmar(String s1, String s2) {
		return JOptionPane.showConfirmDialog(this, s1, s2, 0, 1, null);
	}
	String confirmarIngreso(String s) {
		return JOptionPane.showInputDialog(this, "", s, 3);
	}
	String ajustar(int numero) {
		return String.format("%02d", numero);
	}
	String obtenerDatosProducto(int codigoProducto) {
		ArregloProductos am = new ArregloProductos();
	    Producto m = am.buscar(codigoProducto);
	    return "Nombre :  " + m.getNombre() + "\n" +
		       "Precio unitario :  S/ " + m.getPrecioUnitario();
	}
	String obtenerDatosBoleta(int codigoProducto) {
		ArregloProductos am = new ArregloProductos();
	    Producto m = am.buscar(codigoProducto);
	    ArregloBoleta ar = new ArregloBoleta(leerNumeroBoleta());
	    Boleta r = ar.buscar(codigoProducto);
	    return "Nombre :  " + m.getNombre() + "\n" +
	    	   "Cantidad :  " + r.getCantidad() + "\n" +
	           "Precio unitario :  S/ " + r.getPrecioUnitario();
	}
	double redondear(double real) {
		return Math.round(real * 100) / 100.0;
	}
	
}