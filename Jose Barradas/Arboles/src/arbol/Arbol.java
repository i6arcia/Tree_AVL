package arbol;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import javax.swing.JOptionPane;


public class Arbol {
    //Unidad minima del árbol
    private Nodo n;
    //Metodo publico que inserta un dato
    public void inserta(int valor){
        n = inserta(valor, n);
    }
    //metodo Privado que inserta el dato al arbol
    private Nodo inserta(int valor, Nodo m){
        
        if (m != null){
            //Si el nodo ya existe, no hace algo
            if (valor == m.val)
                return m;
            //Si el nodo es menor invoca recursivamente con el nodo izquierdo
            else if (valor < m.val){
                m.izq = inserta(valor, m.izq);
            //Si el nodo es mayr invoca recursivamente con el nodo derecho
            }else if (valor > m.val){
                m.der = inserta (valor, m.der);
            }
        //Si el arbol esta vacio genera el primer nodo
        }else
            m = new Nodo(valor, null, null);
        //invoca al Metodo que optiene la altura o profundidad del nodo y el factor de equilibrio.
        m = getFactores(m);
        //Si el nodo desequilibra el árbol no lo agrega
        if (m.equilibrio > 1 || m.equilibrio < -1){
            JOptionPane.showOptionDialog(null, "El valor que desea insertar desequilibra el Arbol", "Error al insertar",
                        JOptionPane.INFORMATION_MESSAGE, JOptionPane.INFORMATION_MESSAGE, null, new Object[]{"OK"}, "OK");
            equilibrar(valor, m);
        }
        return m;
    }
    //Metodo que calcula Altura y factor de equilibrio
    private Nodo getFactores(Nodo m){
        int alturaMax, alturaIzq, alturaDer;
        //Si el nodo izquierdo esta vacio entonces la altura es de -1 en caso contrario será la altura que contenga en nodo izquiedo.
        alturaIzq = m.izq == null ? -1 : m.izq.altura;
        alturaDer = m.der == null ? -1 : m.der.altura;
        //Obtiene la altura maxima entre izquierda y derecha.
        alturaMax = alturaIzq > alturaDer ? alturaIzq : alturaDer;
        //Almacena alura
        m.altura = alturaMax+1;
        
        int equilibrioD, equilibrioI;
        //Obtiene el valor del equilibrio del nodo izquierdo
        equilibrioI = m.izq == null ? -1: m.izq.altura;
        //Obtiene el valor del equilibrio del nodo derecho
        equilibrioD = m.der == null ? -1: m.der.altura;
        //El equilibrio será igual a equilibrio del nodo derecho menos el equilibrio del nodo izquierdo
        m.equilibrio = equilibrioD-equilibrioI;
        return m;
    }    
    //elimina un nodo hoja cuando el arbol se desequilibro
    private Nodo equilibrar (int val, Nodo m){
        if (m!= null){
            //Si el valor se encuentra en un nodo hoja simplemente se elimina
            if (val == m.val && m.izq==null && m.der==null){
                m = null;
                return m;
            //Si es menor y el nodo izquierdo es igual solo se elimina, en caso contrario se llama recursivamente con izquiedo
            }else if (val < m.val){
                if (m.izq.val == val){
                    m.izq = null;
                    m = getFactores(m);
                }
                else
                    equilibrar(val, m.izq);
            }
            //Si es mayor y el nodo derecho es igual solo se elimina el nodo derecho, en caso contrario se llama recursivamente con derecho
            else if (val > m.val){
                if (m.der.val == val){
                    m.der= null;
                    m= getFactores(m);
                }
                else
                    equilibrar(val, m.der);
            }
        }
        return m;
    }
    //Obtiene el nodo raiz
    public Nodo getNodo(){
        return n;
    }
    //Devuelve el punto de equilibrio de un nodo
    public int puntoDeEquilibrio (Nodo m){
        if (m != null){
            //Punto de quilibrio es igual a la resta del punto de equilibrio del nodo derecho con la del izquierdo
            int equilibrioD, equilibrioI;
            equilibrioI = m.izq == null ? -1: m.izq.altura;
            equilibrioD = m.der == null ? -1: m.der.altura;
            return equilibrioD-equilibrioI;
        }else
            return 0;
    }
    private String sArbol;
    //Obtine la cadena con el contenido del arbol segun la opcion
    public String printPant(int opt){
        String sCadena ="null";
        sArbol="";
        
        switch(opt){
            //Pre orden
            case 0:
                sCadena = pre_Orden(n);
                break;
            //In orden
            case 1:
                sCadena = in_Orden(n);
                break;
            //Post Orden
            case 2:
                sCadena = post_orden(n);
                break;
        }
        return sCadena;
    }
    //Devuelve la cadena con el recorrido del arbol en pre orden
    public String pre_Orden(Nodo m){
        if (m != null){
            sArbol += "Nodo["+m.val+"] | Fac Equil["+m.equilibrio+"] | Altura["+m.altura+"]\n" ;
            pre_Orden(m.izq);
            pre_Orden(m.der);
        }
        return sArbol;
    }
    //devuelve la cadena con el recorrido del arbol in orden
    public String in_Orden(Nodo m){
        if (m != null){
            in_Orden(m.izq);
            sArbol += "Nodo["+m.val+"] | Fac Equil["+m.equilibrio+"] | Altura["+m.altura+"]\n" ;
            in_Orden(m.der);
        }
        return sArbol;
    }
    //devuelve la cadena con el recorrido del arbol post orden
    public String post_orden(Nodo m){
        if (m != null){
            post_orden(m.izq);
            post_orden(m.der);
            sArbol += "Nodo["+m.val+"] | Fac Equil["+m.equilibrio+"] | Altura["+m.altura+"]\n" ;
        }
        return sArbol;
    }
    //Genra el archivo txt con el contenido del arbol
    public void archivo(){
        try{
            //Ruta del archivo
            String ruta = "PostOrden.txt";
            File f = new File(ruta);
            BufferedWriter bw;
            //Si ya existe agrega contenido si no crea el archivo
            if (f.exists()){
                bw = new BufferedWriter(new FileWriter(f));
                bw.write(post_orden(n));
            }else{
                bw = new BufferedWriter(new FileWriter(f));
                bw.write(post_orden(n));
            }
            bw.close();
            JOptionPane.showMessageDialog(null, "Archivo generado");
        }catch (java.io.IOException ioe){
        }
    }
}

//Clase nodo, unidad minima del arbol
class Nodo {
    int val;
    Nodo der, izq;
    int equilibrio;
    int altura;
    
    
    public Nodo(int v){
        this (v, null, null);
    }
    public Nodo(int v, Nodo izq, Nodo der){
        altura = 0;
        equilibrio = 0;
        
        this.val = v;
        this.der = der;
        this.izq = izq;
        
    }    
}