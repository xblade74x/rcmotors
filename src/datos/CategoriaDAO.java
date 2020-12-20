
package datos;

import java.sql.PreparedStatement;
import database.Conexion;
import datos.interfaces.CrudSimpleInterface;
import entidades.Servicio;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
/**
 *
 * @author MIGUEL
 */
public class CategoriaDAO implements CrudSimpleInterface<Servicio> {
    private final Conexion CON;  //instanciar clase conexion
    private PreparedStatement ps;  //compilar sentencia sql y enviarlo al motor mysql
    private ResultSet rs;  //permite almacenar el resultado al ejecutar un select
    private boolean resp;  // objetotipo respuesta
    
    public CategoriaDAO(){
        CON=Conexion.getInstancia();
    }

    @Override
    public List<Servicio> listar(String texto) {
        List<Servicio> registros=new ArrayList();
        try{
            ps=CON.conectar().prepareStatement("SELECT * FROM servicio WHERE nombre LIKE ?");
            ps.setString(1,"%" + texto +"%");
            rs=ps.executeQuery();
            while(rs.next()){
                registros.add(new Servicio(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDouble(4),rs.getBoolean(5)));
            }
            ps.close();
            rs.close();
            
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        } finally{
            ps=null;
            rs=null;
            CON.desconectar();
        }
        return registros;
    }

    @Override
    public boolean insertar(Servicio obj) {
    resp=false;
    try{
        ps=CON.conectar().prepareStatement("INSERT INTO servicio (codigo,nombre,precio,activo) VALUE (?,?,?,1)");
        ps.setString(1, obj.getCodigo());
        ps.setString(2, obj.getNombre());
        ps.setDouble(3, obj.getPrecio());
        
        if (ps.executeUpdate()>0){
            resp=true;
        }
        ps.close();
    } catch (SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        } finally{
            ps=null;
            CON.desconectar();
        }
        return resp;
    }

    @Override
    public boolean actualizar(Servicio obj) {
        resp=false;
    try{
        ps=CON.conectar().prepareStatement("UPDATE servicio SET codigo=?,nombre=?,precio=? WHERE idservicio=? ");
        ps.setString(1, obj.getCodigo());
        ps.setString(2, obj.getNombre());
        ps.setDouble(3, obj.getPrecio());
        ps.setInt(4, obj.getIdServicio());
        if (ps.executeUpdate()>0){
            resp=true;
        }
        ps.close();
    } catch (SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        } finally{
            ps=null;
            CON.desconectar();
        }
        return resp;
    }

    @Override
    public boolean desactivar(int idservicio) {
        resp=false;
    try{
        ps=CON.conectar().prepareStatement("UPDATE servicio SET activo=0 WHERE idservicio=? ");
        ps.setInt(1, idservicio);
        if (ps.executeUpdate()>0){
            resp=true;
        }
        ps.close();
    } catch (SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        } finally{
            ps=null;
            CON.desconectar();
        }
        return resp;
    }

    @Override
    public boolean activar(int idservicio) {
        resp=false;
    try{
        ps=CON.conectar().prepareStatement("UPDATE servicio SET activo=1 WHERE idservicio=? ");
        ps.setInt(1, idservicio);
        if (ps.executeUpdate()>0){
            resp=true;
        }
        ps.close();
    } catch (SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        } finally{
            ps=null;
            CON.desconectar();
        }
        return resp;
    }

    @Override
    public int total() {
        int totalRegistros=0;
        
    try{
        ps=CON.conectar().prepareStatement("SELECT COUNT(idservicio) FROM servicio");
        rs=ps.executeQuery();
        
        while(rs.next()){
            totalRegistros=rs.getInt("COUNT(idservicio)");
        }
        
        ps.close();
        rs.close();
    } catch (SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        } finally{
            ps=null;
            rs=null;
            CON.desconectar();
        }
        return totalRegistros;
    }

    @Override
    public boolean existe(String texto) {
        resp=false;
        
    try{
        ps=CON.conectar().prepareStatement("SELECT nombre FROM servicio WHERE nombre=?");
        ps.setString(1, texto);
        rs=ps.executeQuery();
        rs.last();
        if (rs.getRow()>0){
            resp=true;
        }
        
        ps.close();
        rs.close();
    } catch (SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        } finally{
            ps=null;
            rs=null;
            CON.desconectar();
        }
        return resp;
    }
    
}
