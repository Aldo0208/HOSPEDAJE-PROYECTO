package clases;

public class Producto {

	//	Atributos privados
	private int codigoProducto, stock;
	private String nombre, descripcion;
	private double precioUnitario;
	//	Constructor
	public Producto(int codigoProducto, int stock, String nombre, String descripcion, double precioUnitario) {
	super();
		this.codigoProducto = codigoProducto;
		this.stock = stock;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precioUnitario = precioUnitario;
	}
	


	//  Métodos de acceso público: set/get
	public int getCodigoProducto() {
		return codigoProducto;
	}
	public void setCodigoProducto(int codigoProducto) {
		this.codigoProducto = codigoProducto;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public double getPrecioUnitario() {
		return precioUnitario;
	}
	public void setPrecioUnitario(double precioUnitario) {
		this.precioUnitario = precioUnitario;
	}



	
}