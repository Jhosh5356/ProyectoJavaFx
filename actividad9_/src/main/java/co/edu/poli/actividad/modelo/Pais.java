package co.edu.poli.actividad.modelo;

public class Pais {
	
	public Pais(String id_pais, String continente, String idioma, String divisa, String nombre) {
		super();
		this.id_pais = id_pais;
		this.continente = continente;
		this.idioma = idioma;
		this.divisa = divisa;
		this.nombre = nombre;
	}

	public String getId_pais() {
		return id_pais;
	}

	public void setId_pais(String id_pais) {
		this.id_pais = id_pais;
	}

	public String getContinente() {
		return continente;
	}

	public void setContinente(String continente) {
		this.continente = continente;
	}

	public String getIdioma() {
		return idioma;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

	public String getDivisa() {
		return divisa;
	}

	public void setDivisa(String divisa) {
		this.divisa = divisa;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public Pais() {
    }

	@Override
	public String toString() {
		return 
		"««« Pais »»»" + 
		"\n id: "+ id_pais + 
		"\n continente: " + continente + 
		"\n idioma: " + idioma +
		"\n divisa: " + divisa +
		"\n nombre: " + nombre +
		"\n getId_pais(): " + getId_pais() +
		"\n " + "\n";
	}
	
    /**
     * 
     */
    private String id_pais;

    /**
     * 
     */
    private String continente;

    /**
     * 
     */
    private String idioma;

    /**
     * 
     */
    private String divisa;

    /**
     * 
     */
    private String nombre;


}