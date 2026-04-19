package com.notas.ug.vista;

import com.notas.ug.controlador.NotasControlador;
import com.notas.ug.modelo.ResultadoNota;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

public class NotasVista extends JFrame {

    private static final String TITULO_VENTANA    = "Calculadora de Notas — Universidad de Guayaquil";
    private static final String TITULO_ENTRADAS   = "Ingreso de Notas";
    private static final String TITULO_RESULTADOS = "Resultados";
    private static final String COLOR_APROBADO    = "#1a7a1a";
    private static final String COLOR_REPROBADO   = "#b30000";
    private static final int    ANCHO_VENTANA     = 520;
    private static final int    ALTO_VENTANA      = 420;

    private final JTextField campoParcial1;
    private final JTextField campoParcial2;

    private final JLabel etiquetaPromedio;
    private final JLabel etiquetaEstado;
    private final JLabel etiquetaRecuperacion;
    private final JLabel etiquetaDetalle;

    private final NotasControlador controlador;

    public NotasVista() {
        controlador          = new NotasControlador();
        campoParcial1        = new JTextField(10);
        campoParcial2        = new JTextField(10);
        etiquetaPromedio     = new JLabel("—");
        etiquetaEstado       = new JLabel("—");
        etiquetaRecuperacion = new JLabel("—");
        etiquetaDetalle      = new JLabel(" ");

        configurarVentana();
        agregarComponentes();
    }

