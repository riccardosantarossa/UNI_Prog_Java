package Huffman;

public class nodeQueue 
{
    //VARIABILI DI ISTANZA
    private static nodo[] coda;
    private static int dim = 128;

    //Costruttore
    public nodeQueue()
    {
        coda = new nodo[dim];

        for (int i = 0; i < dim; i++) 
        {
            coda[i] = null;
        }
    }

    //restituisce il numero di elementi contenuti nella coda
    public static int size()
    {
        int numNodi = 0;

        for (int i = 0; i < coda.length; i++) 
        {
            if(coda[i] != null)
                numNodi++;
        }

        return numNodi;

    }

    //restituisce l’elemento con “peso minore” (senza rimuoverlo dalla coda)
    public static nodo peek()
    {
        return coda[0];
    }

    
    //restituisce e rimuove dalla coda l’elemento con “peso minore”
    public static nodo poll()
    {
        nodo primo;

        primo = coda[0];
        coda[0] = null;
        
        for (int i = 1; i < coda.length; i++) 
        {
            if(coda[i-1] == null)
            {
                coda[i-1] = coda[i];
                coda[i] = null;
            }
                
        }
        return primo;
    }

     //aggiunge un nuovo elemento n alla coda
     public static void add(nodo n)
     {
        int posIns = 0;

        if(size() == 0)
            posIns = 0;

        for (int i = 0; i < coda.length; i++) 
        {
            if(n.peso() >= coda[size()-1].peso())
                    posIns = size();
            else
            {
                if(n.peso() <= coda[i].peso())
                {
                    posIns = i;
                    break;
                }
            }
           
        }

        for (int j = size()-1; j >= posIns ; j--) 
        {
            if(coda[j+1] == null)
            {
                coda[j+1] = coda[j];
                coda[j] = null;
            }
        }

        coda[posIns] = n;
     }

   
}
