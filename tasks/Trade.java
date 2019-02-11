package scripts.tasks;

import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Player;
import org.powerbot.script.rt4.Widget;
import scripts.Task;

public class Trade {

    private String playername;
    private boolean trading;
    public static final String WAIT_TEXT = "Other player has accepted.";
    private ClientContext ctx;
    public static final int
            WIDGET_SECOND = 334,
            ACCEPT_SECOND = 49,
            DECLINE_SECOND = 54,
            WAITING_FOR_SECOND = 14,

            WIDGET = 335,
            CLOSE_BUTTON = 14,
            ACCEPT_FIRST = 21,
            DECLINE_FIRST = 23,
            OUR_INVENTORY = 26,
            THEIR_INVENTORY = 29,
            WAITING_FOR_PLAYER = 33,
            DEPOSIT_INVENTORY = 40,
            DEPOSIT_POUCH = 41,
            OTHER_VALUE = 47,

    MONEY_WIDGET = 1469,
            MONEY_COMPONENT = 2;

    public Trade( ClientContext ctx, String playername) {
        this.playername = playername;
        this.ctx = ctx;
    }




    public void initTrade() {
        Player player = ctx.players.select().name(playername).poll();
        player.interact("Trade");

    }
    public boolean isTrading(){
       return opened();

        }


    public boolean opened() {
        return firstOpened() || secondOpened();
    }

    public boolean firstOpened() {
        //return ctx.widgets.component(WIDGET, ACCEPT_FIRST).visible();
          return ctx.widgets.widget(335).component(12).visible();
    }

    public boolean secondOpened() {
        //return ctx.widgets.component(WIDGET_SECOND, ACCEPT_SECOND).visible();
         return ctx.widgets.widget(334).component(13).visible();
    }
    public boolean accept() {
        //return opened() && click(firstOpened() ? ACCEPT_FIRST : ACCEPT_SECOND);
        if(firstOpened()){
            ctx.widgets.widget(335).component(12).click();
            return true;
        }
        else if(secondOpened()){
            ctx.widgets.widget(334).component(13).click();
            return true;
        }
        else{
            return false;
        }
    }


    public boolean hasPlayerAccepted() {
        if(firstOpened()){
            return ctx.widgets.widget(335).component(30).text().equals(WAIT_TEXT);
        }
        else if (secondOpened()) {
            return ctx.widgets.widget(334).component(4).text().equals(WAIT_TEXT);
        }
        else{
            return false;
        }
    }

}
