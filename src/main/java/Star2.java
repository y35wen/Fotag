import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class Star2 extends JLabel implements IView{

    //fields
    private BufferedImage imgafter;
    private BufferedImage img2after;
    public boolean isSelected =false;
    public boolean isClicked = false;
    private ArrayList<IView> views = new ArrayList<IView>();

    private Star1 oneStar;
    private ImageModel imageModel;

    //constructor
    public Star2(Star1 star1, ImageModel imageModel1){

        this.imageModel=imageModel1;
        this.oneStar=star1;
        star1.addView(this);

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
                if(isSelected==false){
                    changeToYellow();
                }else{
                    changeToWhite();
                }
                imageModel.setRate(2);

            }
        });

    }

    public void changeToYellow(){
        isSelected=true;
        oneStar.changeToYellow();
        setIcon(new ImageIcon(imgafter));

    }

    public void changeToWhite(){
        isSelected= false;
        isClicked=true;
        notifyObserver();
    }


    public void addView(IView view) {
        views.add(view);
        view.updateView();
    }

    public void notifyObserver() {
        for(IView view: this.views ){
            view.updateView();
        }
    }

    public void updateView() {
        if(oneStar.isClicked==true){
            this.isClicked=true;
            notifyObserver();
            setIcon(new ImageIcon(img2after));
        }
    }
}
