package pe.edu.upeu.dao;

import java.text.SimpleDateFormat;
import java.util.Date;

import pe.edu.upeu.data.AppCrud;
import pe.edu.upeu.modelo.ProductoTO;
import pe.edu.upeu.modelo.VentaDetalleTO;
import pe.edu.upeu.modelo.VentaTO;
import pe.edu.upeu.util.LeerArchivo;
import pe.edu.upeu.util.LeerTeclado;
import pe.edu.upeu.util.UtilsX;

import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiConsole;
import static org.fusesource.jansi.Ansi.Color.*;

public class VentaDao extends AppCrud{
   LeerArchivo lar;
   LeerTeclado lte=new LeerTeclado();
   UtilsX ut=new UtilsX();
   ProductoTO prodTO;
   VentaTO ventTO;
   VentaDetalleTO vdTO;
   SimpleDateFormat formato=new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
   SimpleDateFormat formatoFecha=new SimpleDateFormat("dd-MM-yyyy");    
   Ansi color=new Ansi();

    public void registroVentaGeneral() {
        VentaTO vTO=crearVenta();
        double precioTotalX=0;
        String continuaV="SI";
        do {
            VentaDetalleTO toVD=carritoVenta(vTO);

            precioTotalX=precioTotalX+toVD.getPrecioTotal();
            
            continuaV=lte.leer("", "Desea comprar algo mas? (SI=S, NO=N):");
        } while (continuaV.charAt(0)=='S');
        vTO.setPrecioTotal(precioTotalX);
        vTO.setNetoTotal(precioTotalX/1.18);
        vTO.setIgv(vTO.getNetoTotal()*0.18);
        lar=new LeerArchivo("Venta.txt");
        editarRegistro(lar, 0, vTO.getIdVenta(), vTO);        
    }

    public VentaTO crearVenta() {
        lar=new LeerArchivo("Venta.txt");
        ventTO=new VentaTO();
        ventTO.setIdVenta(generarId(lar, 0, "V", 1));
        ventTO.setDniCliente(lte.leer("", "Ingrese el DNI del cliente:"));
        ventTO.setFechaVenta(formato.format(new Date()));
        ventTO.setIgv(0.0);
        ventTO.setNetoTotal(0.0);
        ventTO.setPrecioTotal(0.0);
        lar=new LeerArchivo("Venta.txt");
        agregarContenido(lar, ventTO);
        return ventTO;
    }

    public VentaDetalleTO carritoVenta(VentaTO vTO) {
        
        vdTO=new VentaDetalleTO();
        ut.clearConsole();
        System.out.println("*************Agregar Productos a carrito de venta********");
        mostrarProductos();
        vdTO.setIdProducto(lte.leer("", "Ingrese el ID del Producto:"));
        vdTO.setIdVenta(vTO.getIdVenta());
        lar=new LeerArchivo("VentaDetalle.txt");
        vdTO.setIdVentaDetalle(generarId(lar, 0, "DV", 2));
        lar=new LeerArchivo("Producto.txt");
        Object[][] dataP=buscarContenido(lar, 0, vdTO.getIdProducto());
        double porcentUtil=Double.parseDouble(String.valueOf(dataP[0][5]));
        double punit=Double.parseDouble(String.valueOf(dataP[0][4]));
        vdTO.setPorcentUtil(porcentUtil);
        vdTO.setPrecioUnit(punit+punit*porcentUtil);
        vdTO.setCantidad(lte.leer(0.0, "Ingrese una cantidad:"));
        vdTO.setPrecioTotal(vdTO.getCantidad()*vdTO.getPrecioUnit());
        lar=new LeerArchivo("VentaDetalle.txt");
        agregarContenido(lar, vdTO);
        return vdTO;
    }

