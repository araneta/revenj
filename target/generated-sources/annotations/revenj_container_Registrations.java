public class revenj_container_Registrations implements org.revenj.extensibility.SystemAspect {
  @Override
  public void configure(org.revenj.extensibility.Container container) {
    container.registerFactory(hello.security.endpoint.RefreshTokenEndpoint.class, c -> new hello.security.endpoint.RefreshTokenEndpoint(c.resolve(org.revenj.patterns.DataContext.class)), false);
    container.registerFactory(hello.InitialData.class, c -> new hello.InitialData(c.resolve(org.revenj.patterns.DataContext.class)), false);
    container.registerFactory(hello.security.auth.ajax.AjaxAuthenticationProvider.class, c -> new hello.security.auth.ajax.AjaxAuthenticationProvider(c.resolve(org.revenj.patterns.DataContext.class)), false);

  }
}