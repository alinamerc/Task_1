package com.zhirova.task_1.model;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhirova.task_1.R;
import com.zhirova.task_1.store.Person;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    private List<Person> persons;
    private ClickListener clickListener;


    public MyAdapter(Context context) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }


    public void setData(List<Person> persons){
        this.persons = persons;
        notifyDataSetChanged();
    }


    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }


    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.category_list_item, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(clickListener != null){
                    clickListener.onClick((Person)v.getTag());
                }
            }
        });
        return holder;
    }


    @Override
    public void onBindViewHolder(MyAdapter.MyViewHolder holder, int position) {
        Person curPerson = persons.get(position);
        holder.itemView.setTag(curPerson);

        holder.personId.setText(String.valueOf(position + 1) + ")");
        holder.personName.setText(curPerson.getName());
        holder.personPhone.setText(curPerson.getPhone());
    }


    @Override
    public int getItemCount() {
        return persons.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView personId;
        public TextView personName;
        public TextView personPhone;
        public ImageView personCircle;

        MyViewHolder(View view){
            super(view);
            personId = view.findViewById(R.id.category_id_text_view);
            personName = view.findViewById(R.id.category_name_text_view);
            personPhone = view.findViewById(R.id.category_phone_text_view);
            personCircle = view.findViewById(R.id.category_image_view);
        }
    }


    public interface ClickListener{
        void onClick(Person person);
    }


}
