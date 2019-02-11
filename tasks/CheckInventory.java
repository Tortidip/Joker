package scripts.tasks;

import org.powerbot.script.Client;
import org.powerbot.script.rt4.ClientContext;
import scripts.Task;

public class CheckInventory {

    private ClientContext ctx;
    public CheckInventory(ClientContext ctx) {
    this.ctx = ctx;
    }


    public int getAmount(){
        return ctx.inventory.select().count();
    }
    public int getCoin(){
        return ctx.inventory.select().id(995).count(true);
    }
}
