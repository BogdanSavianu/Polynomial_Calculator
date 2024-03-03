package operations;

import model.Monomial;
import model.Polynomial;

import java.util.Map;

import static model.Polynomial.deepCloneMonomials;

public class OperationsImplementation implements Operations {
    @Override
    public Polynomial add(Polynomial pol1, Polynomial pol2) {
        Polynomial result;
        try {
            result = pol2.clone();
            result.setMonomials(deepCloneMonomials(pol2.getMonomials()));
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
        for(Map.Entry<Integer, Monomial> it: pol1.getMonomials().entrySet())
            if(it != null)
                result.addMonomial(it.getValue());
        return result;
    }

    @Override
    public Polynomial subtract(Polynomial pol1, Polynomial pol2) {
        Double inverse = -1.0;
        Polynomial result;
        try {
            result = pol1.clone();
            result.setMonomials(deepCloneMonomials(pol1.getMonomials()));
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
        for(Map.Entry<Integer, Monomial> it: pol2.getMonomials().entrySet())
            if(it != null) {
                it.getValue().setCoefficient(inverse * (Double) it.getValue().getCoefficient());
                result.addMonomial(it.getValue());
            }
        return result;
    }

    @Override
    public Polynomial multiply(Polynomial pol1, Polynomial pol2) {
        return null;
    }

    @Override
    public Polynomial divide(Polynomial pol1, Polynomial pol2) {
        return null;
    }

    @Override
    public Polynomial differentiate(Polynomial pol1, Polynomial pol2) {
        return null;
    }

    @Override
    public Polynomial integrate(Polynomial pol1, Polynomial pol2) {
        return null;
    }
}
