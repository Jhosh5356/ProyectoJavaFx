package co.edu.poli.actividad.servicios;


import co.edu.poli.actividad.modelo.Moneda;
import java.io.*;
import java.util.ArrayList;

/**
 * Clase que maneja la lectura y escritura de objetos Moneda en un archivo.
 */
public class OperacionArchivo {

    // Nombre del archivo donde se guardan las monedas
    private static final String NOMBRE_ARCHIVO = "monedas.dat";

    /**
     * Guarda (serializa) la lista de monedas en un archivo binario.
     * @param lista Lista de monedas a guardar
     */
    public static void guardarArchivo(ArrayList<Moneda> lista) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(NOMBRE_ARCHIVO))) {
            oos.writeObject(lista);
            System.out.println("✅ Archivo guardado exitosamente en: " + NOMBRE_ARCHIVO);
        } catch (IOException e) {
            System.out.println("❌ Error al guardar el archivo: " + e.getMessage());
        }
    }

    /**
     * Carga (deserializa) la lista de monedas desde el archivo.
     * @return Lista de monedas cargada. Si hay error, retorna una lista vacía.
     */
    @SuppressWarnings("unchecked")
    public static ArrayList<Moneda> cargarArchivo() {
        ArrayList<Moneda> lista = new ArrayList<>();

        File archivo = new File(NOMBRE_ARCHIVO);
        if (!archivo.exists()) {
            System.out.println("⚠️ No se encontró el archivo de datos. Se creará uno nuevo al guardar.");
            return lista;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(NOMBRE_ARCHIVO))) {
            lista = (ArrayList<Moneda>) ois.readObject();
            System.out.println("✅ Archivo cargado correctamente. " + lista.size() + " monedas encontradas.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("❌ Error al cargar el archivo: " + e.getMessage());
        }

        return lista;
    }
}
