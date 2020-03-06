import java.io.*;

public class Assignment3 {

    public static void main(String[] args) {
        try {
            FileInputStream myInpuiFile = new FileInputStream("yoda.tif");
            readHeader(myInpuiFile);
            readEntryData(myInpuiFile);
            //readImgData(myInpuiFile);

        } catch (IOException ex) {
            System.out.println("File Error!");
        }
    }

    private static void readHeader(FileInputStream input) throws IOException {
        String byteOrder = "";
        String version = "";
        String offset = "";
        String left = "";
        String right = "";
        int col = 0;
        int r;

        //find header
        while ((r = input.read()) != -1) {
            String num = String.format("%02X", (0xFF & r));
            col++;

            if (col == 2 || col == 1) {
                byteOrder += num;

            } else if (col == 3) {
                version = num;

            } else if (col == 5 || col == 6) {
                if (col == 5) {
                    left = num;
                } else {
                    right = num;
                }
                offset = right + left;
            }
        }

        System.out.println("");
        System.out.println("-----------------------------Header Info-----------------------------");
        System.out.println("Byte Order: " + byteOrder);
        System.out.println("Version: " + version);
        System.out.println("Offset: " + offset.substring(3));
    }

    private static void readEntryData(FileInputStream input) throws IOException {
        int r;
        int col = 0;
        String mForString = "";
        int m = 0;
        String left = "";
        String right = "";

        int ide = 0;
        String tag = "";
        String type = "";
        String count = "";
        String valueForString = "";
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

        while ((r = input.read()) != -1) {
            String num = String.format("%02X", (0xFF & r));
            col++;

            //find m
            if (col == 9 || col == 10) {
                if (col == 9) {
                    left = num;
                } else {
                    right = num;
                }

                mForString = right + left;
                m = Integer.parseInt(mForString, 16);
            }

            //rang of IDE, data entry
            if (col >= 11 && col <= (m * 12) + 10) {
                ide++;

                //tag
                for (int i = 1; i < (m * 12) + 10; i += 12) {
                    if (i == ide) {
                        left = num;
                    }
                }

                for (int j = 2; j < (m * 12) + 10; j += 12) {
                    if (j == ide) {
                        right = num;
                        tag = right + left;
                        if (tag.equals("00FE")) {
                            newFileType = tag;
                        } else if (tag.equals("0100")) {
                            width = tag;
                        } else if (tag.equals("0101")) {
                            height = tag;
                        } else if (tag.equals("0102")) {
                            bits = tag;
                        } else if (tag.equals("0103")) {
                            compression = tag;
                        } else if (tag.equals("0106")) {
                            photometric = tag;
                        } else if (tag.equals("0111")) {
                            stripOffset = tag;
                        } else if (tag.equals("0115")) {
                            samplePixel = tag;
                        } else if (tag.equals("0116")) {
                            rowsStrip = tag;
                        } else if (tag.equals("0117")) {
                            stripByte = tag;
                        } else if (tag.equals("011A")) {
                            x = tag;
                        } else if (tag.equals("011B")) {
                            y = tag;
                        }
                    }
                }

                //type
                for (int k = 3; k < (m * 12) + 10; k += 12) {
                    if (k == ide) {
                        type = num;

                        if (type.equals("03")) {
                            typeLength = "(SHORT)";
                        } else if (type.equals("04")) {
                            typeLength = "(LONG)";
                        } else if (type.equals("05")) {
                            typeLength = "(RATIONAL)";
                        }

                        if (tag.equals("00FE")) {
                            newFileTypeType = type + typeLength;
                        } else if (tag.equals("0100")) {
                            widthType = type + typeLength;
                        } else if (tag.equals("0101")) {
                            heightType = type + typeLength;
                        } else if (tag.equals("0102")) {
                            bitsType = type + typeLength;
                        } else if (tag.equals("0103")) {
                            compressionType = type + typeLength;
                        } else if (tag.equals("0106")) {
                            photometricType = type + typeLength;
                        } else if (tag.equals("0111")) {
                            stripOffsetType = type + typeLength;
                        } else if (tag.equals("0115")) {
                            samplePixelType = type + typeLength;
                        } else if (tag.equals("0116")) {
                            rowsStripType = type + typeLength;
                        } else if (tag.equals("0117")) {
                            stripByteType = type + typeLength;
                        } else if (tag.equals("011A")) {
                            xType = type + typeLength;
                        } else if (tag.equals("011B")) {
                            yType = type + typeLength;
                        }
                    }
                }

                //count
                for (int k = 5; k < (m * 12) + 10; k += 12) {
                    if (k == ide) {
                        count = num;

                        if (tag.equals("00FE")) {
                            newFileTypeCount = count;
                        } else if (tag.equals("0100")) {
                            widthCount = count;
                        } else if (tag.equals("0101")) {
                            heightCount = count;
                        } else if (tag.equals("0102")) {
                            bitsCount = count;
                        } else if (tag.equals("0103")) {
                            compressionCount = count;
                        } else if (tag.equals("0106")) {
                            photometricCount = count;
                        } else if (tag.equals("0111")) {
                            stripOffsetCount = count;
                        } else if (tag.equals("0115")) {
                            samplePixelCount = count;
                        } else if (tag.equals("0116")) {
                            rowsStripCount = count;
                        } else if (tag.equals("0117")) {
                            stripByteCount = count;
                        } else if (tag.equals("011A")) {
                            xCount = count;
                        } else if (tag.equals("011B")) {
                            yCount = count;
                        }
                    }
                }

                //value
                for (int i = 9; i < (m * 12) + 10; i += 12) {
                    if (i == ide) {
                        left = num;
                    }
                }

                for (int k = 10; k < (m * 12) + 10; k += 12) {
                    if (k == ide) {
                        right = num;
                        valueForString = right + left;
                        value = Integer.parseInt(valueForString, 16);
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
                }
            }
        }

        System.out.println("-----------------------------Data  Entry-----------------------------");
        System.out.println("Tag\t\t\t\t\tType\t\tCount\tValue");
        System.out.println("---------------------------------------------------------------------");

        System.out.println(newFileType.substring(2) + " (New sub file type)\t\t\t" + newFileTypeType.substring(1) + "\t\t" + newFileTypeCount.substring(1) + "\t" + newFileTypeValue);
        System.out.println(width.substring(1) + " (Image width)\t\t\t" + widthType.substring(1) + "\t" + widthCount.substring(1) + "\t" + widthValue);
        System.out.println(height.substring(1) + " (Image height)\t\t\t" + heightType.substring(1) + "\t" + heightCount.substring(1) + "\t" + heightValue);
        System.out.println(bits.substring(1) + " (Bits per sample)\t\t\t" + bitsType.substring(1) + "\t" + bitsCount.substring(1) + "\t" + bitsValue);
        System.out.println(compression.substring(1) + " (Compression)\t\t\t" + compressionType.substring(1) + "\t" + compressionCount.substring(1) + "\t" + compressionValue);
        System.out.println(photometric.substring(1) + " (Photometric interpretation)\t" + photometricType.substring(1) + "\t" + photometricCount.substring(1) + "\t" + photometricValue);
        System.out.println(stripOffset.substring(1) + " (Strip offsets)\t\t\t" + stripOffsetType.substring(1) + "\t\t" + stripOffsetCount.substring(1) + "\t" + stripOffsetValue);
        System.out.println(samplePixel.substring(1) + " (Samples per pixel)\t\t\t" + samplePixelType.substring(1) + "\t" + samplePixelCount.substring(1) + "\t" + samplePixelValue);
        System.out.println(rowsStrip.substring(1) + " (Rows per strip)\t\t\t" + rowsStripType.substring(1) + "\t" + rowsStripCount.substring(1) + "\t" + rowsStripValue);
        System.out.println(stripByte.substring(1) + " (Strip byte counts)\t\t\t" + stripByteType.substring(1) + "\t\t" + stripByteCount.substring(1) + "\t" + stripByteValue);
        System.out.println(x.substring(1) + " (X resolution)\t\t\t" + xType.substring(1) + "\t" + xCount.substring(1) + "\t" + xValue);
        System.out.println(y.substring(1) + " (Y resolution)\t\t\t" + yType.substring(1) + "\t" + yCount.substring(1) + "\t" + yValue);
    }

     private static void readImgData(FileInputStream input) throws IOException {
        int value;
        int col = 0;
        System.out.println("-----------------------------Image  Data-----------------------------");
        System.out.print(" ");
        while ((value = input.read()) != -1) {
            String num = String.format("%02X", (0xFF & value));
            System.out.print(num);
            col++;
            if (col % 16 == 0) {
                System.out.println();
            }

            if (col % 2 == 0) {
                System.out.print(" ");
            }


        }
        
        
    }
}
