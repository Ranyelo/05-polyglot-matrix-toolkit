import tkinter as tk
from tkinter import ttk, messagebox
import random


class TallerApp:
    def __init__(self, root):
        self.root = root
        self.root.title("Taller Estructura de Datos")  # Título limpio
        self.root.geometry("850x700")
        self.root.configure(bg="#2E2E2E")  # Fondo oscuro

        # Configuración de estilo (Tema Oscuro)
        style = ttk.Style()
        style.theme_use('clam')
        style.configure("TFrame", background="#2E2E2E")
        style.configure("TLabel", background="#2E2E2E", foreground="white", font=("Segoe UI", 10))
        style.configure("TButton", background="#007ACC", foreground="white", borderwidth=0,
                        font=("Segoe UI", 9, "bold"))
        style.map("TButton", background=[("active", "#005f9e")])
        style.configure("TNotebook", background="#2E2E2E", borderwidth=0)
        style.configure("TNotebook.Tab", background="#3E3E3E", foreground="white", padding=[15, 5])
        style.map("TNotebook.Tab", background=[("selected", "#007ACC")])

        self.notebook = ttk.Notebook(root)
        self.notebook.pack(expand=True, fill='both', padx=10, pady=10)

        # Vectores globales (Persistencia de datos Ejer 4)
        self.vector_nombres = []
        self.vector_edades = []

        # Inicializar pestañas
        self.setup_ejercicio1()
        self.setup_ejercicio2()
        self.setup_ejercicio3()
        self.setup_ejercicio4()
        self.setup_ejercicio5()
        self.setup_ejercicio6()

    # --- Ejer 1: Promedio Simple ---
    def setup_ejercicio1(self):
        frame = ttk.Frame(self.notebook)
        self.notebook.add(frame, text='Ejer 1')
        ttk.Label(frame, text="Ingrese notas (separadas por espacio):").pack(pady=20)
        entry = ttk.Entry(frame, width=50)
        entry.pack(pady=5)
        lbl = ttk.Label(frame, text="Resultado: -", font=("Segoe UI", 12))
        lbl.pack(pady=20)

        def calc():
            try:
                vec = [float(x) for x in entry.get().split()]
                if not vec: return
                prom = sum(vec) / len(vec)
                lbl.config(text=f"Promedio: {prom:.2f}", foreground="#00FF00")
            except:
                messagebox.showerror("Error", "Revise los datos")

        ttk.Button(frame, text="CALCULAR", command=calc).pack()

    # --- Ejer 2: Promedio + Cantidad ---
    def setup_ejercicio2(self):
        frame = ttk.Frame(self.notebook)
        self.notebook.add(frame, text='Ejer 2')
        ttk.Label(frame, text="Cantidad (N):").pack(pady=5);
        en = ttk.Entry(frame);
        en.pack()
        ttk.Label(frame, text="Notas:").pack(pady=5);
        enotas = ttk.Entry(frame, width=50);
        enotas.pack()
        lbl = ttk.Label(frame, text="-")
        lbl.pack(pady=20)

        def calc():
            try:
                n = int(en.get())
                vec = [float(x) for x in enotas.get().split()]
                if len(vec) != n: messagebox.showwarning("!", f"Se esperaban {n} notas."); return
                lbl.config(text=f"Promedio: {sum(vec) / n:.2f}", foreground="#00FF00")
            except:
                pass

        ttk.Button(frame, text="CALCULAR", command=calc).pack(pady=10)

    # --- Ejer 3: Decimales ---
    def setup_ejercicio3(self):
        frame = ttk.Frame(self.notebook)
        self.notebook.add(frame, text='Ejer 3')
        ttk.Label(frame, text="Notas con decimales:").pack(pady=20)
        entry = ttk.Entry(frame, width=50);
        entry.pack()
        lbl = ttk.Label(frame, text="-")
        lbl.pack(pady=20)

        def calc():
            try:
                vec = [float(x) for x in entry.get().split()]
                lbl.config(text=f"Exacto: {sum(vec) / len(vec):.4f}", foreground="#00FF00")
            except:
                pass

        ttk.Button(frame, text="CALCULAR", command=calc).pack()

    # --- Ejer 4: Vectores Paralelos (Memoria) ---
    def setup_ejercicio4(self):
        frame = ttk.Frame(self.notebook)
        self.notebook.add(frame, text='Ejer 4')
        ttk.Label(frame, text="Nombre:").pack();
        enom = ttk.Entry(frame);
        enom.pack()
        ttk.Label(frame, text="Edad:").pack();
        eedad = ttk.Entry(frame);
        eedad.pack()

        txt = tk.Text(frame, height=12, width=50, bg="#1E1E1E", fg="#00FF00", font=("Consolas", 10))
        txt.pack(pady=15)

        def agregar():
            if enom.get() and eedad.get().isdigit():
                # Guardar en vectores en memoria
                self.vector_nombres.append(enom.get())
                self.vector_edades.append(int(eedad.get()))

                # Mostrar leyendo los vectores
                txt.delete('1.0', tk.END)
                txt.insert(tk.END, "--- REGISTRO (Vectores Paralelos) ---\n")
                for i in range(len(self.vector_nombres)):
                    txt.insert(tk.END, f"[{i}] {self.vector_nombres[i]} -> {self.vector_edades[i]} años\n")
                enom.delete(0, tk.END);
                eedad.delete(0, tk.END)

        ttk.Button(frame, text="AGREGAR", command=agregar).pack(pady=5)

    # --- Ejer 5: Matrices (CORREGIDO: Muestra A, B y C) ---
    def setup_ejercicio5(self):
        frame = ttk.Frame(self.notebook)
        self.notebook.add(frame, text='Ejer 5')

        txt = tk.Text(frame, height=20, width=60, bg="#1E1E1E", fg="#00FF00", font=("Consolas", 10))
        txt.pack(pady=10)

        def run():
            # 1. Generar datos
            mA = [[random.randint(1, 9) for _ in range(3)] for _ in range(3)]
            mB = [[random.randint(1, 9) for _ in range(3)] for _ in range(3)]
            mC = [[0] * 3 for _ in range(3)]

            # 2. Construir visualización paso a paso
            res = "--- MATRIZ A ---\n"
            for fila in mA:
                res += "  ".join(f"{x:3d}" for x in fila) + "\n"

            res += "\n--- MATRIZ B ---\n"
            for fila in mB:
                res += "  ".join(f"{x:3d}" for x in fila) + "\n"

            res += "\n--- RESULTADO (A + B) ---\n"
            # Calcular y mostrar
            for i in range(3):
                for j in range(3):
                    mC[i][j] = mA[i][j] + mB[i][j]
                res += "  ".join(f"{x:3d}" for x in mC[i]) + "\n"

            txt.delete('1.0', tk.END);
            txt.insert(tk.END, res)

        ttk.Button(frame, text="GENERAR MATRICES", command=run).pack()

    # --- Ejer 6: Tabla (Matriz Real) ---
    def setup_ejercicio6(self):
        frame = ttk.Frame(self.notebook)
        self.notebook.add(frame, text='Ejer 6')
        txt = tk.Text(frame, height=15, width=80, bg="#1E1E1E", fg="cyan", font=("Consolas", 9))
        txt.pack(pady=10)

        def run():
            vec = list(range(1, 11))
            matriz = [[0] * 10 for _ in range(10)]  # Matriz real en memoria

            # Llenar Matriz
            for i in range(10):
                for j in range(10):
                    matriz[i][j] = vec[i] * vec[j]

            # Mostrar Matriz
            s = "   X |" + "".join(f"{n:5d}" for n in vec) + "\n" + "-" * 60 + "\n"
            for i in range(10):
                linea = f"{vec[i]:4d} |"
                for j in range(10):
                    linea += f"{matriz[i][j]:5d}"  # Leer de memoria
                s += linea + "\n"
            txt.delete('1.0', tk.END);
            txt.insert(tk.END, s)

        ttk.Button(frame, text="GENERAR TABLA", command=run).pack()


if __name__ == "__main__":
    root = tk.Tk()
    app = TallerApp(root)
    root.mainloop()