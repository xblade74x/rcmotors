
package entidades;

/**
 *
 * @author MIGUEL
 */
public class Servicio {
    private int idServicio;
    private String codigo;
    private String nombre;
    private Double precio;
    private boolean activo;
    
    //constructores

    public Servicio() {
    }

    public Servicio(int idServicio, String codigo, String nombre, Double precio, boolean activo) {
        this.idServicio = idServicio;
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.activo = activo;
    }
    //setter y getter

    public int getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(int idServicio) {
        this.idServicio = idServicio;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    //metodo toString

    @Override
    public String toString() {
        return "Servicio{" + "idServicio=" + idServicio + ", codigo=" + codigo + ", nombre=" + nombre + ", precio=" + precio + ", activo=" + activo + '}';
    }

    
    
}
