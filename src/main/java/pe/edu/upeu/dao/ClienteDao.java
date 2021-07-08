package pe.edu.upeu.dao;

import pe.edu.upeu.data.AppCrud;
import pe.edu.upeu.modelo.Cliente;
import pe.edu.upeu.util.LeerArchivo;
import pe.edu.upeu.util.LeerTeclado;

public class ClienteDao extends AppCrud {

    Cliente cliente;
    LeerArchivo lar;
    LeerTeclado lte = new LeerTeclado();

    public Object[][] registrarCliente() {
        cliente = new Cliente();
        lar = new LeerArchivo("Cliente.txt");
        cliente.setIdCliente(generarId(lar, 0, "Cl", 1));
        cliente.setNombre(lte.leer("", "Ingrese nombre Cliente:"));
        cliente.setApellidos(lte.leer("", "Ingrese apellidos Cliente:"));
        cliente.setNumeroDocumento(lte.leer("", "Ingrese Número Documento  del Cliente:"));
        cliente.setDireccion(lte.leer("", "Ingrese Dirección Cliente:"));

        return agregarContenido(lar, cliente);
    }

    public void reportarCliente() {
        lar = new LeerArchivo("Cliente.txt");
        imprimirLista(listarContenido(lar));
    }

    public Cliente clientebyNumeroDocumento(String numeroDocumento) {
        lar = new LeerArchivo("Cliente.txt");
        Cliente cliente = new Cliente();
        Object[][] dataP = buscarContenido(lar, 3, numeroDocumento);
        try {

            cliente.setIdCliente(String.valueOf(dataP[0][0]));
            cliente.setNombre(String.valueOf(dataP[0][1]));
            cliente.setApellidos(String.valueOf(dataP[0][2]));
            cliente.setNumeroDocumento(String.valueOf(dataP[0][3]));
            cliente.setDireccion(String.valueOf(dataP[0][4]));
            
            return cliente;
        } catch (Exception e) {

            return null;
        }
    }
}