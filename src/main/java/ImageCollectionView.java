//
// "ImageCollectionView" is the view for ImageCollectionModel objects
//

import java.awt.*;
import java.util.ArrayList;
import javax.swing.JPanel;


public class ImageCollectionView extends JPanel implements IView {

	//fields
	private ImageCollectionModel imageCollectionModel;
	private ArrayList<ImageView> imageViewArrayList = new ArrayList<ImageView>();
	private int mode = 0;
	private int rating = 0;


	//constructor
	public ImageCollectionView(ImageCollectionModel imageCollectionModel) {
		this.imageCollectionModel= imageCollectionModel;
        imageCollectionModel.addView(this);
		gridimgs();
	}

	public int imgs(){
		int total = 0;
		for (int i = 0; i < imageViewArrayList.size(); i++) {
			if (imageViewArrayList.get(i).getModel().getRate() >= imageCollectionModel.getRate() ||imageViewArrayList.get(i).getModel().getRate() ==0 ) {
				total++;
			}
		}
		return total;
	}

	public void gridimgs() {

		this.setSize(new Dimension(800,600));
		int total = imgs();
		int cols =1;
		if(total!=0){
			cols=3;
		}
		int rows = total/cols + 1;
		this.setLayout(new GridLayout(rows,cols));
		for (int i = 0; i < imageViewArrayList.size(); i++) {
			if (imageViewArrayList.get(i).getModel().getRate() >= imageCollectionModel.getRate()||imageViewArrayList.get(i).getModel().getRate() ==0 ) {
				this.add(imageViewArrayList.get(i));
			}
		}
		revalidate();
		repaint();
	}


	public void listimgs() {

		this.setSize(new Dimension(800,600));
		int rows=imgs();
		this.setLayout(new GridLayout(rows,1));

		for (int i = 0; i < imageViewArrayList.size(); i++) {
			if (imageViewArrayList.get(i).getModel().getRate() >= imageCollectionModel.getRate()||imageViewArrayList.get(i).getModel().getRate() ==0) {
				this.add(imageViewArrayList.get(i));
			}
		}
		revalidate();
		repaint();
	}


	// common set methods
    public void addView(){
        if(imageCollectionModel.getisAdded()==true){
            imageCollectionModel.setisAdded(false);
            ImageModel newmodel = imageCollectionModel.getNewmodel();
            ImageView newView = new ImageView(newmodel);
			imageViewArrayList.add(newView);
        }
    }


	public void updateView() {

		if(imageCollectionModel.getisAdded()==true){
            imageCollectionModel.setisAdded(false);
			ImageModel newmodel = imageCollectionModel.getNewmodel();
			ImageView newView = new ImageView(newmodel);
			imageViewArrayList.add(newView);
		}
		this.removeAll();
		if(imageCollectionModel.getGrid()==0){
			this.rating=imageCollectionModel.getRate();
			this.mode=0;
			gridimgs();
		}
		if(imageCollectionModel.getGrid()==1){
			this.rating=imageCollectionModel.getRate();
			this.mode=1;
			listimgs();
		}
	}
}
