package programacion.dam.tarea8.beans;

/**
 *
 * @author Roach
 */
public class Cliente {
    
    // Atributos
    private String dni;
    private String nombre;
    private String apellidos;
    private String[] telefonos;
    private String[] emails;
    
    // Constructores
    public Cliente(){
    }
    
    public Cliente(String dni, String nombre, String apellidos, String[] telefonos, String[] emails){
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.telefonos = telefonos;
        this.emails = emails;    
    }
    
    // Getters y Setters
    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String[] getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(String[] telefonos) {
        this.telefonos = telefonos;
    }

    public String[] getEmails() {
        return emails;
    }

    public void setEmails(String[] emails) {
        this.emails = emails;
    }
}