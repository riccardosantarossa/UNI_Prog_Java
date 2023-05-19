package Altro;
import puzzleboard.*;

public class gioco15 
{
    //VARIABILI DI ISTANZA
    private static int dim;
    private static int[][] sottobosco;
    public static PuzzleBoard campogGioco;
    public int posX= -1, posY=-1;
    public int pos0x=-1, pos0y=-1;
    

    //Costruttore
    public gioco15(int n)
    {
        int cont = -1;
        campogGioco = new PuzzleBoard(n);

        sottobosco = new  int[n][n];
        dim = n;
        for (int i = 0; i < n; i++) 
        {
            for (int j = 0; j < n; j++) 
            {
                cont++;
                sottobosco[i][j] = cont;
            }
        }

        stampaCampoGioco();

    }

    public static void stampa()
    {
        
        for (int i = 0; i < dim; i++) 
        {
            for (int j = 0; j < dim; j++) 
            {
                System.out.println(sottobosco[i][j]); 
            }
        }
    }

    //Verifica se i tasselli sono ordinati
    public boolean haiVinto()
    {
        boolean flag = true;
        int i, j;
        i = dim-1;
        j = dim-1;
        int check = dim^2-1;

        if(!(sottobosco[dim-1][dim-1]==0))
        {
            while (flag && i >=0) 
            {
                while(j>=0)
                {
                    if(i == dim-1 && j == dim-1)
                        flag = true;
               
                    if(sottobosco[i][j]==check)
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
        
        for (int i = 0; i < dim; i++) 
        {
            for (int j = 0; j < dim; j++) 
            {
                if(sottobosco[i][j] == n)
                {
                    posX = j; //COLONNE
                    posY = i; //RIGHE
                }
            }
        }

        if( posX > 0 && sottobosco[posY][posX-1] == 0)
        {
            pos0x = posX-1;
            pos0y = posY;
            return true;
        }
        else if( posY > 0 && sottobosco[posY-1][posX] == 0  )  
        {
            pos0x = posX;
            pos0y = posY-1;
            return true;
        }
        else if( posY < dim-1 && sottobosco[posY][posX+1] == 0)
        {
            pos0x = posX+1;
            pos0y = posY;
            return true;
        }
        else if( posX < dim-1 && sottobosco[posY+1][posX] == 0)
        {
            pos0x = posX;
            pos0y = posY+1;
            return true;
        }
        else 
            return false;
    }

    /* 
    //Genera la soluzione testuale
    public String configTestuale()
    {

    }*/

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

    public void stampaCampoGioco()
    {
        for (int i = 0; i < dim; i++) 
        {

            for (int j = 0; j < dim; j++) 
            {
                int x = sottobosco[i][j];
                campogGioco.setNumber(i+1, j+1, x);
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
         }
    }

}
