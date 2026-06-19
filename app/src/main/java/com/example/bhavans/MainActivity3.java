package com.example.bhavans;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity3 extends AppCompatActivity {

    private String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        title = getIntent().getStringExtra("title");
        if (title == null || title.trim().isEmpty()) {
            title = "Bhavans";
        }

        TextView pageTitleText = findViewById(R.id.pageTitleText);
        pageTitleText.setText(title);

        setupOption(R.id.syllabusButton, "Syllabus");
        setupOption(R.id.notesButton, "Notes");
        setupOption(R.id.questionsButton, "Important Questions");
        setupOption(R.id.uploadButton, "Upload Homework / Notes");
    }

    private void setupOption(int buttonId, String option) {
        Button button = findViewById(buttonId);
        button.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity3.this, MainActivity4.class);
            intent.putExtra("title", title);
            intent.putExtra("option", option);
            startActivity(intent);
        });
    }
}
