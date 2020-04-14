import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.swing.ImageIcon;

public class Star extends JLabel{

    //fields
    BufferedImage imgafter = null;
    BufferedImage img2after = null;

    //constructor
    public Star(){

        BufferedImage img = null;
        BufferedImage img2 = null;
        try {
            img = ImageIO.read(new File("src/main/java/resources/yellowstar.png"));
            imgafter = new BufferedImage(30,30,BufferedImage.TRANSLUCENT);
            Graphics2D g2 = imgafter.createGraphics();
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2.drawImage(img,0,0,30,30,null);
            g2.dispose();

            img2 = ImageIO.read(new File("src/main/java/resources/whitestar.png"));
            img2after = new BufferedImage(30,30,BufferedImage.TRANSLUCENT);
            Graphics2D g21 = img2after.createGraphics();
            g21.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g21.drawImage(img2,0,0,30,30,null);
            g21.dispose();

        } catch (IOException e) {

        }

        setBackground(Color.WHITE);
        setIcon(new ImageIcon(img2after));
    }

    public void ToYellow(){
        setIcon(new ImageIcon(imgafter));

    }

    public void ToWhite(){
        setIcon(new ImageIcon(img2after));
    }

}
