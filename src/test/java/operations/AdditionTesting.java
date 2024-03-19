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

public class AdditionTesting {
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
    public void addition1() {
        Polynomial pol1 = new Polynomial();
        Monomial m1 = new Monomial(-1.0,5);
        Monomial m2 = new Monomial(1.0, 3);
        Monomial m3 = new Monomial(5.0, 2);
        Monomial m4 = new Monomial(10.0,1);
        Monomial m5 = new Monomial(-5.0,0);
        pol1.addMonomial(m1);
        pol1.addMonomial(m2);
        pol1.addMonomial(m3);
        pol1.addMonomial(m4);
        pol1.addMonomial(m5);

        Polynomial pol2 = new Polynomial();
        Monomial m6 = new Monomial(-1.0,5);
        Monomial m7 = new Monomial(1.0, 3);
        Monomial m8 = new Monomial(5.0, 2);
        Monomial m9 = new Monomial(10.0,1);
        pol2.addMonomial(m6);
        pol2.addMonomial(m7);
        pol2.addMonomial(m8);
        pol2.addMonomial(m9);

        Polynomial check = new Polynomial();
        Monomial n1 = new Monomial(-2.0,5);
        Monomial n2 = new Monomial(2.0, 3);
        Monomial n3 = new Monomial(10.0, 2);
        Monomial n4 = new Monomial(20.0,1);
        Monomial n5 = new Monomial(-5.0,0);
        check.addMonomial(n1);
        check.addMonomial(n2);
        check.addMonomial(n3);
        check.addMonomial(n4);
        check.addMonomial(n5);

        Polynomial result = op.add(pol1,pol2);
        assertEquals(check,result);
        nrTestsPassed++;
    }

    @Test
    public void addition2() {
        Polynomial pol1 = new Polynomial();
        Monomial m1 = new Monomial(-1.0,5);
        Monomial m2 = new Monomial(1.0, 3);
        Monomial m3 = new Monomial(5.0, 2);
        Monomial m4 = new Monomial(10.0,1);
        Monomial m5 = new Monomial(-5.0,0);
        pol1.addMonomial(m1);
        pol1.addMonomial(m2);
        pol1.addMonomial(m3);
        pol1.addMonomial(m4);
        pol1.addMonomial(m5);

        Polynomial pol2 = new Polynomial();
        Monomial m6 = new Monomial(1.0,5);
        Monomial m7 = new Monomial(-1.0, 3);
        Monomial m8 = new Monomial(17.0, 2);
        Monomial m9 = new Monomial(2.0,1);
        pol2.addMonomial(m6);
        pol2.addMonomial(m7);
        pol2.addMonomial(m8);
        pol2.addMonomial(m9);

        Polynomial check = new Polynomial();
        Monomial n3 = new Monomial(22.0, 2);
        Monomial n4 = new Monomial(12.0,1);
        Monomial n5 = new Monomial(-5.0,0);
        check.addMonomial(n3);
        check.addMonomial(n4);
        check.addMonomial(n5);

        Polynomial result = op.add(pol1,pol2);
        assertEquals(check,result);
        nrTestsPassed++;
    }
}
