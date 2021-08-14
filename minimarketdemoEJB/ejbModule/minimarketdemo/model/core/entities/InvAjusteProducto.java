package minimarketdemo.model.core.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the inv_ajuste_producto database table.
 * 
 */
@Entity
@Table(name="inv_ajuste_producto")
@NamedQuery(name="InvAjusteProducto.findAll", query="SELECT i FROM InvAjusteProducto i")
public class InvAjusteProducto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_ajuste", unique=true, nullable=false)
	private Integer idAjuste;

	@Temporal(TemporalType.DATE)
	private Date fecha;

	private Boolean impreso;

	@Column(length=500)
	private String motivo;

	@Column(name="numero_ajuste", length=50)
	private String numeroAjuste;

	@Column(name="tipo_ajuste", length=1)
	private String tipoAjuste;

	//bi-directional many-to-one association to InvDetalleAjusteDocumento
	@OneToMany(mappedBy="invAjusteProducto")
	private List<InvDetalleAjusteDocumento> invDetalleAjusteDocumentos;

	//bi-directional many-to-one association to InvDetalleAjusteProducto
	@OneToMany(mappedBy="invAjusteProducto")
	private List<InvDetalleAjusteProducto> invDetalleAjusteProductos;

	public InvAjusteProducto() {
	}

	public Integer getIdAjuste() {
		return this.idAjuste;
	}

	public void setIdAjuste(Integer idAjuste) {
		this.idAjuste = idAjuste;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Boolean getImpreso() {
		return this.impreso;
	}

	public void setImpreso(Boolean impreso) {
		this.impreso = impreso;
	}

	public String getMotivo() {
		return this.motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public String getNumeroAjuste() {
		return this.numeroAjuste;
	}

	public void setNumeroAjuste(String numeroAjuste) {
		this.numeroAjuste = numeroAjuste;
	}

	public String getTipoAjuste() {
		return this.tipoAjuste;
	}

	public void setTipoAjuste(String tipoAjuste) {
		this.tipoAjuste = tipoAjuste;
	}

	public List<InvDetalleAjusteDocumento> getInvDetalleAjusteDocumentos() {
		return this.invDetalleAjusteDocumentos;
	}

	public void setInvDetalleAjusteDocumentos(List<InvDetalleAjusteDocumento> invDetalleAjusteDocumentos) {
		this.invDetalleAjusteDocumentos = invDetalleAjusteDocumentos;
	}

	public InvDetalleAjusteDocumento addInvDetalleAjusteDocumento(InvDetalleAjusteDocumento invDetalleAjusteDocumento) {
		getInvDetalleAjusteDocumentos().add(invDetalleAjusteDocumento);
		invDetalleAjusteDocumento.setInvAjusteProducto(this);

		return invDetalleAjusteDocumento;
	}

	public InvDetalleAjusteDocumento removeInvDetalleAjusteDocumento(InvDetalleAjusteDocumento invDetalleAjusteDocumento) {
		getInvDetalleAjusteDocumentos().remove(invDetalleAjusteDocumento);
		invDetalleAjusteDocumento.setInvAjusteProducto(null);

		return invDetalleAjusteDocumento;
	}

	public List<InvDetalleAjusteProducto> getInvDetalleAjusteProductos() {
		return this.invDetalleAjusteProductos;
	}

	public void setInvDetalleAjusteProductos(List<InvDetalleAjusteProducto> invDetalleAjusteProductos) {
		this.invDetalleAjusteProductos = invDetalleAjusteProductos;
	}

	public InvDetalleAjusteProducto addInvDetalleAjusteProducto(InvDetalleAjusteProducto invDetalleAjusteProducto) {
		getInvDetalleAjusteProductos().add(invDetalleAjusteProducto);
		invDetalleAjusteProducto.setInvAjusteProducto(this);

		return invDetalleAjusteProducto;
	}

	public InvDetalleAjusteProducto removeInvDetalleAjusteProducto(InvDetalleAjusteProducto invDetalleAjusteProducto) {
		getInvDetalleAjusteProductos().remove(invDetalleAjusteProducto);
		invDetalleAjusteProducto.setInvAjusteProducto(null);

		return invDetalleAjusteProducto;
	}

}