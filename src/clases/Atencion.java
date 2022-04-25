package clases;

public class Atencion {

	//	Atributos privados
	private int codigoAtencion, codigoCliente, estado;
	private String fechaAtencion, horaAtencion;
	private double aPagar;
	//	Constructor
	public Atencion(int codigoAtencion, int codigoCliente, String fechaAtencion,
			        String horaAtencion, double aPagar, int estado) {
		this.codigoAtencion = codigoAtencion;
		this.codigoCliente = codigoCliente;
		this.fechaAtencion = fechaAtencion;
		this.horaAtencion = horaAtencion;
		this.aPagar = aPagar;
		this.estado = estado;
	}
	
	//  Métodos de acceso público: 
	public int getCodigoAtencion() {
		return codigoAtencion;
	}
	public void setCodigoAtencion(int codigoAtencion) {
		this.codigoAtencion = codigoAtencion;
	}
	public int getCodigoCliente() {
		return codigoCliente;
	}
	public void setCodigoCliente(int codigoCliente) {
		this.codigoCliente = codigoCliente;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	public String getFechaAtencion() {
		return fechaAtencion;
	}
	public void setFechaAtencion(String fechaAtencion) {
		this.fechaAtencion = fechaAtencion;
	}
	public String getHoraAtencion() {
		return horaAtencion;
	}
	public void setHoraAtencion(String horaAtencion) {
		this.horaAtencion = horaAtencion;
	}
	public double getaPagar() {
		return aPagar;
	}
	public void setaPagar(double aPagar) {
		this.aPagar = aPagar;
	}

	
	
}