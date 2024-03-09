package org.example;

import logic.PolynomialConverter;
import model.Monomial;
import model.Polynomial;
import logic.Operations;
import single_point_access.SinglePointAccess;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Main {
    public static void main(String[] args) {
        Operations operations = SinglePointAccess.getOperations();
        PolynomialConverter polynomialConverter = SinglePointAccess.getPolynomialConverter();
        Monomial mon1 = new Monomial(1.0,2);
        Monomial mon2 = new Monomial(2.0, 1);
        Polynomial pol1 = new Polynomial();
        pol1.addMonomial(mon1);
        pol1.addMonomial(mon2);

        Monomial mon3 = new Monomial(3.0,1);
        Polynomial pol2 = new Polynomial();
        pol2.addMonomial(mon3);

        Polynomial resultAdd = operations.add(pol1,pol2);
        System.out.println(pol1);
        System.out.println(pol2);
        System.out.println(resultAdd);
        Polynomial resultSub = operations.subtract(pol1,pol2);
        System.out.println(resultSub);
        Polynomial resultDifferentiation = operations.differentiate(pol1);
        System.out.println(resultDifferentiation);
        Polynomial resultIntegration = operations.integrate(pol1);
        System.out.println(resultIntegration);
        Polynomial resultMultiplication = operations.multiply(pol1,pol2);
        System.out.println(resultMultiplication);

        Monomial mon4 = new Monomial(1.0,3);
        Monomial mon5 = new Monomial(-2.0,2);
        Monomial mon6 = new Monomial(6.0,1);
        Monomial mon7 = new Monomial(-5.0,0);
        Monomial mon8 = new Monomial(1.0,2);
        Monomial mon9 = new Monomial(-1.0,0);

        Polynomial pol3 = new Polynomial();
        Polynomial pol4 = new Polynomial();
        pol3.addMonomial(mon4);
        pol3.addMonomial(mon5);
        pol3.addMonomial(mon6);
        pol3.addMonomial(mon7);
        pol4.addMonomial(mon8);
        pol4.addMonomial(mon9);

        List<Polynomial> resultDivision = operations.divide(pol3,pol4);
        System.out.println(resultDivision);

        String regex = "([-+]?([0-9]*\\.?[0-9]+)?(X(\\^[+-]?[0-9]+)?)?)+";
        String expression = "3x^3 - 2x^2 + 3x + 5";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(expression);
        Map<Integer, Monomial> polynomialMap = polynomialConverter.parsePolynomialExpression(expression, regex);
        System.out.println(polynomialMap);
    }
}