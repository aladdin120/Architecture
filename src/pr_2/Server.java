package pr_2;

import pr_2.Equation;
import pr_2.EquationImpl;

import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;


public class Server extends EquationImpl {
    public static final String UNIQUE_BINDING_NAME = "server.equation";

    public static void main(String[] args) throws RemoteException, AlreadyBoundException, InterruptedException {
        final EquationImpl server = new EquationImpl();
        final Registry registry = LocateRegistry.createRegistry(2732);

        Remote stub = UnicastRemoteObject.exportObject(server, 0);
        registry.bind(UNIQUE_BINDING_NAME, stub);

        Thread.sleep(Integer.MAX_VALUE);
    }
}
