package operations;

import model.Polynomial;

public interface Operations {
    Polynomial add(Polynomial pol1, Polynomial pol2);
    Polynomial subtract(Polynomial pol1, Polynomial pol2);
    Polynomial multiply(Polynomial pol1, Polynomial pol2);
    Polynomial divide(Polynomial pol1, Polynomial pol2);

    Polynomial differentiate(Polynomial pol1, Polynomial pol2);
    Polynomial integrate(Polynomial pol1, Polynomial pol2);
}
