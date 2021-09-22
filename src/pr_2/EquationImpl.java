package pr_2;

import java.rmi.RemoteException;

public class EquationImpl implements Equation{

    @Override
    public String solution(double a, double b, double c) throws RemoteException {
        double D = b * b - 4 * a * c;
        if (D > 0) {
            double x1, x2;
            x1 = (-b - Math.sqrt(D)) / (2 * a);
            x2 = (-b + Math.sqrt(D)) / (2 * a);
            return "Корни уравнения: x1 = " + x1 + ", x2 = " + x2;
        }
        else if (D == 0) {
            double x;
            x = -b / (2 * a);
            return "Уравнение имеет единственный корень: x = " + x;
        }
        else {
            return "Уравнение не имеет действительных корней!";
        }
    }
}
