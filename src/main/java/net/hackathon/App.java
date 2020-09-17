package net.hackathon;

import net.hackathon.services.Management;
import net.hackathon.services.ManagementQueries;
import net.hackathon.services.ManagementServices;
import org.jdbi.v3.core.Jdbi;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class App {

    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4568;
    }

    static Jdbi getJdbiDatabaseConnection(String defaultJdbcUrl) throws URISyntaxException, SQLException {
        ProcessBuilder processBuilder = new ProcessBuilder();
        String database_url = processBuilder.environment().get("DATABASE_URL");
        if (database_url != null) {

            URI uri = new URI(database_url);
            String[] hostParts = uri.getUserInfo().split(":");
            String username = hostParts[0];
            String password = hostParts[1];
            String host = uri.getHost();

            int port = uri.getPort();

            String path = uri.getPath();
            String url = String.format("jdbc:postgresql://%s:%s%s", host, port, path);

            return Jdbi.create(url, username, password);
        }

        return Jdbi.create(defaultJdbcUrl);

    }

    public static void main(String[] args) {
        try {
            staticFiles.location("/public");
            port(getHerokuAssignedPort());

            Jdbi jdbi = getJdbiDatabaseConnection("jdbc:postgresql://localhost/hucktion_db?user=codex&password=codex123");
            ManagementServices managementServices = new Management(new ManagementQueries(jdbi));

            get("/", (req, res) -> {

                Map<String, Object> map = new HashMap<>();


                return new ModelAndView(map, "index.handlebars");

            }, new HandlebarsTemplateEngine());

            get("/dashboard", (req, res) -> {
                Map<String, Object> map = new HashMap<>();
                return new ModelAndView(map, "dashboard.handlebars");
            }, new HandlebarsTemplateEngine());

            get("/staff", (req, res) -> {
                Map<String, Object> map = new HashMap<>();
                return new ModelAndView(map, "staff.handlebars");

            }, new HandlebarsTemplateEngine());

            get("/fixtures", (req, res) -> {
                Map<String, Object> map = new HashMap<>();
                return new ModelAndView(map, "fixtures.handlebars");
            }, new HandlebarsTemplateEngine());

            get("/gallery", (req, res) -> {
                Map<String, Object> map = new HashMap<>();
                return new ModelAndView(map, "gallery.handlebars");
            }, new HandlebarsTemplateEngine());

            get("/players", (req, res) -> {
                List<Player> players = managementServices.getAllPlayers();

                for (Player p : players) {
                    System.out.println(p.getEmail());
                }

                Map<String, Object> map = new HashMap<>();
                map.put("players", players);
                //System.out.println(players.);
                return new ModelAndView(map, "players.handlebars");

            }, new HandlebarsTemplateEngine());

            get("/add", (req, res) -> {
                Map<String, Object> map = new HashMap<>();

                return new ModelAndView(map, "registerplayer.handlebars");

            }, new HandlebarsTemplateEngine());

            get("/delete/:id", (req, res) -> {
                res.redirect("/players");

                return null;

            }, new HandlebarsTemplateEngine());

            get("/edit/:id", (req, res) -> {
                Player player = managementServices.getPlayerRecord(Integer.parseInt(req.params("id")));

                System.out.println(player.getPosition());
                String name = player.getFirstName();
                Map<String, Object> map = new HashMap<>();
                map.put("player", player);
                map.put("name", name);

                return new ModelAndView(map, "editPlayer.handlebars");

            }, new HandlebarsTemplateEngine());

            post("/add", (req, res) -> {
                Player player = new Player();
                String first_name = req.queryParams("name").toLowerCase();
                String last_name = req.queryParams("surname").toLowerCase();
                String newName = first_name.substring(0, 1).toUpperCase() + first_name.substring(1);
                String newSurname = last_name.substring(0, 1).toUpperCase() + last_name.substring(1);

                player.setFirstName(newName);
                player.setLastName(newSurname);
                player.setEmail(req.queryParams("email"));
                player.setAge(Integer.parseInt(req.queryParams("age")));
                player.setHeight(Double.parseDouble(req.queryParams("height")));
                player.setWeight(Double.parseDouble(req.queryParams("weight")));
                player.setPosition(req.queryParams("position"));

                managementServices.insertPlayerRecord(player);

                Map<String, Object> map = new HashMap<>();
                return new ModelAndView(map, "registerplayer.handlebars");

            }, new HandlebarsTemplateEngine());


        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
