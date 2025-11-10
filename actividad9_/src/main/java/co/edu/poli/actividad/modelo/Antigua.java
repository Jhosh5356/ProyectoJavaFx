package co.edu.poli.actividad.modelo;

import co.edu.poli.actividad.modelo.Certificacion;
import co.edu.poli.actividad.modelo.Coleccion;
import co.edu.poli.actividad.modelo.Conservacion;
import co.edu.poli.actividad.modelo.Especificacion;
import co.edu.poli.actividad.modelo.Moneda;
import co.edu.poli.actividad.modelo.Pais;

public class Antigua extends Moneda {

    private String epoca;
    private int valor_historico;

    // CONSTRUCTOR
    public Antigua(String id, double valorNominal, Pais pais, Especificacion especificacion, 
                   Coleccion coleccion, int annyo_creacion, Conservacion conservacion, 
                   Certificacion certificacion, int valor, String epoca, int valor_historico) {
        
        super(id, valorNominal, pais, especificacion, coleccion, annyo_creacion, 
              conservacion, certificacion, valor);
        this.epoca = epoca;
        this.valor_historico = valor_historico;
    }

    // CONSTRUCTOR VACIO
    public Antigua() {
        super("ANT001");
    }

    // Getters y Setters
    public String getEpoca() {
        return epoca;
    }

    public void setEpoca(String epoca) {
        this.epoca = epoca;
    }

    public int getValor_historico() {
        return valor_historico;
    }

    public void setValor_historico(int valor_historico) {
        this.valor_historico = valor_historico;
    }

    @Override
    public void mostrarInfo(String mensaje) {
        System.out.println("««« Antigua »»»" +
                "\n Época: " + epoca +
                "\n Valor histórico: " + valor_historico +
                "\n " + super.toString() + "\n");
    }

    @Override
    public Object devolverSuper() {
        return super.toString(); // o según lo que necesites devolver
    }

	@Override
	public Object getId_moneda() {
		// TODO Auto-generated method stub
		return null;
	}
}