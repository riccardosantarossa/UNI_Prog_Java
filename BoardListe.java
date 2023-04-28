public class BoardListe 
{
    
    /*Al posto di utilizzare un predicato per controllare le caselle
     * in cui sono poste le regine, si utilizzano 4 liste di indici interi, per controllare
     * righe, colonne e diagonali ascendenti e discendenti
     */

    //Variabili di istanza

    private int dimScacchiera;
    private int numRegine;
    private SList<Integer> lstRighe, lstColonne, lstDiagAsc, lstDiagDesc;
    private final String config;
   

    //Costruttore di scacchiera VUOTA
    public BoardListe(int n)
    {
        dimScacchiera = n;
        numRegine = 0;
        lstRighe = new SList<Integer>();
        lstColonne = new SList<Integer>();
        lstDiagAsc = new SList<Integer>();
        lstDiagDesc = new SList<Integer>();
        config = " ";
    }


    //Costruttore di scacchiera NON VUOTA
    private BoardListe(int n, int nQueens, SList<Integer> lr, SList<Integer> lc, SList<Integer> lda, SList<Integer> ldd, String conf)
    {
        dimScacchiera = n;
        numRegine = nQueens;
        lstRighe = lr;
        lstColonne = lc;
        lstDiagAsc = lda;
        lstDiagDesc = ldd;
        config = conf;
    }


    //Restituisce la dimensione della scacchiera
    public int size()
    {
        return dimScacchiera;
    }

    //Restituisce il numero di regine sulla scacchiera in quel momento
    public int queensOn()
    {
        return numRegine;
    }


    public boolean sottoAttacco(int i, int j)
    {
        if(lstRighe.vuoto|| lstColonne.vuoto || lstDiagAsc.vuoto|| lstDiagDesc.vuoto)
            return false;

        else if(i == lstRighe.car() || j == lstColonne.car() || i+j == lstDiagAsc.car() || i-j == lstDiagDesc.car())
            return true;
        
        else
        {
            /*lstRighe.cons(i);
            lstColonne.cons(j);
            lstDiagAsc.cons(i+j);
            lstDiagDesc.cons(i-j);*/
            return false;
        }
            
    }   

    public BoardListe aggiungiRegina(int i, int j)
    {
        return new BoardListe
        (  dimScacchiera,
           numRegine+1,
           lstRighe.cons(i),
           lstColonne.cons(j),
           lstDiagAsc.cons(i+j),
           lstDiagDesc.cons(i-j),
           config
        );
    }

    //Restituisce la configurazione della scacchiera
    public String arrangement() 
    {
        return config;
    }

    //Restiruisce la configurazione in formato testuale
    public String toString()
    {
        return "[" + arrangement() + "]";
    }



    //------------------------------------------------------------------------------------------------------------//
    private static int numberOfCompletions( BoardListe b ) {
    
        int n = b.size();
        int q = b.queensOn();
        
        if ( q == n ) {
        
          return 1;
        
        } else {
        
          int i = q + 1;
          int count = 0;
          
          for ( int j=1; j<=n; j=j+1 ) {
            if ( !b.sottoAttacco(i,j) ) {
            
              count = count + numberOfCompletions( b.aggiungiRegina(i,j) );
          }}
          return count;
        }
      }

      public static int numberOfSolutions( int n ) {
      
        return numberOfCompletions( new BoardListe(n) );
      }

      public static void main( String args[] ) {
    
        //int n = Integer.parseInt( args[0] );
        
        System.out.println( numberOfSolutions(3));
      }

}
