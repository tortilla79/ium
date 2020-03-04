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
	    b1.onclick = function(){creaDoc("crea");};
	    b2.onclick = function(){eliminaDoc("elimina");};
	}
	creaDoc("crea");
	
    }
    
    function creaDoc(buttId) {
	let label = document.createElement("label");
	let tfield = document.createElement("input");
	let submit = document.createElement("input");

	submit.setAttribute("name", buttId);

	while (div.childElementCount > 2) {
	    div.removeChild(div.lastChild);
	}
	if (div.childElementCount < 3) {
	    label.textContent = "Inserisci il nome del docente: ";
	    tfield.id = "tfdoc";
	    tfield.type = "textfield";
	    submit.type = "submit";
	    submit.value = buttId;

	    
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

	submit.onclick = function(){submitAction("crea")};

    }

    function eliminaDoc(buttId) {
	creaDoc(buttId);
	let select = document.createElement("select");
	let torepl = document.querySelector("#tfdoc");

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
		select.id = "tfdoc";
	    }
	}
	xhr.open('GET', "ItemGetter?param=doc", true);
	xhr.send(null);
	
	let submit = document.querySelector("input[name='elimina']");
	submit.onclick = function(){submitAction("elimina")};

    }

    function submitAction(action) {
	let val;
	if (action == "crea")
	    val = document.querySelector("#tfdoc").value;
	else {
	    let select = document.querySelector("#tfdoc");
	    val = select.options[select.selectedIndex].value;
	}
	let field = val.split(" ");
	let xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
	    if (xhr.readyState == 4 && xhr.status == 200) {
		if(!alert(xhr.response)){
		    if (action == "crea")
	    		creaDoc("crea");
		    else
		    eliminaDoc("elimina");

		}
	    }
	}
	let path =  "DataStorage?param="+ action +"doc&data1="+ field[0] +"&data2="+ field[1];
	xhr.open('GET', path, true);
	xhr.send(null);
	
    }
    
    let doc = document.querySelector('#doc');

    doc.addEventListener("click", docente);


}

adminDoc();
