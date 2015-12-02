package arbol;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;

public class archivo {
    String sTxt="";
    
    public void save(Nodo a) throws IOException{
        String ruta = "./src/arbol/arbol_post_orden.txt";
        File archivo = new File(ruta);
        BufferedWriter bw;
        if (archivo.exists()){
            bw = new BufferedWriter(new FileWriter(archivo));
            bw.write(txtPost(a));
        }else{
            bw = new BufferedWriter(new FileWriter(archivo));
            bw.write(txtPost(a));
        }
        bw.close();
        JOptionPane.showMessageDialog(null, "Árbol Guardado");
    }
    
    private String txtPost(Nodo p){
        if (p == null)
            ;
        else{
            sTxt += "--- DATO-> "+p.dato+" ----- Factor de equilibrio-> " + p.facE +" ----- Profundidad -> "+p.prof+"\n";
            txtPost(p.left);
            txtPost(p.right);
        }
        return sTxt;
    }
}