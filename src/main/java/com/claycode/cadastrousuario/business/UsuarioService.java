package com.claycode.cadastrousuario.business;

import com.claycode.cadastrousuario.business.converter.UsuarioConverter;
import com.claycode.cadastrousuario.business.dtos.UsuarioDTO;
import com.claycode.cadastrousuario.infrastructure.entity.Usuario;
import com.claycode.cadastrousuario.infrastructure.exceptions.ConflictException;
import com.claycode.cadastrousuario.infrastructure.repository.UsuarioRepository;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioConverter usuarioConverter;
    private final PasswordEncoder passwordEncoder;

    public UsuarioDTO salvarUsuario(UsuarioDTO dto) {
        tratarEmailExiste(dto.getEmail());
        dto.setSenha(passwordEncoder.encode(dto.getSenha()));
        Usuario entity = usuarioConverter.paraUsuario(dto);
        return usuarioConverter.paraUsuarioDTO(usuarioRepository.save(entity));
    }

    public void tratarEmailExiste(String email) {
        try {
            boolean existe = verificarEmailExiste(email);
            if (existe) {
                throw new ConflictException("Email já cadastrado: " + email);
            }
        } catch (ConflictException e) {
            throw new ConflictException("Email já cadastrado: ", e.getCause());
        }
    }

    public Boolean verificarEmailExiste(String email) {
        return usuarioRepository.existsByEmail(email);
    }

}
