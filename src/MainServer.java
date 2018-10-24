import chat.Server;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class MainServer {

    public static void main(String[] args) {

        try {
            Server skeleton = new Server();

            String url = "rmi://localhost/TestRMI";

            LocateRegistry.createRegistry(1099);

            Naming.rebind(url, skeleton);

        } catch (RemoteException | MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
