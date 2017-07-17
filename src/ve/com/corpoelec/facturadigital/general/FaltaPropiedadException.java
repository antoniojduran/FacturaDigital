package ve.com.corpoelec.facturadigital.general;

@SuppressWarnings("serial")
public class FaltaPropiedadException extends Exception {
	 
	   private String nombreParametro;
	 
	   public FaltaPropiedadException(String nombreParametro) {
	     super("Falta parametro de configuracion: '" + nombreParametro + "'");
	     this.nombreParametro = nombreParametro;
	   }
	 
	   public String getNombreParametro() {
	     return nombreParametro;
	   }
	 }
	 