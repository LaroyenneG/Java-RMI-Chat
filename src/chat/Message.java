package chat;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.Date;

public class Message implements Serializable {

    private IUser from;
    private String body;
    private Date date;

    public Message(IUser from, String body) {
        this.body = body;
        this.from = from;
        date = new Date();
    }

    @Override
    public String toString() {

        StringBuilder builder = new StringBuilder();

        builder.append("Message");
        builder.append('\n');
        builder.append("At :");
        builder.append(date);
        builder.append('\n');
        builder.append("From :");
        try {
            builder.append(from.getName());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        builder.append('\n');

        builder.append(body);

        return new String(builder);
    }

    public IUser getSender() {
        return from;
    }
}
