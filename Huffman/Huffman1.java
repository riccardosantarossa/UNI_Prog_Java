package Huffman;
import huffman_toolkit.*;
import java.util.*;

public class Huffman1 {

    private static final int CHARS = InputTextFile.CHARS;
    
    public static int[] freqCar(String src){
        InputTextFile in=new InputTextFile(src);
        int[] conto=new int[CHARS];

        for( int i=0; i<CHARS; i++) {                   
            conto[i] = 0;
        }

        while(in.textAvailable()){
            char c=in.readChar();
            conto[c]=conto[c]+1;
        }
        in.close();

        return conto;
    }

    public static nodo alberoHuffman(int[] freq){
        PriorityQueue<nodo> queue = new PriorityQueue<nodo>();  // coda con priorita' di nodi
    
        for(int c=0; c<CHARS; c=c+1 ) {                       // per ogni carattere utilizzato
            if (freq[c] > 0) {
                nodo n = new nodo( (char) c, freq[c] );             // creazione del nodo corrispondente
                queue.add(n);                                     // e inserimento nella coda
            }
        }

        while(queue.size() > 1) {                            // finche' c'e' piu' di un nodo nella coda
            nodo l = queue.poll();                                // rimozione dei due nodi di peso minimo...
            nodo r = queue.poll();
            nodo rad = new nodo( l, r );                            // ...costruzione di un (sotto) albero
            queue.add(rad);                                       // e inserimento nella coda
        }
        return queue.poll();                                    // radice dell'albero di Huffman
    }

    //ricorsiva
    public static String[] tabHuffman( nodo rad ) {
        String[] tab = new String[ CHARS ];                   // tabella dei codici di Huffman
        fillTable( rad, "", tab );                           // compilazione della tabella
        
        return tab;
    }

    private static void fillTable( nodo n, String code, String[] tab ) {
        if ( n.foglia() ) {
            tab[n.simbolo()] = code;                        // nodo foglia: code = codice del carattere
        } else {
            fillTable( n.sinistro(),  code+"0", tab );              // figlio sinistro: bit 0 nel codice
            fillTable( n.destro(), code+"1", tab );              // figlio destro:   bit 1 nel codice
        }
    }

    /*public static String[] tabHuffman( nodo rad ) {
        Stack<Coppia> stack=new Stack<Coppia>();
        stack.push(new Coppia(rad, ""));
        String[] tab = new String[ InputTextFile.CHARS ];                   // tabella dei codici di Huffman
        do{
            Coppia coppia=stack.pop();
            nodo nodo=coppia.nodo;
            String percorso=coppia.percorso;

            if ( nodo.isLeaf() ) {     
                tab[ nodo.simbolo() ] = percorso;
            }else {
                stack.push(new Coppia(nodo.right(),percorso+"1"));                      // codifica lineare del sottoalbero sinistro
                stack.push(new Coppia(nodo.left(),percorso+"0"));
            }
        }while(!stack.empty());                           // compilazione della tabella
        
        return tab;
    }*/

    public static String codificaAlbero( nodo n ) {
        
        if ( n.foglia() ) {     
            char c = n.simbolo();
            if ( (c == '\\') || (c == '@') ) {
                return ( "\\" + c );                                // caratteri speciali: \, @
            } else {
                return ( "" + c );                                  // altri caratteri
            }
        }else {
            return ( "@"                                          // @
                    + codificaAlbero( n.sinistro() )                      // codifica lineare del sottoalbero sinistro
                    + codificaAlbero( n.destro() )                     // codifica lineare del sottoalbero destro
                    );
        }
    }

