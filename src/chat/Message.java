package chat;

import java.io.Serializable;
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

        builder.append("chat.Message");
        builder.append('\n');
        builder.append("At :");
        builder.append(date);
        builder.append('\n');
        builder.append("From :");
        builder.append(from);
        builder.append('\n');

        builder.append(body);

        return new String(builder);
    }

    public IUser getSender() {
        return from;
    }
}
