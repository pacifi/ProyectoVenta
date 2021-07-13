package pe.edu.upeu.dao;

import pe.edu.upeu.data.AppCrud;
import pe.edu.upeu.modelo.VentaDetalleTO;
import pe.edu.upeu.modelo.VentaTO;
import pe.edu.upeu.util.LeerArchivo;
import pe.edu.upeu.util.LeerTeclado;
import pe.edu.upeu.util.UtilsX;

import java.util.ArrayList;
import java.util.List;

// Herencia App Crud
public class VentaDetalleDao extends AppCrud {
    LeerArchivo lar;
    LeerTeclado lte = new LeerTeclado();
    UtilsX ut = new UtilsX();


    public List<VentaDetalleTO> listVetaVentaDetallebyIdVenta(String idVenta) {
        // Busca todos los Detalles de una venta en particular

        // Cargando el txt en una variable
        lar = new LeerArchivo("VentaDetalle.txt");
        List<String> lista2 = new ArrayList();
        List<VentaDetalleTO> ventaDetalleList = new ArrayList<VentaDetalleTO>(); // Lista de retorno
        try {
            lista2 = lar.leer();
            // recorremos las filas del txt
           
            for (String dato : lista2) {
                //"DV1 V2 P1 4.016 0.004. 4.0. 16.064 null"
                String[] column = (dato.toString()).split("\t");

                //[DV1, V2, P1, 4.016, 0.004. 4.0. 16.064, null]  >>> VECTOR
                //[DV2, V2, P1, 3.506, 0.004. 4.0. 16.064, null]  >>> Vector
                if (column[1].equals(idVenta)) {
                    VentaDetalleTO ventaDetalleTO = new VentaDetalleTO();
                    ventaDetalleTO.setIdVentaDetalle(column[0]);
                    ventaDetalleTO.setIdVenta(column[1]);
                    ventaDetalleTO.setIdProducto(column[2]);
                    ventaDetalleTO.setNombreProducto(column[3]);
                    ventaDetalleTO.setPrecioUnit(Double.parseDouble(column[4]));
                    ventaDetalleTO.setPorcentUtil(Double.parseDouble(column[5]));
                    ventaDetalleTO.setCantidad(Double.parseDouble(column[6]));
                    ventaDetalleTO.setPrecioTotal(Double.parseDouble(column[7]));
                    ventaDetalleList.add(ventaDetalleTO);
                }
            }

        } catch (Exception e) {
            System.out.printf("Error" + e.getMessage());
        }

        return ventaDetalleList;
    }

    public VentaDetalleTO buscarVentaById(String idVentaDetalle) {
        // Busca una VentaDetalle por IDVENTADETALLE

        lar = new LeerArchivo("VentaDetalle.txt");
        Object[][] dataP = buscarContenido(lar, 0, idVentaDetalle);
        try {
            VentaDetalleTO ventaDetalleTO = new VentaDetalleTO();
            ventaDetalleTO.setIdVentaDetalle(String.valueOf(dataP[0][0]));
            ventaDetalleTO.setIdVenta(String.valueOf(dataP[0][1]));
            ventaDetalleTO.setIdProducto(String.valueOf(dataP[0][2]));
            ventaDetalleTO.setNombreProducto(String.valueOf(dataP[0][3]));
            ventaDetalleTO.setPrecioUnit(Double.parseDouble(String.valueOf(dataP[0][4])));
            ventaDetalleTO.setPorcentUtil(Double.parseDouble(String.valueOf(dataP[0][5])));
            ventaDetalleTO.setCantidad(Double.parseDouble(String.valueOf(dataP[0][6])));
            ventaDetalleTO.setPrecioTotal(Double.parseDouble(String.valueOf(dataP[0][7])));


            return ventaDetalleTO;
        } catch (Exception e) {

            return null;
        }


    }

}
