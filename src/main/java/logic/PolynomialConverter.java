package logic;

import model.Monomial;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PolynomialConverter {
    private Double extractCoef(String term) {
        String coefficientStr = term.replaceAll("X(\\^[+-]?[0-9]+)?", "").trim();

        if (coefficientStr.isEmpty()) {
            return 1.0; // Default coefficient if empty
        } else if (coefficientStr.equals("-")) {
            return -1.0; // Handle "-" as -1.0
        }

        try {
            return Double.parseDouble(coefficientStr);
        } catch (NumberFormatException e) {
            // Handle the case where the coefficient string is not a valid number
            throw new NumberFormatException("Invalid coefficient format: " + coefficientStr);
        }
    }


    private Integer extractDeg(String term) {
        String degStr = term.replaceAll(".*X(\\^([+-]?[0-9]+))?", "$2").trim();

        if (degStr.isEmpty()) {
            return 0;
        }

        try {
            return Integer.parseInt(degStr);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Invalid degree format: " + degStr);
        }
    }


    public Map<Integer, Monomial> parsePolynomialExpression(String expression, String regex) {
        Map<Integer, Monomial> polynomialMap = new TreeMap<>();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(expression);

        while (matcher.find()) {
            String term = matcher.group();

            if (term != null && !term.isEmpty()) {
                System.out.println(term);
                Double coef = extractCoef(term);
                Integer degree = extractDeg(term);
                polynomialMap.put(degree, new Monomial(coef,degree));
            }
        }

        return polynomialMap;
    }
}
