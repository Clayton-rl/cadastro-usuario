package com.claycode.cadastrousuario.controller;

import com.claycode.cadastrousuario.business.UsuarioService;
import com.claycode.cadastrousuario.business.dtos.UsuarioDTO;
import com.claycode.cadastrousuario.business.dtos.UsuarioLoginDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioDTO> salvarUsuario(@RequestBody UsuarioDTO dto) {
        return ResponseEntity.ok(usuarioService.salvarUsuario(dto));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UsuarioLoginDTO dto) {
        return ResponseEntity.ok(usuarioService.login(dto));
    }

    @GetMapping
    public ResponseEntity<UsuarioDTO> buscarUsuarioPorEmail(@RequestParam("email") String email) {
        return ResponseEntity.ok(usuarioService.buscarUsuarioPorEmail(email));
    }
}
