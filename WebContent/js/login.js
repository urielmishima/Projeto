$(".btnLog").click(function(){
	var id = $("#inputId").val();
	var senha = $("#inputSenha").val();
	$.post("ServletController.do", `command=LoginFuncionario&id=${id}&senha=${senha}`).done(function(funcionario) {
		if(funcionario != null){
			localStorage.setItem("funcionario", funcionario);
			window.location.assign("funcionario.html");
		}
		else {
			alert("Usuario ou senha invalidos");
		}
	}); 
});

