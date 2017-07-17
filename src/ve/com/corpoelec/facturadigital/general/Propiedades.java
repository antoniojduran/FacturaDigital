package ve.com.corpoelec.facturadigital.general;

import java.util.Properties;
import java.util.HashMap;
import java.io.File;
import java.io.FileInputStream;

import javax.servlet.http.HttpServletRequest;

import org.zkoss.zk.ui.Executions;


@SuppressWarnings("unchecked")
public class Propiedades {
	HashMap propiedades;

	private HashMap CargarArchivo(String RutaNombreArchivo) {
		try {     	   			
			File archivo = new File(RutaNombreArchivo);
			FileInputStream f = new FileInputStream(archivo.getPath());
			Properties propiedadesTemporales = new Properties();
			propiedadesTemporales.load(f);
			f.close();
			propiedades = new HashMap(propiedadesTemporales);
		} catch (Exception e) {
			System.err.println("Error al cargar el archivo propiedades. " + e.getMessage());
		}
		return propiedades;
	}

	public Propiedades(String RutaNombreArchivo) {
		propiedades = CargarArchivo(RutaNombreArchivo);
	}
	
	public Propiedades() {
		propiedades = CargarArchivo(getPathSistema() + "/properties/facturadigital.properties");
	}
	
	public String getPropiedad(String nombre) throws FaltaPropiedadException {
		String valor = (String) propiedades.get(nombre);
		if (valor == null)
			throw new FaltaPropiedadException(nombre);

		return valor;
	}
	
	public static String getPathSistema() {
		HttpServletRequest servletRequest= (HttpServletRequest) Executions.getCurrent().getNativeRequest();		
		String pathSistema = servletRequest.getSession().getServletContext().getRealPath("/WEB-INF/");
		//String pathSistema = ("/var/lib/tomcat6");//NO BORRAR
		return pathSistema;
	}
}