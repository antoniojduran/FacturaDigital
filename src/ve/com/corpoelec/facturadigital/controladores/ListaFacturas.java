package ve.com.corpoelec.facturadigital.controladores;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;
import org.apache.axis2.AxisFault;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Image;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listhead;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Space;

import ve.com.corpoelec.facturadigital.cip.facade.FacturaDigitalFacadeExceptionException;
import ve.com.corpoelec.facturadigital.cip.facade.FacturaDigitalFacadeStub;
import ve.com.corpoelec.facturadigital.cip.facade.FacturaDigitalFacadeStub.FacturaInfo;
import ve.com.corpoelec.facturadigital.cip.facade.FacturaDigitalFacadeStub.ListarDocumentosPeriodo;
import ve.com.corpoelec.facturadigital.cip.facade.FacturaDigitalFacadeStub.ListarDocumentosPeriodoResponse;
import ve.com.corpoelec.facturadigital.general.FaltaPropiedadException;
import ve.com.corpoelec.facturadigital.general.Propiedades;

@SuppressWarnings("rawtypes")
public class ListaFacturas extends GenericForwardComposer {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1298867956473034766L;
	String serie ="";
    String nic = "";
    String facturador = "";
    String tipodocumento = "";		
    private Listbox listaFacturas;
    private Checkbox chkEnviarSeleccion;
    private Div dvmensaje;
	private Label lblencabezado;
	private Div dvlista;
	private Label lblusuario;
	private Propiedades prop = new Propiedades();
	private Button btncerrar;
	
	public void onCreate$windFacturas(){
		try {
			TratarMensaje(false);
			RecibirParametros();
			LimpiarVariables();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Problemas con la aplicación"+e.getMessage()+" ; ");
		}
	}

