package Altro;

public class esempiEsami 
{
    //-------------------------------------------------------------------------------------------------//
    // FILE ESEMPI D'ESAME

    public static int UNKNOWN = -1;

    //ES1
    public static int llcs3(String s, String t, String u)
    {
        int n = s.length(), m = t.length(), o = u.length();
        int[][][] mem = new int[n+1][m+1][o+1];

        for (int i = 0; i <= n; i++) 
        {
            for (int j = 0; j <= m; j++) 
            {
                for (int k = 0; k <= o; k++) 
                {
                    mem[i][j][k] = UNKNOWN; //UNKNOWN
                }
            }
        }

        return llcs3Mem(s,t,u,mem);
    }

    public static int llcs3Mem(String s, String t, String u, int[][][] mem)
    {
        int m = u.length(), n = t.length(), o = u.length();
        
        if(mem[m][n][o] == UNKNOWN)
        {
            if(m == 0 || n == 0 || o == 0)
                mem[m][n][o] = 0;
            else if ((s.charAt(0) == t.charAt(0)) && (t.charAt(0) == u.charAt(0)))
                mem[m][n][o] = 1 + llcs3Mem(u.substring(1), t.substring(1), u.substring(1), mem);
            else 
                mem[m][n][o] = Math.max(llcs3Mem(s, t.substring(1), u,  mem), Math.max(llcs3Mem(s, t, u.substring(1),mem),llcs3Mem(s.substring(1), t, u,mem)));
        }

        return mem[m][n][o];
    }

    

    //ES8
    public static boolean heapCheck(double[] v)
    {
        int n = v.length;
        int j, h;

        for (int i = 0; i < n; i++) 
        {

            j=2*i;
            h=j+1;

            if(h<n && v[i]>v[h])
                return false;
            
            if(j<n && v[i]>v[j])
                return false;            
        }

        return true;
    }


    //ES9 
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
        //System.out.print(heapCheck(new double[] { 5.0, 3.1, 5.7, 3.1, 8.5, 6.0, 3.0, 4.2, 9.3 }));
        //System.out.print(lpsDP("irradiare"));
        System.out.print(llcs3("anna", "aria", "alla"));
    }
}
