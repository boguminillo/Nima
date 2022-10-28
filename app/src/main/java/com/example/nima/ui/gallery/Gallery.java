
package com.example.nima.ui.gallery;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nima.R;
import com.example.nima.databinding.FragmentGalleryBinding;

import java.util.ArrayList;

public class Gallery extends Fragment {
private final String[] image_titles = {

        "Img1",
        "Img2",
        "Img3",
        "Img4",
};
    private final Integer[] image_ids = {

            R.drawable.evento1,
            R.drawable.evento2,
            R.drawable.evento3,
            R.drawable.evento4,
    };
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        FragmentGalleryBinding binding = FragmentGalleryBinding.inflate(inflater, container, false);

        RecyclerView recyclerView= binding.gallery;
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(),1, RecyclerView.HORIZONTAL,true);
        recyclerView.setLayoutManager(layoutManager);

        ArrayList<Cell> cells = prepareData();
        MyAdapter adapter = new MyAdapter(getContext(),cells);
        recyclerView.setAdapter(adapter);
        return binding.getRoot();
    }


    private ArrayList<Cell> prepareData(){
        ArrayList<Cell> theimage = new ArrayList<>();
        for(int i=0;i < image_titles.length;i++ ){
           Cell cell =new Cell();
           cell.setTitle(image_titles[i]);
           cell.setImg(image_ids[i]);
           theimage.add(cell);

        }
        return theimage;
    }
}