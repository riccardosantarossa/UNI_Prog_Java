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

import java.util.function.*;

public class Board 
{
    //Variabili d'istanza
    private static final String ROWS = " 123456789ABCDEF";
    private static final String COLUMNS = " abcdefghijklmno";
    private int dimensione;
    private int numRegine;
    private final String config;  // --> configurazione della scacchiera

    //Predicato da usare in underAttack
    private final BiPredicate<Integer, Integer> attack;


    //Costruttore di scacchiera VUOTA
    public Board(int n)
    {
        dimensione = n;
        numRegine = 0;
        attack = (x,y) -> false; 
        config = " ";
    }

    //Costruttore di scacchiera NON VUOTA
    private Board(int n, int nQueens, BiPredicate<Integer, Integer> att, String conf)
    {
        dimensione = n;
        numRegine = nQueens;
        attack = att; 
        config = conf;
    }

    //Restituisce la dimensione della scacchiera
    public int size()
    {
        return dimensione;
    }

    //Restituisce il numero di regine sulla scacchiera in quel momento
    public int queensOn()
    {
        return numRegine;
    }

    //Verifica se la casella selezionata è sotto attacco o meno
    public boolean underAttack(int i, int j)
    {
        return attack.test(i,j);
    }

    //Aggiunge una regina nella casella scelta
    public Board addQueen(int i, int j)
    {
        return new Board
        (
            dimensione, 
            numRegine+1, 
            //Controllo del predicato: righe, colonne, diagonali a salire e scendere
            (x, y) -> (x == i || y == j || x-y == i-j || x+y == i+j) || attack.test(x, y),
            config + COLUMNS.charAt(j) + ROWS.charAt(i) + " " 
        );
    }

    //Restituisce la configurazione della scacchiera
    public String arrangement() 
    {
        return config;
    }

    //Restiruisce la configurazione in formato testuale
    public String toString()
    {
        return "[" + arrangement() + "]";
    }

}
