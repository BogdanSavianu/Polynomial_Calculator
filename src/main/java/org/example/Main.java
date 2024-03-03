package org.example;

import model.Monomial;
import model.Polynomial;
import operations.Operations;
import single_point_access.SinglePointAccess;
import operations.*;


public class Main {
    public static void main(String[] args) {
        Operations operations = SinglePointAccess.getOperations();
        Monomial mon1 = new Monomial(1.0,2);
        Monomial mon2 = new Monomial(2.0, 1);
        Polynomial pol1 = new Polynomial();
        pol1.addMonomial(mon1);
        pol1.addMonomial(mon2);

        Monomial mon3 = new Monomial(3.0,1);
        Polynomial pol2 = new Polynomial();
        pol2.addMonomial(mon3);

        Polynomial resultAdd = operations.add(pol1,pol2);
        System.out.println(resultAdd);
        System.out.println(pol1);
        System.out.println(pol2);
        Polynomial resultSub = operations.subtract(pol1,pol2);
        System.out.println(resultSub);

    }
}