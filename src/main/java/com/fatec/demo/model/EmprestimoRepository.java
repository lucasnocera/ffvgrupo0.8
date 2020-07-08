package com.fatec.demo.model;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.*;

@Repository
public interface EmprestimoRepository extends CrudRepository<Emprestimo, Long> {
	@Query("SELECT l FROM Emprestimo l WHERE l.isbn = :isbn")
	public Emprestimo findByISBN(@Param("isbn") String isbn);
}