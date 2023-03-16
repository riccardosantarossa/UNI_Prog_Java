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

     private boolean empty;
     private int first;
     private intSList rest;


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

}
