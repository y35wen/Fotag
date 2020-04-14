//
//"ImageCollectionModel" represents a collection of imageModel objects.
//

import java.util.ArrayList;


public class ImageCollectionModel {

    //fields
    private int rate;
    private int GridorList;
    private boolean isAdded;
    private ImageModel newModel;
    private ArrayList<ImageModel> imageCollection;

    private ArrayList<IView> views;

    //constructor
    public ImageCollectionModel(){
        this.rate=0;
        this.GridorList = 0;
        this.isAdded=false;
        this.imageCollection=new ArrayList<ImageModel>();
        this.views = new ArrayList<IView>();
    }

    //common get methods

    public int getRate() {
        return rate;
    }

    public int getGrid() {
        return this.GridorList;
    }

    public boolean getisAdded() {
        return isAdded;
    }

    public ImageModel getNewmodel(){
        return this.newModel;
    }

    public ArrayList<ImageModel> getImageCollection(){return this.imageCollection;}

    //common set methods
    public void setisAdded(boolean x){
        isAdded=x;
        this.notifyObservers();
    }

    public void setRate(int n) {
        rate = n;
        this.notifyObservers();
    }

    public void setGridorList(int n) {
        GridorList = n;
        for(int i=0;i<imageCollection.size();i++){
            imageCollection.get(i).setGridorList(n);
        }
        this.notifyObservers();

    }

    public void setImageCollection(ImageModel newModel) {
        this.newModel=newModel;

        for(int i=0; i<imageCollection.size();i++){
            if(newModel.getPath().equals(imageCollection.get(i).getPath())){
                isAdded=false;
                return;
            }
        }

        isAdded=true;
        newModel.setGridorList(this.GridorList);
        imageCollection.add(newModel);
        this.notifyObservers();

    }


    public void clearall(){
        imageCollection.clear();
        for(ImageModel model:this.imageCollection){
            model.clearimg();
        }
        notifyObservers();
    }


//////////////////////////////

    public void addView(IView view) {
        views.add(view);
        view.updateView();
    }

    public void notifyObservers() {
        for(IView view: this.views ){
            view.updateView();
        }
    }

}

