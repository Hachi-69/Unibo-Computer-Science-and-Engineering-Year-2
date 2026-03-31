# BISEZIONE
import numpy as np
import utilities as util

def metodo_di_bisezione(fname, a, b, tolx, tolf):
    fa = fname(a)
    fb = fname(b)

    if np.sign(fa * fb) >= 0:
        print("Il metodo di bisezione non è applicabile")
        return None, None, None

    it = 0
    v_xk = []

    max_it = int(np.ceil(np.log((b-a)/tolx)))-1

    while np.abs(b-a) > tolx and it < max_it:
        xk = a + (b - a) / 2
        if it <= 5:
            util.plot_bisezione_step(fname, a, b, xk, it, "Bisezione")
        v_xk.append(xk)
        fxk = fname(xk)
        it += 1
        
        if np.abs(fxk) < tolf:
            return xk, it, np.array(v_xk)

        if np.sign(fa * fxk) < 0: # La radice si trova in [a, xk]
            b = xk
            fb = fxk
        elif np.sign(fxk * fb) < 0: # La radice si trova in [xk, b]
            a = xk
            fa = fxk

    return xk, it, np.array(v_xk)

# FALSA POSIZIONE

import numpy as np
import utilities as util

def falsi(fname, a, b, tolx, tolf, max_it):
    fa = fname(a)
    fb = fname(b)

    if np.sign(fa * fb) >= 0:
        print("Il metodo di bisezione non è applicabile")
        return None, None, None

    it = 0
    v_xk = []
    errorex = tolx + 1
    fxk = tolf +1
    xprec = a
    while it < max_it and np.abs(fxk) > tolf and errorex > tolx:
        xk = a - fa * (b - a) / (fb - fa)
        if it <= 5:
            util.plot_bisezione_step(fname, a, b, xk, it, "Falsi")
        v_xk.append(xk)
        fxk = fname(xk)
        
        if np.abs(fxk) < tolf:
            return xk, it, np.array(v_xk)

        if np.sign(fa * fxk) < 0: # La radice si trova in [a, xk]
            b = xk
            fb = fxk
        elif np.sign(fxk * fb) < 0: # La radice si trova in [xk, b]
            a = xk
            fa = fxk

        if xk != 0:
            errorex = np.abs(xk - xprec) / np.abs(xk)
        else:
            errorex = np.abs(xk - xprec)
        xprec = xk
        it += 1
    return xk, it, np.array(v_xk)

# CORDE

def corde(fname, a, b, x0, coeff_ang, tolx, tolf, max_it):

    it = 0
    v_xk = []
    errorex = tolx + 1
    erroref = tolf + 1
    while it < max_it and erroref > tolf and errorex > tolx:
        fx0 = fname(x0)
        d = fx0 / coeff_ang

        x1 = x0 - d
        if it <= 5:
            util.plot_corde_step(fname, a, b, x0, x1, coeff_ang, it)
        fx1 = fname(x1)     

        if x1 != 0:
            errorex = np.abs(d) / np.abs(x1)
        else:
            errorex = np.abs(d)
        erroref = np.abs(fx1)
        x0 = x1
        it += 1

    return xk, it, np.array(v_xk)



