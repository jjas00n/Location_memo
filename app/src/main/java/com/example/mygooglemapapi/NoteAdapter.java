package com.example.mygooglemapapi;

import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {

    private List<Note> noteList;
    private OnNoteClickListener onNoteClickListener;

    public interface OnNoteClickListener {
        void onNoteClick(Note note);
    }

    public NoteAdapter(List<Note> noteList, OnNoteClickListener onNoteClickListener) {
        this.noteList = noteList;
        this.onNoteClickListener = onNoteClickListener;
    }

    public void updateList(List<Note> newList) {
        noteList.clear();
        noteList.addAll(newList);
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        Note note = noteList.get(position);
        holder.bind(note, onNoteClickListener);
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    static class NoteViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageViewNote;
        private TextView textViewPlace;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewNote = itemView.findViewById(R.id.imageViewNote);
            textViewPlace = itemView.findViewById(R.id.textViewPlace);
        }

        public void bind(Note note, OnNoteClickListener onNoteClickListener) {
            imageViewNote.setImageBitmap(note.getImage());
            textViewPlace.setText(note.getPlace());
            itemView.setOnClickListener(v -> onNoteClickListener.onNoteClick(note));
        }
    }
}
