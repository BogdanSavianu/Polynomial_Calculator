package operations;

import logic.Operations;
import model.Monomial;
import model.Polynomial;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import single_point_access.SinglePointAccess;

import static org.junit.Assert.assertEquals;

public class DifferentiationTesting {
    private static Integer nrTests = 0;
    private static Integer nrTestsPassed = 0;
    private static Operations op;

    @BeforeClass
    public static void initialize() {
        System.out.println("Starting tests");
        op = SinglePointAccess.getOperations();
    }

    @AfterClass
    public static void finish() {
        System.out.println(nrTests + " have been executed and " + nrTestsPassed + " have passed.");
        op = null;
    }

    @After
    public void increment(){
        nrTests++;
    }

    @Test
    public void differentiate1() {
        Polynomial polynomial = new Polynomial();
        Monomial m1 = new Monomial(5.0, 4);
        Monomial m2 = new Monomial(-3.0, 3);
        Monomial m3 = new Monomial(2.0, 2);
        Monomial m4 = new Monomial(4.0, 1);
        polynomial.addMonomial(m1);
        polynomial.addMonomial(m2);
        polynomial.addMonomial(m3);
        polynomial.addMonomial(m4);

        Polynomial result = op.differentiate(polynomial);

        Polynomial expectedResult = new Polynomial();
        Monomial d1 = new Monomial(20.0, 3);
        Monomial d2 = new Monomial(-9.0, 2);
        Monomial d3 = new Monomial(4.0, 1);
        Monomial d4 = new Monomial(4.0, 0);
        expectedResult.addMonomial(d1);
        expectedResult.addMonomial(d2);
        expectedResult.addMonomial(d3);
        expectedResult.addMonomial(d4);

        assertEquals(expectedResult.getMonomials(), result.getMonomials());
        nrTestsPassed++;
    }

    @Test
    public void differentiate2() {
        Polynomial polynomial = new Polynomial();
        Monomial m1 = new Monomial(1.0, 5);
        Monomial m2 = new Monomial(-4.0, 3);
        Monomial m3 = new Monomial(6.0, 2);
        polynomial.addMonomial(m1);
        polynomial.addMonomial(m2);
        polynomial.addMonomial(m3);

        Polynomial result = op.differentiate(polynomial);

        Polynomial expectedResult = new Polynomial();
        Monomial d1 = new Monomial(5.0, 4);
        Monomial d2 = new Monomial(-12.0, 2);
        Monomial d3 = new Monomial(12.0, 1);
        expectedResult.addMonomial(d1);
        expectedResult.addMonomial(d2);
        expectedResult.addMonomial(d3);

        assertEquals(expectedResult.getMonomials(), result.getMonomials());
        nrTestsPassed++;
    }

    @Test
    public void differentiate3() {
        Polynomial polynomial = new Polynomial();
        Monomial m1 = new Monomial(3.0, 4);
        Monomial m2 = new Monomial(-2.0, 2);
        Monomial m3 = new Monomial(5.0, 1);
        polynomial.addMonomial(m1);
        polynomial.addMonomial(m2);
        polynomial.addMonomial(m3);

        Polynomial result = op.differentiate(polynomial);

        Polynomial expectedResult = new Polynomial();
        Monomial d1 = new Monomial(12.0, 3);
        Monomial d2 = new Monomial(-4.0, 1);
        Monomial d3 = new Monomial(5.0,0);
        expectedResult.addMonomial(d1);
        expectedResult.addMonomial(d2);
        expectedResult.addMonomial(d3);

        assertEquals(expectedResult.getMonomials(), result.getMonomials());
        nrTestsPassed++;
    }

}
