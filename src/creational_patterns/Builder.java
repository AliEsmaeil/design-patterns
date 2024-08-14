package design_patterns.creational_patterns;

// abstract layer for the vehicle product

abstract class ComplexVeihcle {

    String typeOfBody;
    int numberOfWheels;
    int numberOfDoors;
    String lightColors;

    abstract void setUpBody(String typeOfBody);

    abstract void setUpWheels(int numberOfWheels);

    abstract void setUpLightining(String lightColor);

    abstract void setUpDoors(int numberOfDoors);

    abstract ComplexVeihcle getVeihcle();
}

// the actual complex product

class Car extends ComplexVeihcle {

    Car() {
    }

    Car(String typeOfBody, int numberOfWheels, String lightColor, int numberOfDoors) {
        this.typeOfBody = typeOfBody;
        this.numberOfDoors = numberOfDoors;
        this.lightColors = lightColor;
        this.numberOfWheels = numberOfWheels;
    }

    @Override
    public void setUpBody(String typeOfBody) {

        this.typeOfBody = typeOfBody;
    }

    @Override
    public void setUpWheels(int numberOfWheels) {
        this.numberOfWheels = numberOfWheels;
    }

    @Override
    public void setUpLightining(String lightColor) {
        this.lightColors = lightColor;
    }

    @Override
    public void setUpDoors(int numberOfDoors) {
        this.numberOfDoors = numberOfDoors;
    }

    @Override
    Car getVeihcle() {
        return new Car(typeOfBody, numberOfWheels, lightColors, numberOfDoors);
    }

}

class Train extends ComplexVeihcle {

    Train() {
    }

    Train(String typeOfBody, String lightColors, int numberOfWheels, int numberOfDoors) {

        this.typeOfBody = typeOfBody;
        this.numberOfWheels = numberOfWheels;
        this.numberOfDoors = numberOfDoors;
        this.lightColors = lightColors;
    }

    @Override
    void setUpBody(String typeOfBody) {
        this.typeOfBody = typeOfBody;
    }

    @Override
    void setUpWheels(int numberOfWheels) {
        this.numberOfWheels = numberOfWheels;
    }

    @Override
    void setUpLightining(String lightColor) {
        this.lightColors = lightColor;
    }

    @Override
    void setUpDoors(int numberOfDoors) {
        this.numberOfDoors = numberOfDoors;
    }

    @Override
    Train getVeihcle() {

        return new Train(typeOfBody, lightColors, numberOfWheels, numberOfDoors);
    }

}

// the above part ********************only represents the product
// section***************

// the coming is the builder section

abstract class AbstractVehicleBuilder {

    abstract void setUpBody(String typeOfBody);

    abstract void setUpLightning(String lightColor);

    abstract void setUpDoors(int numberOfDoors);

    abstract void setUpWheels(int numberOfWheels);

    abstract ComplexVeihcle getVeihcle();
}

class VehicleBuilder extends AbstractVehicleBuilder {

    ComplexVeihcle veihcle;
    String vehicleType;

    VehicleBuilder(String vehicleType) throws Exception {
        initializeVehicle(vehicleType);
    }

    void initializeVehicle(String vehicleType) throws Exception { // this method conforms to Factory method patterns
        // because it decide which subtype of product to instantiate
        // (that's why Builder design pattern is similar to Factory method pattern)
        // the subtypes being built (builder) and choosed(factory method)

        switch (vehicleType.toLowerCase()) {
            case "car":
                veihcle = new Car();
                break;
            case "train":
                veihcle = new Train();
                break;
            default:
                throw new Exception("Format exception");
        }

    }

    @Override
    void setUpBody(String typeOfBody) {
        veihcle.setUpBody(typeOfBody);
    }

    @Override
    void setUpLightning(String lightColor) {
        veihcle.setUpLightining(lightColor);
    }

    @Override
    void setUpDoors(int numberOfDoors) {
        veihcle.setUpDoors(numberOfDoors);
    }

    @Override
    void setUpWheels(int numberOfWheels) {
        veihcle.setUpWheels(numberOfWheels);
    }

    @Override
    ComplexVeihcle getVeihcle() {
        return veihcle;
    }

}

// the coming part is the belongs to director class , which directs the build
// process of the product

class Director {

    private AbstractVehicleBuilder builder;

    Director(AbstractVehicleBuilder builder) {
        this.builder = builder;
    }

    void setUpBody(String typeOfBody) {
        builder.setUpBody(typeOfBody);
    }

    void setUpLightning(String lightsColor) {
        builder.setUpLightning(lightsColor);
    }

    void setUpDoors(int numberOfDoors) {
        builder.setUpDoors(numberOfDoors);
    }

    void setUpWheels(int numberOfWheels) {
        builder.setUpWheels(numberOfWheels);
    }

    ComplexVeihcle getVeihcle() {
        return builder.getVeihcle();
    }

}