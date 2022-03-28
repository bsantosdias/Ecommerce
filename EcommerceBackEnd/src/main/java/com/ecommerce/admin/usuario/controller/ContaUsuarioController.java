package com.ecommerce.admin.usuario.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ecommerce.admin.security.EcommerceDetalhesUsuario;
import com.ecommerce.admin.usuario.UsuarioService;
import com.ecommerce.comum.entity.Usuario;

@Controller
public class ContaUsuarioController {

	@Autowired
	private UsuarioService servico;
	
	@GetMapping("/conta")
	public String mostrarDetalhes(@AuthenticationPrincipal EcommerceDetalhesUsuario usuarioLogado,
			Model model) {
		String email = usuarioLogado.getUsername();
		Usuario usuario = servico.getUsuarioPorEmail(email);
		
		model.addAttribute("usuario", usuario);
		return "conta";
	}
	
	@PostMapping("/conta/atualizar")
	public String salvarAlteracao(Usuario usuario, RedirectAttributes redirectAttributes,
			@AuthenticationPrincipal EcommerceDetalhesUsuario usuarioLogado) {
		System.out.println(usuario);
		servico.salvar(usuario);
		
		usuarioLogado.setNome(usuario.getNome());
		
		redirectAttributes.addFlashAttribute("message", "Sua conta foi atualizada com sucesso!");

		return "redirect:/conta";
	}
	
}