package hello;

import org.revenj.patterns.DataContext;
import org.revenj.patterns.DomainEventHandler;
import org.revenj.patterns.EventHandler;
import security.RegisterUser;
import security.User;

import java.io.IOException;

@EventHandler
public class RegisterUserEagerHandler implements DomainEventHandler<RegisterUser> {

	private final DataContext context;

	public RegisterUserEagerHandler(DataContext context) {
		this.context = context;
	}

	@Override
	//this handler will be called before event is stored into the database
	public void handle(RegisterUser domainEvent) {
		if (domainEvent.getUsername().isEmpty()) {
			throw new IllegalArgumentException("Username not provided");
		}
		if (domainEvent.getPassword().length == 0) {
			throw new IllegalArgumentException("Password not provided");
		}
		User newUser = new User()
				.setName(domainEvent.getUsername())
				.setPassword(domainEvent.getPassword())
				.setIsAllowed(true);
		newUser.getRoles().add(Role.GUEST.name());
		//which means we can mutate event before it hits the DB and clear sensitive info from it
		domainEvent.setPassword(new byte[0]);
		try {
			context.create(newUser);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
