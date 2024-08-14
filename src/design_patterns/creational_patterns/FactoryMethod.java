package design_patterns.creational_patterns;

// Factory method DP: is a creational design paattern that defines an interface (Factory) for creating objects (SubProducts)
// but let sub classes (concrete factory) decide which subclass (sub product) to instantiate.

abstract class Drink {
    int millimeters;
    String name;
    boolean isHot;

    Drink(int millis, String name, boolean isHot) {
        this.millimeters = millis;
        this.name = name;
        this.isHot = isHot;
    }
}

class Tea extends Drink {

    Tea() {
        super(80, "Tea", true);
    }

}

class Coffee extends Drink {

    Coffee() {
        super(30, "Coffee", true);
    }
}

interface DrinkFactory {

    public Drink getDrink(String drinkName);
}

class ConcreteDrinkFactory implements DrinkFactory {

    public Drink getDrink(String drinkName) {

        switch (drinkName) {
            case "Tea":
                return new Tea();
            case "Coffee":
                return new Coffee();
            default:
                return new Tea();
        }
    }

}