public class RoundTable {

    private int numero; 
    private int brocca;  //etichetta del cavaliere con la brocca
    private intSList altri;
    private intSList codaRovescia;

    //METODI

    //Costruttore
    public RoundTable(int n)
    {
        numero = n;
        brocca = 1;
        altri =  intSList.creaList(2, n);
        codaRovescia = intSList.NULL_INTLIST;
    }

    private RoundTable(int num, int bro, intSList alt, intSList cod)
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
    public intSList servingKnights() 
    {
        intSList ls = intSList.NULL_INTLIST;
        ls.cons(brocca);
        ls.cons(altri.car());
        return ls.reverse();
    }

    public RoundTable serveNeighbour() 
    {
        intSList ls = intSList.NULL_INTLIST;
        ls.cons(brocca);
        ls.cons(altri.car());
        if(numero > 2)
        {
            if(altri.isNull())
                return new RoundTable(numero-1, brocca, codaRovescia.reverse().cdr(), intSList.NULL_INTLIST);
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
                intSList lst = codaRovescia.cons(brocca).reverse();
                return new RoundTable(numero, lst.car(), lst.cdr(), intSList.NULL_INTLIST);
            }
            else
                return new RoundTable(numero, altri.cdr().cdr().cdr().car(), altri.cdr(), codaRovescia.cons(brocca));
        }
        else 
            return this;
        
    }


    
}