/*
     *  board b              --> tipo dell'oggetto
     * 
     *  b = new board(n)     --> scacchiera vuota di n righe e n colonne   
     * 
     *  b.size()             --> ritorna la dimensione della scacchiera
     * 
     *  b.queensOn()         --> ritorna il numero delle regine presenti sulla scacchiera al momento
     * 
     *  b.underAttack(i,j)   --> dice se la casella indicata è protetta o meno, se lo è non posso mettere la regina
     * 
     *  b.addQueen(i,j)      --> aggiunge una regina nella casella indicata
     * 
     *  b.arrangement()      --> restituisce le caselle occupate dalle regine con coordinate es: "e4"
     *           
*/


public class queens 
{
    //Da in output il numero delle soluzioni
    public static int numSoluzioni(int n) 
    {
        return numCompletamenti(new Board());        
    }

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

    public static boardSList listaSoluzioni(int n) 
    {
        return numCompletamenti(new Board());        
    }

    private static boardSList listaCompletamenti(Board b)
    {
        int n = b.size();
        int numQueens = b.queensOn();
       

        if( numQueens == n)
            return boardSList.NULL_BOARDLIST.cons(b);
        else
        {
            int i = numQueens + 1;
            boardSList lst = boardSList.NULL_BOARDLIST;

            for (int j = 1; j < n; j++) 
            {
                if(!b.underAttack(i,j))
                   lst = lst.append(listaCompletamenti(b.addQueen(i,j)));
            }

            return lst;
        }
    }
}
