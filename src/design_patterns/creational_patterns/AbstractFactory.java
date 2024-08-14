package design_patterns.creational_patterns;

/// products part (Drinks and Foods)

// DRINKS PRODUCTS
abstract class Drink {

    String name;
    int millimeters;
    boolean isHot;

    public Drink(String name, int millimeters, boolean isHot) {
        this.name = name;
        this.millimeters = millimeters;
        this.isHot = isHot;
    }

}

class Tea extends Drink {
    Tea() {
        super("tea", 160, true);
    }
}

class Coffee extends Drink {
    Coffee() {
        super("coffee", 40, true);
    }
}

// FOOD PRODUCTS

abstract class Food {
    String name;
    double kilograms;
    boolean isFresh;

    public Food(String name, double kilograms, boolean isFresh) {
        this.name = name;
        this.kilograms = kilograms;
        this.isFresh = isFresh;
    }

}

class Fish extends Food {
    Fish() {
        super("fish", 1, true);
    }
}

class Meat extends Food {
    Meat() {
        super("meat", 1, true);
    }
}

// factories part
// DRINK FACTORY (FACTORY CLASS THAT BUILDS A DRINK)
// FOOD FACTORY (FACTORY CLASS THE BUILDS A FOOD)

// BASE CLASS OF ALL FACTORIES (SUBTYPING HELPS TO RETURN ALL FACTORIS (OCP))

interface Factory<T> {

    T getProduct(String productName);
}

class DrniksFactory implements Factory<Drink> {

    @Override
    public Drink getProduct(String productName) {
        switch (productName) {
            case "tea":
                return new Tea();
            case "coffee":
                return new Coffee();

            default:
                return new Tea();
        }
    }

}

class FoodFactory implements Factory<Food> {

    @Override
    public Food getProduct(String productName) {
        switch (productName) {
            case "fish":
                return new Fish();
            case "meat":
                return new Meat();

            default:
                return new Fish();
        }
    }

}

class FactoryOfFactories {

    @SuppressWarnings("rawtypes")
    Factory getFactory(String drinkOrEat) {

        if (drinkOrEat.contains("eat")) {
            return new FoodFactory();
        } else if (drinkOrEat.contains("drink")) {
            return new DrniksFactory();
        } else {
            return new DrniksFactory();
        }
    }

}
