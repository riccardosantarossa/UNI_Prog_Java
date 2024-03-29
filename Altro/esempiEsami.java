package Altro;
import java.util.*;
import Liste.*;
import Huffman.nodo;

public class esempiEsami 
{
    //ESERCIZI FATTI A LEZIONE, ESAME 2020

    //es2
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



    //ESERCIZI FATTI A LEZIONE, ESAME 2022

    //ES1A
    /*
     *  public static boolean heapCheck(double[] v)
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
     */
    
    //ES2B
    public static int llds( double[] s ) 
    {
        int n = s.length;
        int [][][] mem = new int[n+1][n+1][n+1];
       
        for (int k = 0; k <= n; k++) 
        {
            for (int i = 0; i <= n; i++) 
            {
                for (int j = 0; j <= n; j++) 
                {
                    mem[k][i][j] = UNKNOWN;
                }
            }
        }
       
        return lldsMem( s, 0 , n, n, mem );
    }
       
    private static int lldsMem( double[] s, int k , int i, int j, int [][][] mem ) 
    {

        if (mem[k][i][j] == UNKNOWN) 
        {
            if(k == s.length)
                mem[k][i][j] = 0;
            else if((i == s.length) || ((Math.min(s[i],s[j]) < s[k]) && (s[k] < Math.max(s[i],s[j]))))
                mem[k][i][j] = Math.max(1 + lldsMem(s, k+1, j, k, mem), lldsMem(s, k+1, i, j, mem) );
            else 
                mem[k][i][j] = lldsMem( s, k+1, i, j, mem );
        }

        return mem[k][i][j];
    }

    //ES3B
    public class Pair 
    {
        public final nodo node;
        public final String path;
       
        public Pair(nodo n, String p ) 
        {
            node = n;
            path = p;
        }
    } // class Pair

    public static int codeSizeIter_( nodo root ) 
    {
        long bits = 0;
        Stack<Pair> stack = new Stack<Pair>();
        //stack.push(new Pair(root, ""));
        do 
        {
            Pair current = stack.pop();
            nodo n = current.node;
            String path = current.path;
    
            if(n.foglia())
                bits += path.length() * n.peso();
            else
            {
                //stack.push( new Pair(n.destro(), path + "0"));
                //stack.push(new Pair(n.sinistro(), path + "1"));
            }
                
        } while (!stack.empty());
    
        return (int) ( bits / 7 ) + ( (bits%7 > 0) ? 1 : 0 );
    }

    //ES4B
    public class PairStack 
    {
        private SList<Pair> pairs;
       
        public PairStack() 
        {
            pairs = new SList<Pair>();
        }
       
        public boolean empty() 
        {
            return pairs.isNull();
        }
       
        public void push(Pair pair) 
        {
            pairs = pairs.cons(pair);
        }
       
        public Pair peek() 
        {
            return pairs.car();
        }
       
        public Pair pop() 
        {
            Pair p = pairs.car();
            pairs = pairs.cdr();

            return p;
        }

    } // class PairStack


    //ESERCIZI A LEZIONE, ESAMI MISTI

    //esame 2020 es.4 
    public class Towers
    {
        //Variabili di istanza
        private final int n; 
        private int[] rods;
        private String moves;

        public Towers(int d)
        {
            n = d;
            rods = new int[n+1];
            moves = "";
        }

        public void put(int disk, int rod)
        {
            rods[disk] = rod;
        }

        public void move(int disk, int dst)
        {
           moves += " " + rods[disk] + "->" + dst;
        }

        public int height() 
        {
            return n;
        }

        public int site(int disk) 
        {
            return rods[disk];
        }

        public int transit(int disk, int dst)
        {
            return (6 - site(disk) - dst);
        }

        public String moves()
        {
            return moves;
        }
    }


    //ESAME 06/2011 es. 4
    public static long f( int x, int y ) { // x, y ≥ 0
        if ( (x < 2) || (y < 2) ) {
        return x * y;
        } else {
        return f( x, y-1 ) + f( x-2, y ) + f( x-1, y+1 );
        }
        }

    public static long fDP(int x, int y)
    {
        long[][] mem = new long[x+1][y+1];

        for (int i = 0; i <= x; i++) 
        {
            for (int j = y; j > 0; j--) 
            {
                if( i < 2 || j < 2)
                    mem[i][j] = i * j;
                else
                    mem[i][j] = mem[i][j-1] + mem[i-2][j] + mem[i-1][j+1];
            }  
        }

        return mem[x][0];
    }


    //ESAME 07/2011 es.4
    public static long g(int x, int y)
    {
        long[][] mem = new long[x+1][y+1];

        for (int i = 0; i <= x; i++) 
        {
            for (int j = 0; j <= y; j++) 
            {
                mem[i][j] = UNKNOWN;
            }
        }

        return gMem(x, y, mem);
    }

    public static long gMem(int x, int y, long[][] mem)
    {
        if(mem[x][y] == UNKNOWN)
        {
            if ( (x < 2) || (y < 2) ) 
                mem[x][y] = x * y;
            else 
                mem[x][y] = f( x, y-1 ) + f( x-2, y ) + f( x-1, y+1 );
        }

        return mem[x][y];
    }


