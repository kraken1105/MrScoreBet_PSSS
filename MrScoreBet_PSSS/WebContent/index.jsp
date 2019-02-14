<!DOCTYPE html>

<%
	String info = (String) session.getAttribute("info");
	if(info!=null && !info.equals("null")) {
		out.print("<script> alert(\""+info+"\"); </script> ");
		session.setAttribute("info","null");	// reset dell'info
	}
	
	boolean logged_in = false;
	if(session.getAttribute("utente")!=null) logged_in=true;
%>

<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Mr.ScoreBet</title>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/4.2.0/normalize.css">
	<link rel="stylesheet" href="style.css">

	<meta http-equiv="X-UA-Compatible" content="IE=edge">
  	<meta name="viewport" content="width=device-width, initial-scale=1">
  
	<script>
	  	function Modulo1() {
	  		if (!validazione_psw(document.getElementById("username1").value)) {alert("L'username deve contenere almeno 6 caratteri e massimo 20!"); document.getElementById("username1").focus(); return false;}
	  		else if (!validazione_psw(document.getElementById("psw1").value)) {alert("La password deve contenere almeno 6 caratteri e massimo 20!"); document.getElementById("psw1").focus(); return false;}
			//INVIA IL MODULO
			else {
				document.getElementById("formBet1").action = "<%=request.getContextPath()%>/LogServlet";
				document.getElementById("formBet1").submit();		
			}
		}
	  	
	  	function Modulo2() {
	  		if (!validazione_email(document.getElementById("email2").value)) {alert("Indirizzo email non valido!"); document.getElementById("email2").focus(); return false;}
	  		else if (!validazione_psw(document.getElementById("username2").value)) {alert("L'username deve contenere almeno 6 caratteri e massimo 20!"); document.getElementById("username2").focus(); return false;}
	  		else if (!validazione_psw(document.getElementById("psw2").value)) {alert("La password deve contenere almeno 6 caratteri e massimo 20!"); document.getElementById("psw2").focus(); return false;}
			//INVIA IL MODULO
			else {
				document.getElementById("formBet2").action = "<%=request.getContextPath()%>/RegisterServlet";
				document.getElementById("formBet2").submit();		
			}
		}
	  	
	  	function validazione_email(email) {
	  	  var reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
	  	  if (!reg.test(email)) return false; else return true;
	  	}
	  	
	  	function validazione_psw(psw) {
		  var reg = /^.{6,20}$/;
		  if (!reg.test(psw)) return false; else return true;
		}
	</script>

</head>
<body>

	<!--border: 1px solid red-->
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
			<li class="header__menu__item" <% if(!logged_in) out.print("style=\"display: none\"");%>><a href="<%=request.getContextPath()%>/Logout">Logout</a></li>
		</ul>
	</header>


	<section class="cover">
		<div class="cover__filter"></div>
		<div class="cover__caption">
			<div class="cover__caption__copy">
				<h1> Mr.ScoreBet</h1>
				<h2> Bet for free, win for real!</h2>				
			</div>
		</div>	

		<article class="panel">
			<div class="panel__copy2">
				<h2 align="center">Gioca subito!</h2>

				<div class="panel__card__copy2">
					<div class="panel__card__copy__text">
						<p>Login:</p>
						<form id="formBet1" method="post">				
							<input type="text" id="username1" name="username1" placeholder="username"><br>
							<input type="password" id="psw1" name="psw1" placeholder="password">
							<br><br>
							<input type="button" value="Login" onClick="Modulo1()">
						</form>
					</div>
				</div>

				<div class="panel__card__copy3">
					<div class="panel__card__copy__text">
						<p>Registrati:</p>
						<form id="formBet2" method="post">
							<input type="text" id="email2" name="email2" placeholder="email"><br>
							<input type="text" id="username2" name="username2" placeholder="username"><br>
							<input type="password" id="psw2" name="psw2" placeholder="password">
							<br><br>
							<input type="button" value="Registrati" onClick="Modulo2()">
				 			<input type="reset" value="Reset">
						</form>
					</div>
				</div>

				<p style="color:white;">.</p>
			</div>
		</article>

	</section>


	<section class="cards clearfix">
		<div class="card">
			<img class="card__image" src="images/400x260_1.jpg">
			<div class="card__copy">
				<h3>Intuizione</h3>
				<p>Scendi in campo e piazza la tua scommessa in modo completamente gratuito. Basta avere un account Facebook.</p>
			</div>
		</div>
		<div class="card">
			<img class="card__image" src="images/400x260_3.jpg">
			<div class="card__copy">
				<h3>Emozione</h3>
				<p>Segui le partite ed esulta quando i tuoi pronostici si avverano. Accumulerai punti per ogni risultato esatto.</p>
			</div>
		</div>
		<div class="card">
			<img class="card__image" src="images/400x260_2.jpg">
			<div class="card__copy">
				<h3>Vittoria</h3>
				<p>Vinci come un vero campione e ritira la tua vincita in relazione ai punti accumulati con tutti i tuoi pronostici esatti.</p>
			</div>
		</div>
	</section>


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
