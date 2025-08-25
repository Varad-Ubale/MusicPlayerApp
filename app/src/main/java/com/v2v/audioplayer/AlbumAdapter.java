package com.v2v.audioplayer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class AlbumAdapter extends BaseAdapter {

    private Context context;
    private final String[] albumNames;
    private final int[] albumImages;

    public AlbumAdapter(Context context, String[] albumNames, int[] albumImages) {
        this.context = context;
        this.albumNames = albumNames;
        this.albumImages = albumImages;
    }

    @Override
    public int getCount() { return albumNames.length; }

    @Override
    public Object getItem(int position) { return albumNames[position]; }

    @Override
    public long getItemId(int position) { return position; }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.album_grid_item, parent, false);
        }
        ImageView imageView = convertView.findViewById(R.id.albumImage);
        TextView textView = convertView.findViewById(R.id.albumName);
        imageView.setImageResource(albumImages[position]);
        textView.setText(albumNames[position]);
        return convertView;
    }
}