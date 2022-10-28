
package com.example.nima.ui.gallery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.nima.R;

import java.util.ArrayList;

public class Gallery extends AppCompatActivity {
private final String image_titles[]= {

        "Img1",
        "Img2",
        "Img3",
        "Img4",
};
    private final Integer image_ids[]= {

            R.drawable.evento1,
            R.drawable.evento2,
            R.drawable.evento3,
            R.drawable.evento4,
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        RecyclerView recyclerView= (RecyclerView) findViewById(R.id.gallery);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),3);
        recyclerView.setLayoutManager(layoutManager);

        ArrayList<Cell> cells = prepareData();
        MyAdapter adapter = new MyAdapter(getApplicationContext(),cells);
        recyclerView.setAdapter(adapter);

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