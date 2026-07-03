using System;
using System.Drawing;
using System.Windows.Forms;
using System.Collections.Generic;
using System.Linq;

public class TallerForm : Form {
    // Definimos colores del tema oscuro
    Color colorFondo = Color.FromArgb(30, 30, 30);
    Color colorTexto = Color.White;
    Color colorBoton = Color.FromArgb(0, 120, 215);
    Color colorInput = Color.FromArgb(50, 50, 50);

    // Vectores GLOBALES (Persistencia Ejercicio 4)
    List<string> vectorNombres = new List<string>();
    List<int> vectorEdades = new List<int>();

    public TallerForm() {
        this.Text = "Taller Estructura de Datos";
        this.Size = new Size(900, 750);
        this.BackColor = colorFondo;
        this.ForeColor = colorTexto;
        this.StartPosition = FormStartPosition.CenterScreen;

        TabControl tabs = new TabControl();
        tabs.Dock = DockStyle.Fill;
        
        // Agregar las pestañas (Ahora TODAS están completas)
        tabs.TabPages.Add(TabEjer1());
        tabs.TabPages.Add(TabEjer2());
        tabs.TabPages.Add(TabEjer3()); // ¡Ahora sí está completo!
        tabs.TabPages.Add(TabEjer4()); 
        tabs.TabPages.Add(TabEjer5()); 
        tabs.TabPages.Add(TabEjer6()); 
        
        this.Controls.Add(tabs);
    }

    // Funciones auxiliares para estilizar controles
    private Button CrearBoton(string txt, int top, int left) {
        return new Button() { 
            Text = txt, Top = top, Left = left, Width = 150, Height = 40,
            BackColor = colorBoton, ForeColor = Color.White, FlatStyle = FlatStyle.Flat,
            Font = new Font("Segoe UI", 9, FontStyle.Bold), Cursor = Cursors.Hand
        };
    }
    
    private TextBox CrearInput(int top, int left) {
        return new TextBox() { 
            Top = top, Left = left, Width = 200, 
            BackColor = colorInput, ForeColor = Color.White, 
            BorderStyle = BorderStyle.FixedSingle, Font = new Font("Segoe UI", 10) 
        };
    }

    private Label CrearLabel(string txt, int top, int left) {
        return new Label() { Text = txt, Top = top, Left = left, AutoSize = true, ForeColor = colorTexto, Font = new Font("Segoe UI", 10) };
    }

    // --- EJERCICIO 1: Promedio Simple ---
    private TabPage TabEjer1() {
        TabPage p = new TabPage("Ejer 1"); p.BackColor = colorFondo;
        p.Controls.Add(CrearLabel("Notas (separadas por coma):", 30, 30));
        TextBox t = CrearInput(60, 30);
        Button b = CrearBoton("CALCULAR", 100, 30);
        Label res = CrearLabel("Resultado: -", 160, 30); res.Font = new Font("Segoe UI", 12);
        
        b.Click += (s,e) => {
            try { 
                var notas = t.Text.Split(',').Select(double.Parse).ToArray();
                res.Text = $"Promedio: {notas.Average():F2}"; res.ForeColor = Color.Lime;
            } catch { MessageBox.Show("Error en datos"); }
        };
        p.Controls.Add(t); p.Controls.Add(b); p.Controls.Add(res);
        return p;
    }

    // --- EJERCICIO 2: Promedio con Cantidad ---
    private TabPage TabEjer2() { 
        TabPage p = new TabPage("Ejer 2"); p.BackColor = colorFondo;
        p.Controls.Add(CrearLabel("Cantidad (N):", 30, 30)); TextBox tN = CrearInput(30, 150);
        p.Controls.Add(CrearLabel("Notas:", 70, 30)); TextBox tNt = CrearInput(70, 150);
        Button b = CrearBoton("CALCULAR", 120, 30);
        Label res = CrearLabel("-", 180, 30);
        b.Click += (s,e) => {
            try {
                int n = int.Parse(tN.Text);
                var arr = tNt.Text.Split(',').Select(double.Parse).ToArray();
                if(arr.Length != n) MessageBox.Show($"Faltan notas. N es {n}");
                else { res.Text = $"Promedio: {arr.Average():F2}"; res.ForeColor = Color.Lime; }
            } catch { }
        };
        p.Controls.Add(tN); p.Controls.Add(tNt); p.Controls.Add(b); p.Controls.Add(res);
        return p;
    }

    // --- EJERCICIO 3: Decimales (AHORA SÍ IMPLEMENTADO) ---
    private TabPage TabEjer3() {
        TabPage p = new TabPage("Ejer 3"); p.BackColor = colorFondo;
        
        p.Controls.Add(CrearLabel("Notas decimales (ej: 3.5, 4.25):", 30, 30));
        TextBox t = CrearInput(60, 30);
        Button b = CrearBoton("CALCULAR", 100, 30);
        Label res = CrearLabel("Resultado: -", 160, 30); res.Font = new Font("Segoe UI", 12);
        
        b.Click += (s,e) => {
            try {
                // Parseamos a double para manejar decimales con precisión
                var notas = t.Text.Split(',').Select(double.Parse).ToArray();
                // Mostramos con 4 decimales (F4) para demostrar precisión
                res.Text = $"Promedio Exacto: {notas.Average():F4}"; 
                res.ForeColor = Color.Lime;
            } catch { 
                MessageBox.Show("Error: Use comas para separar las notas."); 
            }
        };
        
        p.Controls.Add(t); p.Controls.Add(b); p.Controls.Add(res);
        return p;
    }

