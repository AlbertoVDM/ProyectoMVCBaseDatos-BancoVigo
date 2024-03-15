package modelo;

import java.sql.Date;


public class Transacciones implements java.io.Serializable {

	private Integer id;
	private Integer trCodCuenta;
	private Date trFechaTransaccion;
	private Character trTipo;
	private Integer trCantidad;

	public Transacciones() {
	}

	public Transacciones(Integer trCodCuenta, Date trFechaTransaccion, Character trTipo, Integer trCantidad) {
		this.trCodCuenta = trCodCuenta;
		this.trFechaTransaccion = trFechaTransaccion;
		this.trTipo = trTipo;
		this.trCantidad = trCantidad;
	}

	public Integer getId() {
		return id;
	}

	public void setId (Integer id) {
		this.id = id;
	}

	public Integer getTrCodCuenta() {
		return trCodCuenta;
	}

	public void setTrCodCuenta(Integer trCodCuenta) {
		this.trCodCuenta = trCodCuenta;
	}

	public Date getTrFechaTransaccion() {
		return trFechaTransaccion;
	}

	public void setTrFechaTransaccion(Date trFechaTransaccion) {
		this.trFechaTransaccion = trFechaTransaccion;
	}

	public Character getTrTipo() {
		return trTipo;
	}

	public void setTrTipo(Character trTipo) {
		this.trTipo = trTipo;
	}

	public Integer getTrCantidad() {
		return trCantidad;
	}

	public void setTrCantidad(Integer trCantidad) {
		this.trCantidad = trCantidad;
	}

	

}
