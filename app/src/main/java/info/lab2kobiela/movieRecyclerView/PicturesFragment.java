package info.lab2kobiela.movieRecyclerView;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import info.lab2kobiela.recyclerview.R;


public class PicturesFragment extends Fragment {
    private static final String INDEX_OF_MOVIE = "Index";
    private int movieIndex;
    private Movie movie;

    public static PicturesFragment newInstance(int movieIndex) {
        PicturesFragment fragment = new PicturesFragment();
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
        View view = inflater.inflate(R.layout.fragment_pictures, container, false);
        ImageView photo1 = (ImageView) view.findViewById(R.id.ivPhoto1);
        ImageView photo2 = (ImageView) view.findViewById(R.id.ivPhoto2);
        ImageView photo3 = (ImageView) view.findViewById(R.id.ivPhoto3);
        ImageView photo4 = (ImageView) view.findViewById(R.id.ivPhoto4);
        ImageView photo5 = (ImageView) view.findViewById(R.id.ivPhoto5);
        ImageView photo6 = (ImageView) view.findViewById(R.id.ivPhoto6);


        if(movie!=null) {
            if(!movie.getPhotoListSetted()) {
                movie.setPhotoList();
                movie.setPhotoListSetted(true);
            }
            int[] photoList = movie.getPhotoList();

            photo1.setImageResource(photoList[0]);
            photo2.setImageResource(photoList[1]);
            photo3.setImageResource(photoList[2]);
            photo4.setImageResource(photoList[3]);
            photo5.setImageResource(photoList[4]);
            photo6.setImageResource(photoList[5]);
        }

        return view;
    }


    @Override
    public void onDetach() {
        super.onDetach();
    }
}
