package aed;

import java.util.ArrayList;
import java.util.Arrays;

public class BestEffort {
    Heap traslados;
    HeapPorSuperavit superavit;
    int topG;    // Máxima ganancia
    int topP;    // Máxima perdida
    int gananciaGlobal;
    int totalTraslados;
    ArrayList<Integer> ciudadesMasGanancia; 
    ArrayList<Integer> ciudadesMasPerdida;  
    ArrayList<Ciudad> ciudades; 

    public BestEffort(int cantCiudades, Traslado[] traslados){     // O(|traslados + |ciudades|)
        ArrayList<Traslado> trasladitos = new ArrayList<>(Arrays.asList(traslados));   // O(|traslados|)
        this.ciudades = new ArrayList<>(cantCiudades);                                 // O(cantCiudades)   
        for (int i = 0; i < cantCiudades; i++){         // O(|ciudades|)
            Ciudad c = new Ciudad(i, 0, 0);  // O(1)
            ciudades.add(c);      // O(1)                        
        }

        ciudadesMasGanancia = new ArrayList<>();  // O(1)
        ciudadesMasPerdida = new ArrayList<>();   // O(1)
        superavit = new HeapPorSuperavit(ciudades);   // O(|ciudades|)
        this.traslados = new Heap(trasladitos);   // O(|traslados|)
        topG = -1;
        topP = -1; 
        gananciaGlobal = 0;
        totalTraslados = 0;
    }

    public void registrarTraslados(Traslado[] traslados){
        for (Traslado traslado : traslados) {
          this.traslados.agregar(traslado);
        }
    }

    // Función Auxiliar
    public void actualizarCiudad(Traslado traslado) {
        
        Ciudad ciudadO = ciudades.get(traslado.origen); 
        ciudadO.nombre = traslado.origen;
        ciudadO.ganancia = ciudadO.ganancia + traslado.gananciaNeta;
        ciudadO.superr += traslado.gananciaNeta;

        Ciudad ciudadD = ciudades.get(traslado.destino);
        ciudadD.nombre = traslado.destino;
        ciudadD.perdida = ciudadD.perdida + traslado.gananciaNeta;
        ciudadD.superr -= traslado.gananciaNeta;


        if (ciudadesMasGanancia.isEmpty()){
            ciudadesMasGanancia.add(ciudadO.nombre);
            this.topG = ciudadO.ganancia;

            ciudadesMasPerdida.add(ciudadD.nombre);
            this.topP = ciudadD.perdida;
        }
        else {
    //El agregar de superavit debe ver el caso de si la ciudad ya pertenece al superavit, en ese caso agregarlo y heapificar, caso contrario solo heapificar la ciudad modificada. 
            
           if (ciudadD.perdida > topP){
               ciudadesMasPerdida.clear();
               ciudadesMasPerdida.add(ciudadD.nombre);
               this.topP = ciudadD.perdida;
        }
           else if (ciudadD.perdida == topP){
               ciudadesMasPerdida.add(ciudadD.nombre);
        }

           if (ciudadO.ganancia > topG){
               ciudadesMasGanancia.clear();
               ciudadesMasGanancia.add(ciudadO.nombre);
               this.topG = ciudadO.ganancia;
     }
           else if (ciudadO.ganancia == topG){
               ciudadesMasGanancia.add(ciudadO.nombre);
     }
    }
        
        superavit.actualizaCiudad(ciudadO);
        superavit.actualizaCiudad(ciudadD);
    
}
        
    
    //
    public int[] despacharMasRedituables(int n){
        int i = 0;
        int[] res = new int[n]; 
        while(i < n){
          Traslado e = traslados.eliminarMax("ganancia");
            res[i] = e.id;
            actualizarCiudad(e);
            gananciaGlobal = gananciaGlobal + e.gananciaNeta;
            totalTraslados = totalTraslados +1;
            i++;
        }
        return res;  
    }

    public int[] despacharMasAntiguos(int n){
        int i = 0;
        int[] res = new int[n]; 
        while(i < n){
          Traslado e = traslados.eliminarMax("tiempo");
            res[i] = e.id;
            actualizarCiudad(e);
            gananciaGlobal = gananciaGlobal + e.gananciaNeta;
            totalTraslados = totalTraslados +1;
            i++;
        }
        return res;  
    }

    public int ciudadConMayorSuperavit(){
        return superavit.verMax();
    }

    public ArrayList<Integer> ciudadesConMayorGanancia(){
        return ciudadesMasGanancia;
    }

    public ArrayList<Integer> ciudadesConMayorPerdida(){
        return ciudadesMasPerdida;
    }

    public int gananciaPromedioPorTraslado(){
         
        if(totalTraslados == 0) {
            return 0;
        }
        return gananciaGlobal/totalTraslados;
    }
    
}
