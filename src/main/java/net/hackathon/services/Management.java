package net.hackathon.services;

import net.hackathon.Player;

import java.util.List;

public class Management implements ManagementServices {

    ManagementQueries managementQueries;

    public Management(ManagementQueries managementQueries) {
        this.managementQueries = managementQueries;
    }

    @Override
    public boolean insertPlayerRecord(Player person) {
        return managementQueries.insertPlayerRecord(person);
    }

    @Override
    public Player getPlayerRecord(int id) {
        return managementQueries.getPlayerRecord(id);
    }

    @Override
    public boolean updatePlayerRecord(int id, Player player) {
        return managementQueries.updatePlayerRecord(id, player);
    }

    @Override
    public boolean deletePlayerRecord(int id) {
        return managementQueries.deletePlayerRecord(id);
    }

    @Override
    public List<Player> getAllPlayers() {
        return managementQueries.getAllPlayers();
    }
}
