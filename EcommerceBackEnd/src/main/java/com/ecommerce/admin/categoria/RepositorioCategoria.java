package com.ecommerce.admin.categoria;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ecommerce.comum.entity.Categoria;

public interface RepositorioCategoria extends PagingAndSortingRepository<Categoria, Integer> {
	
	@Query("UPDATE Categoria c SET c.ativado = ?2 WHERE c.id = ?1")
	@Modifying
	public void atualizarStatusAtivado(Integer id, boolean enabled);
}
