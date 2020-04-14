//
// "ImageView" is responsible for rendering a single "ImageModel"
//

import javax.swing.*;


class ImageView extends JPanel implements IView {

    //fields
    private ImageModel imageModel;
    private ImageViewGrid imageViewGrid;


    // constructor
   public ImageView(ImageModel imageModel1) {

       this.imageModel = imageModel1;
       imageModel.addView(this);
       imageViewGrid = new ImageViewGrid(imageModel);
       this.add(imageViewGrid);

   }

    // common get methods
    public ImageModel getModel() {
        return imageModel;
    }

    public int getRate(){
       return imageModel.getRate();
    }

    /////////////////////////////
   public void updateView() {

   }
}
