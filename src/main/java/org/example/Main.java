package org.example;

import GUI.View;
import logic.PolynomialConverter;
import model.Monomial;
import model.Polynomial;
import logic.Operations;
import single_point_access.SinglePointAccess;

import javax.swing.*;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Main {
    public static void main(String[] args) {
        Operations operations = SinglePointAccess.getOperations();
        PolynomialConverter polynomialConverter = SinglePointAccess.getPolynomialConverter();
        JFrame frame = new View("Simple calculator using MVC");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // frame.pack();
        frame.setVisible(true);

    }
}