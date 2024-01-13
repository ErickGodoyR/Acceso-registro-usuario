package login.crudusuarios.Service;

import login.crudusuarios.Dto.AdminDTO;
import login.crudusuarios.Dto.UsuarioDTO;
import login.crudusuarios.Entity.Rol;
import login.crudusuarios.Entity.Usuario;
import login.crudusuarios.Repository.RolRepository;
import login.crudusuarios.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements UsuarioService{


    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;



    @Override
    public Usuario guardarUsuario(UsuarioDTO usuarioDTO) {
        Usuario nuevoUsuario = new Usuario(usuarioDTO.getId(), usuarioDTO.getNombre(), usuarioDTO.getApellido(),
                usuarioDTO.getEmail(), passwordEncoder.encode(usuarioDTO.getPassword()), usuarioDTO.isEnabled(),
                Arrays.asList(new Rol("USUARIO")));

        return usuarioRepository.save(nuevoUsuario);
    }

    @Override
    public Usuario guardarAdmin(AdminDTO adminDTO) {
        Usuario nuevoAdmin = new Usuario(adminDTO.getId(), adminDTO.getNombre(), adminDTO.getApellido(),
                adminDTO.getEmail(), passwordEncoder.encode(adminDTO.getPassword()), adminDTO.isEnabled(),
                Arrays.asList(new Rol("ADMIN")));

        return usuarioRepository.save(nuevoAdmin);
    }

    @Override
    public Usuario obtenerUsuario(String email) {
        return usuarioRepository.findByEmail(email);
    }

    @Override
    public void eliminarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }


    //Carga el usuario y su contraseña
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByEmail(username);
        if(usuario == null) {
            throw new UsernameNotFoundException("Usuario o password inválidos");
        }
        return new User(usuario.getEmail(),usuario.getPassword(), mapearAutoridadesRoles(usuario.getRol()));
    }

    //Mapear usuario con su rol y autoridad
    private Collection<? extends GrantedAuthority> mapearAutoridadesRoles(Collection<Rol> rol) {
        return rol.stream().map(role -> new SimpleGrantedAuthority(role.getNombre_rol())).collect(Collectors.toList());
    }

}
