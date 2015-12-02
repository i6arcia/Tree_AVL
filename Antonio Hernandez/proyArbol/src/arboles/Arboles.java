package arboles;

import javax.swing.JOptionPane;

public class Arboles {
    Nodos arbol;
    
    public void insertarNodo(int val){
        arbol = insertar(val, arbol);
    }
    private Nodos insertar (int val, Nodos n){
        if (n == null)
            n = new Nodos(val, null, null);
        else{
            if (val == n.dato)
                return n;
            else if (val < n.dato){
                n.left = insertar(val, n.left);
            }else if (val > n.dato){
                n.right = insertar (val, n.right);
            }
        }
        n = getExtras(n);
        
        if (n.facEqui > 1 || n.facEqui < -1){
            JOptionPane.showMessageDialog(null, "Dato Desequilibra el Árbol");
            borrar(val, n);
        }
        return n;
    }
    
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
    
    public Nodos getNodo(){
        return arbol;
    }
    
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
    
    public String pre(Nodos n){
        if (n != null){
            cadena += "Valor del nodo "+n.dato+" _-_ Fac. Equilibrio "+n.facEqui+" _-_ Altura"+n.altura+"\n" ;
            pre(n.left);
            pre(n.right);
        }
        return cadena;
    }
    public String in(Nodos n){
        if (n != null){
            
            in(n.left);
            cadena += "Valor del nodo "+n.dato+" _-_ Fac. Equilibrio "+n.facEqui+" _-_ Altura"+n.altura+"\n" ;
            in(n.right);
        }
        return cadena;
    }
    
    public String post(Nodos n){
        if (n != null){
            
            post(n.left);
            post(n.right);
            cadena += "Valor del nodo "+n.dato+" _-_ Fac. Equilibrio "+n.facEqui+" _-_ Altura"+n.altura+"\n" ;
        }
        return cadena;
    }
    
    /*public void archivo(){
        try{
            String ruta = "PostOrden.txt";
            File f = new File(ruta);
            BufferedWriter bw;
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
    }*/
}
