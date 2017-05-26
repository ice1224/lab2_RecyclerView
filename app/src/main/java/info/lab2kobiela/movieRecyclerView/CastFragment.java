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
import android.widget.TextView;

import info.lab2kobiela.recyclerview.R;


public class CastFragment extends Fragment {
    private static final String INDEX_OF_MOVIE = "Index";
    private int movieIndex;
    private Movie movie;

    public static CastFragment newInstance(int movieIndex) {
        CastFragment fragment = new CastFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cast, container, false);

        ImageView actorPhoto1 = (ImageView) view.findViewById(R.id.ivActor1);
        ImageView actorPhoto2 = (ImageView) view.findViewById(R.id.ivActor2);
        ImageView actorPhoto3 = (ImageView) view.findViewById(R.id.ivActor3);

        TextView actorName1 = (TextView) view.findViewById(R.id.tvActor1);
        TextView actorName2 = (TextView) view.findViewById(R.id.tvActor2);
        TextView actorName3 = (TextView) view.findViewById(R.id.tvActor3);

        Button bCastToInfo = (Button) view.findViewById(R.id.bCastToInfo);
        bCastToInfo.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view){showMovieInfoFragment();}
        });


        if(movie!=null) {
            if(!movie.getActorListSetted()) {
                movie.setActorList();
                movie.setActorListSetted(true);
            }
            Actor[] actorList = movie.getActorList();

            actorPhoto1.setImageResource(actorList[0].getPhoto());
            actorPhoto2.setImageResource(actorList[1].getPhoto());
            actorPhoto3.setImageResource(actorList[2].getPhoto());

            actorName1.setText(actorList[0].getName());
            actorName2.setText(actorList[1].getName());
            actorName3.setText(actorList[2].getName());

        }
        return view;
    }


    private void showMovieInfoFragment(){
        FragmentManager fragmentManager = getFragmentManager();
        Fragment picturesFragment = fragmentManager.findFragmentById(R.id.gallery_container);
        Fragment castFragment = fragmentManager.findFragmentById(R.id.actors_container);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.detach(picturesFragment);
        fragmentTransaction.detach(castFragment);
        fragmentTransaction.add(R.id.mainContainer, MovieInfoFragment.newInstance(movieIndex));
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
