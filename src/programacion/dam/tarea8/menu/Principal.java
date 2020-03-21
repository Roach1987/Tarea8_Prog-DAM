package programacion.dam.tarea8.menu;

import java.util.Scanner;
import programacion.dam.tarea8.util.Util;

/**
 *
 * @author Roach
 */
public class Principal {
    
    // Método main
    public static void main(String[] args){
        Scanner escanerEntrada = new Scanner(System.in);
        boolean salirAplicacion = false;
  
        do{
            Util.pintarMenu();
            
            String opcionUsuario = escanerEntrada.nextLine();
            switch(opcionUsuario){
                case "1":
                    System.out.println("Introduzca los datos en el siguiente formato:");
                    System.out.println("NIF,\"NOMBRE\",\"APELLIDOS\",[TELEFONO - MAIL]+, [TELEFONO - MAIL]+");
                    
                    String cadenaPeticion = escanerEntrada.nextLine(); 
                    
                    // Validamos la cadena introducida por el usuario.
                    String mensaje = Util.validaDatos(cadenaPeticion);
                    
                    if(!mensaje.equals(Util.MENSAJE_OK)){
                        System.out.println();
                        System.out.println();
                        System.out.println(mensaje);
                        System.out.println();
                        System.out.println();
                    }else{
                        System.out.println();
                        System.out.println();
                        System.out.println(Util.obtenerCamposCliente(cadenaPeticion));
                        System.out.println();
                        System.out.println();
                    }
                    break;
                case "2":
                    System.out.println();
                    System.out.println("*************************************");
                    System.out.println("Hasta Pronto!!!!");
                    System.out.println("*************************************");
                    System.out.println();
                    salirAplicacion = true;
                    break;
                default:
                    System.out.println();
                    System.out.println("*************************************");
                    System.out.println("Opción no valida !!!" + "\n" + 
                            "** Opciones validas: " + "\n\t" + "1. Crear documento XML" + "\n\t" +
                            "2. Salir.");
                    System.out.println("*************************************");
                    System.out.println();
                    break;
            }
        }while(!salirAplicacion);
    }
}