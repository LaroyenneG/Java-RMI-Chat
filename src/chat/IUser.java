package chat;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IUser extends Remote {

    String getName() throws RemoteException;

    void receiveMessage(Message m) throws RemoteException;

    void printGroup(Group group) throws RemoteException;
}
