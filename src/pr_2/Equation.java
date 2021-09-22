package pr_2;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Equation extends Remote {
    String solution(double a, double b, double c) throws RemoteException;
}

