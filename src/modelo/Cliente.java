package modelo;
// Generated 1 feb 2024, 13:25:00 by Hibernate Tools 6.3.1.Final

//import java.util.HashSet;
import java.util.Set;

import controlador.Controlador;


public class Cliente implements java.io.Serializable {
	 
	private String clDni;
	private String clNombre;
	private String clApellido;
	private Integer clTelefono;
	//private Set cuentas = new HashSet(0);

	public Cliente() {
	}

	public Cliente(String clDni, String clNombre, String clApellido, Integer clTelefono) {
		this.clDni = clDni;
		this.clNombre = clNombre;
		this.clApellido = clApellido;
		this.clTelefono = clTelefono;
		//this.cuentas = cuentas;
	}

	public String getClDni() {
		return this.clDni;
	}

	public void setClDni(String clDni) {
		this.clDni = clDni;
	}

	public String getClNombre() {
		return this.clNombre;
	}

	public void setClNombre(String clNombre) {
		this.clNombre = clNombre;
	}

	public String getClApellido() {
		return this.clApellido;
	}

	public void setClApellido(String clApellido) {
		this.clApellido = clApellido;
	}

	public Integer getClTelefono() {
		return this.clTelefono;
	}

	public void setClTelefono(Integer clTelefono) {
		this.clTelefono = clTelefono;
	}

//	public Set getCuentas() {
//		return this.cuentas;
//	}
//
//	public void setCuentas(Set cuentas) {
//		this.cuentas = cuentas;
//	}

	public void setMiCoordinador(Controlador miControlador) {
		// TODO Auto-generated method stub
		
	}
	@Override
    public String toString() {
        return clNombre + " " + clApellido;
    }

}
