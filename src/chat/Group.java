package chat;

import java.util.ArrayList;
import java.util.List;

public class Group {

    private List<Message> messageList;
    private List<User> userList;
    private String name;

    public Group(String name) {
        this.name = name;
        userList = new ArrayList<>();
        messageList = new ArrayList<>();
    }


    public boolean addUser(User user) {

        if (userList.contains(user)) {
            return false;
        }

        userList.add(user);
        return true;
    }

    @Override
    public String toString() {

        StringBuilder builder = new StringBuilder();

        builder.append("chat.Group ");
        builder.append(name);
        builder.append(" :\n");
        builder.append(userList);

        return new String(builder);
    }
}
