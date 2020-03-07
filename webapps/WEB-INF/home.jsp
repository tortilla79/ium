<!DOCTYPE>
<html lang="it">
  <head>
    <meta charset="UTF-8"/>
    <title>RipetizioniIUM</title>
    <link rel="stylesheet" href="css/rip3.css"/>
    <link rel="stylesheet" type="text/css" href="http://fonts.googleapis.com/css?family=Schoolbell">
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.min.js"></script>
    <script src="script/login.js"></script>
    <script src="script/calendar.js"></script>
  </head>
  <body>
    <header>
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

    <section>
      <div class="search">
	<label for="subject">Materia: </label>
	<input id="subject" ng-model="subject"/>
	<label for="teacher">Insegnante: </label>
	<input id="teacher" ng-model="teacher"/>
	<input type="submit" value="Cerca" id="buttonFilter"/>
	<p ng-model="search-error"></p>
      </div>
      <main>
	<table>
	  <caption>Orario Ripetizioni</caption>
	  <thead>
	    <tr>
	      <th scope="col">Ora</th>
	      <th scope="col">Lun</th>
	      <th scope="col">Mar</th>
	      <th scope="col">Mer</th>
	      <th scope="col">Gio</th>
	      <th scope="col">Ven</th>
	    </tr>
	    
	  </thead>
	  <tbody>
	    
	    <tr><th scope="row">15-16</th><td></td><td></td><td></td><td></td><td></td></tr>
	    <tr><th scope="row">16-17</th><td></td><td></td><td></td><td></td><td></td></tr>
	    <tr><th scope="row">17-18</th><td></td><td></td><td></td><td></td><td></td></tr>
	    <tr><th scope="row">18-19</th><td></td><td></td><td></td><td></td><td></td></tr>
	    
	  </tbody>
	  <tfooter>
	    <th>Legenda:</th>
	  </tfooter>
	</table>
      </main>
      <!--  <aside ng-app="ripcalendar-filter" ng-controller="filter-ctrl">
	    <div id="radiob">
	      <input type="radio" name="subject" checked>
	      <input type="radio" name="teacher">
	    </div>
	    <div id="search">
	      <label for="subject">Materia: </label>
	      <input id="subject" ng-model="subject"/>
	      <label for="teacher">Insegnante: </label>
	      <input id="teacher" ng-model="teacher"/><br>
	      <input type="submit" value="Cerca" id="buttonFilter"/>
	      <p ng-model="search-error"></p>
	    </div>
      </aside>-->
    </section>
    <footer>
      Se non studi peschi granchi  
    </footer>
  </body>
</html>

<!--	
	  if(request.getSession(false) == null)
	  out.print("<a href=\"login.html\" class=\"dropdown menu\">Accedi</a><div class=\"dropdown-content\"
	     ng-app=\"login\" ng-controller=\"loginCtrl\"> <label for=\"user\">User</label><br>  <input ng-model=\"username\" id=\"user\" placeholder=\"gino il formaggino\"><br>  <label for=\"password\">Password</label><br>
	  <input ng-model=\"password\" id=\"password\" type=\"password\"><br> <input type=\"submit\" value=\"Log-in\" ng-click=\"sendData()\"> <p ng-bind=\"requestans\"></p> <a href=\"registration.html\">Registrati</a></div>");

	  else {
	  out.print("<a href=\"login.html\" class=\"dropdown menu\">"+ session.getAttribute("user") +"</a>
	<div class=\"dropdown-content\"
	     ng-app=\"login\" ng-controller=\"loginCtrl\">
	  <fieldset>
	    <a class=\"logged\" href="+ response.encodeUrl("home.html") +">Prenota Lezione</a><br/>
	    <a class=\"logged\" href="+ response.encodeUrl(\"user.jsp\") +">Storico Lezioni</a><br/>
	  </fieldset>
	  <a class=\"logged\" href=\"invalidateSession.jsp\">Log-out</a>
	</div>");

	  }
	  
	  -->
