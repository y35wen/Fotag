
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args){

        JFrame jFrame = new JFrame("Fotag!");
        jFrame .setMinimumSize(new Dimension(450, 300));
        jFrame.setSize(new Dimension(800,600));
        jFrame .setMaximumSize(new Dimension(1200,800));
        jFrame.setLayout(new BorderLayout());

        ImageCollectionModel imageCollectionModel = new ImageCollectionModel();
        ImageCollectionView imageCollectionView = new ImageCollectionView(imageCollectionModel);
        Toolbar toolbar  = new Toolbar(imageCollectionModel);

        JScrollPane scrollPane = new JScrollPane(imageCollectionView);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        jFrame.add(scrollPane,BorderLayout.CENTER);
        jFrame.add(toolbar,BorderLayout.NORTH);


        // load saved images
        try{
            File file = new File("saveimgs.txt");
            File filerate = new File("saverate.txt");
            Scanner scanner = new Scanner(file);
            Scanner scanner1 = new Scanner(filerate);

            while(scanner.hasNextLine()){
                String name = scanner.nextLine();
                ImageModel reload = new ImageModel(name);

                String ratestr = scanner1.nextLine();
                ratestr=ratestr.substring(0,1);
                int c = Integer.valueOf(ratestr);
                Star1 star1= new Star1(reload);
                Star2 star2 = new Star2(star1,reload);
                Star3 star3 = new Star3(star2,reload);
                Star4 star4 = new Star4(star3,reload);
                Star5 star5 = new Star5(star4,reload);
                if(c==1){
                    star1.changeToYellow();
                    reload.setRate(1);

                }
                if(c==2){

                    reload.setRate(2);
                    star2.changeToYellow();
                }
                if(c==3){
                    reload.setRate(3);
                    star3.changeToYellow();
                }
                if(c==4){
                    reload.setRate(4);
                    star4.changeToYellow();
                }
                if(c==5){
                    reload.setRate(5);
                    star5.changeToYellow();
                }
                imageCollectionModel.setImageCollection(reload);

            }
            scanner.close();
            scanner1.close();
        }catch (IOException e){
        }

        // save all imgs
        jFrame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                try {
                    FileWriter fw = new FileWriter("saveimgs.txt");
                    FileWriter fwrate = new FileWriter("saverate.txt");
                    for(int i =0;i<imageCollectionModel.getImageCollection().size();i++){
                        String name = imageCollectionModel.getImageCollection().get(i).getPath()+"\n";
                        int c = imageCollectionModel.getImageCollection().get(i).getRate();
                        String cstr= String.valueOf(c);
                        fw.write(name);
                        fwrate.write(cstr+"\n");
                    }
                    fw.close();
                    fwrate.close();
                } catch (IOException e) {

                }
            }
        });

        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}