package Huffman;
import java.util.PriorityQueue;
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
    
    public static String[] tabHuffman(nodo rad)
    {
        String[] tab = new String[InputTextFile.CHARS];
        compilaTab(rad, "", tab);

        return tab;
    }

    private static void compilaTab(nodo n, String cod, String[] tab) 
    {
        if(n.foglia())
            tab[n.simbolo()] = cod;
        else
        {
            compilaTab(n.sinistro(), cod + "0", tab);
            compilaTab(n.destro(), cod + "1", tab);
        }
    }

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
    }

    private static void comprimi(String src, String dst) 
    {
        int[] freq = freqChar(src);
        nodo radice = alberoHuffman(freq);
        String[] tab = tabHuffman(radice); 

        InputTextFile in = new InputTextFile(src);
        OutputTextFile out = new OutputTextFile(src);

        out.writeTextLine(dst);

        while(in.textAvailable())
        {
            char c = in.readChar();
            out.writeChar(tab[c]);
        }

        in.close();
        out.close();

    }

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
    }

    private static void decomprimi(String src, String dst) 
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
                n =(bit==0) ? n.sinistro() : n.destro();

            } while (!n.foglia());

            char c = n.simbolo();
            out.writeChar(c);
        }

        in.close();
        out.close();
    }
}