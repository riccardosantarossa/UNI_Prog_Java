public class GiuseppeFlavio 
{
    public static int ultimoCavaliere(int n) 
    {
        tavolaRotonda t = new tavolaRotonda(n);

        while (t.cavalieriRimasti() > 1) 
        {
            t = t.servi();
            t = t.passa();
        }

        return t.cavaliereConBrocca();
    }

    public static void main(String[] args) 
    {
        System.out.println(ultimoCavaliere(9));
    }
}
