import numpy as np
import matplotlib.pyplot as plt
def plot_corde_history(fname, xk, a_int, b_int,m, a=None, b=None, ampiezza=1.0, n_punti=400):
    """
    Visualizza il metodo delle corde mettendo in evidenza che
    tutte le rette iterative hanno coefficiente angolare fisso,
    uguale a quello della retta che collega gli estremi dell'intervallo.

    Parametri
    ---------
    fname : funzione
        Funzione f
    xk : lista
        Lista degli iterati [x0, x1, x2, ...]
    a_int, b_int : float
        Estremi dell'intervallo usati per definire il coefficiente angolare fisso
    a, b : float opzionali
        Estremi del grafico; se omessi, zoom automatico sugli iterati
    ampiezza : float
        Ampiezza minima della finestra
    n_punti : int
        Numero di punti per il grafico
    """

     

    # scala locale automatica
    if a is None or b is None:
        xmin = min(xk)
        xmax = max(xk)
        centro = 0.5 * (xmin + xmax)
        delta = max(xmax - xmin, ampiezza)
        a = centro - delta
        b = centro + delta

    xs = np.linspace(a, b, n_punti)
    ys = fname(xs)

    plt.figure(figsize=(9, 5))
    plt.plot(xs, ys, color='black', linewidth=2, label='f(x)')
    plt.axhline(0, linewidth=1)
    plt.axvline(0, linewidth=1)

    # colori diversi per le iterazioni
    colors = plt.cm.plasma(np.linspace(0, 1, max(2, len(xk)-1)))

    # retta di riferimento che collega gli estremi
    y_ref = fname(a_int) + m * (xs - a_int)
    plt.plot(xs, y_ref, color='gray', linewidth=2.5, alpha=0.9,
             label='Retta di riferimento tra gli estremi')

    # estremi evidenziati
    plt.scatter([a_int, b_int], [fname(a_int), fname(b_int)],
                color='gray', s=70, zorder=4)
    plt.text(a_int, fname(a_int), '  A', fontsize=10)
    plt.text(b_int, fname(b_int), '  B', fontsize=10)

    # iterazioni
    for k in range(len(xk) - 1):
        x0 = xk[k]
        x1 = xk[k + 1]
        fx0 = fname(x0)
        c = colors[k]

        # retta del metodo delle corde: stessa pendenza m, passante per (x0,f(x0))
        y_corda = fx0 + m * (xs - x0)

        plt.plot(xs, y_corda, '--', color=c, alpha=0.85,
                 label='Rette iterative' if k == 0 else "")
        plt.scatter(x0, fx0, color=c, s=60, zorder=5)
        plt.scatter(x1, 0, color=c, s=60, edgecolor='black', zorder=6)

        # segmenti guida
        plt.plot([x0, x0], [0, fx0], ':', color=c, alpha=0.9)
        plt.plot([x0, x1], [fx0, 0], ':', color=c, alpha=0.9)

        # etichette leggere
        plt.text(x0, fx0, f' $x_{k}$', fontsize=9)
        plt.text(x1, 0, f' $x_{k+1}$', fontsize=9)

    ymin = min(np.min(ys), 0)
    ymax = max(np.max(ys), 0)
    margine = 0.1 * (ymax - ymin + 1e-12)

    plt.xlim(a, b)
    plt.ylim(ymin - margine, ymax + margine)
    plt.title("Metodo delle corde: rette parallele con coefficiente angolare fisso")
    plt.grid(True)
    plt.legend()
    plt.show()
    

def plot_bisezione_step(fname, a, b, c, it, titolo, n_punti=400, ampiezza=0.2):
    """
    Visualizza un singolo passo di un metodo basato su intervalli
    (bisezione o falsa posizione).

    Parametri
    ---------
    fname : funzione
    a, b : estremi dell'intervallo corrente
    c : punto selezionato (medio o falsa posizione)
    it : iterazione
    titolo : stringa per il titolo del grafico
    """

    larghezza = b - a
    xmin = a - ampiezza * larghezza
    xmax = b + ampiezza * larghezza

    xs = np.linspace(xmin, xmax, n_punti)
    
    ys = fname(xs)

    fa = fname(a)
    fb = fname(b)
    fc = fname(c)

    plt.figure(figsize=(8, 5))
    plt.plot(xs, ys, color='black', linewidth=2, label='f(x)')

    plt.axhline(0, linewidth=1)
    plt.axvline(0, linewidth=1)

    # Intervallo corrente
    plt.plot([a, b], [0, 0], color='orange', linewidth=5, alpha=0.7,
             label='Intervallo corrente')

    # Punti
    plt.scatter(a, fa, color='blue', s=60, zorder=4)
    plt.scatter(b, fb, color='green', s=60, zorder=4)
    plt.scatter(c, fc, color='red', s=70, zorder=5)

    # Proiezioni sull'asse x
    plt.scatter(a, 0, color='blue', s=40)
    plt.scatter(b, 0, color='green', s=40)
    plt.scatter(c, 0, color='red', s=50)

    # Segmenti verticali
    plt.plot([a, a], [0, fa], ':', color='blue')
    plt.plot([b, b], [0, fb], ':', color='green')
    plt.plot([c, c], [0, fc], ':', color='red')

    # Etichette
    plt.text(a, fa, '  a', fontsize=10)
    plt.text(b, fb, '  b', fontsize=10)
    plt.text(c, fc, '  c', fontsize=10)

    # Limiti asse y
    ymin = min(np.min(ys), fa, fb, fc, 0)
    ymax = max(np.max(ys), fa, fb, fc, 0)
    margine = 0.1 * (ymax - ymin + 1e-12)

    plt.xlim(xmin, xmax)
    plt.ylim(ymin - margine, ymax + margine)

    #  TITOLO PARAMETRICO
    plt.title(f"{titolo} - iterazione {it}")

    plt.grid(True)
    plt.legend()
    plt.show()
    
 

