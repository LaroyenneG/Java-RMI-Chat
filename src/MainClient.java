import chat.IServer;
import chat.Message;
import chat.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;

public class MainClient {

    public static void main(String[] args) {


        try {

            Remote remote = Naming.lookup("rmi://localhost/TestRMI");

            if (!(remote instanceof IServer)) {
                System.err.println("Invalid object");
                System.exit(-1);
            }

            IServer server = (IServer) remote;


            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));


            System.out.println("Welcome :-)\n");

            User user = null;
            do {

                System.out.println("User list : " + server.getUserNameList());

                System.out.println("Enter a valid login (not already used) :");

                user = new User(reader.readLine());

            } while (!server.register(user));


            boolean status;
            do {
                System.out.println("Group list :");
                System.out.println(server.getListGroup());

                System.out.println("Do you want create a new group ? (yes/no)");

                boolean option = reader.readLine().equals("yes");

                System.out.println("Enter a group name :");

                String name = reader.readLine();

                status = (option) ? server.buildNewGroup(name, user) : server.addInGroup(name, user);

            } while (!status);


            String body;

            while (!(body = reader.readLine()).equals("exit")) {
                server.sendMessage(new Message(user, body));
            }

        } catch (NotBoundException | IOException e) {
            e.printStackTrace();
        }


    }
}
