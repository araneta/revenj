package hello;

import org.revenj.patterns.DataSource;
import org.revenj.patterns.Query;
import org.revenj.patterns.Specification;
import org.revenj.security.PermissionManager;

import javax.servlet.ServletContext;
import java.io.Closeable;
import java.security.Principal;
import java.util.List;

class SpringPermissionManager implements PermissionManager {

	private final ServletContext servletContext;

	public SpringPermissionManager(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	@Override
	public boolean canAccess(String identifier, Principal user) {
		//user is Servlet provided principal
		//if (user == null) throw new SecurityException("Login on /home before you can access this");
		//return "user".equals(user.getName());
		//custom error messages can be provided with security exception
		return true;

	}

	@Override
	public <T extends DataSource, S extends T> Query<S> applyFilters(Class<T> manifest, Principal user, Query<S> data) {
		return data;
	}

	@Override
	public <T extends DataSource, S extends T> List<S> applyFilters(Class<T> manifest, Principal user, List<S> data) {
		return data;
	}

	@Override
	public <T extends DataSource> Closeable registerFilter(Class<T> manifest, Specification<T> filter, String role, boolean inverse) {
		return null;
	}
}
