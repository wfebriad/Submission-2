package id.web.wfebriadi.submission2;

import android.util.Log;

import org.json.JSONObject;

import static android.support.constraint.Constraints.TAG;

public class FilmItem {

    private Integer film_id;
    private String film_title;
    private String film_overview;
    private String film_release_date;
    private String film_poster;
    private String film_backdrop_poster;

    public FilmItem(JSONObject object){
        try {
            int id = object.getInt("id");
            String title = object.getString("title");
            String overview = object.getString("overview");
            String release = object.getString("release_date");
            String poster = object.getString("poster_path");
            String backdrop_poster = object.getString("backdrop_path");

            this.film_id = id;
            this.film_title = title;
            this.film_overview = overview;
            this.film_release_date = release;
            this.film_poster = poster;
            this.film_backdrop_poster = backdrop_poster;
            Log.e(TAG, "FilmItems : try");

        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public int getId(){
        return film_id;
    }
    public void setId(int id){
        this.film_id = id;
    }

    public String getTitle(){
        return film_title;
    }
    public void setTitle(String title){
        this.film_title = title;
    }

    public String getOverview(){
        return film_overview;
    }
    public void setOverview(String overview){
        this.film_overview = overview;
    }

    public String getRelease(){
        return film_release_date;
    }
    public void setRelease(String release){
        this.film_release_date = film_release_date;
    }

    public String getPoster(){
        return film_poster;
    }
    public void setPoster(String poster){
        this.film_poster = poster;
    }

    public String getBackdropPoster(){
        return film_backdrop_poster;
    }
    public void setBackdrop_poster(String backdrop_poster){
        this.film_backdrop_poster = backdrop_poster;
    }
}
