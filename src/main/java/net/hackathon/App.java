package net.hackathon;

import org.jdbi.v3.core.Jdbi;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Integer.parseInt;
import static spark.Spark.*;

public class App {

    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return parseInt(processBuilder.environment().get("PORT"));
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

            Jdbi jdbi = getJdbiDatabaseConnection("jdbc:postgresql://localhost/spark_hbs_jdbi?user=thando&password=thando123");

            ManagementServices managementServices = new Management(new ManagementQueries(jdbi));

            get("/", (req, res) -> {
                Map<String, Object> map = new HashMap<>();
                return new ModelAndView(map, "index.handlebars");
            }, new HandlebarsTemplateEngine());

            get("/dashboard", (req, res) -> {
                List<Player> players = managementServices.getAllPlayers();


                Map<String, Object> map = new HashMap<>();
                map.put("theGraphLabelOne", "Attendance");
                map.put("labelsOne", "['Training', 'Workshops', 'Team Builing', 'Lessons']");
                map.put("dataOne", "[10, 15, 19, 6,]");

                map.put("theGraphLabelTwo", "Win vs Losses");
                map.put("labelsTwo", "['Wins', 'Losses']");
                map.put("dataTwo", "[14, 19]");

                map.put("theGraphLabelThree", "Financials");
                map.put("labelsThree", "['Transfers', 'Wages', 'TravelCosts', 'Equipment']");
                map.put("dataThree", "[10, 8, 11, 23]");

                map.put("theGraphLabelFour", "Income");
                map.put("labelsFour", "['Sales', 'Sponsors', 'Subscriptions', 'Tickets', 'Prize Money']");
                map.put("dataFour", "[18, 19, 10, 40,8]");

                map.put("totalPlayers", players.size());

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
                Map<String, Object> map = new HashMap<>();
                map.put("players", players);
                return new ModelAndView(map, "players.handlebars");

            }, new HandlebarsTemplateEngine());

            get("/players", (req, res) -> {
                List<Player> players = managementServices.getAllPlayers();
                Map<String, Object> map = new HashMap<>();
                map.put("players", players);
                return new ModelAndView(map, "players.handlebars");
            }, new HandlebarsTemplateEngine());

            get("/select", (req, res) -> {
                List<Player> players = managementServices.getAllPlayers();
                Map<String, Object> map = new HashMap<>();
                map.put("players", players);
                return new ModelAndView(map, "select.handlebars");

            }, new HandlebarsTemplateEngine());



            get("/selected", (req, res) -> {
                List<Player> players = managementServices.getSelectedPlayers();
                Map<String, Object> map = new HashMap<>();
                map.put("players", players);
                return new ModelAndView(map, "selected.handlebars");
            }, new HandlebarsTemplateEngine());

            get("/add", (req, res) -> {
                Map<String, Object> map = new HashMap<>();
                return new ModelAndView(map, "register.handlebars");

            }, new HandlebarsTemplateEngine());

            get("/delete/:id", (req, res) -> {
                managementServices.deletePlayerRecord(parseInt(req.params("id")));
                res.redirect("/players");

                return null;

            }, new HandlebarsTemplateEngine());

            get("/bookings", (req, res) -> {
                List<Bookings> bookings = managementServices.getAllBooking();
                Map<String, Object> map = new HashMap<>();
                map.put("bookings", bookings);
                return new ModelAndView(map, "bookings.handlebars");
            }, new HandlebarsTemplateEngine());

            get("/edit/:id", (req, res) -> {
                int id = Integer.parseInt(req.params("id"));
                Player player = managementServices.getPlayerRecord(id);
                System.out.println(id);
                String name = player.getFirstName();
                Map<String, Object> map = new HashMap<>();
                map.put("player", player);

                return new ModelAndView(map, "edit-player.handlebars");

            }, new HandlebarsTemplateEngine());

            post("/edit/:id", (req, res) -> {
                int id = parseInt(req.params("id"));
                Map<String, Object> map = new HashMap<>();

                System.out.println(req.queryParams());
                System.out.println(req.params("id"));

                res.redirect("/players");
                return null;

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
                player.setAge(parseInt(req.queryParams("age")));
                player.setHeight(Double.parseDouble(req.queryParams("height")));
                player.setWeight(Double.parseDouble(req.queryParams("weight")));
                player.setFieldPosition(req.queryParams("position"));

                managementServices.insertPlayerRecord(player);

                Map<String, Object> map = new HashMap<>();
                return new ModelAndView(map, "register.handlebars");

            }, new HandlebarsTemplateEngine());

            post("/save", (req, res) -> {
                List<Player> selected = new ArrayList<>();
                System.out.println(req.queryParams());
                for (String item: req.queryParams()) {
                    //managementServices.insertPlayerRecord(player);
                    selected.add(managementServices.getPlayerRecord(Integer.parseInt(item)));
                }
                System.out.println(selected.size());
                Map<String, Object> map = new HashMap<>();

                res.redirect("/staff");

                return null;

            }, new HandlebarsTemplateEngine());


        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
