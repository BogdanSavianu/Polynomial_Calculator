package model;

import java.util.HashMap;
import java.util.Map;

public class Polynomial implements Cloneable{
    HashMap<Integer, Monomial> monomials;

    public Polynomial() {
        this.monomials = new HashMap<>();
    }

    public HashMap<Integer, Monomial> getMonomials() {
        return monomials;
    }

    public void setMonomials(HashMap<Integer, Monomial> monomials) {
        this.monomials = monomials;
    }

    public void addMonomial(Monomial monomialToAdd){
        Monomial newMonomial = this.monomials.get(monomialToAdd.getDegree());
        if(monomialToAdd != null && newMonomial != null)
            newMonomial.setCoefficient((Double) newMonomial.getCoefficient() + (Double) monomialToAdd.getCoefficient());
        else if(monomialToAdd != null && newMonomial == null)
            newMonomial = monomialToAdd;
        this.monomials.put(monomialToAdd.getDegree(), newMonomial);
    }
    public static HashMap<Integer, Monomial> deepCloneMonomials(HashMap<Integer, Monomial> monomials) {
        HashMap<Integer, Monomial> clonedMonomials = new HashMap<>();
        for (Map.Entry<Integer, Monomial> entry : monomials.entrySet()) {
            clonedMonomials.put(entry.getKey(), entry.getValue().clone());
        }
        return clonedMonomials;
    }
    @Override public Polynomial clone() throws CloneNotSupportedException {
        return (Polynomial) super.clone();
    }
    @Override
    public String toString() {
        return "Polynomial{" +
                "monomials=" + monomials;
    }
}
