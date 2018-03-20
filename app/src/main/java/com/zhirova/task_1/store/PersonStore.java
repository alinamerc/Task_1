package com.zhirova.task_1.store;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
        Person person1 = new Person(UUID.randomUUID().toString(), "Ivanov", "1111");
        persons.add(person1);
        Person person2 = new Person(UUID.randomUUID().toString(), "Smirnov", "2222");
        persons.add(person2);
        Person person3 = new Person(UUID.randomUUID().toString(), "Sidorova", "3333");
        persons.add(person3);
        Person person4 = new Person(UUID.randomUUID().toString(), "Osipov", "4444");
        persons.add(person4);
        Person person5 = new Person(UUID.randomUUID().toString(), "Glebova", "5555");
        persons.add(person5);
        Person person6 = new Person(UUID.randomUUID().toString(), "Andreev", "6666");
        persons.add(person6);
        Person person7 = new Person(UUID.randomUUID().toString(), "Scherbakov", "7777");
        persons.add(person7);
        Person person8 = new Person(UUID.randomUUID().toString(), "Lazutova", "8888");
        persons.add(person8);
        Person person9 = new Person(UUID.randomUUID().toString(), "Baranov", "9999");
        persons.add(person9);
        Person person10 = new Person(UUID.randomUUID().toString(), "Arbyzov", "1010");
        persons.add(person10);
        Person person11 = new Person(UUID.randomUUID().toString(), "Gladkova", "34534");
        persons.add(person11);
        Person person12 = new Person(UUID.randomUUID().toString(), "Avdeev", "4569678");
        persons.add(person12);
        Person person13 = new Person(UUID.randomUUID().toString(), "Kozlov", "6732");
        persons.add(person13);
        Person person14 = new Person(UUID.randomUUID().toString(), "Marinina", "564");
        persons.add(person14);
        Person person15 = new Person(UUID.randomUUID().toString(), "Polykova", "68876");
        persons.add(person15);
        Person person16 = new Person(UUID.randomUUID().toString(), "Sopov", "23434");
        persons.add(person16);
        Person person17 = new Person(UUID.randomUUID().toString(), "Kolosov", "567569");
        persons.add(person17);
        Person person18 = new Person(UUID.randomUUID().toString(), "Kalyagina", "78978");
        persons.add(person18);
        Person person19 = new Person(UUID.randomUUID().toString(), "Stepanov", "1231567");
        persons.add(person19);
        Person person20 = new Person(UUID.randomUUID().toString(), "Sinnikov", "56789");
        persons.add(person20);
    }


    public boolean add(Person newPerson){
        for (int i = 0; i < persons.size(); i++) {
            Person oldPerson = persons.get(i);
            if (oldPerson.getName().equalsIgnoreCase(newPerson.getName())) {
                return false;
            }
        }
        persons.add(newPerson);
        return true;
    }


    public void delete(String id){
        for (int i = 0; i < persons.size(); i++) {
            Person curPerson = persons.get(i);
            String personId = curPerson.getId();

            if (id.equals(personId)) {
                persons.remove(i);
                break;
            }
        }
    }


    public List<Person> getPersons(){
        return persons;
    }


}
