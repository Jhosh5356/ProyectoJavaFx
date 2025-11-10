package co.edu.poli.actividad.modelo;


/**
 * 
 */
public class Certificacion {

	
    public Certificacion(String id_registro, int denominacion, String pais, String descripcion, String entidad) {
		super();
		this.id_registro = id_registro;
		this.denominacion = denominacion;
		this.pais = pais;
		this.descripcion = descripcion;
		this.entidad = entidad;
	}
	

	public String getId_registro() {
		return id_registro;
	}



	public void setId_registro(String id_registro) {
		this.id_registro = id_registro;
	}



	public int getDenominacion() {
		return denominacion;
	}



	public void setDenominacion(int denominacion) {
		this.denominacion = denominacion;
	}



	public String getPais() {
		return pais;
	}



	public void setPais(String pais) {
		this.pais = pais;
	}



	public String getDescripcion() {
		return descripcion;
	}



	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}



	public String getEntidad() {
		return entidad;
	}



	public void setEntidad(String entidad) {
		this.entidad = entidad;
	}

	@Override
	public String toString() {
		return "««« Certificacion »»»" +  
				"\n id_registro: " + id_registro + 
				"\n denominacion: " + denominacion + 
				"\n pais: " + pais + 
				"\n descripcion: " + descripcion +
				"\n entidad: "+ entidad + "\n" + "\n";

	}

	/**
     * 
     */
    private String id_registro;

    /**
     * 
     */
    private int denominacion;

    /**
     * 
     */
    private String pais;

    /**
     * 
     */
    private String descripcion ;

    /**
     * 
     */
    private String entidad;


    /**
     * 
     */
    private void validar_certificacion() {
        // TODO implement here
    }


	public Object getEntidad_certificadora() {
		// TODO Auto-generated method stub
		return null;
	}

}