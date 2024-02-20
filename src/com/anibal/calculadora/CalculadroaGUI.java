package com.anibal.calculadora;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

class CalculadoraGUI extends JFrame {

    private JTextField pantalla; // Para mostrar resultados y entradas
    private double resultado; // Almacenar el resultado de las operaciones
    private String operacion; // Almacenar la operación actual
    private boolean nuevaOperacion; // Para saber si comenzar una nueva operación

    public CalculadoraGUI() {
        setTitle("Calculadora");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initComponentes();
        resultado = 0;
        operacion = "="; // Inicializa con "=" para manejar la primera entrada como un número
        nuevaOperacion = true;
    }

    private void initComponentes() {
        setLayout(new GridLayout(0, 4));

        pantalla = new JTextField();
        pantalla.setEditable(false); // El usuario no puede escribir directamente en la pantalla
        add(pantalla);

        ActionListener insertar = e -> {
            String entrada = e.getActionCommand();
            if (nuevaOperacion) {
                pantalla.setText("");
                nuevaOperacion = false;
            }
            pantalla.setText(pantalla.getText() + entrada);
        };

        ActionListener comando = e -> {
            String op = e.getActionCommand();
            if (op.equals("=")) {
                calcular(Double.parseDouble(pantalla.getText()));
            } else {
                if (!nuevaOperacion) {
                    calcular(Double.parseDouble(pantalla.getText()));
                    operacion = op;
                }
            }
            nuevaOperacion = true;
        };

        String[] botones = {"7", "8", "9", "/", "4", "5", "6", "*", "1", "2", "3", "-", "0", ".", "=", "+"};
        for (String textoBoton : botones) {
            JButton boton = new JButton(textoBoton);
            if (textoBoton.equals("+") || textoBoton.equals("-") || textoBoton.equals("*") || textoBoton.equals("/") || textoBoton.equals("=")) {
                boton.addActionListener(comando);
            } else {
                boton.addActionListener(insertar);
            }
            add(boton);
        }
    }

    private void calcular(double n) {
        switch (operacion) {
            case "+":
                resultado += n;
                break;
            case "-":
                resultado -= n;
                break;
            case "*":
                resultado *= n;
                break;
            case "/":
                resultado /= n;
                break;
            case "=":
                resultado = n;
                break;
        }
        pantalla.setText("" + resultado);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CalculadoraGUI ventana = new CalculadoraGUI();
            ventana.setVisible(true);
        });
    }
}
