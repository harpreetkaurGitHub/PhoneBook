package dataStructures;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class PhoneBook {

    HashTable<PhoneNumber, String> phoneBookTable = new HashTable<>();
    ObjectMapper objectMapper = new ObjectMapper();

    public PhoneBook(int maxSize){
        if (phoneBookTable.size() <= maxSize){
        System.out.println("HashTable Bucket Size :" + maxSize);
        }
    }

    public void load(String filename){
       try {
           String file = new String(Files.readAllBytes(Paths.get(filename)));
           Map<String,String> map = objectMapper.readValue(file, new TypeReference<Map<String,String>>(){});

           for (Map.Entry<String,String> mapData : map.entrySet()){
               String phoneNumber = mapData.getKey();
               PhoneNumber phoneNumberObj = new PhoneNumber(phoneNumber);
               String name = mapData.getValue();
               addEntry(phoneNumberObj,name);
           }
       }
       catch (Exception e) {
           e.printStackTrace();
       }
    }

    public String numberLookup(PhoneNumber number){
        Iterator<HashNode<PhoneNumber,String>> iterator = phoneBookTable.keys();
        while (iterator.hasNext()){
            HashNode<PhoneNumber,String> hashNode = iterator.next();
            while (Objects.nonNull(hashNode)){
                if (hashNode.key.equals(number)){
                    return hashNode.value;
                }
            }
        }
        return null;
    }

    public PhoneNumber nameLookup(String name){
        Iterator<HashNode<PhoneNumber,String>> iterator = phoneBookTable.keys();
        while (iterator.hasNext()){
            HashNode<PhoneNumber,String> hashNode = iterator.next();
            while (Objects.nonNull(hashNode)){
                if (hashNode.value.equals(name)){
                    return hashNode.key;
                }
            }
        }
        return null;
    }

    public boolean addEntry(PhoneNumber number, String name){
            return phoneBookTable.add(number,name);
    }

    public boolean deleteEntry(PhoneNumber number){
        return phoneBookTable.delete(number);
    }

    public void printAll(){
        Iterator<HashNode<PhoneNumber,String>> iterator = phoneBookTable.keys();
        while (iterator.hasNext()){
            HashNode<PhoneNumber,String> hashNode = iterator.next();
            while (Objects.nonNull(hashNode)){
                System.out.println("Phone Number: "+hashNode.key + " Name: " + hashNode.value);
                hashNode = hashNode.next;
            }
        }
    }

    public void printByAreaCode(String code){
        Iterator<HashNode<PhoneNumber,String>> hashNodeIterator = phoneBookTable.keys();
        while (hashNodeIterator.hasNext()){
            HashNode<PhoneNumber,String> hashNode = hashNodeIterator.next();
            while (Objects.nonNull(hashNode)){
                if (hashNode.key.getAreaCode().equals(code)){
                    System.out.println("Phone Number: "+hashNode.key.getPhoneNumber()+ " Name: "+hashNode.value);
                }
                hashNode = hashNode.next;
            }
        }
    }

    public void printNames(){
        Iterator<HashNode<PhoneNumber,String>> phoneBookIterator = phoneBookTable.values();
        while (phoneBookIterator.hasNext()){
            HashNode<PhoneNumber,String> hashNode = phoneBookIterator.next();
            while (Objects.nonNull(hashNode)){
                System.out.println(hashNode.value);
                hashNode = hashNode.next;
            }
        }
    }

    public static void main(String[] args) {
        PhoneBook phoneBook = new PhoneBook(10);
        phoneBook.load("D:\\file2.json");
        phoneBook.printAll();

    }
}