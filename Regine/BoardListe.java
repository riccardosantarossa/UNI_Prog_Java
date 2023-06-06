package Regine;


public class BoardListe 
{
    
    //Variabili di istanza
    private static final String ROW = "123456789ABCDEF";
    private static final String COL = "abcdefghijklmno";
    private int dimScacchiera;
    private int numRegine;
    private  IntSList lstRighe, lstColonne, lstDiagAsc, lstDiagDesc;
    private final String config;
   

    //Costruttore di scacchiera VUOTA
    public BoardListe(int n)
    {
        dimScacchiera = n;
        numRegine = 0;
        lstRighe = new IntSList();
        lstColonne = new IntSList();
        lstDiagAsc = new IntSList();
        lstDiagDesc = new IntSList();
        config = " ";
    }


    //Costruttore di scacchiera NON VUOTA
    private BoardListe(BoardListe b, int i, int j)
    {
        dimScacchiera = b.size();
        numRegine = b.queensOn() + 1;
        lstRighe = b.lstRighe.cons(i);
        lstColonne = b.lstColonne.cons(j);
        lstDiagAsc = b.lstDiagAsc.cons(i-j);
        lstDiagDesc = b.lstDiagDesc.cons(i+j);
        config = b.arrangement() + " " + COL.charAt(j) + ROW.charAt(i);
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
        return
        (
            IntSList.findElement(lstRighe, i) ||
            IntSList.findElement(lstColonne, j) ||
            IntSList.findElement(lstDiagAsc, i-j) ||
            IntSList.findElement(lstDiagDesc, i+j)
        ); 
    }   

    public BoardListe aggiungiRegina(int i, int j)
    {
        return new BoardListe(this, i, j);
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
    private static int numberOfCompletions( BoardListe b ) 
    {
    
        int n = b.size();
        int q = b.queensOn();
        
        if ( q == n ) 
            return 1;
        else 
        {
        
          int i = q + 1;
          int count = 0;
          
          for ( int j=1; j<=n; j=j+1 ) 
          {
            if ( !b.sottoAttacco(i,j) ) 
              count = count + numberOfCompletions( b.aggiungiRegina(i,j) );
          }
          return count;
        }
      }

      public static int numberOfSolutions( int n ) {
      
        return numberOfCompletions( new BoardListe(n) );
      }

      public static void main( String args[] ) 
      {
    
        System.out.println(numberOfSolutions(8));

      }

}
