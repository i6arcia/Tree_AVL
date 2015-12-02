package arbol;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;

public class archivo {
    String sTxt="";
    
    public void save(Nodo a) throws IOException{
        //Ruta en donde se guarda el archivo txt
        String ruta = "./src/arbol/arbol_post_orden.txt";
        File archivo = new File(ruta);
        BufferedWriter bw;
        //Si el archivo ya existe agrega si no lo crea y agrega
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
//devuelve la cadena con el contenido del árbol en post orden    
    private String txtPost(Nodo p){
        if (p == null)
            ;
        else{
            //Contenido del nodo
            sTxt += "--- DATO-> "+p.dato+" ----- Factor de equilibrio-> " + p.facE +" ----- Profundidad -> "+p.prof+"\n";
            //Invoca recursivamente con el nodo izquierdo
            txtPost(p.left);
            //Invoca recursivamente con el nodo derecho
            txtPost(p.right);
        }
        return sTxt;
    }
}