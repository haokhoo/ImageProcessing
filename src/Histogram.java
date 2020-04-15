import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Histogram {
    static ArrayList<Integer> list = new ArrayList<>();
    static String imgName = "lena.raw";
    static int greyLvl[] = new int[256];
    static int greyLvl1[] = new int[256];
    
    public static void main(String[] args) throws FileNotFoundException{
        
        double runSum = 0;
        double run1 = 0;
        int[] greyLvl = new int[256];
        
        try{
            FileInputStream myInputFile = new FileInputStream(imgName);
            int value;
            
            while((value = myInputFile.read()) != -1){
                greyLvl[value]+=1;
        }
            
            System.out.println("Grey-Level \tNo of Pixel \tRun Sum \tNormalized \tMultiply " + greyLvl.length);
            
            double normalized = 0;
            double multiply = 0;
            double run = 0;
            
            for (int i = 0; i < greyLvl.length; i++) {
                run1+=greyLvl[i];
            }
            
            runSum = run1;
            for (int i = 0; i < greyLvl.length; i++) {
                run+=greyLvl[i];
                normalized = run/runSum;
                multiply = normalized * (greyLvl.length-1);
                greyLvl1[i]=(int) Math.round(multiply);
               System.out.println(i + "\t\t" + greyLvl[i] + "\t\t" + run + "\t\t" + Math.round(normalized) + "\t\t" + Math.round(multiply));
            }
            
            myInputFile.close();
            
        }catch(IOException ex){
            System.out.println("File error!");
        }
        
        try(FileInputStream myInputFile = new FileInputStream(imgName);){
            int value;
            while ((value = myInputFile.read()) != -1){
                list.add(greyLvl1[value]);
            }
            myInputFile.close();
        }catch (IOException ex){
            System.out.print("File Error!");
        }
        
        String fileNameOutput = "Histogram" + imgName;
        try (FileOutputStream myOutputFile = new FileOutputStream(fileNameOutput)) {
            for (Integer str : list) {
                if (str>255){
                    myOutputFile.write(255);
                }
                else if(str < 0) {
                    myOutputFile.write(0);
                }else myOutputFile.write(str);
            }
            myOutputFile.close();
        } catch (IOException ex) {
            System.out.print("File output error!");
        }
        
    }
    
}