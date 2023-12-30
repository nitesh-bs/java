//package org.example;
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.jsoup.nodes.Element;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.net.URLEncoder;
//import java.nio.charset.StandardCharsets;
//
//public class GoogleCrawlerr {
//    public static void main(String[] args) throws Exception {
//        String searchTerm = "healthcare management software";
//        System.out.println("Google Search Parser Tutorial");
//        System.out.println("Searching for: " + searchTerm);
//        String query = "https://www.google.com/search?q=allintitle:" + searchTerm ;
//        System.out.println(query);
//
//        String responseBody = getSearchContent(searchTerm);
//
//        String resultStats = getResultStats(responseBody);
//        System.out.println();
//        System.out.println("Result Stats:");
//        System.out.println(resultStats);
//    }
//
//    public static String getString(InputStream is) {
//        StringBuilder sb = new StringBuilder();
//        BufferedReader br = new BufferedReader(new InputStreamReader(is));
//        String line;
//        try {
//            while ((line = br.readLine()) != null) {
//                sb.append(line);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            /** finally block to close the {@link BufferedReader} */
//            if (br != null) {
//                try {
//                    br.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        return sb.toString();
//    }
//
//    public static String getSearchContent(String googleSearchQuery) throws Exception {
//        googleSearchQuery = URLEncoder.encode(googleSearchQuery, StandardCharsets.UTF_8.toString());
//        final String agent = "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/113.0.0.0 Safari/537.36";
//        String baseUrl = "https://www.google.com/search?q=allintitle:";
//        String encodedQuery = URLEncoder.encode(googleSearchQuery, StandardCharsets.UTF_8.toString());
//        String urlString = baseUrl + encodedQuery ;
//        URL url = new URL(urlString);
//        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//        connection.setRequestMethod("GET");
//        connection.setRequestProperty("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/113.0.0.0 Safari/537.36");
//        connection.setRequestProperty("sec-ch-ua", "Google Chrome;v=113, Chromium;v=113, Not-A.Brand;v=24");
//        connection.setRequestProperty("Authority", "www.google.com");
//        connection.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7");
////        connection.setRequestProperty("Accept-Encoding", "gzip, deflate, br");
//        connection.setRequestProperty("Accept-Language", "en-US,en;q=0.9");
////        connection.setRequestProperty("Sec-Ch-Ua", "\"Google Chrome\";v=\"113\", \"Chromium\";v=\"113\", \"Not-A.Brand\";v=\"24\"");
//        connection.setRequestProperty("Sec-Ch-Ua-Arch", "\"x86\"");
//        connection.setRequestProperty("Sec-Ch-Ua-Bitness", "\"64\"");
//        connection.setRequestProperty("Sec-Ch-Ua-Full-Version-List", "\"Google Chrome\";v=\"113.0.5672.63\", \"Chromium\";v=\"113.0.5672.63\", \"Not-A.Brand\";v=\"24.0.0.0\"");
//        connection.setRequestProperty("Sec-Ch-Ua-Mobile", "?0");
//        connection.setRequestProperty("Sec-Ch-Ua-Model", "\"\"");
//        connection.setRequestProperty("Sec-Ch-Ua-Platform", "\"Linux\"");
//        connection.setRequestProperty("Sec-Ch-Ua-Platform-Version", "\"5.15.0\"");
//        connection.setRequestProperty("Sec-Ch-Ua-Wow64", "?0");
//        connection.setRequestProperty("Sec-Fetch-Dest", "document");
//        connection.setRequestProperty("Sec-Fetch-Mode", "navigate");
//        connection.setRequestProperty("Sec-Fetch-Site", "none");
//        connection.setRequestProperty("Sec-Fetch-User", "?1");
//        connection.setRequestProperty("Upgrade-Insecure-Requests", "1");
//        connection.setRequestProperty("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/113.0.0.0 Safari/537.36");
//
//        int responseCode = connection.getResponseCode();
//        if (responseCode == HttpURLConnection.HTTP_OK) {
//            InputStream inputStream = connection.getInputStream();
//            return getString(inputStream);
//        } else {
//            throw new IOException("HTTP request failed with response code: " + responseCode);
//        }
//    }
//
//    public static String getResultStats(final String html) throws Exception {
//        Document doc = Jsoup.parse(html);
//        Element resultStatsElement = doc.getElementById("result-stats");
//        if (resultStatsElement != null) {
//            return resultStatsElement.text();
//        }
//        return null;
//    }
//
//}