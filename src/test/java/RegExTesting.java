import logic.PolynomialConverter;
import model.Monomial;
import model.Polynomial;
import org.junit.*;
import single_point_access.SinglePointAccess;

import static org.junit.Assert.assertEquals;

public class RegExTesting {
    private static Integer nrTests = 0;
    private static Integer nrTestsPassed = 0;

    private static PolynomialConverter polynomialConverter = SinglePointAccess.getPolynomialConverter();

    @BeforeClass
    public static void initialize() {
        System.out.println("Starting tests");
    }

    @AfterClass
    public static void finish() {
        System.out.println(nrTests + " have been executed and " + nrTestsPassed + " have passed.");
        polynomialConverter = null;
    }

    @After
    public void increment() {
        nrTests++;
    }

    @Test
    public void test1(){
        String expression = "x^4 + 3x^3 - 20x^2 + 3x + 5";
        expression = expression.replace(" ", "");
        Polynomial result = polynomialConverter.parse(expression);
        Polynomial check = new Polynomial();
        Monomial m1 = new Monomial(1.0,4);
        Monomial m2 = new Monomial(3.0, 3);
        Monomial m3 = new Monomial(-20.0, 2);
        Monomial m4 = new Monomial(3.0,1);
        Monomial m5 = new Monomial(5.0,0);
        check.addMonomial(m1);
        check.addMonomial(m2);
        check.addMonomial(m3);
        check.addMonomial(m4);
        check.addMonomial(m5);

        assertEquals(check, result);
        nrTestsPassed++;
    }

    @Test
    public void test2(){
        String expression = "-x^5 + x^3 + 5x^2 + 10x - 5";
        expression = expression.replace(" ", "");
        Polynomial result = polynomialConverter.parse(expression);
        Polynomial check = new Polynomial();
        Monomial m1 = new Monomial(-1.0,5);
        Monomial m2 = new Monomial(1.0, 3);
        Monomial m3 = new Monomial(5.0, 2);
        Monomial m4 = new Monomial(10.0,1);
        Monomial m5 = new Monomial(-5.0,0);
        check.addMonomial(m1);
        check.addMonomial(m2);
        check.addMonomial(m3);
        check.addMonomial(m4);
        check.addMonomial(m5);

        assertEquals(check,result);
        nrTestsPassed++;
    }

    @Test
    public void test3(){
        String expression = "4.99x^10 - 99x^3 + 73";
        expression = expression.replace(" ", "");
        Polynomial result = polynomialConverter.parse(expression);
        Polynomial check = new Polynomial();
        Monomial m1 = new Monomial(4.99,10);
        Monomial m2 = new Monomial(-99.0, 3);
        Monomial m3 = new Monomial(73.0,0);
        check.addMonomial(m1);
        check.addMonomial(m2);
        check.addMonomial(m3);

        assertEquals(check, result);
        nrTestsPassed++;
    }
    @Test
    public void test4(){
        String expression = "x^4 + 3x^3 - x^2 + 3x + 5";
        expression = expression.replace(" ", "");
        Polynomial result = polynomialConverter.parse(expression);
        Polynomial check = new Polynomial();
        Monomial m1 = new Monomial(1.0,4);
        Monomial m2 = new Monomial(3.0, 3);
        Monomial m3 = new Monomial(-1.0, 2);
        Monomial m4 = new Monomial(3.0,1);
        Monomial m5 = new Monomial(5.0,0);
        check.addMonomial(m1);
        check.addMonomial(m2);
        check.addMonomial(m3);
        check.addMonomial(m4);
        check.addMonomial(m5);

        assertEquals(check, result);
        nrTestsPassed++;
    }

    @Test
    public void test5(){
        String expression = "- x^101 + 3x^3";
        expression = expression.replace(" ", "");
        Polynomial result = polynomialConverter.parse(expression);
        Polynomial check = new Polynomial();
        Monomial m1 = new Monomial(-1.0,101);
        Monomial m2 = new Monomial(3.0, 3);
        check.addMonomial(m1);
        check.addMonomial(m2);

        assertEquals(check,result);
        nrTestsPassed++;
    }
}
