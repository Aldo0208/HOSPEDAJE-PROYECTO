package gui;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;

public class Proyecto_Hospedaje extends JFrame implements ActionListener {

	private JLabel lblFondo;
	private JMenuBar menuProyecto;
	private JMenu mnArchivo;
	private JMenu mnMantenimiento;
	private JMenu mnRegistro;
	private JMenu mnPago;
	private JMenu mnReporte;
	private JMenuItem mntmSalir;
	private JMenuItem mntmCliente;
	private JMenuItem mntmProducto;	
	private JMenuItem mntmAtencion;
	private JMenuItem mntmHospedaje;
	private JMenuItem mntmAtenciones;
	private JMenuItem mntmHospedajes;
	private JMenuItem mntmAtencionesPendientes;
	private JMenuItem mntmHospedajesPendientes;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Proyecto_Hospedaje frame = new Proyecto_Hospedaje();
					frame.setVisible(true);
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the frame.
	 */
	public Proyecto_Hospedaje() {
		setAutoRequestFocus(false);
		int ANCHO = 760, ALTO = 510,
			X = 160, Y = 140;
		
		setResizable(false);
		setTitle("PROYECTO DE HOSPEDAJE");
		setIconImage(new ImageIcon("src/img/icono.png").getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(ANCHO + X, ALTO + Y);
		this.setLocationRelativeTo(null);
		
		menuProyecto = new JMenuBar();
		setJMenuBar(menuProyecto);
		
		mnArchivo = new JMenu("Archivo");
		menuProyecto.add(mnArchivo);
		
		mntmSalir = new JMenuItem("Salir");  
		mntmSalir.setIcon(new ImageIcon(Proyecto_Hospedaje.class.getResource("/img/iconfinder_simpline_43_2305619.png")));
		mntmSalir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5, InputEvent.ALT_MASK));
		mntmSalir.addActionListener(this);
		mnArchivo.add(mntmSalir);
		
		mnMantenimiento = new JMenu("Mantenimiento");
		menuProyecto.add(mnMantenimiento);
		
		mntmCliente = new JMenuItem("Cliente");
		mntmCliente.addActionListener(this);
		mnMantenimiento.add(mntmCliente);
		
		mntmProducto = new JMenuItem("Producto");
		mntmProducto.addActionListener(this);
		mnMantenimiento.add(mntmProducto);
		
		mnRegistro = new JMenu("Registro");
		menuProyecto.add(mnRegistro);
		
		mntmAtencion = new JMenuItem("Atencion");
		mntmAtencion.addActionListener(this);
		mnRegistro.add(mntmAtencion);
		
		mntmHospedaje = new JMenuItem("Hospedaje");
		mntmHospedaje.addActionListener(this);
		mnRegistro.add(mntmHospedaje);
		
		mnPago = new JMenu("Pago");
		menuProyecto.add(mnPago);
		
		mntmAtenciones = new JMenuItem("Atenciones");
		mntmAtenciones.addActionListener(this);
		mnPago.add(mntmAtenciones);
	
		mntmHospedajes = new JMenuItem("Hospedajes");
		mntmHospedajes.addActionListener(this);
		mnPago.add(mntmHospedajes);
		
		mnReporte = new JMenu("Boleta");
		menuProyecto.add(mnReporte);
	
		mntmAtencionesPendientes = new JMenuItem("Atenciones pendientes");
		mntmAtencionesPendientes.addActionListener(this);
		mnReporte.add(mntmAtencionesPendientes);
		
		mntmHospedajesPendientes = new JMenuItem("Hospedajes pendientes");
		mntmHospedajesPendientes.addActionListener(this);
		mnReporte.add(mntmHospedajesPendientes);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblFondo = new JLabel(new ImageIcon("src/img/fondo.jpg"));
		lblFondo.setBounds(0, 0, 914, 600);
		getContentPane().add(lblFondo);	
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == mntmHospedajesPendientes) {
			actionPerformedMntmHospedajesPendientes(e);
		}
		if (e.getSource() == mntmAtencionesPendientes) {
			actionPerformedMntmAtencionesPendientes(e);
		}
		if (e.getSource() == mntmHospedajes) {
			actionPerformedMntmHospedajes(e);
		}
		if (e.getSource() == mntmAtenciones) {
			actionPerformedMntmAtenciones(e);
		}	
		if (e.getSource() == mntmHospedaje) {
			actionPerformedMntmHospedaje(e);
		}
		if (e.getSource() == mntmAtencion) {
			actionPerformedMntmAtencion(e);
		}
		if (e.getSource() == mntmProducto) {
			actionPerformedMntmProducto(e);
		}
		if (e.getSource() == mntmCliente) {
			actionPerformedMntmCliente(e);
		}
		
		if (e.getSource() == mntmSalir) {
			actionPerformedMntmSalir(e);
		}
	}
	protected void actionPerformedMntmSalir(ActionEvent arg0) {
		dispose();
	}
	
	protected void actionPerformedMntmCliente(ActionEvent arg0) {
		DlgCliente dp = new DlgCliente();
		dp.setVisible(true);
		lblFondo.add(dp);
	}
	protected void actionPerformedMntmProducto(ActionEvent arg0) {
		DlgProducto dm = new DlgProducto();
		dm.setVisible(true);
		lblFondo.add(dm);
	}
	protected void actionPerformedMntmAtencion(ActionEvent arg0) {
		DlgAtencion da = new DlgAtencion();
		da.setVisible(true);
		lblFondo.add(da);
	}
	protected void actionPerformedMntmHospedaje(ActionEvent arg0) {
		DlgHospedaje di = new DlgHospedaje();
		di.setVisible(true);
		lblFondo.add(di);
	}
	protected void actionPerformedMntmAtenciones(ActionEvent arg0) {
		DlgAtenciones da = new DlgAtenciones();
		da.setVisible(true);
		lblFondo.add(da);
	}
	protected void actionPerformedMntmHospedajes(ActionEvent arg0) {
		DlgHospedajes di = new DlgHospedajes();
		di.setVisible(true);
		lblFondo.add(di);
	}
	protected void actionPerformedMntmAtencionesPendientes(ActionEvent arg0) {
		DlgAtencionesPendientes dap = new DlgAtencionesPendientes();
		dap.setVisible(true);
		lblFondo.add(dap);
	}
	protected void actionPerformedMntmHospedajesPendientes(ActionEvent arg0) {
		DlgHospedajesPendientes dip = new DlgHospedajesPendientes();
		dip.setVisible(true);
		lblFondo.add(dip);
	}
	
}