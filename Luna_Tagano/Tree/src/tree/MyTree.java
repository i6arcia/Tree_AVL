package tree;

import java.io.*;
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
            else if (dato < b.valor){
                b.izquierdo = insert(dato, b.izquierdo);
            }else if (dato > b.valor){
                b.derecho = insert (dato, b.derecho);
            }
        }
        b.profundidad = profMax(getProfundidad (b.izquierdo), getProfundidad (b.derecho)) + 1;
        b.facEquilibrio = getFactorEquilibrio(b.izquierdo, b.derecho);
        System.out.println("Val[" + b.valor + "] Fe["+ b.facEquilibrio+"] Pf["+b.profundidad+"]");
        if (b.facEquilibrio > 1 || b.facEquilibrio < -1){
            msgDesequilibrio(dato);
            System.out.println("El valor [" + dato + "] desequilobra el arbol");
            borrarNodo(dato, b);
        }
        return b;
    }
    
    private Nodo borrarNodo(int val, Nodo b){
        if (b!= null){
            if (val == b.valor && b.izquierdo==null && b.derecho==null){
                System.out.println("Borrado");
                b = null;
                return b;
            }
            else if (val < b.valor){
                if (b.izquierdo.valor == val){
                    b.izquierdo = null;
                    b.profundidad = profMax(getProfundidad (b.izquierdo), getProfundidad (b.derecho)) + 1;
                    b.facEquilibrio = getFactorEquilibrio(b.izquierdo, b.derecho);
                }
                else
                    borrarNodo(val, b.izquierdo);
            }
            else if (val > b.valor){
                if (b.derecho.valor == val){
                    b.derecho= null;
                    b.profundidad = profMax(getProfundidad (b.izquierdo), getProfundidad (b.derecho)) + 1;
                    b.facEquilibrio = getFactorEquilibrio(b.izquierdo, b.derecho);
                }
                else
                    borrarNodo(val, b.derecho);
            }
            System.out.println("Nodo["+b.valor+"]");
        }
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
    
    public String imprimir(int val){
        String sTree ="null";
        switch(val){
            case 0:
                pre = "<html><body>";
                sTree = impPre_Orden(a) + "</body></html>";
                break;
            case 1:
                in = "<html><body>";
                sTree = impIn_Orden(a) + "</body></html>";
                break;
            case 2:
                post = "<html><body>";
                sTree = impPost_orden(a) + "</body></html>";
                break;
        }
        return sTree;
    }
    
    String pre;
    public String impPre_Orden(Nodo b){
        if (b != null){
            pre += "Valor["+b.valor+"]---F.E["+b.facEquilibrio+"]---Pf["+b.profundidad+"]<br>" ;
            impPre_Orden(b.izquierdo);
            impPre_Orden(b.derecho);
        }
        return pre;
    }
    
    String in;
    public String impIn_Orden(Nodo b){
        if (b != null){
            impIn_Orden(b.izquierdo);
            in += "Valor["+b.valor+"]---F.E["+b.facEquilibrio+"]---Pf["+b.profundidad+"]<br>" ;
            impIn_Orden(b.derecho);
        }
        return in;
    }
    
    String post;
    public String impPost_orden(Nodo b){
        if (b != null){
            impPost_orden(b.izquierdo);
            impPost_orden(b.derecho);
            post +=  "Valor["+b.valor+"]---F.E["+b.facEquilibrio+"]---Pf["+b.profundidad+"]<br>" ;
        }
        return post;
    }
    
    public void guardar(){
        try{
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(isr);
            FileWriter fw = new FileWriter("./src/tree/arbol.txt",true);
            BufferedWriter bw  = new BufferedWriter (fw);
            PrintWriter pw = new PrintWriter(bw);
            pw.println(arbolToTxt(a));
            pw.close();
        }catch (java.io.IOException ioe){
            
        }
    }
    String cadena="";
    private String arbolToTxt(Nodo b){
        if (b != null){
            cadena += "Valor["+b.valor+"] ----- Factor de Equilibrio["+b.facEquilibrio+"]"
                    + " ----- Profundidad["+b.profundidad+"]\n" ;
            arbolToTxt(b.izquierdo);
            arbolToTxt(b.derecho);
        }
        return cadena;
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
