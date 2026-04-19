package com.notas.ug.controlador;

import com.notas.ug.modelo.Estudiante;
import com.notas.ug.modelo.ResultadoNota;

public class NotasControlador {

    private static final double NOTA_MINIMA = 0.00;
    private static final double NOTA_MAXIMA = 10.00;

    public ResultadoNota procesarNotas(String textoParcial1, String textoParcial2) {

        double parcial1 = parsearNota(textoParcial1, "Parcial 1");
        double parcial2 = parsearNota(textoParcial2, "Parcial 2");

        validarRango(parcial1, "Parcial 1");
        validarRango(parcial2, "Parcial 2");

        Estudiante estudiante = new Estudiante(parcial1, parcial2);

        double promedio     = estudiante.calcularPromedio();
        boolean aprobado    = estudiante.estaAprobado();
        double recuperacion = aprobado ? -1 : estudiante.calcularNotaRecuperacion();
        int parcialMasBajo  = estudiante.obtenerNumeroParcialMasBajo();

        return new ResultadoNota(parcial1, parcial2,
                                 promedio, aprobado, recuperacion, parcialMasBajo);
    }

    private double parsearNota(String texto, String nombreCampo) {
        if (texto == null || texto.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "El campo \"" + nombreCampo + "\" no puede estar vacío.");
        }
        try {
            return Double.parseDouble(texto.trim().replace(",", "."));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(
                "El campo \"" + nombreCampo + "\" debe ser un número válido (ej: 8.5).");
        }
    }

    private void validarRango(double nota, String nombreCampo) {
        if (nota < NOTA_MINIMA || nota > NOTA_MAXIMA) {
            throw new IllegalArgumentException(
                "El campo \"" + nombreCampo + "\" debe estar entre 0 y 10. "
                + "Valor ingresado: " + nota);
        }
    }
}