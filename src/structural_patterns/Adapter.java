
import java.util.HashMap;

class JSONPrinter {

    // suppose this is the json object
    HashMap<String, Object> json;

    JSONPrinter(HashMap<String, Object> json) {
        this.json = json;
    }

    JSONPrinter() {
        json = new HashMap<>();
    }

    void printJson() {
        for (var entry : json.entrySet()) {

            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }

}

class XMLToJsonAdapterPrinter {

    // suppose this string represents a xml object
    String xml;
    // encapsulate an object of the adaptee type to use it after making adaption
    // (convert XML to JSON)

    private JSONPrinter printer;

    XMLToJsonAdapterPrinter(String xml) {
        this.xml = xml;
    }

    void printJson() {
        HashMap<String, Object> json = convertXMLToJson(); // convert XML to JSON (adapter responsibility)

        // moreover, finishing the required task asked by client (to print!) is a
        // responsibility of the adapter
        // so, an adaptee object should be encapsulated within the adapter
        printer = new JSONPrinter(json);
        printer.printJson();
    }

    HashMap<String, Object> convertXMLToJson() {

        HashMap<String, Object> map = new HashMap<>();

        String key = "";
        String value = "";
        String tempKey = "";
        String tempValue = "";
        boolean keyFound = false;
        boolean valueFound = false;

        for (int i = 0; i < xml.length() - 1; i++) {

            // <firstName>Ali</firstName><lastName>Esmaeil</lastName>

            if (xml.charAt(i) == '<' && xml.charAt(i + 1) != '/' || i == xml.length() - 2) {
                // key found
                keyFound = true;
                valueFound = false;
                tempValue = value;
            }
            if (keyFound) {
                value = "";
                key += xml.charAt(i);
            }
            if (xml.charAt(i) == '>' && xml.charAt(i + 1) != '<') {
                valueFound = true;
                keyFound = false;
                tempKey = key;
            }
            if (valueFound) {
                key = "";
                value += xml.charAt(i);
            }
            if (!tempKey.isEmpty() && !tempValue.isEmpty()) {
                tempKey = tempKey.replaceAll("<", "");
                tempKey = tempKey.replaceAll(">", "");
                tempValue = tempValue.replaceAll(">", "");
                tempValue = tempValue.replaceAll("<", "");
                tempValue = tempValue.split("/")[0];
                map.put(tempKey, tempValue);
                tempKey = tempValue = key = value = "";

            }
        }
        return map;
    }

}
