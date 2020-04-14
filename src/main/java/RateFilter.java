import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class RateFilter extends JPanel {

    //fields
    private ImageCollectionModel imageCollectionModel;

    private JLabel loadbtn;
    private Star star1 = new Star();
    private Star star2 = new Star();
    private Star star3 = new Star();
    private Star star4 = new Star();
    private Star star5 = new Star();
    private JLabel resetLabel;


    //constructors
    public RateFilter(ImageCollectionModel imageCollectionModel1){

        this.imageCollectionModel=imageCollectionModel1;
        this.setBackground(Color.LIGHT_GRAY);

        // load
        BufferedImage img1 = null;
        BufferedImage img1after =null;
        try {
            img1 = ImageIO.read(new File("src/main/java/resources/open.png"));
            img1after = new BufferedImage(40,40,BufferedImage.TRANSLUCENT);
            Graphics2D g2 = img1after.createGraphics();
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2.drawImage(img1,0,0,40,40,null);
            g2.dispose();

        } catch (IOException e) {
        }
        loadbtn = new JLabel(new ImageIcon(img1after));

        loadbtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JFileChooser jFileChooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("jpeg","jpeg");
                FileNameExtensionFilter filter1 = new FileNameExtensionFilter("jpg","jpg");
                FileNameExtensionFilter filter2 = new FileNameExtensionFilter("png","png");
                jFileChooser .setFileFilter(filter);
                jFileChooser .setFileFilter(filter1);
                jFileChooser .setFileFilter(filter2);
                jFileChooser .setMultiSelectionEnabled(true);
                File[] files = null;
                BufferedImage img=null;
                if(jFileChooser.showOpenDialog(null)==jFileChooser.APPROVE_OPTION){
                    files = jFileChooser.getSelectedFiles();
                }
                for(File file: files){
                    try{
                        img = ImageIO.read(file);
                    } catch (IOException ex){

                    }
                    ImageModel newModel = new ImageModel(file.getAbsolutePath());
                    imageCollectionModel.setImageCollection(newModel);
                }
            }
        });

        this.add(loadbtn);
        JLabel whitespace = new JLabel("   Filter by:");
        this.add(whitespace);

        // stars
        star1.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                star1.ToYellow();
                star2.ToWhite();
                star3.ToWhite();
                star4.ToWhite();
                star5.ToWhite();

                imageCollectionModel.setRate(1);
            }
        });

        star2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                star1.ToYellow();
                star2.ToYellow();
                star3.ToWhite();
                star4.ToWhite();
                star5.ToWhite();
                imageCollectionModel.setRate(2);
            }
        });

        star3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                star1.ToYellow();
                star2.ToYellow();
                star3.ToYellow();
                star4.ToWhite();
                star5.ToWhite();
                imageCollectionModel.setRate(3);
            }
        });
        star4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                star1.ToYellow();
                star2.ToYellow();
                star3.ToYellow();
                star4.ToYellow();
                star5.ToWhite();
                imageCollectionModel.setRate(4);
            }
        });
        star5.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                star1.ToYellow();
                star2.ToYellow();
                star3.ToYellow();
                star4.ToYellow();
                star5.ToYellow();
                imageCollectionModel.setRate(5);
            }
        });
        this.add(star1);
        this.add(star2);
        this.add(star3);
        this.add(star4);
        this.add(star5);
    }

}