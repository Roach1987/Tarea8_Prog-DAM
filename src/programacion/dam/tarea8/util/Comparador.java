package programacion.dam.tarea8.util;

import java.util.Comparator;

/**
 *
 * @author Roach
 */
public class Comparador implements Comparator{
    @Override
    public int compare(Object o1, Object o2) {     
        String telefono1 = o1.toString();
        String telefono2 = o2.toString();
        int orden=0;
        int res = telefono1.compareTo(telefono2);
        if (res < 0){
            orden = 1;
        } else if (res > 0){
            orden = -1;
        }
        return orden; 
    } 
}