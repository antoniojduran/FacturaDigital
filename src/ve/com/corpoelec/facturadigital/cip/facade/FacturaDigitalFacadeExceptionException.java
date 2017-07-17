
/**
 * FacturaDigitalFacadeExceptionException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

package ve.com.corpoelec.facturadigital.cip.facade;

public class FacturaDigitalFacadeExceptionException extends java.lang.Exception{

    private static final long serialVersionUID = 1457094214437L;
    
    private ve.com.corpoelec.facturadigital.cip.facade.FacturaDigitalFacadeStub.FacturaDigitalFacadeException faultMessage;

    
        public FacturaDigitalFacadeExceptionException() {
            super("FacturaDigitalFacadeExceptionException");
        }

        public FacturaDigitalFacadeExceptionException(java.lang.String s) {
           super(s);
        }

        public FacturaDigitalFacadeExceptionException(java.lang.String s, java.lang.Throwable ex) {
          super(s, ex);
        }

        public FacturaDigitalFacadeExceptionException(java.lang.Throwable cause) {
            super(cause);
        }
    

    public void setFaultMessage(ve.com.corpoelec.facturadigital.cip.facade.FacturaDigitalFacadeStub.FacturaDigitalFacadeException msg){
       faultMessage = msg;
    }
    
    public ve.com.corpoelec.facturadigital.cip.facade.FacturaDigitalFacadeStub.FacturaDigitalFacadeException getFaultMessage(){
       return faultMessage;
    }
}
    