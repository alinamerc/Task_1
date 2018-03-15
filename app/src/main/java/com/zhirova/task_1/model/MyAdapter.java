package com.zhirova.task_1.model;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
    public void onBindViewHolder(MyAdapter.MyViewHolder holder, final int position) {
        final Note curNote = notes.get(position);
        holder.item.setText(context.getResources().getString(R.string.item) + curNote.getId());

        if (curNote.isExistCircle()) {
            holder.circle.setVisibility(View.VISIBLE);
            holder.circle.setImageResource(curNote.getImage());
        }
        else {
            holder.circle.setVisibility(View.INVISIBLE);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = context.getResources().getString(R.string.click_message) + " " + String.valueOf(position + 1);
                Toast toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
                toast.show();
            }
        });
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
