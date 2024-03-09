package single_point_access;

import logic.Operations;
import logic.OperationsImplementation;
import logic.PolynomialConverter;

public class SinglePointAccess {
    private static Operations operations;
    private static PolynomialConverter polynomialConverter;
    static{
        operations= new OperationsImplementation();
        polynomialConverter = new PolynomialConverter();
    }
    public static Operations getOperations() {
        return operations;
    }

    public static PolynomialConverter getPolynomialConverter() {
        return polynomialConverter;
    }
}
