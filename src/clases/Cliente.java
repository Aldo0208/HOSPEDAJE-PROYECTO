package clases;

public class Cliente {
	//	Atributos privados
	private int codigoCliente;
	private String nombres, apellidos, telefono, dni;
	//	Constructor
	public Cliente(int codigoCliente, String nombres, String apellidos, String telefono, String dni) {
		this.codigoCliente = codigoCliente;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.telefono = telefono;
		this.dni = dni;
	}
	
	public Cliente() {
		// TODO Auto-generated constructor stub
	}

	//  Métodos de acceso público: set/get
	public void setCodigoCliente(int codigoCliente) {
		this.codigoCliente = codigoCliente;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public int getCodigoCliente() {
		return codigoCliente;
	}
	public String getNombres() {
		return nombres;
	}
	public String getApellidos() {
		return apellidos;
	}
	public String getTelefono() {
		return telefono;
	}
	public String getDni() {
		return dni;
	}
	
}