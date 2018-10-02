package id.web.wfebriadi.submission2.loader;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.SyncHttpClient;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;
import id.web.wfebriadi.submission2.FilmItem;

import static id.web.wfebriadi.submission2.BuildConfig.API_KEY;

public class MovieAsyncTaskLoader extends AsyncTaskLoader<ArrayList<FilmItem>> {

    private ArrayList<FilmItem> mData;
    private boolean mHashResult = false;

    private String mJudulFilm;

    public MovieAsyncTaskLoader(final Context context, String judulFilm){
        super(context);

        onContentChanged();
        this.mJudulFilm = judulFilm;
    }
    protected void onStartLoading(){
        if (takeContentChanged())
            forceLoad();
        else if (mHashResult)
            deliverResult(mData);
    }
    public void deliverResult(final ArrayList<FilmItem> data) {
        mData = data;
        mHashResult = true;
        super.deliverResult(data);
    }
    public void onReset(){
        super.onReset();
        onStopLoading();
        if (mHashResult){
            onReleaseResource(mData);
            mData = null;
            mHashResult = false;
        }
    }
    private void onReleaseResource(ArrayList<FilmItem> mData){
        //untuk onReset di if
    }

    @Override
    public ArrayList<FilmItem> loadInBackground() {
        SyncHttpClient client = new SyncHttpClient();
        final ArrayList<FilmItem> movie_items = new ArrayList<>();
        String URL = "https://api.themoviedb.org/3/search/movie/?api_key=" + API_KEY + "&language=en-US&query=" + mJudulFilm;

        client.get(URL, new AsyncHttpResponseHandler() {
            public void onStart(){
                super.onStart();
                setUseSynchronousMode(true);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {

                    String result = new String(responseBody);
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray list = responseObject.getJSONArray("results");

                    for (int i = 0; i < list.length(); i++) {
                        JSONObject film = list.getJSONObject(i);
                        FilmItem movieItems = new FilmItem(film);
                        movie_items.add(movieItems);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
        return movie_items;
    }
}