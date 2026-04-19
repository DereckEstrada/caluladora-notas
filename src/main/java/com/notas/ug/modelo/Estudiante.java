package com.notas.ug.modelo;

public class Estudiante {

    private static final double PESO_PARCIAL = 0.50;
    private static final double NOTA_MINIMA_APROBACION = 7.00;
    private static final double NOTA_MAXIMA = 10.00;
    private static final double NOTA_MINIMA = 0.00;

    private double notaParcial1;
    private double notaParcial2;

    public Estudiante(double notaParcial1, double notaParcial2) {
        this.notaParcial1 = notaParcial1;
        this.notaParcial2 = notaParcial2;
    }

    public double calcularPromedio() {
        return (notaParcial1 * PESO_PARCIAL) + (notaParcial2 * PESO_PARCIAL);
    }

    public boolean estaAprobado() {
        return calcularPromedio() >= NOTA_MINIMA_APROBACION;
    }

    public double calcularNotaRecuperacion() {
        double sumaOtrasNotas = calcularSumaExcluyendoMasBaja();

        double notaNecesaria = (NOTA_MINIMA_APROBACION - sumaOtrasNotas) / PESO_PARCIAL;

        if (notaNecesaria > NOTA_MAXIMA) {
            return -1;
        }
        if (notaNecesaria < NOTA_MINIMA) {
            return NOTA_MINIMA;
        }
        return notaNecesaria;
    }

    public double obtenerNotaMasBaja() {
        if (notaParcial1 <= notaParcial2) {
            return notaParcial1;
        }
        return notaParcial2;
    }

    public int obtenerNumeroParcialMasBajo() {
        if (notaParcial1 <= notaParcial2) {
            return 1;
        }
        return 2;
    }

    private double calcularSumaExcluyendoMasBaja() {
        if (notaParcial1 <= notaParcial2) {
            return notaParcial2 * PESO_PARCIAL;
        }
        return notaParcial1 * PESO_PARCIAL;
    }

    public double getNotaParcial1() {
        return notaParcial1;
    }

    public void setNotaParcial1(double notaParcial1) {
        this.notaParcial1 = notaParcial1;
    }

    public double getNotaParcial2() {
        return notaParcial2;
    }

    public void setNotaParcial2(double notaParcial2) {
        this.notaParcial2 = notaParcial2;
    }

    public static double getNotaMinimaAprobacion() {
        return NOTA_MINIMA_APROBACION;
    }
}