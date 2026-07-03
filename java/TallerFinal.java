import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class TallerFinal extends JFrame {

    // --- Colores del Tema Oscuro ---
    private final Color colorFondo = new Color(46, 46, 46);      // Gris oscuro
    private final Color colorPanel = new Color(60, 63, 65);      // Gris más claro para paneles
    private final Color colorTexto = new Color(230, 230, 230);   // Blanco hueso
    private final Color colorBoton = new Color(0, 122, 204);     // Azul VS Code
    private final Color colorInput = new Color(80, 80, 80);      // Fondo inputs

    // --- Vectores Globales (Persistencia Ejercicio 4) ---
    private ArrayList<String> vectorNombres = new ArrayList<>();
    private ArrayList<Integer> vectorEdades = new ArrayList<>();

    public TallerFinal() {
        setTitle("Taller Estructura de Datos - Java");
        setSize(900, 750);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(colorFondo);

        // Configuración de Pestañas
        JTabbedPane tabs = new JTabbedPane();
        tabs.setBackground(colorFondo);
        tabs.setForeground(Color.BLACK); // Color texto pestañas

        // Agregamos los 6 ejercicios completos
        tabs.addTab("Ejer 1", panelEjer1());
        tabs.addTab("Ejer 2", panelEjer2());
        tabs.addTab("Ejer 3", panelEjer3());
        tabs.addTab("Ejer 4", panelEjer4()); // Vectores Paralelos
        tabs.addTab("Ejer 5", panelEjer5()); // Matrices A, B y C
        tabs.addTab("Ejer 6", panelEjer6()); // Matriz Real

        add(tabs);
    }

    // --- Métodos Auxiliares para Estilo (Look & Feel) ---

    private JButton crearBoton(String texto) {
        JButton btn = new JButton(texto);
        btn.setBackground(colorBoton);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }

    private JTextField crearInput() {
        JTextField txt = new JTextField(20);
        txt.setBackground(colorInput);
        txt.setForeground(Color.WHITE);
        txt.setCaretColor(Color.WHITE);
        txt.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        return txt;
    }

    private JLabel crearLabel(String texto) {
        JLabel lbl = new JLabel(texto);
        lbl.setForeground(colorTexto);
        lbl.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        return lbl;
    }

    private JTextArea crearAreaTexto() {
        JTextArea area = new JTextArea();
        area.setBackground(new Color(30, 30, 30)); // Fondo negro consola
        area.setForeground(new Color(0, 255, 128)); // Texto verde hacker
        area.setFont(new Font("Consolas", Font.PLAIN, 12)); // Fuente Monoespaciada vital para tablas
        area.setEditable(false);
        return area;
    }

    private JPanel crearPanelBase() {
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.setBackground(colorPanel);
        p.setBorder(new EmptyBorder(20, 20, 20, 20));
        return p;
    }

    // --- EJERCICIO 1: Promedio Simple ---
    private JPanel panelEjer1() {
        JPanel p = crearPanelBase();

        JPanel pIn = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pIn.setBackground(colorPanel);

        pIn.add(crearLabel("Notas (separadas por coma): "));
        JTextField txt = crearInput();
        pIn.add(txt);

        JButton btn = crearBoton("CALCULAR");
        JLabel lblRes = crearLabel("Resultado: -");
        lblRes.setFont(new Font("Segoe UI", Font.BOLD, 16));

        btn.addActionListener(e -> {
            try {
                String[] parts = txt.getText().split(",");
                double suma = 0;
                for(String s : parts) suma += Double.parseDouble(s.trim());
                lblRes.setText(String.format("Promedio: %.2f", suma/parts.length));
                lblRes.setForeground(Color.GREEN);
            } catch(Exception ex) { JOptionPane.showMessageDialog(this, "Revise los datos"); }
        });

        p.add(pIn); p.add(Box.createVerticalStrut(10));
        p.add(btn); p.add(Box.createVerticalStrut(20));
        p.add(lblRes);
        return p;
    }

    // --- EJERCICIO 2: Promedio con Cantidad ---
    private JPanel panelEjer2() {
        JPanel p = crearPanelBase();

        JPanel row1 = new JPanel(new FlowLayout(FlowLayout.LEFT)); row1.setBackground(colorPanel);
        row1.add(crearLabel("Cantidad (N): ")); JTextField txtN = crearInput(); row1.add(txtN);

        JPanel row2 = new JPanel(new FlowLayout(FlowLayout.LEFT)); row2.setBackground(colorPanel);
        row2.add(crearLabel("Notas:         ")); JTextField txtNt = crearInput(); row2.add(txtNt);

        JButton btn = crearBoton("CALCULAR");
        JLabel lblRes = crearLabel("-");

        btn.addActionListener(e -> {
            try {
                int n = Integer.parseInt(txtN.getText());
                String[] parts = txtNt.getText().split(",");
                if(parts.length != n) JOptionPane.showMessageDialog(this, "Faltan notas. N es " + n);
                else {
                    double suma = 0;
                    for(String s : parts) suma += Double.parseDouble(s.trim());
                    lblRes.setText(String.format("Promedio: %.2f", suma/n));
                }
            } catch(Exception ex) {}
        });

        p.add(row1); p.add(row2); p.add(btn); p.add(Box.createVerticalStrut(10)); p.add(lblRes);
        return p;
    }

    // --- EJERCICIO 3: Decimales ---
    private JPanel panelEjer3() {
        JPanel p = crearPanelBase();
        JPanel row = new JPanel(new FlowLayout(FlowLayout.LEFT)); row.setBackground(colorPanel);
        row.add(crearLabel("Notas Decimales (ej: 4.5, 3.8): "));
        JTextField txt = crearInput(); row.add(txt);

        JButton btn = crearBoton("CALCULAR EXACTO");
        JLabel lblRes = crearLabel("-");

        btn.addActionListener(e -> {
            try {
                String[] parts = txt.getText().split(",");
                double suma = 0;
                for(String s : parts) suma += Double.parseDouble(s.trim());
                // Mostramos 4 decimales
                lblRes.setText(String.format("Promedio Exacto: %.4f", suma/parts.length));
                lblRes.setForeground(Color.GREEN);
            } catch(Exception ex) {}
        });

        p.add(row); p.add(btn); p.add(Box.createVerticalStrut(10)); p.add(lblRes);
        return p;
    }

    // --- EJERCICIO 4: VECTORES PARALELOS (MEMORIA) ---
    private JPanel panelEjer4() {
        JPanel p = crearPanelBase();

        JPanel row1 = new JPanel(new FlowLayout(FlowLayout.LEFT)); row1.setBackground(colorPanel);
        row1.add(crearLabel("Nombre: ")); JTextField txtNom = crearInput(); row1.add(txtNom);

        JPanel row2 = new JPanel(new FlowLayout(FlowLayout.LEFT)); row2.setBackground(colorPanel);
        row2.add(crearLabel("Edad:   ")); JTextField txtEdad = crearInput(); row2.add(txtEdad);

        JButton btn = crearBoton("AGREGAR AL VECTOR");
        JTextArea area = crearAreaTexto();

        btn.addActionListener(e -> {
            try {
                int edad = Integer.parseInt(txtEdad.getText());
                String nombre = txtNom.getText();
                if(!nombre.isEmpty()) {
                    // 1. Guardar en memoria (Vectores Paralelos)
                    vectorNombres.add(nombre);
                    vectorEdades.add(edad);

                    // 2. Recorrer y mostrar
                    StringBuilder sb = new StringBuilder("--- REGISTRO (Vectores en Memoria) ---\n");
                    for(int i=0; i<vectorNombres.size(); i++) {
                        sb.append(String.format("Indice [%d]: %s -> %d años\n",
                                i, vectorNombres.get(i), vectorEdades.get(i)));
                    }
                    area.setText(sb.toString());
                    txtNom.setText(""); txtEdad.setText("");
                }
            } catch(Exception ex) { JOptionPane.showMessageDialog(this, "Edad inválida"); }
        });

        p.add(row1); p.add(row2); p.add(btn);
        p.add(Box.createVerticalStrut(10));
        p.add(new JScrollPane(area));
        return p;
    }

    // --- EJERCICIO 5: MATRICES A, B y C ---
    private JPanel panelEjer5() {
        JPanel p = crearPanelBase();
        JButton btn = crearBoton("GENERAR Y SUMAR MATRICES");
        JTextArea area = crearAreaTexto();
        area.setFont(new Font("Monospaced", Font.PLAIN, 14)); // Más grande para ver bien

        btn.addActionListener(e -> {
            int[][] A = new int[3][3];
            int[][] B = new int[3][3];
            int[][] C = new int[3][3];
            StringBuilder sb = new StringBuilder();

            sb.append("--- MATRIZ A ---\n");
            for(int i=0; i<3; i++){
                for(int j=0; j<3; j++){
                    A[i][j] = (int)(Math.random()*10);
                    B[i][j] = (int)(Math.random()*10);
                    sb.append(String.format("%4d", A[i][j]));
                }
                sb.append("\n");
            }

            sb.append("\n--- MATRIZ B ---\n");
            for(int[] row : B) {
                for(int val : row) sb.append(String.format("%4d", val));
                sb.append("\n");
            }

            sb.append("\n--- RESULTADO (A+B) ---\n");
            for(int i=0; i<3; i++) {
                for(int j=0; j<3; j++) {
                    C[i][j] = A[i][j] + B[i][j]; // Operación Suma
                    sb.append(String.format("%4d", C[i][j]));
                }
                sb.append("\n");
            }
            area.setText(sb.toString());
        });

        p.add(btn); p.add(Box.createVerticalStrut(10)); p.add(new JScrollPane(area));
        return p;
    }

    // --- EJERCICIO 6: TABLA (MATRIZ REAL) ---
    private JPanel panelEjer6() {
        JPanel p = crearPanelBase();
        JButton btn = crearBoton("GENERAR TABLA (MATRIZ EN MEMORIA)");
        JTextArea area = crearAreaTexto();

        btn.addActionListener(e -> {
            int[] vector = {1,2,3,4,5,6,7,8,9,10};
            int[][] matrizRes = new int[10][10]; // Matriz real

            // 1. Llenado lógico
            for(int i=0; i<10; i++)
                for(int j=0; j<10; j++)
                    matrizRes[i][j] = vector[i] * vector[j];

            // 2. Visualización
            StringBuilder sb = new StringBuilder();
            sb.append("   X | 1    2    3    4    5    6    7    8    9   10\n");
            sb.append("------------------------------------------------------\n");

            for(int i=0; i<10; i++) {
                sb.append(String.format("%4d |", vector[i]));
                for(int j=0; j<10; j++) {
                    // Lectura desde la matriz
                    sb.append(String.format("%5d", matrizRes[i][j]));
                }
                sb.append("\n");
            }
            area.setText(sb.toString());
        });

        p.add(btn); p.add(Box.createVerticalStrut(10)); p.add(new JScrollPane(area));
        return p;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TallerFinal().setVisible(true));
    }
}