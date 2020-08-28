package com.linkdoan.backend.controller;

import com.linkdoan.backend.base.dto.CustomUserDetails;
import com.linkdoan.backend.config.JwtTokenUtil;
import com.linkdoan.backend.model.JwtRequest;
import com.linkdoan.backend.model.JwtResponse;
import com.linkdoan.backend.model.User;
import com.linkdoan.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin
public class JwtAuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Qualifier("userRepository")
    @Autowired
    private UserRepository userRepository;
    //@Autowired
    //private JwtUserDetailsService userDetailsService;
    private UserDetailsService userDetailsService;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@ModelAttribute @Valid JwtRequest authenticationRequest) throws Exception { //@RequestBody JwtRequest authenticationRequest
        //authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getUsername(),
                        authenticationRequest.getPassword()
                )
        );
        User userDetails = userRepository.findByUsername(authenticationRequest.getUsername());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        //CustomUserDetails customUserDetails=(CustomUserDetails) authentication.getPrincipal();

        final String token = jwtTokenUtil.generateToken((CustomUserDetails) authentication.getPrincipal());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtTokenUtil.AUTHORIZATION_HEADER, "Bearer " + token);
        return new ResponseEntity<>(new JwtResponse(token, userDetails), httpHeaders, HttpStatus.OK);
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home() {
        return "home";
    }

}