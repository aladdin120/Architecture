package pr_2;

import pr_2.Equation;

import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
    public static final String UNIQUE_BINDING_NAME = "server.equation";

    public static void main(String[] args) throws RemoteException, NotBoundException {

        final Registry registry = LocateRegistry.getRegistry(2732);

        Equation equation = (Equation) registry.lookup(UNIQUE_BINDING_NAME);
        String result = equation.solution(3, 4, 5);

        System.out.println(result);
    }
}
