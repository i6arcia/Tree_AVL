package tree;

import java.io.BufferedInputStream.*;
import javax.swing.JOptionPane;

public class MyTree {
    private Nodo a;
    
    public void insertar(int val){
        a = insert(val, a);
    }
    
    private Nodo insert(int dato, Nodo b){
        if (b == null)
            b = new Nodo(dato, null, null);
        else{
            if (dato == b.valor)
                return b;
            else if (b.profundidad == 1 && dato < b.valor && b.facEquilibrio == -1){
                msgDesequilibrio(dato);
                return b;
            }else if (b.profundidad == 1 && dato > b.valor && b.facEquilibrio == 1){
                msgDesequilibrio(dato);
                return b;
            }
            else if (dato < b.valor){
                b.izquierdo = insert(dato, b.izquierdo);
            }else if (dato > b.valor){
                b.derecho = insert (dato, b.derecho);
            }
        }
        b.profundidad = profMax(getProfundidad (b.izquierdo), getProfundidad (b.derecho)) + 1;
        b.facEquilibrio = getFactorEquilibrio(b.izquierdo, b.derecho);
        System.out.println("Val[" + b.valor + "] Fe["+ b.facEquilibrio+"] Pf["+b.profundidad+"]");
        return b;
    }
    
    private int profMax(int profIzq, int profDer){
        return profIzq > profDer ? profIzq : profDer;
    }
    private int getProfundidad(Nodo b){
        return b == null ? -1 : b.profundidad;
    }
    private int getFactorEquilibrio(Nodo izq, Nodo der){
        int fEi, fEd;
        fEi = izq == null ? -1 : izq.profundidad;
        fEd = der == null ? -1 : der.profundidad;
        return fEd- fEi;
    }
    
    private void msgDesequilibrio(int val){
        JOptionPane.showOptionDialog(null, "El valor ["+val+"] desequilibra el Arbol", "Problemas al insertar",
                        JOptionPane.INFORMATION_MESSAGE, JOptionPane.INFORMATION_MESSAGE, null, new Object[]{"OK"}, "OK");
    }
    public void impPost_orden(){
        
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
