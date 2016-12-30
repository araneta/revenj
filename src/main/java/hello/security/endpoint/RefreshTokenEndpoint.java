package hello.security.endpoint;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
import javax.inject.Inject;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.revenj.patterns.DataContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import security.User;

import hello.security.auth.jwt.extractor.TokenExtractor;
import hello.security.auth.jwt.verifier.TokenVerifier;
import hello.security.config.JwtSettings;
import hello.WebSecurityConfig;
import hello.security.exceptions.InvalidJwtToken;
import hello.security.model.UserContext;
import hello.security.model.token.JwtToken;
import hello.security.model.token.JwtTokenFactory;
import hello.security.model.token.RawAccessJwtToken;
import hello.security.model.token.RefreshToken;

/**
 * RefreshTokenEndpoint
 * 
 * @author vladimir.stankovic
 *
 * Aug 17, 2016
 */
@RestController
public class RefreshTokenEndpoint {
    private final DataContext context;

    @Autowired private JwtTokenFactory tokenFactory;
    @Autowired private JwtSettings jwtSettings;
    @Autowired private TokenVerifier tokenVerifier;
    @Autowired @Qualifier("jwtHeaderTokenExtractor") private TokenExtractor tokenExtractor;

    @Inject
    public RefreshTokenEndpoint(DataContext context) {
        this.context = context;
    }
    @RequestMapping(value="/api/auth/token", method=RequestMethod.GET, produces={ MediaType.APPLICATION_JSON_VALUE })
    public @ResponseBody JwtToken refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String tokenPayload = tokenExtractor.extract(request.getHeader(WebSecurityConfig.JWT_TOKEN_HEADER_PARAM));
        
        RawAccessJwtToken rawToken = new RawAccessJwtToken(tokenPayload);
        RefreshToken refreshToken = RefreshToken.create(rawToken, jwtSettings.getTokenSigningKey()).orElseThrow(() -> new InvalidJwtToken());

        String jti = refreshToken.getJti();
        if (!tokenVerifier.verify(jti)) {
            throw new InvalidJwtToken();
        }

        String subject = refreshToken.getSubject();
        //User user = userService.getByUsername(subject).orElseThrow(() -> new UsernameNotFoundException("User not found: " + subject));
        List<User> users = context.find(User.class, new String[]{subject});
        if(users.isEmpty())
            throw new UsernameNotFoundException("User not found: " + subject);
        User user = users.get(0);

        if (user.getRoles() == null) throw new InsufficientAuthenticationException("User has no roles assigned");
        //List<GrantedAuthority> authorities = user.getRoles().stream()
                //.map(authority -> new SimpleGrantedAuthority(authority.getRole().authority()))
                //.collect(Collectors.toList());
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        UserContext userContext = UserContext.create(user.getName(), authorities);

        return tokenFactory.createAccessJwtToken(userContext);
    }
}
