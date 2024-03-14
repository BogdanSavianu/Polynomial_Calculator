package logic;

import model.Monomial;
import model.Polynomial;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static model.Polynomial.deepCloneMonomials;

public class OperationsImplementation implements Operations {
    @Override
    public Polynomial add(Polynomial pol1, Polynomial pol2) {
        Polynomial result = pol2;
//        try {
//            result = pol2.clone();
//            result.setMonomials(deepCloneMonomials(pol2.getMonomials()));
//        } catch (CloneNotSupportedException e) {
//            throw new RuntimeException(e);
//        }
        for(Map.Entry<Integer, Monomial> it: pol1.getMonomials().entrySet())
            if(it != null) {
                result.addMonomial(it.getValue());
                if(result.getMonomials().get(it.getKey()).getCoefficient().doubleValue() == 0.0)
                    result.getMonomials().remove(it.getKey());
            }
        return result;
    }

    @Override
    public Polynomial subtract(Polynomial pol1, Polynomial pol2) {
        Double inverse = -1.0;
        Polynomial result = pol1;
//        try {
//            result = pol1.clone();
//            result.setMonomials(deepCloneMonomials(pol1.getMonomials()));
//        } catch (CloneNotSupportedException e) {
//            throw new RuntimeException(e);
//        }
        for(Map.Entry<Integer, Monomial> it: pol2.getMonomials().entrySet())
            if(it != null) {
                result.addMonomial(new Monomial(it.getValue().getCoefficient().doubleValue() * inverse, it.getKey()));
                if(result.getMonomials().get(it.getKey()).getCoefficient().doubleValue()==0.0)
                    result.getMonomials().remove(it.getKey());
            }
        return result;
    }

    @Override
    public Polynomial multiply(Polynomial pol1, Polynomial pol2) {
        Polynomial result = new Polynomial();
        for(Map.Entry<Integer, Monomial> it_pol1: pol1.getMonomials().entrySet()){
            for(Map.Entry<Integer, Monomial> it_pol2: pol2.getMonomials().entrySet()) {
                Monomial newMonomial = new Monomial(it_pol1.getValue().getCoefficient().doubleValue(), it_pol1.getKey());
                newMonomial.setCoefficient(newMonomial.getCoefficient().doubleValue() * it_pol2.getValue().getCoefficient().doubleValue());
                newMonomial.setDegree(newMonomial.getDegree() + it_pol2.getKey());
                if (result.getMonomials().containsKey(newMonomial.getDegree()))
                    newMonomial.setCoefficient(newMonomial.getCoefficient().doubleValue() + result.getMonomials().get(newMonomial.getDegree()).getCoefficient().doubleValue());
                result.getMonomials().put(newMonomial.getDegree(),newMonomial);
            }
        }

        return result;
    }

    @Override
    public List<Polynomial> divide(Polynomial pol1, Polynomial pol2) throws DivisionByZero {
        if(pol2.getMonomials().isEmpty()) {
            throw new DivisionByZero("Cannot divide by 0");
        }
        Polynomial remainder, q;
        List<Polynomial> list = new ArrayList<>();
        try {
            q= new Polynomial();
            remainder = pol1.clone();
            remainder.setMonomials(deepCloneMonomials(pol1.getMonomials()));
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }

        while(!remainder.getMonomials().isEmpty() && degree(remainder).compareTo(degree(pol2))>=0) {
            Monomial t = new Monomial(lead(remainder).getCoefficient().doubleValue() / lead(pol2).getCoefficient().doubleValue(),lead(remainder).getDegree() - lead(pol2).getDegree());
            q.addMonomial(t);
            Polynomial aux = new Polynomial();
            aux.addMonomial(t);
            aux = multiply(aux, pol2);
            remainder = subtract(remainder, aux);
        }
        list.add(q);
        list.add(remainder);
        return list;
    }

    public Integer degree(Polynomial pol){
        return pol.getMonomials().lastEntry().getKey();
    }

    public Monomial lead(Polynomial pol){
        return pol.getMonomials().lastEntry().getValue();
    }

    @Override
    public Polynomial differentiate(Polynomial pol) {
        Polynomial result = new Polynomial();
        for(Map.Entry<Integer,Monomial> it: pol.getMonomials().entrySet()) {
            if(it != null && it.getKey()!=0) {
                Monomial differentiated = new Monomial(it.getValue().getDegree().doubleValue() * (Double) it.getValue().getCoefficient(), it.getKey() - 1);
                result.getMonomials().put(differentiated.getDegree(), differentiated);
            }
        }
        return result;
    }

    @Override
    public Polynomial integrate(Polynomial pol) {
        Polynomial result = new Polynomial();

        for(Map.Entry<Integer,Monomial> it: pol.getMonomials().entrySet()) {
            if(it != null) {
                BigDecimal dividend = BigDecimal.valueOf(it.getValue().getCoefficient().doubleValue());
                BigDecimal divisor = BigDecimal.valueOf(it.getValue().getDegree().doubleValue()+1);
                BigDecimal coef = dividend.divide(divisor,3,RoundingMode.HALF_EVEN);
                Monomial integrated = new Monomial(coef.doubleValue(), it.getKey() + 1);
                result.getMonomials().put(integrated.getDegree(), integrated);
                //result.getMonomials().remove(integrated.getDegree()-1);
            }
        }
        return result;
    }
}
