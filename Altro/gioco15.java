package Altro;
import puzzleboard.*;
import java.util.*;

public class gioco15 
{
    //VARIABILI DI ISTANZA
    private static int dim;
    private static int[][] sottobosco;
    public static PuzzleBoard campogGioco;
    public int posX= -1, posY=-1;
    public int pos0x=-1, pos0y=-1;
    
    //Lista in cui vengono messi i numeri e mescolati prima di stampare il campo di gioco
    private List<Integer> numbers = new ArrayList<>();
    

    //Costruttore
    public gioco15(int n)
    {
        
        campogGioco = new PuzzleBoard(n);
        
        //Lista riempita randomicamente
        random(n);
    
        sottobosco = new  int[n+1][n+1];
        dim = n;
  
        for (int i = 0; i < sottobosco.length; i++) {

            for (int j = 0; j < sottobosco.length; j++) {

                if(i==0 || j==0)
                {
                    //Colonna di -1 usata per ovviare alla partenza da 1 della matrice
                    sottobosco[i][j]=-1;
                }
                else
                {
                    sottobosco[i][j] = numbers.get(0);
                    numbers.remove(0);
                }
                
            }            
        }

        /*sottobosco[1][1]=1;
        sottobosco[1][2]=2;
        sottobosco[1][3]=3;
        sottobosco[1][4]=4;
        sottobosco[2][1]=5;
        sottobosco[2][2]=6;
        sottobosco[2][3]=7;
        sottobosco[2][4]=8;
        sottobosco[3][1]=9;
        sottobosco[3][2]=10;
        sottobosco[3][3]=11;
        sottobosco[3][4]=12;
        sottobosco[4][1]=13;
        sottobosco[4][2]=14;
        sottobosco[4][3]=0;
        sottobosco[4][4]=15;*/

        stampa(sottobosco);
        stampaCampoGioco();

    }

    //Collezione di numeri random
    public void random(int x) {

        int cont =-1;
        int d = x*x;

        for (int i = 0; i < d; i++) {
            cont++;
            numbers.add(cont);
        }

        Collections.shuffle(numbers);
    }

    //Stampa in console in forma matriciale
    public static void stampa(int mat[][])
    {
        for (int[] row : mat)
            System.out.println(Arrays.toString(row));
    }

    //Verifica se i tasselli sono ordinati
    public boolean haiVinto()
    {
        boolean flag = true;
        int i, j;
        i = dim;
        j = dim;
        int check = (int) Math.pow(dim,2);

        if(sottobosco[dim][dim]==0)
        {
            while (flag && i >=1) 
            {
                j=dim;
                while(j>=1)
                {
                    if(i == dim && j == dim)
                    {
                        flag = true;
                        check--;

                    }
                    else if(sottobosco[i][j]==check)
                    {
                        flag=true;
                        check--;
                    }
                    else
                    {
                        flag=false;
                    }
                    j--;
                }

                i--;
            
            }
        }
        else
            flag = false;

        return flag; 
    }


    //Verificare spostamento
    public boolean controllaSpostamento()
    {
        int n = campogGioco.get();
        
        for (int i = 1; i <= dim; i++) 
        {
            for (int j = 1; j <= dim; j++) 
            {
                if(sottobosco[i][j] == n)
                {
                    posX = j; //COLONNE
                    posY = i; //RIGHE
                }
            }
        }

        if( (posX > 1 && posX<=dim) && sottobosco[posY][posX-1] == 0)
        {
            pos0x = posX-1;
            pos0y = posY;
            return true;
        }
        else if( (posY > 1 && posY<=dim) && sottobosco[posY-1][posX] == 0  )  
        {
            pos0x = posX;
            pos0y = posY-1;
            return true;
        }
        else if( (posX>=1 && posX < dim) && sottobosco[posY][posX+1] == 0)
        {
            pos0x = posX+1;
            pos0y = posY;
            return true;
        }
        else if( (posY>=1 && posY < dim) && sottobosco[posY+1][posX] == 0)
        {
            pos0x = posX;
            pos0y = posY+1;
            return true;
        }
        else 
            return false;
    }


    //Spostare tassello
    public void spostaNumero()
    {

        if(controllaSpostamento())
        {
            sottobosco[pos0y][pos0x] = sottobosco[posY][posX];
            sottobosco[posY][posX] = 0;
            stampaCampoGioco();
        }
    }

    //Stampa in forma grafica il campo di gioco
    public void stampaCampoGioco()
    {
        for (int i = 1; i <= dim; i++) 
        {
            for (int j = 1; j <= dim; j++) 
            {
                int x = sottobosco[i][j];
                campogGioco.setNumber(i, j, x);
                campogGioco.display();
            }
        }

        if(haiVinto())
            System.out.println("Hai vinto <3");
    }
    
    
    public static void main(String[] args) 
    {
         gioco15 a = new gioco15(4);
         a.stampaCampoGioco();

         while (!a.haiVinto()) 
         {
            a.spostaNumero();

            //Stampa anche la configurazione sotto forma di matrice
            System.out.println("-------------------------------");
            stampa(sottobosco);
            System.out.println("-------------------------------");
         }
    }

}