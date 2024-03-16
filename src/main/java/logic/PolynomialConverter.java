package logic;

import model.Monomial;
import model.Polynomial;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static java.lang.Math.abs;

public class PolynomialConverter {

    public static Polynomial parsePolynomial(String expression) {
        TreeMap<Integer, Monomial> polynomialMap = new TreeMap<>();
        Polynomial result = new Polynomial();

        String[] monomials = expression.split("\\s*(?=[+-])");

        for (String monomial : monomials) {
            Monomial parsedMonomial = parseMonomial(monomial);
            polynomialMap.put(parsedMonomial.getDegree(), parsedMonomial);
        }


        ///TODO check if the following is actually necessary
        List<Integer> toDelete = new ArrayList<>();
        for(Map.Entry<Integer,Monomial> it: polynomialMap.entrySet())
            if(it.getValue().getCoefficient().doubleValue() == 0.0)
                toDelete.add(it.getKey());
        for(Integer it: toDelete)
            polynomialMap.remove(it);
        result.setMonomials(polynomialMap);
        return result;
    }

    public static Monomial parseMonomial(String monomial) {
        double coefficient = 0.0;
        int degree = 0;

        monomial = monomial.replaceAll("\\s+", "");


        ///TODO maybe without this, the previous todo can be deleted
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

    public static String printPolynomial(Polynomial pol){
        StringBuilder polynomial = new StringBuilder();

        for(Map.Entry<Integer, Monomial> it: pol.getMonomials().descendingMap().entrySet()){
            if(it.getValue().getCoefficient().doubleValue() != 0){
                if(it.getValue().getCoefficient().doubleValue() < 0.0)
                    polynomial.append(" ");
                else if(!polynomial.isEmpty())
                    polynomial.append("+ ");
                if(it.getValue().getCoefficient().doubleValue() != 1.0){
                    if(it.getValue().getCoefficient().doubleValue() == -1.0)
                        polynomial.append("-");
                    else {
                        if(it.getValue().getCoefficient().doubleValue() == it.getValue().getCoefficient().intValue())
                            polynomial.append(String.valueOf(it.getValue().getCoefficient().intValue()));
                        else {
                            BigDecimal coef = new BigDecimal(it.getValue().getCoefficient().doubleValue());
                            polynomial.append(String.valueOf(coef.setScale(3, RoundingMode.HALF_EVEN)));
                        }
                    }
                }

            }
            if(it.getKey() == 1 )
                polynomial.append("x");
            else if(it.getKey() > 1) {
                polynomial.append("x^");
                polynomial.append(String.valueOf(it.getKey()));
            }
        }
        return polynomial.toString();
    }

}
