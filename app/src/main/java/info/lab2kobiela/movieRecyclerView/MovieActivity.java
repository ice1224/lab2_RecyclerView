package info.lab2kobiela.movieRecyclerView;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import info.lab2kobiela.recyclerview.R;

public class MovieActivity extends AppCompatActivity {

    private static final String INDEX_OF_MOVIE = "Index";
    private int movieIndex = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        if (savedInstanceState == null)
            showMovieFragment();

    }


    private void showMovieFragment(){
        getArgs();
        FragmentManager fragmentManager = getFragmentManager();
        if(fragmentManager.findFragmentById(R.id.gallery_container) == null){
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            MovieInfoFragment movieInfoFragment = MovieInfoFragment.newInstance(movieIndex);
            fragmentTransaction.add(R.id.mainContainer, movieInfoFragment);
            fragmentTransaction.commit();
        }
    }

    private void getArgs(){
        movieIndex = getIntent().getIntExtra(INDEX_OF_MOVIE,0);
    }


    static public Intent getStartIntent(Context context, int index){
        Intent intent = new Intent(context, MovieActivity.class);
        return intent.putExtra(INDEX_OF_MOVIE, index);
    }




}

