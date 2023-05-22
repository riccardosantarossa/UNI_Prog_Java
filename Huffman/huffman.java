package Huffman;
import java.util.*;
import huffman_toolkit.*;

public class huffman
{
    public static int[] freqChar(String src) 
    {
        InputTextFile in = new InputTextFile(src);

        int[] conto = new int[InputTextFile.CHARS];

        for (int i = 0; i < conto.length; i++) 
        {
            conto[i] = 0;
        }

        while (in.textAvailable())
        {
            char c = in.readChar();
            conto[c] = conto[c] +1;        
        }

        in.close();

        return conto;
    }

    public static nodo alberoHuffman(int[] freq) 
    {
        PriorityQueue<nodo> coda = new PriorityQueue<nodo>();

        for (int i = 0; i < freq.length; i++) 
        {
            if(freq[i] > 0)
            {
                nodo n = new nodo((char) i, freq[i]);
                coda.add(n);
            }
        }

        while (coda.size() > 1) 
        {
            nodo l = coda.poll();
            nodo r = coda.poll();

            nodo rad = new nodo(l, r);
            coda.add(rad);
        }
        
        return coda.poll();
    }
    
    //Versione iterativa
    public static String[] tabHuffman(nodo rad)
    {
        Stack<coppia> stack= new Stack<coppia>();
        stack.push(new coppia(rad, ""));

        String[] tab = new String[InputTextFile.CHARS];

        do 
        {
            coppia c = stack.pop();
            nodo n = c.nodo;
            String percorso = c.percorso; 

            if(n.foglia())
                tab[n.simbolo()] = percorso;

            else
            {
                stack.push(new coppia(n.destro(), percorso + "1"));
                stack.push(new coppia(n.sinistro(), percorso + "0"));
            }
                
        } while (!stack.empty());
        

        return tab;
    }

    /* VERSIONE RICORSIVA
    private static void compilaTab(nodo n, String cod, String[] tab) 
    {
        if(n.foglia())
            tab[n.simbolo()] = cod;
        else
        {
            compilaTab(n.sinistro(), cod + "0", tab);
            compilaTab(n.destro(), cod + "1", tab);
        }
    }*/

    /*VERSIONE RICORSIVA 
    private static void compilaTab(nodo n, String cod, String[] tab) 
    {
        if(n.foglia())
            tab[n.simbolo()] = cod;
        else
        {
            compilaTab(n.sinistro(), cod + "0", tab);
            compilaTab(n.destro(), cod + "1", tab);
        }
    }/*

    /* VERSIONE RICORSIVA RIP
    public static String codificaAlbero(nodo n) 
    {
        if(n.foglia())
        {
            char c = n.simbolo();

            if(c == '@' || c == '\\')
                return "\\" + c;
            else
                return "" + c;
        }
        else
            return "@" + codificaAlbero(n.sinistro()) + codificaAlbero(n.destro()); 
    }*/

    //Versione iterativa
    public static String codificaAlbero(nodo radice) 
    {
        Stack<nodo> stack = new Stack<nodo>();
        stack.push(radice);

        String codifica = "";

        do 
        {
            nodo n = stack.pop();

            if(n.foglia())
            {
                char c = radice.simbolo();

                if(c == '@' || c == '\\')
                    codifica += "\\" + c;
                else
                    codifica += c;
            }
            else
            {
                codifica += '@';
                stack.push(radice.destro()); 
                stack.push(radice.sinistro());
            }
                
        } while (!stack.empty());

        return codifica;
    }

    /*VERSIONE RICORSIVA 
    public static nodo ripristinaALbero(InputTextFile in) 
    {
        char c = in.readChar();

        if(c == '@')
        {
            nodo l = ripristinaALbero(in);
            nodo r = ripristinaALbero(in);

            return new nodo(l, r);
        }
            
        else 
            if(c == '\\')
                c = in.readChar();
        
        return new nodo(c, 0);
    }*/

    //Versione iterativa
    public static nodo ripristinaALbero(InputTextFile in) 
    {
        Stack<frame> s = new Stack<frame>();
        s.push(new frame());

        nodo n = null;

        do 
        {
            frame f = s.peek();

            switch (f.stato) 
            {
                case 0: 
                {
                    char c = in.readChar();

                    if(c == '@')
                    {
                        s.push(new frame());
                        f.stato = 1;
                    } 
                    else
                        if(c == '\\')
                            c = in.readChar();

                    n = new nodo(c, 0);
                    s.pop();
                }
                break;

                case 1: 
                {
                    f.sin = n;
                    s.push(new frame());
                    f.stato = 2;
                }
                break;

                case 2: 
                {
                   f.des = n;
                   n = new nodo(f.sin, f.des);
                   s.pop();
                }
                break;
            
                default:
                    break;
            }

        } while (!s.empty());
        
        return n;
    }

    public static void comprimi(String src, String dst) 
    {
        int[] freq = freqChar(src);
        nodo radice = alberoHuffman(freq);
        String[] tab = tabHuffman(radice); 

        InputTextFile in = new InputTextFile(src);
        OutputTextFile out = new OutputTextFile(dst);

        out.writeTextLine("" + radice.peso());
        out.writeTextLine(codificaAlbero(radice));

        while(in.textAvailable())
        {
            char c = in.readChar();
            out.writeCode(tab[c]);
        }

        in.close();
        out.close();

    }

    public static void decomprimi(String src, String dst) 
    {
        
        InputTextFile in = new InputTextFile(src);
        OutputTextFile out = new OutputTextFile(dst);

        nodo radice = ripristinaALbero(in);
        int conto = Integer.parseInt(in.readTextLine());
        String dummy = in.readTextLine();

        for(int i=0; i<conto; i++)
        {
            nodo n = radice;

            do 
            {
                int bit = in.readBit();
                n = (bit==0) ? n.sinistro() : n.destro();

            } while (!n.foglia());

            char c = n.simbolo();
            out.writeChar(c);
        }

        in.close();
        out.close();
    }

    public static void main(String[] args) 
    {
        huffman.comprimi("fileDiTesto\\nodo.txt", "fileDiTesto\\nodoCompresso.txt");
    }
}