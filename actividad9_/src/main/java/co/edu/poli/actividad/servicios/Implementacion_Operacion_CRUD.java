package co.edu.poli.actividad.servicios;

import java.util.Scanner;
import co.edu.poli.actividad.modelo.Moneda;
import java.io.*; 
import co.edu.poli.actividad.modelo.Pais;


/**
 * Implementación de la interfaz Operacion_CRUD.
 * Utiliza un arreglo para almacenar objetos de tipo Moneda.
 *
 * La operación create inserta en la primera posición null disponible,
 * y se puede expandir dinámicamente para permitir más objetos.
 *
 * @author Jhosayde
 * 
 */

public class Implementacion_Operacion_CRUD implements Operacion_CRUD {

    private Moneda[] arregloMonedas;
    private int contador;
    private final int MAX_TAMANIO = 100;
    private final String RUTA_ARCHIVO = "monedas.dat";

    public Implementacion_Operacion_CRUD() {
        arregloMonedas = new Moneda[MAX_TAMANIO];
        contador = 0;
    }

    @Override
    public void crear(Moneda moneda) {
        if (contador < MAX_TAMANIO) {
            arregloMonedas[contador] = moneda;
            contador++;
        } else {
            System.out.println("❌ No se pueden agregar más monedas. Capacidad máxima alcanzada.");
        }
    }

    @Override
    public Moneda leer(String id) {
        boolean encontrado = false;
        for (int i = 0; i < contador; i++) {
            if (arregloMonedas[i] != null && arregloMonedas[i].getId().equalsIgnoreCase(id)) {
                System.out.println(arregloMonedas[i]);
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            System.out.println("❌ No se encontró ninguna moneda con el ID: " + id);
        }
		return null;
    }

    @Override
    public void actualizar(String id, Scanner sc) {
        for (Moneda m : arregloMonedas) {
            if (m != null && m.getId().equals(id)) {
                System.out.println("Ingrese nuevo valor de la moneda: ");
                double nuevoValor = sc.nextDouble();
                sc.nextLine(); // limpiar buffer

                System.out.println("Ingrese nuevo país de la moneda: ");
                String nuevoPais = sc.nextLine();

                m.setValor(nuevoValor);
                m.setPais(new Pais(nuevoPais, nuevoPais, nuevoPais, nuevoPais, nuevoPais)); // según tu clase Moneda

                System.out.println("Moneda actualizada correctamente (vía Scanner).");
                return;
            }
        }
        System.out.println("No se encontró la moneda con el ID indicado.");
    }

    @Override
    public void actualizar(String id, Moneda actualizada) {
        for (Moneda m : arregloMonedas) {
            if (m != null && m.getId().equals(id)) {
                m.setValor(actualizada.getValor());
                m.setPais(actualizada.getPais());
                System.out.println("Moneda actualizada correctamente (vía objeto Moneda).");
                return;
            }
        }
        System.out.println("No se encontró la moneda con el ID indicado.");
    }



    @Override
    public void eliminar(String id) {
        boolean encontrado = false;
        for (int i = 0; i < contador; i++) {
            if (arregloMonedas[i] != null && arregloMonedas[i].getId().equalsIgnoreCase(id)) {
                for (int j = i; j < contador - 1; j++) {
                    arregloMonedas[j] = arregloMonedas[j + 1];
                }
                arregloMonedas[contador - 1] = null;
                contador--;
                System.out.println("✅ Moneda eliminada correctamente.");
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            System.out.println("❌ No se encontró ninguna moneda con el ID: " + id);
        }
    }

    @Override
    public void listar() {
        if (contador == 0) {
            System.out.println("No hay monedas registradas.");
        } else {
            System.out.println("=== LISTA DE MONEDAS REGISTRADAS ===");
            for (int i = 0; i < contador; i++) {
                System.out.println((i + 1) + ". " + arregloMonedas[i]);
            }
        }
    }

    @Override
    public void serializar() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(RUTA_ARCHIVO))) {
            oos.writeObject(arregloMonedas);
            oos.writeInt(contador);
            System.out.println("💾 Datos guardados correctamente en archivo.");
        } catch (IOException e) {
            System.out.println("⚠️ Error al guardar los datos: " + e.getMessage());
        }
    }

    @Override
    public void deserializar() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(RUTA_ARCHIVO))) {
            arregloMonedas = (Moneda[]) ois.readObject();
            contador = ois.readInt();
            System.out.println("📂 Datos cargados correctamente desde archivo.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("⚠️ Error al cargar los datos: " + e.getMessage());
        }
    }
}