//SANTAROSSA RICCARDO 16/03/2023

public class ES1
{
     public static void main(String[] args) 
     {
        //DEBUG DELLA PROCEDURA BTR SUCC

            //String out = btrSucc("--++.-");
            //System.out.println(out);
        

        //DEBUG DELLA PROCEDURA C1
        
        //    String out = compl1("00111100");
        //    System.out.println(out);
        
        
     }

     public String btrSucc( String btr) 
     {
        int n = btr.length();
        char lsb = btr.charAt(n-1);

        if( n==1 )
            if(lsb == '+')
                return "+-";
            else
                return "+";
        else
        {
            String pre  = btr.substring(0, n-1);
            
            if( lsb == '+')
                return btrSucc(pre) + "-";
            else
            {
                String append = "";

                if ( lsb == '-') 
                    append = ".";
                else 
                    append = "+";

                return pre + append;
            }
                
        }
     }


     public static String compl1( String num) 
     {
        String c1 = "";
        if (num.equals(""))
            return c1;
        else 
        {
            for (int i = 0; i < num.length(); i++) 
            {
                if (num.charAt(i) == '0')
                    c1 += "1";
                else
                    c1+= "0";
            }
        }
        return c1;
     }
}
