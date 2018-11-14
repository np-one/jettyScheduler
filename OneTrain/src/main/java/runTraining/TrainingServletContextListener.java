package runTraining;

import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.concurrent.TimeUnit;


public class TrainingServletContextListener implements ServletContextListener {


    public static final EventLoopGroup group = new NioEventLoopGroup(1);

    public void contextInitialized(ServletContextEvent event) {
        System.out.println("Initializing TrainingServletContextListener...");
//        logger.info("Initializing TrainingServletContextListener...");
        runScheduledTraining();
    }

    public void contextDestroyed(ServletContextEvent event) {
    }

    public static void runScheduledTraining() {
        // run training every 24 hours
        group.scheduleWithFixedDelay(
                new Runnable() {
                    @Override
                    public void run() {
                        try {
                            System.out.println("Running training now...");
//                            logger.info("Running training now...");
                            Runtime.getRuntime().exec("sh runTrain.sh");
                            // TODO: Add code to run training here
                            System.out.println("Completed training.");
//                            logger.info("Completed training.");
                        }
                        catch (Throwable e) {
                            System.out.println("Error during Training: ");
//                            logger.error("Error during Training: " + e.getMessage(), e);
                        }
                    }
                }, 24, 24, TimeUnit.HOURS
        );
    }
}

