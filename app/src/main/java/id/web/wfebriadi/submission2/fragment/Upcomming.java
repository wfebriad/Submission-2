package id.web.wfebriadi.submission2.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import id.web.wfebriadi.submission2.FilmItem;
import id.web.wfebriadi.submission2.R;
import id.web.wfebriadi.submission2.adapter.NowUpcomming;
import id.web.wfebriadi.submission2.loader.UpcommingAsyncTaskLoader;

/**
 * A simple {@link Fragment} subclass.
 */
public class Upcomming extends Fragment implements LoaderManager.LoaderCallbacks<ArrayList<FilmItem>> {

    NowUpcomming adapter;
    Context context;
    RecyclerView mRecyclerView;
    private ArrayList<FilmItem> upComingData;

    public Upcomming() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_upcomming, container, false );
        context = view.getContext();

        mRecyclerView = (RecyclerView)view.findViewById(R.id.rv_upcoming);

        adapter = new NowUpcomming(getActivity());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        mRecyclerView.setAdapter(adapter);

        getLoaderManager().initLoader(0, null, this);
        return view;
    }


    @NonNull
    @Override
    public Loader<ArrayList<FilmItem>> onCreateLoader(int id, @Nullable Bundle args) {
        return new UpcommingAsyncTaskLoader(getContext(), upComingData);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<ArrayList<FilmItem>> loader, ArrayList<FilmItem> data) {
        adapter.setData(data);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<ArrayList<FilmItem>> loader) {

    }
}