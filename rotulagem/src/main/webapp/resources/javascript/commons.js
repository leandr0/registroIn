/**
 * 
 */

var imported = document.createElement('script');
imported.src = 'resources/javascript/jspdf.min.js';
document.head.appendChild(imported);



function showDivHideDiv(showDiv,hideDiv){
	jQuery(hideDiv).hide();
	jQuery(showDiv).fadeIn(1500);
}

function hideDiv(idDiv){
	jQuery(idDiv).hide();
}

function hideLoading(idDiv){
	jQuery(idDiv).delay(2000).hide();
}

function showDiv(idDiv){
	jQuery(idDiv).show();
}

function showDialog(idDialog) {
	PF(idDialog).show();
}

function toggle(idComponent){
	jQuery(idComponent).toggle();
}

function generatePDF () {
	
	window.print();
}

function changeElement(elementID) {
    jQuery('.element-change').removeClass('active');
    jQuery(elementID).addClass('active')  
  }