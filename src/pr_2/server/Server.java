package pr_2.server;

import pr_2.Equation;
import pr_2.EquationImpl;

import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;


public class Server extends EquationImpl {
    public Server () {}

    public static void main(String[] args) throws RemoteException, AlreadyBoundException {
        try {
            EquationImpl equation = new EquationImpl();

            Equation stub = (Equation) UnicastRemoteObject.exportObject(equation, 0);

            Registry registry = LocateRegistry.getRegistry();
            registry.bind("Equation", stub);
            System.out.println("Ready");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
