package programacion.dam.tarea8.beans;

import java.util.ArrayList;

/**
 *
 * @author Roach
 */
public class Cliente {
    
    // Atributos
    private String nif;
    private String nombre;
    private String apellidos;
    private ArrayList<String> telefonos;
    private ArrayList<String> emails;
    
    // Constructores
    public Cliente(){
    }
    
    public Cliente(String nif, String nombre, String apellidos, ArrayList<String> telefonos, ArrayList<String> emails){
        this.nif = nif;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.telefonos = telefonos;
        this.emails = emails;    
    }
    
    // Getters y Setters
    public String getNif() {
        return nif;
    }

    public void setDni(String nif) {
        this.nif = nif;
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

    public ArrayList<String> getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(ArrayList<String> telefonos) {
        this.telefonos = telefonos;
    }

    public ArrayList<String> getEmails() {
        return emails;
    }

    public void setEmails(ArrayList<String> emails) {
        this.emails = emails;
    }
}