    private void configurarVentana() {
        setTitle(TITULO_VENTANA);
        setSize(ANCHO_VENTANA, ALTO_VENTANA);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout(10, 10));
        ((JPanel) getContentPane()).setBorder(
            BorderFactory.createEmptyBorder(10, 15, 10, 15));
    }

    private void agregarComponentes() {
        add(crearPanelEncabezado(), BorderLayout.NORTH);
        add(crearPanelEntradas(),   BorderLayout.CENTER);
        add(crearPanelResultados(), BorderLayout.SOUTH);
    }

    private JPanel crearPanelEncabezado() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel titulo = new JLabel("Calculadora de Notas Estudiantiles");
        titulo.setFont(new Font("SansSerif", Font.BOLD, 18));
        titulo.setForeground(new Color(0, 51, 102));
        panel.add(titulo);
        return panel;
    }

    private JPanel crearPanelEntradas() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createEtchedBorder(), TITULO_ENTRADAS,
            TitledBorder.LEFT, TitledBorder.TOP,
            new Font("SansSerif", Font.BOLD, 12)));

        GridBagConstraints gbc = crearConstraintsBase();

        agregarFilaFormulario(panel, gbc, 0, "Parcial 1  (0 – 10):", campoParcial1);
        agregarFilaFormulario(panel, gbc, 1, "Parcial 2  (0 – 10):", campoParcial2);
        agregarBotonCalcular(panel, gbc, 2);

        return panel;
    }

    private JPanel crearPanelResultados() {
        JPanel panel = new JPanel(new GridLayout(5, 1, 4, 4));
        panel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createEtchedBorder(), TITULO_RESULTADOS,
            TitledBorder.LEFT, TitledBorder.TOP,
            new Font("SansSerif", Font.BOLD, 12)));

        Font fuenteResultado = new Font("SansSerif", Font.PLAIN, 13);

        etiquetaPromedio.setFont(fuenteResultado);
        etiquetaEstado.setFont(new Font("SansSerif", Font.BOLD, 13));
        etiquetaRecuperacion.setFont(fuenteResultado);
        etiquetaDetalle.setFont(new Font("SansSerif", Font.ITALIC, 11));
        etiquetaDetalle.setForeground(Color.GRAY);

        panel.add(crearFilaResultado("Promedio ponderado:", etiquetaPromedio));
        panel.add(crearFilaResultado("Estado:", etiquetaEstado));
        panel.add(crearFilaResultado("Nota de recuperación:", etiquetaRecuperacion));
        panel.add(etiquetaDetalle);

        return panel;
    }

    private GridBagConstraints crearConstraintsBase() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 10, 8, 10);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill   = GridBagConstraints.HORIZONTAL;
        return gbc;
    }

    private void agregarFilaFormulario(JPanel panel, GridBagConstraints gbc,
                                       int fila, String label, JTextField campo) {
        gbc.gridx = 0;
        gbc.gridy = fila;
        gbc.weightx = 0.4;
        panel.add(new JLabel(label), gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.6;
        panel.add(campo, gbc);
    }

    private void agregarBotonCalcular(JPanel panel, GridBagConstraints gbc, int fila) {
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));

        JButton botonCalcular = new JButton("Calcular");
        botonCalcular.setFont(new Font("SansSerif", Font.BOLD, 13));
        botonCalcular.setBackground(new Color(0, 102, 204));
        botonCalcular.setForeground(Color.WHITE);
        botonCalcular.setFocusPainted(false);
        botonCalcular.addActionListener((ActionEvent e) -> ejecutarCalculo());

        JButton botonLimpiar = new JButton("Limpiar");
        botonLimpiar.setFont(new Font("SansSerif", Font.PLAIN, 13));
        botonLimpiar.addActionListener((ActionEvent e) -> limpiarFormulario());

        panelBotones.add(botonCalcular);
        panelBotones.add(botonLimpiar);

        gbc.gridx     = 0;
        gbc.gridy     = fila;
        gbc.gridwidth = 2;
        gbc.anchor    = GridBagConstraints.CENTER;
        panel.add(panelBotones, gbc);
        gbc.gridwidth = 1;
    }

    private JPanel crearFilaResultado(String labelTexto, JLabel valor) {
        JPanel fila = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 2));
        JLabel label = new JLabel(labelTexto);
        label.setFont(new Font("SansSerif", Font.BOLD, 13));
        label.setPreferredSize(new Dimension(200, 20));
        fila.add(label);
        fila.add(valor);
        return fila;
    }

    private void ejecutarCalculo() {
        try {
            ResultadoNota resultado = controlador.procesarNotas(
                campoParcial1.getText(),
                campoParcial2.getText()
            );
            mostrarResultados(resultado);
        } catch (IllegalArgumentException ex) {
            mostrarError(ex.getMessage());
        }
    }

    private void mostrarResultados(ResultadoNota resultado) {
        etiquetaPromedio.setText(String.format("%.2f / 10.00", resultado.getPromedio()));

        if (resultado.isAprobado()) {
            etiquetaEstado.setText("APROBADO");
            etiquetaEstado.setForeground(Color.decode(COLOR_APROBADO));
            etiquetaRecuperacion.setText("No aplica");
            etiquetaRecuperacion.setForeground(Color.GRAY);
            etiquetaDetalle.setText(" ");
        } else {
            etiquetaEstado.setText("DEBE RECUPERACIÓN");
            etiquetaEstado.setForeground(Color.decode(COLOR_REPROBADO));
            mostrarInfoRecuperacion(resultado);
        }
    }

    private void mostrarInfoRecuperacion(ResultadoNota resultado) {
        double notaRec = resultado.getNotaRecuperacion();

        if (notaRec < 0) {
            etiquetaRecuperacion.setText("Imposible aprobar (requiere > 10)");
            etiquetaRecuperacion.setForeground(Color.decode(COLOR_REPROBADO));
            etiquetaDetalle.setText("Ninguna nota en recuperación permite alcanzar 7.00");
        } else {
            etiquetaRecuperacion.setText(
                String.format("%.2f en Parcial %d", notaRec, resultado.getParcialMasBajo()));
            etiquetaRecuperacion.setForeground(new Color(180, 90, 0));
            etiquetaDetalle.setText(
                String.format("Parcial %d es el más bajo — la recuperación lo reemplaza.",
                              resultado.getParcialMasBajo()));
        }
    }

    private void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje,
            "Dato inválido", JOptionPane.WARNING_MESSAGE);
    }

    private void limpiarFormulario() {
        campoParcial1.setText("");
        campoParcial2.setText("");
        etiquetaPromedio.setText("—");
        etiquetaEstado.setText("—");
        etiquetaEstado.setForeground(Color.BLACK);
        etiquetaRecuperacion.setText("—");
        etiquetaRecuperacion.setForeground(Color.BLACK);
        etiquetaDetalle.setText(" ");
        campoParcial1.requestFocus();
    }
}