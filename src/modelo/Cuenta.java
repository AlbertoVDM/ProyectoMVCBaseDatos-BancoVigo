package modelo;

import java.sql.Date;


public class Cuenta implements java.io.Serializable {

	private Integer cuCodCuenta;
	private Integer cuCodSucursal;
	private Date cuFechaCreacion;
	private Integer cuSaldo;

	

	public Cuenta(Integer cuCodCuenta, Integer cuCodSucursal, java.util.Date fecha, Integer cuSaldo ) {
		this.cuCodCuenta = cuCodCuenta;
		this.cuCodSucursal = cuCodSucursal;
		this.cuFechaCreacion = (Date) fecha;
		this.cuSaldo = cuSaldo;
	}

	public Integer getCuCodCuenta() {
		return cuCodCuenta;
	}

	public void setCuCodCuenta(Integer cuCodCuenta) {
		this.cuCodCuenta = cuCodCuenta;
	}

	public Integer getCuCodSucursal() {
		return cuCodSucursal;
	}

	public void setCuCodSucursal(Integer cuCodSucursal) {
		this.cuCodSucursal = cuCodSucursal;
	}

	public Date getCuFechaCreacion() {
		return cuFechaCreacion;
	}

	public void setCuFechaCreacion(Date cuFechaCreacion) {
		this.cuFechaCreacion = cuFechaCreacion;
	}

	public Integer getCuSaldo() {
		return cuSaldo;
	}

	public void setCuSaldo(Integer cuSaldo) {
		this.cuSaldo = cuSaldo;
	}

	


}
