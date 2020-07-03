package com.linkdoan.backend.controller;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import com.linkdoan.backend.base.dto.CustomUserDetails;
import com.linkdoan.backend.dto.UserDTO;
import com.linkdoan.backend.util.MessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import com.linkdoan.backend.service.JwtUserDetailsService;
import com.linkdoan.backend.config.JwtTokenUtil;
import com.linkdoan.backend.model.JwtRequest;
import com.linkdoan.backend.model.JwtResponse;

import javax.validation.Valid;

@RestController
@CrossOrigin
public class JwtAuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    //@Autowired
    //private JwtUserDetailsService userDetailsService;
    private UserDetailsService userDetailsService;
    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken( @ModelAttribute @Valid JwtRequest authenticationRequest  ) throws Exception { //@RequestBody JwtRequest authenticationRequest
        //authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getUsername(),
                        authenticationRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        //CustomUserDetails customUserDetails = (CustomUserDetails) userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

        final String token = jwtTokenUtil.generateToken((CustomUserDetails) authentication.getPrincipal());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtTokenUtil.AUTHORIZATION_HEADER, "Bearer " + token);
        return new ResponseEntity<>(new JwtResponse(token),httpHeaders, HttpStatus.OK);
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



    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home(){
        return "home";
    }

}