var lastIdMensagem = 0;
$("#signOut").click(function(){
	localStorage.clear();
	window.location.assign("login.html");
});

$("#btnNovoAtendimento").click(function(){
	var funcionario = JSON.parse(localStorage.getItem("funcionario"));
	$.get("ServletController.do", `command=NewAtendimento&idFuncionario=${funcionario.id}`).done(function(data){
		localStorage.setItem("cliente", data);
		var cliente = JSON.parse(localStorage.getItem("cliente"));
		appendAtendente(`Olá, ${cliente.nome}. Qual a sua Duvida?`);
		checkMensagem(lastIdMensagem);
	})
});

$(".enviarMsg").click(function(){
	var funcionario = JSON.parse(localStorage.getItem("funcionario"));
	var cliente = JSON.parse(localStorage.getItem("cliente"));
	var mensagem = $("#inputMessage").val();
	appendAtendente(mensagem);
	$.post("ServletController.do", `command=NewMensagemFuncionario&idCliente=${cliente.id}&mensagemEnviada=${mensagem}`);
});

var checkMensagem = (lastIdMensagem) => {
	var cliente = JSON.parse(localStorage.getItem("cliente"));
	$.get("ServletController.do", `command=SearchMensagemCliente&idCliente=${cliente.id}&lastIdMensagem=${lastIdMensagem}`).done(function(mensagem){
		var mensagem = JSON.parse(mensagem);
		lastIdMensagem = mensagem.id;
		appendSend(mensagem.mensagem);		
		checkMensagem(lastIdMensagem);
	});
}

var appendSend = (msg) => {
	var templateSend = `<div class="row msg_container base_sent justify-content-end"><div class="col-xs-4 col-md-4"><div class="messages msg_sent">${msg}</div></div><div class="col-md-1 col-xs-1 avatar"><img src="https://icon-icons.com/icons2/877/PNG/512/male-profile-picture_icon-icons.com_68388.png" class=" img-responsive "></div></div>`;
	$(".boxMessage").append(templateSend);
}

var appendAtendente = (msg) => {
	var templateReceived = `<div class="row msg_container base_receive"><div class="col-md-1 col-xs-1 avatar"><img src="http://russocorretora.com.br/wp-content/uploads/2017/07/icon-helpdesk.png" class=" img-responsive "></div><div class="col-xs-4 col-md-4"><div class="messages msg_receive"><p>${msg}</p></div></div></div>`;
	$(".boxMessage").append(templateReceived);
}
