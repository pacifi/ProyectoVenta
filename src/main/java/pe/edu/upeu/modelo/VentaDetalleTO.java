package pe.edu.upeu.modelo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VentaDetalleTO {
    //idVentaDetalle	idVenta	idProducto	precioUnit	porcentUtil	cantidad	precioTotal
    public String idVentaDetalle, idVenta, idProducto, nombreProducto;
    public double precioUnit, porcentUtil, cantidad, precioTotal;

    public String getIdVentaDetalle() {
        return idVentaDetalle;
    }

    public void setIdVentaDetalle(String idVentaDetalle) {
        this.idVentaDetalle = idVentaDetalle;
    }

    public String getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(String idVenta) {
        this.idVenta = idVenta;
    }

    public String getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public double getPrecioUnit() {
        return precioUnit;
    }

    public void setPrecioUnit(double precioUnit) {
        this.precioUnit = precioUnit;
    }

    public double getPorcentUtil() {
        return porcentUtil;
    }

    public void setPorcentUtil(double porcentUtil) {
        this.porcentUtil = porcentUtil;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(double precioTotal) {
        this.precioTotal = precioTotal;
    }
}
