import numpy as np

def metodo_bisezione(fname, a, b, tolx,tolf):
     """
     Implementa il metodo di bisezione per il calcolo degli zeri di un'equazione non lineare.
    
     Parametri:
      f: La funzione da cui si vuole calcolare lo zero.
      a: L'estremo sinistro dell'intervallo di ricerca.
      b: L'estremo destro dell'intervallo di ricerca.
      tol: La tolleranza di errore.
    
     Restituisce:
      Lo zero approssimato della funzione, il numero di iterazioni e la lista di valori intermedi.
     """
     fa=fname(a)
     fb=fname(b)
     if # to do
         print("Non è possibile applicare il metodo di bisezione \n")
         return None, None,None
    
     it = 0
     v_xk = []
    
     max_it=int(np.ceil(np.log2((b - a) / tolx))) -1
     print("Max_It ",max_it)
     while #to do
            xk = to do 
          
            v_xk.append(xk)
            it += 1
            fxk=fname(xk)
            if np.abs(fxk)<tolf:
              return xk, it, np.array(v_xk)
        
            if   #to do   #la radice si trova nell'intervallo [a, xk].
              b = xk
              fb=fxk
            elif #to do   #la radice si trova nell'intervallo [xk, b].
              a = xk
              fa=fxk
        
     
     return xk, it, np.array(v_xk)


def falsi(fname, a, b, maxit, tolx,tolf):
 """
 Implementa il metodo di falsa posizione per il calcolo degli zeri di un'equazione non lineare.

 Parametri:
  f: La funzione da cui si vuole calcolare lo zero.
  a: L'estremo sinistro dell'intervallo di ricerca.
  b: L'estremo destro dell'intervallo di ricerca.
  tol: La tolleranza di errore.

 Restituisce:
  Lo zero approssimato della funzione, il numero di iterazioni e la lista di valori intermedi.
 """
 fa=fname(a);
 fb=fname(b);
 if #to do
     print("Non è possibile applicare il metodo di falsa posizione \n")
     return None, None,None

 it = 0
 v_xk = []
 
 fxk=tolf+1

 errore=tolx+1
 xk=None
 xprec=a
 
 while #to do 
        xk =#to do 
         
        fxk=fname(xk)
        if np.abs(fxk)<tolf:
          return xk, it, np.array(v_xk)
    
        if #  #la radice si trova nell'intervallo [a, xk].
          b = xk
          fb=fxk
        elif  #to do   #la radice si trova nell'intervallo [xk, b].
          a = xk
          fa=fxk
    
        if xk!=0:
             errore=# to do
        else:
             errore=#to do
        
        xprec=xk
        v_xk.append(xk)
        it += 1
 return xk, it, np.array(v_xk)

def corde(fname,a,b,coeff_ang,x0,tolx,tolf,nmax):
    
     # coeff_ang è il coefficiente angolare della retta che rimane fisso per tutte le iterazioni
        v_xk=[]
        xk=x0
        
        it=0
        errorex=1+tolx
        erroref=1+tolf
        xk1=None

        if abs(coeff_ang) <= np.spacing(1):
           print("Metodo delle corde: coefficiente angolare nullo")
           return None, None, None
                       
        while #to do
           
           fxk=fname(xk)

        
           d=# to do
           '''
           #xk= ascissa del punto di intersezione tra  la retta che passa per il punto
           (xi,f(xi)) e ha pendenza uguale a coeff_ang  e l'asse x
           '''
           xk1=# to do
           
            
           fxk1=fname(xk1)
           if xk1!=0:
                errorex=# to do 
           else:
                errorex=# to do 
           
           erroref=np.abs(fxk1)
           
           xk=xk1
           it=it+1
           v_xk.append(xk1)
          
        if it==nmax:
            print('Corde : raggiunto massimo numero di iterazioni \n')
            
        
        return xk1,it,np.array(v_xk)

