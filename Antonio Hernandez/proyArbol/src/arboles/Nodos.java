package arboles;


public class Nodos {
    
    int dato;
    Nodos right, left;
    int altura;
    int facEqui;
    
    public Nodos(int val){
        this (val, null, null);
    }
    public Nodos(int val, Nodos der, Nodos izq){
        this.dato = val;
        this.right = der;
        this.left = izq;
        altura = 0;
        facEqui = 0;
    }

    Nodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
