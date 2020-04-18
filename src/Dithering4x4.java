import java.io.*;
import java.util.*;

public class Dithering4x4 {

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
                if(count > 0 && count <= width/2){
                    if (count > 0 && count <= ((width+1)/2)){
                        firstRow1(myInputFile.read(),1);
                    }  
                    if (count > 0 && count <= ((width+1)/2)){
                        firstRow1(myInputFile.read(),2);
                    } 
                    if (count > 0 && count <= ((width+1)/2)){
                        firstRow1(myInputFile.read(),3);
                    } 
                    if (count > 0 && count <= ((width+1)/2)){
                        firstRow1(myInputFile.read(),4);
                    }
                    //
                    if(count > 0 && count <=(width)/2){
                        firstRow2(myInputFile.read(),1);
                    }
                    if(count > 0 && count <=(width)/2){
                        firstRow2(myInputFile.read(),2);
                    }
                    if(count > 0 && count <=(width)/2){
                        firstRow2(myInputFile.read(),3);
                    }
                    if(count > 0 && count <=(width)/2){
                        firstRow2(myInputFile.read(),4);
                    }
                }else{
                    if(count > (width)/2 && count <= width){
                        secondRow1(myInputFile.read(),1);
                    }
                    if(count > (width)/2 && count <= width){
                        secondRow1(myInputFile.read(),2);
                    }
                    if(count > (width)/2 && count <= width){
                        secondRow1(myInputFile.read(),3);
                    }
                    if(count > (width)/2 && count <= width){
                        secondRow1(myInputFile.read(),4);
                    }
                    //
                    if(count > (width)/2 && count <= width){
                        secondRow2(myInputFile.read(),1);
                    }
                    if(count > (width)/2 && count <= width){
                        secondRow2(myInputFile.read(),2);
                    }
                    if(count > (width)/2 && count <= width){
                        secondRow2(myInputFile.read(),3);
                    }
                    if(count > (width)/2 && count <= width){
                        secondRow2(myInputFile.read(),4);
                    }
                    
                }
                
                if(count == width){
                        count = 0;
                    }
                
            }
            myInputFile.close();
            
            String outputFileName ="Dithering2"+imgName;
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
    
    static void firstRow1(int value ,int num){
        int compare = 0;
        
        if(num == 1){
            if(value>0){
                compare = 255;
            }else{
                compare = 0;
            }
        }else if(num == 2){
            if(value>32){
                compare = 255;
            }else{
                compare = 0;
            }
        }else if(num == 3){
            if(value>48){
                compare = 255;
            }else{
                compare = 0;
            }
        }else if(num == 4){
            if(value>16){
                compare = 255;
            }else{
                compare = 0;
            }
        }
        
        finalData.addAll(Arrays.asList(compare));
    }
    
    static void firstRow2(int value, int num){
        int compare = 0;
        
        if(num == 1){
            if(value>128){
                compare = 255;
            }else{
                compare = 0;
            }
        }else if(num == 2){
            if(value>160){
                compare = 255;
            }else{
                compare = 0;
            }
        }else if(num == 3){
            if(value>176){
                compare = 255;
            }else{
                compare = 0;
            }
        }else if(num == 4){
            if(value>144){
                compare = 255;
            }else{
                compare = 0;
            }
        }
        
        finalData.addAll(Arrays.asList(compare));
    }
    
    static void secondRow1(int value,int num){
        int compare = 0;
        
        if(num == 1){
            if(value>192){
                compare = 255;
            }else{
                compare = 0;
            }
        }else if(num == 2){
            if(value>224){
                compare = 255;
            }else{
                compare = 0;
            }
        }else if(num == 3){
            if(value>240){
                compare = 255;
            }else{
                compare = 0;
            }
        }else if(num == 4){
                if(value>208){
                compare = 255;
            }else{
                compare = 0;
            }
        }
        
        finalData.addAll(Arrays.asList(compare));
    }
    
    static void secondRow2(int value,int num){
        int compare = 0;
        
        if(num == 1){
            if(value>64){
                compare = 255;
            }else{
                compare = 0;
            }
        }else if(num == 2){
            if(value>96){
                compare = 255;
            }else{
                compare = 0;
            }
        }else if(num == 3){
            if(value>112){
                compare = 255;
            }else{
                compare = 0;
            }
        }else if(num == 4){
            if(value>80){
                compare = 255;
            }else{
                compare = 0;
            }
        }
        
        finalData.addAll(Arrays.asList(compare));
    }
}