    // --- EJERCICIO 4: VECTORES PARALELOS ---
    private TabPage TabEjer4() {
        TabPage p = new TabPage("Ejer 4 (Vectores)"); p.BackColor = colorFondo;
        p.Controls.Add(CrearLabel("Nombre:", 30, 30)); TextBox tNom = CrearInput(30, 100);
        p.Controls.Add(CrearLabel("Edad:", 70, 30)); TextBox tEdad = CrearInput(70, 100);
        Button b = CrearBoton("AGREGAR", 120, 30);
        
        RichTextBox rtb = new RichTextBox() { Top=180, Left=30, Width=400, Height=300, BackColor=Color.FromArgb(40,40,40), ForeColor=Color.Lime, Font=new Font("Consolas", 10) };
        
        b.Click += (s,e) => {
            if(int.TryParse(tEdad.Text, out int edad)) {
                // Guardar en memoria
                vectorNombres.Add(tNom.Text);
                vectorEdades.Add(edad);
                
                // Mostrar desde memoria
                rtb.Clear();
                rtb.AppendText("--- DATOS EN VECTORES ---\n");
                for(int i=0; i<vectorNombres.Count; i++) {
                    rtb.AppendText($"Pos[{i}]: {vectorNombres[i]} -> {vectorEdades[i]} años\n");
                }
                tNom.Clear(); tEdad.Clear();
            }
        };
        p.Controls.Add(tNom); p.Controls.Add(tEdad); p.Controls.Add(b); p.Controls.Add(rtb);
        return p;
    }

    // --- EJERCICIO 5: MATRICES A, B Y C ---
    private TabPage TabEjer5() {
        TabPage p = new TabPage("Ejer 5 (Matrices)"); p.BackColor = colorFondo;
        Button b = CrearBoton("GENERAR MATRICES", 30, 30); b.Width=200;
        RichTextBox rtb = new RichTextBox() { Top=90, Left=30, Width=600, Height=500, BackColor=Color.FromArgb(40,40,40), ForeColor=Color.White, Font=new Font("Consolas", 10) };
        
        b.Click += (s,e) => {
            int[,] A = new int[3,3]; int[,] B = new int[3,3]; int[,] C = new int[3,3];
            Random r = new Random();
            rtb.Clear();
            
            string txt = "--- MATRIZ A ---\n";
            for(int i=0; i<3; i++) {
                for(int j=0; j<3; j++) { A[i,j] = r.Next(1,9); txt += $"{A[i,j], 4}"; }
                txt += "\n";
            }
            
            txt += "\n--- MATRIZ B ---\n";
            for(int i=0; i<3; i++) {
                for(int j=0; j<3; j++) { B[i,j] = r.Next(1,9); txt += $"{B[i,j], 4}"; }
                txt += "\n";
            }
            
            txt += "\n--- RESULTADO (A + B) ---\n";
            for(int i=0; i<3; i++) {
                for(int j=0; j<3; j++) { C[i,j] = A[i,j] + B[i,j]; txt += $"{C[i,j], 4}"; }
                txt += "\n";
            }
            rtb.Text = txt;
        };
        p.Controls.Add(b); p.Controls.Add(rtb);
        return p;
    }

    // --- EJERCICIO 6: MATRIZ REAL ---
    private TabPage TabEjer6() {
        TabPage p = new TabPage("Ejer 6 (Tabla)"); p.BackColor = colorFondo;
        Button b = CrearBoton("VER TABLA", 30, 30);
        RichTextBox rtb = new RichTextBox() { Top=90, Left=30, Width=700, Height=500, BackColor=Color.FromArgb(40,40,40), ForeColor=Color.Cyan, Font=new Font("Consolas", 10) };
        
        b.Click += (s,e) => {
            int[,] M = new int[10,10];
            int[] v = {1,2,3,4,5,6,7,8,9,10};
            
            // Llenar Matriz
            for(int i=0; i<10; i++) for(int j=0; j<10; j++) M[i,j] = v[i]*v[j];
            
            rtb.Clear();
            rtb.AppendText("   X | 1    2    3    4    5    6    7    8    9   10\n");
            rtb.AppendText("------------------------------------------------------\n");
            for(int i=0; i<10; i++) {
                rtb.AppendText($"{v[i], 4} |");
                for(int j=0; j<10; j++) rtb.AppendText($"{M[i,j], 5}");
                rtb.AppendText("\n");
            }
        };
        p.Controls.Add(b); p.Controls.Add(rtb);
        return p;
    }

    [STAThread]
    static void Main() {
        Application.EnableVisualStyles();
        Application.Run(new TallerForm());
    }
}