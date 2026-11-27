package com.claycode.cadastrousuario.business.converter;

import com.claycode.cadastrousuario.business.dtos.EnderecoDTO;
import com.claycode.cadastrousuario.business.dtos.TelefoneDTO;
import com.claycode.cadastrousuario.business.dtos.UsuarioDTO;
import com.claycode.cadastrousuario.infrastructure.entity.Endereco;
import com.claycode.cadastrousuario.infrastructure.entity.Telefone;
import com.claycode.cadastrousuario.infrastructure.entity.Usuario;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UsuarioConverter {

    public Usuario paraUsuario(UsuarioDTO dto) {
        return Usuario.builder()
                .nome(dto.getNome())
                .email(dto.getEmail())
                .senha(dto.getSenha())
                .enderecos(paraListaEndereco(dto.getEnderecos()))
                .telefones(paraListaTelefone(dto.getTelefones()))
                .build();
    }

    public List<Endereco> paraListaEndereco(List<EnderecoDTO> dtos) {
        return dtos.stream().map(this::paraEndereco).toList();
    }

    public Endereco paraEndereco(EnderecoDTO dto) {
        return Endereco.builder()
                .rua(dto.getRua())
                .numero(dto.getNumero())
                .complemento(dto.getComplemento())
                .cidade(dto.getCidade())
                .estado(dto.getEstado())
                .cep(dto.getCep())
                .build();
    }

    public List<Telefone> paraListaTelefone(List<TelefoneDTO> dtos) {
        return dtos.stream().map(this::paraTelefone).toList();
    }

    public Telefone paraTelefone(TelefoneDTO dto) {
        return Telefone.builder()
                .ddd(dto.getDdd())
                .numero(dto.getNumero())
                .build();
    }

    public UsuarioDTO paraUsuarioDTO(Usuario entity) {
        return UsuarioDTO.builder()
                .nome(entity.getNome())
                .email(entity.getEmail())
                .senha(entity.getSenha())
                .enderecos(paraListaEnderecoDTO(entity.getEnderecos()))
                .telefones(paraListaTelefoneDTO(entity.getTelefones()))
                .build();
    }

    public List<EnderecoDTO> paraListaEnderecoDTO(List<Endereco> entity) {
        return entity.stream().map(this::paraEnderecoDTO).toList();
    }

    public EnderecoDTO paraEnderecoDTO(Endereco entity) {
        return EnderecoDTO.builder()
                .rua(entity.getRua())
                .numero(entity.getNumero())
                .complemento(entity.getComplemento())
                .cidade(entity.getCidade())
                .estado(entity.getEstado())
                .cep(entity.getCep())
                .build();
    }

    public List<TelefoneDTO> paraListaTelefoneDTO(List<Telefone> entity) {
        return entity.stream().map(this::paraTelefoneDTO).toList();
    }

    public TelefoneDTO paraTelefoneDTO(Telefone entity) {
        return TelefoneDTO.builder()
                .ddd(entity.getDdd())
                .numero(entity.getNumero())
                .build();
    }
}
