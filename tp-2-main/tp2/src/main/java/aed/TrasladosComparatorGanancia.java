package aed;
import java.util.Comparator;

public class TrasladosComparatorGanancia implements Comparator<Traslado> {
    @Override
    public int compare(Traslado t1, Traslado t2){
        return Integer.compare(t2.gananciaNeta, t1.gananciaNeta);
    }
}

