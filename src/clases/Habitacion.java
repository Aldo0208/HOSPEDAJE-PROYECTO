package clases;

public class Habitacion {
	//	Atributos privados
	private int codhabi, categoria, estado;
	private double precioDia;
	//	Constructor
	public Habitacion(int numerohabi, int categoria, double precioDia, int estado) {
		this.codhabi = numerohabi;
		this.categoria = categoria;
		this.precioDia = precioDia;
		this.estado = estado;
	}
	//  Métodos de acceso público: set/get
	public int getNumerohabi() {
		return codhabi;
	}
	public void setNumerohabi(int numerohabi) {
		this.codhabi = numerohabi;
	}
	public int getCategoria() {
		return categoria;
	}
	public void setCategoria(int categoria) {
		this.categoria = categoria;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	public double getPrecioDia() {
		return precioDia;
	}
	public void setPrecioDia(double precioDia) {
		this.precioDia = precioDia;
	}

	
}