package com.zhirova.task_1;

import android.annotation.SuppressLint;
import android.app.LauncherActivity;
import android.content.Context;
import android.icu.text.AlphabeticIndex;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.zhirova.task_1.model.ItemList;

import java.util.ArrayList;
import java.util.List;


public class RecyclerviewFragment extends Fragment {

    private String name;
     MyAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
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
        int ITEM_COUNT = 50;
        int COLORS[] = {};

        for (int i = 0; i < ITEM_COUNT; i++) {
            ItemList newItem = new ItemList(i + 1, true, 0);
        }

        RecyclerView categoryRecyclerView = view.findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        categoryRecyclerView.setLayoutManager(layoutManager);
        adapter = new MyAdapter(getContext());
        categoryRecyclerView.setAdapter(adapter);

        //adapter.setData(ItemList.items);
    }


    private void initUI() {
        ActionBar toolbar = ((AppCompatActivity) getContext()).getSupportActionBar();
        if (toolbar != null) {
            toolbar.setTitle(R.string.recycle_title);
        }
    }


    private static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView categoryTextView;
        private RadioButton categoryRadioButton;
        private int curPosition;


        public MyViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.category_list_item, parent, false));
            itemView.setOnClickListener(this);
            categoryTextView = itemView.findViewById(R.id.category_text_view);
            //categoryRadioButton = itemView.findViewById(R.id.category_radiobutton);
        }


        public void bind(ItemList curItem) {
            String itemText = R.string.item + String.valueOf(curItem.getId());
            categoryTextView.setText(itemText);

            if (curItem.isExistCircle()) {
                int itemColor = curItem.getCircleColor();
                categoryRadioButton.setVisibility(View.VISIBLE);
                categoryRadioButton.setBackgroundColor(itemColor);
            } else {
                categoryRadioButton.setVisibility(View.INVISIBLE);
            }
        }


        @Override
        public void onClick(View view) {
            //Toast toast = Toast.makeText(getContext(), "You are pushed on item " + (curPosition + 1), Toast.LENGTH_SHORT);
            //toast.show();
        }
    }

    public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

        Context context;

         List<String> items = new ArrayList<>();

         public MyAdapter(Context context) {
            this.context = context;
            name = "s";
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            return new MyViewHolder(inflater, parent);
        }


        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            //curPosition = position;
            ItemList curItem = ItemList.items.get(position);
             String item = items.get(position);
             holder.categoryTextView.setText(R.string.app_name);

        }


        @Override
        public int getItemCount() {
            return items.size();
        }

        public void setData(List<String> data){
             this.items = data;
             notifyDataSetChanged();
        }
    }


}
