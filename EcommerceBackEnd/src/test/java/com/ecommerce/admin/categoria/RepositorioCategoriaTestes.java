package com.ecommerce.admin.categoria;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.ecommerce.comum.entity.Categoria;

@DataJpaTest(showSql = false)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class RepositorioCategoriaTestes {

	@Autowired
	private RepositorioCategoria repo;
	
	@Test
	public void criarCategoria() {
		Categoria cate = new Categoria("Eletronicos");
		Categoria salvar = repo.save(cate);
		
		assertThat(salvar.getId()).isGreaterThan(0);
	}
	
	@Test
	public void criarSubCategoria() {
		Categoria categoria = new Categoria(2);
		Categoria cat1 = new Categoria("Cameras", categoria);
		Categoria cat2 = new Categoria("Fog�o", categoria);
		
//		Categoria salvar = repo.save(celular);
		repo.saveAll(List.of(cat1, cat2));
//		assertThat(salvar.getId()).isGreaterThan(0);
	}
	
	@Test
	public void getCategoria() {
		Categoria categoria = repo.findById(1).get();
		System.out.println(categoria.getNome());
		
		Set<Categoria> subCategoria = categoria.getTipo();
		
		for(Categoria subCate : subCategoria) {
			System.out.println(subCate.getNome());
		}
		
		assertThat(subCategoria.size()).isGreaterThan(0);
	}
	
	@Test
	public void categoriaPorHierarquia() {
		Iterable<Categoria> categorias = repo.findAll();
		
		for(Categoria categoria : categorias) {
			if(categoria.getTipo() == null) {
				System.out.println(categoria.getNome());
				
				Set<Categoria> sub = categoria.getTipo();
				
				for(Categoria subCategoria : sub) {
					System.out.println("--"+subCategoria.getNome());
				}
			}
		}
	}
	
	@Test
	public void desativarCategoriaTeste() {
		Integer id =1;
		repo.atualizarStatusAtivado(id, false);
	}
	
	@Test
	public void ativarCategoriaTeste() {
		Integer id =1;
		repo.atualizarStatusAtivado(id, true);
	}
}
