import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class InterfazPrestamo extends JFrame {
    private JTextField tfMonto, tfAnticipo, tfTasa, tfPlazo;
    private JTextArea resultado;
    private JTable tablaAmortizacion;

    public InterfazPrestamo() {
        setTitle("Simulador de Préstamos MXN - Análisis Financiero");
        setSize(700, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panelInputs = new JPanel(new GridLayout(5, 2));

        tfMonto = new JTextField();
        tfAnticipo = new JTextField();
        tfTasa = new JTextField();
        tfPlazo = new JTextField();
        resultado = new JTextArea(8, 50);
        resultado.setEditable(false);

        panelInputs.add(new JLabel("Monto del préstamo (MXN):"));
        panelInputs.add(tfMonto);

        panelInputs.add(new JLabel("Anticipo (MXN):"));
        panelInputs.add(tfAnticipo);

        panelInputs.add(new JLabel("Tasa de interés anual (%):"));
        panelInputs.add(tfTasa);

        panelInputs.add(new JLabel("Plazo en años (1-30):"));
        panelInputs.add(tfPlazo);

        JButton calcular = new JButton("Calcular");
        panelInputs.add(calcular);

        add(panelInputs, BorderLayout.NORTH);

        calcular.addActionListener(e -> calcularPrestamo());

        JPanel panelResultados = new JPanel(new BorderLayout());
        panelResultados.add(new JScrollPane(resultado), BorderLayout.NORTH);

        tablaAmortizacion = new JTable();
        JScrollPane scrollTabla = new JScrollPane(tablaAmortizacion);
        panelResultados.add(scrollTabla, BorderLayout.CENTER);

        add(panelResultados, BorderLayout.CENTER);

        setVisible(true);
    }

    private void calcularPrestamo() {
        try {
            double monto = Double.parseDouble(tfMonto.getText());
            double anticipo = Double.parseDouble(tfAnticipo.getText());
            double tasa = Double.parseDouble(tfTasa.getText());
            int plazo = Integer.parseInt(tfPlazo.getText());

            Prestamo prestamo = new Prestamo(monto, anticipo, tasa, plazo);

            double mensualidad = prestamo.calcularCuotaMensual();
            double total = prestamo.calcularTotalAPagar();
            double intereses = prestamo.calcularInteresesTotales();
            double montoFinanciado = prestamo.getMontoFinanciado();

            double tasaMensual = tasa / 100 / 12;
            int n = plazo * 12;
            double cuotaJusta = prestamo.calcularCuotaPagoJusto(montoFinanciado, tasaMensual, n);

            resultado.setText("Resumen del préstamo:\n");
            resultado.append("Monto financiado: $" + String.format("%.2f", montoFinanciado) + "\n");
            resultado.append("Cuota mensual estándar: $" + String.format("%.2f", mensualidad) + "\n");
            resultado.append("Cuota mensual (pago justo): $" + String.format("%.2f", cuotaJusta) + "\n");
            resultado.append("Total a pagar: $" + String.format("%.2f", total) + "\n");
            resultado.append("Intereses totales: $" + String.format("%.2f", intereses) + "\n");

            generarTablaAmortizacion(prestamo, cuotaJusta);

        } catch (NumberFormatException ex) {
            resultado.setText("Error: Verifica que los datos ingresados sean válidos numéricamente.");
        }
    }

    private void generarTablaAmortizacion(Prestamo prestamo, double cuota) {
        String[] columnas = {"Mes", "Saldo Inicial", "Pago", "Interés", "Amortización", "Saldo Final"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);

        double saldo = prestamo.getMontoFinanciado();
        double tasaMensual = prestamo.getTasaMensual();

        for (int mes = 1; mes <= prestamo.getPlazo() * 12; mes++) {
            double interes = saldo * tasaMensual;
            double amortizacion = cuota - interes;
            double nuevoSaldo = saldo - amortizacion;
            modelo.addRow(new Object[] {
                mes,
                String.format("%.2f", saldo),
                String.format("%.2f", cuota),
                String.format("%.2f", interes),
                String.format("%.2f", amortizacion),
                String.format("%.2f", Math.max(nuevoSaldo, 0))
            });
            saldo = nuevoSaldo;
        }
        tablaAmortizacion.setModel(modelo);
    }

    public static void main(String[] args) {
        new InterfazPrestamo();
    }
}
