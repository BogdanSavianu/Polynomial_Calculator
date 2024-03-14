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

public class IntegrationTesting {
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
    public void integration1() {
        Polynomial polynomial = new Polynomial();
        Monomial m1 = new Monomial(3.0, 4);
        Monomial m2 = new Monomial(-2.0, 2);
        Monomial m3 = new Monomial(5.0, 1);
        polynomial.addMonomial(m1);
        polynomial.addMonomial(m2);
        polynomial.addMonomial(m3);

        Polynomial result = op.integrate(polynomial);

        Polynomial expectedResult = new Polynomial();
        Monomial i1 = new Monomial(0.6, 5); // 0.6x^5
        Monomial i2 = new Monomial(-0.667, 3); // -0.6667x^3
        Monomial i3 = new Monomial(2.5, 2); // 2.5x^2
        expectedResult.addMonomial(i1);
        expectedResult.addMonomial(i2);
        expectedResult.addMonomial(i3);

        assertEquals(expectedResult.getMonomials(), result.getMonomials());
        nrTestsPassed++;
    }
    @Test
    public void integration2() {
        Polynomial polynomial = new Polynomial();
        Monomial m1 = new Monomial(2.0, 3);
        Monomial m2 = new Monomial(-1.0, 2);
        Monomial m3 = new Monomial(4.0, 1);
        polynomial.addMonomial(m1);
        polynomial.addMonomial(m2);
        polynomial.addMonomial(m3);

        Polynomial result =op.integrate(polynomial);

        Polynomial expectedResult = new Polynomial();
        Monomial r1 = new Monomial(0.5, 4);
        Monomial r2 = new Monomial(-0.333, 3);
        Monomial r3 = new Monomial(2.0, 2);
        expectedResult.addMonomial(r1);
        expectedResult.addMonomial(r2);
        expectedResult.addMonomial(r3);

        assertEquals(expectedResult, result);
    }
}