def plot_secanti_step(fname, xkm1, xk, xk1, it, a=None, b=None, ampiezza=1.0, n_punti=400):
    """
    Visualizza un singolo passo del metodo delle secanti.

    Parametri
    ---------
    fname : funzione
        Funzione f
    xkm1 : float
        Iterato x_{k-1}
    xk : float
        Iterato x_k
    xk1 : float
        Nuovo iterato x_{k+1}
    it : int
        Numero di iterazione
    a, b : float opzionali
        Estremi del grafico; se omessi, zoom automatico locale
    ampiezza : float
        Ampiezza minima della finestra
    n_punti : int
        Numero di punti del grafico
    """

    fxkm1 = fname(xkm1)
    fxk = fname(xk)
    fxk1 = fname(xk1)

    # coefficiente angolare della secante
    m = (fxk - fxkm1) / (xk - xkm1)

    # finestra locale automatica
    if a is None or b is None:
        xmin = min(xkm1, xk, xk1)
        xmax = max(xkm1, xk, xk1)
        centro = 0.5 * (xmin + xmax)
        delta = max(xmax - xmin, ampiezza)
        a = centro - delta
        b = centro + delta

    xs = np.linspace(a, b, n_punti)
    ys = fname(xs)

    # retta secante
    secante = fxk + m * (xs - xk)

    plt.figure(figsize=(8, 5))
    plt.plot(xs, ys, color='black', linewidth=2, label='f(x)')
    plt.plot(xs, secante, '--', linewidth=2, label='Secante')

    plt.axhline(0, color='black', linewidth=1)
    plt.axvline(0, color='black', linewidth=1)

    # tre punti richiesti
    plt.scatter(xkm1, fxkm1, color='blue', s=70, zorder=5, label=r'$(x_{k-1},f(x_{k-1}))$')
    plt.scatter(xk, fxk, color='green', s=70, zorder=5, label=r'$(x_k,f(x_k))$')
    plt.scatter(xk1, 0, color='red', s=80, edgecolor='black', zorder=6, label=r'$x_{k+1}$')

    # proiezioni e segmenti guida
    plt.plot([xkm1, xkm1], [0, fxkm1], ':', color='blue')
    plt.plot([xk, xk], [0, fxk], ':', color='green')
    plt.plot([xk1, xk1], [0, fxk1], ':', color='red', alpha=0.7)

    # segmento che unisce i due punti della funzione
    plt.plot([xkm1, xk], [fxkm1, fxk], ':', color='purple', linewidth=1.5)

    # etichette
    plt.text(xkm1, fxkm1, r'  $x_{k-1}$', fontsize=10)
    plt.text(xk, fxk, r'  $x_k$', fontsize=10)
    plt.text(xk1, 0, r'  $x_{k+1}$', fontsize=10)

    ymin = min(np.min(ys), np.min(secante), fxkm1, fxk, 0)
    ymax = max(np.max(ys), np.max(secante), fxkm1, fxk, 0)
    margine = 0.1 * (ymax - ymin + 1e-12)

    plt.xlim(a, b)
    plt.ylim(ymin - margine, ymax + margine)

    plt.title(f"Metodo delle secanti - iterazione {it}")
    plt.grid(True)
    plt.legend()
    plt.show()