def newton(fname,fpname,x0,tolx,tolf,nmax):
    
     # fpname è la lambda function contenente la derivata prima di fname
        v_xk=[]
        xk=x0
        
        it=0
        xk1=None
        errorex=1+tolx
        erroref=1+tolf
        while #to do  :
           
           fxk=fname(xk)
           fpxk=fpname(xk)
           if #to do 
             print("Newton: La derivata prima si annulla ")
             return None,None,None
        
           d=#to do
                    
           '''
           #xk1= ascissa del punto di intersezione tra  la retta che passa per il punto
           (xk,f(xk)) e ha pendenza uguale a quella della tangente nel punto  e l'asse x
           '''
           xk1=#to do
           
           fxk1=fname(xk1)
           
           if xk1!=0:
                errorex=#to do
           else:
                errorex=#to do 
           
           erroref=np.abs(fxk1)

           v_xk.append(xk1)
 
           xk=xk1
           it=it+1
          
        if it==nmax:
            print('Newton : raggiunto massimo numero di iterazioni \n')
            
        
        return xk1,it,np.array(v_xk)

def newton_modificato(fname,fpname,m,x0,tolx,tolf,nmax):
     #Il codice implementa il metodo di Newton modificato per radici multiple.
     #m è la molteplicità della radice
     # fpname è la lambda function contenente la derivata prima di fname
        v_xk=[]
        xk=x0
        
        xk1=None
        it=0
        errorex=1+tolx
        erroref=1+tolf
        while #to do :
           
           fxk=fname(xk)
           fpxk=fpname(xk)
           if #todo   #Se la derivata prima e' pià piccola della precisione di macchina stop
             print("Newton Modificato: La derivata prima si annulla ")
             return None,None,None
        
           d=# to do
                    
           '''
           #xk1= ascissa del punto di intersezione tra  la retta che passa per il punto
           (xk,f(xk)) e ha pendenza uguale a quella della tangente nel punto  e l'asse x
           '''
           xk1=# to do 
           fxk1=fname(xk1)
           if xk1!=0:
                errorex=#to do 
           else:
                errorex=#to do 
           
           erroref=np.abs(fxk1)
           v_xk.append(xk1)
           xk=xk1
           it=it+1
           
          
        if it==nmax:
            print('Newton Modificato : raggiunto massimo numero di iterazioni \n')
            
        return xk1,it,np.array(v_xk)

def secanti(fname,xm1,x0,tolx,tolf,nmax):
        #Metodo delle secanti per il calcolo degli zeri di un'equazione non lineare.
        #xm1, x0 due iterati iniziali
        
        v_xk=[]
        
        it=0
        errorex=1+tolx
        erroref=1+tolf
        xkm1=xm1
        xk=x0
        xk1=None #Inizializzare xk1 fa sì che se non si entra nel while la funzionenon dà errore, dovendo restituire xk1
        
        while #to do :
            
            fxkm1=fname(xkm1)
            fxk=fname(xk)
            c_ang_k=#to do 
            if #to do 
                print("Coefficiente angolare secanti troppo piccolo")
                return None, None, None
                
            d=#to do 
            
            #xk1 è l'ascissa del punto di intersezione tra la retta che passa due iterati precedenti e l'asse x
            xk1= #to do 
             
            fxk1=fname(xk1)
            v_xk.append(xk1);
            #Criteri di arresto
            if xk1!=0:
                errorex=abs(d)/abs(xk1)
            else:
                errorex=abs(d)
                
            erroref=np.abs(fxk1)
            #Aggiornamento di xkm1 ed xk
            xkm1 = xk
            xk = xk1
            
            it=it+1;
           
       
        if it==nmax:
           print('Secanti: raggiunto massimo numero di iterazioni \n')
        
        return xk1,it,np.array(v_xk)

def stima_ordine(xk,iterazioni):
     #Vedi dispensa allegata per la spiegazione

      k=iterazioni-4
      p=np.log(abs(xk[k+2]-xk[k+3])/abs(xk[k+1]-xk[k+2]))/np.log(abs(xk[k+1]-xk[k+2])/abs(xk[k]-xk[k+1]));
     
      ordine=p
      return ordine


