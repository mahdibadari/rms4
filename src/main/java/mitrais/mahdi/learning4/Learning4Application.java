package mitrais.mahdi.learning4;

import javax.sql.DataSource;
import java.util.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.net.URL;

import java.net.HttpURLConnection;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import org.json.*;
import com.google.gson.*;

@SpringBootApplication
@EnableJpaRepositories("mitrais.mahdi.learning4")
@EntityScan("mitrais.mahdi.learning4")
//public class Learning4Application implements CommandLineRunner {
public class Learning4Application{
	@Autowired
    DataSource dataSource;
 
    @Autowired
    SystemRepository systemRepository;
	public static void main(String[] args) throws IOException, JSONException {
        SpringApplication.run(Learning4Application.class, args);
	}

	/* @Override
    public void run(String... args) throws Exception {
		java.lang.System.out.println("Our DataSource is = " + dataSource);
        Iterable<mitrais.mahdi.learning4.System> systemlist = systemRepository.findAll();
        for(mitrais.mahdi.learning4.System systemmodel:systemlist){
            java.lang.System.out.println("Here is a system: " + systemmodel.toString());
        }                 
        String[] data = getMovieTitles("spiderman");
    } */

    static String[] getMovieTitles(String substr) throws IOException, JSONException {
        List<String> tempResult = new ArrayList<String>();
        String json = null;
        for (int i=1; i < 100; i++)
        {
            try {
            URL url = new URL("https://jsonmock.hackerrank.com/api/movies/search/?Title="+substr+"&page="+i);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setInstanceFollowRedirects(false);
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("charset", "utf-8");
            connection.connect();           
            InputStream inStream = connection.getInputStream();
            json = streamToString(inStream); // input stream to string 
            java.lang.System.out.println(json) ;
            JSONObject json1 = new JSONObject(json);     
            JSONArray arr = json1.getJSONArray("data");
            if (arr.length() == 0){
                break;
            }
            //gson function
            java.lang.reflect.Type listType = new com.google.gson.reflect.TypeToken<ArrayList<Movie>>(){}.getType();
            List<Movie> yourClassList = new Gson().fromJson(arr.toString(), listType);
            java.lang.System.out.println(yourClassList.get(0).getTitle());
            //end gson function
            for (int d = 0; d < arr.length(); d++)
            {
                String post_id = arr.getJSONObject(d).getString("Title");
                tempResult.add(post_id);
            }      
            inStream.close();
            connection.disconnect();
            } catch (IOException ex) {
                ex.printStackTrace();
              }
        } 
        Collections.sort(tempResult);
        for (int d = 0; d < tempResult.size(); d++)
            {                
                java.lang.System.out.println(tempResult.get(d));   
            }     
        String[] result = tempResult.toArray(new String[tempResult.size()]);
        return result;
    }

    private static String streamToString(InputStream inputStream) {
        String text = new Scanner(inputStream, "UTF-8").useDelimiter("\\Z").next();
        return text;
      }

      private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
          sb.append((char) cp);
        }
        return sb.toString();
      }
    
}
