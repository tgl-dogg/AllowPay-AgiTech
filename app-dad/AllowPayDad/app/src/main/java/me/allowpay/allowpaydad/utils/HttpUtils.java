package me.allowpay.allowpaydad.utils;

import org.springframework.http.HttpHeaders;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Auxilia nas requisições HTTP.
 */
public class HttpUtils {

    public static final String URL = "http://172.13.0.84:8080/v1/";

    private static final String POST = "POST";
    private static final String GET = "GET";
    private static final String PUT = "PUT";

    private static final String CONTENT_TYPE = "content-type";
    private static final String APPLICATION_JSON = "application/json";

    private static final String TOKEN_TYPE = "token";
    private static final String ALLOWPAY_TOKEN = "ELTOKENZAO";

    /**
     * Realiza uma requisição HTTP GET genérica.
     *
     * @param url URL destino da requisição.
     * @return Resposta recebida.
     * @throws IOException Caso não consiga completar a requisição.
     */
    public static String get(String url) throws IOException {
        HttpURLConnection connection = buildDefaultConnection(url, GET);

        return readResponse(connection);
    }

    /**
     * Realiza uma requisição HTTP POST genérica.
     *
     * @param url  URL destino da requisição.
     * @param data Conteúdo da requisição.
     * @return Resposta recebida.
     * @throws IOException Caso não consiga completar a requisição.
     */
    public static String post(String url, String data) throws IOException {
        HttpURLConnection connection = buildDefaultConnection(url, POST);

        connection.setDoOutput(true); // Informa que enviaremos dados na requisição.
        connection.setFixedLengthStreamingMode(data.getBytes().length); // Informa tamanho do dado.
        connection.getOutputStream().write(data.getBytes()); // Escreve o conteúdo no POST.

        return readResponse(connection);
    }

    private static HttpURLConnection buildDefaultConnection(String url, String method) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();

        connection.setRequestMethod(method);
        connection.setRequestProperty(CONTENT_TYPE, APPLICATION_JSON);
        connection.setRequestProperty(TOKEN_TYPE, ALLOWPAY_TOKEN);

        return connection;
    }

    private static String readResponse(HttpURLConnection connection) throws IOException {
        String content;
        int httpStatus = connection.getResponseCode();

        switch (httpStatus) {
            case 200: // OK
                content = readStream(connection.getInputStream());
                break;

            default:
                content = readStream(connection.getErrorStream());
        }

        return content;
    }

    private static String readStream(InputStream inputStream) throws IOException {
        BufferedReader reader = null;

        try {
            String line;
            StringBuilder sb = new StringBuilder();

            reader = new BufferedReader(new InputStreamReader(inputStream));
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }

            return sb.toString();
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
    }

    public static HttpHeaders getDefaultHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set(CONTENT_TYPE, APPLICATION_JSON);
        headers.set(TOKEN_TYPE, ALLOWPAY_TOKEN);

        return headers;
    }
}
