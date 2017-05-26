package info.lab2kobiela.movieRecyclerView;

/**
 * Created by Mariusz on 2017-05-25.
 */

public class Actor {

    private String name;
    private int photo;

    public Actor(String n, int p){
        name = n;
        photo = p;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }

}
