function adminCorso() {
    let div = document.querySelector("div");

    function corso() {
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
	    b1.onclick = function(){creaCorso("crea");};
	    b2.onclick = function(){eliminaCorso("elimina");};
	}
	creaCorso("crea");
	// div.innerHtml = '<button name="crea">Aggiungi</button><button name="elimina">Rimuovi</button>';
    }

    function creaCorso(buttId) {
	let label = document.createElement("label");
	let tfield = document.createElement("input");
	let submit = document.createElement("input");
	let p = document.createElement("p");
	tfield.setAttribute("id", "tfcorsi");
	submit.setAttribute("name", buttId);

	while (div.childElementCount > 2) {
	    div.removeChild(div.lastChild);
	}
	
	if (div.childElementCount < 3) {

	    label.textContent = "Inserisci il nome del corso: ";
	    tfield.type = "textfield";
	    submit.type = "submit";
	    submit.value = buttId;

	    
	    div.appendChild(label);
	    div.appendChild(tfield);
	    div.appendChild(submit);
	    div.appendChild(p);

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

	submit.onclick = function(){submitAction("crea")};
    }

    function eliminaCorso(buttId) {
	creaCorso(buttId);

	let select = document.createElement("select");
	let torepl = document.querySelector("#tfcorsi");

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
		div.replaceChild(select, torepl);
		select.id = "tfcorsi"; 
	    }
	}
	xhr.open('GET', "ItemGetter?param=corsi", true);
	xhr.send(null);

	torepl.setAttribute("id", "tfcorsi");
	let submit = document.querySelector("input[name='elimina']");
	submit.onclick = function(){submitAction("elimina")};
    }

    function submitAction(action) {
	let val;
	if (action == "crea")
	    val = document.querySelector("#tfcorsi").value;
	else {
	    let select = document.querySelector("#tfcorsi");
	    val = select.options[select.selectedIndex].value;
	}
	let xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
	    if (xhr.readyState == 4 && xhr.status == 200) {
		if(!alert(xhr.response)){
		    if (action == "crea")
	    		creaCorso("crea");
		    else
			eliminaCorso("elimina");
		}
	    }
	}
	xhr.open('GET', "DataStorage?param="+ action +"corso&data1=" + val, true);
	xhr.send(null);
	
    }
    
    let corsi = document.querySelector('#corso');

    corsi.addEventListener("click", corso);

}

adminCorso();
