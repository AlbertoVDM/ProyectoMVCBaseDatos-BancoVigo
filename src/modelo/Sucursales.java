package modelo;

import java.math.BigDecimal;

public class Sucursales implements java.io.Serializable {

	private int suCodSucursal;
	private String suCiudad;
	private BigDecimal suActivo;

	public Sucursales() {
	}

	public Sucursales(int suCodSucursal, String suCiudad, BigDecimal suActivo) {
		this.suCodSucursal = suCodSucursal;
		this.suCiudad = suCiudad;
		this.suActivo = suActivo;
	}

	public int getSuCodSucursal() {
		return this.suCodSucursal;
	}

	public void setSuCodSucursal(int suCodSucursal) {
		this.suCodSucursal = suCodSucursal;
	}

	public String getSuCiudad() {
		return this.suCiudad;
	}

	public void setSuCiudad(String suCiudad) {
		this.suCiudad = suCiudad;
	}

	public BigDecimal getSuActivo() {
		return this.suActivo;
	}

	public void setSuActivo(BigDecimal suActivo) {
		this.suActivo = suActivo;
	}
	@Override
    public String toString() {
        return suCodSucursal + " " + suCiudad;
    }

	


}
