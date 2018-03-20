package com.zhirova.task_1;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.zhirova.task_1.store.Person;
import com.zhirova.task_1.store.PersonStore;

import java.util.List;
import java.util.UUID;


public class FillingFormFragment extends Fragment {

    public final static String TAG = "FillingFormFragment";
    private EditText nameView;
    EditText phoneView;
    FloatingActionButton floatingActionButtonForm;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_filling_form, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUI();
    }


    private void initUI() {
        ActionBar toolbar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        if (toolbar != null) {
            toolbar.setTitle(R.string.form_title);
        }
        nameView = getView().findViewById(R.id.form_name_edit_text);
        phoneView = getView().findViewById(R.id.form_phone_edit_text);
        floatingActionButtonForm = getView().findViewById(R.id.fab_form);
        floatingActionButtonForm.setOnClickListener(view1 -> {
            //Snackbar.make(view, "Hello, FillingFormFragment", Snackbar.LENGTH_LONG).show();
            if(!isResumed()) return;
            createItem();
        });
    }

    private void createItem(){
        PersonStore personStore = PersonStore.getInstance();
        if (personStore != null) {
            String nameText = nameView.getText().toString();
            String phoneText = phoneView.getText().toString();
            Activity activity =getActivity();
            if (nameText.isEmpty() || phoneText.isEmpty()) {
                Snackbar.make(floatingActionButtonForm, R.string.form_empty_data, Snackbar.LENGTH_LONG).show();
            } else {
                Person newPerson = new Person(UUID.randomUUID().toString(), nameText, phoneText);
                if (personStore.add(newPerson)) {
                    if(activity instanceof NewItemListener){
                        ((NewItemListener)activity).onNewItem(newPerson.getId());
                    }
                    getActivity().onBackPressed();
                    View viewFocus = getActivity().getCurrentFocus();
                    if (viewFocus != null) {
                        InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(viewFocus.getWindowToken(), 0);
                    }
                } else {
                    Snackbar.make(floatingActionButtonForm, R.string.form_same_name, Snackbar.LENGTH_LONG).show();
                }
            }
        }
    }

    public interface NewItemListener{
        void onNewItem(String id);
    }

}
