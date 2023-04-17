public class RoundTable {

    private int numero; 
    private int brocca;  //etichetta del cavaliere con la brocca
    private SList<Integer> altri;
    private SList<Integer> codaRovescia;

    //METODI

    //Costruttore
    public RoundTable(int n)
    {
        numero = n;
        brocca = 1;
        altri =  SList.creaList(2, n);
        codaRovescia = new SList<Integer>();
    }

    private RoundTable(int num, int bro, SList<Integer> alt, SList<Integer> cod)
    {
        numero = num;
        brocca = bro;
        altri = alt;
        codaRovescia = cod;
    }

    public int numberOfKnights() 
    {
        return numero;
    }

    //Controlla chi ha la brocca
    public SList<Integer> servingKnights() 
    {
        SList<Integer> ls = new SList<Integer>();
        ls.cons(brocca);
        ls.cons(altri.car());
        return ls.reverse();
    }

    public RoundTable serveNeighbour() 
    {
        SList<Integer> ls = new SList<Integer>();
        ls.cons(brocca);
        ls.cons(altri.car());
        if(numero > 2)
        {
            if(altri.isNull())
                return new RoundTable(numero-1, brocca, codaRovescia.reverse().cdr(), new SList<Integer>());
            else
                return new RoundTable(numero-1, brocca, ls.append(altri.cdr().cdr()), codaRovescia);
        }
        else
            return this;
    }

    //Passa la brocca
    public RoundTable passJug() 
    {
        //intSList ls = intSList.NULL_INTLIST;

        if(numero > 2)
        {
            if(altri.isNull())
            {
                SList<Integer> lst = codaRovescia.cons(brocca).reverse();
                return new RoundTable(numero, lst.car(), lst.cdr(), new SList<Integer>());
            }
            else
                return new RoundTable(numero, altri.cdr().cdr().cdr().car(), altri.cdr(), codaRovescia.cons(brocca));
        }
        else 
            return this;
        
    }


    
}