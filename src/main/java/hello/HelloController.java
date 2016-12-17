package hello;

import org.revenj.patterns.DataContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.List;

@RestController
public class HelloController {

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
}