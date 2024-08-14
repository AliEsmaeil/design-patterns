
  package design_patterns.structural_patterns;
  
  // decorator pattern is inspired by required updates on GUI Components
  // decorator DP: it's totally a conformation to OCP by adding a new
  //functionalities to the existing module.
  
  // the idea will be drink maker that just able to make tea, but we need to
  //add the functionality of making coffee
  
  interface AbstractHotDrinkMaker {
  
  public String getCupOfTea();
  }
  
  class TeaMaker implements AbstractHotDrinkMaker {
  
  @Override
  public String getCupOfTea() {
  // this string may be a product to return
  return "Here's a cup of tea";
  }
  
  }
  
  // unitl now, this is just a working component in the system, but we wanna
 // add
  // another functionality (cup of coffee)
  
  
  abstract class CoffeeDecorator implements AbstractHotDrinkMaker {
  
  AbstractHotDrinkMaker teaMaker; // Association relationship: specifically
  //composition (The decorator compose a
  // Tea Maker)
  
  CoffeeDecorator(AbstractHotDrinkMaker teaMaker) {
  this.teaMaker = teaMaker;
  }
  
  abstract String getCupOfCoffee();
  }
  
  class TeaAndCoffeeMaker extends CoffeeDecorator {
  
  TeaAndCoffeeMaker() {
  super(new TeaMaker());
  }
  
  @Override
  public String getCupOfTea() {
  
  return teaMaker.getCupOfTea(); // use the existing functionality
  }
  
  public String getCupOfCoffee() { // additional functionality
  return "Here's a Cup of Coffee";
  }
  
  }
 