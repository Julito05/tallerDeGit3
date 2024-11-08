package aed;

import java.util.ArrayList;
import java.util.Arrays;

public class BestEffort {
    HeapPorGanancia trasladosRedituables;
    HeapPorTimestamp trasladosAntiguos;
    HeapPorSuperavit superavit;
    Ciudad[] ganancia;
    Ciudad[] perdida;

    public BestEffort(int cantCiudades, Traslado[] traslados){
        ArrayList<Traslado> trasladitos = new ArrayList<>(Arrays.asList(traslados));
        Ciudad[] listaCiudades = new Ciudad[cantCiudades];
        ArrayList<Ciudad> ciudades = new ArrayList<>();
        ganancia = listaCiudades;
        perdida = listaCiudades;
        superavit = new HeapPorSuperavit(ciudades);
        trasladosRedituables = new HeapPorGanancia(trasladitos);
        trasladosAntiguos = new HeapPorTimestamp(trasladitos); 
        

    }

    public void registrarTraslados(Traslado[] traslados){
        for (Traslado traslado : traslados) {
            trasladosRedituables.agregar(traslado);
            trasladosAntiguos.agregar(traslado);
        }
    }

    public int[] despacharMasRedituables(int n){
        int i = 0;
        int[] res = new int[n]; 
        while(i < n){
            res[i] = trasladosRedituables.eliminarMax();
            i++;
        }
        return res;
        
    }
    

    public int[] despacharMasAntiguos(int n){
        int i = 0;
        int[] res = new int[n]; 
        while(i < n){
            res[i] = trasladosAntiguos.eliminarMax();
            i++;
        }
        return res;
    }

    public int ciudadConMayorSuperavit(){
        return superavit.eliminarMax();
    }

    public ArrayList<Integer> ciudadesConMayorGanancia(){
        
        return null;
    }

    public ArrayList<Integer> ciudadesConMayorPerdida(){
        // Implementar
        return null;
    }

    public int gananciaPromedioPorTraslado(){
        // Implementar
        return 0;
    }
    
}
