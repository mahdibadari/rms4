package mitrais.mahdi.learning4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import java.net.HttpURLConnection;
import java.util.Scanner;

import mitrais.mahdi.learning4.service.*;
import org.json.*;
import com.google.gson.*;

@Controller
@SessionAttributes("name")
public class TodoController {
    @Autowired
    TodoService service;
    @Autowired
    MovieService movieService;
    @RequestMapping(value="/list-todos", method = RequestMethod.GET)
    public String showTodos(ModelMap model){
        String name = (String) model.get("name");
        model.put("todos", service.retrieveTodos(name));
        return "list-todos";
    }

    @RequestMapping(value="/movieTitle", method = RequestMethod.GET)
    public String showmovieTitle(ModelMap model) throws IOException {
        String name = (String) model.get("name");
        model.put("movies", movieService.retrieveMovies(name));
        return "movieTitle";
    }

    static List<String> getMovieTitles(String substr) throws IOException, JSONException {
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
        //String[] result = tempResult.toArray(new String[tempResult.size()]);
        return tempResult;
    }

    private static String streamToString(InputStream inputStream) {
        String text = new Scanner(inputStream, "UTF-8").useDelimiter("\\Z").next();
        return text;
      }
}