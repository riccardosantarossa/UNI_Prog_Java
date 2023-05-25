package Altro;

public class esempiEsami 
{
    //-------------------------------------------------------------------------------------------------//
    //ESAME 2022

    //ES1
    public static boolean heapCheck(double[] v)
    {
        boolean flag = false;
        int i = 0, j = 2, k = 3;

        //while (!flag || (i < v.length && (j < v.length || k < v.length)))
        while (!flag || i<v.length)
        {
            if(v[i] <= v[j] || v[i] <= v[k])
                flag = true;
            else
                flag = false;

            i++;
            j = 2*i;
            k = (2*i) + 1;
        }
        
        return flag;
    }


    //-------------------------------------------------------------------------------------------------//

    public static void main(String[] args) 
    {
        System.out.print(heapCheck(new double[] { 5.0, 3.1, 5.7, 3.1, 8.5, 6.0, 3.0, 4.2, 9.3 }));
    }
}
