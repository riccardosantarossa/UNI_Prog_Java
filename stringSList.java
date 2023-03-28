public class stringSList 
{
     /*
     * Operazioni sulle liste nello stile di scheme
     * 
     * null - null?
     * car  - cdr
     * cons 
     * 
     */

     public static final stringSList NULL_STRINGLIST = new stringSList();

     //Variabili di istanzaà6
     private final boolean vuota;
     private final String primo;
     private final stringSList resto;


     //Costruttore di lista vuota
     public stringSList() 
     {
        this.vuota = true;
        this.primo = "";
        this.resto  = null;
     }

     //Costruttore di lista non vuota
     public stringSList(String s, stringSList lst) 
     {
        this.vuota = false;
        this.primo = s;
        this.resto  = lst;
     }
     

     //Metodo null? di scheme
     public boolean isNull()
     {
        return this.vuota;
     }


     //Metodo car di scheme
     public String car()
     {
        return this.primo;
     }


    //Metodo cdr di scheme
     public stringSList cdr() 
     {
        return this.resto; 
     }


    //Metodo cons di scheme
    public stringSList cons(String s)
    {
        return new stringSList(s, this);
    }

    //Funzione LENGTH
    public int length()
    {
      if(isNull())
         return 0;
      else
         return 1 + this.cdr().length();
    }

    public boolean equals( stringSList lst) 
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
    public String listRef( int k ) 
    {                                                                      
        if ( k == 0 ) 
            return car();                     
        else 
            return cdr().listRef(k-1);                  
    }

    //Procedura APPEND
    public stringSList append(stringSList lst)
    {
      if(isNull())
         return lst;
      else
         return cdr().append(lst).cons(this.car());
    }

    //Procedura REVERSE
    public stringSList reverse()
    {
        return reverseRec( NULL_STRINGLIST );
    }

    // metodo di supporto: private!
    private stringSList reverseRec( stringSList re ) 
    {
        if ( isNull() ) 
            return re;
         else 
            return cdr().reverseRec( re.cons(car()) );
        
    }

   //Implementazione del toString
    public String toString()
    {
       
       if(this.isNull())
         return "'()";
       else
       {
         String txt = "( " + car();
         stringSList r = cdr();

         while (!r.isNull()) 
         {
            txt += ", " + r.car();
            r = r.cdr();
         }

         return txt + " )";
       }
    }

   public static stringSList test (String btr, int n)
    {
        stringSList lsd= new stringSList();
        ES1 b = new ES1();
        
        for(int i=0; i<n; i++)
        {
            lsd =lsd.cons(btr);
            btr = b.btrSucc(btr);
        }

        return lsd.reverse();
    }

    public static void main(String[] args) 
    {
        //Spazio per i test
    }
    
}
