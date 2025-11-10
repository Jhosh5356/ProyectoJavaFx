package co.edu.poli.actividad.modelo;

import java.io.Serializable;

import co.edu.poli.actividad.modelo.Certificacion;
import co.edu.poli.actividad.modelo.Coleccion;
import co.edu.poli.actividad.modelo.Conservacion;
import co.edu.poli.actividad.modelo.Especificacion;
import co.edu.poli.actividad.modelo.Pais;

public abstract class Moneda implements Serializable {
    
    // ========== ATRIBUTOS ==========
    private final String id;
    private double valor;  // Cambiado de 'peso' a 'valor' para claridad
    private Pais pais;
    private Especificacion especificacion;
    private Coleccion coleccion;
    private int annyo_creacion;
    private Conservacion conservacion;
    private Certificacion certificacion;
    private int precio_mercado;  // Valor comercial de la moneda
    
    // ========== CONSTRUCTORES ==========
    
    /**
     * Constructor completo
     */
    public Moneda(String id, double valor, Pais pais, Especificacion especificacion, 
                  Coleccion coleccion, int annyo_creacion, Conservacion conservacion, 
                  Certificacion certificacion, int precio_mercado) {
        this.id = id;
        this.valor = valor;
        this.pais = pais;
        this.especificacion = especificacion;
        this.coleccion = coleccion;
        this.annyo_creacion = annyo_creacion;
        this.conservacion = conservacion;
        this.certificacion = certificacion;
        this.precio_mercado = precio_mercado;
    }

    /**
     * Constructor simplificado (solo con ID)
     */
    public Moneda(String id) {
        this.id = id;
    }

    // ========== GETTERS Y SETTERS ==========
    
    public final String getId() {
        return id;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public Especificacion getEspecificacion() {
        return especificacion;
    }

    public void setEspecificacion(Especificacion especificacion) {
        this.especificacion = especificacion;
    }

    public Coleccion getColeccion() {
        return coleccion;
    }

    public void setColeccion(Coleccion coleccion) {
        this.coleccion = coleccion;
    }

    public int getAnnyo_creacion() {
        return annyo_creacion;
    }

    public void setAnnyo_creacion(int annyo_creacion) {
        this.annyo_creacion = annyo_creacion;
    }

    public Conservacion getConservacion() {
        return conservacion;
    }

    public void setConservacion(Conservacion conservacion) {
        this.conservacion = conservacion;
    }

    public Certificacion getCertificacion() {
        return certificacion;
    }

    public void setCertificacion(Certificacion certificacion) {
        this.certificacion = certificacion;
    }

    public int getPrecio_mercado() {
        return precio_mercado;
    }

    public void setPrecio_mercado(int precio_mercado) {
        this.precio_mercado = precio_mercado;
    }

    // ========== MÉTODOS ABSTRACTOS ==========
    
    /**
     * Muestra información específica de cada tipo de moneda
     */
    public abstract void mostrarInfo(String mensaje);

    /**
     * Devuelve información de la superclase
     */
    public abstract Object devolverSuper();

    // ========== MÉTODOS CONCRETOS ==========
    
    /**
     * Calcula la edad de la moneda
     * @param annyoReferencia año actual o de referencia
     * @return edad en años
     */
    public int determinarEdadActual(int annyoReferencia) {
        return annyoReferencia - annyo_creacion;
    }

    /**
     * Registra una moneda en el sistema
     * @return true si se registró exitosamente
     */
    public boolean registrarMoneda() {
        System.out.println("Registrando moneda: " + id);
        System.out.println("   Valor: " + valor);
        System.out.println("   País: " + (pais != null ? pais.getNombre() : "N/A"));
        System.out.println("   Año: " + annyo_creacion);
        return true;
    }

    // ========== TOSTRING ==========
    
    @Override
    public String toString() {
        return "««« Moneda »»»" +
               "\n ID: " + id +
               "\n Valor: " + valor +
               "\n País: " + pais +
               "\n Especificación: " + especificacion +
               "\n Colección: " + coleccion +
               "\n Año creación: " + annyo_creacion +
               "\n Conservación: " + conservacion +
               "\n Certificación: " + certificacion +
               "\n Precio mercado: " + precio_mercado + "\n";
    }

	public abstract Object getId_moneda();
}