def plot_step(fname, xk, xnext, m, it, a=None, b=None, ampiezza=1.0, n_punti=400,
              titolo="Metodo iterativo", mostra_punto_precedente=None):
    """
    Disegna un passo grafico di un metodo iterativo basato su una retta.

    Parametri
    ---------
    fname : funzione
    xk : iterato corrente
    xnext : nuovo iterato
    m : coefficiente angolare della retta
    it : numero iterazione
    mostra_punto_precedente : opzionale, per le secanti
    """
    fxk = fname(xk)

    if a is None or b is None:
        xmin = min(xk, xnext) if mostra_punto_precedente is None else min(xk, xnext, mostra_punto_precedente)
        xmax = max(xk, xnext) if mostra_punto_precedente is None else max(xk, xnext, mostra_punto_precedente)
        centro = 0.5 * (xmin + xmax)
        delta = max(xmax - xmin, ampiezza)
        a = centro - delta
        b = centro + delta

    xs = np.linspace(a, b, n_punti)
    ys = fname(xs)
    retta = fxk + m * (xs - xk)

    plt.figure(figsize=(7, 4))
    plt.plot(xs, ys, color='black', label='f(x)')
    plt.plot(xs, retta, '--', label='Retta di approssimazione')

    plt.axhline(0, linewidth=1)
    plt.axvline(0, linewidth=1)

    plt.scatter(xk, fxk, s=60, zorder=3, label=f'Punto corrente')
    plt.scatter(xnext, 0, s=60, zorder=4, edgecolor='black', label=f'Nuovo iterato')

    plt.plot([xk, xk], [0, fxk], ':')
    plt.plot([xk, xnext], [fxk, 0], ':')

    if mostra_punto_precedente is not None:
        plt.scatter(mostra_punto_precedente, fname(mostra_punto_precedente), s=60, zorder=3,
                    label='Punto precedente')

    ymin = min(np.min(ys), np.min(retta), fxk, 0)
    ymax = max(np.max(ys), np.max(retta), fxk, 0)
    margine = 0.1 * (ymax - ymin + 1e-12)

    plt.xlim(a, b)
    plt.ylim(ymin - margine, ymax + margine)
    plt.title(f"{titolo} - iterazione {it}")
    plt.grid(True)
    plt.legend()
    plt.show()
def plot_corde_step(fname, a_ref, b_ref, xk, xk1, m, it, n_punti=500, margine_x=0.1):
    """
    Grafico  di un passo del metodo delle corde,
    con parallelismo evidenziato tra retta fissa e retta iterativa.
    """

    fa = fname(a_ref)
    fb = fname(b_ref)
    fxk = fname(xk)

  

    # finestra grafica
    xmin = min(a_ref, b_ref, xk, xk1)
    xmax = max(a_ref, b_ref, xk, xk1)
    dx = xmax - xmin
    if dx == 0:
        dx = 1.0

    xmin -= margine_x * dx
    xmax += margine_x * dx

    xs = np.linspace(xmin, xmax, n_punti)
    ys = fname(xs)

    # retta fissa
    y_ref = fa + m * (xs - a_ref)

    # retta iterativa
    y_iter = fxk + m * (xs - xk)

    plt.figure(figsize=(8, 5))
    plt.plot(xs, ys, color='black', linewidth=2, label='f(x)')
    plt.plot(xs, y_ref, color='gray', linewidth=2.5, alpha=0.95,
             label='Retta fissa passante per gli estremi')
    plt.plot(xs, y_iter, '--', color='tab:blue', linewidth=2.3,
             label='Retta dell’iterazione corrente')

    plt.axhline(0, color='black', linewidth=1)
    plt.axvline(0, color='black', linewidth=1)

    # estremi della retta fissa
    plt.scatter([a_ref, b_ref], [fa, fb], color='dimgray', s=70, zorder=5)
    plt.text(a_ref, fa, '  A', fontsize=10)
    plt.text(b_ref, fb, '  B', fontsize=10)

    # punto corrente e nuovo iterato
    plt.scatter(xk, fxk, color='tab:blue', s=75, zorder=6, label=r'$(x_k,f(x_k))$')
    plt.scatter(xk1, 0, color='tab:red', edgecolor='black', s=85, zorder=7, label=r'$x_{k+1}$')

    plt.text(xk, fxk, r'  $x_k$', fontsize=10)
    plt.text(xk1, 0, r'  $x_{k+1}$', fontsize=10)

    # guida essenziale
    plt.plot([xk, xk], [0, fxk], ':', color='tab:blue', alpha=0.8)

    # --- marcatori di parallelismo ---
    # piccolo segmento orizzontale uguale sulle due rette
    dx_mark = 0.18 * (xmax - xmin)

    # marcatore sulla retta fissa
    x_ref_mark = a_ref + 0.18 * (b_ref - a_ref)
    y_ref_mark = fa + m * (x_ref_mark - a_ref)
    plt.plot([x_ref_mark, x_ref_mark + dx_mark],
             [y_ref_mark, y_ref_mark + m * dx_mark],
             color='gray', linewidth=4, solid_capstyle='round')

    # marcatore sulla retta iterativa
    x_iter_mark = xk + 0.08 * (xmax - xmin)
    y_iter_mark = fxk + m * (x_iter_mark - xk)
    plt.plot([x_iter_mark, x_iter_mark + dx_mark],
             [y_iter_mark, y_iter_mark + m * dx_mark],
             color='tab:blue', linewidth=4, solid_capstyle='round')

    ymin = min(np.min(ys), np.min(y_ref), np.min(y_iter), 0)
    ymax = max(np.max(ys), np.max(y_ref), np.max(y_iter), 0)
    margine_y = 0.1 * (ymax - ymin + 1e-12)

    plt.xlim(xmin, xmax)
    plt.ylim(ymin - margine_y, ymax + margine_y)
    plt.title(f"Metodo delle corde - iterazione {it}")
    plt.grid(True, alpha=0.3)
    plt.legend()
    plt.show()