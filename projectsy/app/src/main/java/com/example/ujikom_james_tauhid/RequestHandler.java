package com.example.ujikom_james_tauhid;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class RequestHandler {
    //metode yang digunakan mengirim httpost request

    //Metode yang digunakan mengirim httpPostRequest
    public String
    sendPostRequest(String requestURL, HashMap<String, String>
            postDataParams) {
        //Membuat URL
        URL url;
        //Objek StringBuilder digunakan menyimpan pesan diambil
        //dari server
        StringBuilder sb = new StringBuilder();
        try {
            //Inisialisasi URL
            url = new URL(requestURL);

            //Koneksi HttpURLConnection
            HttpURLConnection conn = (HttpURLConnection)
                    url.openConnection();
            //Konfigurasi koneksi
            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);

            //Membuat Keluaran Stream
            OutputStream os = conn.getOutputStream();
            //Menuliskan Parameter Untuk Permintaan
            //Kita menggunakan metode getPostDataString yang
            //didefinisikan di bawah ini
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));
            writer.write(getPostDataString(postDataParams));
            writer.flush();
            writer.close();
            os.close();
            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader br = new BufferedReader(new
                        InputStreamReader(conn.getInputStream()));
                sb = new StringBuilder();
                String response;
                //Pembaca respon server
                while ((response = br.readLine()) != null) {
                    sb.append(response);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public String sendGetRequest(String requestURL) {
        StringBuilder sb = new StringBuilder();
        try {
            URL url = new URL(requestURL);
            HttpURLConnection con = (HttpURLConnection)
                    url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new
                    InputStreamReader(con.getInputStream()));

            String s;
            while ((s = bufferedReader.readLine()) != null) {
                sb.append(s + "\n");
            }
        } catch (Exception e) {
        }
        return sb.toString();
    }

    public String sendGetRequestParam(String requestURL, String
            id) {
        StringBuilder sb = new StringBuilder();
        try {
            URL url = new URL(requestURL + id);
            HttpURLConnection con = (HttpURLConnection)
                    url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new
                    InputStreamReader(con.getInputStream()));
            String s;
            while ((s = bufferedReader.readLine()) != null) {
                sb.append(s + "\n");
            }
        } catch (Exception e) {
        }
        return sb.toString();
    }

    private String getPostDataString(HashMap<String, String>
                                             params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (first)
                first = false;
            else
                result.append("&");
            result.append(URLEncoder.encode(entry.getKey(), "UTF8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(),
                    "UTF-8"));
        }
        return result.toString();
    }

}
