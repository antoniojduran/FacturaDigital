
/**
 * MailFactFacExceptionException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

package ve.com.corpoelec.facturadigital.mail.facade;

public class MailFactFacExceptionException extends java.lang.Exception{

    private static final long serialVersionUID = 1458053000968L;
    
    private ve.com.corpoelec.facturadigital.mail.facade.MailFactFacStub.MailFactFacException faultMessage;

    
        public MailFactFacExceptionException() {
            super("MailFactFacExceptionException");
        }

        public MailFactFacExceptionException(java.lang.String s) {
           super(s);
        }

        public MailFactFacExceptionException(java.lang.String s, java.lang.Throwable ex) {
          super(s, ex);
        }

        public MailFactFacExceptionException(java.lang.Throwable cause) {
            super(cause);
        }
    

    public void setFaultMessage(ve.com.corpoelec.facturadigital.mail.facade.MailFactFacStub.MailFactFacException msg){
       faultMessage = msg;
    }
    
    public ve.com.corpoelec.facturadigital.mail.facade.MailFactFacStub.MailFactFacException getFaultMessage(){
       return faultMessage;
    }
}
    