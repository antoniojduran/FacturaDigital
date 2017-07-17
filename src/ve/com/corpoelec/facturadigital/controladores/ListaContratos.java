package ve.com.corpoelec.facturadigital.controladores;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.axis2.AxisFault;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Div;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;

import ve.com.corpoelec.facturadigital.cip.facade.FacturaDigitalFacadeExceptionException;
import ve.com.corpoelec.facturadigital.cip.facade.FacturaDigitalFacadeStub;
import ve.com.corpoelec.facturadigital.cip.facade.FacturaDigitalFacadeStub.ObtenerCliente;
import ve.com.corpoelec.facturadigital.cip.facade.FacturaDigitalFacadeStub.ObtenerClienteResponse;
import ve.com.corpoelec.facturadigital.general.FaltaPropiedadException;
import ve.com.corpoelec.facturadigital.general.Propiedades;


@SuppressWarnings("rawtypes")
public class ListaContratos extends GenericForwardComposer{

	/**
	 * 
	 */
	private static final long serialVersionUID = -131565130257844881L;

	private Listbox listaContratos;
	private String stilo="font-size : 11px ;font-family : arial";
	private Div dvmensaje;
	private Label lblencabezado;
	private Div dvlista;
	private Propiedades prop = new Propiedades();
	private Label lblusuario;
	private String Cuentas;
	private Button btncerrar;
	
	public ListaContratos() {
		// TODO Auto-generated constructor stub
	}

	public void onCreate$windContratos(){
		try {            
			if (RecibirPeticion()){
				TratarMensaje(false);
				ConsultaCliente();
				LimpiarVariables();			
			}
			else{
				TratarMensaje(true);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Problemas con la aplicación"+e.getMessage()+" ; ");
		}
	}
	
	private boolean RecibirPeticion(){
		boolean resultado=false;
		HttpServletRequest servletRequest= (HttpServletRequest) Executions.getCurrent().getNativeRequest();
		String usuario= "";
		String cuentas="";
		session.removeAttribute("nbusuario");
		if (servletRequest.getParameter("usuario")!=null){
			usuario= servletRequest.getParameter("usuario");
			session.setAttribute("nbusuario",usuario);			
		}
		if (servletRequest.getParameter("cuentas")!=null){
			cuentas=servletRequest.getParameter("cuentas");
			
		}
		if (usuario!=null){
			if (!usuario.equals("")){
				if (cuentas!=null){
					if (!cuentas.equals("")){
						resultado=true;
						lblusuario.setValue(usuario);
						Cuentas=cuentas;
					}
				}
			}
		}				
		return resultado;
	}
	
	
	@SuppressWarnings("unchecked")
	public void LlenarLista(ObtenerClienteResponse respuestaCliente){
		

		final Listcell celserie=new Listcell(respuestaCliente.get_return().getSerie());
		final Listcell celnic=new Listcell(respuestaCliente.get_return().getNic());		
		celnic.addEventListener(Events.ON_CLICK, new EventListener() {
			public void onEvent(Event event) throws Exception {
				session.setAttribute("serie", celserie.getLabel());
				session.setAttribute("nic", celnic.getLabel());				
				session.setAttribute("facturador", "T");
				session.setAttribute("tipodocumento", "0");
				Executions.getCurrent().sendRedirect("ListaFacturas.zul");
			}
		});
		Listcell celtitular =new Listcell(respuestaCliente.get_return().getTitular());	
		Listcell celrif =new Listcell(respuestaCliente.get_return().getCi_rif());
		Listcell celdireccion =new Listcell(respuestaCliente.get_return().getDirSuministro());
		Listitem licliente=new Listitem();
		celserie.setStyle(stilo);
		celnic.setStyle(stilo);
		celnic.setStyle("color: blue");
		celtitular.setStyle(stilo);
		celrif.setStyle(stilo);
		celdireccion.setStyle(stilo);
		licliente.appendChild(celserie);
		licliente.appendChild(celnic);
		licliente.appendChild(celtitular);												
		licliente.appendChild(celrif);
		licliente.appendChild(celdireccion);
		listaContratos.appendChild(licliente);
		
	} 

	public void ConsultaCliente() throws Exception {
		
		FacturaDigitalFacadeStub stub = new FacturaDigitalFacadeStub(prop.getPropiedad("wsFactDigital"));	
		ObtenerCliente Cliente ;				
		ObtenerClienteResponse respuestaCliente = new ObtenerClienteResponse();		
		List listaclientes=new ArrayList();		
	
		listaclientes =SepararCuentas(Cuentas,",");
		
		try {
			
			for (Iterator ite=listaclientes.iterator();ite.hasNext();){
				Cliente = new ObtenerCliente();
				Cliente = (ObtenerCliente) ite.next();
				respuestaCliente = stub.obtenerCliente(Cliente);
				LlenarLista(respuestaCliente);
			}
				
		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			TratarMensaje(true);
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			TratarMensaje(true);
			e.printStackTrace();
		} catch (FacturaDigitalFacadeExceptionException e) {
			// TODO Auto-generated catch block
			TratarMensaje(true);
			e.printStackTrace();
		}	
	}
	
    private void LimpiarVariables(){
    	session.removeAttribute("serie");
		session.removeAttribute("nic");				
		session.removeAttribute("facturador");
		session.removeAttribute("tipodocumento");
    }
    private void TratarMensaje(boolean condicion){
    	dvmensaje.setVisible(condicion);
    	if (condicion){
    		lblencabezado.setValue("Servicio No Disponible");	
    	}
    	else{
    		lblencabezado.setValue("Seleccione el Número de Cuenta Contrato/NIC a consultar");	
    	}
    	
    	dvlista.setVisible(!condicion);    	
    }
 @SuppressWarnings("unchecked")
private List SepararCuentas(String cadena, String caracter){
		List listaclientes=new ArrayList();
	    String[] arrayColumnas = cadena.split(caracter);
		String serienic;
		ObtenerCliente Cliente ; 
		
		for(int i = 0; i < arrayColumnas.length; i++){
			serienic = arrayColumnas[i];
			Cliente=new ObtenerCliente();
			Cliente.setSerie(serienic.substring(0,2));
			Cliente.setNic(serienic.substring(2,14)) ;
			listaclientes.add(Cliente);
		}
		
	return listaclientes;	
	}  
    

 public void onClick$btncerrar() throws FaltaPropiedadException{
	 Executions.getCurrent().sendRedirect(prop.getPropiedad("urlOficina"));
}
 
}
