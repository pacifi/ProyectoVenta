package pe.edu.upeu.dao;

import pe.edu.upeu.data.AppCrud;
import pe.edu.upeu.modelo.Articulo;
import pe.edu.upeu.util.LeerArchivo;
import pe.edu.upeu.util.LeerTeclado;

public class ArticuloDao extends AppCrud {

    Articulo articulo;
    LeerArchivo lar;
    LeerTeclado lte = new LeerTeclado();

    public Object[][] registrarArticulo() {
        articulo = new Articulo();
        lar = new LeerArchivo("Articulo.txt");
        articulo.setIdArticulo(generarId(lar, 0, "A", 1));
        articulo.setNombre(lte.leer("", "Ingrese nombre Articulo:"));
        articulo.setPrecio(lte.leer(0.0, "Ingrese nombre Precio:"));
        return agregarContenido(lar, articulo);
    }

    public void reportarArticulo() {
        lar = new LeerArchivo("Articulo.txt");
        imprimirLista(listarContenido(lar));
    }

    public Articulo buscarArticulo(String idArticulo) {
        lar = new LeerArchivo("Articulo.txt");
        Object[][] dataP = buscarContenido(lar, 0, idArticulo);

        Articulo articulo = new Articulo();
        articulo.setIdArticulo(String.valueOf(dataP[0][0]));
        articulo.setNombre(String.valueOf(dataP[0][1]));
        articulo.setPrecio(Double.valueOf(String.valueOf(dataP[0][2])));
        return articulo;

    }

}