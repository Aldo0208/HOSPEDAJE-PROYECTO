package hilos;

import javax.swing.JFrame;

import gui.Login;

public class HiloCerrar extends Thread{
	
	private JFrame ventana;

	
	
	
	public HiloCerrar(JFrame ventana) {
	
		this.ventana = ventana;
	}




	public void run() {
		
		for(int i=20; i>=0; i--){
			Login.lblTiempo.setText(i+"s");
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.err.println("Error en la pausa del tiempo: "+e.getMessage());
			}
		}
		ventana.dispose();

	}

}
