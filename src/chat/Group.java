package chat;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class Group implements Serializable {

    private List<Message> messageList;
    private List<IUser> userList;
    private String name;

    public Group(String name) {
        this.name = name;
        userList = new ArrayList<>();
        messageList = new ArrayList<>();
    }

    public boolean sendMessage(Message message) throws RemoteException {

        boolean b = userList.contains(message.getSender());

        if (b) {

            for (IUser u : userList) {
                if (!u.equals(message.getSender())) {
                    messageList.add(message);
                    u.receiveMessage(message);
                }
            }

        }

        return b;
    }

    public boolean addUser(IUser user) throws RemoteException {

        if (userList.contains(user)) {
            return false;
        }

        userList.add(user);

        for (IUser u : userList) {
            u.printGroup(this);
        }

        return true;
    }

    public boolean contains(IUser user) {
        return userList.contains(user);
    }

    @Override
    public String toString() {

        StringBuilder builder = new StringBuilder();

        builder.append(name);
        builder.append(" :\n\t");

        for (IUser user : userList) {
            try {
                builder.append(user.getName());
                builder.append(' ');
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        return new String(builder);
    }

    public String getName() {
        return name;
    }
}
