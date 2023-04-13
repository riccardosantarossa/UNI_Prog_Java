
public class queens 
{
    //Da in output il numero delle soluzioni
    public static int numSoluzioni(int n) 
    {
        return numCompletamenti(new Board());        
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
                   cont += numCompletamenti(b.addQueen(i,j));
            }

            return cont;
        }

    }

    //Da in output la lista delle soluzioni
    public static SList<Board> listaSoluzioni(int n) 
    {
        return numCompletamenti(new Board());        
    }

    //Restituisce la lista di possibili soluzioni
    private static SList<Board> listaCompletamenti(Board b)
    {
        int n = b.size();
        int numQueens = b.queensOn();
       
        if( numQueens == n)
            return boardSList.NULL_BOARDLIST.cons(b);
        else
        {
            int i = numQueens + 1;
            SList<Board> lst = new SList<Board>();

            for (int j = 1; j < n; j++) 
            {
                if(!b.underAttack(i,j))
                   lst = lst.append(listaCompletamenti(b.addQueen(i,j)));
            }

            return lst;
        }
    }
}
