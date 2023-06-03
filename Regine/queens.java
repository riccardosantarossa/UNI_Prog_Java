package Regine;
import Liste.SList;

public class queens 
{
    //Da in output il numero delle soluzioni
    public static int numSoluzioni(int n) 
    {
        return numCompletamenti(new Board(n));        
    }

    //Restituisce il numero di possibili soluzioni
    private static int numCompletamenti(Board b)
    {
        int n = b.size();
        int numQueens = b.queensOn();
        int cont = 0;

        if( numQueens == n)
            return 1;
        else
        {
            int i = numQueens + 1;

            for (int j = 1; j < n; j++) 
            {
                if(!b.underAttack(i,j))
                {
                    b.addQueen(i,j);
                    cont += numCompletamenti(b);
                    b.removeQueen(i,j); //tolgo la regina per continuare ad analizzare la scacchiera con uno stato diverso
                }
            }
            return cont;
        }
    }

    //-------------------------------------------------------------------------------------------//

    private static final SList<String> NULL_LIST = new SList<String>();

    //Calcola la lista di possibili soluzioni
    public static SList<String> listaSoluzioni(int n) 
    {
        return listaCompletamenti(new Board(n));        
    }

     //Restituisce la lista di possibili soluzioni
     private static SList<String> listaCompletamenti(Board b)
     {
         int n = b.size();
         int numQueens = b.queensOn();
        
         if( numQueens == n)
             return NULL_LIST.cons(b.toString());
         else
         {
             int i = numQueens + 1;
             SList<String> lst = NULL_LIST;
 
             for (int j = 1; j < n; j++) 
             {
                 if(!b.underAttack(i,j))
                 {
                     b.addQueen(i,j);
                     lst = lst.append(listaCompletamenti(b));
                 }
             }
             
             return lst;
         }
     }

    //-------------------------------------------------------------------------------------------//

    public static void main(String[] args) 
    {
        System.out.println(numSoluzioni(3));
        //System.out.println(listaSoluzioni(3));    
    }

}
