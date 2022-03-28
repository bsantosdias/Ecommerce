package com.ecommerce.admin.produto;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ecommerce.comum.entity.Produto;

public interface RepositorioProduto extends PagingAndSortingRepository<Produto, Integer> {
//	public Produto encontrarPorNome(String nome);
//	
	@Query("UPDATE Produto p SET p.ativado = ?2 WHERE p.id = ?1")
	@Modifying
	public void atualizarStatusAtivado(Integer id, boolean enabled);
}
