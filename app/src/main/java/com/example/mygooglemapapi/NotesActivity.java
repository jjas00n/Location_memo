package com.example.mygooglemapapi;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NotesActivity extends AppCompatActivity implements NoteAdapter.OnNoteClickListener {

    private RecyclerView recyclerViewNotes;
    private NoteAdapter noteAdapter;
    private List<Note> noteList;

    private EditText editTextSearch;
    private ImageButton buttonSearch;

    private ImageButton buttonBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        recyclerViewNotes = findViewById(R.id.recyclerViewNotes);
        editTextSearch = findViewById(R.id.editTextSearch);
        buttonSearch = findViewById(R.id.buttonSearch);
        buttonBack = findViewById(R.id.buttonBack);

        recyclerViewNotes.setLayoutManager(new LinearLayoutManager(this));

        noteList = loadNotesFromInternalStorage();
        noteAdapter = new NoteAdapter(noteList, this);
        recyclerViewNotes.setAdapter(noteAdapter);

        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchNotes();
            }
        });

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


    }

    private List<Note> loadNotesFromInternalStorage() {
        List<Note> notes = new ArrayList<>();
        File[] files = getFilesDir().listFiles();

        for (File file : files) {
            if (file.getName().startsWith("note_") && file.getName().endsWith(".txt")) {
                try (FileInputStream fis = openFileInput(file.getName())) {
                    int size = fis.available();
                    byte[] buffer = new byte[size];
                    fis.read(buffer);
                    String content = new String(buffer);

                    String[] lines = content.split("\n");
                    double lat = Double.parseDouble(lines[0].split(": ")[1]);
                    double lng = Double.parseDouble(lines[1].split(": ")[1]);
                    String place = lines[2].split(": ")[1];
                    String noteText = lines[3].split(": ")[1];
                    float rating = Float.parseFloat(lines[4].split(": ")[1]);

                    String imageName = file.getName().replace("note_", "image_").replace(".txt", ".png");
                    Bitmap image = null;
                    try (FileInputStream imageFis = openFileInput(imageName)) {
                        image = BitmapFactory.decodeStream(imageFis);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    notes.add(new Note(lat, lng, place, noteText, rating, image, imageName));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return notes;
    }


    private void searchNotes() {
        String query = editTextSearch.getText().toString().toLowerCase().trim();
        List<Note> filteredList = new ArrayList<>();

        if (TextUtils.isEmpty(query)) {
            filteredList.addAll(noteList);
        } else {
            for (Note note : noteList) {
                if (note.getText().toLowerCase().contains(query)) {
                    filteredList.add(note);
                }
            }
        }

        noteAdapter.updateList(filteredList);
    }


    @Override
    public void onNoteClick(Note note) {
        Intent intent = new Intent(this, NoteDetailActivity.class);
        intent.putExtra("latitude", note.getLatitude());
        intent.putExtra("longitude", note.getLongitude());
        intent.putExtra("place", note.getPlace());
        intent.putExtra("note", note.getText());
        intent.putExtra("rating", note.getRating());
        intent.putExtra("imageName", note.getImageName()); // 사진 파일 이름을 Intent에 추가
        startActivity(intent);
    }
}
