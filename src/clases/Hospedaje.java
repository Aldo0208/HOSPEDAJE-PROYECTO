package clases;

public class Hospedaje {
	//	Atributos privados
	private int codigoHospedaje, codigoCliente, numeroHabi, estado;
	private String fechaIngreso, horaIngreso, fechaSalida, horaSalida;
	private double totalPagar;
	//	Constructor
	public Hospedaje(int codigoHospedaje, int codigoCliente, int numeroHabi,
			             String fechaIngreso, String horaIngreso, String fechaSalida,
			             String horaSalida, double totalPagar, int estado) {
		this.codigoHospedaje = codigoHospedaje;
		this.codigoCliente = codigoCliente;
		this.numeroHabi= numeroHabi;
		this.fechaIngreso = fechaIngreso;
		this.horaIngreso = horaIngreso;
		this.fechaSalida = fechaSalida;
		this.horaSalida = horaSalida;
		this.totalPagar = totalPagar;
		this.estado = estado;
	}
	//  Métodos de acceso público
	public int getCodigoHospedaje() {
		return codigoHospedaje;
	}
	public void setCodigoHospedaje(int codigoHospedaje) {
		this.codigoHospedaje = codigoHospedaje;
	}
	public int getCodigoCliente() {
		return codigoCliente;
	}
	public void setCodigoCliente(int codigoCliente) {
		this.codigoCliente = codigoCliente;
	}
	public int getNumeroHabi() {
		return numeroHabi;
	}
	public void setNumeroHabi(int numeroHabi) {
		this.numeroHabi = numeroHabi;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	public String getFechaIngreso() {
		return fechaIngreso;
	}
	public void setFechaIngreso(String fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}
	public String getHoraIngreso() {
		return horaIngreso;
	}
	public void setHoraIngreso(String horaIngreso) {
		this.horaIngreso = horaIngreso;
	}
	public String getFechaSalida() {
		return fechaSalida;
	}
	public void setFechaSalida(String fechaSalida) {
		this.fechaSalida = fechaSalida;
	}
	public String getHoraSalida() {
		return horaSalida;
	}
	public void setHoraSalida(String horaSalida) {
		this.horaSalida = horaSalida;
	}
	public double getTotalPagar() {
		return totalPagar;
	}
	public void setTotalPagar(double totalPagar) {
		this.totalPagar = totalPagar;
	}
	

}