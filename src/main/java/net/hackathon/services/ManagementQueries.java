package net.hackathon.services;

import net.hackathon.Bookings;
import net.hackathon.Match;
import net.hackathon.Player;
import org.jdbi.v3.core.Jdbi;

import java.util.List;

public class ManagementQueries {

    Jdbi jdbi;

    public ManagementQueries(Jdbi jdbi) {
        this.jdbi = jdbi;
    }

    public List<Player> getAllPlayers() {
        jdbi.open();
        return jdbi.withHandle((h) -> h.createQuery("select first_name, last_name, email, age, field_position, weight, height from player")
                .mapToBean(Player.class)
                .list());
    }

    public Player getPlayerRecord(int id) {
        jdbi.open();
        return (Player) jdbi.withHandle((h) -> h.createQuery("select * from player where id=?"+id)
                .mapToBean(Player.class)
                .list());
    }

    public boolean updatePlayerRecord(int id, Player player) {
//        TODO: update player row
        jdbi.open();
        jdbi.useTransaction(handle -> handle.execute(""));
        return true;
    }

    public boolean deletePlayerRecord(int id) {
        jdbi.open();
        jdbi.useTransaction(handle -> handle.execute("delete from player where id=?", id));
        return true;
    }

    public boolean insertPlayerRecord(Player player) {
        jdbi.open();
        jdbi.useTransaction(handle -> handle.execute("insert into player (first_name,last_name,email,age,field_position,weight,height) values (?,?,?,?,?,?,?)", player.getFirstName(), player.getLastName(), player.getEmail(), player.getAge(), player.getPosition(), player.getWeight(), player.getHeight()));
        return true;
    }


    public boolean insertBookingRecord(Bookings bookings) {
        jdbi.useHandle((h) -> h.execute("insert into player( player_id, card_id) values (?,?)",
                bookings.getPlayer_id(),
                bookings.getCard_id()
        ));
        return true;
    }

    public Bookings getBookingsRecord(int id) {
        return new Bookings();
    }

    public boolean deleteBookingsRecord(int id) {
        return true;
    }

    public boolean updateBookingsRecord(int id, Bookings bookings) {
        return true;
    }

    public List<Bookings> getAllBookings() {
        return jdbi.withHandle((h) -> h.createQuery("select player_id, card_id from player")
                .mapToBean(Bookings.class)
                .list());
    }

    public boolean insertMatchRecord(Match match) {
        jdbi.useHandle((h) -> h.execute("insert into matches( team1, team2, venue, match_date, match_time) values (?,?,?,?,?)",
                match.getTeam1("Hucktions Athletic F.C"),
                match.getTeam2(),
                match.getVenue(),
                match.getMatch_date(),
                match.getMatch_time()
        ));
        return true;
    }

    public Match getMatchRecord(int id) {
        return new Match();
    }

    public boolean updateMatchRecord(int id, Match match) {
        return true;
    }

    public boolean deleteMatchRecord(int id) {
        return true;
    }

    public List<Match> getAllMatch() {
        return jdbi.withHandle((h) -> h.createQuery("select team1, team2, venue, match_data, match_time from player")
                .mapToBean(Match.class)
                .list());
    }
}
