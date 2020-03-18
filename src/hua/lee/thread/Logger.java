package hua.lee.thread;
public class Logger{
    private static Logger logger = new Logger();
    private static String defalutName = "Logger";
    private Logger(){}

    public static Logger getLogger(){
        return logger;
    }

    public void log(int level,String name,String log){
        System.out.println(name+": "+log);
    }
    public void log(int level,String log){
        System.out.println(defalutName+": "+log);
    }
    public void fine(String log){
        System.out.println(defalutName+": "+log);
    }
}
