package net.hackathon;

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

    @Override
    public boolean insertBookingRecord(Bookings bookings) {
        return managementQueries.insertBookingRecord(bookings);
    }

    @Override
    public Bookings getBookingRecord(int id) {
        return managementQueries.getBookingsRecord(id);
    }

    @Override
    public boolean updateBookingRecord(int id, Bookings bookings) {
        return managementQueries.updateBookingsRecord(id, bookings);
    }


    @Override
    public boolean deleteBookingRecord(int id) {
        return managementQueries.deleteBookingsRecord(id);
    }

    @Override
    public List<Bookings> getAllBooking() {
        return managementQueries.getAllBookings();
    }

    @Override
    public List<Player> getSelectedPlayers() {
        return managementQueries.getSelectedPlayers();
    }

    @Override
    public boolean insertMatchRecord(Match match) {
        return managementQueries.insertMatchRecord(match);
    }

    @Override
    public Match getMatchRecord(int id) {
        return managementQueries.getMatchRecord(id);
    }

    @Override
    public boolean updateMatchRecord(int id, Match match) {
        return managementQueries.updateMatchRecord(id, match);
    }

    @Override
    public boolean deleteMatchRecord(int id) {
        return managementQueries.deleteMatchRecord(id);
    }

    @Override
    public List<Match> getAllMatch() {
        return managementQueries.getAllMatch();
    }
}
