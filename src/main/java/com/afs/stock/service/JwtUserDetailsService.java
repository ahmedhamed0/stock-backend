package com.afs.stock.service;

import com.afs.stock.repository.StockUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

	final private StockUserRepository stockUserRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return stockUserRepository.findByUsername(username).orElseThrow(()->{throw new UsernameNotFoundException("User not found with username: " + username);});
	}

}