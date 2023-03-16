public class traduzioni 
{
    
    //Determina se una parola è femminile
    public static boolean femminile (String s) 
    {
        return s.charAt(s.length() -1) == 'a';
    }

    /*OPERAZIONI SULLE STRINGHE
    
    String s = "stringa";

    s.length();     //lunghezza
    s.charAt(pos)   //Carattere in data posizione
    s.concat(t)     //Concatenazione di s a t 

    */
    //Calcolare il lato più lungo di un foglio Ax
    public static final double s0 = Math.pow(2, 0.25);
    public static final double s1 = Math.pow(2, -0.25); 

    public static double calcolaLato (int a) 
    {
        if (a == 2) 
            return calcolaLato( a-2)/2;
        else
            return (a==0 ? s0 : s1);             
    }
  

    /*VALORI NEL SISTEMA TERNARIO BILANCIATO
    public static int btrVal(String btr) 
    {  
        int k = btr.length() -1;

        if (k == 0) 
            return btdVal(btr);
        else
            return 3*btrVal(btr.substring(0, k)) + btdVal(btr.substring(k));
    }*/

    //Ritorna il valore della singola cifra
    private static int btdVal(char btd)
    {
        switch (btd) 
        {
            case '-': return -1;
            
            case '+': return 1;

            case '.': return 0;

            default: return 999;
        }
    }
    
    //-------------------------------------------------------------------------------------//
    
    //PRIMI COSTRUTTI DELLA PROGRAMMAZIONE IMPERATIVA

    //Procedura per il massimo comune divisore
    public static int mcd( int x, int y) //x,y : numeri interi
    {
        while (x != y) 
        {
            if (x < y) 
                y-=x;
            else 
                x-=y;
        }
        return x;
    }

    //Minimo comune multiplo
    public static int mcm(int m, int n) //m,n : numeri interi
    {   
        int x = m;

        while ( x % n == 0) 
        {
            x += m;
        }

        return x;
    }

    //Verifica se un numero è primo
    public static boolean isPrime(int n) 
    {
        if (n == 2) 
            return true;
        else if(n % 2 == 0)
            return false;
        else 
        {
            int a = 3;
            while (a <= Math.sqrt(n)) 
            {
                if (n % a == 0) 
                    return false;
                n += 2;
            }
            return true;
        }
        
    }

    //Restituisce tutti i numeri primi fino ad un certo punto
    public static void stampaPrimi(int n) 
    {
        for (int i = 2; i <= n; i++) 
        {
            if(isPrime(i))
                System.out.println(i);
        }
    }

    //Procedura UFO
    public static int ufo(int x) 
    {
        int[] v = new int[x + 1];
        v[1] = 1;
        
        for (int i = 0; i <= x; i++) 
        {
            if ( i % 2 == 0) 
                v[i] = 2 * v[i/2] -1;
            else
                v[i] = 2 * v[i/2] +1;
        }
        return v[x];
    }


    //Conversione da BTR a intero
    public static int btrToInt(String btr) //btr: stringa nel sistema ternario bilanciato
    {
        int n = 0;

        for (int i = 0; i < btr.length(); i++) 
        {
            n = 3*n + btdVal(btr.charAt(i));
        }

        return n;
    }
    
    //Procedura di ordinamento: INSERTION SORT
    public static void insertionSort(int[] v) //v: array di interi
    {
         
        for (int i = 0; i < v.length; i++) 
        {
           int x = v[i];
           int k = i - 1;

           while ( k>=0 && x<v[k]) 
           {
              v[k+1] = v[k];
              k--;
           }

           v[k+1] = x;
        }

        System.out.println(v);
    }

    public static void main(String[] args) 
    {
        int[] vett = new int[]{1, 7, 2, 4, 35};
        
        insertionSort(vett);
    }
}

