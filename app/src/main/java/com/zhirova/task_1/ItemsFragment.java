package com.zhirova.task_1;

import android.content.DialogInterface;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhirova.task_1.model.MyAdapter;
import com.zhirova.task_1.store.Person;
import com.zhirova.task_1.store.PersonStore;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class ItemsFragment extends Fragment implements MyAdapter.ClickListener {
    public final static String TAG = "ItemsFragment";
    private PersonStore personStore;
    private List<Person> persons;
    private MyAdapter adapter;
    private FloatingActionButton floatingActionButtonItems;
    private String needScroolId;
    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        initUI();
        View root = inflater.inflate(R.layout.fragment_items, container, false);
        return root;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recycler_view);
        floatingActionButtonItems = view.findViewById(R.id.fab_items);
        recyclerViewConnection(view);
        fabItemsClick();
    }


    private void initUI() {
        ActionBar toolbar = ((AppCompatActivity)getContext()).getSupportActionBar();
        if (toolbar != null) {
            toolbar.setTitle(R.string.recycle_title);
        }
    }

    public void setNeedScrool(String id){
        this.needScroolId = id;
    }

    private void recyclerViewConnection(View view) {
        personStore = PersonStore.getInstance();
        persons = personStore.getPersons();

        adapter = new MyAdapter(getContext());
        adapter.setClickListener(this);

        adapter.setData(null);
        recyclerView.setAdapter(adapter);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0 && floatingActionButtonItems.getVisibility() == View.VISIBLE) {
                    floatingActionButtonItems.hide();
                } else if (dy < 0 && floatingActionButtonItems.getVisibility() != View.VISIBLE) {
                    floatingActionButtonItems.show();
                }
            }
        });

        Collections.sort(persons, new Comparator<Person>() {
            @Override
            public int compare(Person person1, Person person2) {
                return person1.getName().compareToIgnoreCase(person2.getName());
            }
        });
        adapter.setData(persons);
      // recyclerView.smoothScrollToPosition(adapter.getItemCount() - 1);
        Handler h = new Handler(Looper.getMainLooper());

        h.postDelayed(() -> {
            if(needScroolId != null){
                int scrollPos = adapter.positionById(needScroolId);
                recyclerView.smoothScrollToPosition(scrollPos);
                needScroolId = null;
            }
        }, 200);

    }


    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onClick(Person person) {
        if (person == null) return;

        String message = getResources().getString(R.string.click_message) + " " + person.getName();
        Snackbar snackbar = Snackbar.make(floatingActionButtonItems, message, Snackbar.LENGTH_LONG);
        snackbar.show();

        showDialog(person);
    }


    public void showDialog(final Person person) {
        String title = getResources().getString(R.string.title_question);
        String message = getResources().getString(R.string.desc_question) + " " + (person.getName()) + "?";
        String buttonYes = getResources().getString(R.string.yes);
        String buttonNo = getResources().getString(R.string.no);

        AlertDialog.Builder builder = new AlertDialog.Builder(this.getContext());
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setCancelable(false);

        builder.setPositiveButton(buttonYes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                personStore.delete(person.getId());
                adapter.setData(personStore.getPersons());
            }
        });

        builder.setNegativeButton(buttonNo, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int arg1) {
                dialog.dismiss();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


    private void fabItemsClick() {

        floatingActionButtonItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Hello, ItemsFragment", Snackbar.LENGTH_LONG).show();

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FillingFormFragment curFragment = new FillingFormFragment();

                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container, curFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
    }


}
