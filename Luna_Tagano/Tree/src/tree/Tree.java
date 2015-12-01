package tree;

public class Tree {

    public static void main(String[] args) {
        
        MyTree miArbol = new MyTree();
        
        miArbol.insertar(5);
        miArbol.insertar(3);
        
        Principal win = new Principal();
        win.setTitle("Trabajando con Arboles");
        win.setVisible(true);
    }
    
}
