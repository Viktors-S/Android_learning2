package com.main.simplejsonlistwuthcustomui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class ServiceHandler {

    String response = null;
    String url = null;

    public ServiceHandler(String url){
        this.url = url;
    }

    public String makeServiceCall(){
        BufferedReader reader = null;
        URLConnection connection;

        try {
            URL urlObject = new URL(url);
            connection = urlObject.openConnection();
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuffer stringBuffer = new StringBuffer();
            String line;

            while ((line=reader.readLine())!=null){
                stringBuffer.append(line);
            }

            response=stringBuffer.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(reader!=null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return response;
    }

}
