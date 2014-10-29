package pl.looksok.spring.security.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class MyAuthenticationProvider implements AuthenticationProvider {

	private static HashMap<String, User> userRepository;
	
	static{
		userRepository = new HashMap<String, User>();
		userRepository.put("admin", new User("admin", "pwd", "ADMIN"));
		userRepository.put("user", new User("user", "pwd", "USER"));
	}
	
@Override
public Authentication authenticate(Authentication authentication)
		throws AuthenticationException {
	
	User userFromRepository = userRepository.get(authentication.getName().toLowerCase());
	if(userFromRepository != null){
		if(userFromRepository.password.equals(authentication.getCredentials())){
			List<GrantedAuthority> grantedAuths = new ArrayList<GrantedAuthority>();
			grantedAuths.add(new SimpleGrantedAuthority(userFromRepository.role));
			
			return new UsernamePasswordAuthenticationToken(
					userFromRepository.login, 
					userFromRepository.password, 
					grantedAuths);
		}else{
			throw new BadCredentialsException("invalid password!");
		}

		//here you can provide even more security checks like 
		//account/password expiration, account lock etc.
		// check AuthenticationException.class siblings
	}else{
		throw new UsernameNotFoundException("unknown username");
	}
}

	@Override
	public boolean supports(Class<?> authentication) {
		return true;
	}

	private static class User{
		String login;
		String password;
		String role;
		
		public User(String login, String password, String role) {
			this.login = login;
			this.password = password;
			this.role = role;
		}
	}
}
