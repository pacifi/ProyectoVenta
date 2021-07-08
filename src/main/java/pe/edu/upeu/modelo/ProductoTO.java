package pe.edu.upeu.modelo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductoTO {
    public String idProducto, nombre, idCateg, unidaMed;
    public double precioUnit, porcentUtil, stock;

    public String getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIdCateg() {
        return idCateg;
    }

    public void setIdCateg(String idCateg) {
        this.idCateg = idCateg;
    }

    public String getUnidaMed() {
        return unidaMed;
    }

    public void setUnidaMed(String unidaMed) {
        this.unidaMed = unidaMed;
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

    public double getStock() {
        return stock;
    }

    public void setStock(double stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "ProductoTO{" +
                "idProducto='" + idProducto + '\'' +
                ", nombre='" + nombre + '\'' +
                ", idCateg='" + idCateg + '\'' +
                ", unidaMed='" + unidaMed + '\'' +
                ", precioUnit=" + precioUnit +
                ", porcentUtil=" + porcentUtil +
                ", stock=" + stock +
                '}';
    }
}
