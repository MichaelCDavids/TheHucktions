package net.hackathon;

import com.github.javafaker.Faker;
import net.hackathon.Player;
import net.hackathon.Bookings;
import net.hackathon.Management;
import net.hackathon.ManagementQueries;
import net.hackathon.ManagementServices;
import org.jdbi.v3.core.Jdbi;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;

public class FakeValueService {
    static Faker faker = new Faker();
    static String[] postions = {"GK", "DEF", "MID", "ATT"};


    public static void main(String[] args) {
        createFakePlayers(1);
        createFakeBookings(1);
        createFakeMatches(1);
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

    public static void createFakePlayers(int numberOfPlayers) {
        try {
            Jdbi jdbi = getJdbiDatabaseConnection("jdbc:postgresql://localhost/spark_hbs_jdbi?user=thando&password=thando123");
            for (int i = 0; i < numberOfPlayers; i++) {
                String first_name = faker.name().firstName();
                String last_name = faker.name().lastName();
                String email = first_name.toLowerCase() + last_name.toLowerCase() + "@gmail.com";
                int age = faker.random().nextInt(17, 35);
                double weight = (double) (faker.random().nextInt(50, 85)/3)*2.98;
                double height = (double) faker.random().nextInt(1, 2)*1.25;
                String position = postions[faker.random().nextInt(0, 3)];
                Player newPlayer = new Player(first_name, last_name, email, age, position, weight, height);
                ManagementServices managementServices = new Management(new ManagementQueries(jdbi));
                managementServices.insertPlayerRecord(newPlayer);
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

    public static void createFakeBookings(int numberOfBookings){
        try {
            Jdbi jdbi = getJdbiDatabaseConnection("jdbc:postgresql://localhost/spark_hbs_jdbi?user=thando&password=thando123");
            for (int i = 0; i< numberOfBookings; i++){
                int player_id = faker.random().nextInt(1, 20);
                int card_id = faker.random().nextInt(1,2);
                Bookings newBooking = new Bookings(player_id, card_id);
                System.out.println(String.format("%s\n%s",
                        player_id,
                        card_id
                ));
                ManagementServices managementServices = new Management(new ManagementQueries(jdbi));
                managementServices.insertBookingRecord(newBooking);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public static void createFakeMatches(int numberOfMatches){
        try{
            Jdbi jdbi = getJdbiDatabaseConnection("jdbc:postgresql://localhost/hucktion_db?user=mike&password=mike123");
            for (int i = 0; i< numberOfMatches; i++){
                String team2 = faker.name().name();
                String venue = faker.name().name();
                //Date match_date = faker.date().
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
