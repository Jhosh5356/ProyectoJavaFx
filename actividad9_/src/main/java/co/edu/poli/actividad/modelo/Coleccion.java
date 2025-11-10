package co.edu.poli.actividad.modelo;
public class Coleccion {

	
    public Coleccion(String id_coleccion, String nombre, String fecha_inicio, String tematica) {
		super();
		this.id_coleccion = id_coleccion;
		this.nombre = nombre;
		this.fecha_inicio = fecha_inicio;
		this.tematica = tematica;
	}


	public String getId_coleccion() {
		return id_coleccion;
	}



	public void setId_coleccion(String id_coleccion) {
		this.id_coleccion = id_coleccion;
	}



	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public String getFecha_inicio() {
		return fecha_inicio;
	}



	public void setFecha_inicio(String fecha_inicio) {
		this.fecha_inicio = fecha_inicio;
	}



	public String getTematica() {
		return tematica;
	}



	public void setTematica(String tematica) {
		this.tematica = tematica;
	}


	@Override
	public String toString() {
		return 
		"««« Coleccion »»»" +  
		"\n id_coleccion: " + id_coleccion + 
		"\n nombre: " + nombre + 
		"\n fecha inicio: " + fecha_inicio + 
		"\n tematica: " + tematica +
		"\n getId_coleccion: "+ getId_coleccion() +
		"\n getNombre(): "+ getNombre() +
		"\n getFecha_inicio():" + getFecha_inicio() +
		"\n getTematica():" + getTematica() + "\n" + "\n";
	}
	/**
     * 
     */
    private String id_coleccion;

    /**
     * 
     */
    private String nombre;

    /**
     * 
     */
    private String fecha_inicio;

    /**
     * 
     */
    private String tematica;

	public Object getNombre_coleccion() {
		// TODO Auto-generated method stub
		return null;
	}



}