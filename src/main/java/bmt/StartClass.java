package bmt;

import bmt.controllers.RequestController;
import bmt.game.heroes.Necromancer;
import bmt.game.spells.SpellName;
import bmt.models.Lobby;
import bmt.models.Player;
import bmt.models.User;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.Date;
import java.util.Objects;
import java.util.Scanner;

public class StartClass {
    public static void main(String[] args) throws IOException, InterruptedException {
       /* Player player1 = new Player(new Necromancer());
        Player player2 = new Player(new Necromancer());
        CreateLobby(new Gson().toJson(player1));
        SearchLobby(new Gson().toJson(player2));*/
        /*LoginRequest();
        RegisterRequest();*/
        //UpdateRequest();
        /*Type listType = new TypeToken<ArrayList<User>>(){}.getType();
        List<User> yourClassList = new Gson().fromJson(GetRequest(), listType);
        System.out.println(yourClassList.get(5).getUsername());*/
        //RegisterRequest();
        //System.out.println(RequestController.LoginRequest("Waazzzzuuup"));

        //add map<hero, description>
        Scanner in = new Scanner(System.in);
        System.out.println("Hello, user!\n1 - Register\n2 - Login");
        String a = in.nextLine();
        System.out.println("Enter your UserName:");
        String username = in.nextLine();
        String inputReq = "[]";
        switch (a){
            case "1":
                inputReq = RequestController.RegisterRequest(username);
                break;
            case "2":
                inputReq = RequestController.LoginRequest(username);
                break;
        }
        User user = new Gson().fromJson(inputReq, User.class);
        System.out.println("Hello, "+user.getUsername());

        Player player = new Player();


            System.out.println("Now pick your hero:");
            System.out.println("1 - Necromancer\n" +
                    "2 - Bloodmage");
            a = in.nextLine();
            a ="1";
            switch (a) {
                case "1":
                    player = new Player(new Necromancer());
                    System.out.println("Great, now you are Necromancer :)");
                    player.setUsername(user.getUsername());
                    break;
                default:
                    //player = new Player();
                    System.out.println("Sorry, it's just alpha verison :(");
                    break;
            }


        System.out.println("Now choose:\n1 - Create new lobby\n2 - Search lobby");
        a = in.nextLine();
        Lobby lobby;
        switch (a){
            case "1":
                inputReq = RequestController.CreateLobby(new Gson().toJson(player));
                lobby = new Gson().fromJson(inputReq, Lobby.class);
                System.out.println("Your lobby was created!\nWaiting for opponent...");
                while(lobby.getPlayer2()==null){
                    inputReq = RequestController.WaitRequest(lobby.getId());
                    lobby = new Gson().fromJson(inputReq, Lobby.class);
                    if(lobby.getPlayer2()!=null)System.out.println("Great! Your enemy is " + lobby.getPlayer2() +":" +lobby.getPlayer2().getHeroname());
                }
                break;
            default:
                System.out.println("Starting search, please wait...");
                inputReq = RequestController.SearchLobby(new Gson().toJson(player));
                lobby = new Gson().fromJson(inputReq, Lobby.class);
                System.out.println("Great! Your enemy is " + lobby.getPlayer1() +":" +lobby.getPlayer1().getHeroname());
                break;
        }

        System.out.println("What spell do you want to cast?" +
                "\n1 - Cadarkhas" +
                "\n2 - Nacrayo");
        a = in.nextLine();
        System.out.println("Target:\n1 - Enemy\n2 - You");

        String t = in.nextLine();
        boolean target = t.equals("1");
        switch (a){
            case "1":
                inputReq = RequestController.WaitRequest(lobby.getId());
                lobby = new Gson().fromJson(inputReq, Lobby.class);
                inputReq = RequestController.Cast(lobby.getId(), player.getUsername(), SpellName.Cadarkhas, target);
                System.out.println("Waiting for opponent...");
                while(lobby.getSpellcounter()!=2){
                    inputReq = RequestController.WaitRequest(lobby.getId());
                    lobby = new Gson().fromJson(inputReq, Lobby.class);
                    if(lobby.getSpellcounter()==2){
                        System.out.println(inputReq);
                        Date date = new Date();
                        String str = String.format("Congratulation first spells were casted at: %tc", date);
                        System.out.println(str);
                    }
                }
                break;
            default:
                inputReq = RequestController.WaitRequest(lobby.getId());
                lobby = new Gson().fromJson(inputReq, Lobby.class);
                inputReq = RequestController.Cast(lobby.getId(), player.getUsername(), SpellName.Nacrayo, target);
                System.out.println("Waiting for opponent...");
                while(lobby.getSpellcounter()!=2){
                    inputReq = RequestController.WaitRequest(lobby.getId());
                    lobby = new Gson().fromJson(inputReq, Lobby.class);
                    if(lobby.getSpellcounter()==2){
                        System.out.println(inputReq);
                        Date date = new Date();
                        String str = String.format("Congratulation first spells were casted at: %tc", date);
                        System.out.println(str);
                    }
                }
                break;
        }

        lobby = new Gson().fromJson(inputReq, Lobby.class);
        player = getPlayer(lobby, player.getUsername());
        System.out.println("Your HP:"+player.getHealth());
        Player enemy = Objects.equals(lobby.getPlayer1().getHeroname(), player.getHeroname()) ?lobby.getPlayer2():lobby.getPlayer1();
        System.out.println("Enemy HP:"+enemy.getHealth());
        System.out.println("Update is coming! \nPress XUY to exit...");
        in.nextLine();
    }

