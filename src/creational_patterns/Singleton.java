package design_patterns.creational_patterns;

public class Singleton {

    private static Singleton instance;

    private Singleton() {
    }

    // global access point for every client

    public static synchronized Singleton getInstance() {

        // synchronized keyword : blocks multiple threads to access the same shared
        // resource (getInstance()) at the same time

        if (instance == null) {
            instance = new Singleton();
        }
        return instance;

    }

}
