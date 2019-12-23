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

function toggle(idComponent){
	jQuery(idComponent).toggle();
}

function generatePDF () {
	
	window.print();
}