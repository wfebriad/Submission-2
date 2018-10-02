package id.web.wfebriadi.submission2;

import android.content.Context;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FilmDetail extends AppCompatActivity {
    public static String FILM_TITLE = "film_title";
    public static String FILM_OVERVIEW = "film_overview";
    public static String FILM_RELEASE_DATE = "film_release_date";
    public static String FILM_POSTER = "film_poster";
    public static String FILM_BACKDROP_POSTER = "film_backdrop_poster";

    private TextView tvTitle, tvOverview, tvReleaseDate;
    private ImageView imgPoster, imgBackdropPoster;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_detail);

        imgBackdropPoster = (ImageView)findViewById(R.id.poster_backdrop);
        tvTitle = (TextView)findViewById(R.id.tv_judulFilm);
        imgPoster = (ImageView)findViewById(R.id.img_posterFilm);
        tvReleaseDate = (TextView)findViewById(R.id.tv_tanggalRilis);
        tvOverview = (TextView)findViewById(R.id.tv_deskripsiFilm);

        String title = getIntent().getStringExtra(FILM_TITLE);
        String overview = getIntent().getStringExtra(FILM_OVERVIEW);
        String release_date = getIntent().getStringExtra(FILM_RELEASE_DATE);
        String poster = getIntent().getStringExtra(FILM_POSTER);
        String backdrop_poster = getIntent().getStringExtra(FILM_BACKDROP_POSTER);

        SimpleDateFormat date_format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = date_format.parse(release_date);
            SimpleDateFormat new_format_tanggal = new SimpleDateFormat("EEEE, dd/MM/yyyy");
            String tanggal_rilis = new_format_tanggal.format(date);
            tvReleaseDate.setText(tanggal_rilis);
        } catch (Exception e){
            e.printStackTrace();
        }

        tvTitle.setText(title);
        tvOverview.setText(overview);

        Picasso.with(this).load("http://image.tmdb.org/t/p/w500/" + poster).into(imgPoster);
        Picasso.with(this).load("http://image.tmdb.org/t/p/w780/" + backdrop_poster).into(imgBackdropPoster);
        //Glide.with(context).load("http://image.tmdb.org/t/p/w500/" + poster).into(Poster);
        //Glide.with(context).load("http://image.tmdb.org/t/p/w500/" + backdrop_poster).into(BackdropPoster);


        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout)findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle(title);


        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppBar);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar);
    }
}