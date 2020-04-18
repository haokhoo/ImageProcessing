import java.io.*;
import java.util.*;

public class ReadImgData {

    static ArrayList<String> arrangedData = new ArrayList<>();
    static String fileName;
    public static void main(String[] args) throws FileNotFoundException {
        String formatHeader = "%-15s %-15s"; // formatter
        
        Scanner input = new Scanner(System.in);
        
        
        System.out.print("Enter Image Name: ");
        fileName = input.nextLine(); 
        
        try (FileInputStream myInputFile = new FileInputStream(fileName)) {
            String byteOrder = String.format("%02x", myInputFile.read()) + String.format("%02x", myInputFile.read());
            System.out.println("-----------------------------Header Info-----------------------------");
            System.out.println(String.format(formatHeader, "Byte order", ":" + byteOrder));
            //Check LSB or MSB and arranged the data
            checkByteOrder(byteOrder);
            String offset = "";
            for (int p = 5; p < 9; p++) {
                offset += arrangedData.get(p);
            }
            String version = String.format("%02x", myInputFile.read()).toUpperCase();
            System.out.println(String.format(formatHeader, "Version", ":" + version));
            System.out.println(String.format(formatHeader, "Offset", ":" + offset.replaceAll("0", "")));

            //Data Entry
            String formatBody = "%-37s %-13s %-10s %-10s"; //formatter
            System.out.println("-----------------------------Data Entry------------------------------");
            System.out.println(String.format(formatBody, "Tag", "Type", "Count", "Value"));
            System.out.println("---------------------------------------------------------------------");
            int DEC = Integer.parseInt(arrangedData.get(9), 16); //range of data
            int getStripOffSet = 0;
            for (int i = 10; i < DEC * 12; i += 12) {
                //tag name
                String tagInt = arrangedData.get(i) + arrangedData.get(i + 1); //get tag from correspond position
                tagInt = tagInt.replaceFirst("^0+(?!$)", ""); //remove leading 0 from tag
                String displayTagInt = tagInt;
                tagInt = Integer.parseInt(tagInt, 16) + ""; //convert it into decimal from hex
                String tagName = getTag(Integer.parseInt(tagInt)); //get tag name by it's converted int

                //type name
                String typeInt = arrangedData.get(i + 2) + arrangedData.get(i + 3);
                typeInt = typeInt.replaceFirst("^0+(?!$)", "");
                typeInt = Integer.parseInt(typeInt, 16) + "";
                String typeName = getType(Integer.parseInt(typeInt));

                //length
                String lengthInt = arrangedData.get(i + 4) + arrangedData.get(i + 5); //first group/2 byte
                String lengthInt2 = arrangedData.get(i + 6) + arrangedData.get(i + 7); //second group/2 byte
                lengthInt = lengthInt.replaceFirst("^0+(?!$)", "");
                lengthInt2 = lengthInt2.replaceFirst("^0+(?!$)", "");
                int lengthValue = Integer.parseInt(lengthInt, 16) + Integer.parseInt(lengthInt2, 16);

                //value
                String valueInt = arrangedData.get(i + 8) + arrangedData.get(i + 9); //first group/2 byte
                String valueInt2 = arrangedData.get(i + 10) + arrangedData.get(i + 11); //second group/2 byte
                valueInt = valueInt.replaceFirst("^0+(?!$)", "");
                valueInt2 = valueInt2.replaceFirst("^0+(?!$)", "");
                int dataValue = Integer.parseInt(valueInt, 16) + Integer.parseInt(valueInt2, 16);
                if ("111".equals(displayTagInt)) {
                    getStripOffSet = dataValue;
                }

                String finalOutput = String.format(formatBody, displayTagInt + tagName, typeName, lengthValue, dataValue); //data set per row
                System.out.println(finalOutput);
            }
            System.out.println("-----------------------------Image  Data-----------------------------");
            for (int o = getStripOffSet; o < arrangedData.size(); o += 2) {
                    Collections.swap(arrangedData, o, o + 1);
                }
            int count = 0;
            for (int b = getStripOffSet; b < arrangedData.size(); b++) {
                System.out.print(arrangedData.get(b) + " ");
                count++;
                if (count > 17) {
                    System.out.println();
                    count = 0;
                }
            }
            myInputFile.close();   
        } catch (IOException ex) {
            System.out.print("File Error!\n" + ex);
        }
    }

    static void checkByteOrder(String byteOrder) {
        if ("4949".equals(byteOrder)) {
            try {
                FileInputStream myInputFile = new FileInputStream(fileName);
                int value;
                while ((value = myInputFile.read()) != -1) {
                    arrangedData.add(String.format("%02x", value).toUpperCase()); //add all data to arrayList
                }
                for (int o = 0; o < arrangedData.size(); o += 2) {
                    Collections.swap(arrangedData, o, o + 1);
                }
            } catch (IOException ex) {
                System.out.print("File Error!\n" + ex);
            }
        }
        else {
            try {
                FileInputStream myInputFile = new FileInputStream(fileName);
                int value;
                while ((value = myInputFile.read()) != -1) {
                    arrangedData.add(String.format("%02x", value).toUpperCase()); //add all data to arrayList
                }
            } catch (IOException ex) {
                System.out.print("File Error!\n" + ex);
            }
        }
    }

    static String getTag(int tagInt) {
        String tag = "";
        switch (tagInt) {
            case 254:
                tag = " (NewSubfileType)";
                break;
            case 255:
                tag = " (SubFileType)";
                break;
            case 256:
                tag = " (ImageWidth)";
                break;
            case 257:
                tag = " (ImageLength)";
                break;
            case 258:
                tag = " (BitsPerSample)";
                break;
            case 259:
                tag = " (Compression)";
                break;
            case 262:
                tag = " (PhotometricInterpretation)";
                break;
            case 269:
                tag = " (DocumentName)";
                break;
            case 273:
                tag = " (StripOffsets)";
                break;
            case 277:
                tag = " (SamplesPerPixel)";
                break;
            case 278:
                tag = " (RowsPerStrip)";
                break;
            case 279:
                tag = " (StripByteCounts)";
                break;
            case 282:
                tag = " (XResolution)";
                break;
            case 283:
                tag = " (YResolution)";
                break;
            case 284:
                tag = " (PlanarConfiguration)";
                break;
            case 296:
                tag = " (ResolutionUnit)";
                break;
            case 37724:
                tag = " (ImageSourceData)";
                break;
            case 34665:
                tag = " (ExifIFD)";
                break;
            case 34377:
                tag = " (Photoshop)";
                break;
            case 700:
                tag = " (XMP)";
                break;
            case 274:
                tag = " (Orientation)";
                break;
            default:
                break;
        }
        return tag;
    }

    static String getType(int typeInt) {
        String type = "";
        switch (typeInt) {
            case 1:
                type = " (BYTE)";
                break;
            case 2:
                type = " (ASCII)";
                break;
            case 3:
                type = " (SHORT)";
                break;
            case 4:
                type = " (LONG)";
                break;
            case 5:
                type = " (RATIONAL)";
                break;
            case 6:
                type = " (SBYTE)";
                break;
            case 7:
                type = " (UNDEFINE)";
                break;
            case 8:
                type = " (SSHORT)";
                break;
            case 9:
                type = " (SLONG)";
                break;
            case 10:
                type = " (SRATIONAL)";
                break;
            case 11:
                type = " (FLOAT)";
                break;
            case 12:
                type = " (DOUBLE)";
                break;
            default:
                break;
        }
        return type;
    }

}