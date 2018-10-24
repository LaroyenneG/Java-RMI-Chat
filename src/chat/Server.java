package chat;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class Server extends UnicastRemoteObject implements IServer {

    private List<IUser> userList;
    private List<Group> groupList;

    public Server() throws RemoteException {
        groupList = new ArrayList<>();
        userList = new ArrayList<>();
    }

    @Override
    public List<Group> getListGroup() {
        return groupList;
    }


    @Override
    public boolean register(IUser user) {

        boolean b = userList.contains(user);

        if (!b) {
            userList.add(user);
        }

        return !b;
    }

    @Override
    public boolean addInGroup(String name, IUser user) {

        if (!userList.contains(user)) {
            return false;
        }

        for (Group g : groupList) {
            if (g.getName().equals(name)) {
                return g.addUser(user);
            }
        }

        return false;
    }

    @Override
    public boolean sendMessage(Message message) throws RemoteException {

        if (!userList.contains(message.getSender())) {
            return false;
        }

        for (Group g : groupList) {
            if (g.contains(message.getSender())) {
                g.sendMessage(message);
            }
        }

        return false;
    }

    @Override
    public List<String> getUserNameList() throws RemoteException {

        List<String> stringList = new ArrayList<>();

        for (IUser u : userList) {
            stringList.add(u.getName());
        }

        return stringList;
    }

    @Override
    public boolean buildNewGroup(String name, IUser user) {

        if (!userList.contains(user)) {
            return false;
        }

        boolean b = true;


        for (Group g : groupList) {
            if (g.getName().equals(name)) {
                b = false;
                break;
            }
        }

        if (b) {
            Group group = new Group(name);
            group.addUser(user);
            groupList.add(group);
        }

        return b;
    }
}
