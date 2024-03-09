package Testing;

import model.Polynomial;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class OperationTesting {
    private static Integer nrTests;
    private static Integer nrTestsPassed;
    @BeforeClass
    public static void initialize(){
        System.out.println("Starting tests");
    }
    @AfterClass
    public static void finish(){
        System.out.println(nrTests+" have been executed and "+nrTestsPassed+" have passed.");
    }
    @Test
    public void addition1(){

    }
}
