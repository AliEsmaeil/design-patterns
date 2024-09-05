package behavioral_patterns.CoR;

// this is the abstraction layer for every handler in the sustem to extend.
public abstract class Handler {

    Handler nextHandler;

    void setNextHandler(Handler handler) {

        nextHandler = handler;
    }

    public abstract void handle(Request<String> request);

}

// Here's the scenario: suppose we are implemnting a Speech to text system and
// it works perfectly (in arabic)
// but we just need to remove the Diacritics in the arabic words like (fatha,
// kasra, ...etc) and remove extra unneeded spaces.
// so, we will need two handlers one for the Diacritics and the other for spaces
// while the text will pass through both of them
// to get proccessed(data cleaning). i know it's in the most clean format but
// users don't need Diacritics in the text!

class DiacriticsHandler extends Handler {

    @Override
    public void handle(Request<String> request) {
        
        setNextHandler(new UnneededSpacesHandler());
        
        if(request.data instanceof  String){

            String diacriticRegExp = "[\\u0617-\\u061A\\u064B-\\u0652]";

            request.setData(request.data.replaceAll(diacriticRegExp, ""));
        }

        if(nextHandler != null){
            nextHandler.handle(request);
        }

        
    }

}


class UnneededSpacesHandler extends  Handler{

    @Override
    public void handle(Request<String> request) {
        setNextHandler(null);
       
        if(request.data.contains("  ")){ // if it contains more than one sequential space char

            StringBuilder stringBuilder = new StringBuilder();

            String [] arr = request.data.split(" ");

            for(var str : arr){
                if(!str.isEmpty()){
                    stringBuilder.append(str.trim());
                }
            }
            request.data = stringBuilder.toString();
        }
        if(nextHandler != null){ // although i have set it above, but it's ok
        // (this if clause will never get executed because  there's no more handlers)
            nextHandler.handle(request);
        }
    }

    
}

        //Notes:
        /*
         * The chain is implemnted implicitly within each handler (each handler refers to the next one)
         * we need to manually set next handlers here, handlers should know each other and refer to other handlers
         * 
         * (It's a good practice to remove this responsibility from shoulders of clients , it's not their responsibility)
         * (unless they need to specify orders of the handlers manually , it's again and again a usecase)
         * 
         * 
         * Do not ever break the chain, which will cause the data get returned without any processing
         * Borken Chain problem, you should check for next handlers and pass requests to them depending on the usecase
         * a usecase may required that request to handled by multiple handlers like our cleaning example
         * and other usecases may require only one handler to handle the request once a match found, then the chain get broken
         * so, it's a usecase (what do you need) how this program should behave. 
         */
