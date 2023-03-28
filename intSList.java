public class intSList 
{
    /*
     * Operazioni sulle liste nello stile di scheme
     * 
     * null - null?
     * car  - cdr
     * cons 
     * 
     */

     public static final intSList NULL_INTLIST = new intSList();

     //Variabili di istanzaà6
     private final boolean empty;
     private final int first;
     private final intSList rest;



     //Costruttore di lista vuota
     public intSList() 
     {
        this.empty = true;
        this.first = 0;
        this.rest  = null;
     }

     //Costruttore di lista non vuota
     public intSList(int n, intSList lst) 
     {
        this.empty = false;
        this.first = n;
        this.rest  = lst;
     }
     

     //Metodo null? di scheme
     public boolean isNull()
     {
        return this.empty;
     }


     //Metodo car di scheme
     public int car()
     {
        return this.first;
     }


    //Metodo cdr di scheme
     public intSList cdr() 
     {
        return this.rest; 
     }


    //Metodo cons di scheme
    public intSList cons(int n)
    {
        return new intSList(n, this);
    }

    //Funzione LENGTH
    public int length()
    {
      if(isNull())
         return 0;
      else
         return 1 + this.cdr().length();
    }

    public boolean equals( intSList lst) 
    {

      if(lst == null)
         return false; 
      else if(lst.isNull())
         return false;
      else if (isNull())
         return false;
      else if( lst.car() == car())
         return cdr().equals(lst.cdr());
      else 
         return false;
    }

    //Funzione LIST-REF: esercizio
    public int listRef(intSList lst, int n)
    {
        int index = 0;

        while (!lst.isNull()) 
        {
            if(n == lst.car())
               break;
            else
            {
               index ++;
               lst = lst.cdr();
            }
        }
        return index;
    }

    //Procedura APPEND
    public intSList append( intSList lst)
    {
      if(isNull())
         return lst;
      else
         return cdr().append(lst).cons(this.car());
    }

    //Procedura REVERSE
    public intSList reverse() 
    { 
     
      //Versione imperativa
      intSList il = this;
      intSList rl = NULL_INTLIST;

      while(!il.isNull())
      {
         rl = rl.cons(il.car());
         il = il.cdr();
      }
         return rl;
    }


   //Implementazione del toString
    public String toString()
    {
       
       if(this.isNull())
         return "'()";
       else
       {
         String txt = "( ";
         intSList r = this.cdr();

         while (!r.isNull()) 
         {
            txt += ", " + r.car();
            r = r.cdr();
         }

         return txt + " )";
       }
    }

    public static intSList creaList(int inf, int soup)
    {
      intSList lsd = new intSList();
       
      for (int i = inf; i < soup; i++) 
      {
         lsd = lsd.cons(i);
      }

      return lsd;
    }


    
    public static void main(String[] args) 
    {
         //SPAZIO PER I TEST
    }

}
