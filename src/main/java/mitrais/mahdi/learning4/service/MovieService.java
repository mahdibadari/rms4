package mitrais.mahdi.learning4.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

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
import mitrais.mahdi.learning4.Movie;

@Service
public class MovieService {
    private static List<Movie> movies = new ArrayList<Movie>();
    public List<Movie> retrieveMovies(String user) throws IOException {
        movies = getMovieTitles("spiderman");       
        return movies;
    }

    static List<Movie> getMovieTitles(String substr) throws IOException, JSONException {
        List<Movie> result = new ArrayList<Movie>();
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
            JSONObject json1 = new JSONObject(json);     
            JSONArray arr = json1.getJSONArray("data");
            if (arr.length() == 0){
                break;
            }
            //gson function
            java.lang.reflect.Type listType = new com.google.gson.reflect.TypeToken<ArrayList<Movie>>(){}.getType();
            List<Movie> temp = new Gson().fromJson(arr.toString(), listType);
            result.addAll(temp);
            //end gson function   
            inStream.close();
            connection.disconnect();
            } catch (IOException ex) {
                ex.printStackTrace();
              }
        }         
        return result;
    }

    private static String streamToString(InputStream inputStream) {
        String text = new Scanner(inputStream, "UTF-8").useDelimiter("\\Z").next();
        return text;
      }
}