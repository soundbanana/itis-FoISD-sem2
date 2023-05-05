package com.chemaev.httpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class Client implements HttpClient {

    private String formatParams(Map<String, String> params) {  // Форматирование параметров под URL запрос
        StringBuilder res = new StringBuilder("?");
        params.forEach((key, value) -> res.append(String.format("%s=%s&", key, value)));

        return String.valueOf(res.deleteCharAt(res.length() - 1));
    }

    private HttpURLConnection setConnection(String requestType, URL url, Map<String, String> headers) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod(requestType);
        headers.forEach((key, value) -> connection.setRequestProperty(key, value));
        connection.setConnectTimeout(7000);
        connection.setReadTimeout(7000);
        System.out.println(connection.getResponseCode());
        return connection;
    }

    private String getResponse(HttpURLConnection connection) throws IOException {
        StringBuilder content = new StringBuilder();
        String input;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            while ((input = reader.readLine()) != null) {
                content.append(input);
            }
        }
        return String.valueOf(content);
    }

    @Override
    public String get(String url, Map<String, String> headers, Map<String, String> params) {
        String res;
        try {
            URL urlWithParams = new URL(url + formatParams(params));

            HttpURLConnection connection = setConnection("GET", urlWithParams, headers);

            System.out.println("Getting data from API...");

            System.out.println(connection.getResponseCode());

            res = getContent(connection);

            connection.disconnect();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return res;
    }

    private String getContent(HttpURLConnection connection) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            StringBuilder content = new StringBuilder();
            String input;
            while ((input = reader.readLine()) != null) {
                content.append(input);
            }
            return String.valueOf(content);
        }
    }

    @Override
    public String post(String url, Map<String, String> headers, Map<String, String> params) {
        String res;
        try {
            URL urlWithParams = new URL(url + formatParams(params));

            HttpURLConnection connection = setConnection("POST", urlWithParams, headers);

            connection.setDoOutput(true);

            System.out.println(connection.getResponseCode());

            res = getResponse(connection);

            connection.disconnect();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return res;
    }
}