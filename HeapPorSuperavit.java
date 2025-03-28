package aed;

import java.util.ArrayList;

public class HeapPorSuperavit {
    private ArrayList<Ciudad> heap;
    private int tamaño;

    public HeapPorSuperavit(ArrayList<Ciudad> array) {
        this.heap = new ArrayList<>(array);  // Aquí no usamos copyOf
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

        int izqSuperavit = izq.ganancia - izq.perdida;
        int derSuperavit = der.ganancia - der.perdida;
        int mayorSuperavit = mayor.ganancia - mayor.perdida;

        // Verificar si el hijo izquierdo es mayor que el padre
        if (posicionIzq < heap.size() && izqSuperavit > derSuperavit) {
            mayor = izq;
            posicionMayor = posicionIzq;
            mayorSuperavit = izqSuperavit;
        }

        // Verificar si el hijo derecho es mayor que el padre
        if (posicionDer < heap.size() && derSuperavit > mayorSuperavit) {
            mayor = der;
            posicionMayor = posicionDer;
            mayorSuperavit = derSuperavit;
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

    public void agregar(ArrayList<Traslado> array, Traslado tras){
        array.set(tamaño, tras);
        Traslado padre = array.get((tamaño-1) / 2);
        int posActual = tamaño;
        int posPadre = (tamaño-1) / 2;

        while (tras.gananciaNeta > padre.gananciaNeta){
            array.set(posPadre, tras);
            array.set(posActual, padre);

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

    public int eliminarMax(ArrayList<Traslado> array){
//        if(tamaño == 0){
//            return;
//        }

        Traslado res = array.get(0);
        array.set(0, array.get(tamaño-1));
        array.remove(tamaño-1);

        int posActual = 0;
        Traslado actual = array.get(0);
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
                Traslado HijoIzq = array.get(posHijoIzq);
                Traslado HijoDer = array.get(posHijoDer);
                posMayorHijo = posMayor(HijoIzq, HijoDer, posActual);
            }
            
            Traslado hijoMayor = array.get(posMayorHijo);

            if(actual.gananciaNeta < hijoMayor.gananciaNeta){
                array.set(posActual, hijoMayor);
                array.set(posMayorHijo, actual);
            }else{
                break;
            }
            posActual = posMayorHijo;
            
        }
        tamaño --;
        return res.id;
    
    }
}

