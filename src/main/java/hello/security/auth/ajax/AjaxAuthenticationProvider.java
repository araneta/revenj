package hello.security.auth.ajax;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

//import hello.entity.User;
import javax.inject.Inject;
import security.User;
import org.revenj.patterns.DataContext;
import hello.security.model.UserContext;
//import hello.user.service.DatabaseUserService;

/**
 * 
 * @author vladimir.stankovic
 *
 * Aug 3, 2016
 */
@Component
public class AjaxAuthenticationProvider implements AuthenticationProvider {
    //private final BCryptPasswordEncoder encoder;
    //private final DatabaseUserService userService;
	private final DataContext context;
    private static final Charset UTF8 = Charset.forName("UTF-8");
    //@Autowired
    //public AjaxAuthenticationProvider(final DatabaseUserService userService, final BCryptPasswordEncoder encoder) {
    @Inject
    public AjaxAuthenticationProvider(DataContext context) {
		this.context = context;
	}

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Assert.notNull(authentication, "No authentication data provided");

        String username = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();

        //
        // User user = userService.getByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
        List<User> users = context.find(User.class, new String[]{username});
        if(users.isEmpty())
            throw new UsernameNotFoundException("User not found: " + username);
        User user = users.get(0);
        System.out.println(user.getPassword());
        System.out.println(user.getName());
        byte[] bPass = password.getBytes(UTF8);
        byte[] bRealPass = user.getPassword();

        //if (!encoder.matches(password, user.getPassword())) {
        if (!Arrays.equals(bPass,bRealPass)) {
            throw new BadCredentialsException("Authentication Failed. Username or Password not valid.");
        }

        if (user.getRoles() == null) throw new InsufficientAuthenticationException("User has no roles assigned");
        
        //List<GrantedAuthority> authorities = user.getRoles().stream()
                //.map(authority -> new SimpleGrantedAuthority(authority.getRole().authority()))
                //.collect(Collectors.toList());
        System.out.println("123456");
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        GrantedAuthority aut = new SimpleGrantedAuthority("ADMIN");
        authorities.add(aut);

        UserContext userContext = UserContext.create(user.getName(), authorities);
        
        return new UsernamePasswordAuthenticationToken(userContext, null, userContext.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
