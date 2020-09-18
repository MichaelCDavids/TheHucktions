package net.hackathon;

import org.jdbi.v3.core.Jdbi;

import java.util.List;

public class ManagementQueries {

    Jdbi jdbi;

    public ManagementQueries(Jdbi jdbi) {
        this.jdbi = jdbi;
    }

    public boolean insertPlayerRecord(Player player) {
        jdbi.open();
        jdbi.useTransaction(handle -> handle.execute("insert into player (first_name,last_name,email,age,field_position,weight,height,yellow_cards,red_cards,goals,assists,clean_sheets) values (?,?,?,?,?,?,?,?,?,?,?,?)", player.getFirstName(), player.getLastName(), player.getEmail(), player.getAge(), player.getFieldPosition(), player.getWeight(), player.getHeight(), 0, 0, 0, 0, 0));
        return true;
    }

    public boolean insertBookingRecord(Bookings bookings) {
        jdbi.useHandle((h) -> h.execute("insert into bookings( player_id, card_id) values (?,?)",
                bookings.getPlayer_id(),
                bookings.getCard_id()
        ));
        return true;
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



    public Player getById(int id) {
        return (Player) jdbi.withHandle((h) -> h.select("select * from player where id = :id")
                .bind("id", id)
                .mapToBean(Player.class)
                .findOnly());

    }

    public Player getPlayerRecord(int id) {
        return (Player) jdbi.withHandle((h) -> h.select("select id,first_name,last_name,email,age,field_position,weight,height,red_cards,yellow_cards,goals from player where id = :id")
                .bind("id", id)
                .mapToBean(Player.class)
                .findOnly());
    }

    public Bookings getBookingsRecord(int id) {
        return new Bookings();
    }

    public Match getMatchRecord(int id) {
        return new Match();
    }

    public List<Player> getAllPlayers() {
        jdbi.open();
        return jdbi.withHandle((h) -> h.createQuery("select id, first_name, last_name, email, age, field_position, red_cards, yellow_cards, weight, height, goals, assists, clean_sheets from player")
                .mapToBean(Player.class)
                .list());
    }

    public List<Player> getSelectedPlayers() {
        jdbi.open();
        return jdbi.withHandle((h) -> h.createQuery("select player.first_name, player.last_name, player.field_position from selected join player on selected.player_id=player.id")
                .mapToBean(Player.class)
                .list());
    }

    public List<Bookings> getAllBookings() {
        return jdbi.withHandle((h) -> h.createQuery("select first_name, last_name, field_position from bookings join player on bookings.player_id=player.id")
                .mapToBean(Bookings.class)
                .list());
    }

    public List<Match> getAllMatch() {
        return jdbi.withHandle((h) -> h.createQuery("select team1, team2, venue, match_data, match_time from player")
                .mapToBean(Match.class)
                .list());
    }



    public boolean updateBookingsRecord(int id, Bookings bookings) {
        return true;
    }

    public boolean updateMatchRecord(int id, Match match) {
        return true;
    }

    public boolean updatePlayerRecord(int id, Player player) {
//        TODO: update player row
        jdbi.open();
        jdbi.useTransaction(handle -> handle.execute(""));
        return true;
    }

    public boolean deleteMatchRecord(int id) {
        return true;
    }

    public boolean deleteBookingsRecord(int id) {
        return true;
    }

    public boolean deletePlayerRecord(int id) {
        jdbi.open();
        jdbi.useTransaction(handle -> handle.execute("delete from player where id=?", id));
        return true;
    }
}
