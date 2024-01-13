package login.crudusuarios.Controller;

import login.crudusuarios.Dto.AdminDTO;
import login.crudusuarios.Dto.UsuarioDTO;
import login.crudusuarios.Entity.Usuario;
import login.crudusuarios.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;


    @ModelAttribute("usuario")
    public UsuarioDTO retornarNuevoUsuarioDto() {

        return new UsuarioDTO();
    }

    @ModelAttribute("admin")
    public AdminDTO retornarNuevoAdminDto(){
        return  new AdminDTO();
    }


    @GetMapping
    public String mostrarFormularioRegistro() {
        return "registro";
    }

    @GetMapping("/nvoUsuario")
    public String mostrarAgregarUsuario(){
        return "Usuarios/agregarUsuario";

    }

    @GetMapping("/nvoAdmin")
    public String mostrarAgregarAdmin(){
        return "Admin/agregarAdmin";
    }



    @PostMapping("/guardarUsuario")
    public String registrarUsuario(@ModelAttribute("usuario") UsuarioDTO usuarioDTO){
        try{
            usuarioService.guardarUsuario(usuarioDTO);
        }catch (Exception e){
            return "Usuarios/agregarUsuario";
        }

        return "redirect:/usuarios/nvoUsuario?exito";
    }


    @PostMapping("/guardarAdmin")
    public String registrarAdmin(@ModelAttribute("admin") AdminDTO adminDTO){
        try {
            usuarioService.guardarAdmin(adminDTO);

        }catch (Exception e){
            return "Admin/agregarAdmin";
        }

        return "redirect:/usuarios/nvoAdmin?exito";
    }


    @GetMapping("/{email}")
    public Usuario obtenerUsuario(@PathVariable("email") String email) {
        return usuarioService.obtenerUsuario(email);
    }


}
