package iot.home.fusion.chipstagsviews;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.ArrayList;

import io.views.chip.tag.Chip;
import io.views.chip.tag.ChipTagView;

public class MainActivity extends AppCompatActivity {

    ChipTagView chipTagView;
    ArrayList<String> fruitsList = new ArrayList<>();
    int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        chipTagView = findViewById(R.id.chip_layout);

        fruitsList.add("Banana");
        fruitsList.add("Apple");
        fruitsList.add("Jonathan Lee");
        fruitsList.add("Ashish");
        fruitsList.add("Woodland Shoes");
        fruitsList.add("Apple");
        fruitsList.add("Grapes");
        fruitsList.add("Database");
        fruitsList.add("Jonathan Lee");
        fruitsList.add("Ashish");
        fruitsList.add("Woodland Shoes");
        fruitsList.add("Apple");
        fruitsList.add("Grapes");
        fruitsList.add("Database");
        fruitsList.add("Neeaction");
        fruitsList.add("Mango");
        fruitsList.add("Mumbai");

    }

    public void add(View view) {
        chipTagView.addChip(new Chip(count+1, fruitsList.get(count)));
        count++;
    }
}
