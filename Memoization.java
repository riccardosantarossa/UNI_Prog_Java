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

    private static final int UNKNOWN = -1;

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

    //---------------------------------------------------------------------------------------------//
    
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

    //---------------------------------------------------------------------------------------------//

    //TERZO ESEMPIO: SOTTOSEQUENZA COMUNE + LUNGA

    //Procedura di costruzione della matrice
    private static int llcsMem(String u, String v, int[][] mem) 
    {
        int m = u.length(), n = v.length();
        
        if(mem[m][n] == UNKNOWN)
        {
            if(m == 0 || n == 0)
                mem[m][n] = 0;
            else if (u.charAt(0) == v.charAt(0))
                mem[m][n] = 1 + llcsMem(u.substring(1), v.substring(1), mem);
            else 
                mem[m][n] = Math.max(llcsMem(u, v.substring(1), mem), llcsMem(u.substring(1), v, mem));
        }

        return mem[m][n];
    }
    
    //Procedura con memoisation : LUNGHEZZA DELLA LCS
    public static int llcs(String u, String v) 
    {
        int m = u.length(), n = v.length();
        
        int[][] mem = new int[m+1][n+1];

        for (int k = 0; k <=m; k++) 
        {
            for (int l = 0; l <=n; l++) 
            {
                mem[k][l] = UNKNOWN;
            }
        }

        return llcsMem(u, v, mem);
    }


    //Restituisce la sottostringa e non la lunghezza
    private static String lcsMem(String u, String v, String[][] mem) 
    {
        int m = u.length(), n = v.length();
        
        if(mem[m][n] == null)
        {
            if(m == 0 || n == 0)
                mem[m][n] = "";
            else if (u.charAt(0) == v.charAt(0))
                mem[m][n] = u.charAt(0) + lcsMem(u.substring(1), v.substring(1), mem);
            else 
                mem[m][n] = longer(lcsMem(u, v.substring(1), mem), lcsMem(u.substring(1), v, mem));
        }

        return mem[m][n];
    }

    private static String longer(String u, String v)
    {
        int m = u.length(), n = v.length();

        if(m < n)
            return v;
        else if (m > n)
            return u;
        else if(Math.random() < 0.5)
            return v;
        else 
            return u;            
    }
    
    //Procedura con memoisation :  LCS
    public static String lcs(String u, String v) 
    {
        int m = u.length(), n = v.length();
        
        String[][] mem = new String[m+1][n+1];

        for (int k = 0; k <=m; k++) 
        {
            for (int l = 0; l <=n; l++) 
            {
                mem[k][l] = null;
            }
        }

        return lcsMem(u, v, mem);
    }

    
    //Versione con DYNAMIC PROGRAMMING
    public static int llcsDP(String u, String v) 
    {
        int m = u.length(), n = v.length();
        int k,l;
        
        int[][] mem = new int[m+1][n+1];

        for (l = 0; l < n; l++) 
            mem[0][l] = 0;

        for (k = 0; k < m; k++) 
            mem[k][0] = 0;

        for (k = 1; k <= m; k++) 
        {
            for (l = 1; l <= n; l++) 
            {
                if(u.charAt(m-l) == v.charAt(n-k))
                    mem[k][l] = 1 + mem[k-1][l-1];
                else
                    mem[k][l] = Math.max(mem[k-1][l], mem[k][l-1]);
            }
        }

        return mem[m][n];
    }


    //---------------------------------------------------------------------------------------------//

     //SPAZIO PER I TEST    
     public static void main(String[] args) 
     {
         //System.out.println(fib(4));
         //System.out.println(manhM(20, 20));
         System.out.println(llcsDP("arto", "atrio"));
     }

}
