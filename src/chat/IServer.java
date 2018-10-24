package chat;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IServer extends Remote {

    List<Group> getListGroup() throws RemoteException;

    boolean register(IUser user) throws RemoteException;

    boolean addInGroup(String name, IUser user) throws RemoteException;

    boolean sendMessage(Message message) throws RemoteException;

    List<String> getUserNameList() throws RemoteException;

    boolean buildNewGroup(String name, IUser user) throws RemoteException;
}
