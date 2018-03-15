package com.zhirova.task_1;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.zhirova.task_1.model.MyAdapter;
import com.zhirova.task_1.model.Note;

import java.util.List;


public class RecyclerviewFragment extends Fragment {

    private MyAdapter adapter;
    private List<Note> notes;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        initUI();
        View root = inflater.inflate(R.layout.fragment_recyclerview, container, false);
        return root;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerViewConnection(view);
    }


    private void recyclerViewConnection(View view) {
        Note.init();
        notes = Note.getAllNotes();
        adapter = new MyAdapter(getContext(), notes);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setAdapter(adapter);
    }


    private void initUI() {
        ActionBar toolbar = ((AppCompatActivity) getContext()).getSupportActionBar();
        if (toolbar != null) {
            toolbar.setTitle(R.string.recycle_title);
        }
    }


}