def newton_raphson(initial_guess, F_numerical, J_numerical, tolX, tolF, max_iterations):
    # Converte la stima iniziale in un array NumPy di float
    X = np.array(initial_guess, dtype=float)

    # Contatore delle iterazioni
    it = 0

    # Inizializzo gli errori con valori maggiori delle tolleranze
    # così il ciclo while parte sicuramente
    erroreF = 1 + tolF
    erroreX = 1 + tolX

    # Lista che conterrà la storia dell'errore relativo sugli iterati
    errore = []

     
    while #to do 
        # Calcolo della matrice Jacobiana nel punto corrente X
        jx = #to do 

        if #to do 
            print("La matrice Jacobiana calcolata nell'iterato corrente non è a rango massimo")
            return None, None, None

        # Calcolo del valore della funzione F nel punto corrente
        fx = # to do

        
        s = #to do 

        # Aggiorno l'iterato
        Xnew = #to do 

        # Calcolo dell'errore relativo tra iterati successivi
        # usando la norma 1
        normaXnew = np.linalg.norm(Xnew, 1)
        if normaXnew != 0:
            erroreX = #to do 
        else:
            erroreX = #to do 

        # Salvo l'errore relativo per analisi successive
        errore.append(erroreX)

        # Calcolo di F nel nuovo punto
        fxnew = #to do 
        erroreF = np.linalg.norm(fxnew, 1)

        # Aggiorno l'iterato corrente
        X = Xnew
        it = it + 1

    # Restituisce:
    # - l'approssimazione della soluzione
    # - il numero di iterazioni eseguite
    # - la lista degli errori relativi sugli iterati
    return X, it, errore

def newton_raphson_corde(initial_guess, F_numerical, J_numerical, tolX, tolF, max_iterations):
    
    # Converte il punto iniziale in array NumPy
    X = np.array(initial_guess, dtype=float)

    # Contatore iterazioni
    it = 0

    # Inizializzazione errori (devono essere > tolleranze per entrare nel ciclo)
    erroreF = 1 + tolF
    erroreX = 1 + tolX

    # Lista per salvare errore relativo sugli iterati
    errore = []

    # La Jacobiana viene calcolata SOLO alla prima iterazione
    # e poi riutilizzata sempre (metodo delle corde)
    
    jx = #to do
    # Controllo che la Jacobiana sia invertibile
    if #to do 
          print("La matrice Jacobiana non è a rango massimo")
          return None, None, None
            
    # Ciclo iterativo
    while #to do 

        # Valutazione della funzione nel punto corrente
        fx = #to do 

        
        s = #to do 

        # Aggiornamento dell'iterato
        Xnew = #to do 

        # Calcolo errore relativo tra iterati successivi (norma 1)
        normaXnew = np.linalg.norm(Xnew, 1)
        if normaXnew != 0:
            erroreX = #to do 
        else:
            erroreX =  #to do 

        # Salvataggio errore
        errore.append(erroreX)

        # Calcolo del residuo nel nuovo punto
        fxnew = #to do 
        erroreF = np.linalg.norm(fxnew, 1)

        # Aggiornamento iterato
        X = Xnew
        it = it + 1

    # Output finale
    return X, it, errore

def newton_raphson_sham(initial_guess,F_numerical, J_numerical, tolX, tolF, update, max_iterations):
    
    # Converte il vettore iniziale in un array NumPy di float
    X = np.array(initial_guess, dtype=float)

    # Contatore delle iterazioni
    it = 0

    # Inizializzazione degli errori con valori maggiori delle tolleranze,
    # in modo da entrare sicuramente nel ciclo while
    erroreF = 1 + tolF
    erroreX = 1 + tolX

    # Lista per memorizzare l'errore relativo sugli iterati
    errore = []

    # 
    while #to do 
    
        if it % update == 0:
            jx = #to do 

            # Controlla che la Jacobiana sia invertibile
            if #to do 
                print("La matrice Jacobiana calcolata non è a rango massimo")
                return None, None, None

        # Calcola il valore della funzione F nel punto corrente
        fx = #to do 

        s = #to do 

        # Aggiorna l'iterato
        Xnew = #to do 

        # Calcola l'errore relativo tra iterati successivi usando la norma 1
        normaXnew = np.linalg.norm(Xnew, 1)
        if normaXnew != 0:
            erroreX = #to do 
        else:
            erroreX = #to do 

        # Salva l'errore relativo nella lista
        errore.append(erroreX)

        # Calcola il residuo nel nuovo punto
        fxnew = #to do 
        erroreF = np.linalg.norm(fxnew, 1)

        # Aggiorna l'iterato corrente e il contatore
        X = Xnew
        it = it + 1

    # Restituisce:
    # - l'approssimazione della soluzione
    # - il numero di iterazioni eseguite
    # - la lista degli errori relativi
    return X, it, errore


