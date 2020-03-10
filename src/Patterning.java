import java.io.*;
import java.util.*;


public class Patterning{
    static ArrayList<Integer> first = new ArrayList<>();
    static ArrayList<Integer> second = new ArrayList<>();
    static ArrayList<Integer> third = new ArrayList<>();
    
    
    public static void main(String[] args) {
        String imgName = "shape.raw";
        int width = 400;
        int value;
        int count = 0;
        
        try{
            FileInputStream myInputFile = new FileInputStream(imgName);
            
            ArrayList<Integer> finalData = new ArrayList<>();
            while((value = myInputFile.read()) != -1){
                replaceData(value);
                count++;
                if (count >= width) {
                    finalData.addAll(first);
                    finalData.addAll(second);
                    finalData.addAll(third);
                    first.clear();
                    second.clear();
                    third.clear();
                    count = 0;
                }
            }  
            myInputFile.close();
            
            String outputFileName ="Patterning"+imgName;
            try (FileOutputStream myOutputFile = new FileOutputStream(outputFileName)) {
                for (Integer data : finalData) {
                    myOutputFile.write(data);
                 }
                
                myOutputFile.close();
            } catch (IOException ex) {
                System.out.print("File output error!");
            }
            
        }catch(IOException ex){
            System.out.println("File Input Error!");
        }
    }   
    
    static void replaceData(int value) {
        if (value < 26) { // lvl 0
            first.addAll(Arrays.asList(0, 0, 0));
            second.addAll(Arrays.asList(0, 0, 0));
            third.addAll(Arrays.asList(0, 0, 0));
        } else if (value < 52) { // lvl 1
            first.addAll(Arrays.asList(0, 0, 0));
            second.addAll(Arrays.asList(0, 0, 0));
            third.addAll(Arrays.asList(0, 0, 255));
        } else if (value < 78) { // lvl 2
            first.addAll(Arrays.asList(255, 0, 0));
            second.addAll(Arrays.asList(0, 0, 0));
            third.addAll(Arrays.asList(0, 0, 255));
        } else if (value < 104) { // lvl 3
            first.addAll(Arrays.asList(255, 0, 255));
            second.addAll(Arrays.asList(0, 0, 0));
            third.addAll(Arrays.asList(0, 0, 255));
        } else if (value < 130) { // lvl 4
            first.addAll(Arrays.asList(255, 0, 255));
            second.addAll(Arrays.asList(0, 0, 0));
            third.addAll(Arrays.asList(255, 0, 255));
        } else if (value < 156) { // lvl 5
            first.addAll(Arrays.asList(255, 0, 255));
            second.addAll(Arrays.asList(0, 0, 0));
            third.addAll(Arrays.asList(255, 255, 255));
        } else if (value < 182) { // lvl 6
            first.addAll(Arrays.asList(255, 0, 255));
            second.addAll(Arrays.asList(255, 0, 0));
            third.addAll(Arrays.asList(255, 255, 255));
        } else if (value < 208) { // lvl 7
            first.addAll(Arrays.asList(255, 255, 255));
            second.addAll(Arrays.asList(255, 0, 0));
            third.addAll(Arrays.asList(255, 255, 255));
        } else if (value < 234) { // lvl 8
            first.addAll(Arrays.asList(255, 255, 255));
            second.addAll(Arrays.asList(255, 0, 255));
            third.addAll(Arrays.asList(255, 255, 255));
        } else if (value <= 255) { // lvl 9
            first.addAll(Arrays.asList(255, 255, 255));
            second.addAll(Arrays.asList(255, 255, 255));
            third.addAll(Arrays.asList(255, 255, 255));
        }
    }
}
