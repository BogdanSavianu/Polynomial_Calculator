package logic;

import model.Polynomial;

import java.util.List;

public interface Operations {
    Polynomial add(Polynomial pol1, Polynomial pol2);
    Polynomial subtract(Polynomial pol1, Polynomial pol2);
    Polynomial multiply(Polynomial pol1, Polynomial pol2);
    List<Polynomial> divide(Polynomial pol1, Polynomial pol2) throws DivisionByZero;
    Polynomial differentiate(Polynomial pol);
    Polynomial integrate(Polynomial pol);
}
