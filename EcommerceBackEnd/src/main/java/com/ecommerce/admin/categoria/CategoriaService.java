package com.ecommerce.admin.categoria;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.comum.entity.Categoria;

@Service
@Transactional
public class CategoriaService {

	@Autowired
	private RepositorioCategoria repo;

	public List<Categoria> listarTodos() {
		return (List<Categoria>) repo.findAll();
	}

	public void atualizarCategoriaStatusAtivado(Integer id, boolean enabled) {
		repo.atualizarStatusAtivado(id, enabled);
	}
}
