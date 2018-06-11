
USE `chatBot` ;

insert into PalavraChave(PalavraChave) values ('azul'), ('verde');

Insert into Resposta(resposta) 
	values 
		('A atmosfeea atua como um prisma...etc'),
        ('É a cor preferida do criador...'),
        ('É o reflexo do mar...');

Insert into Pontuacao(idResposta, idPalavraChave, pontuacao) 
	values 
		(1, 1, 0),
        (2, 1, 2),	
        (3, 1, 3),
		(1, 2, 0),
        (2, 2, 0),
        (3, 2, 0);
        
        
select * from atendimento;


select * from mensagem;

INSERT INTO funcionario(senha, tipo, status) VALUES ('root', 2, 1);

delete from cliente where id = 6;

INSERT INTO atendimento(interacoes, status, idCliente) VALUES (0, 1, 5);

UPDATE atendimento SET dtFim='2018-06-11', interacoes=1, status=2 WHERE id=1;

UPDATE atendimento SET status = 3 where id = 5;

select * from funcionario;

INSERT INTO atendimento(interacoes, status, idCliente) VALUES (0, 1, 5);

select
	count(id)																		as qtdAtendimentos,
    sum(interacoes)																	as interacoes,
    (select count(interacoes) from atendimento where interacoes = 1)					as primeira,
    (select count(interacoes) from atendimento where interacoes = 2)					as segunda,
    (select count(interacoes) from atendimento where interacoes = 3 and`status` < 3)	as terceira,
    (select count(`status`) from atendimento where `status` > 2)					as encaminhados	
	from atendimento
    Where dtInicio >= curdate() - INTERVAL DAYOFWEEK(curdate())+6 DAY
	AND dtInicio < curdate() - INTERVAL DAYOFWEEK(curdate())-1 DAY;
    
select count(id) from atendimento;

update atendimento set dtInicio = '2018-05-01' where id = 1;