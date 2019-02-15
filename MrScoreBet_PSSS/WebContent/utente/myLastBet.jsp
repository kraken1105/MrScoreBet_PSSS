<!DOCTYPE html>

<%@page import="java.util.ArrayList"%>
<%@page import="modelMVC.*"%>
<%
	//Utente in sessione
	Utente utente = (Utente) session.getAttribute("utente");

	// Ultimo pronostico
	Pronostico lastPlayed = utente.getLastPlayedBet();
	
	if (lastPlayed == null) {
		ArrayList<String> gameList = new ArrayList<String>();
		for(int i=1; i<11; i++) gameList.add(new String(""));
		lastPlayed = new Pronostico(0,gameList,new Schedina(0,null,gameList,new Esito(0,gameList)),null);
		
		session.setAttribute("info", "Non è stato trovato alcun pronostico!");
		response.sendRedirect(request.getContextPath()+"/user.jsp");
	} else {
		if(lastPlayed.getSchedina().getEsito()==null) {
			ArrayList<String> gameList = new ArrayList<String>();
			for(int i=1; i<11; i++) gameList.add(new String(""));
			lastPlayed.getSchedina().setEsito(new Esito(0,gameList));
		}			
	}
%>

<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Last bet</title>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/4.2.0/normalize.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/style.css">

	<meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">

