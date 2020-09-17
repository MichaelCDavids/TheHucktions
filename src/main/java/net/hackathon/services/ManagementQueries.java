package net.hackathon.services;

import net.hackathon.Player;
import org.jdbi.v3.core.Jdbi;

import java.util.List;

public class ManagementQueries {

    Jdbi jdbi;

    public ManagementQueries(Jdbi jdbi) {
        this.jdbi = jdbi;
    }

    public List<Player> getAllPlayers() {
        return jdbi.withHandle((h) -> h.createQuery("select * from player")
                .mapToBean(Player.class)
                .list());
    }

    public Player getPlayerRecord(int id) {
        return new Player();
    }

    public boolean updatePlayerRecord(int id, Player player) {
        return true;
    }

    public boolean deletePlayerRecord(int id) {
        return true;
    }

    public boolean insertPlayerRecord(Player player) {
        jdbi.open();
        jdbi.useTransaction(handle -> handle.execute("insert into player (first_name,last_name,email,age,pos,weight,height) values (?,?,?,?,?,?,?)", player.getFirstName(), player.getLastName(), player.getEmail(), player.getAge(), player.getPosition(), player.getWeight(), player.getHeight()));
        System.out.println("Happy Days");
        return true;
    }


}
