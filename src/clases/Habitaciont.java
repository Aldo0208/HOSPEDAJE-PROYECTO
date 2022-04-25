package clases;

public class Habitaciont {
//	Atributos privados
	private int id_habita;
	private String des_habita;
	//	Constructor
	public Habitaciont(int numerohabi, String descripcion) {
		this.id_habita = numerohabi;
		this.des_habita=descripcion;
		
	}
	//  Métodos de acceso público: set/get
	public int getNumerohabi() {
		return id_habita;
	}
	public void setNumerohabi(int numerohabi) {
		this.id_habita = numerohabi;
	}
	public String getDes_habita() {
		return des_habita;
	}
	public void setDes_habita(String des_habita) {
		this.des_habita = des_habita;
	}}
