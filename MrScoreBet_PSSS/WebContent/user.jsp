<!DOCTYPE html>

<%@page import="modelMVC.*"%>
<%@page import="java.time.LocalDateTime"%>
<%
	// Utente in sessione
	Utente utente = (Utente) session.getAttribute("utente");
	
	// Dati relativi all'utente
	Pronostico lastPlayed = utente.getLastPlayedBet();	
	Schedina toPlayBet = utente.getToPlayBet();
	
	String lastPlayedGiornata = new String("-");
	if (lastPlayed != null) lastPlayedGiornata = String.valueOf(lastPlayed.getSchedina().getGiornata());
	
	LocalDateTime data = null;
	String toPlayGiornata = new String("-");
	if (toPlayBet != null) {
		data = toPlayBet.getDataScadenza();
		toPlayGiornata = String.valueOf(toPlayBet.getGiornata());		
	}
	
	// Per msg info
	String info = (String) session.getAttribute("info");
	if(info!=null && !info.equals("null")) {
		out.print("<script> alert(\""+info+"\"); </script> ");
		session.setAttribute("info","null");	// reset dell'info
	}	
%>

<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Area Personale</title>
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
				<h1>Area Personale</h1>
				<h2></h2>
			</div>
		</div>
	</section>

	<article class="panel">
		<div class="panel__copy">
			<h2 align="center">Dati personali</h2>

			<p>Username: <br></br><b><%=""+utente.getUsername() %></b></p>
			<p>E-mail: <br></br><b><%=""+utente.getEmail() %></b></p>

			<h2 align="center">Schedine</h2>
			<p>Punti totali: <b><%=""+utente.getCrediti() %> pts</b></p>
<!-- TO-DO: sistemare query string per indirizzamneto alla servlet -->
			<p>Ultima schedina giocata: <a href="<%=request.getContextPath()%>/app/bets?to=myLastBet"><%=lastPlayedGiornata%> giornata </a></p>
			<p>Nuova schedina da giocare: <a href="<%=request.getContextPath()%>/app/bets?to=placeMyBet"><%=toPlayGiornata%> giornata </a><b id="mytimer"></b></p>
		
		</div>

		<!-- Questo pannello compare solo se è loggato un admin -->
		<div class="panel__copy3" <% if(!utente.getRuolo().equals("admin")) out.print("style=\"display: none\"");%> > 
			<h2 align="center">Area admin</h2>

			<p>Inserisci i <a href="<%=request.getContextPath()%>/admin/InsertEsitoServlet">risultati (esiti)</a> di una giornata</p>
			<p>Inserisci una <a href="<%=request.getContextPath()%>/admin/InsertBetServlet">nuova schedina</a></p>
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


<!-- Codice JavaScript per l'aggiornamento del timer -->
<script>
	function countDown(second,endMinute,endHour,endDay,endMonth,endYear) {
		var now = new Date();
		second = (arguments.length == 1) ? second + now.getSeconds() : second;
		endYear =  typeof(endYear) != 'undefined' ?  endYear : now.getFullYear();            
		endMonth = endMonth ? endMonth - 1 : now.getMonth();
		endDay = typeof(endDay) != 'undefined' ? endDay :  now.getDate();    
		endHour = typeof(endHour) != 'undefined' ?  endHour : now.getHours();
		endMinute = typeof(endMinute) != 'undefined' ? endMinute : now.getMinutes();
		var endDate = new Date(endYear,endMonth,endDay,endHour,endMinute,second+1);
		var interval = setInterval(function() {
		    var time = endDate.getTime() - now.getTime();
		    if (time < 0) {                      //se la data finale impostata è meno di 1 secondo
		    	document.getElementById('mytimer').innerHTML = '(giornata iniziata)';           
		    } else {           
		        var days = Math.floor(time / 864e5);
		        var hours = Math.floor(time / 36e5) % 24;
		        var minutes = Math.floor(time / 6e4) % 60;
		        var seconds = Math.floor(time / 1e3) % 60; 
		        var digit='<div style="width:70px;float:left;text-align:center">'+
		        '<div style="font-family:Stencil;font-size:55px;">';// grandezza e famiglia font
		        var text='</div><div>'
		        var end='</div></div><div style="float:left;font-size:45px;">:</div>'
		        
		        if (!seconds && !minutes && !days && !hours) {             
		        	document.getElementById('mytimer').innerHTML = '(giornata iniziata)';             
		        } else {
		        	document.getElementById('mytimer').innerHTML = '(' + days +'g '+ hours + 'h ' + minutes + 'm ' + seconds +'s rimanenti)';
		        }         
		    }
		    now.setSeconds(now.getSeconds() + 1);
		}, 1000);
	}
	
	<%if(data != null) out.print("countDown("+data.getSecond()+","+data.getMinute()+","+data.getHour()+","+
		data.getDayOfMonth()+","+data.getMonthValue()+","+data.getYear()+");");
	  else
		  out.print("document.getElementById('mytimer').innerHTML = '(non disponibile)';");%>
</script>



</body>
</html>