package arbol;

public class Nodo {
    int dato;
    int prof;
    int facE;
    Nodo right, left;
    
//Constructor
    public Nodo(int d){
        this (d, null, null);
    }
    public Nodo(int d, Nodo r, Nodo l){
        this.dato = d;
        this.right = r;
        this.left = l;
        prof = 0;
        facE = 0;
    }
}