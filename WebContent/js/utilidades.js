function Maximizar(){
window.moveTo(0, 0);
	if (document.all) {
		top.window.resizeTo(screen.availWidth, screen.availHeight);
	} else if (document.layers || document.getElementById) {
		if (screen.availHeight > top.window.outerHeight
				|| screen.availWidth > top.window.outerWidth) {
			top.window.outerHeight = screen.availHeight;
			top.window.outerWidth = screen.availWidth;
		}
	}
}
function LlamarVentana(direccion,nombre){
	window.open(direccion,nombre,'resizable=yes,scrollbars=yes,width=820,height=560,status=yes,top=140,left=190'); 
}

function LlamarPdf(){
	window.open('PdfFactura.zul','Pdf','resizable=no,scrollbars=yes,width=1080px,height=800px,status=yes,top=0,left=35');
}

function desactivarMenuContextual(){
	document.oncontextmenu=new Function("return false");
}

function activarMenuContextual(){
	document.oncontextmenu=new Function("return true");
}