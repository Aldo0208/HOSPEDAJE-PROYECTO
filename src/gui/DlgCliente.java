package gui;

import clases.Cliente;
import clases.Producto;
import mantenimientos.GestionCliente;
import arreglos.ArregloCliente;

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

public class DlgCliente extends JInternalFrame implements ActionListener, KeyListener, MouseListener {
	
	private JLabel lblImgCliente;
	private JLabel lblCodigoCliente;
	private JLabel lblNombres;
	private JLabel lblApellidos;
	private JLabel lblTelefono;
	private JLabel lblDni;
	private JTextField txtCodigoCliente;
	private JTextField txtNombres;
	private JTextField txtApellidos;
	private JTextField txtTelefono;
	private JTextField txtDni;
	private JScrollPane scrollPane;
	private JButton btnAceptar;
	private JButton btnAdicionar;
	private JButton btnModificar;
	private JButton btnEliminar;
	private JTable tblCliente;
	private DefaultTableModel modelo;
	
	int idSeleccionado=-1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DlgCliente dialog = new DlgCliente();
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
	public DlgCliente() {
		setIconifiable(true);
		setClosable(true);
		setResizable(false);
		setTitle("Mantenimiento | Cliente");
		setBounds(100, 100, 701, 434);
		getContentPane().setLayout(null);
		
		lblImgCliente = new JLabel();
		lblImgCliente.setIcon(new ImageIcon("src/img/cliente.jpg"));
		lblImgCliente.setBounds(555, 25, 88, 89);
		getContentPane().add(lblImgCliente);
		
		lblCodigoCliente = new JLabel("C\u00F3digo");
		lblCodigoCliente.setBounds(10, 24, 110, 23);
		getContentPane().add(lblCodigoCliente);
		
		lblNombres = new JLabel("Nombres");
		lblNombres.setBounds(10, 54, 70, 23);
		getContentPane().add(lblNombres);
	
		lblApellidos = new JLabel("Apellidos");
		lblApellidos.setBounds(10, 84, 70, 23);
		getContentPane().add(lblApellidos);
		
		lblTelefono = new JLabel("Teléfono");
		lblTelefono.setBounds(305, 24, 70, 23);
		getContentPane().add(lblTelefono);
		
		lblDni = new JLabel("DNI");
		lblDni.setBounds(305, 54, 70, 23);
		getContentPane().add(lblDni);
		
		txtCodigoCliente = new JTextField();
		txtCodigoCliente.setBounds(90, 24, 85, 23);
		getContentPane().add(txtCodigoCliente);
		txtCodigoCliente.setColumns(10);

		txtNombres = new JTextField();
		txtNombres.setBounds(90, 54, 200, 23);
		getContentPane().add(txtNombres);
		txtNombres.setColumns(10);

		txtApellidos = new JTextField();
		txtApellidos.setBounds(90, 84, 200, 23);
		getContentPane().add(txtApellidos);
		txtApellidos.setColumns(10);
		
		txtTelefono = new JTextField();
		txtTelefono.setBounds(385, 24, 85, 23);
		getContentPane().add(txtTelefono);
		txtTelefono.setColumns(10);

		txtDni = new JTextField();
		txtDni.setBounds(385, 54, 85, 23);
		getContentPane().add(txtDni);
		txtDni.setColumns(10);

		btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(this);
		btnAceptar.addMouseListener(this);
		btnAceptar.setForeground(Color.ORANGE);
		btnAceptar.setBounds(555, 120, 100, 23);
		getContentPane().add(btnAceptar);
		
		btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addActionListener(this);
		btnAdicionar.addMouseListener(this);
		btnAdicionar.setForeground(Color.RED);
		btnAdicionar.setBounds(46, 360, 150, 23);
		getContentPane().add(btnAdicionar);

		btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(this);
		btnModificar.addMouseListener(this);
		btnModificar.setForeground(Color.RED);
		btnModificar.setBounds(278, 360, 150, 23);
		getContentPane().add(btnModificar);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(this);
		btnEliminar.addMouseListener(this);
		btnEliminar.setForeground(Color.RED);
		btnEliminar.setBounds(481, 360, 150, 23);
		getContentPane().add(btnEliminar);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 154, 675, 195);
		getContentPane().add(scrollPane);

		tblCliente = new JTable();
		tblCliente.addKeyListener(this);
		tblCliente.addMouseListener(this);
		tblCliente.setFillsViewportHeight(true);
		scrollPane.setViewportView(tblCliente);

		modelo = new DefaultTableModel();
		modelo.addColumn("CÓDIGO");
		modelo.addColumn("NOMBRES");
		modelo.addColumn("APELLIDOS");
		modelo.addColumn("TELÉFONO");
		modelo.addColumn("DNI");
		
		tblCliente.setModel(modelo);
		
		txtCodigoCliente.setEditable(false);
		
