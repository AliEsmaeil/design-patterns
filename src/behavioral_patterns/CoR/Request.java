package behavioral_patterns.CoR;

// this is the request abstraction layer, whenever you need to make a request you should extend of this layer

public abstract class Request<T> {

    T data;

    Request(T data) {
        this.data = data;
    }

    void setData(T newData){
        data = newData;
    }

    T getData(){
        return data;
    }

}

// concrete request

class SpeechTextCleaningRequest extends Request<String>{

    SpeechTextCleaningRequest(String data){
        super(data);
    }

}



