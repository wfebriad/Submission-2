package id.web.wfebriadi.submission2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import id.web.wfebriadi.submission2.FilmItem;
import id.web.wfebriadi.submission2.R;

public class MovieAdapter extends BaseAdapter {
    private ArrayList<FilmItem> mData = new ArrayList<>();
    private LayoutInflater mInflater;
    private Context context;

    public MovieAdapter(Context context){
        this.context = context;
        mInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    public void setData(ArrayList<FilmItem> item){
        mData = item;
        notifyDataSetChanged();
    }
    public void addItem(final FilmItem item){
        mData.add(item);
        notifyDataSetChanged();
    }
    public void clearData(){
        mData.clear();
    }
    public int getItemViewType(int positition) {
        return 0;
    }
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public int getCount() {
        if (mData == null) return 0;
        return mData.size();
    }
    @Override
    public FilmItem getItem(int position){
        return mData.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null){
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.list_search_movie, null);
            holder.poster_film = (ImageView)convertView.findViewById(R.id.poster_search);
            holder.release_date_film = (TextView)convertView.findViewById(R.id.release_date_search);
            holder.title_film = (TextView)convertView.findViewById(R.id.title_search);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }
        holder.title_film.setText(mData.get(position).getTitle());
        Picasso.with(this.context).load("http://image.tmdb.org/t/p/w500/" + mData.get(position).getPoster()).into(holder.poster_film);

        //Glide.with(this).load(URL_IMG + mData.get(position).getPoster()).into(holder.imagePoster);


        String retrieveDate = mData.get(position).getRelease();
        SimpleDateFormat date_format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = date_format.parse(retrieveDate);
            SimpleDateFormat new_date_format = new SimpleDateFormat("EEEE, dd MMMM yyyy");
            String release_date = new_date_format.format(date);
            holder.release_date_film.setText(release_date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return convertView;
    }

    public static class ViewHolder {
        ImageView poster_film;
        TextView title_film;
        TextView release_date_film;
    }
}