def jacobi(A,b,x0,toll,it_max):          # Definisce una funzione che applica il metodo iterativo di Jacobi
    errore= 1+toll                       

    d=#to do                        
    D=#to do 
    n=A.shape[0]                         
    
    E=#to do 
    F=#to do 

    M=#to do 
    N=#to do 
    
    T=#to do 

    autovalori=np.linalg.eigvals(T)     # Calcola gli autovalori della matrice di iterazione T
    raggiospettrale=#to do  # Calcola il raggio spettrale

    print("raggio spettrale jacobi", raggiospettrale) # Stampa il raggio spettrale

    it=0                                # Inizializza il contatore delle iterazioni

    er_vet=[]                           # Crea una lista vuota per salvare gli errori a ogni iterazione

    while #to do 

        x=#to do       # Calcola la nuova approssimazione x con la formula di Jacobi

        errore=#to do # Calcola l'errore relativo tra due iterazioni successive

        er_vet.append(errore)           # Aggiunge l'errore corrente alla lista degli errori

        x0=x.copy()                     # Aggiorna x0 con la nuova approssimazione x

        it=it+1                         # Incrementa il numero di iterazioni

    return x,it,er_vet                  # Restituisce la soluzione approssimata, il numero di iterazioni e il vettore degli errori

def gauss_seidel(A,b,x0,toll,it_max):   # Definisce la funzione che implementa il metodo iterativo di Gauss-Seidel

    errore=1+toll                      # Inizializza l'errore con un valore maggiore di toll per entrare nel ciclo

    d=#to do   
    D=#to do  

    E=#to do   
    F=#to do  

    M=#to do  
    N=#to do  
    
    T=#to do                             # Costruisce la matrice di iterazione di Gauss-Seidel

    autovalori=np.linalg.eigvals(T)     # Calcola gli autovalori della matrice di iterazione
    raggiospettrale=#to do  # Calcola il raggio spettrale 

    print("raggio spettrale Gauss-Seidel ",raggiospettrale) # Stampa il raggio spettrale

    it=0                                # Inizializza il contatore delle iterazioni

    er_vet=[]                           # Lista per salvare l'errore a ogni iterazione

    while #to do  

        

        x= #to do 

        errore=# to do Calcola l'errore relativo tra due iterazioni successive

        er_vet.append(errore)           # Salva l'errore corrente

        x0=x.copy()                     # Aggiorna la soluzione per l'iterazione successiva

        it=it+1                         # Incrementa il contatore delle iterazioni

    return x,it,er_vet                  # Restituisce: soluzione approssimata, numero iterazioni, vettore errori

def gauss_seidel_sor(A,b,x0,toll,it_max,omega):  # Definisce la funzione per il metodo di Gauss-Seidel con rilassamento (SOR)

    errore=1+toll                                 # Inizializza l'errore con un valore più grande di toll

    d=#to do  
    D=#to do  
    
    E=#to do  
    F=#to do  

    Momega=D+omega*E                             # Matrice M modificata per SOR: D + ωE
    Nomega=(1-omega)*D-omega*F                   # Matrice N modificata per SOR

    T=np.dot(np.linalg.inv(Momega),Nomega)       # Matrice di iterazione SOR: T = Mω^{-1} Nω

    autovalori=np.linalg.eigvals(T)              # Calcola gli autovalori della matrice di iterazione
    raggiospettrale=# Calcola il raggio spettrale

    print("raggio spettrale Gauss-Seidel SOR ", raggiospettrale)  # Stampa il raggio spettrale

    M=#to do  
    N=#to do  

    it=0                                         # Contatore iterazioni

    
    
    er_vet=[]                                    # Lista per memorizzare gli errori

    while #to do          # Ciclo iterativo con criterio di arresto

        
        xtilde=#to do  

        x=#to do  

        errore=#to do   # Errore relativo tra iterazioni successive

        er_vet.append(errore)                    # Salva l'errore

        x0=x.copy()                         # Aggiorna la soluzione per iterazione successiva

        it=it+1                                  # Incrementa il contatore

    return x,it,er_vet                        # Restituisce soluzione, numero iterazioni e vettore errori

