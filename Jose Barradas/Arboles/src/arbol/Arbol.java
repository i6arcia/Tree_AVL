package arbol;

import javax.swing.JOptionPane;


public class Arbol {
    private Nodo n;
    
    public void inserta(int valor){
        n = inserta(valor, n);
    }
    
    private Nodo inserta(int valor, Nodo m){
        if (m != null){
            if (valor == m.val)
                return m;
            else if (valor < m.val){
                m.izq = inserta(valor, m.izq);
            }else if (valor > m.val){
                m.der = inserta (valor, m.der);
            }
        }else
            m = new Nodo(valor, null, null);
        
        m = getFactores(m);
        if (m.equilibrio > 1 || m.equilibrio < -1){
            JOptionPane.showOptionDialog(null, "El valor que desea insertar desequilibra el Arbol", "Error al insertar",
                        JOptionPane.INFORMATION_MESSAGE, JOptionPane.INFORMATION_MESSAGE, null, new Object[]{"OK"}, "OK");
            equilibrar(valor, m);
        }
        return m;
    }
    
    private Nodo getFactores(Nodo m){
        int alturaMax, alturaIzq, alturaDer;
        alturaIzq = m.izq == null ? -1 : m.izq.altura;
        alturaDer = m.der == null ? -1 : m.der.altura;
        alturaMax = alturaIzq > alturaDer ? alturaIzq : alturaDer;
        m.altura = alturaMax+1;
        
        int equilibrioD, equilibrioI;
        equilibrioI = m.izq == null ? -1: m.izq.altura;
        equilibrioD = m.der == null ? -1: m.der.altura;
        m.equilibrio = equilibrioD-equilibrioI;
        return m;
    }    
    
    private Nodo equilibrar (int val, Nodo m){
        if (m!= null){
            if (val == m.val && m.izq==null && m.der==null){
                m = null;
                return m;
            }else if (val < m.val){
                if (m.izq.val == val){
                    m.izq = null;
                    m = getFactores(m);
                }
                else
                    equilibrar(val, m.izq);
            }
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
    
    public Nodo getNodo(){
        return n;
    }
    
    public int puntoDeEquilibrio (Nodo m){
        if (m != null){
            int equilibrioD, equilibrioI;
            equilibrioI = m.izq == null ? -1: m.izq.altura;
            equilibrioD = m.der == null ? -1: m.der.altura;
            return equilibrioD-equilibrioI;
        }else
            return 0;
    }
    private String sArbol;
    public String printPant(int opt){
        String sCadena ="null";
        sArbol="";
        
        switch(opt){
            case 0:
                sCadena = pre_Orden(n);
                break;
            case 1:
                sCadena = in_Orden(n);
                break;
            case 2:
                sCadena = post_orden(n);
                break;
        }
        return sCadena;
    }
    
    public String pre_Orden(Nodo m){
        if (m != null){
            sArbol += "Nodo["+m.val+"] | Fac Equil["+m.equilibrio+"] | Altura["+m.altura+"]\n" ;
            pre_Orden(m.izq);
            pre_Orden(m.der);
        }
        return sArbol;
    }
    public String in_Orden(Nodo m){
        if (m != null){
            in_Orden(m.izq);
            sArbol += "Nodo["+m.val+"] | Fac Equil["+m.equilibrio+"] | Altura["+m.altura+"]\n" ;
            in_Orden(m.der);
        }
        return sArbol;
    }
    
    public String post_orden(Nodo m){
        if (m != null){
            post_orden(m.izq);
            post_orden(m.der);
            sArbol += "Nodo["+m.val+"] | Fac Equil["+m.equilibrio+"] | Altura["+m.altura+"]\n" ;
        }
        return sArbol;
    }
    
    /*public void guardar(){
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
            pw.println(arbolToTxt(a));
            pw.close();
        }catch (java.io.IOException ioe){
            
        }
    }
    String cadena="";
    private String arbolToTxt(Nodo b){
        if (b != null){
            cadena += "Valor["+b.valor+"] ----- Factor de Equilibrio["+b.facEquilibrio+"]"
                    + " ----- Profundidad["+b.profundidad+"] \n" ;
            arbolToTxt(b.izquierdo);
            arbolToTxt(b.derecho);
        }
        return cadena;
    }*/
}


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