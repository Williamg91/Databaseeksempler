
import java.io.File;
import java.util.Scanner;

public class HurtigParsing
{
    public static void main(String[] args) throws Exception
    {

        boolean bool;
        Scanner keyboard = new Scanner(System.in);

        System.out.println("vægt:");
        double vægt = keyboard.nextDouble();
        System.out.println("højde:");
        double højde = keyboard.nextDouble();
        System.out.println("kreatinin:");
        double kreatinin = keyboard.nextDouble();
        double eGFR1 = 39.1*(Math.pow(højde/kreatinin,0.516)* (1.099)* Math.pow(højde/1.4, 0.188));
//System.out.println("GFR" + " " + "=" + " " + eGFR1);

        if (bool=true){
            System.out.println(eGFR1);
        }

        boolean boool;
        System.out.println("cystatin:");
        double cystatin = keyboard.nextDouble();
        double eGFR2 = 39.1*(Math.pow(højde/kreatinin,0.516)* Math.pow(1.8/cystatin, 0.294)* (1.099)* Math.pow(højde/1.4, 0.188));

        if (boool=true){
            System.out.println(eGFR2);

        }
        System.out.println("karbamid:");
        double karbamid = keyboard.nextDouble();

        double eGFR = 39.1*(Math.pow(højde/kreatinin,0.516)* Math.pow(1.8/cystatin, 0.294)* Math.pow(30/karbamid, 0.169)* (1.099)* Math.pow(højde/1.4, 0.188));
        System.out.println("GFR" + " " + "=" + " " + eGFR);





        String vægts =String.valueOf(vægt);
        String GFRS = String.valueOf(eGFR);
        //try
        File myObj = new File("/Users/asmaaali/Desktop/test.txt");
        // can be modified to act on all users - place on Desktop.
        try (Scanner myReader = new Scanner(myObj)) {
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] dataa = data.split("  ");        //System.out.println(data);


                if(vægt>40){
                    vægts= ">40";
                }
                if(vægt<40){
                    vægts= "<40";
                }

                if (0<eGFR & eGFR<10){
                    GFRS = "0-10";
                }
                if (10<eGFR && eGFR<30){
                    GFRS = "10-30";
                }
                if (30<eGFR && eGFR<100){
                    GFRS = "30-100";
                }

                if(vægts.equals(dataa[0]) & GFRS.equals(dataa[1]) ){
                    System.out.println("dosis" + " " + "=" + " " + dataa[2] + " " + dataa[3]);
                }
            }
        }

    }}

