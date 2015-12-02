package arbol;

import javax.swing.JOptionPane;

public class tree {
    //Inidad minima del árbol
    private Nodo a;
    //Almacenan el contenido del árbol cuando se solicite
    String sTxt="", sPre="", sOrd="", sPost="";
    //Metodo publico para insera un nuevo nodo
    public void insert(int dato){
        a = insert(dato, a);
    }
    //Metodo para insertar un nodo a la raiz.
    private Nodo insert(int d, Nodo p){
        //Si la raiz está vacia crea el nodo
        if (p == null)
            p = new Nodo(d, null, null);
        else{
            //Si el dato ya existe no hace algo
            if (d == p.dato)
                return p;
            //En caso contrario compara e invoca recursivamente con el nodo izquierdo o derecho segun corresponda.
            else if (d < p.dato){
                p.left = insert(d, p.left);
            }else if (d > p.dato){
                p.right = insert (d, p.right);
            }
        }
        //Optiene profundidad
        p.prof = (profNodo(p.left)> profNodo(p.right) ? profNodo(p.left): profNodo(p.right))+1;
        //Optiene factor de equilibrio
        p.facE = facEqNodo(p);
        //Si el dato que se quiere agregar desequilibra el arbol, se elimina
        if (p.facE > 1 || p.facE < -1){
            JOptionPane.showMessageDialog(null, "El árbol quedaria desiquilibrado \n Nodo no agregado");
            eliminarHoja(d, p);
        }
        return p;
    }
    
    //Metodo que optiene la profundidad del nodo
    private int profNodo(Nodo p){
        // si el nodo está vacio entonces la profundidad es -1 en caso contrario el valor de profundidad que tenga.
        return p == null ? -1 : p.prof;
    }
    
    //Metodo que optiene el factor de equilibrio de un nodo cualquiera
    private int facEqNodo(Nodo raiz){
        int fEleft, fEright;
        fEleft = raiz.left == null ? -1 : raiz.left.prof;
        fEright = raiz.right == null ? -1 : raiz.right.prof;
        return fEright- fEleft;
    }
    //Factor de equilibrio de la raiz, para mostrar con el boton Factor de equilibrio (GUI)
    public int facEqRaiz(){
        if (a!= null){
            int fEleft, fEright;
            fEleft = a.left == null ? -1 : a.left.prof;
            fEright = a.right == null ? -1 : a.right.prof;
            return fEright- fEleft;
        }else 
            return 0;
    }
    //Metodo que elimina nodo hoja unicamente cuando éste desequilibra el árbol
    private Nodo eliminarHoja (int dato, Nodo p){
        if (p == null)
            ;
        else{
            //Eliminar nodo raiz
            if (dato == p.dato && p.left == null && p.right == null){
                p = null;
                return p;
            }
            //si el dato es menos a la raiz, se invoca recursivamente con el nodo izquierdo si es igual solo se elimina el nodo
            else if (dato < p.dato){
                if (p.left.dato == dato){
                    p.left = null;
                    p.prof = (profNodo(p.left)> profNodo(p.right) ? profNodo(p.left): profNodo(p.right))+1;
                    p.facE = facEqNodo(p);
                }
                else
                    eliminarHoja(dato, p.left);
            }
            //Si el dato es mayo, recursivamente con el nodo derecho. Si es igual se elimina
            else if (dato > p.dato){
                if (p.right.dato == dato){
                    p.right= null;
                    p.prof = (profNodo(p.left)> profNodo(p.right) ? profNodo(p.left): profNodo(p.right))+1;
                    p.facE = facEqNodo(p);
                }
                else
                    eliminarHoja(dato, p.right);
            }
        }
        return p;
    }
    
    //////////////////////////////////
    //Mostrar Árbol
    /////////////////////////////////
    public String imprimir(int val){
        if (val == 0){
            //Almacena cadena con el contenido del arbol
            //Agrega codigo html para poder insertar saltos de linea al mostrarlo en un label
            sPre = "<html>";
            return mostrarPre(a);
        }else if (val == 1){
            sOrd = "<html>";
            return mostrarOrd(a);
        }else if (val ==2){
            sPost = "<html>";
            return mostrarPost(a);
        }
        return "";
    }
    public String mostrarPre(Nodo p){
        if (p == null)
            ;
        else{
            //Contenido de un nodo y al final un salto de linea
            sPre += "dato -> [ "+ p.dato+" ]<br>" ;
            mostrarPre(p.left);
            mostrarPre(p.right);
        }
        return sPre;
    }
    public String mostrarOrd(Nodo p){
        if (p == null)
            ;
        else{
            mostrarOrd(p.left);
            sOrd += "Dato -> [ "+p.dato+" ]<br>" ;
            mostrarOrd(p.right);
        }
        return sOrd;
    }
    public String mostrarPost(Nodo p){
        if (p == null)
            ;
        else{
            mostrarPost(p.left);
            mostrarPost(p.right);
            sPost +=  "Dato -> [ "+p.dato+" ]<br>" ;
        }
        return sPost;
    }
    //Obtine el nodo raiz
    public Nodo getArbol (){
        return a;
    }
}
