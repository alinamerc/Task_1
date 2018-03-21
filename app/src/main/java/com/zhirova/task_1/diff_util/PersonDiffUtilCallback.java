package com.zhirova.task_1.diff_util;


import android.support.v7.util.DiffUtil;
import android.util.Log;

import com.zhirova.task_1.store.Person;
import java.util.List;


public class PersonDiffUtilCallback extends DiffUtil.Callback {

    private final List<Person> oldList;
    private final List<Person> newList;


    public PersonDiffUtilCallback(List<Person> oldList, List<Person> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }


    @Override
    public int getOldListSize() {
        return oldList.size();
    }


    @Override
    public int getNewListSize() {
        return newList.size();
    }


    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        Person oldPerson = oldList.get(oldItemPosition);
        Person newPerson = newList.get(newItemPosition);

        return oldPerson.getId().equals(newPerson.getId());
    }


    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        Person oldPerson = oldList.get(oldItemPosition);
        Person newPerson = newList.get(newItemPosition);

        return oldPerson.getName().equals(newPerson.getName())
                && oldPerson.getPhone().equals(newPerson.getPhone());
    }


}
