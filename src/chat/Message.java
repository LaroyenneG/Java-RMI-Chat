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


    public String toPrint() throws RemoteException {

        StringBuilder builder = new StringBuilder();

        builder.append("Message \n");
        builder.append("At :");
        builder.append(date);
        builder.append('\n');
        builder.append("From :");

        builder.append(from.getName());

        builder.append('\n');

        builder.append(body);

        return new String(builder);
    }

    public IUser getSender() {
        return from;
    }
}