    public static String codificaAlberoStack( nodo rad ) {
        
        Stack<nodo> stack=new Stack<nodo>();
        stack.push(rad);
        String codifica="";

        do{
            nodo nodo=stack.pop();
            if ( nodo.foglia() ) {     
                char c = nodo.simbolo();
                if((c == '@') || (c == '\\')) {
                    codifica=codifica+ "\\" + c;                                // caratteri speciali: \, @
                }else {
                    codifica=codifica + c;                                  // altri caratteri
                }
            }else {
                codifica=codifica+"@";                                         // @
                stack.push(nodo.destro());                      // codifica lineare del sottoalbero sinistro
                stack.push(nodo.sinistro());
            }
        }while(!stack.empty());
        return codifica;
    }   
    public static void comprimi( String src, String dst ) {
    
        // Scansione I : frequenze dei carattteri
        int[] freq = freqCar( src );                      // istogramma delle frequenze
        nodo rad = alberoHuffman( freq );                        // albero di Huffman
        int count = rad.peso(); 
        String[] codes = tabHuffman( rad );             // tabella (array) dei codici
        
        // Scansione II : codifica di Huffman
        InputTextFile in = new InputTextFile( src );            // documento originale
        OutputTextFile out = new OutputTextFile( dst );         // documento compresso
        
        out.writeTextLine(""+count);
        out.writeTextLine(codificaAlbero(rad));

        for (int j=0; j<count; j=j+1 ) {                       // scansione: codifica dei caratteri
            char c = in.readChar();
            out.writeCode( codes[c] );
        }
        in.close();
        out.close();
    }

    public static nodo ripristinaAlbero( InputTextFile in ) {

        char c = (char) in.readChar();                          // carattere dell'intestazione
        
        if ( c == '@' ) {                                       // '@' ?
            nodo left  = ripristinaAlbero( in );                       // sottoalbero sinistro
            nodo right = ripristinaAlbero( in );                       // sottoalbero destro
            return new nodo( left, right );                       // nodo genitore
        } else {
            if ( c == '\\' ) {
                c = (char) in.readChar();                           // carattere speciale (skip)
            }
            return new nodo( c, 0 );                              // foglia
        }
    }

    public static nodo ripristina(InputTextFile in){
        Stack<frame> stack=new Stack<frame>();
        stack.push(new frame());
        nodo nodo=null;

        do {
            frame f=stack.peek();
            switch(f.stato){
                case 0:{
                    char c=in.readChar();
                    if ( c == '@' ){
                        stack.push(new frame());
                        f.stato=1;
                    }else{
                        if(c=='\\'){
                            c=in.readChar();
                        }
                        nodo=new nodo(c,0);
                        stack.pop();
                    }
                    break;
                }
                case 1:{
                    f.sin=nodo;
                    stack.push(new frame());
                    f.stato=2;
                    break;
                }
                case 2:{
                    f.des=nodo;
                    nodo=new nodo(f.sin, f.des);
                    stack.pop();
                    break;
                }
            }
        } while (!stack.empty());
        
        return nodo;
    }
    public static void decomprimi( String src, String dst ) {
        
        InputTextFile in = new InputTextFile( src );            // documento compresso
        OutputTextFile out = new OutputTextFile( dst );
        int conto=Integer.parseInt(in.readTextLine());
        nodo root = ripristina( in );
        String dummy = in.readTextLine();
         
        for( int j=0; j<conto; j=j+1 ) {                       // scansione: decodifica dei caratteri
            /*nodo n=root;
            do {
                int bit=in.readBit();
                n=(bit==0) ? n.sinistro() : n.destro();  //se bit=0 vado dal sinistro se no destro
            } while (!n.foglia());
            char c = n.simbolo();*/
            char c = decodeNextChar( root, in );
            out.writeChar( c );
        }
        in.close();
        out.close();
    }
    private static char decodeNextChar( nodo root, InputTextFile in ) {
  
    nodo n = root;                                          // percorso lungo l'albero di Huffman
    
    do {
      if ( in.readBit() == 0 ) {
        n = n.sinistro();                                       // bit 0: figlio sinistro
      } else {
        n = n.destro();                                      // bit 1: figlio destro
      }
    } while ( !n.foglia() );                                // fino al raggiungimento di una foglia
    
    return n.simbolo();                                   // carattere individuato
  }

    public static void main(String[] args) throws Exception {
        //System.out.println(freqCar("Huffman.java"));
        comprimi("fileDiTesto\\nodo.txt", "fileDiTesto\\nodoCompresso.txt");
        decomprimi("fileDiTesto\\nodoCompresso.txt", "fileDiTesto\\DeCompresso.txt");

    }
}
