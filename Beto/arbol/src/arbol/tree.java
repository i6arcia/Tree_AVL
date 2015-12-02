package arbol;

import javax.swing.JOptionPane;

public class tree {
    private Nodo a;
    String sTxt="", sPre="", sOrd="", sPost="";
    
    public void insert(int dato){
        a = insert(dato, a);
    }
    
    private Nodo insert(int d, Nodo p){
        if (p == null)
            p = new Nodo(d, null, null);
        else{
            if (d == p.dato)
                return p;
            else if (d < p.dato){
                p.left = insert(d, p.left);
            }else if (d > p.dato){
                p.right = insert (d, p.right);
            }
        }
        p.prof = (profNodo(p.left)> profNodo(p.right) ? profNodo(p.left): profNodo(p.right))+1;
        p.facE = facEqNodo(p.left, p.right);
        if (p.facE > 1 || p.facE < -1){
            JOptionPane.showMessageDialog(null, "El árbol quedaria desiquilibrado \n Nodo no agregado");
            eliminarHoja(d, p);
        }
        return p;
    }
    
    private int profNodo(Nodo p){
        return p == null ? -1 : p.prof;
    }
    private int facEqNodo(Nodo l, Nodo r){
        int fEleft, fEright;
        fEleft = l == null ? -1 : l.prof;
        fEright = r == null ? -1 : r.prof;
        return fEright- fEleft;
    }
    private Nodo eliminarHoja (int dato, Nodo p){
        if (p == null)
            ;
        else{
            //Eliminar nodo raiz
            if (dato == p.dato && p.left == null && p.right == null){
                p = null;
                return p;
            }
            else if (dato < p.dato){
                if (p.left.dato == dato){
                    p.left = null;
                    p.prof = (profNodo(p.left)> profNodo(p.right) ? profNodo(p.left): profNodo(p.right))+1;
                    p.facE = facEqNodo(p.left, p.right);
                }
                else
                    eliminarHoja(dato, p.left);
            }
            else if (dato > p.dato){
                if (p.right.dato == dato){
                    p.right= null;
                    p.prof = (profNodo(p.left)> profNodo(p.right) ? profNodo(p.left): profNodo(p.right))+1;
                    p.facE = facEqNodo(p.left, p.right);
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
            sPre = "<html>";
            return mostrarPre(a);
        }else if (val == 1){
            sOrd = "<html>";
            return mostrarOrd(a);
        }else if (val ==2){
            sPost = "<html>";
            mostrarPost(a);
        }
        return "";
    }
    public String mostrarPre(Nodo p){
        if (p == null)
            ;
        else{
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
}
