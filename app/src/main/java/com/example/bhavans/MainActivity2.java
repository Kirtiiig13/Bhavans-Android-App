package com.example.bhavans;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        setupButton(R.id.subject1Button, "Computer Science", "subject");
        setupButton(R.id.subject2Button, "Physics", "subject");
        setupButton(R.id.subject3Button, "Mathematics", "subject");
        setupButton(R.id.subject4Button, "Chemistry", "subject");
        setupButton(R.id.subject5Button, "English", "subject");
        setupButton(R.id.subject6Button, "Environmental Science", "subject");

        setupButton(R.id.timetableButton, "Time Table", "timetable");
        setupButton(R.id.mapButton, "Map of College", "map");
        setupButton(R.id.floorsButton, "Floors", "floors");
        setupButton(R.id.streamsButton, "All Streams", "streams");
    }

    private void setupButton(int buttonId, String title, String category) {
        Button button = findViewById(buttonId);
        button.setOnClickListener(v -> {
            if (category.equals("subject")) {
                Intent intent = new Intent(MainActivity2.this, MainActivity3.class);
                intent.putExtra("title", title);
                intent.putExtra("category", category);
                startActivity(intent);
            } else {
                Intent intent = new Intent(MainActivity2.this, MainActivity4.class);
                intent.putExtra("title", title);
                intent.putExtra("option", category);
                startActivity(intent);
            }
        });
    }
}
