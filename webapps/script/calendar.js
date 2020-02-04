document.addEventListener("DOMContentLoaded", function(){
    
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function() {
	if (xhr.readyState == 4 && xhr.status == 200) {
	    let tdatas = document.querySelectorAll('td');
	    for(let i=0; i<tdatas.length; i++)
		tdatas[i].innerHTML=xhr.responseText;
	}
    }
    xhr.open('GET', "Calendar", true);
    xhr.send(null);
    
    //popTable();
    
});
