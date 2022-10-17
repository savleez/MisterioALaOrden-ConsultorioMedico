package com.MisterioALaOrden.consultorio.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "usuario")
public class Usuario implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2083916305807004865L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "cedula", length = 20, nullable = false, unique = true)
	private String cedula;
	
	@Column(name = "password", length = 45, nullable = false)
	private String password;
	
	@Column(name = "fechaHoraCreacion")
	private Date fechaHoraCreacion;
	
	@Column(name = "tipo", columnDefinition = "nvarchar(45) default 'user'")
	private String tipo;
	
	@Column(name = "estado", columnDefinition = "nvarchar(45) default 'activo'")
	private String estado;

	
	public Usuario() {
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public Date getFechaHoraCreacion() {
		return fechaHoraCreacion;
	}


	public void setFechaHoraCreacion(Date fechaHoraCreacion) {
		this.fechaHoraCreacion = fechaHoraCreacion;
	}


	public String getTipo() {
		return tipo;
	}


	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


	public String getEstado() {
		return estado;
	}


	public void setEstado(String estado) {
		this.estado = estado;
	}


	public String getCedula() {
		return cedula;
	}


	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	
	
	
}
