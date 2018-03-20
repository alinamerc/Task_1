package com.zhirova.task_1;

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


public class FillingFormFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        initUI();
        View root = inflater.inflate(R.layout.fragment_filling_form, container, false);
        return root;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fabFormClick(view);
    }


    private void initUI() {
        ActionBar toolbar = ((AppCompatActivity)getContext()).getSupportActionBar();
        if (toolbar != null) {
            toolbar.setTitle(R.string.form_title);
        }
    }


    private void fabFormClick(View view) {
        FloatingActionButton floatingActionButtonForm = view.findViewById(R.id.fab_form);
        floatingActionButtonForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Hello, FillingFormFragment", Snackbar.LENGTH_LONG).show();
                Log.d("MAIN", "FAB_FORM_CLICK");

                PersonStore personStore = PersonStore.getInstance();
                if (personStore != null) {
                    List<Person> persons = personStore.getPersons();
                    EditText nameView = getView().findViewById(R.id.form_name_edit_text);
                    EditText phoneView = getView().findViewById(R.id.form_phone_edit_text);

                    String nameText = nameView.getText().toString();
                    String phoneText = phoneView.getText().toString();

                    if (nameText.isEmpty() || phoneText.isEmpty()) {
                        Snackbar.make(view, R.string.form_empty_data, Snackbar.LENGTH_LONG).show();
                    } else {
                        Person newPerson = new Person(persons.size() + 1, nameText, phoneText);
                        if (personStore.add(newPerson)) {
                            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                            fragmentManager.popBackStack();

                            View viewFocus = getActivity().getCurrentFocus();
                            if (viewFocus != null) {
                                InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                                imm.hideSoftInputFromWindow(viewFocus.getWindowToken(), 0);
                            }
                        }
                        else {
                            Snackbar.make(view, R.string.form_same_name, Snackbar.LENGTH_LONG).show();
                        }
                    }
                }
            }
        });
    }



}
