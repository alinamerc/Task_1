package com.zhirova.task_1.model;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.zhirova.task_1.R;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    private List<Note> notes;


    public MyAdapter(Context context, List<Note> notes) {
        this.context = context;
        this.notes = notes;
        this.inflater = LayoutInflater.from(context);
    }


    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.category_list_item, parent, false);
        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(MyAdapter.MyViewHolder holder, int position) {
        Note curNote = notes.get(position);
        holder.circle.setImageResource(curNote.getImage());
        holder.item.setText(context.getResources().getString(R.string.item) + curNote.getId());
    }


    @Override
    public int getItemCount() {
        return notes.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public final ImageView circle;
        public final TextView item;

        MyViewHolder(View view){
            super(view);
            circle = view.findViewById(R.id.category_image_view);
            item = view.findViewById(R.id.category_text_view);
        }
    }


}
