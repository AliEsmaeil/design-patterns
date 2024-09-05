
import behavioral_patterns.Request;
import java.util.logging.Handler;



public class App {

    public static void main(String[] args) {
        
        // Here's the request, suppose we have the following text to clean
        Request<String> request = new SpeechTextCleaningRequest("تَجْرِيبٌ    تَجْرِيبٌ           تَجْرِيبٌ");

        Handler firstHandlerInChain = new DiacriticsHandler();

        firstHandlerInChain.handle(request);

        System.out.println("Data After Cleaning: " + request.getData());

        // and here's the result: Data After Cleaning: تجريب تجريب تجريب 


    }
}