    public void mostrarProductos() {
        lar=new LeerArchivo("Producto.txt");
        Object[][] dataP=listarContenido(lar);
        for (int i = 0; i < dataP.length; i++) {
            System.out.print(dataP[i][0]+"="+dataP[i][1]+
            "(P.Unit S/.:"+dataP[i][4]+", Stock:"+dataP[i][6]+"); ");
        }
        System.out.println("");
    }

    public void reporteVentasPorFechas() {
        AnsiConsole.systemInstall();
        System.out.println("***************Reporte de Ventas por fechas*******************");
        String fechaInit=lte.leer("", "Ingrese fecha de inicio (dd-MM-yyyy):");
        String fechaFin=lte.leer("", "Ingrese fecha de Finalizacion (dd-MM-yyyy):");
        ut.clearConsole();
        lar=new LeerArchivo("Venta.txt");

        Object[][] dataV=listarContenido(lar);
        int cantRegVent=0;
        try {
            for (int i = 0; i < dataV.length; i++) {
                String[] fechaVent=dataV[i][2].toString().split(" ");
                if( (fechaVent[0].toString().equals(fechaInit) || formatoFecha.parse(fechaVent[0])
                .after(formatoFecha.parse(fechaInit)) ) && 
                (formatoFecha.parse(fechaVent[0]).before(formatoFecha.parse(fechaFin)) 
                || fechaVent[0].toString().equals(fechaFin) ) ){
                    cantRegVent++;
                }
            }
            Object[][] dataReal=new Object[cantRegVent][dataV[0].length];
            int indexFilaX=0;
            double netoTotalX=0, igvX=0, preciototalX=0;
            for (int i = 0; i < dataV.length; i++) {
                String[] fechaVent=dataV[i][2].toString().split(" ");
                if( (fechaVent[0].toString().equals(fechaInit) || formatoFecha.parse(fechaVent[0])
                .after(formatoFecha.parse(fechaInit)) ) && 
                (formatoFecha.parse(fechaVent[0]).before(formatoFecha.parse(fechaFin)) 
                || fechaVent[0].toString().equals(fechaFin))){
                   for (int j = 0; j < dataV[0].length; j++) {
                    dataReal[indexFilaX][j]=dataV[i][j];
                    if (j==3) { netoTotalX+=Double.parseDouble(String.valueOf(dataV[i][j])); }
                    if (j==4) { igvX+=Double.parseDouble(String.valueOf(dataV[i][j])); }
                    if (j==5) { preciototalX+=Double.parseDouble(String.valueOf(dataV[i][j])); }
                   }
                   indexFilaX++;
                }
            }            

            ut.clearConsole();
            System.out.println("********************Reporte de ventes*************************");
            System.out.println("*********Entre "+fechaInit+" a "+fechaFin+"  **********");
            ut.pintarLine('H', 40);
            ut.pintarTextHeadBody('B', 3, "ID,DNI Cli.,F.Venta,Neto S/.,IGV,P. Total S/.");
            ut.pintarLine('H', 40);            
            for (Object[] objects : dataReal) {
                String datacont=objects[0]+","+objects[1]+","+
                objects[2]+","+objects[3]+","+objects[4]+","+objects[5];
                ut.pintarTextHeadBody('B', 3, datacont);
            }
            ut.pintarLine('H', 40);        
         
            System.out.println(color.render("@|red Neto Total:S/. |@ @|green "+(Math.round(netoTotalX*100.0)/100.0)+
            "|@ | @|red IGV: S/.|@ @|green "+(Math.round(igvX*100.0)/100.0)+"|@  | @|red Monto total: S/. |@ @|green "+
            (Math.round(preciototalX*100.0)/100.0)+"|@"));
            
            ut.pintarLine('H', 40);
            
            
            //System.out.println( color.bg(GREEN).a("Hello").fg(GREEN).a(" World").reset() );

            //System.out.println(color.render("@|red Hello"+igvX+" |@ @|green World|@") );


        } catch (Exception e) {      }
    }


}
