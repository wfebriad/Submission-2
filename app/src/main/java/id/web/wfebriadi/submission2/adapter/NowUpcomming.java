package id.web.wfebriadi.submission2.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import id.web.wfebriadi.submission2.FilmDetail;
import id.web.wfebriadi.submission2.FilmItem;
import id.web.wfebriadi.submission2.R;

public class NowUpcomming extends RecyclerView.Adapter<NowUpcomming.ViewHolder>{

    private ArrayList<FilmItem> mFilmItem = new ArrayList<>();
    private LayoutInflater mInflater;
    private Context context;

    public NowUpcomming(final Context context){
        this.context = context;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    public void setData(ArrayList<FilmItem> items){
        mFilmItem = items;
        notifyDataSetChanged();
    }
    public void addItem(final FilmItem item){
        mFilmItem.add(item);
        notifyDataSetChanged();
    }


    public int getItemViewType (int position) {
        return 0;
    }
    public FilmItem getItem(int position){
        return mFilmItem.get(position);
    }
    public long getItemId(int position){
        return position;
    }
    public int getItemCount(){
        if (mFilmItem == null) return 0;
        return mFilmItem.size();
    }
    public void clearData(){
        mFilmItem.clear();
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.detail_film, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        holder.judul_film.setText(mFilmItem.get(position).getTitle());
        holder.deskripsi.setText(mFilmItem.get(position).getOverview());

        Glide.with(this.context).load("http://image.tmdb.org/t/p/w500/" + mFilmItem.get(position).getPoster()).into(holder.poster);

        String tanggal = mFilmItem.get(position).getRelease();
        SimpleDateFormat date_format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = date_format.parse(tanggal);
            SimpleDateFormat new_format_tanggal = new SimpleDateFormat("EEEE, dd/MM/yyyy");
            String tanggal_rilis = new_format_tanggal.format(date);
            holder.tanggal_rilis.setText(tanggal_rilis);
        } catch (Exception e){
            e.printStackTrace();
        }
        holder.btn_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, (context.getString(R.string.detail)) + " " + mFilmItem
                        .get(position)
                        .getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
        holder.btn_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, (context.getString(R.string.share)) + " " + mFilmItem
                        .get(position).getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
        holder.cardView.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(context, FilmDetail.class);

                intent.putExtra(FilmDetail.FILM_TITLE, mFilmItem.get(position).getTitle());
                intent.putExtra(FilmDetail.FILM_OVERVIEW, mFilmItem.get(position).getOverview());
                intent.putExtra(FilmDetail.FILM_RELEASE_DATE, mFilmItem.get(position).getRelease());
                intent.putExtra(FilmDetail.FILM_POSTER, mFilmItem.get(position).getPoster());
                intent.putExtra(FilmDetail.FILM_BACKDROP_POSTER, mFilmItem.get(position).getBackdropPoster());

                context.startActivity(intent);
            }
        });
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView poster;
        TextView judul_film;
        TextView tanggal_rilis;
        TextView deskripsi;
        Button btn_detail;
        Button btn_share;
        CardView cardView;


        public ViewHolder(View itemView){
            super(itemView);
            cardView = (CardView)itemView.findViewById(R.id.card_view);
            poster = (ImageView)itemView.findViewById(R.id.img_posterFilm);
            judul_film = (TextView)itemView.findViewById(R.id.tv_judulFilm);
            tanggal_rilis = (TextView)itemView.findViewById(R.id.tv_tanggalRilis);
            deskripsi = (TextView)itemView.findViewById(R.id.tv_deskripsiFilm);
            btn_share = (Button)itemView.findViewById(R.id.btn_share);
            btn_detail = (Button)itemView.findViewById(R.id.btn_detail);
        }
    }
}