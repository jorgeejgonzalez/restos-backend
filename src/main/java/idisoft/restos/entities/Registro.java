package idisoft.restos.entities;

import javax.persistence.Column;

public abstract class Registro {
	
	@Column(name="estatus_registro")
	private EstatusRegistro estatusRegistro;

	public EstatusRegistro getEstatusRegistro() {
		return estatusRegistro;
	}

	public void setEstatusRegistro(EstatusRegistro estatusRegistro) {
		this.estatusRegistro = estatusRegistro;
	}
	

}
