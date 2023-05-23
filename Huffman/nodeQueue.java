package Huffman;

public class nodeQueue 
{
    //VARIABILI DI ISTANZA
    private static nodo[] coda;

    //Costruttore
    public nodeQueue()
    {
        coda = new nodo[128];
    }

    //restituisce il numero di elementi contenuti nella coda
    public static int size()
    {
        int numNodi = 0, index = 0;

        while(coda[index] != null)
        {
            numNodi ++;
            index ++;
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
        

        return primo;

    }


    //ordinamento ausiliario
    public static void ordina()
    {
        nodo temp;

        for (int i = 0; i < ( coda.length - 1 ); i++) 
        {
            for (int j = 0; j < coda.length - i - 1; j++) 
            {
              if (coda[j].peso() < coda[j+1].peso()) 
              {
                temp = coda[j];
                coda[j] = coda[j+1];
                coda[j+1] = temp;
              }
            }
          }
    }

    


    //aggiunge un nuovo elemento n alla coda
    public static void add(nodo n)
    {

    }
}
