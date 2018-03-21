package com.zhirova.task_1;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhirova.task_1.diff_util.PersonDiffUtilCallback;
import com.zhirova.task_1.model.ItemsAdapter;
import com.zhirova.task_1.store.Person;
import com.zhirova.task_1.store.PersonStore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class ItemsFragment extends Fragment implements ItemsAdapter.ClickListener {

    public final static String TAG = "ItemsFragment";
    private String needScrollId;
    private PersonStore personStore;

    private ItemsAdapter adapter;
    private FloatingActionButton floatingActionButtonItems;
    private RecyclerView recyclerView;


    public void setNeedScroll(String id){
        this.needScrollId = id;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_items, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUI();
    }


    private void initUI() {
        ActionBar toolbar = ((AppCompatActivity)getContext()).getSupportActionBar();
        if (toolbar != null) {
            toolbar.setTitle(R.string.recycle_title);
        }
        recyclerView = getView().findViewById(R.id.recycler_view);
        floatingActionButtonItems = getView().findViewById(R.id.fab_items);
        recyclerViewConnection();
        fabItemsClick();
    }


    private void recyclerViewConnection() {
        personStore = PersonStore.getInstance();
        List<Person> persons = personStore.getPersons();

        adapter = new ItemsAdapter(getContext());
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

        Collections.sort(persons, (person1, person2) -> person1.getName().compareToIgnoreCase(person2.getName()));
        adapter.setData(persons);

        Handler scrollHandler = new Handler(Looper.getMainLooper());
        scrollHandler.postDelayed(() -> {
            if (needScrollId != null){
                int scrollPos = adapter.positionById(needScrollId);
                if (scrollPos >= 0) {
                    recyclerView.smoothScrollToPosition(scrollPos);
                    needScrollId = null;
                }
            }
        }, 200);
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

        builder.setPositiveButton(buttonYes, (dialog, which) -> {
            List<Person> oldList = new ArrayList<>(personStore.getPersons());
            personStore.delete(person.getId());
            List<Person> newList = new ArrayList<>(personStore.getPersons());

            PersonDiffUtilCallback personDiffUtilCallback = new PersonDiffUtilCallback(oldList, newList);
            DiffUtil.DiffResult personDiffResult = DiffUtil.calculateDiff(personDiffUtilCallback, true);

            adapter.setData(personStore.getPersons());
            personDiffResult.dispatchUpdatesTo(adapter);

        });

        builder.setNegativeButton(buttonNo, (dialog, arg1) -> dialog.dismiss());
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


    private void fabItemsClick() {
        floatingActionButtonItems.setOnClickListener(view -> {
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FillingFormFragment curFragment = new FillingFormFragment();

            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container, curFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        });
    }


}
