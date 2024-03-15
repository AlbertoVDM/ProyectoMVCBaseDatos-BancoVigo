package modelo;

import java.sql.Date;
import java.util.Set;

public class CuentasCliente implements java.io.Serializable {

	private String ccDNI;
	private Integer ccCodCuenta;

	public CuentasCliente() {
	}

	public CuentasCliente(String ccDNI, Integer ccCodCuenta) {
		this.ccDNI = ccDNI;
		this.ccCodCuenta = ccCodCuenta;
	}

	public String getCcDNI() {
		return ccDNI;
	}

	public void setCcDNI(String ccDNI) {
		this.ccDNI = ccDNI;
	}

	public Integer getCcCodCuenta() {
		return ccCodCuenta;
	}

	public void setCcCodCuenta(Integer ccCodCuenta) {
		this.ccCodCuenta = ccCodCuenta;
	}


}
