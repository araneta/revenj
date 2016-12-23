public class revenj_container_Registrations implements org.revenj.extensibility.SystemAspect {
  @Override
  public void configure(org.revenj.extensibility.Container container) {
    container.registerFactory(hello.InitialData.class, c -> new hello.InitialData(c.resolve(org.revenj.patterns.DataContext.class)), false);

  }
}