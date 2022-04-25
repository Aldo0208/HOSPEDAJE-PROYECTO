package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import hilos.HiloCerrar;
import rojerusan.componentes.RSProgressMaterial;
import rojeru_san.RSPanelShadow;
import rojeru_san.RSMTextFull;
import java.awt.Color;
import rojeru_san.RSMPassView;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import rojeru_san.RSButtonRiple;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Login extends JFrame {
	
	private JFrame ventana;

	




	private JPanel contentPane;

	private int cont=0;
	private JPanel pnlCargando;
	private RSProgressMaterial progressMaterial;
	private JPanel pnlSesion;
	private JLabel label;
	private JLabel label_1;
	private RSMTextFull txtusuario;
	private RSMPassView txtpassword;
	private RSButtonRiple btnAceptar;
	private JLabel label_2;
	public static JLabel lblTiempo;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				iniciatiempo();
			}
		});
		setResizable(false);
		setTitle("Proyecto_Hospedaje");
		setIconImage(new ImageIcon("src/img/icono.png").getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 652, 352);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		{
			pnlSesion = new JPanel();
			pnlSesion.setBounds(309, 0, 383, 407);
			contentPane.add(pnlSesion);
			pnlSesion.setLayout(null);
			{
				label = new JLabel("");
				label.setBounds(10, 187, 40, 42);
				pnlSesion.add(label);
			}
			{
				label_1 = new JLabel("");
				label_1.setBounds(10, 135, 40, 42);
				pnlSesion.add(label_1);
			}
			{
				txtusuario = new RSMTextFull();
				txtusuario.addKeyListener(new KeyAdapter() {
					@Override
					public void keyPressed(KeyEvent arg0) {
						if (arg0.getKeyCode()==KeyEvent.VK_TAB)
							JOptionPane.showMessageDialog(null, "¡Ingrese usuario y contraseña!");
					}
				});
				txtusuario.setPlaceholder("Ingrese usuario");
				txtusuario.setFont(new Font("Dialog", Font.PLAIN, 14));
				txtusuario.setBounds(60, 134, 200, 42);
				pnlSesion.add(txtusuario);
			}
			{
				txtpassword = new RSMPassView();
				txtpassword.setPlaceholder("Ingrese contrase\u00F1a");
				txtpassword.setFont(new Font("Dialog", Font.PLAIN, 14));
				txtpassword.setBounds(60, 187, 200, 42);
				pnlSesion.add(txtpassword);
			}
			{
				btnAceptar = new RSButtonRiple();
				btnAceptar.setColorText(Color.WHITE);
				btnAceptar.setBackground(Color.BLUE);
				btnAceptar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if (cont>2){
							JOptionPane.showMessageDialog(null, "ERROR, Exceso de errores");
							System.exit(0);
						}else if (txtusuario.getText().isEmpty() || txtpassword.getText().isEmpty()){
								JOptionPane.showMessageDialog(null, "¡Ingrese usuario y contraseña!");
						}else{
							if (txtusuario.getText().equals("ADMINISTRADOR")&& txtpassword.getText().equals("ADMIN")){
								pnlSesion.setVisible(false);
								pnlCargando.setVisible(true);
								
								new Thread(new Runnable(){
									@Override
									public void run(){
										try {
											Thread.sleep(4000);
											JOptionPane.showMessageDialog(null, "¡Bienvenido");
											dispose();
											new Proyecto_Hospedaje().setVisible(true);					
										} catch (InterruptedException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
									}
								}).start();
								
								
								
								
								
							}else{
								JOptionPane.showMessageDialog(null, "¡Usuario y/o contraseña incorrecta");
								cont++;
								txtusuario.setText("");
								txtpassword.setText("");
								txtusuario.requestFocus();
							}
						}
					}
				});
				btnAceptar.setText("Aceptar");
				btnAceptar.setBounds(60, 253, 200, 40);
				pnlSesion.add(btnAceptar);
			}
			{
				label_2 = new JLabel("");
				label_2.setBounds(92, 11, 96, 105);
				pnlSesion.add(label_2);
			}
			
			lblTiempo = new JLabel("20s");
			lblTiempo.setBounds(241, 26, 46, 14);
			pnlSesion.add(lblTiempo);
			
			JLabel lblNewLabel = new JLabel("Esta ventana se cerrara en ");
			lblNewLabel.setBounds(60, 26, 171, 14);
			pnlSesion.add(lblNewLabel);
		}
		{
			pnlCargando = new JPanel();
			pnlCargando.setVisible(false);
			pnlCargando.setBounds(336, 11, 257, 282);
			contentPane.add(pnlCargando);
			pnlCargando.setLayout(null);
			{
				progressMaterial = new RSProgressMaterial();
				progressMaterial.setBounds(72, 61, 150, 150);
				pnlCargando.add(progressMaterial);
			}
		}
		
		JLabel jLbl_Imagen = new JLabel();
		jLbl_Imagen.setBounds(-15, 3, 344, 355);
		contentPane.add(jLbl_Imagen);
		javax.swing.ImageIcon Imagen = new javax.swing.ImageIcon("src/img/hotel.png");
		javax.swing.JLabel Img = new javax.swing.JLabel(Imagen);
		Img.setLocation(0, 22);
		contentPane.add(Img);
		Img.setSize(310, 292);
		setLocationRelativeTo(null);
	
	}
	
	
	
	void iniciatiempo() {
		
		HiloCerrar hilo=new HiloCerrar(this);
		hilo.start();
		
	}
	
	
}
