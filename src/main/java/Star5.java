import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.swing.ImageIcon;

public class Star5 extends JLabel implements IView {

    //fields
    private BufferedImage imgafter;
    private BufferedImage img2after;

    private Star4 star4;
    private ImageModel imageModel;

    //constructor
    public Star5(Star4 star4,ImageModel imageModel1){

        this.imageModel=imageModel1;
        this.star4=star4;
        star4.addView(this);

        BufferedImage img = null;
        BufferedImage img2 = null;
        try {
            img = ImageIO.read(new File("src/main/java/resources/yellowstar.png"));
            imgafter = new BufferedImage(18,18,BufferedImage.TRANSLUCENT);
            Graphics2D g2 = imgafter.createGraphics();
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2.drawImage(img,0,0,18,18,null);
            g2.dispose();

            img2 = ImageIO.read(new File("src/main/java/resources/whitestar.png"));
            img2after = new BufferedImage(18,18,BufferedImage.TRANSLUCENT);
            Graphics2D g21 = img2after.createGraphics();
            g21.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g21.drawImage(img2,0,0,18,18,null);
            g21.dispose();
        } catch (IOException e) {

        }

        setIcon(new ImageIcon(img2after));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                    changeToYellow();
                    imageModel.setRate(5);
            }
        });

    }

    public void changeToYellow(){

        star4.changeToYellow();
        setIcon(new ImageIcon(imgafter));

    }

    public void updateView() {
        if(star4.isClicked==true){
            setIcon(new ImageIcon(img2after));
        }
    }

}
