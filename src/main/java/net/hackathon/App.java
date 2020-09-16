package net.hackathon;

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
        return 4567;
    }

    static Jdbi getJdbiDatabaseConnection(String defualtJdbcUrl) throws URISyntaxException, SQLException {
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

        return Jdbi.create(defualtJdbcUrl);

    }

    public static void main(String[] args) {
        try {
            staticFiles.location("/public");
            port(getHerokuAssignedPort());

            Jdbi jdbi = getJdbiDatabaseConnection("jdbc:postgresql://localhost/spark_hbs_jdbi?user=thando&password=thando123");

            get("/", (req, res) -> {

                List<Person> people = jdbi.withHandle((h) -> {
                    List<Person> thePeople = h.createQuery("select firstName, lastName, email from users")
                            .mapToBean(Person.class)
                            .list();
                    return thePeople;
                });


                Map<String, Object> map = new HashMap<>();
                map.put("people", people);
                map.put("data", "[2, 19, 3, 5, 2, 23]");
                map.put("theGraphLabel", "The graph label");
                map.put("labels", "['Red', 'Blue', 'Yellow', 'Green', 'Purple', 'Orange']");

                return new ModelAndView(map, "index.handlebars");

            }, new HandlebarsTemplateEngine());


            post("/person", (req, res) -> {

                String firstName = req.queryParams("firstName");
                String lastName = req.queryParams("lastName");
                String email = req.queryParams("email");

                jdbi.useHandle(h -> {
                    h.execute("insert into users (firstName, lastName, email) values (?, ?, ?)",
                            firstName,
                            lastName,
                            email);
                });

                res.redirect("/");
                return "";
            });


            get("/players", (req, res) -> {

//                List<Person> people = jdbi.withHandle((h) -> {
//                    List<Person> thePeople = h.createQuery("select firstName, lastName, email from users")
//                            .mapToBean(Person.class)
//                            .list();
//                    return thePeople;
//                });
//
//
                Map<String, Object> map = new HashMap<>();
//                map.put("people", people);
//                map.put("data", "[2, 19, 3, 5, 2, 23]");
//                map.put("theGraphLabel", "The graph label");
//                map.put("labels", "['Red', 'Blue', 'Yellow', 'Green', 'Purple', 'Orange']");

                return new ModelAndView(map, "players.handlebars");

            }, new HandlebarsTemplateEngine());


            get("/add", (req, res) -> {

//                List<Person> people = jdbi.withHandle((h) -> {
//                    List<Person> thePeople = h.createQuery("select firstName, lastName, email from users")
//                            .mapToBean(Person.class)
//                            .list();
//                    return thePeople;
//                });
//
//
                Map<String, Object> map = new HashMap<>();
//                map.put("people", people);
//                map.put("data", "[2, 19, 3, 5, 2, 23]");
//                map.put("theGraphLabel", "The graph label");
//                map.put("labels", "['Red', 'Blue', 'Yellow', 'Green', 'Purple', 'Orange']");

                return new ModelAndView(map, "registerplayer.handlebars");

            }, new HandlebarsTemplateEngine());


            post("/add", (req, res) -> {
                System.out.println(req.queryParams("name"));
                System.out.println(req.queryParams("surname"));
                System.out.println(req.queryParams("email"));
                System.out.println(req.queryParams("age"));
                System.out.println(req.queryParams("height"));
                System.out.println(req.queryParams("weight"));
                System.out.println(req.queryParams("position"));

                Map<String, Object> map = new HashMap<>();
                return new ModelAndView(map, "registerplayer.handlebars");

            }, new HandlebarsTemplateEngine());




        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
