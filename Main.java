// Builtin libraries
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.Objects;

// jAR libraries
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class Main {

    static JSONObject readJson(String fileName) {
        try {
            Object object = new JSONParser().parse(
                    new FileReader(fileName)
            );
            JSONObject jsonObject = (JSONObject) object;
            return jsonObject;
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    static boolean writeJson(String fileName, JSONObject jsonObject) {
        try (PrintWriter out = new PrintWriter(new FileWriter(fileName))) {
            out.write(jsonObject.toString());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    static Object queryTopologies(JSONObject jsonObject) {
        return jsonObject.get("id");
    }

    static boolean deleteTopology(JSONObject jsonObject, String topologyId) {
        Object obj = jsonObject.get(topologyId);
        if (obj != null) {
            jsonObject.clear();
            return true;
        }
        return false;
    }

    static String[] queryDevices(JSONObject jsonObject) {
        JSONArray componentsList = (JSONArray) jsonObject.get("components");
        String[] arr = new String[componentsList.size()];
        int counter = 0;
        for (JSONObject value : (Iterable<JSONObject>) componentsList) {
            String val = value.get("id").toString();
            arr[counter] = val;
            counter++;
        }
        return arr;
    }

    static Object queryDevicesWithNetListNode(JSONObject jsonObject, String netListNodeId) {
        JSONArray componentsList = (JSONArray) jsonObject.get("components");
        for (JSONObject value : (Iterable<JSONObject>) componentsList) {
            String val = value.get("id").toString();
            if (Objects.equals(val, netListNodeId)) {
                return value.get("netlist");
            }
            ;
        }
        return null;
    }

    public static void main(String[] args) {
        // 1) Read a topology from a given JSON file and store it in the memory
        String fileName = "\\\\top.json"; // needs to be changed
        JSONObject jsonObject = readJson(fileName);

        // 2) Write a given topology from the memory to a JSON file.
//        writeJson(fileName, jsonObject);

        // 3) Query about which topologies are currently in the memory.
//        queryTopologies(jsonObject);

        // 4) Delete a given topology from memory
//        deleteTopology(jsonObject,"top1");

        // 5) Query about which devices are in a given topology.
//        queryDevices(jsonObject);

        // 6) Query about which devices are connected to a given netlist node in a given topology
//        queryDevicesWithNetListNode(jsonObject, "res1")

    }
}
