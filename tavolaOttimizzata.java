public class tavolaOttimizzata 
{
    //ATTRIBUTI
    private int numero; 
    private int brocca;  //etichetta del cavaliere con la brocca
    private SList<Integer> altri;
    private SList<Integer>codaRovescia;

    //METODI

    //Costruttore
    public tavolaOttimizzata(int n)
    {
        numero = n;
        brocca = 1;
        altri =  creaList(2, n);
        codaRovescia = new SList<Integer>();
    }

    //Costruttore interno
    private tavolaOttimizzata(int num, int bro, SList<Integer> alt, SList<Integer> cod)
    {
        numero = num;
        brocca = bro;
        altri = alt;
        codaRovescia = cod;
    }

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
    public tavolaOttimizzata servi() 
    {
        if(numero > 1)
        {
            if(altri.isNull())
                return new tavolaOttimizzata(numero-1, brocca, codaRovescia.reverse().cdr(), intSList.NULL_INTLIST);
            else
                return new tavolaOttimizzata(numero-1, brocca, altri.cdr(), codaRovescia);
        }
        else
            return this;
    }

    //Passa la brocca
    public tavolaOttimizzata passa() 
    {
        if(numero > 1)
        {
            if(altri.isNull())
            {
                SList<Integer> lst = codaRovescia.cons(brocca).reverse();
                return new tavolaOttimizzata(numero, lst.car(), lst.cdr(), intSList.NULL_INTLIST);
            }
            else
                return new tavolaOttimizzata(numero, altri.car(), altri.cdr(), codaRovescia.cons(brocca));
        }
        else 
            return this;
        
    }


    //Crea la lista dei cavalieri
    private static SList<Integer> creaList(int inf, int sup) 
    {
        SList<Integer> lst = new SList<Integer>();

        for(int i = sup; i>=inf; i --)
            lst = lst.cons(i);

        return lst;
    }

}
