//
// "Toolbar" is a panel that has at least the following buttons:
//	File upload; View toggle(grid or list); Rating filter(custom control).
//
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Toolbar extends JPanel implements IView {

    //fields
    private ImageCollectionModel imageCollectionModel;


    //constructor
    public Toolbar(ImageCollectionModel imageCollectionModel1) {

        this.setLayout(new BorderLayout());
        this.setBackground(Color.LIGHT_GRAY);
        this.imageCollectionModel = imageCollectionModel1;
        imageCollectionModel.addView(this);

        // the title
        JLabel titleLabel = new JLabel("                Fotag!");
        titleLabel.setFont(new Font("Serif", Font.BOLD, 40));
        this.add(titleLabel, BorderLayout.CENTER);

        // View toggle(grid or list)
        BufferedImage img2 = null;
        BufferedImage img2after =null;
        BufferedImage img3 = null;
        BufferedImage img3after = null;
        try {
            img2 = ImageIO.read(new File("src/main/java/resources/list.png"));
            img2after = new BufferedImage(40,40,BufferedImage.TRANSLUCENT);
            Graphics2D g2 = img2after.createGraphics();
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2.drawImage(img2,0,0,40,40,null);
            g2.dispose();

            img3 = ImageIO.read(new File("src/main/java/resources/grid.png"));
            img3after = new BufferedImage(40,40,BufferedImage.TRANSLUCENT);
            Graphics2D g21 = img3after.createGraphics();
            g21.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g21.drawImage(img3,0,0,40,40,null);
            g21.dispose();

        } catch (IOException e) {
        }

        JPanel toggle = new JPanel();
        toggle.setBackground(Color.LIGHT_GRAY);
        JButton gridBtn = new JButton();
        gridBtn.setIcon(new ImageIcon(img2after));
        gridBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                imageCollectionModel.setGridorList(0);
            }
        });
        JButton listBtn = new JButton();
        listBtn.setIcon(new ImageIcon(img3after));
        listBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                imageCollectionModel.setGridorList(1);
            }
        });
        toggle.setLayout(new GridLayout(1, 3));
        toggle.add(gridBtn);
        toggle.add(listBtn);
        this.add(toggle, BorderLayout.WEST);


        //Rating filter
        JPanel rateFilter = new JPanel();
        rateFilter.setBackground(Color.LIGHT_GRAY);
        RateFilter fiveStars = new RateFilter(imageCollectionModel);
        rateFilter.add(fiveStars);
        this.add(rateFilter, BorderLayout.EAST);

    }

    public void updateView() {

    }
}



