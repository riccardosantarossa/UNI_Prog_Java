package Huffman;

public class nodo implements Comparable<nodo>
{
    private final char car;
    private final int peso;
    private final nodo sin;
    private final nodo des;

    //nodo foglia
    public nodo( char c, int w)
    {
        car = c;
        peso = w;
        sin = null;
        des = null;
    }

    //nodo non foglia
    public nodo(nodo l , nodo r)
    {
        car = (char) 0;
        peso = l.peso() + r.peso();
        sin = l;
        des = r;
    }

    public boolean foglia() //capire se è foglia o no
    {
        return sin == null;
    }

    public char simbolo() //simbolo associato al nodo
    {
        return car;
    }

    public int peso() //occorrenze di un carattere
    {
        return peso;
    }

    public nodo sinistro()  //determinare il figlio sinistro
    {
        return sin;
    }

    public nodo destro()  //determinare il figlio destro
    {
        return des;
    }

    

    public int compareTo(nodo altro)
    {
        if(peso < altro.peso())
            return -1;
        else if(peso == altro.peso())
            return 0;
        else
            return 1;

    }
}
