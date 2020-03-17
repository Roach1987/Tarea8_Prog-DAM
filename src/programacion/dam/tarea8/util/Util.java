package programacion.dam.tarea8.util;

/**
 *
 * @author Roach
 */
public class Util {
    
    // Constantes
    public static final String MENSAJE_OK = "OK";
    public static final String MENSAJE_KO_NIF = "NIF_KO";
    public static final String MENSAJE_KO_NOMBRE = "NOMBRE_KO";
    public static final String MENSAJE_KO_APELLIDOS = "APELLIDOS_KO";
    
    // Patrones
    public static final String PATRON_NIF = "[XYxy]?[0-9]{8,9}[A-Za-z]";
    public static final String PATRON_NOMBRE_APELLIDOS = "\"[A-Za-z]*\"";
    
// **************************************** Validaciones **************************************************
    /**
     * Método que valida los datos introducidos por el usuario en la cadena.
     * @param cadena
     * @return boolean
     */
    public static String validaDatos(String cadena){
        if(!validaCadena(cadena)){
            return "La cadena introducida no contiene todos los datos necesarios.";
        }
        String[] listaCampos = cadena.split(",");
        return validaCamposPrincipales(listaCampos);
    }
    
    /**
     * Método que valida la cadena de entrada, se separa y comprueba que tenga minimo 5 posiciones
     * @param cadena
     * @return boolean
     */
    public static boolean validaCadena(String cadena){   
        String[] listaCampos = cadena.split(",");   
        return listaCampos.length > 4;
    }
    
    /**
     * Método que valida que los campos NIF, Nombre y Apellidos sean correctos.
     * @param listaCampos
     * @return String
     */
    public static String validaCamposPrincipales(String[] listaCampos){
        if(!listaCampos[0].matches(PATRON_NIF)){
            return "El NIF indicado en la primera posición no es valido.";
        }
        if(!listaCampos[1].matches(PATRON_NOMBRE_APELLIDOS)){
            return "El nombre indicado no esta entrecomillado \" \", o contiene caracteres numericos.";
        }
        if(!listaCampos[2].matches(PATRON_NOMBRE_APELLIDOS)){
            return "Los apellidos no estan entrecomillados \" \", o contienen caracteres numericos";
        }
        return MENSAJE_OK;
    }
// ********************************************************************************************************
// *********************************** Aplicar logica funcional *******************************************

    // ******** Continuar codigo!!!!!!!!!!!!!!!!!!!!!!

// ********************************************************************************************************
// ***************************************** Utilitarios **************************************************    
    /**
     * Método para pintar el menu principal.
     */
    public static void pintarMenu(){
        System.out.println("*** Menú principal ***");
        System.out.println("1 - Añadir cliente");
        System.out.println("2 - Salir de la aplicación");
        System.out.println("*** Introduzca la opción deseada (1-2) ***");
    }
// ********************************************************************************************************
}
