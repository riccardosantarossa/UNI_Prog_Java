public class Memoization 
{
    //Procedura di fibonacci CON RICORSIVA: EWW
    public static int fib(int n)
    {
        if(n < 2)
            return 1;
        else
            return fib(n-2) + fib(n-1);

    }

    //Creazione e riempimento dell'array che contiene i valori noti
    public static int fibM(int n) 
    {
        int[] mem = new int[n + 1];

        for (int i = 0; i < n; i++) 
            mem[i] = UNKNOWN;
        
        return fibMem(n, mem);
    }

    private static final int UNKNOWN = 0;

    //Procedura di fibonacci SENZA RICORSIVA: DAJE
    private static int fibMem(int n, int[] mem)
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

    
    //SPAZIO PER I TEST    
    public static void main(String[] args) 
    {
        //System.out.println(fib(4));
    }
}
