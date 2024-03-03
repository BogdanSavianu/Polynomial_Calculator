package model;

public class Monomial implements Cloneable{
    private Number coefficient;
    private Integer degree;

    public Monomial(Double coefficient, Integer degree) {
        this.coefficient = coefficient;
        this.degree = degree;
    }

    public Number getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(Number coefficient) {
        this.coefficient = coefficient;
    }

    public Integer getDegree() {
        return degree;
    }

    public void setDegree(Integer degree) {
        this.degree = degree;
    }
    @Override public Monomial clone(){
        try {
            return (Monomial) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public String toString() {
        return coefficient+"* x^" + degree+" ";
    }
}
