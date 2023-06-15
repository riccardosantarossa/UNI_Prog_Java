package Huffman;
import huffman_toolkit.*;
import java.util.*;

public class Huffman2 {
    private static final int CHARS = InputTextFile.CHARS;
    
    private static int[] CharacterFrequencyPercentage() {
        int[] freq = new int[InputTextFile.CHARS];
        for(int i = 0; i < freq.length; i++) {
            freq[i] = 0;
        }
        int nCharacters = 100000;

        float avg_length_word = 5.1f;
        float avg_length_phrase = 24.5f;
        float Characters_for_phrase = avg_length_word * avg_length_phrase;
       
        //Inizializzo il vettore freq con le rispettive frequenze dei caratteri

        freq['a'] = 8167;
        freq['b'] = 1492;
        freq['c'] = 2782;
        freq['d'] = 4253;
        freq['e'] = 12702;
        freq['f'] = 2228;
        freq['g'] = 2015;
        freq['h'] = 6094;
        freq['i'] = 6966;
        freq['j'] = 153;
        freq['k'] = 772;
        freq['l'] = 4025;
        freq['m'] = 2406;
        freq['n'] = 6749;
        freq['o'] = 7507;
        freq['p'] = 1929;
        freq['q'] = 95;
        freq['r'] = 5987;
        freq['s'] = 6327;
        freq['t'] = 9056;
        freq['u'] = 2758;
        freq['v'] = 978;
        freq['w'] = 2361;
        freq['x'] = 150;
        freq['y'] = 1974;
        freq['z'] = 74;

        //Faccio lo stesso per le lettere Maiuscole

        freq['A'] = (int) (8167 / Characters_for_phrase);
        freq['B'] = (int) (1492 / Characters_for_phrase);
        freq['C'] = (int) (2782 / Characters_for_phrase);
        freq['D'] = (int) (4253 / Characters_for_phrase);
        freq['E'] = (int) (12702 / Characters_for_phrase);
        freq['F'] = (int) (2228 / Characters_for_phrase);
        freq['G'] = (int) (2015 / Characters_for_phrase);
        freq['H'] = (int) (6094 / Characters_for_phrase);
        freq['I'] = (int) (6966 / Characters_for_phrase);
        freq['J'] = (int) (153 / Characters_for_phrase);
        freq['K'] = (int) (772 / Characters_for_phrase);
        freq['L'] = (int) (4025 / Characters_for_phrase);
        freq['M'] = (int) (2406 / Characters_for_phrase);
        freq['N'] = (int) (6749 / Characters_for_phrase);
        freq['O'] = (int) (7507 / Characters_for_phrase);
        freq['P'] = (int) (1929 / Characters_for_phrase);
        freq['Q'] = (int) (95 / Characters_for_phrase);
        freq['R'] = (int) (5987 / Characters_for_phrase);
        freq['S'] = (int) (6327 / Characters_for_phrase);
        freq['T'] = (int) (9056 / Characters_for_phrase);
        freq['U'] = (int) (2758 / Characters_for_phrase);
        freq['V'] = (int) (978 / Characters_for_phrase);
        freq['W'] = (int) (2361 / Characters_for_phrase);
        freq['X'] = (int) (150 / Characters_for_phrase);
        freq['Y'] = (int) (1974 / Characters_for_phrase);
        freq['Z'] = (int) (74 / Characters_for_phrase);

        //Faccio lo stesso per la punteggiatura

        freq['.'] = (int) (nCharacters / Characters_for_phrase);
        freq[','] = (int) (nCharacters * 0.45f / Characters_for_phrase);
        freq['\''] = (int) (nCharacters * 0.4f / Characters_for_phrase);
        freq[':'] = (int) (nCharacters * 0.05f / Characters_for_phrase);
        freq[';'] = (int) (nCharacters * 0.05f / Characters_for_phrase);
        freq['"'] = (int) (nCharacters * 0.05f / Characters_for_phrase);

       
        // Inizializza tutti gli altri caratteri in almeno un'occorrenza
        for(int i = 0; i < freq.length; i++) {
            if (freq[i] == 0) {
                freq[i] = 1;
            }
        }

        return freq;
    }
    

    // Albero binario di codifica dei caratteri
    // freq: vettore con la frequenza dei caratteri
    
    public static nodo huffmanTree( int[] freq ) {
    
        PriorityQueue<nodo> queue = new PriorityQueue<nodo>();    // coda con priorita' di nodi
        
        for ( int c=0; c<CHARS; c=c+1 ) {                                           
            if ( freq[c] > 0 ) {
                nodo n = new nodo( (char) c, freq[c] );                         // creazione del nodo corrispondente
                queue.add( n );                                                                         // e inserimento nella coda
        }}
        while ( queue.size() > 1 ) {                                                        // finche' c'e' piu' di un nodo nella coda
        
            nodo l = queue.poll();                                                                // rimozione dei due nodi di peso minimo...
            nodo r = queue.poll();
            
            nodo n = new nodo( l, r );                                                        // ...costruzione di un (sotto) albero
            queue.add( n );                                                                             // e inserimento nella coda
        }
        return queue.poll();                                                                        // radice dell'albero di Huffman
    }
    
    
    // Tabella di codifica dei caratteri:
    // root: nodo radice dell'albero di Huffman
    
    public static String[] huffmanCodesTable( nodo root ) {
    
        String[] codes = new String[ CHARS ];                                     // tabella dei codici di Huffman
        
        fillTable( root, "", codes );                                                     // compilazione della tabella
        
        return codes;
    }
    
