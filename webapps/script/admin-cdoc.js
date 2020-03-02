function adminCDoc() {
    let div = document.querySelector("div");

    function docente() {
	while (div.firstChild) {
	    div.removeChild(div.firstChild);
	}
	let b1 = document.createElement("button");
	let b2 = document.createElement("button");
	let text = document.createTextNode("Aggiungi");
	let text1 = document.createTextNode("Rimuovi");
	if (div.childElementCount === 0) {
	    b1.appendChild(text);
	    div.appendChild(b1);
	    b2.appendChild(text1);
	    div.appendChild(b2);
	   // b1.onclick = creacDoc;
	    //	    b2.onclick = eliminacDoc;
	    b1.addEventListener('click', function() {creacDoc("Aggiungi")});
	    b2.addEventListener('click', function() {eliminacDoc("Rimuovi")});
	}
	eliminacDoc("Rimuovi");
	// div.innerHtml = '<button name="crea">Aggiungi</button><button name="elimina">Rimuovi</button>';
    }

    function creacDoc(labText) {
	while(div.lastChild.nodeName !== "BUTTON")
	    div.removeChild(div.lastChild);
	let labelC = document.createElement("label");
	let tfieldC = document.createElement("input");
	let labelD = document.createElement("label");
	let tfieldD = document.createElement("input");
	let submit = document.createElement("input");

	if (div.childElementCount < 3) {
	    labelC.textContent = labText + " al corso ";
	    tfieldC.type = "textfield";
	    labelD.textContent = "il docente ";
	    tfieldD.type = "textfield";
	    submit.type = "submit";
	    submit.value = "Conferma";

	    
	    div.appendChild(labelC);
	    div.appendChild(tfieldC);
	    div.appendChild(document.createElement("br"));
	    div.appendChild(labelD);
	    div.appendChild(tfieldD);
	    div.appendChild(document.createElement("br"));
	    div.appendChild(submit);

	    let bts = document.querySelectorAll("button");

	    function hideBorder(indx) {
		for (let i=0; i<bts.length; i++) {
		    if (indx === i)
			bts[i].style = "background: blue;";
		    else
			bts[i].style = "background: white;";
		}
		
	    }

	    for(let i=0; i<bts.length; i++)
		bts[i].addEventListener("click", function() {hideBorder(i)});

	}
    }

    function eliminacDoc(labText) {
	creacDoc(labText);
    }

    let cdoc = document.querySelector('#cdoc');

    cdoc.addEventListener("click", docente);
    

}

adminCDoc();
