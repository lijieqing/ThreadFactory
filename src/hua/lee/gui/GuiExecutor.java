package hua.lee.gui;

import java.util.List;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.TimeUnit;

public class GuiExecutor extends AbstractExecutorService {
    private static final GuiExecutor instance = new GuiExecutor();

    private GuiExecutor() {
    }

    public static GuiExecutor instance() {
        return instance;
    }

    @Override
    public void shutdown() {
        

    }

    @Override
    public List<Runnable> shutdownNow() {
        
        return null;
    }

    @Override
    public boolean isShutdown() {
        
        return false;
    }

    @Override
    public boolean isTerminated() {
        
        return false;
    }

    @Override
    public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
        
        return false;
    }

    @Override
    public void execute(Runnable r) {
        if(SwingUtilities.isEventDispatchThread()){
            r.run();
        }else{
            SwingUtilities.invokeLater(r);
        }

    }
    
}