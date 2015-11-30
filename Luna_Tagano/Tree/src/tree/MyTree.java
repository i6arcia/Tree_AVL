package tree;

import java.io.BufferedInputStream.*;

public class MyTree {
    private Nodo a;
    
    public void insertar(int val){
        a = insert(val, a);
    }
    
    private Nodo insert(int dato, Nodo b){
        if (b == null)
            b = new Nodo(dato, null, null);
        return b;
    }
    
    public void impPos_orden(){
        
    }
    
    public void impIn_Orden(){
        
    }
    public void impOrden(){
        
    }
}

class Nodo {
    int valor;
    int profundidad;
    int facEquilibrio;
    Nodo derecho, izquierdo;
    
//Constructores
    public Nodo(int val){
        this (val, null, null);
    }
    public Nodo(int val, Nodo izq, Nodo der){
        this.valor = val;
        this.izquierdo = izq;
        this.derecho = der;
        profundidad = 0;
        facEquilibrio = 0;
    }
}
