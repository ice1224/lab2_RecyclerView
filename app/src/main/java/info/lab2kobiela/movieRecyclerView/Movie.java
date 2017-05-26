package info.lab2kobiela.movieRecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import info.lab2kobiela.recyclerview.R;

public class Movie {
    private String title, genre, year;
    private int posterId;
    private float rate;
    private boolean watched, photoListSetted, actorListSetted;
    private int[] photoList = new int[6];
    private Actor[] actorList = new Actor[3];


    public Movie(String title) {
        this(title,"");
    }

    public Movie(String title, String genre) {
        this(title,genre,"");
    }

    public Movie(String title, String genre, String year) {
        this(title,genre,year, R.drawable.dflt);
    }

    public Movie(String title, String genre, String year, int posterId) {
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.posterId = posterId;

        rate = 0f;
        watched = false;
        photoListSetted = false;
        actorListSetted = false;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getPosterId() {
        return posterId;
    }

    public void setPosterId(int posterId) {
        this.posterId = posterId;
    }

    public float getRate(){return rate;}

    public void setRate(float rate){this.rate = rate;}

    public boolean getWatched(){return watched;}

    public void setWatched(boolean watched){this.watched = watched;}

    public void changeWatched(){
        if(watched) watched = false;
        else watched = true;
    }

    public void setPhotoListSetted(boolean setted){
        photoListSetted = setted;
    }
    public boolean getPhotoListSetted(){
        return photoListSetted;
    }

    public void setActorListSetted(boolean setted){
        actorListSetted = setted;
    }
    public boolean getActorListSetted(){return actorListSetted;}

    public void setPhotoList(){
        List<Integer> savedList = new ArrayList<>();
        for(int i=0;i<baseOfPhotos.size();i++){
            savedList.add(baseOfPhotos.get(i));
        }
        Random r = new Random();

        for(int i=0;i<6;i++){
            int cur = r.nextInt(10-i);
            photoList[i] = savedList.get(cur);
            savedList.remove(cur);
        }
    }

    public int[] getPhotoList(){return photoList;}

    public void setActorList(){
        List<Actor> savedList = new ArrayList<>();
        for(int i=0;i<baseOfActors.size();i++){
            savedList.add(baseOfActors.get(i));
        }
        Random r = new Random();

        for(int i=0;i<3;i++){
            int cur = r.nextInt(6-i);
            actorList[i] = savedList.get(cur);
            savedList.remove(cur);
        }
    }

    public Actor[] getActorList(){return actorList;}


    private static List<Integer> baseOfPhotos = new ArrayList<>(Arrays.asList(R.drawable.s1, R.drawable.s2, R.drawable.s3, R.drawable.s4, R.drawable.s5,
                                                                              R.drawable.s6, R.drawable.s7, R.drawable.s8, R.drawable.s9, R.drawable.s10));

    private static List<Actor> baseOfActors = new ArrayList<>(Arrays.asList(new Actor("Brad Pitt", R.drawable.a1), new Actor("Clint Eastwood", R.drawable.a2),
                                                                            new Actor("Leonardo DiCaprio", R.drawable.a3), new Actor("Tom Hanks", R.drawable.a4),
                                                                            new Actor("Al Pacino", R.drawable.a5), new Actor("Natalie Portman", R.drawable.a6)));

}























