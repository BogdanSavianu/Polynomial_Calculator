package GUI;

import logic.Operations;
import model.Polynomial;
import single_point_access.SinglePointAccess;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        if(command == "COMPUTE"){
            Polynomial pol1 = parsePolynomial(view.getFirstNumberTextField().getText());
            Polynomial pol2 = parsePolynomial(view.getSecondNumberTextField().getText());
            String operation = String.valueOf(view.getOperationsComboBox().getSelectedItem());
            Polynomial result = new Polynomial();
            switch(operation){
                case "Add": result = operations.add(pol1, pol2);
                    break;
                case "Subtract": result = operations.subtract(pol1, pol2);
                    break;
                case "Multiply": result = operations.multiply(pol1, pol2);
                    break;
            }
            view.getResultValueLabel().setText(printPolynomial(result));
        }
    }

}

