import org.apache.log4j.Logger;

public class LoggerExample {

    private final static Logger logger = Logger.getLogger(LoggerExample.class);

    public static void main(String[] args) {

        LoggerExample obj = new LoggerExample();
        obj.runMe("mkyong");
    }

    private void runMe(String parameter){
        org.apache.log4j.BasicConfigurator.configure();
        if(logger.isDebugEnabled()){
            logger.debug("This is debug : " + parameter);
        }

        if(logger.isInfoEnabled()){
            logger.info("This is info : " + parameter);
        }

        logger.warn("This is warn : " + parameter);
        logger.error("This is error : " + parameter);
        logger.fatal("This is fatal : " + parameter);

    }

}
