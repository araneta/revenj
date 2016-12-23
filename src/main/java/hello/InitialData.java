package hello;

import org.revenj.patterns.DataContext;
import security.GlobalPermission;
import security.RolePermission;
import security.User;

import javax.inject.Inject;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class InitialData {

	private final DataContext context;

	@Inject
	public InitialData(DataContext context) {
		this.context = context;
	}

	private static final Charset UTF8 = Charset.forName("UTF-8");

	private void addUserIfMissing(String name, Role role, List<User> existing, List<User> newUsers) {
		if (existing.stream().noneMatch(it -> it.getName().equals(name))) {
			User user = new User()
					.setName(name)
					.setIsAllowed(true)
					.setPassword(name.getBytes(UTF8));
			user.getRoles().add(role.name());
			newUsers.add(user);
		}
	}

	public void setup() throws IOException {
		List<User> users = context.find(User.class, new String[]{"user", "admin"});
		List<User> newUsers = new ArrayList<>();
		addUserIfMissing("user", Role.USER, users, newUsers);
		addUserIfMissing("admin", Role.ADMIN, users, newUsers);
		if (!newUsers.isEmpty()) {
			context.create(newUsers);
		}
		List<GlobalPermission> revenjGlobal =
				context.search(GlobalPermission.class, new GlobalPermission.WithPrefix("org.revenj"));
		List<GlobalPermission> securityGlobal =
				context.search(GlobalPermission.class, new GlobalPermission.WithPrefix("security"));
		List<GlobalPermission> newGlobal = new ArrayList<>();
		if (revenjGlobal.isEmpty()) {
			//allow global access to org.revenj stuff
			newGlobal.add(new GlobalPermission().setName("org.revenj").setIsAllowed(true));
		}
		if (securityGlobal.isEmpty()) {
			//disallow global access to security related stuff
			newGlobal.add(new GlobalPermission().setName("security").setIsAllowed(false));
		}
		if (!newGlobal.isEmpty()) {
			context.create(newGlobal);
		}
		List<RolePermission> adminRole =
				context.search(RolePermission.class, new RolePermission.ForRole(Role.ADMIN.name()));
		if (adminRole.isEmpty()) {
			//allow access to security to users in admin role
			context.create(new RolePermission().setName("security").setRoleID(Role.ADMIN.name()).setIsAllowed(true));
		}
	}
}
