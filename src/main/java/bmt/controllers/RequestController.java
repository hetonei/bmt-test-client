package bmt.controllers;

import bmt.game.spells.SpellName;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RequestController {

    public static String CreateLobby(String playerObj) throws IOException, InterruptedException {
        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost("http://176.37.39.11:32280/game/create");
        post.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
        //StringEntity input = new StringEntity("{\"Username\":\"Trickster\",\"Rank\":\"1\"}");
        StringEntity input = new StringEntity(playerObj);
        post.setEntity(input);
        HttpResponse response = client.execute(post);
        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        return rd.readLine();
    }

    public static String SearchLobby(String playerObj) throws IOException, InterruptedException {
        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost("http://176.37.39.11:32280/game/search");
        post.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
        StringEntity input = new StringEntity(playerObj);
        post.setEntity(input);
        HttpResponse response = client.execute(post);
        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        return rd.readLine();
    }

    public static String Cast(String lobbyID, String playerName, SpellName spellName, boolean EnemyCaster) throws IOException{
        HttpClient client = new DefaultHttpClient();
        HttpPut put = new HttpPut("http://176.37.39.11:32280/game/cast/"+ lobbyID +"/" + playerName + "/" + spellName + "/" + EnemyCaster);
        HttpResponse response = client.execute(put);
        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        return rd.readLine();
    }

    public static String LoginRequest(String username) throws IOException{
        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost("http://176.37.39.11:32280/users/login");
        StringEntity input = new StringEntity(username);
        post.setEntity(input);
        HttpResponse response = client.execute(post);
        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        return rd.readLine();
    }

    public static String RegisterRequest(String username) throws IOException{
        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost("http://176.37.39.11:32280/users/register");
        StringEntity input = new StringEntity(username);
        post.setEntity(input);
        HttpResponse response = client.execute(post);
        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        return rd.readLine();
    }

    public static String WaitRequest(String lobbyId) throws IOException{
        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet("http://176.37.39.11:32280/game/wait/"+lobbyId);
        HttpResponse response = client.execute(request);
        BufferedReader rd = new BufferedReader (new InputStreamReader(response.getEntity().getContent()));
        return rd.readLine();
    }
}