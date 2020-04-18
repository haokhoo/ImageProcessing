import java.io.*;
import java.util.*;

public class Dithering2x2 {

    static ArrayList<Integer> finalData = new ArrayList<>();
    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        String imgName;
        String widthStr;
        
        System.out.print("Enter Image Name: ");
        imgName = input.nextLine(); 
        
        System.out.print("Enter Image Width: ");
        widthStr = input.nextLine(); 
        
        int width = Integer.parseInt(widthStr);	
        int count = 0;
        
        try{
            FileInputStream myInputFile = new FileInputStream(imgName);
            int var = myInputFile.available();
            
            while(var > 0){
                count++;
                var--;
                if(count > 0 && count <=width/2){
                    if (count > 0 && count <= ((width+1)/2)){
                        firstRow1(myInputFile.read());
                    }    
                if(count > 0 && count <=(width)/2){
                        firstRow2(myInputFile.read());
                    }
                }else{
                    if(count > (width)/2 && count <= width){
                        secondRow1(myInputFile.read());
                    }
                    if(count > (width)/2 && count <= width){
                        secondRow2(myInputFile.read());
                    }
                    
                }
                
                if(count == width){
                        count = 0;
                    }
                
            }
            myInputFile.close();
            
            String outputFileName ="Dithering"+imgName;
            try (FileOutputStream myOutputFile = new FileOutputStream(outputFileName)) {
                for (Integer data : finalData) {
                    myOutputFile.write(data);
                 }
                
                System.out.println("Your Output File Name: " + outputFileName);
                myOutputFile.close();
            } catch (IOException ex) {
                System.out.print("File output error!");
            }
        }catch(IOException ex){
            System.out.println("File Error" +ex);
        }
    }
    
    static void firstRow1(int value){
        int compare;
        
        if(value>0){
            compare = 255;
        }else{
            compare = 0;
        }
        
        finalData.addAll(Arrays.asList(compare));
    }
    
    static void firstRow2(int value){
        int compare;
        
        if(value>128){
            compare = 255;
        }else{
            compare = 0;
        }
        
        finalData.addAll(Arrays.asList(compare));
    }
    
    static void secondRow1(int value){
        int compare;
        
        if(value>192){
            compare = 255;
        }else{
            compare = 0;
        }
        
        finalData.addAll(Arrays.asList(compare));
    }
    
    static void secondRow2(int value){
        int compare;
        
        if(value>64){
            compare = 255;
        }else{
            compare = 0;
        }
        
        finalData.addAll(Arrays.asList(compare));
    }
}