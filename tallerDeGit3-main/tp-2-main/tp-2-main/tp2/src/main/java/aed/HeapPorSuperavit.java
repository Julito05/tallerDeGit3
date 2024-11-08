package aed;

import java.util.ArrayList;

public class HeapPorSuperavit{
    private ArrayList<Ciudad> heap;
    private int tamaño;

    public HeapPorSuperavit(ArrayList<Ciudad> array) {
        this.heap = new ArrayList<>(array);  
        MaxHeap();
        tamaño = 0;
    }

    // Construir el Heap Max
    private void MaxHeap() {
        for (int i = heap.size()/ 2 - 1; i >= 0; i--) {
            heapify(i);
            tamaño ++;
        }
    }

    // Mantener la propiedad de Heap Max
    private void heapify(int i) {
        int posicionIzq = 2 * i + 1;
        int posicionDer = 2 * i + 2;
        int posicionMayor = i;
        Ciudad mayor = heap.get(i);
        Ciudad izq = heap.get(2 * i + 1);
        Ciudad der= heap.get(2 * i + 2);

        // Verificar si el hijo izquierdo es mayor que el padre
        if (posicionIzq < heap.size() && (izq.superr > mayor.superr || (izq.superr == mayor.superr && izq.nombre < mayor.nombre))){
                mayor = izq;
                posicionMayor = posicionIzq;
        
        
        }

        // Verificar si el hijo derecho es mayor que el padre
        if (posicionDer < heap.size() && (der.superr > mayor.superr || (der.superr == mayor.superr && der.nombre < mayor.nombre))) {
            mayor = der;
            posicionMayor = posicionDer;
        }
        // Si el mayor no es el padre, intercambiar y continuar heapificando
        if (mayor != heap.get(i)) {
            swap(i, posicionMayor);
            heapify(posicionMayor);
        }
    }

    // Intercambiar elementos en el arreglo
    private void swap(int i, int j) {
        Ciudad t = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, t);
    }

    public void agregar(Ciudad tras){
        
        heap.set(tamaño, tras);
        Ciudad padre = heap.get((tamaño-1) / 2);
        int posActual = tamaño;
        int posPadre = (tamaño-1) / 2;

        while (tras.superr > padre.superr){
            heap.set(posPadre, tras);
            heap.set(posActual, padre);

            posActual = posPadre;
            posPadre = (posActual-1)/2;
        }
        tamaño ++;

    }

    private Ciudad MayorHijo(Ciudad t1, Ciudad t2){
        if(t1.superr >= t2.superr){
            return t1;
        }
        return t2;
    }

    private int posMayor(Ciudad izq, Ciudad der, int i){
        if(MayorHijo(izq, der) == izq){
            return 2*i+1;
        }
        return 2*i+2;

    }

    public int eliminarMax(){
//        if(tamaño == 0){
//            return;
//        }

        Ciudad res = heap.get(0);
        heap.set(0, heap.get(tamaño-1));
        heap.remove(tamaño-1);

        int posActual = 0;
        Ciudad actual = heap.get(0);
        int posMayorHijo;

        while(true){
            int posHijoIzq = 2 * posActual + 1;
            int posHijoDer = 2 * posActual + 2;

            if(posHijoDer >= tamaño && posHijoIzq >= tamaño){
                break;
            }
            if(posHijoDer >= tamaño){
                posMayorHijo = posHijoIzq;
            }else if(posHijoIzq >= tamaño){
                posMayorHijo = posHijoDer;
            }
            else{
                Ciudad HijoIzq = heap.get(posHijoIzq);
                Ciudad HijoDer = heap.get(posHijoDer);
                posMayorHijo = posMayor(HijoIzq, HijoDer, posActual);
            }
            
            Ciudad hijoMayor = heap.get(posMayorHijo);

            if(actual.superr < hijoMayor.superr){
                heap.set(posActual, hijoMayor);
                heap.set(posMayorHijo, actual);
            }else{
                break;
            }
            posActual = posMayorHijo;
            
        }
        tamaño --;
        return res.nombre;
    
    }
}
