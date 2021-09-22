package pr_2;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Equation extends Remote {
    void solution(double a, double b, double c) throws RemoteException;
}

