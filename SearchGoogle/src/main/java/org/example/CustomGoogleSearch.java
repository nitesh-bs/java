package org.example;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class CustomGoogleSearch {
    final static String apiKey = "AIzaSyDJCo9vqbnYrLAL4kz6HV4NnHodpA2zxXw";
    final static String customSearchEngineKey = "95b878cf616da41e2";
    final static String searchURL = "https://www.googleapis.com/customsearch/v1?";
    public static String search(String pUrl) {
        try {
            URL url = new URL(pUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuffer buffer = new StringBuffer();
            while ((line = br.readLine()) != null) {
                buffer.append(line);
            }
            return buffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    private static String buildSearchString(String searchString, int start, int numOfResults) {
        String toSearch = searchURL + "key=" + apiKey + "&cx=" + customSearchEngineKey + "&tbs=lf:1,lf_ui:9&tbm=lcl&rflfq=1&num=10"+ "&q=";
        // replace spaces in the search query with +
        String newSearchString = searchString.replace(" ", "%20");
        toSearch += newSearchString;
        // specify response format as json
//        toSearch += "&alt=json";
        // specify starting result number
//        toSearch += "&start=" + start;
//        // specify the number of results you need from the starting position
//        toSearch += "&num=" + numOfResults;
        System.out.println("Seacrh URL: " + toSearch);
        return toSearch;
    }
    public static void main(String[] args) throws Exception {
        String url = buildSearchString("allintitle%3Ahealthcare+management+software", 1, 10);
        String result = search(url);
        System.out.println(result);
    }
}