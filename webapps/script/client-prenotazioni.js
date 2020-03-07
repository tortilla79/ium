
function donePrenotazione(row){
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function() {
	if (xhr.readyState == 4 && xhr.status == 200) {
	    window.location.reload(false);
	}
    }
    let tr = document.querySelector("tbody tr:nth-child("+ (row+2) +")");
    let usr = document.querySelector("tr:nth-child(2) td:nth-child(1)").innerHTML;
    let corso = document.querySelector("tr:nth-child(2) td:nth-child(2)").innerHTML;
    let doc = document.querySelector("tr:nth-child(2) td:nth-child(3)").innerHTML.split(" ");
    let ora = document.querySelector("tr:nth-child(2) td:nth-child(4)").innerHTML;
    let giorno = document.querySelector("tr:nth-child(2) td:nth-child(5)").innerHTML;
    let path = "Prenotazione?param=conferma&usr="+ usr +"&corso="+ corso +"&docname="+ doc[0] +"&docsur="+ doc[1] +"&ora="+ ora +"&giorno=" + giorno;
    xhr.open('GET', path, true);
    xhr.send(null);
        
}

function annulPrenotazione(row){
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function() {
	if (xhr.readyState == 4 && xhr.status == 200) {
	    window.location.reload(false);
	}
    }
    let tr = document.querySelector("tbody tr:nth-child("+ (row+2) +")");
    let usr = document.querySelector("tr:nth-child(2) td:nth-child(1)").innerHTML;
    let corso = document.querySelector("tr:nth-child(2) td:nth-child(2)").innerHTML;
    let doc = document.querySelector("tr:nth-child(2) td:nth-child(3)").innerHTML.split(" ");
    let ora = document.querySelector("tr:nth-child(2) td:nth-child(4)").innerHTML;
    let giorno = document.querySelector("tr:nth-child(2) td:nth-child(5)").innerHTML;
    let path = "Prenotazione?param=disdici&usr="+ usr +"&corso="+ corso +"&docname="+ doc[0] +"&docsur="+ doc[1] +"&ora="+ ora +"&giorno=" + giorno;
    xhr.open('GET', path, true);
    xhr.send(null);
  
}
