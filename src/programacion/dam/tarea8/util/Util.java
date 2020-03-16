package programacion.dam.tarea8.util;

/**
 *
 * @author Roach
 */
public class Util {
    
    // Constantes
    public static final String MENSAJE_OK = "OK";
    
    /**
     * Método que valida los datos introducidos por el usuario.
     * @param cadena
     * @return boolean
     */
    public static String validaDatos(String cadena){

        
        if(!validaCadena(cadena)){
            return "La cadena introducida no contiene todos los datos necesarios.";
        }
        
        // Continuar codigo
        
        
        return MENSAJE_OK;    
    }
    
    
    
    /**
     * Método que valida la cadena de entrada, se separa y comprueba que tenga minimo 5 posiciones
     * @param cadena
     * @return boolean
     */
    public static boolean validaCadena(String cadena){   
        String[] lista = cadena.split(",");   
        return lista.length > 4;
    }
    
    /**
     * Método para pintar el menu principal.
     */
    public static void pintarMenu(){
        System.out.println("*** Menú principal ***");
        System.out.println("1 - Añadir cliente");
        System.out.println("2 - Salir de la aplicación");
        System.out.println("*** Introduzca la opción deseada (1-2) ***");
    }
}
