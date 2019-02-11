package scripts.tasks;

import org.powerbot.script.Condition;
import org.powerbot.script.MessageEvent;
import org.powerbot.script.rt4.ClientContext;


import java.util.ArrayList;
import java.util.concurrent.Callable;

public class TellJoke {

    private ArrayList<String> questions = new ArrayList<String>();
    private ArrayList<String> answers = new ArrayList<String>();
    private int index;
    public boolean answered = false;
    public ClientContext ctx;

    public TellJoke(ClientContext ctx) {
        this.ctx = ctx;
        index = 0;

        //joke1
        questions.add("How do you call a guy with a rubber toe");
        answers.add("Roberto");

        //joke2
        questions.add("Why did the old man fall into the well");
        answers.add("because he couldn't see that well");

        //joke3
        questions.add("Yo mama is like al kharid gate");
        answers.add("only 10 gp to enter");

        //joke4
        questions.add("What is the major difference between a bird and a fly");
        answers.add("a bird can fly, but a fly cannot bird");

        //joke5
        questions.add("why did the jewish wife ask her husband to get circumsized");
        answers.add("because she wouldn't touch things that arent 75 percent off");

        //joke6
        questions.add("why are americans bad at chess");
        answers.add("because they lost two towers");

        //joke7
        questions.add("what do you call a fat psychic");
        answers.add("a four chin teller");

        //joke8
        questions.add("what do you call a computer that sings");
        answers.add("A Dell");

        //joke9
        questions.add("Why was the baby strawberry crying");
        answers.add("Because its mom and dad were in a jam");

        //joke10
        questions.add("What do you call cheese that isnt yours");
        answers.add("nacho cheese");
    }


    public void tell() {
        if(index == questions.size()){
            index = 0;
        }

        ctx.input.sendln(questions.get(index));
        Condition.wait(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return answered;
            }
        },500,12);

        ctx.input.sendln(answers.get(index));
        answered = false;
        index++;
    }



}
