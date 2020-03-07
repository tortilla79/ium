document.addEventListener("DOMContentLoaded", function(){
    
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function() {
	if (xhr.readyState == 4 && xhr.status == 200) {
	    let tdatas = document.querySelectorAll('td');
	    for(let i=0; i<tdatas.length; i++) {
		tdatas[i].innerHTML=xhr.responseText;
		tdatas[i].childNodes.forEach(function(item){item.setAttribute("onclick", "prenota('"+item.innerHTML+"', '"+ enumDay(i%5) +"', '"+ Math.floor(i/5+15) +"')")}) 
	    }
	}
    }
    xhr.open('GET', "Calendar", true);
    xhr.send(null);
    
    //popTable();
    
});

function prenota(subject, date, time) {

    let tab = document.querySelector("table");
    let header = "<thead><tr><th>Corso</th><th>Docente</th><th>Ora</th><th>Data</th><th>Conferma</th></tr></thead>";
    
    let data = "<tbody><tr><td>"+ subject.toUpperCase() +"</td><td><select id='docselect'><select></td><td>"+ time +"</td><td>"+ date +"</td><td><button onclick='"+ "confermaPrenotazione()" +"'>Prenota</button></td></tr></tbody>";
    tab.innerHTML = header + data;
    let select = document.querySelector("table #docselect");
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function() {
	if (xhr.readyState == 4 && xhr.status == 200) {
	    let options = xhr.responseText.split(",");
	    for (let i=0; i<options.length; i++) 
		select.appendChild(document.createElement("option"));
	    for (let i=0; i<options.length; i++) {
		select.children[i].value = options[i];
		select.children[i].innerText = options[i];
	    }
	}
    }
    let path = "ItemGetter?param=doc&filter="+ subject +"&busyhour="+ time +"&busyday="+ date;
    xhr.open('GET', path, true);
    xhr.send(null);


}

function enumDay(nmb) {
    switch(nmb){
    case 0:
	return "mon";
	break;
    case 1:
	return "tue";
	break;
    case 2:	
	return "wed";
	break;
    case 3:
	return "thu";
	break;
    case 4:
	return "fry";
	break;

    }
}

function confermaPrenotazione() {
    let tdatas = document.querySelectorAll('td');
    let redir  = document.querySelector("nav div a").href;
    
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function() {
	if (xhr.readyState == 4 && xhr.status == 200) {
	    if(!alert("Prenotazione Eseguita"))
		document.location.href=redir;
	}
    }
    let select = document.querySelector("#docselect");
    let usr = document.querySelector(".dropdown .menu").innerHTML;
    let corso = tdatas[0].innerHTML.toLowerCase();
    let doc = select.options[select.selectedIndex].value.split(" ");
    let ora = tdatas[2].innerHTML;
    let giorno = tdatas[3].innerHTML;
    let path = "Prenotazione?param=prenota&usr="+ usr +"&corso="+ corso +"&docname="+ doc[0] +"&docsur="+ doc[1] +"&ora="+ ora +"&giorno=" + giorno;
    xhr.open('GET', path, true);
    xhr.send(null);
}
