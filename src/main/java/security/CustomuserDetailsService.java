package security;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.Token.entities.UserEntity;
import com.example.Token.repository.UserRepository;

public class CustomuserDetailsService implements UserDetailsService {

	private UserRepository userRepository;

	@Autowired
	public CustomuserDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity user= userRepository.findByLogin(username).orElseThrow(() -> new UsernameNotFoundException("sername non trovato"));
		return new User(user.getLogin(),user.getPassword(),(Collection<? extends GrantedAuthority>) user.getRoles());

	}
}