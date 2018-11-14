package runTraining;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TrainingServer {

    public static final int DEFAULT_PORT = 8070;
    public static final int MAX_POOL_THREADS = 10;
    public static final int IDLE_TIMEOUT = 30000;

    public static void main(String[] args) throws Exception {
        Server server = new Server(DEFAULT_PORT);

        ServletContextHandler context = new ServletContextHandler();
        context.setContextPath("/");

        TrainingServletContextListener contextListener = new TrainingServletContextListener();
        context.addEventListener(contextListener);

        // for context based static file serving and error handling
        context.addServlet(DefaultServlet.class, "/");

        HandlerList handlers = new HandlerList();
        handlers.addHandler(context);
        // for non-context error handling
        handlers.addHandler(new DefaultHandler());

        server.setHandler(handlers);
        server.start();
        server.join();
    }

    public static void addShutdownHook() {
        Thread shutdownHook = new Thread() {
            @Override
            public void run() {
                shutdown();
            }
        };
        Runtime.getRuntime().addShutdownHook(shutdownHook);
    }

    private static void shutdown() {
        try {
            // put shutdown logic here
        }
        catch (Throwable t) {
            System.out.println("Failed to shutdown training server.");
//            logger.error("Failed to shutdown training server.");
        }
    }
}
