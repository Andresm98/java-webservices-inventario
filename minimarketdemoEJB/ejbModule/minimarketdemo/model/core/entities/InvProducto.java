package minimarketdemo.model.core.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the inv_producto database table.
 * 
 */
@Entity
@Table(name="inv_producto")
@NamedQuery(name="InvProducto.findAll", query="SELECT i FROM InvProducto i")
public class InvProducto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_producto", unique=true, nullable=false)
	private Integer idProducto;

	@Column(name="codigo_producto", length=50)
	private String codigoProducto;

	@Column(precision=7, scale=2)
	private BigDecimal costo;

	@Column(name="descripcion_", length=200)
	private String descripcion;

	private Boolean estado;

	@Column(precision=131089)
	private BigDecimal iva;

	@Column(name="nombre_producto", length=100)
	private String nombreProducto;

	@Column(precision=7, scale=2)
	private BigDecimal pvp;

	private Integer stock;

	//bi-directional many-to-one association to InvDetalleAjusteProducto
	@OneToMany(mappedBy="invProducto")
	private List<InvDetalleAjusteProducto> invDetalleAjusteProductos;

	//bi-directional many-to-one association to InvCategoriaProducto
	@ManyToOne
	@JoinColumn(name="id_categoria_producto")
	private InvCategoriaProducto invCategoriaProducto;

	public InvProducto() {
	}

	public Integer getIdProducto() {
		return this.idProducto;
	}

	public void setIdProducto(Integer idProducto) {
		this.idProducto = idProducto;
	}

	public String getCodigoProducto() {
		return this.codigoProducto;
	}

	public void setCodigoProducto(String codigoProducto) {
		this.codigoProducto = codigoProducto;
	}

	public BigDecimal getCosto() {
		return this.costo;
	}

	public void setCosto(BigDecimal costo) {
		this.costo = costo;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Boolean getEstado() {
		return this.estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public BigDecimal getIva() {
		return this.iva;
	}

	public void setIva(BigDecimal iva) {
		this.iva = iva;
	}

	public String getNombreProducto() {
		return this.nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	public BigDecimal getPvp() {
		return this.pvp;
	}

	public void setPvp(BigDecimal pvp) {
		this.pvp = pvp;
	}

	public Integer getStock() {
		return this.stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public List<InvDetalleAjusteProducto> getInvDetalleAjusteProductos() {
		return this.invDetalleAjusteProductos;
	}

	public void setInvDetalleAjusteProductos(List<InvDetalleAjusteProducto> invDetalleAjusteProductos) {
		this.invDetalleAjusteProductos = invDetalleAjusteProductos;
	}

	public InvDetalleAjusteProducto addInvDetalleAjusteProducto(InvDetalleAjusteProducto invDetalleAjusteProducto) {
		getInvDetalleAjusteProductos().add(invDetalleAjusteProducto);
		invDetalleAjusteProducto.setInvProducto(this);

		return invDetalleAjusteProducto;
	}

	public InvDetalleAjusteProducto removeInvDetalleAjusteProducto(InvDetalleAjusteProducto invDetalleAjusteProducto) {
		getInvDetalleAjusteProductos().remove(invDetalleAjusteProducto);
		invDetalleAjusteProducto.setInvProducto(null);

		return invDetalleAjusteProducto;
	}

	public InvCategoriaProducto getInvCategoriaProducto() {
		return this.invCategoriaProducto;
	}

	public void setInvCategoriaProducto(InvCategoriaProducto invCategoriaProducto) {
		this.invCategoriaProducto = invCategoriaProducto;
	}

}