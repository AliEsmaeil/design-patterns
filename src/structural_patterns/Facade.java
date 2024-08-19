
// facede pattern: defines a higher level interface to make a complex subsystem easy to use

// suppose we have a shape drawing system like the following:

interface ShapeDrawer {

    void draw();
}

class CircleDrawer implements ShapeDrawer {

    @Override
    public void draw() {
        // code to draw circle here
        System.out.println("Circle Drawn");
    }

}

class SquareDrawer implements ShapeDrawer {

    @Override
    public void draw() {
        System.out.println("Square Drawn");
    }
}

class TriangleDrawer implements ShapeDrawer {

    @Override
    public void draw() {
        System.out.println("Triangle Drawn");
    }
}

// end of drawing system
// now, in our system : suppose we need to draw all of these shapes once the
// user press some button!
// should we go and collaborate with this subsystem directly, what a complexity!
// (such subsystems will be more complex, this's a simple one)

/**
 * Drawer Facade
 */
interface Facade {

    public void drawAllShapes();
}

class DrawerFacade implements Facade {

    @Override
    public void drawAllShapes() {
        new CircleDrawer().draw();
        new SquareDrawer().draw();
        new TriangleDrawer().draw();

    }
}
