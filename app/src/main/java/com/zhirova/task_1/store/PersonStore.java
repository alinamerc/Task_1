package com.zhirova.task_1.store;

import java.util.ArrayList;
import java.util.List;

public class PersonStore {

    private static PersonStore INSTANCE;
    private List<Person> persons = new ArrayList<>();


    public static PersonStore getInstance(){
        if(INSTANCE == null){
            INSTANCE = new PersonStore();
        }
        return INSTANCE;
    }


    private PersonStore(){
        Person person1 = new Person(1, "Ivanov", "1111");
        persons.add(person1);
        Person person2 = new Person(2, "Smirnov", "2222");
        persons.add(person2);
        Person person3 = new Person(3, "Sidorova", "3333");
        persons.add(person3);
        Person person4 = new Person(4, "Osipov", "4444");
        persons.add(person4);
        Person person5 = new Person(5, "Glebova", "5555");
        persons.add(person5);
        Person person6 = new Person(6, "Andreev", "6666");
        persons.add(person6);
        Person person7 = new Person(7, "Scherbakov", "7777");
        persons.add(person7);
        Person person8 = new Person(8, "Lazutova", "8888");
        persons.add(person8);
        Person person9 = new Person(9, "Baranov", "9999");
        persons.add(person9);
        Person person10 = new Person(10, "Arbyzov", "1010");
        persons.add(person10);
        Person person11 = new Person(11, "Gladkova", "34534");
        persons.add(person11);
        Person person12 = new Person(12, "Avdeev", "4569678");
        persons.add(person12);
        Person person13 = new Person(13, "Kozlov", "6732");
        persons.add(person13);
        Person person14 = new Person(14, "Marinina", "564");
        persons.add(person14);
        Person person15 = new Person(15, "Polykova", "68876");
        persons.add(person15);
        Person person16 = new Person(16, "Sopov", "23434");
        persons.add(person16);
        Person person17 = new Person(17, "Kolosov", "567569");
        persons.add(person17);
        Person person18 = new Person(18, "Kalyagina", "78978");
        persons.add(person18);
        Person person19 = new Person(19, "Stepanov", "1231567");
        persons.add(person19);
        Person person20 = new Person(20, "Sinnikov", "56789");
        persons.add(person20);
    }


    public void add(Person person){

    }


    public void delete(int id){
        for (int i = 0; i < persons.size(); i++) {
            Person curPerson = persons.get(i);
            int personId = curPerson.getId();

            if (id == personId) {
                persons.remove(i);
                break;
            }
        }
    }


    public List<Person> getPersons(){
        return persons;
    }


}
