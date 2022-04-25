package clases;

public class Boleta {
	
	//  Atributos privados
	private int codigoProducto, cantidad;
	private double precioUnitario;	
	//  Constructor
    public Boleta(int codigoProducto, int cantidad, double precioUnitario) {
        this.codigoProducto = codigoProducto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }
    //  Métodos de acceso público: set/get
	public void setCodigoProducto(int codigoProducto) {
		this.codigoProducto = codigoProducto;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public void setPrecioUnitario(double precioUnitario) {
		this.precioUnitario = precioUnitario;
	}
	public int getCodigoProducto() {
		return codigoProducto;
	}
	public int getCantidad() {
		return cantidad;
	}
	public double getPrecioUnitario() {
		return precioUnitario;
	}
	
}