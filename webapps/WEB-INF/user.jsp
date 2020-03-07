<!DOCTYPE>
<html lang="it">
  <head>
    <title>RipetizioniIUM</title>
    <link rel="stylesheet" href="css/rip3.css"/>
    <link rel="stylesheet" type="text/css" href="http://fonts.googleapis.com/css?family=Schoolbell">
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.min.js"></script>
    <script src="script/login.js"></script>
    <script src="script/personal.js" async></script>
    <script src="script/client-visualizza.js" async></script>
    <script src="script/client-prenotazioni.js" async></script>

  </head>
  <body>
    <header>
      <!-- logo non esiste ancora-->
      <!--<img id="logo" alt="Studia&Studia" src="./img/logo.jpg"/>-->
      SblockNotes
    </header>
    <nav>
      <div>
	<a href=<%= response.encodeUrl("home.jsp") %> class="menu">Home</a>
      </div>
      <div>
	<a href="contacts.html" class="menu">Contatti</a>
      </div>
      <div class="dropdown">
	<a href="login.html" class="dropdown menu"><%= session.getAttribute("user") %></a>
	<div class="dropdown-content"
	     ng-app="login" ng-controller="loginCtrl">
	  <fieldset>
	    <a class="logged" href=<%= response.encodeUrl("home.jsp") %>>Prenota Lezione</a><br/>
	    <a class="logged" href=<%= response.encodeUrl("user.jsp") %>>Storico Lezioni</a><br/>
	  </fieldset>
	  <a class="logged" href="invalidateSession.jsp">Log-out</a>
	</div>
      </div>
    </nav>

    
    
    <div id="ripetizioni">
      <h1>Bentornato <%= session.getAttribute("user") %></h1>
      <button id="usratt">Lezioni Prenotate</button>
      <button id="usrpass">Lezioni Frequentate</button>
      <button id="usrdisd">Lezioni Disdette</button>
      <section>
	<h2><span>Materia</span><span>Docente</span><span>Data</span></h2>

      </section>
    </div>
    <p style="height: 5vw"></p>

    <footer>
      Se non studi peschi granchi  
    </footer>
  </body>
</html>
