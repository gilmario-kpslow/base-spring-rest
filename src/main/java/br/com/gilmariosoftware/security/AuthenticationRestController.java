package br.com.gilmariosoftware.security;

import br.com.gilmariosoftware.security.jwt.JwtAuthenticationRequest;
import br.com.gilmariosoftware.security.jwt.JwtTokenUtil;
import br.com.gilmariosoftware.usuario.Usuario;
import br.com.gilmariosoftware.usuario.UsuarioService;
import javax.security.sasl.AuthenticationException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class AuthenticationRestController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UsuarioService userService;

    @PostMapping(value = "/api/auth")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest)
            throws AuthenticationException {
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        System.out.println(authenticationRequest.getUsername());
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        final Usuario user = userService.findByUsername(authenticationRequest.getUsername());
        user.setPassword(null);
        return ResponseEntity.ok(new CurrentUser(token, user.getUsername()));

    }

    @PostMapping(value = "/api/refresh")
    public ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest request) {
        String token = request.getHeader(JwtTokenUtil.HTTP_HEADER_AUTHORIZATION);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        final Usuario user = userService.findByUsername(username);
        if (jwtTokenUtil.canTokenBeRefreshed(token)) {
            String refreshToken = jwtTokenUtil.refreshToken(token);
            return ResponseEntity.ok(new CurrentUser(refreshToken, user.getUsername()));
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
