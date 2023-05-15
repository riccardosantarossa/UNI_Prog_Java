package Huffman;
import java.util.PriorityQueue;

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

    private static void comprimi(String src, String dst) 
    {
        int[] freq = freqChar(src);
        nodo radice = alberoHuffman(freq);
        String[] tab = tabHuffman(radice); 

        InputTextFIle in = InputTextFile(src);
        OutputTextFIle out = InputTextFile(src);

        while(in.textAvailable())
        {
            char c = in.readChar();
            out.writeChar(tab[c]);
        }

        in.close();
        out.close();
    }
}