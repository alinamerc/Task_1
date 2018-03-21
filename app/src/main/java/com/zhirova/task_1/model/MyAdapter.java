package com.zhirova.task_1.model;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhirova.task_1.R;
import com.zhirova.task_1.store.Person;

import java.util.ArrayList;
import java.util.List;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private final LayoutInflater inflater;
    private List<Person> persons = new ArrayList<>();
    private ClickListener clickListener;
    private Context context;


    public MyAdapter(Context context) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }


    public void setData(List<Person> persons){
        this.persons = persons;
        //notifyDataSetChanged();
    }


    public int positionById(String id){
        for(int i = 0; i < persons.size(); i++){
            if(persons.get(i).getId().equals(id)){
                return i;
            }
        }
        return -1;
    }


    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }


    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.category_list_item, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        holder.itemView.setOnClickListener(v -> {
            if(clickListener != null){
                clickListener.onClick((Person)v.getTag());
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

        colorUpdate(holder, position);
    }


    @Override
    public int getItemCount() {
        return persons.size();
    }


    private void colorUpdate(MyAdapter.MyViewHolder holder, int position) {
        int RESOURCES[] = {R.color.circleRed, R.color.circleOrange, R.color.circleYellow,
                R.color.circleGreen, R.color.circleBlue, R.color.circleDarkBlue, R.color.circlePurple};

        int curIndex = (position + 1) % 8;
        if (curIndex % 8 == 0) {
            holder.personCircle.setVisibility(View.INVISIBLE);
        }
        else {
            holder.personCircle.setVisibility(View.VISIBLE);
            holder.personCircle.setColorFilter(ContextCompat.getColor(context, RESOURCES[curIndex - 1]));
        }
    }


     static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView personId;
        private TextView personName;
        private TextView personPhone;
        private  ImageView personCircle;

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
