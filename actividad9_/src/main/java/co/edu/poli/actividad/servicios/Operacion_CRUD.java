package co.edu.poli.actividad.servicios;

import co.edu.poli.actividad.modelo.Moneda;

import java.util.Scanner;

/**
 * Interfaz que define las operaciones CRUD para objetos de tipo Moneda.
 * 
 * CRUD = Create, Read, Update, Delete.
 * Cada operación se debe implementar en clases que usen esta interfaz.
 * 
 *
 * @author Jhosayde
 */
public interface Operacion_CRUD {

    /**
     * Crea una nueva moneda en el repositorio.
     * @param moneda Objeto de tipo Moneda a registrar.
     */
    void crear(Moneda moneda);

    /**
     * Consulta una moneda por su identificador único.
     * @param id Identificador de la moneda.
     * @return La moneda encontrada o null si no existe.
     */
    Moneda leer(String id);

    /**
     * Actualiza los datos de una moneda existente.
     * @param id Identificador de la moneda a modificar.
     * @param moneda Nueva información de la moneda.
     */
    void actualizar(String id, Scanner sc);
    
    /**
     * Elimina una moneda del repositorio.
     * @param id Identificador de la moneda a eliminar.
     */
    void eliminar(String id);
    
    void listar();
    
    void serializar();
    
    void deserializar();

	void actualizar(String id, Moneda actualizada);
}
