import chat.Server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class MainServer {

    public static void main(String[] args) {

        if (args.length != 1) {
            System.err.println("Invalid argument number");
            System.exit(-1);
        }

        try {
            Server skeleton = new Server();

            Registry registry = LocateRegistry.createRegistry(Integer.parseInt(args[0]));

            registry.rebind("Chat", skeleton);

            System.out.println("Server is started");

        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
