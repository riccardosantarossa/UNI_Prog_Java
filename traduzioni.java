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
}

