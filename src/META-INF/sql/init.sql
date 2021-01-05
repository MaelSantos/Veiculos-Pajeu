insert into configuracao (id, horas_limpeza, valorkmcontrole, valorkmlivre, usuario) values (1, '12:00', 100, 100, 'Sistema');

insert into super_usuario (id, login, nome, senha, senha_padrao, configuracao) values (1, 'admin', 'admin', 'adminadmin', 'adminadmin', 1);

CREATE OR REPLACE VIEW ReservaView AS SELECT a.id, a.cliente, a.data_locacao, a.data_reserva, a.hora_reserva, a.categoria FROM Reserva a WHERE a.data_locacao <= CURRENT_DATE AND a.hora_reserva + interval '1 hours' <= CURRENT_TIME AND a.ativo = true;

CREATE OR REPLACE VIEW LocacaoView AS SELECT a.id, a.cliente, a.data_locacao, a.data_devolucao, a.valor_total, a.tipoLocacao FROM Locacao a WHERE a.data_devolucao <= CURRENT_DATE AND a.hora_devolucao + interval '1 hours' <= CURRENT_TIME AND a.ativo = true;

CREATE VIEW AutomovelView AS SELECT a.id, a.fabricante, a.ano_modelo, a.modelo, a.categoria, a.placa FROM automovel a WHERE a.manutencao = true;

CREATE VIEW PassageirosView AS SELECT b.id, b.fabricante, b.ano_modelo, b.modelo, b.categoria, b.placa FROM camionete_passageiros b WHERE b.manutencao = true;

CREATE VIEW CargaView AS SELECT c.id, c.fabricante, c.ano_modelo, c.modelo, c.categoria, c.placa FROM camionete_carga c WHERE c.manutencao = true;

CREATE OR REPLACE FUNCTION array_sort_unique (ANYARRAY) RETURNS ANYARRAY LANGUAGE SQL AS $body$ SELECT ARRAY( SELECT DISTINCT $1[s.i] FROM generate_series(array_lower($1,1), array_upper($1,1)) AS s(i) ORDER BY 1); $body$;
						 
CREATE OR REPLACE FUNCTION array_remove(arr ANYARRAY, values_to_remove ANYARRAY) RETURNS ANYARRAY AS $$ WITH rowz AS ( SELECT unnest(array_sort_unique(arr)) EXCEPT SELECT unnest(values_to_remove)) SELECT array_agg(unnest) FROM rowz; $$ LANGUAGE sql 

CREATE OR REPLACE FUNCTION criar_log() RETURNS TRIGGER AS' BEGIN IF TG_OP = ''INSERT'' THEN INSERT INTO log (data, autor, alteracao, tabela, anterior) Select now(), c.usuario, TG_OP, TG_RELNAME, ''Novo Registro'' from configuracao c where c.id = 1; ELSE INSERT INTO log (data, autor, alteracao, tabela, anterior) Select now(), c.usuario, TG_OP, TG_RELNAME, array_remove(REPLACE(REPLACE(REPLACE(NEW::varchar, ''('', ''{''), '')'',''}''),'',,'','',-,'')::varchar[] , REPLACE(REPLACE(REPLACE(OLD::varchar, ''('', ''{''), '')'',''}''),'',,'','','')::varchar[]) from configuracao c where c.id = 1; END IF; RETURN NEW; END ' LANGUAGE plpgsql;

CREATE TRIGGER log_automovel BEFORE INSERT OR UPDATE OR DELETE ON automovel FOR EACH ROW EXECUTE PROCEDURE criar_log();

CREATE TRIGGER log_carga BEFORE INSERT OR UPDATE OR DELETE ON camionete_carga FOR EACH ROW EXECUTE PROCEDURE criar_log();

CREATE TRIGGER log_passageiros BEFORE INSERT OR UPDATE OR DELETE ON camionete_passageiros FOR EACH ROW EXECUTE PROCEDURE criar_log();

CREATE TRIGGER log_categoria BEFORE INSERT OR UPDATE OR DELETE ON categoria FOR EACH ROW EXECUTE PROCEDURE criar_log();

