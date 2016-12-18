package hello;

import org.revenj.patterns.Query;
import org.revenj.patterns.Specification;
import org.revenj.patterns.DataSource;
import org.revenj.security.PermissionManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;

import java.io.Closeable;
import java.security.Principal;
import java.util.List;
import java.util.Objects;

public  class SpringPermissionManager implements PermissionManager {
    @Override
    public boolean canAccess(String identifier, Principal user) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return auth.isAuthenticated();

    }
    @Override
    public <T extends org.revenj.patterns.DataSource, S extends T> Query<S> applyFilters(Class<T> manifest, Principal user, Query<S> data) {
        return data;
    }
    @Override
    public <T extends org.revenj.patterns.DataSource, S extends T> List<S> applyFilters(Class<T> manifest, Principal user, List<S> data) {
        return data;
    }
    @Override
    public <T extends org.revenj.patterns.DataSource> Closeable registerFilter(Class<T> manifest, Specification<T> filter, String role, boolean inverse) {
        return null;
    }
}
