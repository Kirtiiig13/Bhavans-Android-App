package com.example.bhavans;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity4 extends AppCompatActivity {

    private static final int PICK_FILE_REQUEST = 101;
    private TextView fileNameText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        String title = getIntent().getStringExtra("title");
        String option = getIntent().getStringExtra("option");

        if (title == null) title = "Bhavans";
        if (option == null) option = "Content";

        TextView detailTitleText = findViewById(R.id.detailTitleText);
        TextView detailBodyText = findViewById(R.id.detailBodyText);
        ImageView detailImageView = findViewById(R.id.detailImageView);
        Button selectFileButton = findViewById(R.id.selectFileButton);
        fileNameText = findViewById(R.id.fileNameText);

        selectFileButton.setVisibility(View.GONE);
        fileNameText.setVisibility(View.GONE);
        detailImageView.setVisibility(View.GONE);

        String lowerOption = option.toLowerCase();

        if (lowerOption.equals("timetable")) {
            detailTitleText.setText("Time Table");
            detailImageView.setImageResource(R.drawable.timetable_placeholder);
            detailImageView.setVisibility(View.VISIBLE);
            detailBodyText.setText("Weekly timetable for classes and labs. Replace this demo timetable image with the actual college timetable image in app/src/main/res/drawable/.");
        } else if (lowerOption.equals("map")) {
            detailTitleText.setText("Map of College");
            detailImageView.setImageResource(R.drawable.college_map_placeholder);
            detailImageView.setVisibility(View.VISIBLE);
            detailBodyText.setText("College campus map showing main gate, reception, library, computer lab, auditorium, garden and parking. Replace this placeholder with the actual college map image.");
        } else if (lowerOption.equals("floors")) {
            detailTitleText.setText("Floors & Room Details");
            detailBodyText.setText(
                    "Ground Floor\n" +
                            "• Reception and Office\n" +
                            "• Library\n" +
                            "• Principal Office\n\n" +
                            "First Floor\n" +
                            "• BCA 1st Year - Room 101\n" +
                            "• BCA 2nd Year - Room 102\n" +
                            "• Computer Lab - Room 105\n\n" +
                            "Second Floor\n" +
                            "• B.Com - Room 201\n" +
                            "• BBA - Room 202\n" +
                            "• Physical Science Lab - Room 205\n\n" +
                            "Third Floor\n" +
                            "• Masters Classes - Room 301\n" +
                            "• Seminar Hall - Room 305"
            );
        } else if (lowerOption.equals("streams")) {
            detailTitleText.setText("All Streams");
            detailBodyText.setText(
                    "Available Streams / Courses\n\n" +
                            "• BCA - Bachelor of Computer Applications\n" +
                            "• B.Com - Bachelor of Commerce\n" +
                            "• BBA - Bachelor of Business Administration\n" +
                            "• Masters Programs\n" +
                            "• Physical Science\n" +
                            "• Arts\n" +
                            "• Commerce"
            );
        } else if (lowerOption.contains("upload")) {
            detailTitleText.setText(title + " - Upload Homework / Notes");
            detailBodyText.setText("Upload homework, assignments or notes from your device. Teachers can review selected submissions.\n\nNote: This demo stores the selected file name on screen. For real teacher dashboard, connect this app with Firebase or a backend server.");
            selectFileButton.setVisibility(View.VISIBLE);
            fileNameText.setVisibility(View.VISIBLE);
        } else {
            detailTitleText.setText(title + " - " + option);
            detailBodyText.setText("This section is prepared for " + option + ".\n\nYou can add PDF links, text content, images or uploaded documents here for " + title + ".");
        }

        selectFileButton.setOnClickListener(v -> openFilePicker());
    }

    private void openFilePicker() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(Intent.createChooser(intent, "Select Homework or Notes"), PICK_FILE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_FILE_REQUEST && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();
            fileNameText.setText("Selected file: " + getFileName(uri));
        }
    }

    private String getFileName(Uri uri) {
        String result = "Selected document";
        if (uri == null) return result;
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        try {
            if (cursor != null && cursor.moveToFirst()) {
                int nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
                if (nameIndex >= 0) {
                    result = cursor.getString(nameIndex);
                }
            }
        } finally {
            if (cursor != null) cursor.close();
        }
        return result;
    }
}
