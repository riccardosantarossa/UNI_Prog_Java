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
        if(lstRighe.isNull() || lstColonne.isNull() || lstDiagAsc.isNull() || lstDiagDesc.isNull())
            return false;

        else if(i == lstRighe.car() || j == lstColonne.car() || i+j == lstDiagAsc.car() || i-j == lstDiagDesc.car())
            return true;
        
        else
        {
            lstRighe.cons(i);
            lstColonne.cons(j);
            lstDiagAsc.cons(i+j);
            lstDiagDesc.cons(i-j);
            return false;
        }
            
    }   

    public BoardListe aggiungiRegina(int i, int j)
    {
        return new BoardListe
        (  dimScacchiera,
           numRegine+1,
           lstRighe,
           lstColonne, 
           lstDiagAsc, 
           lstDiagDesc, 
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

}
