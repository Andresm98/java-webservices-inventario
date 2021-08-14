package minimarketdemo.model.core.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the inv_categoria_producto database table.
 * 
 */
@Entity
@Table(name="inv_categoria_producto")
@NamedQuery(name="InvCategoriaProducto.findAll", query="SELECT i FROM InvCategoriaProducto i")
public class InvCategoriaProducto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_categoria_producto", unique=true, nullable=false)
	private Integer idCategoriaProducto;

	@Column(name="codigo_prod", length=10)
	private String codigoProd;

	@Column(name="descripcion_categoria", length=200)
	private String descripcionCategoria;

	@Column(name="nombre_categoria", length=50)
	private String nombreCategoria;

	//bi-directional many-to-one association to InvProducto
	@OneToMany(mappedBy="invCategoriaProducto")
	private List<InvProducto> invProductos;

	public InvCategoriaProducto() {
	}

	public Integer getIdCategoriaProducto() {
		return this.idCategoriaProducto;
	}

	public void setIdCategoriaProducto(Integer idCategoriaProducto) {
		this.idCategoriaProducto = idCategoriaProducto;
	}

	public String getCodigoProd() {
		return this.codigoProd;
	}

	public void setCodigoProd(String codigoProd) {
		this.codigoProd = codigoProd;
	}

	public String getDescripcionCategoria() {
		return this.descripcionCategoria;
	}

	public void setDescripcionCategoria(String descripcionCategoria) {
		this.descripcionCategoria = descripcionCategoria;
	}

	public String getNombreCategoria() {
		return this.nombreCategoria;
	}

	public void setNombreCategoria(String nombreCategoria) {
		this.nombreCategoria = nombreCategoria;
	}

	public List<InvProducto> getInvProductos() {
		return this.invProductos;
	}

	public void setInvProductos(List<InvProducto> invProductos) {
		this.invProductos = invProductos;
	}

	public InvProducto addInvProducto(InvProducto invProducto) {
		getInvProductos().add(invProducto);
		invProducto.setInvCategoriaProducto(this);

		return invProducto;
	}

	public InvProducto removeInvProducto(InvProducto invProducto) {
		getInvProductos().remove(invProducto);
		invProducto.setInvCategoriaProducto(null);

		return invProducto;
	}

}