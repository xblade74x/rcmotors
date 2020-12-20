
package negocio;

import datos.CategoriaDAO;
import entidades.Servicio;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author MIGUEL
 */
public class ServicioControl {
    private final CategoriaDAO DATOS;
    private Servicio obj;
    private DefaultTableModel modeloTabla;
    public int registrosMostrados;
    
    public ServicioControl(){
        this.DATOS = new CategoriaDAO();
        this.obj=new Servicio();
        this.registrosMostrados=0;
        
    }
    
    public DefaultTableModel listar(String texto){
        List<Servicio> lista=new ArrayList();
        lista.addAll(DATOS.listar(texto));
        
        String[] titulos={"Id","Codigo","Nombre","Precio","Estado"}; //Vector titulos
        this.modeloTabla=new DefaultTableModel (null,titulos);
        
        String estado;
        String []registro=new String[5];
        
        this.registrosMostrados=0;
        for(Servicio item:lista){
            if (item.isActivo()){
                estado="Activo";
            } else{
                estado="Inactivo";
            }
            registro[0]=Integer.toString(item.getIdServicio());
            registro[1]=item.getCodigo();
            registro[2]=item.getNombre();
            registro[3]=Double.toString(item.getPrecio());
            registro[4]=estado;
            this.modeloTabla.addRow(registro);
            this.registrosMostrados=this.registrosMostrados+1;
            
        }
        return this.modeloTabla;
    }
    
    public String insertar(String codigo, String nombre, Double precio){
        if (DATOS.existe(nombre)){
            return "El registro ya existe";
        }else{
            obj.setCodigo(codigo);
            obj.setNombre(nombre);
            obj.setPrecio(precio);
            if (DATOS.insertar(obj)){
                return "OK";
            }else{
                return "Error en el registro";
            }
        }
    }
    
    public String actualizar (int id, String codigo, String nombre, Double precio, String nombreAnt){
        if(nombre.equals(nombreAnt)){
            obj.setIdServicio(id);
            obj.setCodigo(codigo);
            obj.setNombre(nombre);
            obj.setPrecio(precio);
            if(DATOS.actualizar(obj)){
                return "OK";
            }else{
                return "Error en la actualización.";
            }
        }else{
            if (DATOS.existe(nombre)){
                return "El registro ya existe";
            }else{
                obj.setIdServicio(id);
                obj.setCodigo(codigo);
                obj.setNombre(nombre);
                obj.setPrecio(precio);
                if(DATOS.actualizar(obj)){
                    return "OK";
                }else{
                    return "Error en la actualización";
                }
            }
        }
    }
    
    public String desactivar(int id){
        if (DATOS.desactivar(id)){
            return "OK";
        }else{
            return "No se puede desactivar el registro";
        }
    }
    
     public String activar(int id){
        if (DATOS.activar(id)){
            return "OK";
        }else{
            return "No se puede activar el registro";
        }
    }
     
     public int total(){
         return DATOS.total();
     }
     
     public int totalMostrados(){
         return this.registrosMostrados;
     }
}
