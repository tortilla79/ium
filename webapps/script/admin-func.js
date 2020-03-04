function admin(func) {
    let div = document.querySelector("div");

    function func() {
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
	    b1.onclick = function(){crea("crea");};
	    b2.onclick = function(){elimina("elimina");};
	}
	crea("crea");
	// div.innerHtml = '<button name="crea">Aggiungi</button><button name="elimina">Rimuovi</button>';
    }

    function crea(buttId) {
	let label = document.createElement("label");
	let tfield = document.createElement("input");
	let submit = document.createElement("input");

	submit.setAttribute("name", buttId);

	while (div.childElementCount > 2) {
	    div.removeChild(div.lastChild);
	}
	
	if (div.childElementCount < 3) {

	    label.textContent = "Inserisci il nome del " + func + ": ";
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

    function elimina(buttId) {
	crea(buttId);
    }

}

function adminCorso() {
    let corsi = document.querySelector('#corso');

    corsi.addEventListener("click", function() {admin("corso");});

}

adminCorso();
