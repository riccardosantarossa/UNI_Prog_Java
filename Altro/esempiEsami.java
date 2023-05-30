package Altro;

public class esempiEsami 
{
    //-------------------------------------------------------------------------------------------------//
    // FILE ESEMPI D'ESAME

    //ES8
    public static boolean heapCheck(double[] v)
    {
        int n = v.length;
        int j, h;

        for (int i = 0; i < n; i++) 
        {

            j=2*i;
            h=j+1;

            if(h<n && v[i]<v[h])
                return false;
            
            if(j<n && v[i]<v[j])
                return false;            
        }

        return true;
    }


    /*ES9
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

    public static String lpsDP( String s ) 
    {
        int n = s.length();
        String[][] mem = new String[n+1][n+1];

        for ( int k=0; k<=n; k=k+1 ) 
        {
            for ( int i=0; i<=n-k; i=i+1 ) 
            {
            // k : lunghezza della sottostringa s* di s considerata;
            // i : posizione di s* in s:
            // s* corrisponde al potenziale argomento di una invocazione ricorsiva di lps.
                if ( k < 2 ) 
                    mem[i][k] = "" + s.charAt(k);
            
                else if ( s.charAt(i) == s.charAt(i+k-1) ) 
                
                    mem[i][k] += s.charAt(i) + mem[i+1][k] + s.charAt(n-1); 
                
                else 
                
                    mem[i][k] = longer(mem[0][k-1], mem[1][k]);
            }

            }
            return mem[n][n];
        }*/

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

        public static String lpsDP(String s) {
            int n = s.length();
            String[][] mem = new String[n][n];
        
            for (int k = 0; k < n; k++) {
                for (int i = 0; i < n - k; i++) {
                    if (k < 2) {
                        mem[i][i + k] = s.substring(i, i + k + 1);
                    } else if (s.charAt(i) == s.charAt(i + k)) {
                        mem[i][i + k] = s.charAt(i) + mem[i + 1][i + k - 1] + s.charAt(i + k);
                    } else {

                        mem[i][i + k] = longer(mem[i][i + k - 1], mem[i + 1][i + k]);
                    }
                }
            }
        
            return mem[0][n - 1];
        }
    
    //-------------------------------------------------------------------------------------------------//

    public static void main(String[] args) 
    {
        //System.out.print(heapCheck(new double[] { 5.0, 3.1, 5.7, 3.1, 8.5, 6.0, 3.8, 4.2, 9.3 }));
        System.out.print(lpsDP("irradiare"));
    }
}