	private void RecibirParametros() throws FaltaPropiedadException {
		
		if (session.getAttribute("nbusuario")!=null){
			 lblusuario.setValue((String) session.getAttribute("nbusuario"));
		 }
		
		 if (session.getAttribute("serie")!=null){
			 serie = (String) session.getAttribute("serie");	 
		 }
         if (session.getAttribute("nic")!=null){
        	 nic = (String) session.getAttribute("nic");	 
         }
         if (session.getAttribute("facturador")!=null){
        	 facturador = (String) session.getAttribute("facturador");	 
         }         
         if (session.getAttribute("tipodocumento")!=null){
        	 tipodocumento = (String) session.getAttribute("tipodocumento");	 
         }         
         try {
			Consultar_FacturaLista(serie,nic,facturador,tipodocumento);
		}
         catch (AxisFault e) {
 			// TODO Auto-generated catch block
 			TratarMensaje(true);
 			e.printStackTrace();
 		}
         catch (RemoteException e) {
			// TODO Auto-generated catch block
			TratarMensaje(true);
			e.printStackTrace();
		} catch (FacturaDigitalFacadeExceptionException e) {
			// TODO Auto-generated catch block
			TratarMensaje(true);
			e.printStackTrace();
		}
         
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void Consultar_FacturaLista(String serie2, String nic2,
			String facturador2, String tipodocumento2) throws RemoteException, FacturaDigitalFacadeExceptionException, AxisFault, FaltaPropiedadException {
	
		
			FacturaDigitalFacadeStub stub = new FacturaDigitalFacadeStub(prop.getPropiedad("wsFactDigital"));
			ListarDocumentosPeriodo listadocumentos=new ListarDocumentosPeriodo();
			ListarDocumentosPeriodoResponse respuestalista=new ListarDocumentosPeriodoResponse();
			
			listadocumentos.setSerie(serie2);
			listadocumentos.setNic(nic2);
			listadocumentos.setFacturador(facturador2);
			listadocumentos.setTipoDocumento(tipodocumento2);
			Calendar fecha = GregorianCalendar.getInstance();    
			listadocumentos.setFechaFin(fecha.getTime());
			fecha.add(Calendar.MONTH, -24);
			listadocumentos.setFechaInicio(fecha.getTime());
			respuestalista = stub.listarDocumentosPeriodo(listadocumentos);				
			
			FacturaInfo[] lista =null;
			
			lista=respuestalista.get_return();
			
			for (int i=0;i<lista.length;i++)
			{
			    final Listcell celfactura =new Listcell(lista[i].getFactura());
				Listcell celperiodo =new Listcell(ddmmyyyySqlSeparadoConSlashString(lista[i].getPeriodoInicio())+" al "+ ddmmyyyySqlSeparadoConSlashString(lista[0].getPeriodoFin()));				
				Listcell celemision =new Listcell(ddmmyyyySqlSeparadoConSlashString(lista[i].getFechaEmision()));
				Listcell celvencimiento =new Listcell(ddmmyyyySqlSeparadoConSlashString(lista[i].getFechaVencimiento()));
				Listcell celimporte =new Listcell(lista[i].getTotalFormato());
				Listcell celaccion =new Listcell();
				Image imagenpdf = new Image();
				imagenpdf.setSrc("/imagenes/pdf.png");
				imagenpdf.addEventListener(Events.ON_CLICK, new EventListener() {
					public void onEvent(Event event) throws Exception {
						session.setAttribute("nroDocumento", celfactura.getLabel());
						Clients.evalJavaScript("LlamarPdf()");	
					}
				});
				celaccion.appendChild(imagenpdf);
				Space espacio=new Space(); 
				celaccion.appendChild(espacio);
				Image imagenemail = new Image();
				imagenemail.setSrc("/imagenes/correo.png");
				imagenemail.addEventListener(Events.ON_CLICK, new EventListener() {
					public void onEvent(Event event) throws Exception {
						session.setAttribute("nroDocumento", celfactura.getLabel());
						session.setAttribute("opcion", "1");
						Executions.getCurrent().sendRedirect("EnviarFactura.zul");
					}
				});
				celaccion.appendChild(imagenemail);
				espacio=new Space();
				celaccion.appendChild(espacio);
				Checkbox seleccion= new Checkbox();
				celaccion.appendChild(seleccion);
				Listitem licliente=new Listitem();
				licliente.appendChild(celfactura);
				licliente.appendChild(celperiodo);
				licliente.appendChild(celemision);												
				licliente.appendChild(celvencimiento);
				licliente.appendChild(celimporte);
				licliente.appendChild(celaccion);
				listaFacturas.appendChild(licliente);
			}		
	}

	@SuppressWarnings("unchecked")
	public void onCheck$chkEnviarSeleccion(){
		
		List facturasenviar=new ArrayList();
		
		
		if (chkEnviarSeleccion.isChecked()){
		
		
		List lista=listaFacturas.getItems();
		if (lista.size()>0)
		{
		  for (Iterator ite=lista.iterator();ite.hasNext();){
			 Listitem item=(Listitem) ite.next();		 
			 Listcell ultimacelda= (Listcell)item.getLastChild();
             Checkbox seleccion=(Checkbox) ultimacelda.getLastChild();
			 if (seleccion.isChecked()){				 
				 Listcell primeracelda= (Listcell)item.getFirstChild();
				 String factura=primeracelda.getLabel();
				 facturasenviar.add(factura);
			 }
		  }	
		}
	if (facturasenviar.size()>0){
		session.setAttribute("ListaFacturas", facturasenviar);
		session.setAttribute("opcion", "2");
		Executions.getCurrent().sendRedirect("EnviarFactura.zul");
		
	}
		
	}
	}
	 
	 public static String ddmmyyyySqlSeparadoConSlashString(String Sfecha) {
	     StringTokenizer fechaNac = new StringTokenizer(Sfecha, "-");
	     String annoNac = fechaNac.nextToken();
	     String mesNac = fechaNac.nextToken();
	     String diaNac = fechaNac.nextToken();
	     String FechaNacNormal = diaNac + "/" + mesNac + "/" + annoNac;
	     return FechaNacNormal;
	  }
	 
	 private void LimpiarVariables(){
		   session.removeAttribute("nroDocumento");
	       session.removeAttribute("ListaFacturas");
	       session.removeAttribute("opcion");
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
	 public void onClick$btncerrar() throws FaltaPropiedadException{
		 Executions.getCurrent().sendRedirect(prop.getPropiedad("urlOficina"));
	}
}
