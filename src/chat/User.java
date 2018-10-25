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
    public void receiveMessage(Message m) throws RemoteException {
        System.out.println(m.toPrint());
    }

    @Override
    public void printGroup(Group group) throws RemoteException {
        System.out.println(group.toPrint());
    }
}

