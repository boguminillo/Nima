package com.example.nima.ui.gallery;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.recyclerview.widget.RecyclerView;

import com.example.nima.R;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private ArrayList<Cell>galleryList;
    private Context context;

    public MyAdapter(Context context, ArrayList<Cell>galleryList){
        this.galleryList = galleryList;
        this.context=context;


    }




    @Override
    public ViewHolder onCreateViewHolder( ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cell,viewGroup,false  );
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyAdapter.ViewHolder viewHolder, int i){
        viewHolder.title.setText(galleryList.get(i).getTitle());
        viewHolder.img.setScaleType(ImageView.ScaleType.CENTER_CROP);
        viewHolder.img.setImageResource(galleryList.get(i).getImg());
        viewHolder.img.setOnClickListener(new  View.OnClickListener(){
            @Override
            public void onClick(View view){
                Toast.makeText(context,"Image",Toast.LENGTH_SHORT);
            }


        });
    }

    @Override

    public int getItemCount(){
        return galleryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView title;
        private ImageView img;
        public ViewHolder(View view){
            super(view);
            title =(TextView) view.findViewById(R.id.title);
            img=(ImageView) view.findViewById(R.id.img);

        }
    }
}
