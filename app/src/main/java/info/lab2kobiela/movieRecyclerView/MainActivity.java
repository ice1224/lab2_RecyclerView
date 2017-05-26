package info.lab2kobiela.movieRecyclerView;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import info.lab2kobiela.recyclerview.R;

import static info.lab2kobiela.recyclerview.R.id.recycler_view;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MoviesAdapter mAdapter;
    private ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
            deleteItem(viewHolder);
        }
    });



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setRecyclerView();

        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    private void setRecyclerView(){
        recyclerView = (RecyclerView) findViewById(recycler_view);
        mAdapter = new MoviesAdapter(movieList);

        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);


        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Movie movie = movieList.get(position);
                Toast.makeText(getApplicationContext(), movie.getTitle() + " is selected!", Toast.LENGTH_SHORT).show();
                Intent intentMovie = MovieActivity.getStartIntent(MainActivity.this, position);
                startActivity(intentMovie);
            }

            @Override
            public void onLongClick(View view, int position) {
                Movie movie = movieList.get(position);
                movie.changeWatched();
                mAdapter.notifyItemChanged(position);
                Toast.makeText(getApplicationContext(), (movie.getWatched() ? "You want to see " : "You don't want to see ") + movie.getTitle(), Toast.LENGTH_SHORT).show();

            }


        }));
    }

    private void deleteItem(RecyclerView.ViewHolder viewHolder){
        int position = viewHolder.getAdapterPosition();
        String removedTitle = movieList.get(position).getTitle();
        movieList.remove(position);
        mAdapter.notifyItemRemoved(position);
        Toast.makeText(getApplicationContext(), removedTitle + " removed!", Toast.LENGTH_SHORT).show();
    }

    public static List<Movie> movieList = new ArrayList<>(Arrays.asList(new Movie("Mad Max: Fury Road", "Action & Adventure", "2015", R.drawable.p1),
                                                                        new Movie("Inside Out", "Animation, Kids & Family", "2015", R.drawable.p2),
                                                                        new Movie("Star Wars: Episode VII - The Force Awakens", "Action", "2015", R.drawable.p3),
                                                                        new Movie("The Martian", "Science Fiction & Fantasy", "2015", R.drawable.p4),
                                                                        new Movie("Gran Torino"),
                                                                        new Movie("Mission: Impossible Rogue Nation", "Action", "2015", R.drawable.p5),
                                                                        new Movie("Up", "Animation", "2009", R.drawable.p6),
                                                                        new Movie("Star Trek", "Science Fiction", "2009", R.drawable.p7),
                                                                        new Movie("Aliens", "Science Fiction", "1986")));

}
