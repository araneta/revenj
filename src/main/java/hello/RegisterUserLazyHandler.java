package hello;

import org.revenj.patterns.DataContext;
import org.revenj.patterns.DomainEventHandler;
import org.revenj.patterns.EventHandler;
import security.RegisterUser;
import security.User;
import security.UserRegistered;

import java.io.IOException;
import java.util.concurrent.Callable;

@EventHandler
public class RegisterUserLazyHandler implements DomainEventHandler<Callable<RegisterUser>> {

	private final DataContext context;

	public RegisterUserLazyHandler(DataContext context) {
		this.context = context;
	}

	@Override
	//this handler will be called after event is stored into the database
	public void handle(Callable<RegisterUser> domainEvent) {
		try {
			UserRegistered registered = new UserRegistered()
					.setUsername(domainEvent.call().getUsername());
			context.submit(registered);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
