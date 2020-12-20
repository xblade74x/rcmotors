/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

/**
 *
 * @author MIGUEL
 */
public class Repuesto {
    private int idrepuesto;
    private String codigo;
    private String nombre;
    private Double precioventa;
    private int stock;
    private String especificacion;
    private String clasificacion;
    private String imagen;
    private String observacion;
    private boolean activo;

    public Repuesto() {
    }

    public Repuesto(int idrepuesto, String codigo, String nombre, Double precioventa, int stock, String especificacion, String clasificacion, String imagen, String observacion, boolean activo) {
        this.idrepuesto = idrepuesto;
        this.codigo = codigo;
        this.nombre = nombre;
        this.precioventa = precioventa;
        this.stock = stock;
        this.especificacion = especificacion;
        this.clasificacion = clasificacion;
        this.imagen = imagen;
        this.observacion = observacion;
        this.activo = activo;
    }

    public int getIdrepuesto() {
        return idrepuesto;
    }

    public void setIdrepuesto(int idrepuesto) {
        this.idrepuesto = idrepuesto;
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

    public Double getPrecioventa() {
        return precioventa;
    }

    public void setPrecioventa(Double precioventa) {
        this.precioventa = precioventa;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getEspecificacion() {
        return especificacion;
    }

    public void setEspecificacion(String especificacion) {
        this.especificacion = especificacion;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        return "Repuesto{" + "idrepuesto=" + idrepuesto + ", codigo=" + codigo + ", nombre=" + nombre + ", precioventa=" + precioventa + ", stock=" + stock + ", especificacion=" + especificacion + ", clasificacion=" + clasificacion + ", imagen=" + imagen + ", observacion=" + observacion + ", activo=" + activo + '}';
    }
    
    
    
}
