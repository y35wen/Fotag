import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;


public class ImageViewGrid extends JPanel implements IView{

    //fields
    private ImageModel imageModel;


    //constructor
    public ImageViewGrid(ImageModel imageModel1){

        this.imageModel=imageModel1;
        imageModel.addView(this);


        // To show the picture
        BufferedImage img = null;
        BufferedImage imgafter = null;
        File file = new File(imageModel.getPath());
        try{
            img = ImageIO.read(file);
            imgafter = new BufferedImage(150,150,BufferedImage.TRANSLUCENT);
            Graphics2D g2 = imgafter.createGraphics();
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2.drawImage(img,0,0,150,150,null);
            g2.dispose();
        } catch (Exception e){
        }

        JLabel imaging=new JLabel();
        imaging.setIcon(new ImageIcon(imgafter));
        imaging.setName(imageModel.getPath());
        imaging.setAlignmentX(Component.CENTER_ALIGNMENT);
        imaging.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new WholeImage(imageModel.getPath());
            }
        });
        this.add(imaging);

        // infor Box that contain metadata of the imaging
        JPanel imgInfor = new JPanel();
        imgInfor.setLayout(new BoxLayout(imgInfor, BoxLayout.Y_AXIS));


        // add name to infor box
        JLabel nameLabel;
        String name = imageModel.getPath();
        int index = name.lastIndexOf('/');
        name=name.substring(index+1);
        nameLabel = new JLabel(name);
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        imgInfor.add(nameLabel);


        // add date to infor box
        JLabel dateLabel = new JLabel();
        String date;
        try{
            BasicFileAttributes attr = Files.readAttributes(file.toPath(),BasicFileAttributes.class);
            date = attr.lastModifiedTime().toString();
            String yr = date.substring(0,4);
            String mt = date.substring(5,7);
            String da = date.substring(8,10);
            String date1 = mt+"/"+da+"/"+yr;
            dateLabel = new JLabel(date1);
        } catch (Exception e){

        }
        dateLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        imgInfor.add(dateLabel);


        // add rate to infor box
        JPanel ratingBar = new JPanel();
        Star1 star1 = new Star1(imageModel);
        Star2 star2 = new Star2(star1,imageModel);
        Star3 star3 = new Star3(star2,imageModel);
        Star4 star4 = new Star4(star3,imageModel);
        Star5 star5 = new Star5(star4,imageModel);
        ratingBar.add(star1);
        ratingBar.add(star2);
        ratingBar.add(star3);
        ratingBar.add(star4);
        ratingBar.add(star5);
        ratingBar.setAlignmentX(Component.CENTER_ALIGNMENT);
        imgInfor.add(ratingBar);

        add(imgInfor);
    }

    public void updateView() {
        if(imageModel.getGridorList()==1){
            this.setPreferredSize(new Dimension(800, 180));
            this.setLayout(new FlowLayout(FlowLayout.LEFT));

        }
        if(imageModel.getGridorList()==0){
            this.setPreferredSize(new Dimension(220, 220));
            this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        }
    }

}
