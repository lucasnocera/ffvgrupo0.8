package com.fatec.demo.model;

import org.joda.time.*;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class Servico {
	/**
	 * Objetivo - verificar se houve atraso na devolucao
	 *
	 * @param umEmprestimo
	 * @return int < 0 se estiver atrasado e > 0 se estiver no prazo retorna nulo se
	 *         o objeto emprestimo é nulo.
	 */
	public Integer verificaAtraso(Emprestimo umEmprestimo) {
		if (umEmprestimo != null) {
			DateTimeFormatter fmt = DateTimeFormat.forPattern("YYYY/MM/dd");
			DateTime dataAtual = fmt.parseDateTime(new DateTime().toString(fmt));
			DateTime dataDevolucao = fmt.parseDateTime(umEmprestimo.getDataDevolucao());
			int dias = Days.daysBetween(dataAtual, dataDevolucao).getDays();
			return dias;
		} else {
			return null;
		}
	}
}
