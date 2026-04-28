import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class LoadBalancer {

    private static String[] nodes = {
            "http://localhost:8001/ingest",
            "http://localhost:8002/ingest",
            "http://localhost:8003/ingest"
    };

    private static int index = 0;

    public static String forward(String json) {
        try {
            String target = nodes[index];
            index = (index + 1) % nodes.length;

            URL url = new URL(target);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            OutputStream os = conn.getOutputStream();
            os.write(json.getBytes());
            os.flush();

            byte[] response = conn.getInputStream().readAllBytes();
            return new String(response);

        } catch (Exception e) {
            e.printStackTrace();
            return "ERROR";
        }
    }
}