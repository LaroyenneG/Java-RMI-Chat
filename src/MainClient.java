import chat.IServer;
import chat.Message;
import chat.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class MainClient {

    public static void main(String[] args) {

        if (args.length != 2) {
            System.out.println("Invalid argument number");
            System.exit(-1);
        }

        try {

            Registry registry = LocateRegistry.getRegistry(args[0], Integer.parseInt(args[1]));

            Remote remote = registry.lookup("Chat");

            if (!(remote instanceof IServer)) {
                System.err.println("Invalid object");
                System.exit(-1);
            }

            IServer stub = (IServer) remote;

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));


            System.out.println("Welcome :-)\n");

            User user = null;
            do {

                System.out.println("User list : " + stub.getUserNameList());

                System.out.println("Enter a valid login (not already used) :");

                user = new User(reader.readLine());

            } while (!stub.register(user));


            boolean status;
            do {
                System.out.println("Group list :");
                System.out.println(stub.getListGroup());

                System.out.println("Do you want create a new group ? (yes/no)");

                boolean option = reader.readLine().equals("yes");

                System.out.println("Enter a group name :");

                String name = reader.readLine();

                status = (option) ? stub.buildNewGroup(name, user) : stub.addInGroup(name, user);

            } while (!status);

            System.out.println("You can write messages");

            String body;

            while (!(body = reader.readLine()).equals("exit")) {
                stub.sendMessage(new Message(user, body));
            }

        } catch (NotBoundException | IOException e) {
            e.printStackTrace();
        }


    }
}
