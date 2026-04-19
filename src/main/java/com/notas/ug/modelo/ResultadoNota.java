package com.notas.ug.modelo;

public class ResultadoNota {

    private final double notaParcial1;
    private final double notaParcial2;
    private final double promedio;
    private final boolean aprobado;
    private final double notaRecuperacion;
    private final int parcialMasBajo;

    public ResultadoNota(double notaParcial1, double notaParcial2,
                         double promedio, boolean aprobado,
                         double notaRecuperacion, int parcialMasBajo) {
        this.notaParcial1     = notaParcial1;
        this.notaParcial2     = notaParcial2;
        this.promedio         = promedio;
        this.aprobado         = aprobado;
        this.notaRecuperacion = notaRecuperacion;
        this.parcialMasBajo   = parcialMasBajo;
    }

    public double getNotaParcial1() {
        return notaParcial1;
    }

    public double getNotaParcial2() {
        return notaParcial2;
    }

    public double getPromedio() {
        return promedio;
    }

    public boolean isAprobado() {
        return aprobado;
    }

    public double getNotaRecuperacion() {
        return notaRecuperacion;
    }

    public int getParcialMasBajo() {
        return parcialMasBajo;
    }
}