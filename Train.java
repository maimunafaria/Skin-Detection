import java.io.File;

import java.awt.Color;

import java.awt.image.BufferedImage;
import java.io.FileWriter;
import java.util.Arrays;

import javax.imageio.ImageIO;

public class Train{

    public static void main(String args[])throws Exception
    {
        double [][][] skin= new double[256][256][256];
        double [][][] nonSkin= new double[256][256][256];
        double [][][] probability= new double[256][256][256];
        FileWriter myWriter = new FileWriter("D:\\5th sem\\DBMS-2\\filename.txt");


        for(int i=0;i<256;i++)
        {
            for(int j=0;j<256;j++)
            {
                for(int k=0;k<256;k++)
                {
                    skin[i][j][k]=0;
                    nonSkin[i][j][k]=0;
                    probability[i][j][k]=1;
                }

            }

        }
        File directoryPath = new File("D:\\5th sem\\DBMS-2\\Mask");
        File directoryPath1 = new File("D:\\5th sem\\DBMS-2\\ibtd");
        int fileNo=0;
        File filesList[] = directoryPath.listFiles();
        File filesList1[] = directoryPath1.listFiles();

        Arrays.sort(filesList);
        Arrays.sort(filesList1);

        for(File file : filesList ) {

            File mask= new File(file.getAbsolutePath());
            File nonMask= new File(filesList1[fileNo].getAbsolutePath());
            fileNo++;



            BufferedImage maskImg = ImageIO.read(mask);
            BufferedImage img = ImageIO.read(nonMask);

            for (int y = 0; y < maskImg.getHeight(); y++)
            {

                for (int x = 0; x < maskImg.getWidth(); x++)
                {


                    int maskPixel = maskImg.getRGB(x,y);

                    Color maskColor = new Color(maskPixel, true);

                    int maskRed = maskColor.getRed();

                    int maskGreen = maskColor.getGreen();

                    int maskBlue = maskColor.getBlue();

                    int pixel = img.getRGB(x,y);


                    Color color = new Color(pixel, true);


                    int red = color.getRed();

                    int green = color.getGreen();

                    int blue = color.getBlue();


                    if(maskRed>240 && maskGreen>240 && maskBlue>240)
                    {
                        nonSkin[red][green][blue]++;
                    }
                    else
                    {
                        skin[red][green][blue]++;
                    }
                }

            }

        }
        for(int i=0;i<256;i++)
        {
            for(int j=0;j<256;j++)
            {
                for(int k=0;k<256;k++)
                {
                    if(nonSkin[i][j][k]!=0)

                        probability[i][j][k]=skin[i][j][k]/nonSkin[i][j][k];
                    myWriter.write(String.valueOf(probability[i][j][k])+"\n");


                }

            }


        }

        myWriter.close();
        System.out.println("done");

    }

}
