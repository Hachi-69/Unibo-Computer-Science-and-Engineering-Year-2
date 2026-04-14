from mpl_toolkits.mplot3d import Axes3D
import sympy as sym


# Definizione variabili simboliche
x_sym, y_sym = sym.symbols('x_sym y_sym')

f1_sym = lambda x_sym,y_sym: x_sym+y_sym-3   
f2_sym= lambda x_sym,y_sym: x_sym**2+y_sym**2-9   

#Disegnare le superfici

# Creazione dei vettori x e y nell'intervallo [-4, 4] con passo 0.1
x = np.arange(-4, 4, 0.1)
y = np.arange(-4, 4, 0.1)

# Creazione della griglia 2D di punti (X, Y)
# X e Y contengono tutte le combinazioni possibili di x e y
X, Y = np.meshgrid(x, y)

# Creazione del piano z = 0 (stessa dimensione della griglia)
Z = np.zeros_like(X)

# Valutazione del sistema non lineare sui punti della griglia
# F_numerical restituisce un vettore con due componenti:
# superfici[0,:,:] = f1(X,Y)
# superfici[1,:,:] = f2(X,Y)
superfici = F_numerical(X, Y).squeeze()

# Creazione della figura 3D
fig = plt.figure()
ax = fig.add_subplot(111, projection='3d')

# Plot della prima superficie z = f1(x,y)
ax.plot_surface(X, Y, superfici[0,:,:], cmap='viridis', alpha=0.5)

# Plot della seconda superficie z = f2(x,y)
ax.plot_surface(X, Y, superfici[1,:,:], cmap='Reds', alpha=0.5)

# Plot del piano z = 0 (serve per visualizzare dove le superfici si annullano)
ax.plot_surface(X, Y, Z, cmap='gray', alpha=0.5)

# Curve di livello z = 0 della prima funzione (f1(x,y) = 0)
plt.contour(X, Y, superfici[0,:,:], levels=[0], colors='black')

# Curve di livello z = 0 della seconda funzione (f2(x,y) = 0)
plt.contour(X, Y, superfici[1,:,:], levels=[0], colors='red')

# Mostra il grafico
plt.show()


def F_sym(f1_sym,f2_sym):
    return sym.Matrix([[f1_sym(x_sym,y_sym)], [f2_sym(x_sym,y_sym)]])   

# Calcolo della matrice Jacobiana simbolicamente
J_sym = F_sym(f1_sym,f2_sym).jacobian(sym.Matrix([x_sym, y_sym]))

# Converte la matrice jacobiana Simbolica in una funzione che può essere valutata numericamente mediante lambdify
J_numerical = sym.lambdify([x_sym, y_sym], J_sym, np)

# Converte il vettore di funzioni Simbolico in una funzione che può essere valutata numericamente mediante lambdify
F_numerical = sym.lambdify([x_sym, y_sym], F_sym(f1_sym,f2_sym), np)

#Definizione di una funzione in 2 varibili simboliche
F_sym=0.5*(0.001*(x_sym-1)**2+(x_sym**2-y_sym)**2)   
#Calcolo del vettore gradiente simbolicamente
grad_f = sym.derive_by_array(F_sym, (x_sym,y_sym))
#Calcolo della matrice Hessiana simbolicamente
H = sym.hessian(F_sym, (x_sym,y_sym))

# Conversione delle espressioni simboliche in funzioni numeriche
grad_f_func = sym.lambdify((x_sym,y_sym), grad_f, np)
H_func = sym.lambdify((x_sym,y_sym), H, np)
F_func=sym.lambdify((x_sym,y_sym), F_sym, np)
H = sym.hessian(F_sym, (x_sym,y_sym))
