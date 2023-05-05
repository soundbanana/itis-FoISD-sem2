package com.chemaev.httpClient;

import java.util.Map;

public interface HttpClient {

    String get(String url, Map<String, String> headers, Map<String, String> params);

    String post(String url, Map<String, String> headers, Map<String, String> params);
}