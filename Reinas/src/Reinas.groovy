class Reinas {
    static int dimension;
    static int marcadas;
    static int[][] tablero, posibles;
    static int next;
    
    public static void main(String[] args) {
        dimension = 32;
        marcadas  = 0;
        next      = 0;
        tablero   = new int[dimension][dimension];
        posibles  = new int[dimension][dimension];
        marcar();
        println "Marcadas = " + marcadas
        mostrarTablero(tablero, posibles)
    }
    
    static int siguiente() {
        int sig;
        for(int i=0; i<dimension; i++) {
            for(int j=0; j<dimension; j++) {
                if(posibles[i][j]==0) {
                    sig = i*dimension+j;
                    if(sig >= next) {
                        next = sig;                  
                        return next;
                    }
                }
            }
        }
        return -1;
    }

    static boolean marcar() {
        int sig = siguiente();
        if(sig==-1) {
            return false;
        }
        
        int f   = sig / dimension; ///
        int c   = sig % dimension; 

        tablero[f][c] = 1;
        agregarPosibles(f,c);
        marcadas++;
        if(marcadas >= dimension) return true;
        
        boolean respuesta = marcar();
        //mostrarTablero(tablero, posibles);
        if(respuesta == true) return true;
        else {
            tablero[f][c]=0;
            quitarPosibles(f,c);
            marcadas--;
            next=sig+1;
            if(next==dimension*dimension) return false;
            return marcar();
        }
    }

    static void agregarPosibles(int f,int c) {
        int[][] tmp = atacadasPor(f,c);
        for(int i=0; i<dimension; i++) {
            for(int j=0; j<dimension; j++) {
                posibles[i][j]+=tmp[i][j];
            }
        }
    }
   
    static void quitarPosibles(int f,int c) {
        int[][] tmp = atacadasPor(f,c);
        for(int i=0; i<dimension; i++) {
            for(int j=0; j<dimension; j++) {
                posibles[i][j]-=tmp[i][j];
            }
        }
    }
    
    static int[][] atacadasPor(int fila, int columna) {
        int[][] m = new int[dimension][dimension];
        for(int i=0; i<dimension; i++) {
            for(int j=0; j<dimension; j++) {
                if(i==fila && j==columna) m[i][j]=1;
                else if(i==fila || j==columna) m[i][j]=1;
                else if(j-columna==fila-i|| j-columna==i-fila) m[i][j]=1;
            }
        }
        return m;
    }

    static public mostrarTablero(int[][] m, int[][] t) {
        println "";
        for(int i=0; i<m.length; i++) {
            print m[i]
            print "\t\t"
            println t[i];
        }
    }
}	