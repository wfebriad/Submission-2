package id.web.wfebriadi.submission2.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

import id.web.wfebriadi.submission2.FilmDetail;
import id.web.wfebriadi.submission2.FilmItem;
import id.web.wfebriadi.submission2.R;
import id.web.wfebriadi.submission2.adapter.MovieAdapter;
import id.web.wfebriadi.submission2.loader.MovieAsyncTaskLoader;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment implements LoaderManager.LoaderCallbacks<ArrayList<FilmItem>> {

    ImageView poster;
    ListView listView;
    MovieAdapter adapter;
    EditText judul_film;
    Button btnSearch;

    static final String EXTRAS_FILM = "EXTRAS_FILM";

    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        adapter = new MovieAdapter(getActivity());
        adapter.notifyDataSetChanged();

        listView = (ListView)view.findViewById(R.id.listView);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FilmItem item = (FilmItem) parent.getItemAtPosition(position);
                Intent intent = new Intent(getActivity(), FilmDetail.class);

                intent.putExtra(FilmDetail.FILM_TITLE, item.getTitle());
                intent.putExtra(FilmDetail.FILM_OVERVIEW, item.getOverview());
                intent.putExtra(FilmDetail.FILM_RELEASE_DATE, item.getRelease());
                intent.putExtra(FilmDetail.FILM_POSTER, item.getPoster());
                intent.putExtra(FilmDetail.FILM_BACKDROP_POSTER, item.getBackdropPoster());

                startActivity(intent);
            }
        });
        judul_film = (EditText)view.findViewById(R.id.cari_film);
        poster = (ImageView)view.findViewById(R.id.poster_search);
        btnSearch = (Button)view.findViewById(R.id.btn_cari_film);
        btnSearch.setOnClickListener(myListener);

        String title = judul_film.getText().toString();
        Bundle bundle = new Bundle();
        bundle.putString(EXTRAS_FILM, title);
        getLoaderManager().initLoader(0, bundle, this);

        return view;
    }


    View.OnClickListener myListener = new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            String judulFilm = judul_film.getText().toString();
            if (TextUtils.isEmpty(judulFilm))return;
            Bundle bundle = new Bundle();
            bundle.putString(EXTRAS_FILM, judulFilm);
            getLoaderManager().restartLoader(0, bundle, SearchFragment.this);
        }
    };

    @NonNull
    @Override
    public Loader<ArrayList<FilmItem>> onCreateLoader(int id, @Nullable Bundle bundle) {
        String judulFilm = "";
        if (bundle != null){
            judulFilm = bundle.getString(EXTRAS_FILM);
        }
        return new MovieAsyncTaskLoader(getActivity(), judulFilm);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<ArrayList<FilmItem>> loader, ArrayList<FilmItem> data) {
        adapter.setData(data);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<ArrayList<FilmItem>> loader) {
        adapter.setData(null);
    }
}
