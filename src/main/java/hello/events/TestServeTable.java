package hello.events;
import org.revenj.patterns.DataContext;
import java.util.concurrent.Callable;
import org.revenj.patterns.DomainEventHandler;	

@org.revenj.patterns.EventHandler
public class TestServeTable implements DomainEventHandler<Callable<Inheritance.ServeTable>> {
  private final DataContext context;
  public TestServeTable(DataContext context) { this.context = context; }
  public void handle(Callable<Inheritance.ServeTable> afterPersistance) {
		System.out.println("test event");
  }
}
