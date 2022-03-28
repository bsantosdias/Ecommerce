package com.ecommerce.admin.categoria;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ecommerce.comum.entity.Categoria;

@Controller
public class CategoriaController {
	
	 @Autowired
	 private CategoriaService servico;
	 
	 @GetMapping("/categorias")
	 public String listarTodos(Model model) {
		 List<Categoria> lista = servico.listarTodos();
		 model.addAttribute("listaCategoria", lista);
		 
		 return "categorias";
	 }
	 
	 @GetMapping("/categorias/cadastrar")
	 public String cadastrarCategoria(Model model) {
		 model.addAttribute("categoria", new Categoria());
		 model.addAttribute("titulo", "Criar nova categoria");
		 return "formulario_categoria";
	 }
	 
 @GetMapping("/categorias/{id}/ativado/{status}")
		public String atualizarStatusAtivadoUsuario(@PathVariable("id") Integer id, @PathVariable("status") boolean enabled,
			RedirectAttributes redirectAttributes) {

			servico.atualizarCategoriaStatusAtivado(id, enabled);
			String status = enabled ? "ativado" : "desativado";
			String message = "A categoria " + id + " foi " + status;
			redirectAttributes.addFlashAttribute("message", message);

			return "redirect:/categorias";

		}

}
