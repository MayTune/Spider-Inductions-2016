package com.example.aishwarya.spidertask3;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by Aishwarya on 7/5/2016.
 */
public class MovieAdapter extends ArrayAdapter<Movies> {
    ArrayList<Movies> listItem;
    LayoutInflater inflater;
    int Resource;
    ViewHolder holder;
    public MovieAdapter(Context context, int resource, ArrayList<Movies> objects) {
        super(context, resource, objects);
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        Resource = resource;
        listItem = objects;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
// convert view = design
        View v = convertView;
        if (v == null) {
            holder = new ViewHolder();
            v = inflater.inflate(Resource, null);
            holder.imageview = (ImageView) v.findViewById(R.id.ivImage);
            holder.tvName = (TextView) v.findViewById(R.id.tvName);
            holder.tvGenre = (TextView) v.findViewById(R.id.tvGenre);
            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }
        holder.imageview.setImageResource(R.mipmap.ic_launcher);
        new DownloadImage(holder.imageview).execute(listItem.get(position).getPoster());
        holder.tvName.setText(listItem.get(position).getTitle());
        holder.tvGenre.setText(listItem.get(position).getGenre());
        return v;
    }
    static class ViewHolder {
        public ImageView imageview;
        public TextView tvName;
        public TextView tvGenre;
    }
    private class DownloadImage extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImage(ImageView bmImage) {
            this.bmImage = bmImage;
        }
        @Override
        protected Bitmap doInBackground(String... urls) {
// TODO Auto-generated method stub
            String urldisplay = urls[0];
            Bitmap Icon = null;
            try{
                InputStream in = new java.net.URL(urldisplay).openStream();
                Icon = BitmapFactory.decodeStream(in);
            }catch(Exception e){
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return Icon;
        }
/* (non-Javadoc)
* @see android.os.AsyncTask#onPostExecute(java.lang.Object)*/

        @Override
        protected void onPostExecute(Bitmap result) {
// TODO Auto-generated method stub
            bmImage.setImageBitmap(result);
        }
    }

}
