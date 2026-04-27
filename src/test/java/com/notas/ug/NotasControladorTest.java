package com.notas.ug;

import com.notas.ug.controlador.NotasControlador;
import com.notas.ug.modelo.ResultadoNota;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class NotasControladorTest {

    @Test
    void testProcesarNotas_assertEquals_aprobado() {
        NotasControlador ctrl = new NotasControlador();
        ResultadoNota res = ctrl.procesarNotas("8", "9");
        assertEquals(8.5, res.getPromedio(), 0.001, "Promedio 8 y 9 debe ser 8.5");
        assertTrue(res.isAprobado(), "Promedio 8.5 debe aprobar");
        assertEquals(-1.0, res.getNotaRecuperacion(), 0.001, "Aprobado: recuperacion=-1");
    }

    @Test
    void testProcesarNotas_assertThrows_fueraDeRango() {
        NotasControlador ctrl = new NotasControlador();
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> ctrl.procesarNotas("11", "8"));
        assertTrue(ex.getMessage().contains("Parcial 1"));
        assertTrue(ex.getMessage().contains("entre 0 y 10"));
    }

    @Test
    void testProcesarNotas_assertEquals_fallo() {
        NotasControlador ctrl = new NotasControlador();
        ResultadoNota res = ctrl.procesarNotas("6", "5");
        assertEquals(7.0, res.getPromedio(), 0.001, "[FALLO INTENCIONAL] esperado 7.0");
    }


    @Disabled
    @Test
    void testEstadoAprobacion_assertTrue_assertFalse() {
        NotasControlador ctrl = new NotasControlador();
        assertTrue(ctrl.procesarNotas("8","8").isAprobado());
        assertFalse(ctrl.procesarNotas("5","5").isAprobado());
    }


    @Disabled
    @Test
    void testResultado_assertNull_assertNotNull() {
        NotasControlador ctrl = new NotasControlador();
        ResultadoNota res = ctrl.procesarNotas("9", "8");
        assertNotNull(res, "Resultado nunca debe ser null");
        ResultadoNota nulo = null;
        assertNull(nulo, "Referencia nula debe ser null");
    }


    @Disabled
    @Test
    void testPromedio_assertNotEquals() {
        NotasControlador ctrl = new NotasControlador();
        assertNotEquals(0.0, ctrl.procesarNotas("7","8").getPromedio(), 0.001);
    }


    @Disabled
    @Test
    void testInstancias_assertSame_assertNotSame() {
        NotasControlador c1 = new NotasControlador();
        NotasControlador c2 = c1;
        NotasControlador c3 = new NotasControlador();
        assertSame(c1, c2);
        assertNotSame(c1, c3);
    }


    @Disabled
    @Test
    void testParciales_assertArrayEquals() {
        NotasControlador ctrl = new NotasControlador();
        ResultadoNota res = ctrl.procesarNotas("6", "8");
        assertArrayEquals(new double[]{6.0,8.0},
                new double[]{res.getNotaParcial1(),res.getNotaParcial2()}, 0.001);
    }


    @Disabled
    @Test
    void testProcesarNotas_assertThrows_campoVacio() {
        NotasControlador ctrl = new NotasControlador();
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> ctrl.procesarNotas("", "7"));
        assertTrue(ex.getMessage().contains("no puede estar vacío"));
    }


    @Disabled
    @Test
    void testProcesarNotas_assertThrows_noNumerico() {
        NotasControlador ctrl = new NotasControlador();
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> ctrl.procesarNotas("abc", "7"));
        assertTrue(ex.getMessage().contains("número válido"));
    }


    @Disabled
    @Test
    void testRecuperacion_assertTrue_esCalculable() {
        NotasControlador ctrl = new NotasControlador();
        ResultadoNota res = ctrl.procesarNotas("5", "5");
        assertFalse(res.isAprobado());
        assertTrue(res.getNotaRecuperacion() >= 0);
        assertTrue(res.getNotaRecuperacion() <= 10);
    }
}
