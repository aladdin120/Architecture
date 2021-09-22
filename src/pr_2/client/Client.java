package pr_2.client;

import pr_2.Equation;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
    private Client() {};

    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry(null);
            Equation stub = (Equation) registry.lookup("Equation");
            stub.solution(1, 2, 3);
        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }
    }
}