def steepestdescent(A, b, x0, itmax, tol):
    # A = matrice del sistema
    # b = termine noto
    # x0 = punto iniziale
    # itmax = numero massimo di iterazioni
    # tol = tolleranza per il criterio di arresto

    n, m = A.shape  # dimensioni della matrice A
    if n != m:
        print("Matrice non quadrata")
        return [], []  # il metodo richiede una matrice quadrata

    # inizializzazione
    x = x0  # soluzione iniziale

    r =#to do    
    p =#to do  
    it = 0              # contatore iterazioni

    nb = np.linalg.norm(b)         
    errore =#to do                         # errore relativo iniziale

    vec_sol = []            # lista delle soluzioni iterate
    vec_sol.append(x.copy())

    vet_r = []              # lista degli errori
    vet_r.append(errore)

    # ciclo del metodo del gradiente
    while errore >= tol and it < itmax:
        it = it + 1  # incremento iterazioni

        Ap=#to do  
        # calcolo del passo ottimo (minimizza lungo la direzione p)
        alpha =#to do  

        # aggiornamento soluzione
        x=#to do  

        # salva iterata
        vec_sol.append(x.copy())

        # aggiornamento residuo (più efficiente che ricalcolare A@x - b)
        r =#to do  

        # aggiornamento errore
        errore =#to do  
        vet_r.append(errore)

        # nuova direzione  
        p =#to do  

    # costruzione matrice delle iterate (ogni riga = una iterazione)
    iterates_array = np.array(vec_sol)

    # output
    return x, vet_r, iterates_array, it

def conjugate_gradient(A, b, x0, itmax,tol):
    # Metodo del gradiente coniugato
    

    n, m = A.shape  # dimensioni della matrice A
    if n != m:
        print("Matrice non quadrata")
        return [], []  # necessario che A sia quadrata

    # inizializzazione
    x = x0  # punto iniziale

   

    r =#to do 
    p =#to do 
    it = 0             # contatore iterazioni

    nb = np.linalg.norm(b)   # norma di b (non usata qui)
    errore = #to do 
    
    vec_sol = []             # lista delle soluzioni
    vec_sol.append(x0.copy())

    vet_r = []               # lista degli errori
    vet_r.append(errore)

    # ciclo del gradiente coniugato
    while #to do 
        it = it + 1  # incremento iterazioni

        Ap = #to do 

        # passo ottimo lungo la direzione coniugata p
        alpha = #to do 

        # aggiornamento soluzione
        x =#to do 

        
        vec_sol.append(x.copy())  # salva iterata

        # salva (r^T r) vecchio per calcolare gamma
        rtr_old = #to do 

        # aggiornamento residuo
        r = #to do 

        # coefficiente di coniugazione (Fletcher-Reeves)
        gamma = #to do

        # aggiornamento errore
        errore = np.linalg.norm(r)/nb
        vet_r.append(errore)

        # nuova direzione: 
        p = #to do 

    # costruzione matrice delle iterate (ogni riga = una iterazione)
    iterates_array = np.array(vec_sol).squeeze()

    # output
    return x, vet_r, iterates_array, it

def eqnorm(A, b):
    
    #matrice delle equazioni normali 
    G = #to do 

    # Costruisce il termine noto delle equazioni normali:
    #
    
    f = #to do 

    # Applica la fattorizzazione di Cholesky alla matrice G e la utilizza per risolve il sistema Gx=f 
    
    return x


