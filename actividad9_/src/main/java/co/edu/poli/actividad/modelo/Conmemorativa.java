package co.edu.poli.actividad.modelo;

import co.edu.poli.actividad.modelo.Certificacion;
import co.edu.poli.actividad.modelo.Coleccion;
import co.edu.poli.actividad.modelo.Conservacion;
import co.edu.poli.actividad.modelo.Especificacion;
import co.edu.poli.actividad.modelo.Moneda;
import co.edu.poli.actividad.modelo.Pais;

public class Conmemorativa extends Moneda {

    private String evento;
    
    private int annyoConmemoracion;

    public Conmemorativa(String id, double peso, Pais pais, Especificacion especificacion, Coleccion coleccion,
                         int annyo_creacion, Conservacion conservacion, Certificacion certificacion, int valor,
                         String evento, int anioEvento) {

        super(id, peso, pais, especificacion, coleccion, annyo_creacion, conservacion, certificacion, valor);

        this.evento = evento;
        this.annyoConmemoracion = anioEvento;
    }

    public Conmemorativa() {
        super("GEN002");
    }

    public String getEvento() {
        return evento;
    }

    public void setEvento(String evento) {
        this.evento = evento;
    }

    public int getAnnyoConmemoracion() {
        return annyoConmemoracion;
    }

    public void setAnnyoConmemoracion(int string) {
        this.annyoConmemoracion = string;
    }

    @Override
    public void mostrarInfo(String mensaje) {
        System.out.println("««« Conmemorativa »»»" +
                "\n Evento: " + evento +
                "\n Año Conmemoración: " + annyoConmemoracion +
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

	public void setMotivo_conmemoracion(String trim) {
		// TODO Auto-generated method stub
		
	}

	public void setanioEvento(int int1) {
		// TODO Auto-generated method stub
		
	}

	public String getMotivo_conmemoracion() {
		// TODO Auto-generated method stub
		return null;
	}

	public char[] getAnnio_evento() {
		// TODO Auto-generated method stub
		return null;
	}
}
