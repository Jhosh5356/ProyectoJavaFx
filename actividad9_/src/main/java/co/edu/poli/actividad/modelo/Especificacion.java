package co.edu.poli.actividad.modelo;

public class Especificacion {


	
    public Especificacion(String id_especificacion, double peso, String material, double diametro, String acabado) {
		super();
		this.id_especificacion = id_especificacion;
		this.peso = peso;
		this.material = material;
		this.diametro = diametro;
		this.acabado = acabado;
	}

	
	public String getId_especificacion() {
		return id_especificacion;
	}


	public void setId_especificacion(String id_especificacion) {
		this.id_especificacion = id_especificacion;
	}


	public double getPeso() {
		return peso;
	}


	public void setPeso(double peso) {
		this.peso = peso;
	}


	public String getMaterial() {
		return material;
	}


	public void setMaterial(String material) {
		this.material = material;
	}


	public double getDiametro() {
		return diametro;
	}


	public void setDiametro(double diametro) {
		this.diametro = diametro;
	}


	public String getAcabado() {
		return acabado;
	}


	public void setAcabado(String acabado) {
		this.acabado = acabado;
	}


	@Override
	public String toString() {
		return "««« Especificacion »»»" + 
				"\n id_especificacion: "+ id_especificacion + 
				"\n peso: " + peso + 
				"\n material: " + material + 
				"\n diametro: " + diametro + 
				"\n acabado: " + acabado + "\n" + "\n";

	}

	/**
     * 
     */
    private String id_especificacion;

    /**
     * 
     */
    private double peso;

    /**
     * 
     */
    private String material;

    /**
     * 
     */
    private double diametro;

    /**
     * 
     */
    private String acabado;


}