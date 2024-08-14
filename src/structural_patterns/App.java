package design_patterns.structural_patterns;

public class App {

    public static void main(String[] args) {

        Facade myFacadeLayer = new DrawerFacade();

        myFacadeLayer.drawAllShapes();

    }
}
