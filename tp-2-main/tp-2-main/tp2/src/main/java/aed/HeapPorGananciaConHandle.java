package aed;

import java.util.ArrayList;

public class HeapPorGananciaConHandle {
    private ArrayList<Handle> heap;
    private int tamaño;
    private Handle handle;

    public HeapPorGananciaConHandle(ArrayList<Traslado> array) {
        this.heap = new ArrayList<>();  // Aquí no usamos copyOf
        MaxHeap();
        tamaño = 0;
        for(int i=0; i < array.size(); i++){
            heap.add(new Handle(array.get(i), i));
        }
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
        Handle mayorHandle = heap.get(i);
        Traslado izq = heap.get(2 * i + 1).traslado;
        Traslado der= heap.get(2 * i + 2).traslado;
        Traslado mayor = heap.get(i).traslado;

        // Verificar si el hijo izquierdo es mayor que el padre
        if (posicionIzq < heap.size() && izq.gananciaNeta > mayor.gananciaNeta) {
            mayor = izq;
            posicionMayor = posicionIzq;
        }

        // Verificar si el hijo derecho es mayor que el padre
        if (posicionDer < heap.size() && der.gananciaNeta > mayor.gananciaNeta) {
            mayor = der;
            posicionMayor = posicionDer;
        }

        // Si el mayor no es el padre, intercambiar y continuar heapificando
        if (mayor != heap.get(i).traslado) {
            swap(i, posicionMayor);
            heapify(posicionMayor);
        }
    }

    // Intercambiar elementos en el arreglo
    private void swap(int i, int j) {
        Handle t = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, t);
    }

    public void agregar(Traslado tras){
        Handle h = new Handle(tras, tamaño-1);
        heap.set(tamaño,h);
        Handle padre = heap.get((tamaño-1) / 2);
        int posActual = tamaño;
        int posPadre = (tamaño-1) / 2;

        while (tras.gananciaNeta > padre.gananciaNeta){
            heap.set(posPadre, tras);
            heap.set(posActual, padre);

            posActual = posPadre;
            posPadre = (posActual-1)/2;
        }
        tamaño ++;

    }

    private Traslado MayorHijo(Traslado t1, Traslado t2){
        if(t1.gananciaNeta >= t2.gananciaNeta){
            return t1;
        }
        return t2;
    }

    private int posMayor(Traslado izq, Traslado der, int i){
        if(MayorHijo(izq, der) == izq){
            return 2*i+1;
        }
        return 2*i+2;

    }

    public int eliminarMax(){
//        if(tamaño == 0){
//            return;
//        }

        Traslado res = heap.get(0);
        heap.set(0, heap.get(tamaño-1));
        heap.remove(tamaño-1);

        int posActual = 0;
        Traslado actual = heap.get(0);
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
                Traslado HijoIzq = heap.get(posHijoIzq);
                Traslado HijoDer = heap.get(posHijoDer);
                posMayorHijo = posMayor(HijoIzq, HijoDer, posActual);
            }
            
            Traslado hijoMayor = heap.get(posMayorHijo);

            if(actual.gananciaNeta < hijoMayor.gananciaNeta){
                heap.set(posActual, hijoMayor);
                heap.set(posMayorHijo, actual);
            }else{
                break;
            }
            posActual = posMayorHijo;
            
        }
        tamaño --;
        return res.id;
    
    }
}
