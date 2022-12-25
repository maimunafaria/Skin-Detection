import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

public class Test {
	
    public static void test() throws Exception {
        double[][][] probability = new double[256][256][256];
        BufferedReader br=new BufferedReader(new FileReader("D:\\5th sem\\DBMS-2\\filename.txt"));
        for (int i = 0; i < 256; i++) {

            for (int j = 0; j < 256; j++) {

                for (int k = 0; k < 256; k++) {
                    probability[i][j][k] = Double.parseDouble(br.readLine());
                }
            }
        }


        File testFile = new File("D:\\5th sem\\DBMS-2\\input.jpg");

        BufferedImage testImg = ImageIO.read(testFile);

        for (int y = 0; y < testImg.getHeight(); y++) {

            for (int x = 0; x < testImg.getWidth(); x++) {

//Retrieving contents of a pixel

                int testPixel = testImg.getRGB(x, y);

//Creating a Color object from pixel value

                Color testColor = new Color(testPixel, true);
                Color myWhite = new Color(255, 255, 255); // Color white
                int rgb = myWhite.getRGB();

//Retrieving the R G B values

                int testRed = testColor.getRed();

                int testGreen = testColor.getGreen();

                int testBlue = testColor.getBlue();

                if (probability[testRed][testGreen][testBlue] < 0.4) {
                    testImg.setRGB(x, y, rgb);
                }
            }
        }

        File outputfile = new File("D:\\5th sem\\DBMS-2\\output.jpg");
        ImageIO.write(testImg, "jpg", outputfile);
        System.out.println("done");
    }

}