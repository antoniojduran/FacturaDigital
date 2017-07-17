package ve.com.corpoelec.facturadigital.controladores;

import java.rmi.RemoteException;
import java.util.Iterator;
import java.util.List;

import org.apache.axis2.AxisFault;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Label;
import org.zkoss.zul.Textbox;

import ve.com.corpoelec.facturadigital.general.FaltaPropiedadException;
import ve.com.corpoelec.facturadigital.general.Propiedades;
import ve.com.corpoelec.facturadigital.mail.facade.MailFactFacExceptionException;
import ve.com.corpoelec.facturadigital.mail.facade.MailFactFacStub;
import ve.com.corpoelec.facturadigital.mail.facade.MailFactFacStub.EnviarFacturaMail;
import ve.com.corpoelec.facturadigital.mail.facade.MailFactFacStub.EnviarFacturaMailResponse;

@SuppressWarnings("rawtypes")
public class EnviarFactura extends GenericForwardComposer {
    /**
	 * 
	 */
	private static final long serialVersionUID = -8454622603506753222L;
	private String nroDocumento;
    private String opcion;
    private List ListaFacturas;
    private Textbox txtEnviar;
    private Label lblmensaje;
    private Label lblusuario;
    private Button btncerrar;
    private Propiedades prop = new Propiedades();
	
	public void onCreate$windEnviarFactura(){
		try {
			RecibirParametros();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
private void RecibirParametros() {

	    if (session.getAttribute("nbusuario")!=null){
		    lblusuario.setValue((String) session.getAttribute("nbusuario"));
	    }     
	    if (session.getAttribute("nroDocumento")!=null){
	    	nroDocumento = (String) session.getAttribute("nroDocumento");	
	    }
        if (session.getAttribute("ListaFacturas")!=null){
        	ListaFacturas = (List) session.getAttribute("ListaFacturas");	
	    }
        if (session.getAttribute("opcion")!=null){
            opcion= (String) session.getAttribute("opcion");	
        }
	}

public void onClick$btnEnviar() throws AxisFault, FaltaPropiedadException{
	String para="";
	long posicion=0;
	EnviarFacturaMail enviarfactura=new EnviarFacturaMail();
	
	TratarMensaje(false,"","");		

	
	if (!txtEnviar.equals("")){		
		
		txtEnviar.setConstraint("/.+@.+\\.[a-z]+/: Suministre un correo válido.");
		para = txtEnviar.getText();
		enviarfactura.setPara(para);
		

		if (opcion!=null){
				if (opcion.equals("1")){
					enviarfactura.setNroDocumento(nroDocumento);
					posicion=EnviarFacturaEmail(enviarfactura);
				}
				else if (opcion.equals("2")){
					String documento="";
					for (Iterator ite=ListaFacturas.iterator();ite.hasNext();){
						if (documento.equals("")){
							documento=documento.concat((String) ite.next());	
						}
						else{
							documento=documento.concat(","+(String) ite.next());
						}
					}				
					enviarfactura.setNroDocumento(documento);
					posicion=EnviarFacturaEmail(enviarfactura);
				}
				
			}
	   if (posicion>=0){
				TratarMensaje(true,"Su solicitud se encuentra en proceso","font-size : 16px ;font-weight : bolder;font-family : arial");
				txtEnviar.setConstraint("");
				txtEnviar.setText("");				
		    }			
	}
}

private long EnviarFacturaEmail(EnviarFacturaMail enviarfactura) throws FaltaPropiedadException{
	long posicion=-1;
	String stilo="font-size : 16px ;font-weight : bolder;font-family : arial;color : red";
	MailFactFacStub stub;		
	EnviarFacturaMailResponse enviarfacturaresp=new EnviarFacturaMailResponse();
	
	try {
		stub = new MailFactFacStub(prop.getPropiedad("wsMailFact"));
		enviarfacturaresp =stub.enviarFacturaMail(enviarfactura);
		posicion=enviarfacturaresp.get_return();
	} catch (AxisFault e) {
		// TODO Auto-generated catch block
		TratarMensaje(true,"Lo sentimos en este momento el servicio no se encuentra disponible, intente su solicitud más tarde.",stilo);
		e.printStackTrace();
	} catch (RemoteException e) {
		TratarMensaje(true,"Lo sentimos en este momento el servicio no se encuentra disponible, intente su solicitud más tarde.",stilo);
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (MailFactFacExceptionException e) {
		TratarMensaje(true,"Lo sentimos en este momento el servicio no se encuentra disponible, intente su solicitud más tarde.",stilo);
		// TODO Auto-generated catch block
		e.printStackTrace();
	}			
	return posicion;
}



private void TratarMensaje(boolean condicion, String mensaje, String stilo){
	lblmensaje.setVisible(condicion);
	lblmensaje.setValue(mensaje);
	lblmensaje.setStyle(stilo);	
}

public void onClick$btncerrar() throws FaltaPropiedadException{
	 Executions.getCurrent().sendRedirect(prop.getPropiedad("urlOficina"));
}


}
