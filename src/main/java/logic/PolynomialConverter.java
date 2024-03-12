package logic;

import model.Monomial;

import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PolynomialConverter {
    private Double extractCoef(String term) {
        String coefficientStr = term.trim().replaceAll("X(\\^[+-]?[0-9]+)?", "").trim();

        if (coefficientStr.isEmpty() || coefficientStr.equals("+")) {
            return 1.0; // Default coefficient if empty or "+"
        } else if (coefficientStr.equals("-")) {
            return -1.0; // Handle "-" as -1.0
        }

        try {
            return Double.parseDouble(coefficientStr);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Invalid coefficient format: " + coefficientStr);
        }
    }



    private Integer extractDeg(String term) {
        // Updated regex to capture the exponent part correctly
        String degStr = term.trim().replaceAll(".*X(\\^([+-]?[0-9]+))?", "$2");

        if (degStr.isEmpty() || degStr.equals("-") || degStr.equals("+")) {
            return 1; // Default degree if empty, "-" or "+"
        }

        try {
            return Integer.parseInt(degStr);
        } catch (NumberFormatException e) {
            // Handle the case where the exponent string is not a valid number
            throw new NumberFormatException("Invalid degree format: " + degStr);
        }
    }

    public static Map<Integer, Monomial> parsePolynomial(String expression) {
        Map<Integer, Monomial> polynomialMap = new TreeMap<>();

        // Split the expression into monomials
        String[] monomials = expression.split("\\s*(?=[+-])");

        for (String monomial : monomials) {
            // Parse the monomial
            Monomial parsedMonomial = parseMonomial(monomial);
            // Add the parsed monomial to the polynomial map
            polynomialMap.put(parsedMonomial.getDegree(), parsedMonomial);
        }

        return polynomialMap;
    }

    public static Monomial parseMonomial(String monomial) {
        double coefficient = 0.0;
        int degree = 0;

        monomial = monomial.replaceAll("\\s+", "");

        if (monomial.isEmpty()) {
            return new Monomial(0.0, 0);
        }
        char sign = '+';
        if (monomial.charAt(0) == '+' || monomial.charAt(0) == '-') {
            sign = monomial.charAt(0);
            monomial = monomial.substring(1);
        }

        String[] parts = monomial.split("x", 2);

        if (parts.length == 0) {
            return new Monomial(0.0, 0);
        } else if (parts.length == 1) {
            if (!parts[0].isEmpty()) {
                coefficient = Double.parseDouble(sign + parts[0]);
            } else {
                coefficient = (sign == '+') ? 1.0 : -1.0;
            }
        } else {
            if (!parts[0].isEmpty()) {
                coefficient = Double.parseDouble(sign + parts[0]);
            } else {
                coefficient = (sign == '+') ? 1.0 : -1.0;
            }
            String term = parts[1];
            if (term.isEmpty()) {
                degree = 1;
            } else {
                degree = Integer.parseInt(term.substring(1));
            }
        }

        return new Monomial(coefficient, degree);
    }

}
