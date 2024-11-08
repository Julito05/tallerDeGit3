package aed;

import java.util.Comparator;

public class Comparador<T> {
    private Comparator<T> comparador;

    public Comparador(Comparator<T> comparador){
        this.comparador = comparador;   
    }

    public int comp(T obj1, T obj2){
        int res = comparador.compare(obj1, obj2);
        return res;
    }

     

    
}
