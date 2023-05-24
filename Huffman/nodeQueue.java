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
        
     }

   
}
