package com.ecommerce.admin.produto;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.ecommerce.comum.entity.Produto;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class RepositorioProdutoTeste {

	@Autowired
	private RepositorioProduto repo;

	@Autowired
	private TestEntityManager entityManeger;

	@Test
	public void testeCriarProduto() {
		Produto p1 = new Produto();
		p1.setNome("Livro 3");
		p1.setDescricaoCurta("Uma p�ra assassina");
		p1.setDescricaoLonga(
				"O mundo das frutas se abala com o caso de uma ma�� morta por outra fruta. Elas se envolver�o num mist�rios jamais visto e descobrir�o quem � o assasino");

		p1.setDataCriacao(new Date());
		p1.setDataAtualizacao(new Date());

		p1.setPreco(59.29f);
		p1.setQuantidade(50);
		p1.setEmEstoque(true);
		p1.setAtivado(true);

		Produto salvarProduto = repo.save(p1);

		assertThat(salvarProduto).isNotNull();
		assertThat(salvarProduto.getId()).isGreaterThan(0);
	}

	@Test
	public void listarTodos() {
		Iterable<Produto> prod = repo.findAll();

		prod.forEach(System.out::println);
	}

	@Test
	public void encontrarProduto() {
		Integer id = 2;
		Produto prod = repo.findById(id).get();

		System.out.println(prod);
		assertThat(prod).isNotNull();
	}

	@Test
	public void atualizarProduto() {
		Integer id = 1;
		Produto prod = repo.findById(id).get();

		prod.setPreco(19.90f);
		repo.save(prod);

		Produto salvarProduto = entityManeger.find(Produto.class, id);

		assertThat(salvarProduto.getPreco()).isEqualTo(19.90f);
		assertThat(salvarProduto.getId()).isGreaterThan(0);
	}
	
	@Test
	public void deletarProduto() {
		Integer id = 4;
		repo.deleteById(id);
		
		Optional<Produto> prod = repo.findById(id);
		assertThat(!prod.isPresent());
	}
	
	@Test
	public void salvarProdutoComImagem() {
		Integer produtoId = 1;
		Produto produto = repo.findById(produtoId).get();
		
		produto.setImagemPrincipal("imagem-principal"+produtoId+".jpg");
		produto.adicionarImagemExtra("extra1.jpg");
		produto.adicionarImagemExtra("extra2.jpg");
		produto.adicionarImagemExtra("extra3.jpg");
		produto.adicionarImagemExtra("extra4.jpg");
		
		Produto salvarProduto = repo.save(produto);
		assertThat(salvarProduto.getImg().size()).isEqualTo(4);
	}
	@Test
	public void desativarProdutoTeste() {
		Integer id =1;
		repo.atualizarStatusAtivado(id, false);
	}
	
	@Test
	public void ativarProdutoTeste() {
		Integer id =1;
		repo.atualizarStatusAtivado(id, true);
	}
}
