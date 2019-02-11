package scripts.tasks;

import org.powerbot.script.Condition;
import org.powerbot.script.rt4.ClientContext;
import scripts.Task;

import java.util.concurrent.Callable;

public class Bank extends Task {

    public Bank(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return ctx.inventory.select().count()>27 ;
    }

    @Override
    public void execute() {
        if(ctx.bank.opened()){
            if(ctx.bank.depositInventory()){
                final int inventCount = ctx.inventory.select().count();
                Condition.wait(new Callable<Boolean>(){
                    @Override
                    public Boolean call() throws Exception {
                        return ctx.inventory.select().count() != inventCount;
                    }
                }, 250, 20);
                ctx.bank.close();
            }
        } else {
            if(ctx.bank.inViewport()) {
                if(ctx.bank.open()){
                    Condition.wait(new Callable<Boolean>(){
                        @Override
                        public Boolean call() throws Exception {
                            return ctx.bank.opened();
                        }
                    }, 250, 20);
                }
            } else {
                ctx.camera.turnTo(ctx.bank.nearest());
            }
        }

    }
}
