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

public class NowPlayingAsyncTaskLoader extends AsyncTaskLoader<ArrayList<FilmItem>> {

    private ArrayList<FilmItem> mFilmItem;
    private boolean mHashResult = false;

    public NowPlayingAsyncTaskLoader(final Context context, ArrayList<FilmItem> mFilmItem){
        super(context);
        onForceLoad();
    }

    protected void onStartLoading(){
        if (takeContentChanged())
            forceLoad();
        else if (mHashResult)
            deliverResult(mFilmItem);
    }

    public void deliverResult(final ArrayList<FilmItem> data){
        mFilmItem = data;
        mHashResult = true;
        super.deliverResult(data);
    }

    protected void onReset(){
        super.onReset();
        onStartLoading();
        if (mHashResult){
            onReleaseResource(mFilmItem);
            mFilmItem = null;
            mHashResult = false;
        }
    }

    //onRelease di onReset
    private void onReleaseResource(ArrayList<FilmItem> mFilmItem){

    }

    @Override
    public ArrayList<FilmItem> loadInBackground() {
        SyncHttpClient client = new SyncHttpClient();

        final ArrayList<FilmItem> film_items = new ArrayList<>();
        String url = "https://api.themoviedb.org/3/movie/now_playing?api_key=" + API_KEY + "&language=en-US";
        client.get(url, new AsyncHttpResponseHandler(){

            @Override
            public void onStart(){
                super.onStart();
                setUseSynchronousMode(true);
            }
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    //Log.e(TAG, "onSuccess: Success");
                    String result = new String (responseBody);
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray list = responseObject.getJSONArray("results");


                    for (int i = 0; i < list.length(); i++) {
                        JSONObject film = list.getJSONObject(i);
                        FilmItem movieItems = new FilmItem(film);
                        film_items.add(movieItems);
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
        return film_items;
    }
}
