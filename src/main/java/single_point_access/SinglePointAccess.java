package single_point_access;

import operations.Operations;
import operations.OperationsImplementation;

public class SinglePointAccess {
    private static Operations operations = new OperationsImplementation();

    public static Operations getOperations() {
        return operations;
    }
}
