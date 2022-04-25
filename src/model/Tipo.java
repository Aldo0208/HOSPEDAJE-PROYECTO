package model;

public class Tipo {
	private int idtipo;
	private String descripcion;
	public int getIdtipo() {
		return idtipo;
	}
	public void setIdtipo(int idtipo) {
		this.idtipo = idtipo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Tipo(int idtipo, String descripcion) {
	
		this.idtipo = idtipo;
		this.descripcion = descripcion;
	}
	public Tipo() {
		
	}

	
	
}
