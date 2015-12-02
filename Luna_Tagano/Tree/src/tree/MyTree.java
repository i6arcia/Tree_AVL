package tree;

import java.io.*;
import javax.swing.JOptionPane;

public class MyTree {
    //El nodo a se ocupara para todo
    private Nodo a;
    
    //Metodo publico para insertar un dato
    public void insertar(int val){
        a = insert(val, a);
    }
    //Metodo provado que inserta dato en el nodo a
    private Nodo insert(int dato, Nodo b){
        //Si el nodo esta vacio se agrega el primer nodo
        if (b == null)
            b = new Nodo(dato, null, null);
        //En caso contrario se busca si es igual (no se hace algo)
                                    // si es menor, se invoca recursivamente con el nodo izquierdo.
                                    // Si es mayor, se invoca recursivamente con el nodo derecho.
        else{
            if (dato == b.valor)
                return b;
            else if (dato < b.valor){
                b.izquierdo = insert(dato, b.izquierdo);
            }else if (dato > b.valor){
                b.derecho = insert (dato, b.derecho);
            }
        }
        //Compara profundidad de hojas y la que sea mayor se suma 1 obteniendo la profundidad de la raiz
        //Se optiene invocando los metodos profMax y getProfundidad de esta misma clase
        b.profundidad = profMax(getProfundidad (b.izquierdo), getProfundidad (b.derecho)) + 1;
        //El factor de equilibrio se optiene restando la profundad derecha con la izquierda
        //Se optiene invocando el metodo getFactorEquilibrio de esta misma clase.
        b.facEquilibrio = getFactorEquilibrio(b.izquierdo, b.derecho);
        //Mensaje de control por consola
        System.out.println("Val[" + b.valor + "] Fe["+ b.facEquilibrio+"] Pf["+b.profundidad+"]");
        //Si el nodo que se agrego desequilibra el arbol, entonces se borra el nodo.
        if (b.facEquilibrio > 1 || b.facEquilibrio < -1){
            msgDesequilibrio(dato);
            System.out.println("El valor [" + dato + "] desequilobra el arbol");
            borrarNodo(dato, b);
        }
        return b;
    }
    //Solamente elimina nodos hoja, Porque desequilibran el árbol
    private Nodo borrarNodo(int val, Nodo b){
        if (b!= null){
            //Si es un nodo hoja, solo se asigna null
            if (val == b.valor && b.izquierdo==null && b.derecho==null){
                System.out.println("Borrado");
                b = null;
                return b;
            }
            //Si el valor es menos se compara el nodo izquierdo si es igual entonces se elimina y recalcula profundidad y factor e.
            else if (val < b.valor){
                if (b.izquierdo.valor == val){
                    b.izquierdo = null;
                    b.profundidad = profMax(getProfundidad (b.izquierdo), getProfundidad (b.derecho)) + 1;
                    b.facEquilibrio = getFactorEquilibrio(b.izquierdo, b.derecho);
                }
                //En caso contrario recursivamente se invoca con el nodo izquierdo
                else
                    borrarNodo(val, b.izquierdo);
            }
            //Si el valor el mayor hace los mismo que el else anterior solo que con el nodo derecho.
            else if (val > b.valor){
                if (b.derecho.valor == val){
                    b.derecho= null;
                    b.profundidad = profMax(getProfundidad (b.izquierdo), getProfundidad (b.derecho)) + 1;
                    b.facEquilibrio = getFactorEquilibrio(b.izquierdo, b.derecho);
                }
                else
                    borrarNodo(val, b.derecho);
            }
            //Mensaje de control en consola
            System.out.println("Nodo["+b.valor+"]");
        }
        //Devolvemos el arobol
        return b;
    }
    //Para obtener profundidad
    private int profMax(int profIzq, int profDer){
        return profIzq > profDer ? profIzq : profDer;
    }
    //Para obtener profundidad
    private int getProfundidad(Nodo b){
        return b == null ? -1 : b.profundidad;
    }
    //Para obtener el factor de equilibrio
    private int getFactorEquilibrio(Nodo izq, Nodo der){
        int fEi, fEd;
        fEi = izq == null ? -1 : izq.profundidad;
        fEd = der == null ? -1 : der.profundidad;
        return fEd- fEi;
    }
    //Nuestra un mesage 
    private void msgDesequilibrio(int val){
        JOptionPane.showOptionDialog(null, "El valor ["+val+"] desequilibra el Arbol", "Problemas al insertar",
                        JOptionPane.INFORMATION_MESSAGE, JOptionPane.INFORMATION_MESSAGE, null, new Object[]{"OK"}, "OK");
    }
    //Devuelve una cadena con el contenido del arbol
    public String imprimir(int val){
        String sTree ="null";
        switch(val){
            //Pre orden
            case 0:
                pre = "<html><body>";
                sTree = impPre_Orden(a) + "</body></html>";
                break;
            //In Orden
            case 1:
                in = "<html><body>";
                sTree = impIn_Orden(a) + "</body></html>";
                break;
            //Post Orden
            case 2:
                post = "<html><body>";
                sTree = impPost_orden(a) + "</body></html>";
                break;
        }
        return sTree;
    }
    
    String pre;
    //Metodo que recorre el arbol en Pre orden
    public String impPre_Orden(Nodo b){
        if (b != null){
            pre += "Valor["+b.valor+"]---F.E["+b.facEquilibrio+"]---Pf["+b.profundidad+"]<br>" ;
            impPre_Orden(b.izquierdo);
            impPre_Orden(b.derecho);
        }
        return pre;
    }
    
    String in;
    //Metodo que recorre el árbol in orden
    public String impIn_Orden(Nodo b){
        if (b != null){
            impIn_Orden(b.izquierdo);
            in += "Valor["+b.valor+"]---F.E["+b.facEquilibrio+"]---Pf["+b.profundidad+"]<br>" ;
            impIn_Orden(b.derecho);
        }
        return in;
    }
    
    String post;
    //Metodo que recorre el árbol en post orden
    public String impPost_orden(Nodo b){
        if (b != null){
            impPost_orden(b.izquierdo);
            impPost_orden(b.derecho);
            post +=  "Valor["+b.valor+"]---F.E["+b.facEquilibrio+"]---Pf["+b.profundidad+"]<br>" ;
        }
        return post;
    }
    //Metodo que genera el archivo con el contenido del Árbol
    public void guardar(){
        try{
            String sFile = "./src/tree/arbol.txt";
            File f = new File(sFile);
            f.delete();
            f.createNewFile();
            
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(isr);
            FileWriter fw = new FileWriter("./src/tree/arbol.txt",true);
            BufferedWriter bw  = new BufferedWriter (fw);
            PrintWriter pw = new PrintWriter(bw);
            //Invoca el metodo arbolToTXT que devuelve una cadena con el arbol en post orden
            pw.println(arbolToTxt(a));
            pw.close();
        }catch (java.io.IOException ioe){
            
        }
    }
    String cadena="";
    //Devuelve cadena con el contenido del arvol en post orden
    private String arbolToTxt(Nodo b){
        if (b != null){
            cadena += "Valor["+b.valor+"] ----- Factor de Equilibrio["+b.facEquilibrio+"]"
                    + " ----- Profundidad["+b.profundidad+"] \n" ;
            arbolToTxt(b.izquierdo);
            arbolToTxt(b.derecho);
        }
        return cadena;
    }
}
//Clase nodo, unidad del árbol
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
