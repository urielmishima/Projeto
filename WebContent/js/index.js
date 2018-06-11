$(".btnLog").click(function(){
	var nome = $("#inputNome").val();
	var email = $("#inputEmail").val();
	$.post("ServletController.do", `command=NewCliente&nome=${nome}&email=${email}`).done(function(cliente) {
		localStorage.setItem("cliente", cliente);
		window.location.assign("chat.html");
	}); 
});

