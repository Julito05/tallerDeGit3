package aed;

import java.util.ArrayList;


public class BestEffort {
    HeapPorGanancia trasladosRedituables;
    HeapPorTimestamp trasladosAntiguos;
    HeapPorSuperavit superavit;
    Ciudad[] ganancia;
    Ciudad[] perdida;


    public BestEffort(int cantCiudades, Traslado[] traslados){
        Ciudad[] listaCiudades = new Ciudad[cantCiudades];
        ganancia = listaCiudades;
        perdida = listaCiudades;
//        superavit = new HeapPorSuperavit();
        trasladosRedituables = new HeapPorGanancia(traslados);
        trasladosAntiguos = new HeapPorTimestamp(traslados);
        
    }

    public void registrarTraslados(Traslado[] traslados){
        // Implementar
    }

    public int[] despacharMasRedituables(int n){
        // Implementar
        return null;
    }

    public int[] despacharMasAntiguos(int n){
        // Implementar
        return null;
    }

    public int ciudadConMayorSuperavit(){
        // Implementar
        return 0;
    }

    public ArrayList<Integer> ciudadesConMayorGanancia(){
        // Implementar
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
