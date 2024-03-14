package org.example;

import gui.View;
import logic.PolynomialConverter;
import logic.Operations;
import single_point_access.SinglePointAccess;

import javax.swing.*;


public class Main {
    public static void main(String[] args) {
        Operations operations = SinglePointAccess.getOperations();
        PolynomialConverter polynomialConverter = SinglePointAccess.getPolynomialConverter();
        JFrame frame = new View("Polynomial calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // frame.pack();
        frame.setVisible(true);

    }
}