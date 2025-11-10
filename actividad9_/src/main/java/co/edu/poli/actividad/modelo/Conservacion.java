package co.edu.poli.actividad.modelo;

public class Conservacion {


    public Conservacion(String id_conservacion, boolean comun, boolean raro, String descripcion, String grado,
			String observaciones) {
		super();
		this.id_conservacion = id_conservacion;
		this.comun = comun;
		this.raro = raro;
		this.descripcion = descripcion;
		this.grado = grado;
		this.observaciones = observaciones;
	}


	public String getId_conservacion() {
		return id_conservacion;
	}
 
	public void setId_conservacion(String id_conservacion) {
		this.id_conservacion = id_conservacion;
	}


	public boolean isComun() {
		return comun;
	}


	public void setComun(boolean comun) {
		this.comun = comun;
	}


	public boolean isRaro() {
		return raro;
	}


	public void setRaro(boolean raro) {
		this.raro = raro;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public String getGrado() {
		return grado;
	}


	public void setGrado(String grado) {
		this.grado = grado;
	}


	public String getObservaciones() {
		return observaciones;
	}


	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}


	@Override
	public String toString() {
		return "««« Conservacion »»»" + 
				"\n id_conservacion: " + id_conservacion + 
				"\n comun: " + comun + 
				"\n raro: " + raro +
				"\n descripcion: " + descripcion + 
				"\n grado: " + grado + 
				"\n observaciones: " + observaciones + "\n" + "\n";
	}
	/**
     * 
     */
    private String id_conservacion;

    /**
     * 
     */
    private boolean comun;

    /**
     * 
     */
    private boolean raro;

    /**
     * 
     */
    private String descripcion;

    /**
     * 
     */
    private String grado;

    /**
     * 
     */
    private String observaciones;


    /**
     * 
     */
    private void estudiar_conservacion() {
        // TODO implement here
    }


	public void setEstado_conservacion(String value) {
		// TODO Auto-generated method stub
		
	}


	public String getEstado_conservacion() {
		// TODO Auto-generated method stub
		return null;
	}


	public boolean isLimpiada() {
		// TODO Auto-generated method stub
		return false;
	}

}
