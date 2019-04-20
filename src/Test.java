import java.util.HashMap;
import java.util.Map;

public class Test {

    public static void main(String[] args) {
        Map<String, Integer> testMap = new HashMap<>();
        testMap.put("Martin",25);
        testMap.put("Martin", 26);
        System.out.println(testMap.get("Martin"));
    }
}
