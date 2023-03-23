
/*  tavolaRotonda t = new tavolaRotonda(n)
 * 
 *  t.cavalieriRimasti()          --> int
 *  t.cavaliereBrocca(etichetta)  --> int 
 *  
 *  t.servi()                     --> tavolaRotonda
 *  t.passa()                     --> tavolaRotonda
 */

public class tavolaRotonda 
{
    //ATTRIBUTI
    private int numero; 
    private int brocca;  //etichetta del cavaliere con la brocca
    private intSList altri;

    //METODI

    //Costruttore
    public tavolaRotonda(int n)
    {
        numero = n;
        brocca = 1;
        altri =  creaList(2, n);
    }

    //Costruttore interno
    private tavolaRotonda(int num, int bro, intSList alt)
    {
        numero = num;
        brocca = bro;
        altri = alt;
    }

    //Conteggio dei cavalieri rimasti
    public int cavalieriRimasti() 
    {
        return numero;
    }

    //Controlla chi ha la brocca
    public int cavaliereConBrocca() 
    {
        return brocca;
    }

    //Serve il succo
    public tavolaRotonda servi() 
    {
        return new tavolaRotonda(numero-1, brocca, altri.cdr());
    }

    //Passa la brocca
    public tavolaRotonda passa() 
    {
        if(numero > 1)
        {
            intSList lst = intSList.NULL_INTLIST.cons(brocca);
            return new tavolaRotonda(numero, altri.car(), altri.cdr().append(lst));
        }
        else 
            return this;
        
    }


    //Crea la lista dei cavalieri
    private static intSList creaList(int inf, int sup) 
    {
        if(inf>sup)
            return intSList.NULL_INTLIST;
        else 
        {
            intSList lst = creaList(inf+1, sup);
            return lst.cons(inf);
        }
    }

}
