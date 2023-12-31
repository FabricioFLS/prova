package org.senai.ecommerce.config;

import org.senai.ecommerce.entity.Usuario;
import org.senai.ecommerce.service.UsuarioService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class UserDetailsServiceCustom implements UserDetailsService {
    private final UsuarioService usuarioService;

    public UserDetailsServiceCustom(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuario = usuarioService.getUsuarioAutenticacao(username);
        if(!usuario.isPresent())
            new UsernameNotFoundException("Usuário não encontrado!");

        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(usuario.get().getPermissao());
        Set<GrantedAuthority> authorities = new HashSet();
        authorities.add(authority);

        User user = new User(usuario.get().getEmail(), usuario.get().getSenha(), authorities);

        return user;
    }
}
