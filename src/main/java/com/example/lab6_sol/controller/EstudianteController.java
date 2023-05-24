package com.example.lab6_sol.controller;

import com.example.lab6_sol.entity.Usuario;
import com.example.lab6_sol.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/estudiante")
public class EstudianteController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @GetMapping("/lista")
    public String listaUsuarios(Model model){
        List<Usuario> estudiantes = usuarioRepository.findByRolid(5);
        model.addAttribute("estudiantes", estudiantes);
        return "lista_usuarios";
    }


    @GetMapping("/nuevoEstudiante")
    public String nuevoEstudiante(Model model, @ModelAttribute("usuario") Usuario usuario) {
        return "nuevoEstudiante";
    }


    @PostMapping("/saveEstudiante")
    public String saveEstudiante(RedirectAttributes attr,
                                 Model model,
                                 @ModelAttribute("usuario") @Valid Usuario usuario,
                                 BindingResult bindingResult) {

        if(bindingResult.hasErrors()){
            return "nuevoEstudiante";
        }else{
            if (usuario.getId() == 0) {
                Optional<Usuario> usuarioOptional = usuarioRepository.findById(usuario.getId());
                boolean existe = false;
                if (usuarioOptional.isPresent()) {
                    existe = true;
                }
                if (existe) {
                    System.out.println("El usuario ya existe");
                    attr.addFlashAttribute("msgDanger", "El Estudiante ya existe");
                    return "nuevoEstudiante";
                } else {
                    attr.addFlashAttribute("msgGood", "Estudiante creado exitosamente");
                    usuario.setActivo(true);
                    usuario.setRolid(5);
                    String passwordNOencriptada = usuario.getPassword();
                    usuario.setPassword(new BCryptPasswordEncoder().encode(passwordNOencriptada));
                    usuarioRepository.save(usuario);
                    return "redirect:/estudiante/lista";
                }
            } else {
                attr.addFlashAttribute("msgGood", "Estudiante actualizado exitosamente");
                usuarioRepository.save(usuario);
                return "redirect:/estudiante/lista";
            }
        }
    }

    @GetMapping("/editEstudiante")
    public String editarEstudiante(Model model, @RequestParam("id") int id, @ModelAttribute("usuario") Usuario usuario) {

        Optional<Usuario> optUsuario = usuarioRepository.findById(id);

        if (optUsuario.isPresent()) {
            usuario = optUsuario.get();
            model.addAttribute("usuario", usuario);
            model.addAttribute("flagedit", "flagedit");
            return "nuevoEstudiante";
        } else {
            return "redirect:/estudiante/lista";
        }
    }


}
