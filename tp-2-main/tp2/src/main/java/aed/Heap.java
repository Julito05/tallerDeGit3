package aed;
import java.util.ArrayList;
import java.util.Comparator;

public class Heap {
    public ArrayList<Handle> heapGanancia;
    private ArrayList<Handle> heapTiempo;
    private Comparator<Traslado> comparador;

    private int tamaño;

    public Heap(ArrayList<Traslado> array){
        this.heapGanancia = new ArrayList<>();  // Aquí no usamos copyOf
        this.heapTiempo = new ArrayList<>();

        for(int i=0; i < array.size(); i++){            // O(n) (n = longitud array)
            Handle h = new Handle(array.get(i), i, i);   //O(1)
            this.heapGanancia.add(h);   // O(1)
            this.heapTiempo.add(h);     // O(1)
        }
        tamaño = array.size();   // O(1)
        constructorHeap();    // O(n)
    }
 
  // Construir el heapGanancia Max y el heapTimestamp Min
  private void constructorHeap() {                                   // O(n)
        for (int i = heapTiempo.size()/ 2 - 1; i >= 0; i--) {  
            heapifyDown(i,heapTiempo, "tiempo");    // O(n)
            heapifyDown(i, heapGanancia, "ganancia");
        }
  }
  //la complejidad de transformar un arreglo en un heap va a ser O(n), ya que, 

    // Mantener la propiedad de heapGanancia Max
    private void heapifyDown(int i, ArrayList<Handle> heap, String atributo) {  // O(log n)
        if(atributo.equals("ganancia")){        // O(1)
            comparador = new TrasladosComparatorGanancia();       // O(1)
//            heap = heapGanancia;
        }else if(atributo.equals("tiempo")){    // O(1)
            comparador = new TrasladosComparatorTimestamp();    // O(1)
//            heap = heapTiempo;
        }

        int posicionIzq = 2 * i + 1;   
        int posicionDer = 2 * i + 2;                      // O(1)
        int posicionActual = i;
        Traslado actual = heap.get(i).traslado;  


        // Verificar si el hijo izquierdo es mayor que el padre
        if (posicionIzq < heap.size() && comparador.compare(heap.get(posicionIzq).traslado, actual) < 0) {    // O(1)
            posicionActual = posicionIzq;      // O(1)
        }

        // Verificar si el hijo derecho es mayor que el padre
        if (posicionDer < heap.size() && comparador.compare(heap.get(posicionDer).traslado, heap.get(posicionActual).traslado) < 0) {    // O(1)
            posicionActual = posicionDer;      // O(1)
            }  

        // Si el mayor no es el padre, intercambiar y continuar heapificando
        if (posicionActual != i) {    // O(1)
            swap(i, posicionActual, atributo);
            heapifyDown(posicionActual, heap, atributo);   // O(log n). Esto es porque se va a heapificar, en peor caso, la altura del heap veces, lo que equivale a una 
        }
    }
    

    // Intercambiar elementos en el arreglo

    private void swap(int i, int j, String atributo) {   // O(1)

        if(atributo.equals("ganancia")){    // O(1)
            Handle t = heapGanancia.get(i);   // O(1)
            heapGanancia.set(i, heapGanancia.get(j));  // O(1)
            heapGanancia.set(j, t);    // O(1)

            heapGanancia.get(i).posicionG = i;    // O(1)
            heapGanancia.get(j).posicionG = j;    // O(1)
        }
        else if(atributo.equals("tiempo")){     // O(1)
            Handle t = heapTiempo.get(i);     // O(1)
            heapTiempo.set(i, heapTiempo.get(j));   // O(1)
            heapTiempo.set(j, t);    // O(1)

            heapTiempo.get(i).posicionT = i;   // O(1)
            heapTiempo.get(j).posicionT = j;   // O(1)
        }

    }

    private void heapifyUp(int i, ArrayList<Handle> heap, String atributo){  // O(log n)
        if(atributo.equals("ganancia")){
            comparador = new TrasladosComparatorGanancia();
//            heap = heapGanancia;
        }else if(atributo.equals("tiempo")){
            comparador = new TrasladosComparatorTimestamp();
//            heap = heapTiempo;
        }

        int posPadre = (i-1)/2;
//        int posActual = i;

        if ( (i > 0 && i <= tamaño) && comparador.compare(heap.get(i).traslado, heap.get(posPadre).traslado) < 0) {
            //            swap(posicionIzq,posicionActual,atributo);
            swap(i, posPadre, atributo);
            heapifyUp(posPadre,heap,atributo);
        }
    }


public void agregar(Traslado tras){   // O(log n)

    Handle h = new Handle(tras, tamaño, tamaño);  // O(1)

    heapGanancia.add(h);   // O(1)
    heapTiempo.add(h);    // O(1)
    heapifyUp(tamaño,heapGanancia,"ganancia");  // O(log n)
    heapifyUp(tamaño,heapTiempo,"tiempo");    // O(log n)
    this.tamaño++;   // O(1)
}

    private void sincronizar(ArrayList<Handle> heapM, int pos, String atributo){    // O(log n)
        if(pos >= 0 && pos <= tamaño){   // O(1)
           swap(pos, tamaño,atributo);   // O(1)
           heapM.remove(tamaño);   // O(1)
           if(pos == tamaño){return;}  // O(1)
           heapifyDown(pos,heapM,atributo);   // O(log n)
           heapifyUp(pos,heapM,atributo);    // O(log n)
        }

    }

    public Traslado eliminarMax(String atributo){    // O(2*log n) = O(log n)
        if(atributo.equals("ganancia")){
            comparador = new TrasladosComparatorGanancia();                       // O(1)
        }else if(atributo.equals("tiempo")){
            comparador = new TrasladosComparatorTimestamp();
        }

        if(tamaño == 0){return null;}    // O(1)
        tamaño --;   // O(1)
        if(atributo.equals("ganancia")){        // O(1)
            
            Handle res = heapGanancia.get(0);   // O(1)
            swap(0, tamaño,atributo);     // O(1)
            heapGanancia.remove(tamaño);  // O(1)  
            if (tamaño != 0 ) {    // O(1)
                heapifyDown(0,heapGanancia,atributo);   // O(log n)
                
            }
            sincronizar(heapTiempo,res.posicionT,"tiempo");    // O(log n)
            return res.traslado;    // O(1)
            
        }
            
        
        else{

            Handle res = heapTiempo.get(0);    // O(1)
            swap(0, tamaño,atributo);     // O(1)
            heapTiempo.remove(tamaño);    // O(1)
            if (tamaño != 0) {      // O(1)
                heapifyDown(0,heapTiempo, atributo);    // O(log n)
            }
            sincronizar(heapGanancia, res.posicionG,"ganancia");    // O(log n)
            return res.traslado;     // O(1)
            
        }
    }
}
