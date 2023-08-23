package rmi2;

import java.rmi.RemoteException;

public class Dog implements Animal{
    private String name;
    public Dog(String name){
        this.name = name;
    }
    @Override
    public void speak()throws RemoteException{
        System.out.println("woof");
    }
    @Override
    public void printName()throws RemoteException{
        System.out.println("Dog " + name + " ");
    }
}
