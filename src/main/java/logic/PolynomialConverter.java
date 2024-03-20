package logic;

import model.Monomial;
import model.Polynomial;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import static java.lang.Math.abs;

public class PolynomialConverter {

    public static Polynomial parse(String expression){
        Polynomial result = new Polynomial();
        expression = expression.toLowerCase();
        if(expression.contains("^^"))
            throw new NumberFormatException();
        for(int i = 0; i<expression.length(); i++) {
            String c = expression.substring(i,i+1);
            if("qwertyuiopasdfghjklzcvbnm".contains(c))
                throw new NumberFormatException();
        }
        final String regex = "([+-?]*\\d*)x*\\^?(\\d*)";

        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(expression);

        while (matcher.find()) {
            if(matcher.group().isEmpty())
                break;
            String coefString = matcher.group(1);
            String degString = matcher.group(2);
            Double coefficient;
            Integer degree;
            if(coefString.isEmpty())
                coefficient = 1.0;
            else if(coefString.equals("-"))
                coefficient = -1.0;
            else if(coefString.equals("+"))
                coefficient = 1.0;
            else coefficient = parseDouble(coefString);

            if(matcher.group(0).contains("x")) {
                if (degString.isEmpty())
                    degree = 1;
                else degree = parseInt(degString);
            }
            else degree = 0;
            Monomial monomial = new Monomial(coefficient, degree);
            if(coefficient!=0.0)
                result.addMonomial(monomial);
        }
        return result;
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
                if(it.getKey() == 0 && abs(it.getValue().getCoefficient().doubleValue())==1.0){
                    if(it.getValue().getCoefficient().doubleValue() == -1.0)
                        polynomial.append("1");
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
