package gui;

import logic.DivisionByZero;
import logic.Operations;
import model.Polynomial;
import single_point_access.SinglePointAccess;

import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import static logic.PolynomialConverter.parsePolynomial;
import static logic.PolynomialConverter.printPolynomial;

public class Controller implements ActionListener {

    private View view;

    private Operations operations = SinglePointAccess.getOperations();

    public Controller(View v){
        this.view = v;
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if(command.equals("COMPUTE")){ // Use equals() for string comparison
            Polynomial pol1;
            Polynomial pol2;
            try {
                pol1 = parsePolynomial(view.getFirstNumberTextField().getText());
                pol2 = parsePolynomial(view.getSecondNumberTextField().getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(view, "Invalid polynomial format. Please enter valid polynomials.");
                return;
            }

            String operation = String.valueOf(view.getOperationsComboBox().getSelectedItem());
            Polynomial result = new Polynomial();
            List<Polynomial> resultDiv = new ArrayList<>();
            try {
                switch(operation) {
                    case "Add":
                        result = operations.add(pol1, pol2);
                        break;
                    case "Subtract":
                        result = operations.subtract(pol1, pol2);
                        break;
                    case "Multiply":
                        result = operations.multiply(pol1, pol2);
                        break;
                    case "Divide":
                        resultDiv = operations.divide(pol1, pol2);
                        break;
                    case "Differentiate":
                        result = operations.differentiate(pol1);
                        break;
                    case "Integrate":
                        result = operations.integrate(pol1);
                        break;
                }
                if (operation.equals("Divide"))
                    view.getResultValueLabel().setText(printPolynomial(resultDiv.get(0)) + ", remainder:" + printPolynomial(resultDiv.get(1)));
                else if (operation.equals("Integrate"))
                    view.getResultValueLabel().setText(printPolynomial(result) + " +C");
                else
                    view.getResultValueLabel().setText(printPolynomial(result));
                // Clear error message if no exception occurred
                view.setErrorMessage("");
            } catch (DivisionByZero ex) {
                // Display error message
                view.setErrorMessage("Cannot divide by 0");
            }
        }
    }
}
