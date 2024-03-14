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

public class MultiplicationTesting {
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
    public void multiplication1() {
        Polynomial pol1 = new Polynomial();
        Monomial m1 = new Monomial(2.0, 2);
        Monomial m2 = new Monomial(3.0, 1);
        pol1.addMonomial(m1);
        pol1.addMonomial(m2);

        Polynomial pol2 = new Polynomial();
        Monomial m3 = new Monomial(1.0, 3);
        Monomial m4 = new Monomial(4.0, 1);
        pol2.addMonomial(m3);
        pol2.addMonomial(m4);

        Polynomial check = new Polynomial();
        Monomial n1 = new Monomial(2.0, 5);
        Monomial n2 = new Monomial(8.0, 3);
        Monomial n3 = new Monomial(3.0, 4);
        Monomial n4 = new Monomial(12.0, 2);
        check.addMonomial(n1);
        check.addMonomial(n2);
        check.addMonomial(n3);
        check.addMonomial(n4);

        Polynomial result = op.multiply(pol1, pol2);
        assertEquals(check.getMonomials(), result.getMonomials());
        nrTestsPassed++;
    }
    @Test
    public void multiplication2() {
        Polynomial pol1 = new Polynomial();
        Monomial m1 = new Monomial(-2.0, 2);
        Monomial m2 = new Monomial(3.0, 1);
        pol1.addMonomial(m1);
        pol1.addMonomial(m2);

        Polynomial pol2 = new Polynomial();
        Monomial m3 = new Monomial(1.0, 1);
        Monomial m4 = new Monomial(4.0, 2);
        pol2.addMonomial(m3);
        pol2.addMonomial(m4);

        Polynomial check = new Polynomial();
        Monomial n1 = new Monomial(-2.0, 3);
        Monomial n2 = new Monomial(-8.0, 4);
        Monomial n3 = new Monomial(3.0, 2);
        Monomial n4 = new Monomial(12.0, 3);
        check.addMonomial(n1);
        check.addMonomial(n2);
        check.addMonomial(n3);
        check.addMonomial(n4);

        Polynomial result = op.multiply(pol1, pol2);
        assertEquals(check.getMonomials(), result.getMonomials());
        nrTestsPassed++;
    }

    @Test
    public void multiplication3() {
        Polynomial pol1 = new Polynomial();
        Monomial m1 = new Monomial(-3.0, 3);
        Monomial m2 = new Monomial(2.0, 1);
        pol1.addMonomial(m1);
        pol1.addMonomial(m2);

        Polynomial pol2 = new Polynomial();
        Monomial m3 = new Monomial(-1.0, 2);
        Monomial m4 = new Monomial(4.0, 1);
        pol2.addMonomial(m3);
        pol2.addMonomial(m4);

        Polynomial check = new Polynomial();
        Monomial n1 = new Monomial(3.0, 5);
        Monomial n2 = new Monomial(-12.0, 4);
        Monomial n3 = new Monomial(-2.0, 3);
        Monomial n4 = new Monomial(8.0, 2);
        check.addMonomial(n1);
        check.addMonomial(n2);
        check.addMonomial(n3);
        check.addMonomial(n4);

        Polynomial result = op.multiply(pol1, pol2);
        assertEquals(check.getMonomials(), result.getMonomials());
        nrTestsPassed++;
    }


}
