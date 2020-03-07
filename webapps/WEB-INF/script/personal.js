let bts = document.querySelectorAll("#ripetizioni button");

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
