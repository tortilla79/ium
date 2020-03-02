function adminDoc() {
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
	    b1.onclick = creaDoc;
	    b2.onclick = eliminaDoc;
	}
	creaDoc();
	
    }
    
    function creaDoc() {
	let label = document.createElement("label");
	let tfield = document.createElement("input");
	let submit = document.createElement("input");
	
	if (div.childElementCount < 3) {
	    label.textContent = "Inserisci il nome del docente: ";
	    tfield.id = "docente";
	    tfield.type = "textfield";
	    submit.type = "submit";
	    submit.value = "Conferma";

	    
	    div.appendChild(label);
	    div.appendChild(tfield);
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

    function eliminaDoc() {
	creaDoc();
    }

    let doc = document.querySelector('#doc');

    doc.addEventListener("click", docente);


}

adminDoc();
