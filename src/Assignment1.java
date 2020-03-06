
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class Assignment1 {
    public static void main(String[] args) {
        try{
            FileInputStream myInputFile = new FileInputStream("lena.tif");
            
            int r;
            int col = 0;
            String mForString = "";
            int m = 0;
            String imgData="";
            String byteOrder = "";
            String version = "";
            String offset = "";
            String left = "";
            String right = "";
            String left2 = "";
            String right2 = "";
            
            
            int ifd = 0;
            String tag = "";
            String type = "";
            String count = "";
            String valueForString ="";
            int value = 0;
            
            String typeLength = "";
            String newFileType = "";
            String width = "";
            String height = "";
            String bits = "";
            String compression = "";
            String photometric = "";
            String stripOffset = "";
            String samplePixel = "";
            String rowsStrip = "";
            String stripByte = "";
            String x = "";
            String y = "";
            
            String newFileTypeType = "";
            String widthType = "";
            String heightType = "";
            String bitsType = "";
            String compressionType = "";
            String photometricType = "";
            String stripOffsetType = "";
            String samplePixelType = "";
            String rowsStripType = "";
            String stripByteType = "";
            String xType = "";
            String yType = "";
            
            String newFileTypeCount = "";
            String widthCount = "";
            String heightCount = "";
            String bitsCount = "";
            String compressionCount = "";
            String photometricCount = "";
            String stripOffsetCount = "";
            String samplePixelCount = "";
            String rowsStripCount = "";
            String stripByteCount = "";
            String xCount = "";
            String yCount = "";
            
            int newFileTypeValue = 0;
            int widthValue = 0;
            int heightValue = 0;
            int bitsValue = 0;
            int compressionValue = 0;
            int photometricValue = 0;
            int stripOffsetValue = 0;
            int samplePixelValue = 0;
            int rowsStripValue = 0;
            int stripByteValue = 0;
            int xValue = 0;
            int yValue = 0;
          
            int findData = 0;
            int findDataRange = 0;
            
            while((r = myInputFile.read()) != -1){
                ArrayList<String> data = new ArrayList<String>();
                String num = String.format("%02X", (0xFF & r));
                col++;
                
                
                //find header
                if(col == 1 || col ==2 ){
                    byteOrder +=num;
                    
                }
                if(byteOrder.equals("4949")){
                    
                    if(col == 3){
                        version = num;

                    }else if (col == 5 || col == 6){
                        if(col == 5){
                           left = num;
                        }else{
                            right = num;
                        }
                        offset = right + left;
                    }

                    //find m
                    if(col == 9 || col == 10){
                        if(col == 9){
                           left = num;
                        }else{
                            right = num;
                        }

                        mForString = right + left;
                        m = Integer.parseInt(mForString,16);

                    }

                    //rang of IFD, data entry
                        if(col >= 11 && col <= (m*12)+10){
                            ifd++;

                            //tag
                            for (int i = 1; i < (m*12)+10; i+=12) {
                               if(i==ifd){
                                    left = num;
                                }
                            }

                            for (int j = 2; j < (m*12)+10; j+=12) {
                                if(j==ifd){
                                    right= num;
                                    tag = right + left;
                                    if(tag.equals("00FE")){
                                        newFileType = tag;
                                    }else if(tag.equals("0100")){
                                        width = tag;
                                    }else if(tag.equals("0101")){
                                        height = tag;
                                    }else if(tag.equals("0102")){
                                        bits = tag;
                                    }else if(tag.equals("0103")){
                                        compression = tag;
                                    }else if(tag.equals("0106")){
                                        photometric  = tag;
                                    }else if(tag.equals("0111")){
                                        stripOffset  = tag;
                                    }else if(tag.equals("0115")){
                                        samplePixel  = tag;
                                    }else if(tag.equals("0116")){
                                        rowsStrip  = tag;
                                    }else if(tag.equals("0117")){
                                        stripByte  = tag;
                                    }else if(tag.equals("011A")){
                                        x  = tag;
                                    }else if(tag.equals("011B")){
                                        y  = tag;
                                    }
                                }
                            }

                            //type
                            for (int k = 3; k < (m*12)+10; k+=12) {
                                if(k == ifd){
                                    type = num;

                                    if(type.equals("03")){
                                        typeLength = "(SHORT)";
                                    }else if(type.equals("04")){
                                        typeLength = "(LONG)";
                                    }else if(type.equals("05")){
                                        typeLength = "(RATIONAL)";
                                    }

                                    if(tag.equals("00FE")){
                                        newFileTypeType = type+typeLength;
                                    }else if(tag.equals("0100")){
                                        widthType = type+typeLength;
                                    }else if(tag.equals("0101")){
                                        heightType = type+typeLength;
                                    }else if(tag.equals("0102")){
                                        bitsType = type+typeLength;
                                    }else if(tag.equals("0103")){
                                        compressionType = type+typeLength;
                                    }else if(tag.equals("0106")){
                                        photometricType  = type+typeLength;
                                    }else if(tag.equals("0111")){
                                        stripOffsetType  = type+typeLength;
                                    }else if(tag.equals("0115")){
                                        samplePixelType  = type+typeLength;
                                    }else if(tag.equals("0116")){
                                        rowsStripType  = type+typeLength;
                                    }else if(tag.equals("0117")){
                                        stripByteType  = type+typeLength;
                                    }else if(tag.equals("011A")){
                                        xType  = type+typeLength;
                                    }else if(tag.equals("011B")){
                                        yType  = type+typeLength;
                                    }
                                }
                            }

                            //count
                            for (int k = 5; k < (m*12)+10; k+=12) {
                                if(k == ifd){
                                    count = num;

                                    if(tag.equals("00FE")){
                                        newFileTypeCount = count;
                                    }else if(tag.equals("0100")){
                                        widthCount = count;
                                    }else if(tag.equals("0101")){
                                        heightCount = count;
                                    }else if(tag.equals("0102")){
                                        bitsCount = count;
                                    }else if(tag.equals("0103")){
                                        compressionCount = count;
                                    }else if(tag.equals("0106")){
                                        photometricCount  = count;
                                    }else if(tag.equals("0111")){
                                        stripOffsetCount  = count;
                                    }else if(tag.equals("0115")){
                                        samplePixelCount  = count;
                                    }else if(tag.equals("0116")){
                                        rowsStripCount  = count;
                                    }else if(tag.equals("0117")){
                                        stripByteCount  = count;
                                    }else if(tag.equals("011A")){
                                        xCount  = count;
                                    }else if(tag.equals("011B")){
                                        yCount  = count;
                                    }
                                }
                            }

                            //value
                            for (int i = 9; i < (m*12)+10; i+=12) {
                               if(i==ifd){
                                    left = num;
                                }
                            }

                            for (int k = 10; k < (m*12)+10; k+=12) {
                                if(k == ifd){
                                    right = num; 
                                    valueForString = right + left;
                                    value = Integer.parseInt(valueForString,16);
                                    if(tag.equals("00FE")){
                                        newFileTypeValue = value;
                                    }else if(tag.equals("0100")){
                                        widthValue = value;
                                    }else if(tag.equals("0101")){
                                        heightValue = value;
                                    }else if(tag.equals("0102")){
                                        bitsValue = value;
                                    }else if(tag.equals("0103")){
                                        compressionValue = value;
                                    }else if(tag.equals("0106")){
                                        photometricValue  = value;
                                    }else if(tag.equals("0111")){
                                        stripOffsetValue  = value;
                                    }else if(tag.equals("0115")){
                                        samplePixelValue  = value;
                                    }else if(tag.equals("0116")){
                                        rowsStripValue  = value;
                                    }else if(tag.equals("0117")){
                                        stripByteValue  = value;
                                    }else if(tag.equals("011A")){
                                        xValue  = value;
                                    }else if(tag.equals("011B")){
                                        yValue  = value;
                                    }
                                }
                                findData = stripOffsetValue/m;
                                findDataRange = findData*16;
                            }

                        }
                        //display tif data
                        if(col >= findDataRange+1 && findDataRange != 0){
                            imgData+=num;
                                if (col % 16 == 0){
                                    imgData+="\n";
                                }

                                if (col % 2 == 0){
                                    imgData+=" ";
                                }
                        }
                        //MSB
                }else if(byteOrder.equals("4D4D")){
                    if(col == 4){
                        version = num;

                    }else if (col == 8){
                        offset = num;
                    }
                    
                    //find m
                    if(col == 10){
                        mForString = num;
                        m = Integer.parseInt(mForString,16);
                    }

                    //rang of IFD, data entry
                        if(col >= 11 && col <= (m*12)+10){
                            ifd++;

                            //tag
                            for (int i = 1; i < (m*12)+10; i+=12) {
                               if(i==ifd){
                                    left = num;
                                }
                            }

                            for (int j = 2; j < (m*12)+10; j+=12) {
                                if(j==ifd){
                                    right= num;
                                    tag = left + right;
                                    if(tag.equals("00FE")){
                                        newFileType = tag;
                                    }else if(tag.equals("0100")){
                                        width = tag;
                                    }else if(tag.equals("0101")){
                                        height = tag;
                                    }else if(tag.equals("0102")){
                                        bits = tag;
                                    }else if(tag.equals("0103")){
                                        compression = tag;
                                    }else if(tag.equals("0106")){
                                        photometric  = tag;
                                    }else if(tag.equals("0111")){
                                        stripOffset  = tag;
                                    }else if(tag.equals("0115")){
                                        samplePixel  = tag;
                                    }else if(tag.equals("0116")){
                                        rowsStrip  = tag;
                                    }else if(tag.equals("0117")){
                                        stripByte  = tag;
                                    }else if(tag.equals("011A")){
                                        x  = tag;
                                    }else if(tag.equals("011B")){
                                        y  = tag;
                                    }
                                }
                            }

                            //type
                            for (int k = 4; k < (m*12)+10; k+=12) {
                                if(k == ifd){
                                    type = num;

                                    if(type.equals("03")){
                                        typeLength = "(SHORT)";
                                    }else if(type.equals("04")){
                                        typeLength = "(LONG)";
                                    }else if(type.equals("05")){
                                        typeLength = "(RATIONAL)";
                                    }

                                    if(tag.equals("00FE")){
                                        newFileTypeType = type+typeLength;
                                    }else if(tag.equals("0100")){
                                        widthType = type+typeLength;
                                    }else if(tag.equals("0101")){
                                        heightType = type+typeLength;
                                    }else if(tag.equals("0102")){
                                        bitsType = type+typeLength;
                                    }else if(tag.equals("0103")){
                                        compressionType = type+typeLength;
                                    }else if(tag.equals("0106")){
                                        photometricType  = type+typeLength;
                                    }else if(tag.equals("0111")){
                                        stripOffsetType  = type+typeLength;
                                    }else if(tag.equals("0115")){
                                        samplePixelType  = type+typeLength;
                                    }else if(tag.equals("0116")){
                                        rowsStripType  = type+typeLength;
                                    }else if(tag.equals("0117")){
                                        stripByteType  = type+typeLength;
                                    }else if(tag.equals("011A")){
                                        xType  = type+typeLength;
                                    }else if(tag.equals("011B")){
                                        yType  = type+typeLength;
                                    }
                                }
                            }

                            //count
                            for (int k = 8; k < (m*12)+10; k+=12) {
                                if(k == ifd){
                                    count = num;

                                    if(tag.equals("00FE")){
                                        newFileTypeCount = count;
                                    }else if(tag.equals("0100")){
                                        widthCount = count;
                                    }else if(tag.equals("0101")){
                                        heightCount = count;
                                    }else if(tag.equals("0102")){
                                        bitsCount = count;
                                    }else if(tag.equals("0103")){
                                        compressionCount = count;
                                    }else if(tag.equals("0106")){
                                        photometricCount  = count;
                                    }else if(tag.equals("0111")){
                                        stripOffsetCount  = count;
                                    }else if(tag.equals("0115")){
                                        samplePixelCount  = count;
                                    }else if(tag.equals("0116")){
                                        rowsStripCount  = count;
                                    }else if(tag.equals("0117")){
                                        stripByteCount  = count;
                                    }else if(tag.equals("011A")){
                                        xCount  = count;
                                    }else if(tag.equals("011B")){
                                        yCount  = count;
                                    }
                                }
                            }

                            //value
                            
                            for (int i = 9; i < (m*12)+10; i+=12) {
                               if(i==ifd){
                                    left = num;
                                }
                            }
                            
                            for (int i = 10; i < (m*12)+10; i+=12) {
                               if(i==ifd){
                                    left2 = num;
                                }
                                
                            }
                            
                            for (int i = 11; i < (m*12)+10; i+=12) {
                               if(i==ifd){
                                    right = num;
                                }
                            }
                            
                            for (int i = 12; i < (m*12)+10; i+=12) {
                               if(i==ifd){
                                    right2 = num;
                                    valueForString = left+left2+right+right2;
                                    value = Integer.parseInt(valueForString,16);
                                    if (tag.equals("00FE")) {
                                        newFileTypeValue = value;
                                    } else if (tag.equals("0100")) {
                                        widthValue = value;
                                    } else if (tag.equals("0101")) {
                                        heightValue = value;
                                    } else if (tag.equals("0102")) {
                                        bitsValue = value;
                                    } else if (tag.equals("0103")) {
                                        compressionValue = value;
                                    } else if (tag.equals("0106")) {
                                        photometricValue = value;
                                    } else if (tag.equals("0111")) {
                                        stripOffsetValue = value;
                                    } else if (tag.equals("0115")) {
                                        samplePixelValue = value;
                                    } else if (tag.equals("0116")) {
                                        rowsStripValue = value;
                                    } else if (tag.equals("0117")) {
                                        stripByteValue = value;
                                    } else if (tag.equals("011A")) {
                                        xValue = value;
                                    } else if (tag.equals("011B")) {
                                        yValue = value;
                                    }
                                }
                                findData = stripOffsetValue/m;
                                findDataRange = findData*16;
                                
                            }

                        }
                        //display tif data
//                        if(col >= findDataRange+1 && findDataRange != 0){
//                            imgData+=num;
//                                if (col % 16 == 0){
//                                    imgData+="\n";
//                                }
//
//                                if (col % 2 == 0){
//                                    imgData+=" ";
//                                }
//                        }
                }
                        
            }
            myInputFile.close();
            System.out.println("");
            System.out.println("-----------------------------Header Info-----------------------------");
            System.out.println("Byte Order: " + byteOrder);
            System.out.println("Version: " + version);
            System.out.println("Offset: " + offset.replaceAll("0", ""));
            System.out.println("-----------------------------Data  Entry-----------------------------");
            System.out.println("Tag\t\t\t\t\tType\t\tCount\tValue");
            System.out.println("---------------------------------------------------------------------");
            
            System.out.println(newFileType.replaceAll("0", "") + " (New sub file type)\t\t\t" + newFileTypeType.replaceAll("0", "") + "\t\t" + newFileTypeCount.replaceAll("0", "") + "\t" + newFileTypeValue);
            System.out.println(width + " (Image width)\t\t\t" + widthType.replaceAll("0", "") + "\t" + widthCount.replaceAll("0", "") + "\t" + widthValue);
            System.out.println(height + " (Image height)\t\t\t" + heightType.replaceAll("0", "") + "\t" + heightCount.replaceAll("0", "") + "\t" + heightValue);
            System.out.println(bits + " (Bits per sample)\t\t\t" + bitsType.replaceAll("0", "") + "\t" + bitsCount.replaceAll("0", "") + "\t" + bitsValue);
            System.out.println(compression + " (Compression)\t\t\t" + compressionType.replaceAll("0", "") + "\t" + compressionCount.replaceAll("0", "") + "\t" + compressionValue);
            System.out.println(photometric + " (Photometric interpretation)\t" + photometricType.replaceAll("0", "") + "\t" + photometricCount.replaceAll("0", "") + "\t" + photometricValue);
            System.out.println(stripOffset + " (Strip offsets)\t\t\t" + stripOffsetType.replaceAll("0", "") + "\t\t" + stripOffsetCount.replaceAll("0", "") + "\t" + stripOffsetValue);
            System.out.println(samplePixel + " (Samples per pixel)\t\t" + samplePixelType.replaceAll("0", "") + "\t" + samplePixelCount.replaceAll("0", "") + "\t" + samplePixelValue);
            System.out.println(rowsStrip + " (Rows per strip)\t\t\t" + rowsStripType.replaceAll("0", "") + "   \t" + rowsStripCount.replaceAll("0", "") + "\t" + rowsStripValue);
            System.out.println(stripByte + " (Strip byte counts)\t\t" + stripByteType.replaceAll("0", "") + "\t\t" + stripByteCount.replaceAll("0", "") + "\t" + stripByteValue);
            System.out.println(x + " (X resolution)\t\t\t" + xType.replaceAll("0", "") + "\t" + xCount.replaceAll("0", "") + "\t" + xValue);
            System.out.println(y + " (Y resolution)\t\t\t" + yType.replaceAll("0", "") + "\t" + yCount.replaceAll("0", "") + "\t" + yValue);
                
              
            System.out.println("-----------------------------Image  Data-----------------------------");
            System.out.println(" "+imgData);
        }catch(IOException ex){
            System.out.println("File Error!");
        }
    }
}
