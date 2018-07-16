package com.example.trupti.volley_with_networkimage;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Lenovo on 16-07-2018.
 */

public class MyAdpatre extends RecyclerView.Adapter<MyAdpatre.ViewHolder> {

    ArrayList<HashMap<String,String>> iteamlist;
    Context context;
    ImageLoader imageLoader;

    public MyAdpatre(ArrayList<HashMap<String, String>> iteamlist, Context applicationContext) {
    this.context=applicationContext;
    this.iteamlist=iteamlist;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.child_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.name.setText(iteamlist.get(position).get("Name"));
        holder.mobno.setText(iteamlist.get(position).get("Mobile"));
        holder.education.setText(iteamlist.get(position).get("Education"));

        imageLoader = ImageVolleyRequest.getInstance(holder.itemView.getContext()).getImageLoader();
        imageLoader.get("http://24variyasamaj.com/uploads/"+"education-committee"+"/"+iteamlist.get(position).get("Photo"), ImageLoader.getImageListener(holder.photo, R.drawable.ic_android_black_24dp, android.R.drawable.ic_dialog_alert));


        holder.photo.setImageUrl("http://24variyasamaj.com/uploads/"+"education-committee"+"education-committee"+"/"+iteamlist.get(position).get("Photo"),imageLoader);



    }

    @Override
    public int getItemCount() {
        return iteamlist.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

            TextView name,mobno,education;
            NetworkImageView photo;

        public ViewHolder(View itemView) {
            super(itemView);
        name=(TextView)itemView.findViewById(R.id.tv_name);
        mobno=(TextView)itemView.findViewById(R.id.tv_mobile);
        education=(TextView)itemView.findViewById(R.id.tv_education);
        photo=(NetworkImageView)itemView.findViewById(R.id.image_va);

        }
    }
}
