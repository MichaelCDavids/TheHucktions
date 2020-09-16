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
        return jdbi.withHandle((h) -> h.createQuery("select first_name, last_name, email from users")
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
        return true;
    }


}
