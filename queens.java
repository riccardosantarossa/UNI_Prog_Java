
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
                }
            }
            return cont;
        }

    }

    //Da in output la lista delle soluzioni
    public static SList<Board> listaSoluzioni(int n) 
    {
        return listaCompletamenti(new Board(n));        
    }

    //Restituisce la lista di possibili soluzioni
    private static SList<Board> listaCompletamenti(Board b)
    {
        int n = b.size();
        int numQueens = b.queensOn();
       
        if( numQueens == n)
            return new SList<Board>().cons(b);
        else
        {
            int i = numQueens + 1;
            SList<Board> lst = new SList<Board>();

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
}
