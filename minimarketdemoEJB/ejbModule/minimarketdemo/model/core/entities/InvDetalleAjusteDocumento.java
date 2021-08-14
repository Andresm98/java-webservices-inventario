package minimarketdemo.model.core.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the inv_detalle_ajuste_documento database table.
 * 
 */
@Entity
@Table(name="inv_detalle_ajuste_documento")
@NamedQuery(name="InvDetalleAjusteDocumento.findAll", query="SELECT i FROM InvDetalleAjusteDocumento i")
public class InvDetalleAjusteDocumento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_detalle_ajuste_documento", unique=true, nullable=false)
	private Integer idDetalleAjusteDocumento;

	@Column(name="fecha_impresion")
	private Timestamp fechaImpresion;

	//bi-directional many-to-one association to InvAjusteProducto
	@ManyToOne
	@JoinColumn(name="id_ajuste")
	private InvAjusteProducto invAjusteProducto;

	public InvDetalleAjusteDocumento() {
	}

	public Integer getIdDetalleAjusteDocumento() {
		return this.idDetalleAjusteDocumento;
	}

	public void setIdDetalleAjusteDocumento(Integer idDetalleAjusteDocumento) {
		this.idDetalleAjusteDocumento = idDetalleAjusteDocumento;
	}

	public Timestamp getFechaImpresion() {
		return this.fechaImpresion;
	}

	public void setFechaImpresion(Timestamp fechaImpresion) {
		this.fechaImpresion = fechaImpresion;
	}

	public InvAjusteProducto getInvAjusteProducto() {
		return this.invAjusteProducto;
	}

	public void setInvAjusteProducto(InvAjusteProducto invAjusteProducto) {
		this.invAjusteProducto = invAjusteProducto;
	}

}