package co.edu.poli.actividad9.model;

public class Antigua extends Moneda {

    private String epoca;
    
    private int valor_historico;
//gjgfud
    // CONSTRUCTOR
    public Antigua(String id, Pais pais, Especificacion especificacion, Coleccion coleccion, int annyo_creacion,
                   Conservacion conservacion, Certificacion certificacion, int valor,
                   String epoca, int valor_historico) {

        super(id, 0.0, pais, especificacion, coleccion, annyo_creacion, conservacion, certificacion, valor);

        this.epoca = epoca;
        this.valor_historico = valor_historico;
    }
    
    // CONSTRUCTOR VACIO
    public Antigua() {
        super("GEN001");
    }

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
                "\n epoca: " + epoca +
                "\n valor_historico: " + valor_historico +
                "\n toString: " + super.toString() + "\n");
    }

    @Override
    public Object devolverSuper() {
        return this;
    }
}