def qrLS(A, b):
    n = A.shape[1]

    # Si calcola la fattorizzazione QR della matrice A.

    h = Q.T @ b

    
    x= #

    
    residuo = #to do 

    # La funzione restituisce i coefficienti della soluzione x
    # e il valore del residuo quadratico.
    return x, residuo

def SVDLS(A, b):
    # La funzione risolve il problema ai minimi quadrati:
    #
    #       Ax ≈ b
    #
    # utilizzando la decomposizione ai valori singolari, cioè la SVD.
    # Questo metodo è particolarmente stabile dal punto di vista numerico,
    # soprattutto quando la matrice A è mal condizionata.

    # Si ricavano le dimensioni della matrice A.
    # m è il numero di righe, cioè il numero di dati disponibili.
    # n è il numero di colonne, cioè il numero di incognite del problema.
    m, n = A.shape

    # Si calcola la decomposizione ai valori singolari della matrice A:
    #
    #       A = U Σ V^T
    #
    # dove U e V sono matrici ortogonali, mentre Σ è una matrice diagonale
    # contenente i valori singolari.
    #
    # La funzione spLin.svd(A) restituisce:
    # - U: matrice dei vettori singolari sinistri;
    # - s: array monodimensionale contenente i valori singolari;
    # - VT: matrice V trasposta.
    U, s, VT = spLin.svd(A)
 
    V = VT.T

    # Si definisce una soglia numerica per stabilire quali valori singolari
    # sono effettivamente significativi.
    #
    # np.spacing(1) rappresenta circa la precisione di macchina.
    # La soglia dipende anche dal numero di righe m e dal valore singolare
    # massimo s[0].
    thresh = np.spacing(1) * m * s[0]

    # Si calcola il rango numerico della matrice A contando quanti valori
    # singolari sono maggiori della soglia.
    #
    # I valori singolari troppo piccoli vengono trascurati perché potrebbero
    # causare instabilità numerica, amplificando gli errori di arrotondamento.
    k = np.count_nonzero(s > thresh)
    print("rango=", k)

     
    d = #to do

    # Si considerano solo le prime k componenti di d, cioè quelle associate
    # ai valori singolari significativi.
    d1 = # to do 

    # Si considerano allo stesso modo solo i primi k valori singolari.
    s1 = #to do

    c = #to do 

    
    x = #to do 

    
    residuo = #to do 

    # La funzione restituisce:
    # - x: il vettore dei coefficienti della soluzione;
    # - residuo: l'errore quadratico associato all'approssimazione.
    return x, residuo

def plagr(xnodi, j):
    """
    Restituisce i coefficienti del j-esimo polinomio fondamentale
    di Lagrange associato ai nodi contenuti nel vettore xnodi.
    """

     
    xzeri = np.zeros_like(xnodi)

    # n rappresenta il numero di nodi di interpolazione.
    n = xnodi.size

     
    if j == 0:
        xzeri = #to do 
    else:
        xzeri = np.append(#to do )

    num = #to do 
    den = #to do 

    p = #to do 

    # La funzione restituisce i coefficienti del polinomio L_j(x).
    return p

def InterpL(x, y, xx):
    """
    Funzione che calcola i valori del polinomio interpolante
    di Lagrange in un insieme di punti.

    DATI INPUT:
    x  = vettore contenente i nodi di interpolazione
    y  = vettore contenente i valori della funzione nei nodi
    xx = vettore contenente i punti in cui valutare il polinomio interpolante

    DATI OUTPUT:
    vettore contenente i valori assunti dal polinomio interpolante
    nei punti xx
    """

    # n è il numero di nodi di interpolazione.
    n = x.size

    # m è il numero di punti in cui si vuole valutare il polinomio.
    m = xx.size

     
    L = np.zeros((m, n))

    # Per ogni nodo di interpolazione si costruisce il corrispondente
    # polinomio fondamentale di Lagrange.
    for j in range(n):
        p = #to do 
        L[:, j] = #to do 

    pol=#to do       
    return pol