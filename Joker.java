package scripts;


import com.sun.org.apache.xpath.internal.operations.Bool;
import org.powerbot.script.*;
import org.powerbot.script.rt4.*;
import org.powerbot.script.rt4.ClientContext;
import scripts.tasks.*;
import scripts.tasks.Bank;


import java.util.concurrent.Callable;

@Script.Manifest(name="Joker", description = "I tell jokes around GE for donations", properties = "author=Tortidip; topic=999; client=4;")

public class Joker extends PollingScript<ClientContext> implements MessageListener {

   public boolean in_business = false;
   public boolean joking = false;
   public int n_sale_talks=0;
   public int counter = 0;
   public Task shout;
   public Task walk;
   public Task bank;
   public TellJoke tellJoke;
   public Trade trade;
   public CheckInventory checkInventory;
   public int inventoryCount;
   public int coinCount;
   public String possibleCustomer;
   public Random rand = new Random();




    @Override
   public void start(){
       System.out.println("Starting script");
       shout = new Shout(ctx);
       walk = new Walk(ctx);
       tellJoke = new TellJoke(ctx);
       checkInventory = new CheckInventory(ctx);
       bank = new Bank(ctx);
   }
   @Override
   public void stop(){
       System.out.println("Stopping script");
   }

   @Override
   public void poll() {
      //main loop
       if(!joking && !in_business && bank.activate()){
           bank.execute();

       }
       else if(joking){
           ctx.input.sendln("kk coming up");
           if(possibleCustomer!=null) {
               tellJoke.tell();
               possibleCustomer = null;
               joking = false;
               in_business = false;
           }
       }
       else if(in_business && !joking) {
           if (!trade.opened()) {
               if (checkInventory.getAmount() > inventoryCount || checkInventory.getCoin() > coinCount) {
                  // System.out.println("joke time");
                   joking = true;

               } else {
                   ctx.input.sendln("No freebies m8 :/ i've got to feed my family.");
                   in_business = false;
                   possibleCustomer = null;
               }

           }
       }

       else if(trade != null && trade.isTrading() && !in_business){
           if(trade.firstOpened() && trade.hasPlayerAccepted()){
               trade.accept();

               System.out.println(coinCount);
           }
           else if(trade.secondOpened() && trade.hasPlayerAccepted()){
               trade.accept();
               in_business = true;


           }
       }

       else {
           counter++;
           inventoryCount = checkInventory.getAmount();
           coinCount = checkInventory.getCoin();
           //System.out.println(counter);

           if (!in_business && counter > rand.nextInt(200,300)) {
               shout();
           }
           if (!in_business && n_sale_talks == 5) {
               walk();
           }
       }

    }


   public void shout(){
       if(shout.activate()){
           n_sale_talks++;
           shout.execute();
           counter = 0;
       }

   }

   public void walk(){
       if(walk.activate()){
           walk.execute();
           n_sale_talks = 0;
       }
   }


    @Override
    public void messaged(MessageEvent messageEvent) {
        //System.out.println(messageEvent.text());
        if(messageEvent.type() == 101 && !in_business) {
            String tradePlayer = messageEvent.source();
            trade = new Trade(ctx, tradePlayer);
            trade.initTrade();
            possibleCustomer = tradePlayer;
        }
        else if (messageEvent.source() == possibleCustomer){
            tellJoke.answered = true;
        }
    }
}