    //ESAME 06/2010 es.2
    public static long fm( int i, int j ) {
        long[][] h = new long[ i+j+1 ][];
        for ( int u=0; u<h.length; u=u+1 ) {
        h[u] = new long[ h.length-u ];
        for ( int v=0; v<h[u].length; v=v+1 ) {
        h[u][v] = UNKNOWN;
        }}
        return mem( i, j, h );
        }
    
    public static long mem( int i, int j, long[][] h ) {
        if ( h[i][j] == UNKNOWN ) {
        if ( i+j < 2 ) {
        h[i][j] = i+j;
        } else if ( j == 0 ) {
        h[i][j] = mem( 1, i-2, h ) + mem( 0, i-1, h );
        } else if ( j == 1 ) {
        h[i][j] = mem( 0, i, h ) + mem( i+1, 0, h );
        } else {
        h[i][j] = mem( i+2, j-2, h ) + mem( i+1, j-1, h );
        }}
        return h[i][j];
        }
        
    
    //ESAME 09/2010
    public static long fuckIt( int i, int j, int k ) { // i, j, k ≥ 0
        if ( i*j*k == 0 ) {
        return 1;
        } else {
        return fuckIt( i-1, j, k ) + fuckIt( i, j-1, k ) + fuckIt( i, j, k-1 );
        }}
        
    public static long fuckItDP( int i, int j, int k )
    {
        long[][][] mem = new long[i+1][j+1][k+1];

        for (int l = 0; l <= i; l++) 
        {
            for (int l2 = 0; l2 <= j; l2++) 
            {
                for (int m = 0; m <= k; m++) 
                {
                    if(l * l2 * m == 0)
                        mem[l][l2][m] = 1;
                    else
                        mem[l][l2][m] = mem[l-1][l2][m] + mem[l][l2-1][m] + mem[l][l2][m-1];

                }   
            }
        }

        return mem[i][j][k];
    }


    //ESAME 06/2019 es.3
    public static long st( int n, int k ) 
    { // n, k > 0
        long[] ct = new long[] { 0 }; // contatore: variabile di stato
        sRec( n, k, 1, ct );
        return ct[0];
    }

    private static void sRec( int n, int k, int q, long[] ct ) 
    {
        if ( (k == 1) || (k == n) ) 
        {
            ct[0] = ct[0] + q;
        } else 
        {
            sRec( n-1, k-1, q, ct );
            sRec( n-1, k, k*q, ct );
        }
    }
   
    public static long stIter( int n, int k ) // n, k > 0
    { 
        long[] ct = new long[] { 0 };
        Stack<int[]> stack = new Stack<int[]>();
        
        int[] f = new int[] {n, k, 1};
        stack.push( f );

        while ( !stack.empty()) 
        {
            f = stack.pop();

            if ( (f[1] == 1) || ( f[1] == f[0]) ) 
            {
                ct[0] = ct[0] + f[2];

            } else 
            {
                stack.push( new int[] { f[0] - 1, f[1] - 1, f[2]} );
                stack.push( new int[] { f[0] - 1, f[1], f[1] * f[2]} );
            }
        }
            return ct[0];
    }

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

    //ES2
    public static boolean matrSimm(int[][] m)
    {
        int dim = m.length;
        int[][] trasp = new int[dim][dim];

        //costruisco la trasposta
        for (int i = 0; i < trasp.length; i++) 
        {
            for (int j = 0; j < trasp.length; j++) 
            {
                trasp[i][j] = m[j][i];
            }    
        }

        //confronto la matrice con la trasposta appena costruita
        for (int i = 0; i < trasp.length; i++) 
        {
            for (int j = 0; j < trasp.length; j++) 
            {
                if(m[i][j] != trasp[i][j])
                    return false;
            }
        }

        return true;
    }

    //ES3 (scrivo solo i due metodi, istanzio un oggetto board per non modificare il file della classe)
    /*public static boolean isFreeRow(int i)
    {
        Board b = new Board(i);
        
        for (int j = 0; j < b.size(); j++) 
        {
            if(b.underAttack(i, j))
                return false;
        }

        return true;
    }*/

    public static void addQueen(String pos)
    {
        /*int i = pos.charAt(0), j = pos.charAt(1);
        int n = dimensione;

        numRegine ++;
        righe[i-1] = righe[i-1] + 1;
        colonne[j-1] = colonne[j-1] + 1;
        diagAsc[i-j+n-1] = diagAsc[i-j+n-1] + 1;
        diagDesc[i+j-2] = diagDesc[i+j-2] + 1;
        config = config + j + i + " ";*/
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
                  bits += depth * n.peso();
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
            System.out.print(matrSimm(new int[][]{{1,0,3}, {2,4,6}, {7,0,1}}));
            System.out.println(f(7,8));
            System.out.println(g(7,8));
            System.out.println(fuckIt(3,2,1));
            System.out.println(fuckItDP(3,2,1));
        */
            System.out.print(stIter(6, 3));
    }
}
