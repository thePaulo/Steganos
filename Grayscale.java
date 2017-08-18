

import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.Color;
import java.lang.Byte;
import javax.swing.JOptionPane;

public class Grayscale{
    public static void main(String args[])throws IOException{
        //BufferedImage for source image
        BufferedImage simg = null;
        
        //File object
        File f = null;
        
        String file = new String();

        file=JOptionPane.showInputDialog(" ex:(filename.gif/jpg/png) \nQual o nome do arquivo de entrada?");

        //read source image file
        try{
            f = new File(file);
            simg = ImageIO.read(f);
        }catch(IOException e){
            System.out.println("Error: " + e);
        }
        
        //get source image dimension
        int width = simg.getWidth();
        int height = simg.getHeight();

        String text = new String();
        
        for(int i = 0; i < args.length; i++) {
            //System.out.println(args[i]);
            text = text +" "+ args[i];
        }        

        byte[] bytes = text.getBytes("UTF-8");
        for(int i=0;i< bytes.length;i++){
            System.out.println("bytes= "+ bytes[i]);
        }

        
        //BufferedImage for mirror image
        BufferedImage mimg = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        
        int tmp2=0;

        //create mirror image pixel by pixel
        for(int y = 0; y < height; y++){
            for(int lx = 0; lx < width; lx++){
                //lx starts from the left side of the image
                //rx starts from the right side of the image
                
                //get source pixel value
                int p = simg.getRGB(lx, y);

                int a = (p>>24)&0xff;
                int r = (p>>16)&0xff;
                int g = (p>>8)&0xff;
                int b = p&0xff;
                Color c = new Color(r, g, b, 255);
                if(tmp2!= bytes.length){
                    if(tmp2 == 0 ){
                        c = new Color(r,g,b,bytes.length);
                    }
                    else{
                        c = new Color(r, g, b, bytes[tmp2]);
                    }
                        tmp2++;
                }



                
                //set mirror image pixel value - both left and right
                //mimg.setRGB(lx, y, p);
                mimg.setRGB(lx, y, c.getRGB());
                //mimg.setRGB(rx, y, p);
            }
        }

        //save mirror image
        String fileOutput = new String();
        fileOutput=JOptionPane.showInputDialog(" ex:(filename) \n (Nao includa o formato)\nQual o nome do arquivo de saida?");


        try{
            f = new File(fileOutput+".png");
            ImageIO.write(mimg, "png", f);
        }catch(IOException e){
            System.out.println("Error: " + e);
        }
    }//main() ends here
}//class ends here