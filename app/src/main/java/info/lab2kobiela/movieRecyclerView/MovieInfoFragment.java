package info.lab2kobiela.movieRecyclerView;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import info.lab2kobiela.recyclerview.R;


public class MovieInfoFragment extends Fragment {

    private static final String INDEX_OF_MOVIE = "Index";
    private int movieIndex;
    private Movie movie;

    public static MovieInfoFragment newInstance(int movieIndex) {
        MovieInfoFragment fragment = new MovieInfoFragment();
        Bundle args = new Bundle();
        args.putInt(INDEX_OF_MOVIE, movieIndex);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            movieIndex = getArguments().getInt(INDEX_OF_MOVIE);
            movie = MainActivity.movieList.get(movieIndex);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_info, container, false);

        TextView title = (TextView) view.findViewById(R.id.movieTitleDetailsFragment);
        ImageView poster = (ImageView) view.findViewById(R.id.movieImage);
        RatingBar rate = (RatingBar) view.findViewById(R.id.ratingBarDetailsFragment);
        TextView description = (TextView) view.findViewById(R.id.movie_details_description);
        Button bInfoToCast = (Button) view.findViewById(R.id.bInfoToCast);

        rate.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                if(movie!=null)
                movie.setRate(v);
            }
        });

        poster.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view){startGalleryCastFragments();}
        });

        bInfoToCast.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view){startGalleryCastFragments();}
        });

        if(movie!=null)
            title.setText(movie.getTitle());
            poster.setImageResource(movie.getPosterId());
            rate.setRating(movie.getRate());
            description.setText(movie.getTitle() + " " + description.getText());
        return view;
    }

    private void startGalleryCastFragments(){
        FragmentManager fragmentManager = getFragmentManager();
        Fragment movieInfoFragment = fragmentManager.findFragmentById(R.id.mainContainer);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.detach(movieInfoFragment);
        fragmentTransaction.add(R.id.gallery_container, PicturesFragment.newInstance(movieIndex));
        fragmentTransaction.add(R.id.actors_container, CastFragment.newInstance(movieIndex));
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }


    @Override
    public void onDetach() {
        super.onDetach();
    }

}