		habilitarEntradas(false);
		ajustarAnchoColumnas();
		listar();
		editarFila();
	}
	
	//  Declaración global
	ArregloCliente ac = new ArregloCliente();
	
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
		txtNombres.requestFocus();
	}
	protected void actionPerformedBtnModificar(ActionEvent arg0) {
		
		btnAdicionar.setEnabled(true);
		btnModificar.setEnabled(false);
		if (ac.tamaño() == 0) {
			btnAceptar.setEnabled(false);
			habilitarEntradas(false);
			mensaje("No existen clientes");	
		}
		else {
			editarFila();
			btnAceptar.setEnabled(true);
			habilitarEntradas(true);
			txtNombres.requestFocus();
		}
		
	}
	protected void actionPerformedBtnEliminar(ActionEvent arg0) {
		
		
		int codigoCliente = leerCodigoCliente();
		btnAdicionar.setEnabled(true);
		btnModificar.setEnabled(true);
		btnAceptar.setEnabled(false);
		
		if (ac.tamaño() == 0)
			mensaje("No existen clientes");
		else {
		int vale = confirmar("¿ Desea eliminar el registro ?");
		if (vale == new GestionCliente().eliminar(codigoCliente)) {
			ac.eliminar(ac.buscar(leerCodigoCliente()));
			ac.grabarClientes();;
			listar();
			editarFila();
			
		}}
		
		}
	
	protected void actionPerformedBtnAceptar(ActionEvent arg0) {
		int codigoCliente = leerCodigoCliente();
		String nombres = leerNombres();
		if (nombres.length() > 0) {
			String apellidos = leerApellidos();
			if (apellidos.length() > 0) {
				String telefono = leerTelefono();
				if (telefono.length() > 0) {
					String dni = leerDni();
					if (dni.length() > 0) {
						if (btnAdicionar.isEnabled() == false) {
							Cliente nuevo = new Cliente(codigoCliente, nombres, apellidos, telefono, dni);
                             int ok= new GestionCliente().registrar(nuevo);
							
							if(ok==0) {
								
								JOptionPane.showMessageDialog(null, this, "Error al registrar", ok);
							}
							ac.adicionar(nuevo);
						
							btnAdicionar.setEnabled(true);
							
                             
						}
						if (btnModificar.isEnabled() == false) {
							Cliente nuevo = new Cliente(codigoCliente, nombres, apellidos, telefono, dni);
                            int ok= new GestionCliente().actualizar(nuevo);
							
                           Cliente c = ac.buscar(codigoCliente);
							c.setNombres(nombres);
							c.setApellidos(apellidos);
							c.setTelefono(telefono);
							c.setDni(dni);
							
							btnModificar.setEnabled(true);
							
							
						}
						
						listar();
						habilitarEntradas(false);
						ac.grabarClientes();
					}
					
					else
						error("Ingrese el DNI correcto", txtDni);
				}
				else
					error("Ingrese el TELÉFONO correcto", txtTelefono);		
			}
			else
				error("ingrese los APELLIDOS correctos", txtApellidos);
		}
		else
			error("ingrese los NOMBRES correctos", txtNombres);		
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
		if (arg0.getSource() == tblCliente) {
			mouseEnteredTbCliente(arg0);
		}
	}
	public void mouseExited(MouseEvent arg0) {
	}
	public void mousePressed(MouseEvent arg0) {
	}
	public void mouseReleased(MouseEvent arg0) {
	}
	
	protected void mouseEnteredTbCliente(MouseEvent arg0) {
		tblCliente.setCursor(new Cursor(Cursor.HAND_CURSOR));
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
	//  Métodos tipo void (sin parámetros)
	void actualizarCliente() {
	int codigo=leerCodigoCliente();
	String nom= leerNombres();
	
	Cliente c= new Cliente();
	c.setCodigoCliente(codigo);
	c.setNombres(nom);
		
		
		int ok=new GestionCliente().actualizar(c);
		
		if(ok==0) {
			JOptionPane.showMessageDialog(this, "Codigo no existe");
		}else {
			JOptionPane.showMessageDialog(this, "Cliente actualizado");
		}
		
	}
	
	
	
	
	void eliminarCliente() {
		
		int codigo= leerCodigoCliente();
		
		int ok=new GestionCliente().eliminar(codigo);
		
	
		
		if(ok==0) {
			JOptionPane.showMessageDialog(this, "Codigo no existe");
		}else {
			JOptionPane.showMessageDialog(this, "Cliente eliminado");
		}
		
		
		
	}
	void ajustarAnchoColumnas() {
		TableColumnModel tcm = tblCliente.getColumnModel();
		tcm.getColumn(0).setPreferredWidth(anchoColumna(20));  // Cleinte
		tcm.getColumn(1).setPreferredWidth(anchoColumna(20));  // Nombres
		tcm.getColumn(2).setPreferredWidth(anchoColumna(20));  // Apellidos
		tcm.getColumn(3).setPreferredWidth(anchoColumna(20));  // Telefono
		tcm.getColumn(4).setPreferredWidth(anchoColumna(20));  // DNI
	}
	void listar() {
		int posFila = 0;
		if (modelo.getRowCount() > 0)
			posFila = tblCliente.getSelectedRow();
		if (modelo.getRowCount() == ac.tamaño() - 1)
			posFila = ac.tamaño() - 1;
		if (posFila == ac.tamaño())
			posFila --;
		modelo.setRowCount(0);
		Cliente c;
		for (int i=0; i<ac.tamaño(); i++) {
			c = ac.obtener(i);
			Object[] fila = { 
					c.getCodigoCliente(),
					          c.getNombres(),
					          c.getApellidos(),
					          c.getTelefono(),
					          c.getDni() };
			modelo.addRow(fila);
		}
		if (ac.tamaño() > 0)
			tblCliente.getSelectionModel().setSelectionInterval(posFila, posFila);
	}
	
	void eliminarfila() {
		
		int posFila = 0;
		if (modelo.getRowCount() > 0)
			posFila = tblCliente.getSelectedRow();
		if (modelo.getRowCount() == ac.tamaño() - 1)
			posFila = ac.tamaño() - 1;
		if (posFila == ac.tamaño())
			posFila --;
		Cliente c;
		for (int i=0; i<ac.tamaño(); i++) {
			c = ac.obtener(i);
		 
					          
		modelo.removeRow(c.getCodigoCliente());
		if (ac.tamaño() > 0)
			tblCliente.getSelectionModel().setSelectionInterval(posFila, posFila);}
	}
		
	

	void editarFila() {
		if (ac.tamaño() == 0)
			limpieza();
		else {
			Cliente p = ac.obtener(tblCliente.getSelectedRow());
			txtCodigoCliente.setText("" + p.getCodigoCliente());
			txtNombres.setText(p.getNombres());
			txtApellidos.setText(p.getApellidos());
			txtTelefono.setText(p.getTelefono());
			txtDni.setText(p.getDni());
		}
	}
	void limpieza() {
		txtCodigoCliente.setText("" + ac.codigoCorrelativo());
		txtNombres.setText("");
		txtApellidos.setText("");
		txtTelefono.setText("");
		txtDni.setText("");
	}

	
	void mensaje(String s) {
		JOptionPane.showMessageDialog(this, s, "Información", 0);
	}
	void error(String s, JTextField txt) {
		mensaje(s);
		txt.setText("");
		txt.requestFocus();
	}
	void habilitarEntradas(boolean sino) {
		btnAceptar.setEnabled(sino);
		txtNombres.setEditable(sino);
		txtApellidos.setEditable(sino);
		txtTelefono.setEditable(sino);
		txtDni.setEditable(sino);
	}
	void habilitarBotones(boolean sino) {
		btnAdicionar.setEnabled(sino);
		btnModificar.setEnabled(sino);
	}
	//  Métodos que retornan valor (sin parámetros)
	int leerCodigoCliente() {
		
		if(txtCodigoCliente.getText().length()==0) {
			aviso ("El codigo del cliente no debe estar vacio");
		}
		return Integer.parseInt(txtCodigoCliente.getText().trim());
	}
	private void aviso(String msg) {
		
		JOptionPane.showMessageDialog(this, msg,"Avisos",JOptionPane.WARNING_MESSAGE);
	}

	
	String leerNombres() {
		
		if(txtNombres.getText().length()==0) {
			aviso1 ("El Nombre del cliente no debe estar vacio");
		}
		return txtNombres.getText().trim();
	}
	

	String leerApellidos() {
		if(txtApellidos.getText().length()==0) {
			aviso1 ("Los Apellidos del cliente no debe estar vacio");
		}
		return txtApellidos.getText().trim();
	}
	
	String leerTelefono() {
		if(txtTelefono.getText().length()==0) {
			aviso ("El Campo DNI no debe estar vacio");
		}
		return txtTelefono.getText().trim();
	}
	
	String leerDni() {
		if(txtDni.getText().length()==0) {
			aviso ("El Campo DNI no debe estar vacio");
		}
		return txtDni.getText().trim();
	}
	
	
	int anchoColumna(int porcentaje) {
		return porcentaje * scrollPane.getWidth() / 100;
	}
	int confirmar(String s) {
		return JOptionPane.showConfirmDialog(this, s, "Alerta", 0, 1, null);
	}
	
	
	private void aviso1(String msg) {
		JOptionPane.showMessageDialog(this, msg,"Avisos",JOptionPane.WARNING_MESSAGE);
		
	}
	
	
	void adicionarDatos() {
		Cliente c =new Cliente();
		c.setNombres("adlo");
		c.setApellidos("carlos");
		c.setDni("123213");
		c.setTelefono("123213");
		
		int ok= new GestionCliente().registrar(c);
		
		if(ok==0) {
			
			JOptionPane.showMessageDialog(this, "Error al registrar");
		} else {
			
			JOptionPane.showMessageDialog(this, "Cliente"+c.getNombres()+"registrado");
		}
		
		
		
		
	}
	
	
	
	
}