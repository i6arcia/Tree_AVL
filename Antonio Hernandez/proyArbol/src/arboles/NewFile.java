package arboles;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import javax.swing.JOptionPane;


public class NewFile {
    String cadena="";
    public void generar(Nodos n){
        cadena = "";
        try{
            String ruta = "Post.txt";
            File f = new File(ruta);
            BufferedWriter bw;
            if (f.exists()){
                bw = new BufferedWriter(new FileWriter(f));
                bw.write(post(n));
            }else{
                bw = new BufferedWriter(new FileWriter(f));
                bw.write(post(n));
            }
            bw.close();
            JOptionPane.showMessageDialog(null, "TXT Generado con exito");
        }catch (java.io.IOException ioe){
        }
    }
    public String post(Nodos n){
        if (n != null){
            post(n.left);
            post(n.right);
            cadena += "NODO "+n.dato+" _-_ Fac. Equilibrio "+n.facEqui+" _-_ Altura"+n.altura+"\n" ;
        }
        return cadena;
    }
}
