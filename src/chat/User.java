package chat;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class User extends UnicastRemoteObject implements IUser, Serializable {

    private String name;

    public User(String name) throws RemoteException {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void receiveMessage(Message m) {
        System.out.println("New massage :\n");
        System.out.println(m);
    }

    @Override
    public void printGroup(Group group) {
        System.out.println(group);
    }

    @Override
    public boolean equals(Object user) {

        if (user instanceof User) {
            return ((User) user).name.equals(name);
        }

        return false;
    }
}

