package utp.edu.codekion.finanzas.utils;

import utp.edu.codekion.finanzas.exception.DatoNoIngresadoException;

public class EntidadNoNulaException {

    // Método genérico para verificar si una entidad es null
    public static void verificarEntidadNoNula(Object entidad, String mensajeError) {
        if (entidad == null) {
            throw new DatoNoIngresadoException(mensajeError);
        }
    }

}
