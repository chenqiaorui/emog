package com.emog;

import org.junit.jupiter.api.Test;

public class testSerializable {
   @Test
    public  void testWrite(){
        Person person=new Person();
        person.setId(1);
        person.setName("张丹");
        SerializationUtils.write(person);
    }

//    @Test
//    public  void testRead(){
//        Person p = (Person) SerializationUtils.read();
//        System.out.println(p.getName());
//    }
}