package co.edu.poli.actividad.modelo;

import co.edu.poli.actividad.modelo.Moneda;

public class Tipo_divisa extends Moneda {

    private String nombre;
    private String codigoISO;
    private String simbolo;
    private String paisOrigen;
    private int cantidad;
    private String fechaExpedicion;
    private boolean vigente;
    private int valorComercial;

    public Tipo_divisa(String nombre, String codigoISO, String simbolo, String paisOrigen,
                       int cantidad, String fechaExpedicion, boolean vigente, int valorComercial) {

        super("GEN003"); // id GENERICO

        this.nombre = nombre;
        this.codigoISO = codigoISO;
        this.simbolo = simbolo;
        this.paisOrigen = paisOrigen;
        this.cantidad = cantidad;
        this.fechaExpedicion = fechaExpedicion;
        this.vigente = vigente;
        this.valorComercial = valorComercial;
    }

    public Tipo_divisa() {
        super("GEN003");
    }

    @Override
    public void mostrarInfo(String mensaje) {
        System.out.println("««« Tipo de Divisa »»»" +
                "\n Nombre: " + nombre +
                "\n Código ISO: " + codigoISO +
                "\n Símbolo: " + simbolo +
                "\n País Origen: " + paisOrigen +
                "\n Cantidad: " + cantidad +
                "\n Fecha de Expedición: " + fechaExpedicion +
                "\n Vigente: " + vigente +
                "\n Valor Comercial: " + valorComercial +
                "\n toString: " + super.toString() + "\n");
    }

    @Override
    public Object devolverSuper() {
        return this;
    }

	@Override
	public Object getId_moneda() {
		// TODO Auto-generated method stub
		return null;
	}
}
