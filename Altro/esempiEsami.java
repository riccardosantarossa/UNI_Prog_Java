package Altro;
import java.util.*;

import Huffman.nodo;

public class esempiEsami 
{
    //ESERCIZI FATTI A LEZIONE, ESAME 2020

    //es 2
    public static long recDP(int x, int y, int z)
    {
        long[][] mem = new long[x+1][z+1];

        for (int i = 1; i <= x; i++) 
        {
            for (int j = z; j >= y ; j--) 
            {
                if(i == 1 || j == z)
                    mem[i][j] = 1;
                else
                    mem[i][j] = mem[i-1][j] + i*mem[i][j+1];
            }
        }

        return mem[x][y];
    }


    /*es3
    public static String hanoiIter( Towers hts, int d ) 
    {
        int n = hts.height();
        Stack<int[]> stk = new Stack<int[]>();
        stk.push( new int[]{ n, d } );
        
        while ( !stk.empty()) 
        {
            int[] f = stk.pop();
            n = f[0];
            d = f[1];

            if ( n > 0 ) 
            {
                if ( hts.site(n) == d ) 
                {
                    stk.push( new int[]{n-1, d} );
                }  
                else 
                {
                    int t = hts.transit( n, d );
                    stk.push(new int[] {n-1, d});
                    stk.push(new int[] {-n, d});           ;
                    stk.push(new int[] {n-1, d});
                }
            } else if ( n < 0 ) 
            {
                   hts.move(-n, d);
            }
        }
            return hts.moves();
        }*/





    //-------------------------------------------------------------------------------------------------//
    // FILE ESEMPI D'ESAME

    public static int UNKNOWN = -1;

    //ES1 TEORICAMENTE GIUSTO
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


    //ES4 UN PO' UN CASINO, DA RIVEDERE
    public static int shortestCodeLength( nodo root ) 
    {
        int sc = root.peso();
        Stack<nodo> stack = new Stack<nodo>();
        Stack<Integer> depth = new Stack<Integer>();

        stack.push( root );
        depth.push( 0 );

        do {

            nodo n = stack.pop();
            int d = depth.pop();

            if ( n.foglia()) 
                sc = Math.min( sc, d );

            else if ( d+1 < sc) 
            {
                stack.push(n.sinistro());
                stack.push(n.destro());

        
            }
        } while (!stack.empty() && !depth.empty());
        
        return sc;
    }

    //ES5 FATTO
    public static void closestPair(double[] v)
     {
         double n1=v[0], n2=v[1], diff = Math.abs((n1-n2));
 
         for (int i = 0; i < v.length; i++) 
         {
             for (int j = i+1; j < v.length; j++) 
             {
                 if(Math.abs(v[i] - v[j]) < diff)
                 {
                     diff = Math.abs(v[i] - v[j]);
                     n1 = v[i];
                     n2 = v[j];
                 }
             }
         }
 
         double[] out = new double[] {n1, n2};
         Arrays.sort(out);
 
         for (int i = 0; i < out.length; i++) 
         {
             System.out.print(out[i] + " ");
         }
     }
    
    //ES6 FATTO
    public static int commonStretches(String u, String v)
    {
        int common = 0, num0U = 0, num1U = 0, num0V = 0, num1V = 0;

        for (int i = 0; i < u.length(); i++) 
        {
            String prefixU = u.substring(0, i+1), prefixV = v.substring(0, i+1);

            for (int j = 0; j < prefixU.length(); j++) 
            {
                if(prefixU.charAt(j) == '0')
                    num0U ++;
                else
                    num1U ++;

                if(prefixV.charAt(j) == '0')
                    num0V ++;
                else
                    num1V ++;
            }

            if((num0U == num0V) && (num1U == num1V))
                if(u.charAt(i) == v.charAt(i))
                    common ++;

            num0U = 0; num1U = 0; num0V = 0; num1V = 0;
        }

        return common;
    }

    //ES7 TEORICAMENTE FATTO (commentato perchè frame gli dà fastidio)
    public class Frame 
    {

        public final nodo node;
        public final int depth;
       
        public Frame( nodo n, int prof ) 
        {
            node = n;
            depth = prof;
        }
    } // class Frame

    public static int codeSizeIter( nodo root ) 
        {
            long bits = 0;
            Stack<Frame> stack = new Stack<Frame>();
            //stack.push( new Frame(root, 0));
            do 
            {
                Frame current = stack.pop();
                nodo n = current.node;
                int depth = current.depth;
                
                if(n.foglia())
                  bits = depth * n.peso();
                else
                {
                    //stack.push( new Frame(n.sinistro(), depth+1));
                    //stack.push( new Frame(n.destro(), depth+1));
                }
       
            } while (!stack.empty());
       
            return (int) ( bits / 7 ) + ( (bits%7 > 0) ? 1 : 0 );
        }

    //ES8 FATTO
    public static boolean heapCheck(double[] v)
    {
        int n = v.length;
        int j=1, i;

        for (i = 1 ; i < n; i++) 
        {
           j = 2 * i; 

           if((j<n && v[i] > v[j]))
              return false;

           j = (2*i) + 1;

           if((j<n && v[i] > v[j]))
              return false;
        }

        return true;
    }

    //ES9 CIRCA
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
        /*
            System.out.print(heapCheck(new double[] { 5.0, 3.1, 5.7, 3.1, 8.5, 6.0, 3.0, 4.2, 9.3 }));
            System.out.print(lpsDP("irradiare"));
            System.out.print(llcs3("anna", "aria", "alla"));
            System.out.print(recDP(8, 5, 12));
            closestPair(new double[] {0.3, 0.1, 0.6, 0.8, 0.5, 1.1});
            System.out.print(commonStretches( "001100", "001100" ));
        */
       
    }
}
