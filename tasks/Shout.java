package scripts.tasks;

import org.powerbot.script.rt4.ClientContext;
import scripts.Task;

import java.util.Random;

public class Shout extends Task {

public String[] sale_talks = {"For z donation i will tell you a joke","Telling jokes fr donations","Help a poor comedian out, jokes for donations","joks for donations","yo Mon, i'm the jokermon, gimme a donation for a joke","jokes for donations","Telling juicy jokes for donations","brighten up your day, jokes for donations!","Hear me jokes for a small donation","Donate->Joke->Laugh","Telling jokes for donations!", "Let me cheer up your sad day, jokes for donations", "Have a laugh with me, i'm hilarious! Jokes for donations", "i bring joy to your life, let me tell you a joke for a donation"};
public Random rand = new Random();
    public Shout(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return !ctx.chat.chatting();
    }

    @Override
    public void execute() {

        int index = rand.nextInt(sale_talks.length);
        ctx.input.sendln(sale_talks[index]);

    }
}
