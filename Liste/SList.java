package Liste;
public class SList<T> 
{
    /*
     * Operazioni sulle liste nello stile di scheme
     * 
     * null - null?
     * car  - cdr
     * cons 
     * 
     */

     //Variabili di istanza
     //private final boolean empty;
     private final T first;
     private final SList<T> rest;
     public boolean vuoto;


     //Costruttore di lista vuota
     public SList() 
     {
        vuoto = true;
        this.first = null;
        this.rest  = null;
     }

     //Costruttore di lista non vuota
     public SList(T e, SList<T> lst) 
     {
        vuoto = false;
        this.first = e;
        this.rest  = lst;
     }
     

     //Metodo null? di scheme
     public boolean isNull()
     {
        return first == null;
     }


     //Metodo car di scheme
     public T car()
     {
        return this.first;
     }


    //Metodo cdr di scheme
     public SList<T> cdr() 
     {
        return this.rest; 
     }


    //Metodo cons di scheme
    public SList<T> cons(T e)
    {
        return new SList<T>(e, this);
    }

    //Funzione LENGTH
    public int length()
    {
      if(isNull())
         return 0;
      else
         return 1 + this.cdr().length();
    }

    public boolean equals( SList<T> lst) 
    {

      if(lst == null)
         return false; 
      else if(lst.isNull())
         return false;
      else if (isNull())
         return false;
      else if(lst.car().equals(car()))
         return cdr().equals(lst.cdr());
      else 
         return false;
    }

    //Funzione LIST-REF: esercizio
    public T listRef( int k ) 
    {            
         if ( k == 0 ) 
            return car();                        
         else 
            return ( cdr().listRef(k-1) );        
    }   
    
    //Cerca un elemento all'interno della lista
    public static boolean findElement(SList<Integer> lista, int el) 
    {
         boolean result = false;                   
         for( int i=0; i<lista.length(); i++ )
         {
            if ( el == lista.listRef(i) ) 
            {         
               result = true;
            }
         }
      
         return result;
    }

    //Procedura APPEND
    public SList<T> append( SList<T> lst)
    {
      if(isNull())
         return lst;
      else
         return cdr().append(lst).cons(this.car());
    }

    //Procedura REVERSE
    public SList<T> reverse() 
    { 
     
      //Versione imperativa
      SList<T> il = new SList<T>();
      SList<T> rl = this;

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
         SList<T> r = this.cdr();

         while (!r.isNull()) 
         {
            txt += ", " + r.car();
            r = r.cdr();
         }

         return txt + " )";
       }
    }

    //Crea la lista SOLO DI INTERI, le stringhe vanno fatte diversamente
    public static SList<Integer> creaList(int inf, int soup)
    {
      if(inf > soup)
         return new SList<Integer>();
      else
      {
         SList<Integer> lst = SList.creaList(inf+1, soup);
         return lst.cons(inf);
      }
    }
    
    public static void main(String[] args) 
    {
         //SPAZIO PER I TEST
    }

}
