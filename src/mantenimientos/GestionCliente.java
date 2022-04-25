package mantenimientos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import clases.Cliente;
import clases.Habitaciont;
import clases.Producto;
import interfaces.ClienteInterface;
import model.Tipo;
import utils.MySQLConexion8;

public class GestionCliente implements ClienteInterface{

	public int registrar(Cliente c) {
		
		int rs=0;
		
		//Plantilla!!!!!
		Connection con=null;
		PreparedStatement pst=null;
		
		try {
			
			con=MySQLConexion8.getConexion();
			
			String sql="insert into tb_cliente values(?,?,?,?,?)";
			
			pst=con.prepareStatement(sql);
			pst.setInt(1, c.getCodigoCliente());
			pst.setString(2, c.getNombres());
			pst.setString(3, c.getApellidos());
			pst.setString(4, c.getDni());
			pst.setString(5, c.getTelefono());
			
			rs=pst.executeUpdate();
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error en registrar\n"+e.getMessage());
		}finally {
			try {
				con.close();
			} catch (SQLException e) {
				System.out.println("Error al cerrar: "+e.getMessage());
			}
		}
		
		return rs;
	}

	public int eliminar(int codigo) {
		int rs=0;
		// TODO Auto-generated method stub
		/*!plantilla!!*/
		Connection con=null;
		PreparedStatement pst=null;
		
		try {
			
			con=MySQLConexion8.getConexion();
			
			String sql="delete from tb_cliente where codigo=?";
			
			pst=con.prepareStatement(sql);
			pst.setInt(1, codigo);
			
			rs=pst.executeUpdate();
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error en eliminar\n"+e.getMessage());
		}finally {
			try {
				con.close();
			} catch (SQLException e) {
				System.out.println("Error al cerrar: "+e.getMessage());
			}
		}
		
		return rs;
	}

	public int actualizar(Cliente c) {
		int rs=0;
		//Plantilla
			Connection con = null; 
			PreparedStatement pst = null;

			try {
				con = MySQLConexion8.getConexion();
				String sql = "update tb_cliente set nombre=? where codigo =?";

				pst = con.prepareStatement(sql);
				pst.setString(1, c.getNombres());
				pst.setInt(2, c.getCodigoCliente());

				rs = pst.executeUpdate();

			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Error en actualizar\n"+e.getMessage());
			}finally {
				try {
					con.close();

				} catch (SQLException e) {


					System.out.println("Error al cerrar: "+e.getMessage());

				}

			}

			return rs;

		}
		
		

	public ArrayList<Cliente> listado() {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public ArrayList<Tipo> listadodeTipos() {
		return null;
	
	}

	@Override
	public ArrayList<Habitaciont> listadodeHabitacion() {
		ArrayList<Habitaciont> lista= new ArrayList<Habitaciont>();
		
		// TODO Auto-generated method stub
		/*!plantilla!!*/
		Connection con=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		
		try {
			
			con=MySQLConexion8.getConexion();
			
			String sql="select*from tb_habitacion";
			
			pst=con.prepareStatement(sql);
			
			rs=pst.executeQuery();
			
			
		while(rs.next()) {
			/*Habitacion h = new Habitacion(rs.getInt(1),rs.getString(2));
			lista.add(h);
			
			Habitacion h= new Habitacion();
			h.setNumerohabi(rs.getInt(1));
			h.setDes_habita(rs.getString(2));
			lista.add(h);*/
			
			lista.add(new Habitaciont(rs.getInt(1),rs.getString(2)));
			
		}
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error en eliminar\n"+e.getMessage());
		}finally {
			try {
				con.close();
			} catch (SQLException e) {
				System.out.println("Error al cerrar: "+e.getMessage());
			}
		}
		
		
		return lista;
	}

	@Override
	public int registrar1(Producto p) {
          int rs=0;
		
		//Plantilla!!!!!
		Connection con=null;
		PreparedStatement pst=null;
		
		try {
			
			con=MySQLConexion8.getConexion();
			
			String sql="insert into tb_productos values(?,?,?,?,?)";
			
			pst=con.prepareStatement(sql);
			pst.setInt(1,p.getCodigoProducto());
			pst.setString(2, p.getNombre());
			pst.setString(3, p.getDescripcion());
			pst.setInt(4, p.getStock());
			pst.setDouble(5, p.getPrecioUnitario());
			
			rs=pst.executeUpdate();
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error en registrar\n"+e.getMessage());
		}finally {
			try {
				con.close();
			} catch (SQLException e) {
				System.out.println("Error al cerrar: "+e.getMessage());
			}
		}
		
		return rs;
	}

	@Override
	public int eliminar1(int codigoProducto) {
		int rs=0;
		// TODO Auto-generated method stub
		/*!plantilla!!*/
		Connection con=null;
		PreparedStatement pst=null;
		
		try {
			
			con=MySQLConexion8.getConexion();
			
			String sql="delete from tb_productos where cod_producto =?";
			
			pst=con.prepareStatement(sql);
			pst.setInt(1, codigoProducto);
			
			rs=pst.executeUpdate();
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error en eliminar\n"+e.getMessage());
		}finally {
			try {
				con.close();
			} catch (SQLException e) {
				System.out.println("Error al cerrar: "+e.getMessage());
			}
		}
		
		return rs;
	}

	@Override
	public int actualizar1(Producto p) {
		int rs=0;
		//Plantilla
			Connection con = null; 
			PreparedStatement pst = null;

			try {
				con = MySQLConexion8.getConexion();
				String sql = "update tb_productos set nomprod=? where cod_producto =?";
				
				pst = con.prepareStatement(sql);
				pst.setString(1, p.getNombre());
				pst.setInt(2, p.getCodigoProducto());

				rs = pst.executeUpdate();

			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Error en actualizar\n"+e.getMessage());
			}finally {
				try {
					con.close();

				} catch (SQLException e) {


					System.out.println("Error al cerrar: "+e.getMessage());

				}

			}

			return rs;
	}

	@Override
	public ArrayList<Cliente> listadoxProducto(int codigoProducto) {
		ArrayList<Cliente> lista = new ArrayList<Cliente>();

		
		/*!plantilla!!*/
		Connection con=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		
		try {
			con=MySQLConexion8.getConexion();
			String sql="select*from tb_cliente where id_atencion=?";
			
			pst=con.prepareStatement(sql);
			pst.setInt(1,codigoProducto);
			rs=pst.executeQuery();
			
			
		while(rs.next()) {
			
			lista.add(new Cliente(rs.getInt(1),rs.getString(2),
					rs.getString(3),rs.getString(4),rs.getString(5)));
			
		}
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error en eliminar\n"+e.getMessage());
		}finally {
			try {
				con.close();
			} catch (SQLException e) {
				System.out.println("Error al cerrar: "+e.getMessage());
			}
		}
		
		
		return lista;
		
	}

}