    public static Player getPlayer(Lobby l, String name){
        return Objects.equals(name, l.getPlayer1().getUsername()) ? l.getPlayer1():l.getPlayer2();
    }
    /*//create
    private static void CreateLobby(String in) throws IOException{
        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost("http://176.37.39.11:32280/game/create");
        post.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
        //StringEntity input = new StringEntity("{\"Username\":\"Trickster\",\"Rank\":\"1\"}");
        StringEntity input = new StringEntity(in);
        post.setEntity(input);
        HttpResponse response = client.execute(post);
        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        String line;
        while ((line = rd.readLine()) != null) {
            System.out.println(line);
        }
    }
    //search
    private static void SearchLobby(String in) throws IOException{
        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost("http://176.37.39.11:32280/game/search");
        post.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
        //StringEntity input = new StringEntity("{\"Username\":\"Trickster\",\"Rank\":\"1\"}");
        StringEntity input = new StringEntity(in);
        post.setEntity(input);
        HttpResponse response = client.execute(post);
        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        String line;
        while ((line = rd.readLine()) != null) {
            System.out.println(line);
        }
    }
    //put
    private static void Cast() throws IOException{
        HttpClient client = new DefaultHttpClient();
        HttpPut put = new HttpPut("http://176.37.39.11:32280/game/cast/"+ 1 +"/" + 2 + "/" + 3 + "/" + 4);
        StringEntity input = new StringEntity("skdjfhjkdsfkjdsfhjdskf");
        put.setEntity(input);
        HttpResponse response = client.execute(put);
        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        String line;
        while ((line = rd.readLine()) != null) {
            System.out.println(line);
        }
    }
    //get
    private static String GetRequest() throws IOException{
        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet("http://176.37.39.11:32280/users/getall");
        HttpResponse response = client.execute(request);
        BufferedReader rd = new BufferedReader (new InputStreamReader(response.getEntity().getContent()));
        *//*String line;
        while ((line = rd.readLine()) != null) {
            System.out.println(line);
        }*//*
        return rd.readLine();
    }
    //post
    private static void LoginRequest() throws IOException{
        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost("http://176.37.39.11:32280/users/login");
        *//*post.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
        StringEntity input = new StringEntity("{\"Username\":\"Trickster\",\"Rank\":\"1\"}");*//*
        StringEntity input = new StringEntity("Djerico");
        post.setEntity(input);
        HttpResponse response = client.execute(post);
        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        String line;
        while ((line = rd.readLine()) != null) {
            System.out.println(line);
        }
    }
    //post
    private static void RegisterRequest() throws IOException{
        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost("http://176.37.39.11:32280/users/register");
        *//*post.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
        StringEntity input = new StringEntity("{\"Username\":\"Trickster\",\"Rank\":\"1\"}");*//*
        StringEntity input = new StringEntity("Waazzzzuuup");
        post.setEntity(input);
        HttpResponse response = client.execute(post);
        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        String line;
        while ((line = rd.readLine()) != null) {
            System.out.println(line);
        }
    }
    //put
    private static void UpdateRequest() throws IOException{
        HttpClient client = new DefaultHttpClient();
        HttpPut put = new HttpPut("http://176.37.39.11:32280/users/rank/5aa7a65fd7b213065c210e1d");
        *//*post.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
        StringEntity input = new StringEntity("{\"Username\":\"Trickster\",\"Rank\":\"1\"}");*//*
        StringEntity input = new StringEntity("skdjfhjkdsfkjdsfhjdskf");
        put.setEntity(input);
        HttpResponse response = client.execute(put);
        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        String line;
        while ((line = rd.readLine()) != null) {
            System.out.println(line);
        }
    }*/
}
