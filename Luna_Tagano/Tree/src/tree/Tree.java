package tree;

public class Tree {
    
    public static MyTree miArbol = new MyTree();

    public static void main(String[] args) {
        
        miArbol.insertar(5);
        
        Principal win = new Principal();
        win.setTitle("Trabajando con Arboles");
        win.setVisible(true);
    }
    
}
