package Huffman;
import huffman_toolkit.*;

public class esempiIO 
{
    public static int copiaFile(String src, String dst) 
    {
        InputTextFIle in = InputTextFile(src);
        OutputTextFIle out = InputTextFile(src);

        int cont = 0;

        while (in.textAvailable) 
        {
            String linea = in.readTextLine();
            out.writeTextLine(linea);
            cont ++;
        }

        in.close();
        out.close();

        return cont;
    }
}