    // Riempimento della tabella tramite visita ricorsiva dell'albero di Huffman
    // n:         nodo visitato
    // code:    codice binario del percorso dalla radice al nodo n
    // codes: variabile di stato per registrare i codici dei caratteri
    
    private static void fillTable( nodo n, String code, String[] codes ) {
    
        if ( n.foglia() ) {
            codes[ n.simbolo() ] = code;                                                // nodo foglia: code = codice del carattere
        } else {
            fillTable( n.sinistro(),    code+"0", codes );                            // figlio sinistro: bit 0 nel codice
            fillTable( n.destro(), code+"1", codes );                            // figlio destro:     bit 1 nel codice
        }
    }
    
    
  
    // Codifica lineare dell'albero di Huffman tramite visita ricorsiva
    // n: nodo visitato
    // Struttura:
    // - una foglia e' codificata dal carattere che rappresenta
    //     (i caratteri speciali '@' e '\' sono preceduti da '\')
    // - un albero con piu' di un nodo e' codificato
    //     linearizzando i sottoalberi sinistro e destro,
    //     quindi giustapponendo il simbolo '@' e le stringhe ottenute
    
    public static String flattenTree( nodo n ) {
        
        if ( n.foglia() ) {                                                                         // foglia: codifica del carattere
            char c = n.simbolo();
            if ( (c == '\\') || (c == '@') ) {
                return ( "\\" + c );                                                                // caratteri speciali: \, @
            } else {
                return ( "" + c );                                                                    // altri caratteri
            }
        } else {
            return ( "@"                                                                                    // @
                         + flattenTree( n.sinistro() )                                            // codifica lineare del sottoalbero sinistro
                         + flattenTree( n.destro())                                         // codifica lineare del sottoalbero destro
                             );
        }
    }

  
    // Compressione
    // src: nome del documento da comprimere
    // dst: nome del documento compresso
    public static void comprimi( String src, String dst ) {
        
        
        int[] freq = CharacterFrequencyPercentage();
        
        // albero di Huffman
        nodo root = huffmanTree( freq );

        String[] codes = huffmanCodesTable( root );                         

        
        InputTextFile inputFile = new InputTextFile(src);
        int count = 0;
        while(inputFile.textAvailable()) {
            count++;
            char dummy = inputFile.readChar();
        }
        inputFile.close();
        
    
        InputTextFile in = new InputTextFile( src );
       
        OutputTextFile out = new OutputTextFile( dst );
        
       
        out.writeTextLine( "" + count );
        
        // scansione: codifica dei caratteri
        for ( int j=0; j<count; j=j+1 ) {
        
            char c = in.readChar();
            out.writeCode( codes[c] );
        }
        in.close();
        out.close();
    }
  
  
   
    // Ricostruzione dell'albero di Huffman dalla sua codifica lineare
    // in: documento compresso
    // Struttura:
    // - una foglia e' rappresentata dal carattere corrispondente
    // - un albero con piu' di un nodo e' rappresentato
    //     dalla linearizzazione dei sottoalberi sinistro e destro,
    //     precedute dal simbolo '@'
    
    public static nodo restoreTree( InputTextFile in ) {
    
        char c = (char) in.readChar();                                                    // carattere dell'intestazione
        
        if ( c == '@' ) {                                                                             // '@' ?
        
            nodo left    = restoreTree( in );                                             // sottoalbero sinistro
            
            nodo right = restoreTree( in );                                             // sottoalbero destro
            
            return new nodo( left, right );                                             // nodo genitore
        
        } else {
            if ( c == '\\' ) {
                c = (char) in.readChar();                                                     // carattere speciale (skip)
            }
            return new nodo( c, 0 );                                                            // foglia
        }
    }
    
    

    // Decompressione
    // src: nome del documento compresso
    // dst: nome del documento riportato allo stato normale
    
    public static void decomprimi( String src, String dst ) {
        
        InputTextFile in = new InputTextFile( src );                        // documento compresso
        
        // decodifica dell'intestazione
        int count = Integer.parseInt( in.readTextLine());

        int[] freq = CharacterFrequencyPercentage();
        
        // albero di Huffman
        nodo root = huffmanTree( freq );
        
        OutputTextFile out = new OutputTextFile( dst );                 // documento ripristinato
        
        for ( int j=0; j<count; j=j+1 ) {                                             // scansione: decodifica dei caratteri
            
            char c = decodeNextChar( root, in );
            out.writeChar( c );
        }
        in.close();
        out.close();
    }
    
    // Decodifica del carattere successivo
    // root: nodo radice dell'albero di Huffman
    // in:     documento compresso
    
    private static char decodeNextChar( nodo root, InputTextFile in ) {
    
        nodo n = root;                                                                                    // percorso lungo l'albero di Huffman
        
        do {
            if ( in.readBit() == 0 ) {
                n = n.sinistro();                                                                             // bit 0: figlio sinistro
            } else {
                n = n.destro();                                                                            // bit 1: figlio destro
            }
        } while ( !n.foglia() );                                                                // fino al raggiungimento di una foglia
        
        return n.simbolo();                                                                     // carattere individuato
    }
    
    
 
    public static void main( String[] args ) {
       
        comprimi("fileDiTesto2\\nodo.txt", "fileDiTesto2\\nodoCompresso.txt");
        decomprimi("fileDiTesto2\\nodoCompresso.txt", "fileDiTesto2\\DeCompresso.txt");
       
    }
}  // class Huffman2



