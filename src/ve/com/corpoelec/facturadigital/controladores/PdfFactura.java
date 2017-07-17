package ve.com.corpoelec.facturadigital.controladores;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import org.zkoss.util.media.AMedia;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Iframe;
import org.zkoss.zul.Listbox;

import ve.com.corpoelec.facturadigital.cip.facade.FacturaDigitalFacadeExceptionException;
import ve.com.corpoelec.facturadigital.cip.facade.FacturaDigitalFacadeStub;
import ve.com.corpoelec.facturadigital.cip.facade.FacturaDigitalFacadeStub.GenPdfDocumento;
import ve.com.corpoelec.facturadigital.cip.facade.FacturaDigitalFacadeStub.GenPdfDocumentoResponse;
import ve.com.corpoelec.facturadigital.general.Propiedades;

@SuppressWarnings("rawtypes")
public class PdfFactura extends GenericForwardComposer {	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7790895618506694342L;
	private Iframe reporte ;
	private Propiedades prop = new Propiedades();
	
public void onCreate$windPdfFactura(){
		try {
			RecibeParametros();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

public void RecibeParametros(){
	   
	   String   nroDocumento = "";
	   if (session.getAttribute("nroDocumento")!=null){
		   nroDocumento = (String) session.getAttribute("nroDocumento");		
	}
	    InputStream is = null;
		InputStream mediais = null;
		byte[] fileBytes = null;
		AMedia amedia = null;
		try {
			if (nroDocumento != null){
				FacturaDigitalFacadeStub stub =new FacturaDigitalFacadeStub(prop.getPropiedad("wsFactDigital"));				
				GenPdfDocumento PdfDocumento = new GenPdfDocumento();
				GenPdfDocumentoResponse PdfDocumentoResp=new GenPdfDocumentoResponse();
				PdfDocumento.setNroDocumento(nroDocumento);		
				PdfDocumentoResp = stub.genPdfDocumento(PdfDocumento);				
				mediais = PdfDocumentoResp.get_return().getInputStream();	    			
			}
			amedia = new AMedia("Factura Digital", "pdf", "application/pdf", mediais);
		    reporte.setContent(amedia);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (Exception ex) {

				}
			}
			if (mediais != null) {
				try {
					mediais.close();
				} catch (Exception ex) {

				}
			}
			if (amedia != null) {
				try {
					amedia=null;
				} catch (Exception ex) {

				}
			}
			if (fileBytes != null) {
				try {
					fileBytes=null;
				} catch (Exception ex) {

				}
			}
		}   
}

}
