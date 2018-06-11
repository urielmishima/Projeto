var contador = 0;
var firstTime = true;
var atendimentoHumano = false;
var lastIdMensagem = 0;
$(document).ready(function(){
		var cliente = JSON.parse(localStorage.getItem("cliente"));
		var msg = `Olá, ${cliente.nome}. Qual a sua Duvida?`;
		appendReceive(msg);
	
});

$(".btn-enviar").click(function(){
	var duvida = $("#inputMessage").val();
	var cliente = JSON.parse(localStorage.getItem("cliente"));
	if (firstTime) {
		appendSend(duvida);
		$.get("ServletController.do", `command=NewChatBot&duvida=${duvida}&idCliente=${cliente.id}`).done(function(respostas) {
			localStorage.setItem("respostas", respostas);
			var respostas2 = JSON.parse(localStorage.getItem("respostas"));
			appendReceive(respostas2[contador].resposta);
			feedBack();
		});
		firstTime = false;
	}
	else if (atendimentoHumano) {
		appendSend(duvida);
		$.post("ServletController.do", `command=NewMensagemCliente&idCliente=${cliente.id}&mensagemEnviada=${duvida}`);
	}
});

$(".btn-fim").click(function(){
	var cliente = JSON.parse(localStorage.getItem("cliente"));
	if (atendimentoHumano) {		
		$.post("ServletController.do", `command=EndChat&idCliente=${cliente.id}`).done(function(){
			localStorage.clear();
			window.location.assign("obrigado.html");			
		});
	}
	else {
		var respostas = JSON.parse(localStorage.getItem("respostas"));
		var pontuacao = respostas[contador].pontuacao;			
		$.post("ServletController.do", `command=EndChatBot&idCliente=${cliente.id}&idPalavraChave=${pontuacao.idPalavraChave}&idResposta=${pontuacao.idResposta}&contador=${contador}`).done(function(){
			localStorage.clear();
			window.location.assign("obrigado.html");
		});
	}
});

var feedBack = () => {
	var cliente = JSON.parse(localStorage.getItem("cliente"));	
		var templateSend = `<div class="row msg_container base_receive"><div class="col-md-1 col-xs-1 avatar"><img src="https://d30y9cdsu7xlg0.cloudfront.net/png/415507-200.png" class=" img-responsive "></div><div class="col-xs-4 col-md-4"><div class="messages msg_receive"><p>Satisfeito/a com a resposta, ${cliente.nome}?</p></div></div><div class="col-xs-1 col-md-1 w-25 h-25 rounded feedBackButton"><button id="nao" type="submit" class="btn btn-enviar btn-warning feedBackButton">Não</button></div><div class="col-xs-1 col-md-1 h-25 w-25 rounded feedBackButton"><button type="submit" id="sim" class="btn btn-enviar btn-warning feedBackButton">Sim</button></div></div>`
		$(".boxMessage").append(templateSend);
	
	$("#sim").click(function(){
		var cliente = JSON.parse(localStorage.getItem("cliente"));
		var respostas = JSON.parse(localStorage.getItem("respostas"));
		var pontuacao = respostas[contador].pontuacao;			
		$.post("ServletController.do", `command=EndChatBot&idCliente=${cliente.id}&idPalavraChave=${pontuacao.idPalavraChave}&idResposta=${pontuacao.idResposta}&contador=${contador}`).done(function(){
			localStorage.clear();
			window.location.assign("obrigado.html");
		});
	});
	$("#nao").click(function(){		
		var cliente = JSON.parse(localStorage.getItem("cliente"));
		var respostas = JSON.parse(localStorage.getItem("respostas"));
		$(".feedBackButton").remove();
		appendSend("Não");
		if (contador < 3) {
			var pontuacao = respostas[contador].pontuacao;
			$.post("ServletController.do", `command=NegativarResposta&idCliente=${cliente.id}&idPalavraChave=${pontuacao.idPalavraChave}&idResposta=${pontuacao.idResposta}&contador=${contador}`);	
		}
		if (contador < 2) {
			contador++;
			appendReceive(respostas[contador].resposta);
			feedBack();
		}
		else if (contador < 2) {				
			contador++;
			feedBack();
			
		} else {
			appendReceive("<strong>O atendimento será transferido para um atendente humano.<br/> Aguarde alguns instantes você será atendido.</strong>");
			
			$.post("ServletController.do", `command=NewChat&idCliente=${cliente.id}`).done(function(){
				appendAtendente(`Olá, ${cliente.nome}. Qual a sua Duvida?`);
				atendimentoHumano = true;
				checkMensagem(lastIdMensagem);
				
			});
		}	
	});
		
}

var appendSend = (msg) => {
	var templateSend = `<div class="row msg_container base_sent justify-content-end"><div class="col-xs-4 col-md-4"><div class="messages msg_sent">${msg}</div></div><div class="col-md-1 col-xs-1 avatar"><img src="https://icon-icons.com/icons2/877/PNG/512/male-profile-picture_icon-icons.com_68388.png" class=" img-responsive "></div></div>`;
	$(".boxMessage").append(templateSend);
}

var appendReceive = (msg) => {
	var templateReceived = `<div class="row msg_container base_receive"><div class="col-md-1 col-xs-1 avatar"><img src="https://d30y9cdsu7xlg0.cloudfront.net/png/415507-200.png" class=" img-responsive "></div><div class="col-xs-4 col-md-4"><div class="messages msg_receive"><p>${msg}</p></div></div></div>`;
	$(".boxMessage").append(templateReceived);
}

var appendAtendente = (msg) => {
	var templateReceived = `<div class="row msg_container base_receive"><div class="col-md-1 col-xs-1 avatar"><img src="http://russocorretora.com.br/wp-content/uploads/2017/07/icon-helpdesk.png" class=" img-responsive "></div><div class="col-xs-4 col-md-4"><div class="messages msg_receive"><p>${msg}</p></div></div></div>`;
	$(".boxMessage").append(templateReceived);
}

var checkMensagem = (lastIdMensagem) => {
	var cliente = JSON.parse(localStorage.getItem("cliente"));
	$.get("ServletController.do", `command=SearchMensagemFuncionario&idCliente=${cliente.id}&lastIdMensagem=${lastIdMensagem}`).done(function(data){
		var mensagem = JSON.parse(data);
		lastIdMensagem = mensagem.id;
		appendAtendente(mensagem.mensagem);		
		checkMensagem(lastIdMensagem);
	});
}