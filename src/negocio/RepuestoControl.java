
package negocio;

import datos.CategoriaDAO;
import datos.RepuestoDAO;
import entidades.Repuesto;
import entidades.Servicio;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author MIGUEL
 */
public class RepuestoControl {
    private final RepuestoDAO DATOS;
    private Repuesto obj;
    private DefaultTableModel modeloTabla;
    public int registrosMostrados;
    
    public RepuestoControl(){
        this.DATOS = new RepuestoDAO();
        this.obj=new Repuesto();
        this.registrosMostrados=0;
        
    }
    
    public DefaultTableModel listar(String texto){
        List<Repuesto> lista=new ArrayList();
        lista.addAll(DATOS.listar(texto));
        
        String[] titulos={"Id","Codigo","Nombre","Precio","Stock","Especificacion","Clasificacion","Imagen","Observacion","Estado"}; //Vector titulos
        this.modeloTabla=new DefaultTableModel (null,titulos);
        
        String estado;
        String []registro=new String[10];
        
        this.registrosMostrados=0;
        for(Repuesto item:lista){
            if (item.isActivo()){
                estado="Activo";
            } else{
                estado="Inactivo";
            }
            registro[0]=Integer.toString(item.getIdrepuesto());
            registro[1]=item.getCodigo();
            registro[2]=item.getNombre();
            registro[3]=Double.toString(item.getPrecioventa());
            registro[4]=Integer.toString(item.getStock());
            registro[5]=item.getEspecificacion();
            registro[6]=item.getClasificacion();
            registro[7]=item.getImagen();
            registro[8]=item.getObservacion();
            registro[9]=estado;
            this.modeloTabla.addRow(registro);
            this.registrosMostrados=this.registrosMostrados+1;
            
        }
        return this.modeloTabla;
    }
    
    public String insertar(String codigo, String nombre, Double precioventa, int stock, String especificacion, String clasificacion, String imagen, String observacion){
        if (DATOS.existe(nombre)){
            return "El registro ya existe";
        }else{
            obj.setCodigo(codigo);
            obj.setNombre(nombre);
            obj.setPrecioventa(precioventa);
            obj.setStock(stock);
            obj.setEspecificacion(especificacion);
            obj.setClasificacion(clasificacion);
            obj.setImagen(imagen);
            obj.setObservacion(observacion);
            if (DATOS.insertar(obj)){
                return "OK";
            }else{
                return "Error en el registro";
            }
        }
    }
    
    public String actualizar (int id, String codigo, String nombre, Double precioventa, int stock, String especificacion, String clasificacion, String imagen, String observacion, String nombreAnt){
        if(nombre.equals(nombreAnt)){
            obj.setIdrepuesto(id);
            obj.setCodigo(codigo);
            obj.setNombre(nombre);
            obj.setPrecioventa(precioventa);
            obj.setStock(stock);
            obj.setEspecificacion(especificacion);
            obj.setClasificacion(clasificacion);
            obj.setImagen(imagen);
            obj.setObservacion(observacion);
            if(DATOS.actualizar(obj)){
                return "OK";
            }else{
                return "Error en la actualización.";
            }
        }else{
            if (DATOS.existe(nombre)){
                return "El registro ya existe";
            }else{
                obj.setIdrepuesto(id);
                obj.setCodigo(codigo);
                obj.setNombre(nombre);
                obj.setPrecioventa(precioventa);
                obj.setStock(stock);
                obj.setEspecificacion(especificacion);
                obj.setClasificacion(clasificacion);
                obj.setImagen(imagen);
                obj.setObservacion(observacion);
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
