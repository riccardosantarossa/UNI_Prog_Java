package GiuseppeFlavio;

public class tavolaOttimizzata 
{
    //ATTRIBUTI
    private int numero; 
    private int brocca;  //indice del cavaliere con la brocca dentro l'array
    private int[] cavalieri;

    //METODI

    //Costruttore
    public tavolaOttimizzata(int n)
    {
        numero = n;
        brocca = 0;
        cavalieri = new int[2*n-1];

        //Riempimento dell'ARRAY
        for (int i = 0; i < n ; i++) 
        {
            cavalieri[i] = i + 1;
        }
    }

  
    public int cavalieriRimasti() 
    {
        return numero;
    }

    //Controlla chi ha la brocca
    public int cavaliereConBrocca() 
    {
        return cavalieri[brocca];
    }

    //Serve il succo
    public void servi() 
    {
        cavalieri[brocca+1] = cavalieri[brocca];
        brocca++;
        numero--;
    }

    //Passa la brocca
    public void passa() 
    {
        if(numero > 1)
        {
            cavalieri[brocca+numero] = cavalieri[brocca];
            brocca++;
        }
        
    }

}
