package hello;

import org.revenj.patterns.DataContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import security.RegisterUser;

@RestController
public class HelloController {
	private static final Charset UTF8 = Charset.forName("UTF-8");
	@Autowired
	DataContext context;

	@RequestMapping("/")
	public List<World> index() {
		return context.search(World.class);
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String index(@RequestBody World world) throws IOException {
		context.create(world);
		return world.getURI();
	}

	@RequestMapping("/api/me")
	public List<World> about() {
		return context.search(World.class);
	}
	@RequestMapping("/api/testevent")
	public String testEvent() {
		RegisterUser domainEvent = new RegisterUser();
		domainEvent.setUsername("JOKO");
		domainEvent.setPassword("P@ssw0rd".getBytes(UTF8));
		context.submit(domainEvent);
		return "OK";
	}
}