package com.ecommerce.admin.produto;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.comum.entity.Produto;
import com.ecommerce.comum.entity.Usuario;

@Service
@Transactional
public class ProdutoService {

	@Autowired
	private RepositorioProduto repo;

	public List<Produto> listarTodos() {
		return (List<Produto>) repo.findAll();
	}

//	public void salvar(Produto prod) {
//		boolean estaAtualizando = (prod.getId() != null);
//
//		if (estaAtualizando) {
//			Produto proutoExistente = repo.findById(prod.getId()).get();
//		}
//		repo.save(prod);
//	}

	public Produto salvar(Produto prod) {
		if (prod.getId() == null) {
			prod.setDataCriacao(new Date());
		}
		prod.setDataAtualizacao(new Date());
		return repo.save(prod);
	}

//	public String verificarProdUnico(Integer id, String nome) {
//		boolean criarNovo = (id == null || id == 0);
//		Produto prod = repo.encontrarPorNome(nome);
//
//		if (criarNovo) {
//			if (prod != null) return "Duplicado";
//		} else {
//			if (prod != null && prod.getId() != id) {
//				return "Duplicado";
//			}
//		}
//		return "OK";
//	}

	public void atualizarProdutoStatusAtivado(Integer id, boolean enabled) {
		repo.atualizarStatusAtivado(id, enabled);
	}
}
