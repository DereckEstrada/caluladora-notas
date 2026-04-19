package com.notas.ug;

import com.notas.ug.vista.NotasVista;
import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            NotasVista vista = new NotasVista();
            vista.setVisible(true);
        });
    }
}