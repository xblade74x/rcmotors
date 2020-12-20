
package datos;

import java.sql.PreparedStatement;
import database.Conexion;
import datos.interfaces.CrudSimpleInterface;
import entidades.Repuesto;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
/**
 *
 * @author MIGUEL
 */
public class RepuestoDAO implements CrudSimpleInterface<Repuesto> {
    private final Conexion CON;  //instanciar clase conexion
    private PreparedStatement ps;  //compilar sentencia sql y enviarlo al motor mysql
    private ResultSet rs;  //permite almacenar el resultado al ejecutar un select
    private boolean resp;  // objetotipo respuesta
    
    public RepuestoDAO(){
        CON=Conexion.getInstancia();
    }

    @Override
    public List<Repuesto> listar(String texto) {
        List<Repuesto> registros=new ArrayList();
        try{
            ps=CON.conectar().prepareStatement("SELECT * FROM repuesto WHERE nombre LIKE ?");
            ps.setString(1,"%" + texto +"%");
            rs=ps.executeQuery();
            while(rs.next()){
                registros.add(new Repuesto(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDouble(4),rs.getInt(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getBoolean(10)));
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
    public boolean insertar(Repuesto obj) {
    resp=false;
    try{
        ps=CON.conectar().prepareStatement("INSERT INTO repuesto (codigo,nombre,precioVenta,stock,especificacion,clasificacion,imagen,observacion,activo) VALUE (?,?,?,?,?,?,?,?,1)");
        ps.setString(1, obj.getCodigo());
        ps.setString(2, obj.getNombre());
        ps.setDouble(3, obj.getPrecioventa());
        ps.setInt(4, obj.getStock());
        ps.setString(5, obj.getEspecificacion());
        ps.setString(6, obj.getClasificacion());
        ps.setString(7, obj.getImagen());
        ps.setString(8, obj.getObservacion());
        
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
    public boolean actualizar(Repuesto obj) {
        resp=false;
    try{
        ps=CON.conectar().prepareStatement("UPDATE repuesto SET codigo=?,nombre=?,precioVenta=?,stock=?,especificacion=?,clasificacion=?,imagen=?,observacion=? WHERE idrepuesto=? ");
        ps.setString(1, obj.getCodigo());
        ps.setString(2, obj.getNombre());
        ps.setDouble(3, obj.getPrecioventa());
        ps.setInt(4, obj.getStock());
        ps.setString(5, obj.getEspecificacion());
        ps.setString(6, obj.getClasificacion());
        ps.setString(7, obj.getImagen());
        ps.setString(8, obj.getObservacion());
        ps.setInt(9, obj.getIdrepuesto());
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
    public boolean desactivar(int idrepuesto) {
        resp=false;
    try{
        ps=CON.conectar().prepareStatement("UPDATE repuesto SET activo=0 WHERE idrepuesto=? ");
        ps.setInt(1, idrepuesto);
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
    public boolean activar(int idrepuesto) {
        resp=false;
    try{
        ps=CON.conectar().prepareStatement("UPDATE repuesto SET activo=1 WHERE idrepuesto=? ");
        ps.setInt(1, idrepuesto);
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
        ps=CON.conectar().prepareStatement("SELECT COUNT(idrepuesto) FROM repuesto");
        rs=ps.executeQuery();
        
        while(rs.next()){
            totalRegistros=rs.getInt("COUNT(idrepuesto)");
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
        ps=CON.conectar().prepareStatement("SELECT nombre FROM repuesto WHERE nombre=?");
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
