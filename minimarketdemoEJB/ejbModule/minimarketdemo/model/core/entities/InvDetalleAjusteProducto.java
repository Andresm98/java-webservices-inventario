package minimarketdemo.model.core.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the inv_detalle_ajuste_producto database table.
 * 
 */
@Entity
@Table(name="inv_detalle_ajuste_producto")
@NamedQuery(name="InvDetalleAjusteProducto.findAll", query="SELECT i FROM InvDetalleAjusteProducto i")
public class InvDetalleAjusteProducto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_detalle_ajuste_producto", unique=true, nullable=false)
	private Integer idDetalleAjusteProducto;

	@Column(length=100)
	private String descripcion;

	//bi-directional many-to-one association to InvAjusteProducto
	@ManyToOne
	@JoinColumn(name="id_ajuste")
	private InvAjusteProducto invAjusteProducto;

	//bi-directional many-to-one association to InvProducto
	@ManyToOne
	@JoinColumn(name="id_producto")
	private InvProducto invProducto;

	public InvDetalleAjusteProducto() {
	}

	public Integer getIdDetalleAjusteProducto() {
		return this.idDetalleAjusteProducto;
	}

	public void setIdDetalleAjusteProducto(Integer idDetalleAjusteProducto) {
		this.idDetalleAjusteProducto = idDetalleAjusteProducto;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public InvAjusteProducto getInvAjusteProducto() {
		return this.invAjusteProducto;
	}

	public void setInvAjusteProducto(InvAjusteProducto invAjusteProducto) {
		this.invAjusteProducto = invAjusteProducto;
	}

	public InvProducto getInvProducto() {
		return this.invProducto;
	}

	public void setInvProducto(InvProducto invProducto) {
		this.invProducto = invProducto;
	}

}