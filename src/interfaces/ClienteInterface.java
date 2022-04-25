package interfaces;

import java.util.ArrayList;
import clases.Producto;
import mantenimientos.GestionCliente;
import clases.Atencion;
import clases.Cliente;
import clases.Habitaciont;
import model.Tipo;

public interface ClienteInterface {

	public int registrar(Cliente c);
	public int eliminar(int codigo);
	public int actualizar(Cliente c);
	public ArrayList<Cliente> listado();
	public ArrayList<Habitaciont> listadodeHabitacion();
	ArrayList<Tipo> listadodeTipos();
	
	public int registrar1(Producto p);
	public int eliminar1(int codigoProducto);
	public int actualizar1(Producto p);
	
	public ArrayList<Cliente> listadoxProducto(int codigoProducto);
	
}
