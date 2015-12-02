package tree;
//Inicia clase
public class Tree {
    //Declara variable publica para trabajar con el arbol
    public static MyTree miArbol = new MyTree();
// Metodo main (Inicia el programa)
    public static void main(String[] args) {
        //Inserta un primer nodo al árbol
        miArbol.insertar(5);
        //Invoca a la interfaz gráfica
        Principal win = new Principal();
        win.setTitle("Trabajando con Arboles");
        win.setVisible(true);
    }
    
}
