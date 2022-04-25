package gui;

import clases.Cliente;
import clases.Producto;
import mantenimientos.GestionCliente;
import arreglos.ArregloProductos;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JButton;
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
import java.awt.event.KeyAdapter;

public class DlgProducto extends JInternalFrame implements ActionListener, KeyListener, MouseListener {

	private JLabel lblImgProducto;
	private JLabel lblCodigoProducto;
	private JLabel lblNombre;
	private JLabel lblDescripcion;
	private JLabel lblStock;
	private JLabel lblPrecio;
	private JTextField txtCodigoProducto;
	private JTextField txtNombre;
	private JTextField txtDescripcion;
	private JTextField txtStock;
	private JTextField txtPrecio;
	private JScrollPane scrollPane;
	private JButton btnAceptar;
	private JButton btnAdicionar;
	private JButton btnModificar;
	private JButton btnEliminar;
	private JTable tblProducto;
	private DefaultTableModel modelo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DlgProducto dialog = new DlgProducto();
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
	public DlgProducto() {
		setIconifiable(true);
		setClosable(true);
		setResizable(false);
		setTitle("Mantenimiento | Producto");
		setBounds(100, 100, 702, 440);
		getContentPane().setLayout(null);

		lblImgProducto = new JLabel();
		lblImgProducto.setIcon(new ImageIcon("src/img/productogif.gif"));
		lblImgProducto.setBounds(561, 10, 88, 88);
		getContentPane().add(lblImgProducto);
		
		lblCodigoProducto = new JLabel("C\u00F3digo");
		lblCodigoProducto.setBounds(10, 10, 110, 23);
		getContentPane().add(lblCodigoProducto);
		
		lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(10, 40, 70, 23);
		getContentPane().add(lblNombre);
	
		lblDescripcion = new JLabel("Descripcion");
		lblDescripcion.setBounds(10, 70, 70, 23);
		getContentPane().add(lblDescripcion);
		
		lblStock = new JLabel("Stock");
		lblStock.setBounds(10, 136, 70, 23);
		getContentPane().add(lblStock);
		
		lblPrecio = new JLabel("Precio");
		lblPrecio.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				txtPrecio.setBackground(Color.white);
			}
		});
		lblPrecio.setBounds(185, 136, 70, 23);
		getContentPane().add(lblPrecio);
		
		txtCodigoProducto = new JTextField();
		txtCodigoProducto.setBounds(90, 10, 85, 23);
		getContentPane().add(txtCodigoProducto);
		txtCodigoProducto.setColumns(10);

		txtNombre = new JTextField();
		txtNombre.setBounds(90, 40, 232, 23);
		getContentPane().add(txtNombre);
		txtNombre.setColumns(10);

		txtDescripcion = new JTextField();
		txtDescripcion.setBounds(90, 70, 232, 55);
		getContentPane().add(txtDescripcion);
		txtDescripcion.setColumns(10);

		txtStock = new JTextField();
		txtStock.setBounds(90, 136, 85, 23);
		getContentPane().add(txtStock);
		txtStock.setColumns(10);

		txtPrecio = new JTextField();
		txtPrecio.setBounds(222, 136, 100, 23);
		getContentPane().add(txtPrecio);
		txtPrecio.setColumns(10);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(this);
		btnAceptar.addMouseListener(this);
		btnAceptar.setForeground(Color.RED);
		btnAceptar.setBounds(222, 10, 100, 23);
		getContentPane().add(btnAceptar);
		
		btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addActionListener(this);
		btnAdicionar.addMouseListener(this);
		btnAdicionar.setForeground(Color.ORANGE);
		btnAdicionar.setBounds(50, 377, 150, 23);
		getContentPane().add(btnAdicionar);

		btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(this);
		btnModificar.addMouseListener(this);
		btnModificar.setForeground(Color.ORANGE);
		btnModificar.setBounds(282, 377, 150, 23);
		getContentPane().add(btnModificar);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(this);
		btnEliminar.addMouseListener(this);
		btnEliminar.setForeground(Color.ORANGE);
		btnEliminar.setBounds(499, 377, 150, 23);
		getContentPane().add(btnEliminar);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 170, 675, 195);
		getContentPane().add(scrollPane);

		tblProducto = new JTable();
		tblProducto.addKeyListener(this);
		tblProducto.addMouseListener(this);
		tblProducto.setFillsViewportHeight(true);
		scrollPane.setViewportView(tblProducto);

		modelo = new DefaultTableModel();
		modelo.addColumn("C”DIGO");
		modelo.addColumn("NOMBRE");
		modelo.addColumn("DESCRIPCION");
		modelo.addColumn("STOCK");
		modelo.addColumn("PRECIO UNITARIO");
	
		tblProducto.setModel(modelo);
		
		txtCodigoProducto.setEditable(false);
		
		habilitarEntradas(false);
		ajustarAnchoColumnas();
		listar();
		editarFila();
	}
	
	//  DeclaraciÛn global
	ArregloProductos ap = new ArregloProductos();
	
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnAceptar) {
			actionPerformedBtnAceptar(arg0);
		}
		if (arg0.getSource() == btnEliminar) {
			actionPerformedBtnEliminar(arg0);
		}
		if (arg0.getSource() == btnModificar) {
			actionPerformedBtnModificar(arg0);
		}
		if (arg0.getSource() == btnAdicionar) {
			actionPerformedBtnAdicionar(arg0);
		}
	}
	protected void actionPerformedBtnAdicionar(ActionEvent arg0) {
		btnAdicionar.setEnabled(false);
		btnModificar.setEnabled(true);
		btnAceptar.setEnabled(true);
		limpieza();
		habilitarEntradas(true);
		txtNombre.requestFocus();
	}
	protected void actionPerformedBtnModificar(ActionEvent arg0) {
		btnAdicionar.setEnabled(true);
		btnModificar.setEnabled(false);
		if (ap.tamaÒo() == 0) {
			btnAceptar.setEnabled(false);
			habilitarEntradas(false);
			mensaje("No existen productos");	
		}
		else {
			editarFila();
			btnAceptar.setEnabled(true);
			habilitarEntradas(true);
			txtNombre.requestFocus();
		}
	}
	
	
	protected void actionPerformedBtnEliminar(ActionEvent arg0) {

		int codigoProducto = leerCodigoProducto();
		btnAdicionar.setEnabled(true);
		btnModificar.setEnabled(true);
		btnAceptar.setEnabled(false);
		
		if (ap.tamaÒo() == 0)
			mensaje("No existen clientes");
		else {
		int vale = confirmar("ø Desea eliminar el registro ?");
		if (vale == new GestionCliente().eliminar1(codigoProducto)) {
			ap.eliminar(ap.buscar(leerCodigoProducto()));
			ap.grabarProductos();;
			listar();
			editarFila();
			
		}}
		
	}
	protected void actionPerformedBtnAceptar(ActionEvent arg0) {
		int codigoProducto = leerCodigoProducto();
		String nombre = leerNombre();
		if (nombre.length() > 0) {
			String Descripcion = leerDescripcion();
			if (Descripcion.length() > 0)
				try {
					int stock = leerStock();
					try {
						double precioUnitario = leerPrecio();
						if (btnAdicionar.isEnabled() == false) {
							Producto nueva = new Producto(codigoProducto, stock, Descripcion, nombre, precioUnitario);
							int ok= new GestionCliente().registrar1(nueva);
							ap.adicionar(nueva);
							ap.grabarProductos();
							btnAdicionar.setEnabled(true);
						}
						if (btnModificar.isEnabled() == false) {
							Producto nueva = new Producto(codigoProducto, stock, Descripcion, nombre, precioUnitario);
                            int ok= new GestionCliente().actualizar1(nueva);
                            
							Producto m = ap.buscar(codigoProducto);
							m.setNombre(nombre);
							m.setDescripcion(Descripcion);
							m.setStock(stock);
							m.setPrecioUnitario(precioUnitario);
							
							btnModificar.setEnabled(true);
						}
						ap.grabarProductos();
						listar();
						habilitarEntradas(false);
					}
					catch (Exception e) {
						error("Ingrese el PRECIO correcto", txtPrecio);
					}
				}
				catch (Exception e) {
					error("Ingrese el STOCK correcto", txtStock);
				}
			else
				error("ingrese la DESCRIPCION correcta", txtDescripcion);
		}
		else
			error("ingrese el NOMBRE correcto", txtNombre);		
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
		if (arg0.getSource() == tblProducto) {
			mouseClickedTblMedicina(arg0);
		}
	}
	public void mouseEntered(MouseEvent arg0) {
		if (arg0.getSource() == btnAceptar) {
			mouseEnteredBtnAceptar(arg0);
		}
		if (arg0.getSource() == btnEliminar) {
			mouseEnteredBtnEliminar(arg0);
		}
		if (arg0.getSource() == btnModificar) {
			mouseEnteredBtnModificar(arg0);
		}
		if (arg0.getSource() == btnAdicionar) {
			mouseEnteredBtnAdicionar(arg0);
		}
		if (arg0.getSource() == tblProducto) {
			mouseEnteredTblMedicina(arg0);
		}
	}
	public void mouseExited(MouseEvent arg0) {
	}
	public void mousePressed(MouseEvent arg0) {
	}
	public void mouseReleased(MouseEvent arg0) {
	}
	protected void mouseClickedTblMedicina(MouseEvent arg0) {
		habilitarEntradas(false);
		habilitarBotones(true);
		editarFila();
	}
	protected void mouseEnteredTblMedicina(MouseEvent arg0) {
		tblProducto.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}
	protected void mouseEnteredBtnAdicionar(MouseEvent arg0) {
		btnAdicionar.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}
	protected void mouseEnteredBtnModificar(MouseEvent arg0) {
		btnModificar.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}
	protected void mouseEnteredBtnEliminar(MouseEvent arg0) {
		btnEliminar.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}
	protected void mouseEnteredBtnAceptar(MouseEvent arg0) {
		btnAceptar.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}
	//  MÈtodos tipo void (sin par·metros)
	void ajustarAnchoColumnas() {
		TableColumnModel tcm = tblProducto.getColumnModel();
		tcm.getColumn(0).setPreferredWidth(anchoColumna(20));  // CODIGOPRODUCTO
		tcm.getColumn(1).setPreferredWidth(anchoColumna(20));  // NOMBRE
		tcm.getColumn(2).setPreferredWidth(anchoColumna(20));  // DESCRIPCION
		tcm.getColumn(3).setPreferredWidth(anchoColumna(20));  // STOCK
		tcm.getColumn(4).setPreferredWidth(anchoColumna(20));  // PRECIOM UNI
	}
	void listar() {
		int posFila = 0;
		if (modelo.getRowCount() > 0)
			posFila = tblProducto.getSelectedRow();
		if (modelo.getRowCount() == ap.tamaÒo() - 1)
			posFila = ap.tamaÒo() - 1;
		if (posFila == ap.tamaÒo())
			posFila --;
		modelo.setRowCount(0);
		Producto m;
		for (int i=0; i<ap.tamaÒo(); i++) {
			m = ap.obtener(i);
			Object[] fila = { m.getCodigoProducto(),
					          m.getNombre(),
					          m.getDescripcion(),
					          m.getStock(),
					          m.getPrecioUnitario() };
			modelo.addRow(fila);
		}
		if (ap.tamaÒo() > 0)
			tblProducto.getSelectionModel().setSelectionInterval(posFila, posFila);
	}
	void editarFila() {
		if (ap.tamaÒo() == 0)
			limpieza();
		else {
			Producto m = ap.obtener(tblProducto.getSelectedRow());
			txtCodigoProducto.setText("" + m.getCodigoProducto());
			txtNombre.setText(m.getNombre());
			txtDescripcion.setText(m.getDescripcion());
			txtStock.setText("" + m.getStock());
			txtPrecio.setText("" +m.getPrecioUnitario());
		}
	}
	void limpieza() {
		txtCodigoProducto.setText("" + ap.codigoCorrelativo());
		txtNombre.setText("");
		txtDescripcion.setText("");
		txtStock.setText("");
		txtPrecio.setText("");
	}
	
	
	void mensaje(String s) {
		JOptionPane.showMessageDialog(this, s, "InformaciÛn", 0);
	}
	
	void error(String s, JTextField txt) {
		mensaje(s);
		txt.setText("");
		txt.requestFocus();
	}
	
	void habilitarEntradas(boolean sino) {
		btnAceptar.setEnabled(sino);
		txtNombre.setEditable(sino);
		txtDescripcion.setEditable(sino);
		txtStock.setEditable(sino);
		txtPrecio.setEditable(sino);
	}
	void habilitarBotones(boolean sino) {
		btnAdicionar.setEnabled(sino);
		btnModificar.setEnabled(sino);
	}
	
	int leerCodigoProducto() {
		return Integer.parseInt(txtCodigoProducto.getText().trim());
	}
	String leerDescripcion() {
		if(txtDescripcion.getText().length()==0) {
			aviso1 ("La descripcion no debe estar vacia");
			return null;
		}
		
		
		String patron="[a-zA-ZÒ—·ÈÌÛ˙¡…Õ”⁄]+( [a-zA-ZÒ—·ÈÌÛ˙¡…Õ”⁄]+)*{30,50}";
		if(!txtDescripcion.getText().matches(patron)){
			
			aviso("Descripcion no valida");
			return null;
		}
		
		return txtDescripcion.getText().trim();
		
		
		
	}
	String leerNombre() {
		if(txtNombre.getText().length()==0) {
			aviso1 ("El Nombre no debe estar vacio");
			return null;
		}
		
		
		String patron="[a-zA-ZÒ—·ÈÌÛ˙¡…Õ”⁄]+( [a-zA-ZÒ—·ÈÌÛ˙¡…Õ”⁄]+)*";
				if(!txtNombre.getText().matches(patron)){
					
					aviso("Nombre no valido");
					return null;
				}
				return txtNombre.getText().trim();
	}
	
	
	
	
	
	int leerStock() {
		return Integer.parseInt(txtStock.getText().trim());
	}
	
	
	
	double leerPrecio() {
		if(txtPrecio.getText().length()==0) {
			aviso1 ("El Precio no debe estar vacio");
		}
		return Double.parseDouble(txtPrecio.getText().trim());

	}
	
	private void aviso(String ms) {
		JOptionPane.showMessageDialog(this, ms,"Avisos",JOptionPane.WARNING_MESSAGE);
	}
	//  MÈtodos que retornan valor (con par·metros)
	int anchoColumna(int porcentaje) {
		return porcentaje * scrollPane.getWidth() / 100;
	}
	int confirmar(String s) {
		return JOptionPane.showConfirmDialog(this, s, "Alerta", 0, 1, null);
	}
	private void aviso1(String msg) {
		JOptionPane.showMessageDialog(this, msg,"Avisos",JOptionPane.WARNING_MESSAGE);
		
	}
	
}