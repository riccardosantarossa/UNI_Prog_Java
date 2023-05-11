package Huffman;

public class esempiIO 
{
    public static int copiaFile(String src, String dst) 
    {
        InputTextFIle in = InputTextFile(src);
        OutputTextFIle out = InputTextFile(src);

        int cont = 0;

        while (in.textAvailable) 
        {
            /*Conteggio delle righe
             *
             *  String linea = in.readTextLine();
             *  out.writeTextLine(linea);
             * 
            */

            /*Conteggio dei caratteri 
             *
             *  char c = in.readChar();
             *  out.writeChar(c);
             * 
            */

            /*Conteggio dei bit
             * 
             *  int bit = in.readBit();
             *   out.writeBit(bit);
            */

            /*Lettura di parole di sette bit alla volta
             * 
             * String sette = in.readCode(7);
             * out.writeCode(sette);
             */
            
            cont ++;
        }

        in.close();
        out.close();

        return cont;
    }
}