CREATE TRIGGER log_endereco BEFORE INSERT OR UPDATE OR DELETE ON endereco FOR EACH ROW EXECUTE PROCEDURE criar_log();

CREATE TRIGGER log_filial BEFORE INSERT OR UPDATE OR DELETE ON filial FOR EACH ROW EXECUTE PROCEDURE criar_log();

CREATE TRIGGER log_funcionario BEFORE INSERT OR UPDATE OR DELETE ON funcionario FOR EACH ROW EXECUTE PROCEDURE criar_log();

CREATE TRIGGER log_locacao BEFORE INSERT OR UPDATE OR DELETE ON locacao FOR EACH ROW EXECUTE PROCEDURE criar_log();

CREATE TRIGGER log_fisica BEFORE INSERT OR UPDATE OR DELETE ON pessoa_fisica FOR EACH ROW EXECUTE PROCEDURE criar_log();

CREATE TRIGGER log_juridica BEFORE INSERT OR UPDATE OR DELETE ON pessoa_juridica FOR EACH ROW EXECUTE PROCEDURE criar_log();

CREATE TRIGGER log_reserva BEFORE INSERT OR UPDATE OR DELETE ON reserva FOR EACH ROW EXECUTE PROCEDURE criar_log();

CREATE TRIGGER log_super_usuario BEFORE INSERT OR UPDATE OR DELETE ON super_usuario FOR EACH ROW EXECUTE PROCEDURE criar_log();

CREATE TRIGGER log_super_usuario BEFORE INSERT OR UPDATE OR DELETE ON financeiro FOR EACH ROW EXECUTE PROCEDURE criar_log();

CREATE OR REPLACE FUNCTION gerar_financeiro() RETURNS TRIGGER AS' BEGIN IF TG_OP = ''INSERT'' THEN INSERT INTO financeiro (id, data_aberta, valor_pago, multa, valor_total, data_vencimento, estado, locacao) VALUES (NEW.id, NEW.data_locacao, 0, 0, NEW.valor_total, NEW.data_devolucao, ''PENDENTE'', NEW.id); ELSIF TG_OP = ''UPDATE'' THEN UPDATE financeiro SET data_aberta = NEW.data_locacao, valor_total = NEW.valor_total, data_vencimento = NEW.data_devolucao WHERE id = NEW.id; END IF; RETURN NEW; END' LANGUAGE plpgsql;

CREATE TRIGGER gerar_financeiro AFTER INSERT OR UPDATE ON locacao FOR EACH ROW EXECUTE PROCEDURE gerar_financeiro();

CREATE OR REPLACE FUNCTION verificar_atraso_financeiro() RETURNS void AS' BEGIN UPDATE financeiro f SET estado = ''ATRASADO'' FROM locacao l WHERE f.estado = ''PENDENTE'' AND l.ativo = true AND f.locacao = l.id AND (f.data_vencimento < CURRENT_DATE OR (f.data_vencimento < CURRENT_DATE AND l.hora_devolucao + interval ''1 hours'' <= CURRENT_TIME)); END' LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION verificar_atraso_reserva() RETURNS void AS' BEGIN UPDATE reserva r SET ativo = false WHERE r.ativo = true AND (r.data_locacao < CURRENT_DATE OR (r.data_locacao = CURRENT_DATE AND r.hora_reserva + interval ''1 hours'' <= CURRENT_TIME)); END' LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION verificar_manutencao() RETURNS void AS' BEGIN UPDATE camionete_carga c SET manutencao = false, horas_manutencao = ''00:00'', quilometragem = 0 FROM configuracao g WHERE c.horas_manutencao >= g.horas_limpeza AND c.manutencao = true AND g.id = 1; UPDATE camionete_passageiros c SET manutencao = false, horas_manutencao = ''00:00'', quilometragem = 0 FROM configuracao g WHERE c.horas_manutencao >= g.horas_limpeza AND c.manutencao = true AND g.id = 1; UPDATE automovel c SET manutencao = false, horas_manutencao = ''00:00'', quilometragem = 0 FROM configuracao g WHERE c.horas_manutencao >= g.horas_limpeza AND c.manutencao = true AND g.id = 1; END' LANGUAGE plpgsql;
