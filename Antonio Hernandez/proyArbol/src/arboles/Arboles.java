package arboles;

import javax.swing.JOptionPane;

public class Arboles {
    Nodos arbol;
//Método publica que agrega un valor    
    public void insertarNodo(int val){
        arbol = insertar(val, arbol);
    }
    
    //Método privado que agrega un valor al árbol
    private Nodos insertar (int val, Nodos n){
        //Si el árbol está vacio agrega un primer nodo
        if (n == null)
            n = new Nodos(val, null, null);
        else{
            //Si el nodo ya existe no hace algo.
            if (val == n.dato)
                return n;
            //Si el nodo es menor con recursividad invoca con el nodo izquierdo
            else if (val < n.dato){
                n.left = insertar(val, n.left);
            //Si el dato es mayor invoca recursivamente con el nodo derecho
            }else if (val > n.dato){
                n.right = insertar (val, n.right);
            }
        }
        //Obtiene el factor de quilibrio y la profundidad del nodo
        n = getExtras(n);
        // en caso de que el nodo agregado desequilibre el árbol no se agrega y el borrado
        if (n.facEqui > 1 || n.facEqui < -1){
            JOptionPane.showMessageDialog(null, "Dato Desequilibra el Árbol");
            borrar(val, n);
        }
        return n;
    }
    //Método que optiene factor de equilibrio y altura del nodo
    public Nodos getExtras (Nodos n){
        int max, maxIzq, maxDer;
        maxIzq = n.left == null ? -1 : n.left.altura;
        maxDer = n.right == null ? -1 : n.right.altura;
        max = maxIzq > maxDer ? maxIzq : maxDer;
        n.altura = max+1;
        
        int feLeft, feRight;
        feLeft = n.left == null ? -1: n.left.altura;
        feRight = n.right == null ? -1: n.right.altura;
        n.facEqui = feRight-feLeft;
        return n;
    }
    //Elimina un nodo hoja para cuando el arbol está desequilibrado
    private Nodos borrar (int val, Nodos n){
        if (n!= null){
            if (val == n.dato && n.left==null && n.right==null){
                n = null;
                return n;
            }else if (val < n.dato){
                if (n.left.dato == val){
                    n.left = null;
                    n = getExtras(n);
                }
                else
                    borrar(val, n.left);
            }
            else if (val > n.dato){
                if (n.right.dato == val){
                    n.right= null;
                    n= getExtras(n);
                }
                else
                    borrar(val, n.right);
            }
        }
        return n;
    }
    //Devuelve el nodo raiz del arbol
    public Nodos getNodo(){
        return arbol;
    }
    //Devuelve el factor de equilibrio de un nodo
    public int getFactorEquilibrio(Nodos n){
        if (n != null){
            int derecho, izquierdo;
            izquierdo = n.left == null ? -1: n.left.altura;
            derecho = n.right == null ? -1: n.right.altura;
            return derecho-izquierdo;
        }else
            return 0;
    }
    
    
    
    private String cadena;
    //Devuelve una cadena con el recorrido del arbol segun la opcion
    public String showTree(int opt){
        cadena= "";
        if (opt ==0)
            cadena = pre(arbol);
        else if (opt == 1)
            cadena = in(arbol);
        else if (opt == 2)
            cadena = post(arbol);
        return cadena;
    }
    //recorrido del arbol en pre orden
    public String pre(Nodos n){
        if (n != null){
            cadena += "Valor del nodo "+n.dato+" _-_ Fac. Equilibrio "+n.facEqui+" _-_ Altura"+n.altura+"\n" ;
            pre(n.left);
            pre(n.right);
        }
        return cadena;
    }
    //Recorrido del arbol in orden
    public String in(Nodos n){
        if (n != null){
            
            in(n.left);
            cadena += "Valor del nodo "+n.dato+" _-_ Fac. Equilibrio "+n.facEqui+" _-_ Altura"+n.altura+"\n" ;
            in(n.right);
        }
        return cadena;
    }
    //Recorrido del arbol post orden
    public String post(Nodos n){
        if (n != null){
            
            post(n.left);
            post(n.right);
            cadena += "Valor del nodo "+n.dato+" _-_ Fac. Equilibrio "+n.facEqui+" _-_ Altura"+n.altura+"\n" ;
        }
        return cadena;
    }
}
