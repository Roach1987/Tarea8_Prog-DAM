package programacion.dam.tarea8.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import programacion.dam.tarea8.beans.Cliente;

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
    public static final String LETRAS_DNI = "TRWAGMYFPDXBNJZSQVHLCKE";
    
    // Patrones
    public static final String PATRON_NIF = "[XYZxyz]?[0-9]{8,9}[A-Za-z]";
    public static final String PATRON_VALIDAR_NIF = "(\\d{1,8})([TRWAGMYFPDXBNJZSQVHLCKEtrwagmyfpdxbnjzsqvhlcke])";
    public static final String PATRON_NOMBRE_APELLIDOS = "\"[A-Za-z]*\"";
    public static final String PATRON_MAIL = "[a-zA-Z0-9]*@[a-zA-Z0-9]*\\.[a-z]{2,3}";
    public static final String PATRON_MAIL_COMPLEJO = "^[^@]+@[^@]+\\.[a-zA-Z]{2,}$";
    public static final String PATRON_TELEFONO = "(\\+?[(]?\\d{2,4}[)]?)?[0-9]{7,11}";
    
    // Comparador
    Comparator<String> compararTelefonos;
    
    // Mensajes documento XML
    private static final String MENSAJE_XML = "Documento XML creado correctamente";
    private static final String MENSAJE_NO_TELEFONOS = ", pero no contiene ningún telefono.";
    private static final String MENSAJE_NO_MAILS = ", pero no contiene ningún correo electronico.";
    private static final String MENSAJE_NO_MAIL_NO_TELEFONO = ", pero no contiene ningún correo electronico, ni ningún telefono.";
    
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
        if(listaCampos[0].matches(PATRON_NIF)){
            if(!validarNIF(listaCampos[0])){
                return "El NIF indicado en la primera posición no es valido.";
            }
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

    /**
     * Método que recupera los datos, si no existe por lo menos un mail, o un telefono
     * devolvera un mensaje de error, si todo va bien devolvera un mensaje de OK
     * @param cadena
     * @return String
     */
    public static String obtenerCamposCliente(String cadena){
        String mensaje = "";
        String[] datosCadena = cadena.split(",");
        
        ArrayList<String> telefonos = new ArrayList<>();
        ArrayList<String> mails = new ArrayList<>();
        
        // Guardamos los datos obligatorios NIF, Nombre y Apellidos
        // limpiamos Nombre y Apellidos de las comillas "".
        String nif = datosCadena[0].trim();
        String nombre = datosCadena[1].trim().replace("\"", "");
        String apellidos = datosCadena[2].trim().replace("\"", "");
        
        String[] datosLimpios = limpiarArrayString(datosCadena, 4);
        
        // Recogemos los datos restantes, si alguno no cumple con el patron de email
        // o telefono, se desecha.
        for(String dato : datosLimpios){
            // Comprobamos si el dato actual es un email
            if(dato.trim().matches(PATRON_MAIL)){
                // pasamos el mail a minusculas
                String mailLimpio = dato.trim().toLowerCase();
                
                // Vemos si en la lista ya existe el mail, si no existe lo insertamos.
                if(!mails.contains(mailLimpio)){
                    mails.add(mailLimpio);
                }
            }else if(dato.trim().matches(PATRON_TELEFONO)){
                // Limpiamos el telefono
                String telefonoLimpio = dato.trim().replace("(", "").replace(")", "");
                
                // Vemos si en la lista ya existe el telefono, si no existe lo insertamos.
                if(!telefonos.contains(telefonoLimpio)){
                    telefonos.add(telefonoLimpio);
                }
            }
        }
        
        // Ordenamos los telefonos
        Comparador comparador = new Comparador();
        Collections.sort(telefonos, comparador);
        
        // Creamos el objeto Cliente con los datos ya validados.
        Cliente cliente = new Cliente(nif, nombre, apellidos, telefonos, mails);
        
        // Creamos el documento XML
        mensaje = crearDocumentoXML(cliente);
        
        return mensaje;
    }
    
    /**
     * Método que crea un documento XML a partir de los datos del cliente 
     * llegado por parametro
     * @param cliente
     * @return String
     */
    private static String crearDocumentoXML(Cliente cliente){
        String mensaje = "";
        Element elemento = null;
        Element elementoAux = null;
        int totalTelefonos = 0;
        
        //creamos el documento 
        Document documento = DOMUtil.crearDOMVacio("datos_cliente");
        
        // Creamos el elemento id, que contendra los datos del nif.
        elemento = documento.createElement("id");
        elemento.setTextContent(cliente.getNif());
        documento.getDocumentElement().appendChild(elemento);
        
        // Creamos el elemento nombre.
        elemento = documento.createElement("nombre");
        elemento.setTextContent(cliente.getNombre());
        documento.getDocumentElement().appendChild(elemento);
        
        // Creamos el elemento apellidos.
        elemento = documento.createElement("apellidos");
        elemento.setTextContent(cliente.getApellidos());
        documento.getDocumentElement().appendChild(elemento);
        
        // Creamos el elemento telefonos
        elemento = documento.createElement("telefonos");
        
        if(!cliente.getTelefonos().isEmpty()){
            for(String telefono : cliente.getTelefonos()){
                elementoAux = documento.createElement("telefono");
                elementoAux.setTextContent(telefono);
                elemento.appendChild(elementoAux);
                totalTelefonos++;
            }
        }
        
        elementoAux = documento.createElement("total");
        elementoAux.setTextContent(String.valueOf(totalTelefonos));
        elemento.appendChild(elementoAux);
        documento.getDocumentElement().appendChild(elemento);
        
        // Creamos el elemento mails
        elemento = documento.createElement("mails");
        
        if(!cliente.getEmails().isEmpty()){
            for(String mail : cliente.getEmails()){
                elementoAux = documento.createElement("mail");
                elementoAux.setTextContent(mail);
                elemento.appendChild(elementoAux);
            }
        }
        
        documento.getDocumentElement().appendChild(elemento);
                        
        // Pasamos el documento a un fichero
        DOMUtil.DOM2XML(documento,"cliente.xml");
        
        // Devolvemos el mensaje de la creación del documento XML.
        if(cliente.getTelefonos().isEmpty()){
            if(cliente.getEmails().isEmpty()){
                mensaje = MENSAJE_XML.concat(MENSAJE_NO_MAIL_NO_TELEFONO);
            }else{
                mensaje = MENSAJE_XML.concat(MENSAJE_NO_TELEFONOS);
            }
        }else if(cliente.getEmails().isEmpty()){
            mensaje = MENSAJE_XML.concat(MENSAJE_NO_MAILS);
        }else{
            mensaje = MENSAJE_XML;
        }
        
        return mensaje;
    } 

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
    
    /**
     * Método que limpia un array, desde la posicion indicada (la posicion es la real)
     * Ejemplo: primera posicion = 1.
     * @param arrayALimpiar
     * @param indiceEmpezar
     * @return 
     */
    public static String[] limpiarArrayString(String[] arrayALimpiar, int indiceEmpezar){
        String[] arrayLimpio = new String[arrayALimpiar.length - indiceEmpezar +1];
        int longitudArrayLimpio = arrayLimpio.length;
        int posicionArrayLimpiar = indiceEmpezar -1;
        
        for(int i = 0; i < longitudArrayLimpio; i++){
            arrayLimpio[i] = arrayALimpiar[posicionArrayLimpiar];
            posicionArrayLimpiar++;
        }
        return arrayLimpio;
    }
    
    /**
     * Metodo que valida un DNI, comprobando que su longitud sea correcta, asi
     * como su Letra corresponde con el numero.
     *
     * @param nif
     * @return boolean
     */
    public static boolean validarNIF(String nif) {
        boolean correcto;
        Pattern pattern = Pattern.compile(PATRON_VALIDAR_NIF);
        Matcher matcher = pattern.matcher(nif);
        if (matcher.matches()) {
            String letra = matcher.group(2);
            int indiceLetra = Integer.parseInt(matcher.group(1));
            indiceLetra = indiceLetra % 23;
            String buscarReferenciaLetra = LETRAS_DNI.substring(indiceLetra, indiceLetra + 1);
            // Comparamos la letra del DNI llegado por parametro con el orden
            // logico de las letras ignorando que sea mayuscula o minuscula.
            correcto = buscarReferenciaLetra.equalsIgnoreCase(letra);
        } else {
            correcto = false;
        }
        return correcto;
    }
// ********************************************************************************************************
}
