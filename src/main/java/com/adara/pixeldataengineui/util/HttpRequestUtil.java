package com.adara.pixeldataengineui.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * Created by yzhao on 10/20/16.
 */
public class HttpRequestUtil {
    private static final String USER_AGENT = "Mozilla/5.0";

    // HTTP GET request
    public static String sendGet(String url) throws Exception {
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");
        //add request header
        con.setRequestProperty("Accept-Charset", "UTF-8");
        con.setDoOutput(true);
        con.setReadTimeout(120000);

        int responseCode = con.getResponseCode();
        /*System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);
*/
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return response.toString();

    }

    public static String sendPost(String urlPath, String query) throws IOException {
        // Encode the query
        String encodedQuery = URLEncoder.encode(query, "UTF-8");
        // This is the data that is going to be send to itcuties.com via POST request
        // 'e' parameter contains data to echo
        String postData = "e=" + encodedQuery;

        // Connect to google.com
        URL url = new URL(urlPath);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        connection.setRequestProperty("Content-Length", String.valueOf(postData.length()));

        // Write data
        OutputStream os = connection.getOutputStream();
        os.write(postData.getBytes());

        // Read response
        StringBuilder responseSB = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        String line;
        while ((line = br.readLine()) != null)
            responseSB.append(line);

        // Close streams
        br.close();
        os.close();

        return responseSB.toString();

    }
}
