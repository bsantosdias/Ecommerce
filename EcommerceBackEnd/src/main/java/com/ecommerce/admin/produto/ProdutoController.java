package com.ecommerce.admin.produto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ecommerce.comum.entity.Produto;

@Controller
public class ProdutoController {

	@Autowired
	private ProdutoService servico;

	@GetMapping("/produtos")
	public String listarTodosProdutos(Model model) {
		List<Produto> listar = servico.listarTodos();

		model.addAttribute("listaProdutos", listar);
		return "produtos/produtos";
	}

	@GetMapping("/produtos/cadastrar")
	public String cadastrarProdutos(Model model) {
		Produto prod = new Produto();
		prod.setAtivado(true);
		prod.setEmEstoque(true);

		model.addAttribute("produto", prod);
		model.addAttribute("titulo", "Cadastrar Novo Produto");
		return "produtos/formulario_produto";
	}

	@PostMapping("/produtos/salvar")
	public String salvarProdutos(Produto prod, RedirectAttributes redirectAttributes) {
		System.out.println("Produto " + prod.getNome());
		servico.salvar(prod);
		redirectAttributes.addFlashAttribute("message", "O produto foi salvo com sucesso");
		return "redirect:/produtos";
	}

	@GetMapping("/produtos/{id}/ativado/{status}")
	public String atualizarStatusAtivadoProduto(@PathVariable("id") Integer id, @PathVariable("status") boolean enabled,
			RedirectAttributes redirectAttributes) {
		servico.atualizarProdutoStatusAtivado(id, enabled);
		String status = enabled ? "ativado" : "desativado";
		String message = "O produto " + id + " foi " + status;
		redirectAttributes.addFlashAttribute("message", message);
		return "redirect:/produtos";

	}
}
