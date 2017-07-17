
/**
 * FacturaDigitalFacadeCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

    package ve.com.corpoelec.facturadigital.cip.facade;

    /**
     *  FacturaDigitalFacadeCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class FacturaDigitalFacadeCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public FacturaDigitalFacadeCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public FacturaDigitalFacadeCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for obtenerFactura method
            * override this method for handling normal response from obtenerFactura operation
            */
           public void receiveResultobtenerFactura(
                    ve.com.corpoelec.facturadigital.cip.facade.FacturaDigitalFacadeStub.ObtenerFacturaResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from obtenerFactura operation
           */
            public void receiveErrorobtenerFactura(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for listaClientes method
            * override this method for handling normal response from listaClientes operation
            */
           public void receiveResultlistaClientes(
                    ve.com.corpoelec.facturadigital.cip.facade.FacturaDigitalFacadeStub.ListaClientesResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from listaClientes operation
           */
            public void receiveErrorlistaClientes(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for listarDocColectivas method
            * override this method for handling normal response from listarDocColectivas operation
            */
           public void receiveResultlistarDocColectivas(
                    ve.com.corpoelec.facturadigital.cip.facade.FacturaDigitalFacadeStub.ListarDocColectivasResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from listarDocColectivas operation
           */
            public void receiveErrorlistarDocColectivas(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for listarContratos method
            * override this method for handling normal response from listarContratos operation
            */
           public void receiveResultlistarContratos(
                    ve.com.corpoelec.facturadigital.cip.facade.FacturaDigitalFacadeStub.ListarContratosResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from listarContratos operation
           */
            public void receiveErrorlistarContratos(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for genPdfDocumento method
            * override this method for handling normal response from genPdfDocumento operation
            */
           public void receiveResultgenPdfDocumento(
                    ve.com.corpoelec.facturadigital.cip.facade.FacturaDigitalFacadeStub.GenPdfDocumentoResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from genPdfDocumento operation
           */
            public void receiveErrorgenPdfDocumento(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for listarDocumentos method
            * override this method for handling normal response from listarDocumentos operation
            */
           public void receiveResultlistarDocumentos(
                    ve.com.corpoelec.facturadigital.cip.facade.FacturaDigitalFacadeStub.ListarDocumentosResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from listarDocumentos operation
           */
            public void receiveErrorlistarDocumentos(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for listarNicColectivas method
            * override this method for handling normal response from listarNicColectivas operation
            */
           public void receiveResultlistarNicColectivas(
                    ve.com.corpoelec.facturadigital.cip.facade.FacturaDigitalFacadeStub.ListarNicColectivasResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from listarNicColectivas operation
           */
            public void receiveErrorlistarNicColectivas(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for obtenerCliente method
            * override this method for handling normal response from obtenerCliente operation
            */
           public void receiveResultobtenerCliente(
                    ve.com.corpoelec.facturadigital.cip.facade.FacturaDigitalFacadeStub.ObtenerClienteResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from obtenerCliente operation
           */
            public void receiveErrorobtenerCliente(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for listarDocumentosPeriodo method
            * override this method for handling normal response from listarDocumentosPeriodo operation
            */
           public void receiveResultlistarDocumentosPeriodo(
                    ve.com.corpoelec.facturadigital.cip.facade.FacturaDigitalFacadeStub.ListarDocumentosPeriodoResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from listarDocumentosPeriodo operation
           */
            public void receiveErrorlistarDocumentosPeriodo(java.lang.Exception e) {
            }
                


    }
    