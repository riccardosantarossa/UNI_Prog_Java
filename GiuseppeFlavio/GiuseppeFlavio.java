package GiuseppeFlavio;

public class GiuseppeFlavio 
{
    /*
     * CLASSE PER I TEST DELLE VARIE TAVOLE
     */
    //Metodo con tavola ottimizzata
    public static int ottimoCavaliere(int n) 
    {
        tavolaOttimizzata t = new tavolaOttimizzata(n);

        while (t.cavalieriRimasti() > 1) 
        {
            t.servi();
            t.passa();
        }

        return t.cavaliereConBrocca();
    }

    /*Metodo con classe RoundTable
    public static SList<Integer> dueCavalieri(int n) 
    {
        RoundTable t = new RoundTable(n);

        while (t.numberOfKnights() > 2) 
        {
            t = t.serveNeighbour();
            t = t.passJug();
        }

        return t.servingKnights();
    }*/

    public static void main(String[] args) 
    {
    
        System.out.println(ottimoCavaliere(100000));
        //System.out.println(dueCavalieri(4));
    }
}
