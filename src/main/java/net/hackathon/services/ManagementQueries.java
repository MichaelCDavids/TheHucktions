package net.hackathon.services;

import net.hackathon.Player;
import org.jdbi.v3.core.Jdbi;

import java.util.List;

public class ManagementQueries {

    Jdbi jdbi;

    public ManagementQueries(Jdbi jdbi) {
        this.jdbi = jdbi;
    }

    /*
     * Player Queries
     * */

    public List<Player> getAllPlayers() {
        jdbi.open();
        return jdbi.withHandle((h) -> h.createQuery("select * from player")
                .mapToBean(Player.class)
                .list());
    }

    public Player getPlayerRecord(int id) {
        jdbi.open();
        return (Player) jdbi.withHandle((h) -> h.createQuery("select * from player where id=" + id)
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
        jdbi.useTransaction(handle -> handle.execute("insert into player (first_name,last_name,email,age,pos,weight,height) values (?,?,?,?,?,?,?)", player.getFirstName(), player.getLastName(), player.getEmail(), player.getAge(), player.getPosition(), player.getWeight(), player.getHeight()));
        return true;
    }


}
