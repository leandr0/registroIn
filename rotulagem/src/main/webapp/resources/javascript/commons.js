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

function showDiv(idDiv){
	jQuery(showDiv).fadeIn(1500);
}

function toggle(idComponent){
	jQuery(idComponent).toggle();
}




function generatePDF () {
	
	window.print();
	/**
	var doc = new jsPDF();
	
    doc.fromHTML($('#content').html(), 15, 15, {
        'width': 170,
            'elementHandlers': specialElementHandlers
    });
    doc.save('registroIn-rotulo.pdf');
    **/
}