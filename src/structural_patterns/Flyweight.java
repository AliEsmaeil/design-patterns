
import java.util.*;

// coordinates representer
class Point {
    double x;
    double y;

    Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

}

// soldier abstraction layer

abstract class WarSoldier {

    Point initialLocation;

    WarSoldier(Point initialLocation) {
        this.initialLocation = initialLocation;
    }

    void setInitialLocation(Point p) {
        initialLocation = p;
    }

    void moveToLocation(Point point) {
        // graphical rendering of moving (graphical translation)
    }
}

class Soldier extends WarSoldier {

    // applay singleton to each war man, we just need one instance
    private static Soldier soldier;
    public static final String name = "Soldier";

    private Soldier(Point initialLocation) {
        super(initialLocation);
    }

    public static synchronized Soldier getSoldier(Point initialLoc) {
        if (soldier == null) {
            soldier = new Soldier(initialLoc);
        } else {
            soldier.setInitialLocation(initialLoc);
        }
        return soldier;

    }
}

class Colonil extends WarSoldier {

    // applay singleton to each war man, we just need one instance
    private static Colonil colonil;
    public static final String name = "Colonil";

    private Colonil(Point initialLocation) {
        super(initialLocation);
    }

    public static synchronized Colonil getColonil(Point initialLoc) {
        if (colonil == null) {
            colonil = new Colonil(initialLoc);
        } else {
            colonil.setInitialLocation(initialLoc);
        }
        return colonil;

    }
}

//////////////// objects are done (those objects should e shared because a war
////////////// game could contain thousands of soldiers)
//////////// so here's the flyweight which manages those soldiers through
////////// using an object pool
/////// object pool is considered a temporary caching of existent objects
////// until needed, could be implemented through
///// any data structure like Map or even a list (array), preferably a
//// Map (Dictionary in C#)
/// Let's get this done.

class WarEntityFlyweight {

    // you should make an object pool right there and applay the factory method
    // pattern
    // if you did, this pattern will be called Factory Flyweight.

    /**
     * this is the object pool where objects (soldiers) are cached for later use
     */
    private static HashMap<String, WarSoldier> soldiers = new HashMap<>();

    Soldier getSoldier(Point initialLocation) {
        if (!soldiers.containsKey(Soldier.name)) {
            soldiers.put(Soldier.name, Soldier.getSoldier(initialLocation));

        }
        return (Soldier) soldiers.get(Soldier.name);
    }

    Colonil getColonil(Point initialLocation) {
        if (!soldiers.containsKey(Colonil.name)) {
            soldiers.put(Colonil.name, Colonil.getColonil(initialLocation));

        }
        return (Colonil) soldiers.get(Colonil.name);
    }

    // the above two methods could be considered as Factory method pattern if
    // combined,
    // but they are separated for simplicity reasons

}

// flyweight is done, here's how the client could use it

class WarGame {

    public static void dropSoldiersInAreana() {

        WarEntityFlyweight factoryFlyweight = new WarEntityFlyweight();

        List<WarSoldier> soldiers = new ArrayList<WarSoldier>(50);

        for (int i = 0; i < soldiers.size(); i++) {
            if (i % 10 == 0) {
                soldiers.set(i, factoryFlyweight.getColonil(new Point(i, i)));
            } else {
                soldiers.set(i, factoryFlyweight.getSoldier(new Point(i, i)));
            }
        }

        for (int i = 0; i < soldiers.size(); i++) {

            if (soldiers.get(i).getClass().getName().equals("Soldier")) {
                Soldier soldier = (Soldier) soldiers.get(i);
                System.out.println("It's a " + Soldier.name);
                System.out.println("he is located in " + soldier.initialLocation);
                // will drop him in a 2D Scene
            } else {

                Colonil colonil = (Colonil) soldiers.get(i);
                System.out.println("It's a " + Colonil.name);
                System.out.println("he is located in " + colonil.initialLocation);
                // will drop him in a 2D Scene
            }

        }

    }
}
