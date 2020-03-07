// function that given a parameter [tutte,attuali,passate,disdette]
// return the desired repetition list 
function ripetizioni(param) {
    let usr = document.querySelector("h1").innerHTML.split(" ")[1];
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function() {
	if (xhr.readyState == 4 && xhr.status == 200) {
	    //Display repetition list inside main div
	    let sec = document.querySelector("section");
	    sec.innerHTML = xhr.responseText;
	    if (param == "attuali")
		buttPrenotazione();
	}
    }
    xhr.open('GET', "Ripetizioni?param="+ param +"&usr="+ usr, true);
    xhr.send(null);
}

function buttPrenotazione () {
    let tdatas = document.querySelectorAll('td');
    for(let i=0; i<tdatas.length; i++) {
	if(i%7 == 5)
	    tdatas[i].innerHTML= "<button onclick=donePrenotazione("+ Math.floor(i/7) +")>Fatto</button>";
	if(i%7 == 6)
	    tdatas[i].innerHTML= "<button onclick=annulPrenotazione("+ Math.floor(i/7) +")>Disdici</button>";
    }
}

let attrip =  document.querySelector('#usratt');
attrip.addEventListener("click", function () {ripetizioni("attuali")});
let passrip =  document.querySelector('#usrpass');
passrip.addEventListener("click", function () {ripetizioni("passate")});
let disdrip =  document.querySelector('#usrdisd');
disdrip.addEventListener("click", function () {ripetizioni("disdette")});

ripetizioni("attuali");
