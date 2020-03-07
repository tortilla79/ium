// function that given a parameter [tutte,attuali,passate,disdette]
// return the desired repetition list 
function ripetizioni(param) {
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function() {
	if (xhr.readyState == 4 && xhr.status == 200) {
	    //Display repetition list inside main div
	    let div = document.querySelector("div");
	    div.innerHTML = xhr.responseText;
	}
    }
    xhr.open('GET', "Ripetizioni?param=" + param, true);
    xhr.send(null);
}

let allrip = document.querySelector('#rip');
allrip.addEventListener("click", function () {ripetizioni("tutte")})
let attrip =  document.querySelector('#ripatt');
attrip.addEventListener("click", function () {ripetizioni("attuali")});
let passrip =  document.querySelector('#rippass');
passrip.addEventListener("click", function () {ripetizioni("passate")});
let disdrip =  document.querySelector('#ripdisd');
disdrip.addEventListener("click", function () {ripetizioni("disdette")});
