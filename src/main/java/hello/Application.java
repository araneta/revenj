package hello;

import java.io.IOException;

import org.revenj.Revenj;
import org.revenj.extensibility.Container;
import org.revenj.patterns.DataContext;
import org.revenj.patterns.ServiceLocator;
import org.revenj.security.PermissionManager;
import org.revenj.spring.RevenjStartup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ServletContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.sql.DataSource;

@SpringBootApplication
@Configuration
public class Application implements ServletContextInitializer, WebApplicationInitializer {

	private final Container container;
	private final DataSource dataSource;
	@Autowired
	private RequestMappingHandlerAdapter handlerAdapter;

	public Application() throws IOException {
		container = Revenj.setup();
		RevenjStartup.setup(container);
		dataSource = container.resolve(DataSource.class);
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		try {
			//use Revenj permission manager based on roles
			//container.registerAs(new SpringPermissionManager(servletContext), PermissionManager.class);

			//setup initial data for convenience
			//while we could use new InitialData(dataContext) that would break if we change the signature
			//since InitialData has @Inject it will be registered in the revenj_container_Registration generated code
			InitialData id = container.resolve(InitialData.class);
			id.setup();

			//configure Spring related stuff (to get @Autowired working)
			org.revenj.server.servlet.Application.configure(servletContext, container);
			org.revenj.spring.RevenjStartup.setup(container);
			org.revenj.spring.JacksonSetup.configure(handlerAdapter, container);

		} catch (Exception ex) {
			throw new ServletException(ex);
		}
	}

	@Bean
	public ServiceLocator serviceLocator() throws IOException {
		return container;
	}

	@Bean
	public DataSource dataSource() throws IOException {
		return dataSource;
	}

	@Bean
	public DataContext dataContext(ServiceLocator locator) {
		return locator.resolve(DataContext.class);
	}
}