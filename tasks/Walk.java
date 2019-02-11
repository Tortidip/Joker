package scripts.tasks;

import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import scripts.Task;
import scripts.Walker;

public class Walk extends Task {

    public Tile[] path0 = {new Tile(3165, 3483, 0), new Tile(3161, 3483, 0), new Tile(3158, 3486, 0)};
    public Tile[] path1 = {new Tile(3158, 3486, 0), new Tile(3157, 3490, 0), new Tile(3160, 3493, 0)};
    public Tile[] path2 = {new Tile(3160, 3493, 0), new Tile(3163, 3496, 0), new Tile(3167, 3494, 0), new Tile(3171, 3494, 0)};
    public Tile[] path3 = {new Tile(3172, 3494, 0), new Tile(3169, 3490, 0), new Tile(3172, 3487, 0), new Tile(3168, 3484, 0)};
    public Tile[] path4 = {new Tile(3157, 3488, 0), new Tile(3161, 3489, 0), new Tile(3161, 3493, 0), new Tile(3157, 3494, 0)};
    public Tile[] path5 = {new Tile(3157, 3494, 0), new Tile(3160, 3497, 0), new Tile(3163, 3494, 0), new Tile(3163, 3497, 0), new Tile(3166, 3495, 0)};
    public Tile[] path6 = {new Tile(3166, 3495, 0), new Tile(3170, 3493, 0), new Tile(3168, 3489, 0), new Tile(3170, 3485, 0)};

    Walker walker = new Walker(ctx);
    private int routeNumber;
    public Walk(ClientContext ctx) {
        super(ctx);
        routeNumber = 0;
    }

    @Override
    public boolean activate() {
        return ctx.players.local().animation()==-1;
    }

    @Override
    public void execute() {
        switch (routeNumber){
            case 0:
                walker.walkPath(path0);
                routeNumber++;
                break;
            case 1:
                walker.walkPath(path1);
                routeNumber++;
                break;
            case 2:
                walker.walkPath(path2);
                routeNumber++;
                break;
            case 3:
                walker.walkPath(path3);
                routeNumber++;
                break;
            case 4:
                walker.walkPath(path4);
                routeNumber++;
                break;
            case 5:
                walker.walkPath(path5);
                routeNumber++;
                break;
            case 6:
                walker.walkPath(path6);
                routeNumber = 0;
                break;
            default:
                walker.walkPath(path0);
                break;

        }
    }
}