</head>
<body>

	<header class="header clearfix">
		<ul class="header__left">
			<li class="header__left__item"><a href="<%=request.getContextPath()%>/index.jsp">.</a></li>
		</ul>
		<a href="" class="header__icon-bar">
			<span></span>
			<span></span>
			<span></span>
		</a>
		<ul class="header__menu animate">
			<li class="header__menu__item"><a href="<%=request.getContextPath()%>/user.jsp">Area Personale</a></li>
			<li class="header__menu__item"><a href="<%=request.getContextPath()%>/LogServlet">Logout</a></li>
		</ul>
	</header>

	<section class="cover cover--single">
		<div class="cover__filter"></div>
		<div class="cover__caption">
			<div class="cover__caption__copy">
				<h1>Ultima schedina giocata</h1>
				<h2></h2>
			</div>
		</div>
	</section>

	<article class="panel">
		<div class="panel__copy">
			<h2 align="center">Serie A giornata <%=lastPlayed.getSchedina().getGiornata()%></h2>

			<div class="mrw-table mrw-grid">
			    <div class="mrw-tr">
			        <div class="mrw-th mrw-width-50 mrw-center">Partita</div>
			        <div class="mrw-th mrw-width-25 mrw-center">Pronostico</div>
			        <div class="mrw-th mrw-width-25 mrw-center">Risultato</div>
			    </div>
			    <div class="mrw-tr">
			        <div class="mrw-td mrw-width-50 mrw-center"><%=lastPlayed.getSchedina().getGameList().get(0)%></div>
			        <div class="mrw-td mrw-width-25 mrw-center"><%=lastPlayed.getResultsList().get(0)%></div>
			        <div class="mrw-td mrw-width-25 mrw-center"><% if(lastPlayed.getSchedina().getEsito() == null) out.print(" "); 
			        												else out.print(lastPlayed.getSchedina().getEsito().getResultsList().get(0)); %></div>
			    </div>
			    <div class="mrw-tr">
			        <div class="mrw-td mrw-width-50 mrw-center"><%=lastPlayed.getSchedina().getGameList().get(1)%></div>
			        <div class="mrw-td mrw-width-25 mrw-center"><%=lastPlayed.getResultsList().get(1)%></div>
			        <div class="mrw-td mrw-width-25 mrw-center"><% if(lastPlayed.getSchedina().getEsito() == null) out.print(" "); 
			        												else out.print(lastPlayed.getSchedina().getEsito().getResultsList().get(1)); %></div>
			    </div>
			    <div class="mrw-tr">
			        <div class="mrw-td mrw-width-50 mrw-center"><%=lastPlayed.getSchedina().getGameList().get(2)%></div>
			        <div class="mrw-td mrw-width-25 mrw-center"><%=lastPlayed.getResultsList().get(2)%></div>
			        <div class="mrw-td mrw-width-25 mrw-center"><% if(lastPlayed.getSchedina().getEsito() == null) out.print(" "); 
			        												else out.print(lastPlayed.getSchedina().getEsito().getResultsList().get(2)); %></div>
			    </div>
			    <div class="mrw-tr">
			        <div class="mrw-td mrw-width-50 mrw-center"><%=lastPlayed.getSchedina().getGameList().get(3)%></div>
			        <div class="mrw-td mrw-width-25 mrw-center"><%=lastPlayed.getResultsList().get(3)%></div>
			        <div class="mrw-td mrw-width-25 mrw-center"><% if(lastPlayed.getSchedina().getEsito() == null) out.print(" "); 
			        												else out.print(lastPlayed.getSchedina().getEsito().getResultsList().get(3)); %></div>
			    </div>
			    <div class="mrw-tr">
			        <div class="mrw-td mrw-width-50 mrw-center"><%=lastPlayed.getSchedina().getGameList().get(4)%></div>
			        <div class="mrw-td mrw-width-25 mrw-center"><%=lastPlayed.getResultsList().get(4)%></div>
			        <div class="mrw-td mrw-width-25 mrw-center"><% if(lastPlayed.getSchedina().getEsito() == null) out.print(" "); 
			        												else out.print(lastPlayed.getSchedina().getEsito().getResultsList().get(4)); %></div>
			    </div>
			    <div class="mrw-tr">
			        <div class="mrw-td mrw-width-50 mrw-center"><%=lastPlayed.getSchedina().getGameList().get(5)%></div>
			        <div class="mrw-td mrw-width-25 mrw-center"><%=lastPlayed.getResultsList().get(5)%></div>
			        <div class="mrw-td mrw-width-25 mrw-center"><% if(lastPlayed.getSchedina().getEsito() == null) out.print(" "); 
			        												else out.print(lastPlayed.getSchedina().getEsito().getResultsList().get(5)); %></div>
			    </div>
			    <div class="mrw-tr">
			        <div class="mrw-td mrw-width-50 mrw-center"><%=lastPlayed.getSchedina().getGameList().get(6)%></div>
			        <div class="mrw-td mrw-width-25 mrw-center"><%=lastPlayed.getResultsList().get(6)%></div>
			        <div class="mrw-td mrw-width-25 mrw-center"><% if(lastPlayed.getSchedina().getEsito() == null) out.print(" "); 
			        												else out.print(lastPlayed.getSchedina().getEsito().getResultsList().get(6)); %></div>
			    </div>
			    <div class="mrw-tr">
			        <div class="mrw-td mrw-width-50 mrw-center"><%=lastPlayed.getSchedina().getGameList().get(7)%></div>
			        <div class="mrw-td mrw-width-25 mrw-center"><%=lastPlayed.getResultsList().get(0)%></div>
			        <div class="mrw-td mrw-width-25 mrw-center"><% if(lastPlayed.getSchedina().getEsito() == null) out.print(" "); 
			        												else out.print(lastPlayed.getSchedina().getEsito().getResultsList().get(0)); %></div>
			    </div>
			    <div class="mrw-tr">
			        <div class="mrw-td mrw-width-50 mrw-center"><%=lastPlayed.getSchedina().getGameList().get(7)%></div>
			        <div class="mrw-td mrw-width-25 mrw-center"><%=lastPlayed.getResultsList().get(7)%></div>
			        <div class="mrw-td mrw-width-25 mrw-center"><% if(lastPlayed.getSchedina().getEsito() == null) out.print(" "); 
			        												else out.print(lastPlayed.getSchedina().getEsito().getResultsList().get(7)); %></div>
			    </div>
			    <div class="mrw-tr">
			        <div class="mrw-td mrw-width-50 mrw-center"><%=lastPlayed.getSchedina().getGameList().get(8)%></div>
			        <div class="mrw-td mrw-width-25 mrw-center"><%=lastPlayed.getResultsList().get(8)%></div>
			        <div class="mrw-td mrw-width-25 mrw-center"><% if(lastPlayed.getSchedina().getEsito() == null) out.print(" "); 
			        												else out.print(lastPlayed.getSchedina().getEsito().getResultsList().get(8)); %></div>
			    </div>
			</div>			

			<h2 align="center">Punti totalizzati</h2>
			<p>Punti ottenuti: <b><% if(lastPlayed.getPunti()==null) out.print("?"); 
			        				  else out.print(lastPlayed.getPunti()); %> pts</b></p>
			<p>Punti totali: <b><%=utente.getCrediti()%> pts</b></p>
			<p></p>
			<p><b>Nota:</b> accumuli 10 punti per ogni esito correttamente pronosticato, e nel caso di schedina completamente esatta ulteriori 100.</p>

			<p>Ritorna alla tua <a href="<%=request.getContextPath()%>/user.jsp">Area Personale</a>.</p>
		</div>

	</article>



<footer class="footer">
	<p>Copyright - 2018 PicRof</p>
</footer>


<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
<script>
	 $(document).ready(function(){

			$(".header__icon-bar").click(function(e){

				$(".header__menu").toggleClass('is-open');
				e.preventDefault();

			});
	 });
</script>


</body>
</html>
