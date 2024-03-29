package model;

import java.util.*;

public class Polynomial {
    TreeMap<Integer, Monomial> monomials;

    public Polynomial() {
        this.monomials = new TreeMap<>();
    }

    public TreeMap<Integer, Monomial> getMonomials() {
        return monomials;
    }

    public void setMonomials(TreeMap<Integer, Monomial> monomials) {
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
    @Override
    public String toString() {
        return "Polynomial{" +
                "monomials=" + monomials;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Polynomial that = (Polynomial) o;
        return Objects.equals(monomials, that.monomials);
    }

    @Override
    public int hashCode() {
        return Objects.hash(monomials);
    }
}
