public class Memoization 
{
    //PRIMO ESEMPIO: FIBONACCI

    //Procedura di fibonacci CON RICORSIVA: EWW
    public static long fib(int n)
    {
        if(n < 2)
            return 1;
        else
            return fib(n-2) + fib(n-1);

    }

    //Creazione e riempimento dell'array che contiene i valori noti
    public static long fibM(int n) 
    {
        long[] mem = new long[n + 1];

        for (int i = 0; i < n; i++) 
            mem[i] = UNKNOWN;
        
        return fibMem(n, mem);
    }

    private static final int UNKNOWN = 0;

    //Procedura di fibonacci SENZA RICORSIVA: DAJE
    private static long fibMem(int n, long[] mem)
    {
        if(mem[n] == UNKNOWN)
        {
            if(n < 2)
                mem[n] = 1;
            else
                mem[n] = fibMem(n-2, mem) + fibMem(n-1, mem);
        }

        return mem[n];
    }

    
    //SECONDO ESEMPIO: MANHATTAN

    //Calcola il numero di percorsi di manhattan come SCHEME
    public static long manh(int i, int j)
    {
        if(i == 0 || j == 0)
            return 1;
        else
        {
            return manh(i-1, j) + manh(i, j-1);
        }
    }

    //Inizializzo la matrice
    public static long manhM(int i, int j)
    {
        long[][] mem = new long[i+1][j+1];

        for (int k = 0; k <=i; k++) 
            for (int l = 0; l <=j; l++) 
                mem[k][l] = UNKNOWN;
                
        return manhMem(i, j, mem);
    }

    //Calcola il numero di percorsi di manhattan con una matrice
    private static long manhMem(int i, int j, long[][] mem)
    {
        if(mem[i][j] == UNKNOWN)
        {
            if(i == 0 || j == 0)
                mem[i][j] = 1;
            else
                mem[i][j]  = manhMem(i-1, j, mem) + manhMem(i, j-1, mem);
        }

        return mem[i][j];
    }

    //Versione alternativa SENZA RICORSIONE
    public static long manhOP(int i, int j)
    {
        long[][] mem = new long[i+1][j+1];

        for (int k = 0; k <=i; k++) 
        {
            for (int l = 0; l <=j; l++) 
            {
                if(k == 0 || l == 0)
                    mem[k][l] = 1;
                else
                    mem[k][l] = mem[k-1][l] + mem[k][l-1];
            }
        }

        return mem[i][j];
    }



    //SPAZIO PER I TEST    
    public static void main(String[] args) 
    {
        //System.out.println(fib(4));
        //System.out.println(manhM(20, 20));
    }
}
