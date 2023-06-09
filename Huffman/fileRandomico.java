package Huffman;
import huffman_toolkit.*;

public class fileRandomico 
{
    public static void creaRandomFile(String nomeFile)
    {
        //InputTextFile in = new InputTextFile(nomeFile);
        OutputTextFile out = new OutputTextFile(nomeFile);

        int dimFile = 4096;

        for (int i = 0; i < dimFile; i++) 
        {
            char c = (char) Math.abs(128*Math.random());
            out.writeChar(c);
        }

        out.close();

    }


    public static void main(String[] args) 
    {
        creaRandomFile("fileDiTesto\\out.txt");
        Huffman1.comprimi("fileDiTesto\\out.txt", "fileDiTesto\\outCompresso.txt");   
    }
}
