package aed;

public class Ciudad {
    int nombre;
    int ganancia;
    int perdida;
    int superr;
    

    public Ciudad(int nombre, int ganancia, int perdida){
        this.nombre = nombre;
        this.ganancia = ganancia;
        this.perdida = perdida;
        this.superr = ganancia - perdida;
    }

    public void incrementarGanancia(int ganancia) {
        this.ganancia += ganancia;
    }
    
    public void incrementarPerdida(int perdida) {
        this.perdida += perdida;
    }
    
}
