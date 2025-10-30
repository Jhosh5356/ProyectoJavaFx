package co.edu.poli.actividad9.model;

public class MonedaGenerica extends Moneda {
    public MonedaGenerica(String id, double peso, Pais pais, Especificacion especificacion, Coleccion coleccion,
            int annyo_creacion, Conservacion conservacion, Certificacion certificacion, int valor) {
        super(id, peso, pais, especificacion, coleccion, annyo_creacion, conservacion, certificacion, valor);
    }

    @Override
    public void mostrarInfo(String mensaje) {
        System.out.println("Moneda genérica - " + mensaje);
    }

    @Override
    public Object devolverSuper() {
        return this;
    }
}
