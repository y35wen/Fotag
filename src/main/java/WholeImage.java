import javax.swing.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class WholeImage extends JFrame {

    //constructor
    public WholeImage(String path) {

        int index = path.lastIndexOf('/');
        String name=path.substring(index+1);
        this.setTitle(name);
        this.setMinimumSize(new Dimension(800,600));
        this.setSize(new Dimension(800,600));
        this.setMaximumSize(new Dimension(800,600));

        BufferedImage img1 = null;
        BufferedImage img1after =null;
        try {
            img1 = ImageIO.read(new File(path));
            img1after = new BufferedImage(800,600,BufferedImage.TRANSLUCENT);
            Graphics2D g2 = img1after.createGraphics();
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2.drawImage(img1,0,0,800,600,null);
            g2.dispose();
        } catch (IOException e) {
        }
        JLabel wholeimg = new JLabel(new ImageIcon(img1after));
        this.add(wholeimg);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

    }
}
