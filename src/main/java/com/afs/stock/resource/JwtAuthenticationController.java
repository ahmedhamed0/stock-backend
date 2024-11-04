package com.afs.stock.resource;


import com.afs.stock.model.jwt.JwtRequest;
import com.afs.stock.model.jwt.JwtResponse;
import com.afs.stock.utils.JwtTokenUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class JwtAuthenticationController {


	private final JwtTokenUtil jwtTokenUtil;
	private final UserDetailsService jwtUserDetailsService;

	@PostMapping("login")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest, HttpServletResponse response)
			throws Exception {

		final UserDetails userDetails = jwtUserDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());

		if(!userDetails.getPassword().equals(authenticationRequest.getPassword())) {
				throw new BadCredentialsException("Bad Credentials");
		}

		final String token = jwtTokenUtil.generateToken(userDetails);
		Cookie tokenCookie=new Cookie("JWT_TOKEN", token);
		tokenCookie.setHttpOnly(true);
		response.addCookie(tokenCookie);

		return ResponseEntity.ok(new JwtResponse(token));
	}


}
