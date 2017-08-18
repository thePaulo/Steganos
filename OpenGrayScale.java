
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.Color;
import java.lang.Byte;
import javax.swing.JOptionPane;

public class OpenGrayScale{
    public static void main(String args[])throws IOException{
        //BufferedImage for source image
        BufferedImage simg = null;
        
        //File object
        File f = null;
        
        String file = new String();

        file=JOptionPane.showInputDialog(" ex:(filename.png) \nQual o nome do arquivo?");

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
        
        //BufferedImage for mirror image
        BufferedImage mimg = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        
        int iterator=0;

        String text = new String();

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

                if(y==0 && lx == 0){
                    if(a == 255){
                        JOptionPane.showMessageDialog(null,"Nao há mensagens nessa imagem");
                        System.exit(0);
                    }
                    iterator = a;
                    //JOptionPane.showMessageDialog(null,(char)a);
                    //System.out.println( (char) a);
                }
                if(iterator != 0){
                    if(y != 0 || lx != 0){
                        text = text + (char)a;
                    }
                    iterator--; 
                }

                //JOptionPane.showMessageDialog(null,);
                
                //set mirror image pixel value - both left and right

                //mimg.setRGB(lx, y, c.getRGB());

            }
        }
        JOptionPane.showMessageDialog(null,text);

    }//main() ends here
}//class ends here