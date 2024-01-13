package login.crudusuarios.Service;

import login.crudusuarios.Dto.AdminDTO;
import login.crudusuarios.Dto.UsuarioDTO;
import login.crudusuarios.Entity.Usuario;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;


@Service
public interface UsuarioService extends UserDetailsService {

    public Usuario guardarUsuario(UsuarioDTO usuarioDTO);

    public Usuario guardarAdmin(AdminDTO adminDTO);

    public Usuario obtenerUsuario(String email);

    public void eliminarUsuario(Long id);


}
