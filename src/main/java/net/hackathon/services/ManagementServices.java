package net.hackathon.services;

import net.hackathon.Player;

import java.util.List;

public interface ManagementServices {
    boolean insertPlayerRecord(Player player);
    Player getPlayerRecord(int id);
    boolean updatePlayerRecord(int id, Player player);
    boolean deletePlayerRecord(int id);
    List<Player> getAllPlayers();
}
