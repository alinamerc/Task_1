package com.zhirova.task_1;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.zhirova.task_1.store.Person;
import com.zhirova.task_1.store.PersonStore;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private FragmentManager fragmentManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            goToItemsFragment();
        }
    }


    private void goToItemsFragment() {
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        ItemsFragment curFragment = new ItemsFragment();
        fragmentTransaction.replace(R.id.container, curFragment);
        fragmentTransaction.commit();
    }


    @Override
    protected void onStart() {
        super.onStart();
        fabItemsClick();
    }


    private void fabItemsClick() {
        FloatingActionButton floatingActionButtonItems = findViewById(R.id.fab_items);

        if (floatingActionButtonItems != null) {
            floatingActionButtonItems.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Snackbar.make(view, "Hello Snackbar", Snackbar.LENGTH_LONG).show();

                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    FillingFormFragment curFragment = new FillingFormFragment();

                    fragmentTransaction.replace(R.id.container, curFragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();

                    fabFormClick();
                }
            });
        }
    }


    private void fabFormClick() {
        Log.d("MAIN", "FAB_FORM_CLICK");
        FloatingActionButton floatingActionButtonForm = findViewById(R.id.fab_form);

        if (floatingActionButtonForm != null) {
            floatingActionButtonForm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    PersonStore personStore = PersonStore.getInstance();
                    if (personStore != null) {
                        List<Person> persons = personStore.getPersons();
                        EditText nameView = findViewById(R.id.form_name_edit_text);
                        EditText phoneView = findViewById(R.id.form_phone_edit_text);

                        String nameText = nameView.getText().toString();
                        String phoneText = phoneView.getText().toString();

                        if (nameText.isEmpty() || phoneText.isEmpty()) {
                            Snackbar.make(view, R.string.form_empty_data, Snackbar.LENGTH_LONG).show();
                        } else {
                            Person newPerson = new Person(persons.size() + 1, nameText, phoneText);
                            if (personStore.add(newPerson)) {
                                fragmentManager.popBackStack();
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


}
