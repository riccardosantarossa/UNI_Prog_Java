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
  

    //VALORI NEL SISTEMA TERNARIO BILANCIATO
    public static int btrVal(String btr) 
    {  
        int k = btr.length() -1;

        if (k == 0) 
            return btdVal(btr);
        else
            return 3*btrVal(btr.substring(0, k)) + btdVal(btr.substring(k));
    }

    //Ritorna il valore della singola cifra
    private static int btdVal(String btd)
    {
        switch (btd) 
        {
            case "-": return -1;
            
            case "+": return 1;

            case ".": return 0;

            default: return 999;
        }
    }



}

