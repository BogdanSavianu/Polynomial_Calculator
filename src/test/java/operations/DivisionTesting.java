package operations;

import logic.DivisionByZero;
import logic.Operations;
import model.Monomial;
import model.Polynomial;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import single_point_access.SinglePointAccess;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class DivisionTesting {
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
    public void division1() throws DivisionByZero {
        Polynomial pol1 = new Polynomial();
        Monomial m1 = new Monomial(3.0, 4);
        Monomial m2 = new Monomial(6.0, 3);
        Monomial m3 = new Monomial(-5.0, 2);
        Monomial m4 = new Monomial(9.0, 1);
        Monomial m5 = new Monomial(-3.0, 0);
        pol1.addMonomial(m1);
        pol1.addMonomial(m2);
        pol1.addMonomial(m3);
        pol1.addMonomial(m4);
        pol1.addMonomial(m5);

        Polynomial pol2 = new Polynomial();
        Monomial n1 = new Monomial(1.0, 2);
        Monomial n2 = new Monomial(-1.0, 1);
        Monomial n3 = new Monomial(1.0, 0);
        pol2.addMonomial(n1);
        pol2.addMonomial(n2);
        pol2.addMonomial(n3);

        List<Polynomial> result = op.divide(pol1, pol2);

        Polynomial quotient = new Polynomial();
        Monomial q1 = new Monomial(3.0, 2);
        Monomial q2 = new Monomial(9.0, 1);
        Monomial q3 = new Monomial(1.0,0);
        quotient.addMonomial(q1);
        quotient.addMonomial(q2);
        quotient.addMonomial(q3);

        Polynomial remainder = new Polynomial();
        Monomial r1 = new Monomial(1.0, 1);
        Monomial r2 = new Monomial(-4.0,0);
        remainder.addMonomial(r1);
        remainder.addMonomial(r2);

        List<Polynomial> expectedResult = new ArrayList<>();
        expectedResult.add(quotient);
        expectedResult.add(remainder);

        assertEquals(expectedResult, result);
        nrTestsPassed++;
    }

    @Test
    public void division2() throws DivisionByZero {
        Monomial mon4 = new Monomial(1.0,3);
        Monomial mon5 = new Monomial(-2.0,2);
        Monomial mon6 = new Monomial(6.0,1);
        Monomial mon7 = new Monomial(-5.0,0);
        Monomial mon8 = new Monomial(1.0,2);
        Monomial mon9 = new Monomial(-1.0,0);

        Polynomial pol1 = new Polynomial();
        Polynomial pol2 = new Polynomial();
        pol1.addMonomial(mon4);
        pol1.addMonomial(mon5);
        pol1.addMonomial(mon6);
        pol1.addMonomial(mon7);
        pol2.addMonomial(mon8);
        pol2.addMonomial(mon9);

        List<Polynomial> result = op.divide(pol1, pol2);

        Polynomial quotient = new Polynomial();
        Monomial q1 = new Monomial(1.0, 1);
        Monomial q2 = new Monomial(-2.0, 0);
        quotient.addMonomial(q1);
        quotient.addMonomial(q2);

        Polynomial remainder = new Polynomial();
        Monomial r1 = new Monomial(7.0, 1);
        Monomial r2 = new Monomial(-7.0,0);
        remainder.addMonomial(r1);
        remainder.addMonomial(r2);

        List<Polynomial> expectedResult = new ArrayList<>();
        expectedResult.add(quotient);
        expectedResult.add(remainder);
        assertEquals(expectedResult, result);
        nrTestsPassed++;
    }
    @Test
    public void division3() throws DivisionByZero {
        Monomial mon4 = new Monomial(54.0,1);
        Monomial mon5 = new Monomial(54.0,1);

        Polynomial pol1 = new Polynomial();
        Polynomial pol2 = new Polynomial();
        pol1.addMonomial(mon4);
        pol2.addMonomial(mon5);
        List<Polynomial> result = op.divide(pol1, pol2);

        Polynomial quotient = new Polynomial();
        Monomial q1 = new Monomial(1.0, 0);
        quotient.addMonomial(q1);

        Polynomial remainder = new Polynomial();

        List<Polynomial> expectedResult = new ArrayList<>();
        expectedResult.add(quotient);
        expectedResult.add(remainder);

        assertEquals(expectedResult, result);
        nrTestsPassed++;
    }
    @Test(expected = DivisionByZero.class)
    public void division4() throws DivisionByZero {
        Monomial mon4 = new Monomial(54.0,1);

        Polynomial pol1 = new Polynomial();
        Polynomial pol2 = new Polynomial();
        pol1.addMonomial(mon4);
        nrTestsPassed++;
        List<Polynomial> result = op.divide(pol1, pol2);

        Polynomial quotient = new Polynomial();
        Polynomial remainder = new Polynomial();

        List<Polynomial> expectedResult = new ArrayList<>();
        expectedResult.add(quotient);
        expectedResult.add(remainder);

    }
}
