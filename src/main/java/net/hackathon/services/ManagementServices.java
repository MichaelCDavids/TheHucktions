package net.hackathon.services;

import net.hackathon.Bookings;
import net.hackathon.Player;
import net.hackathon.Match;

import java.util.List;

public interface ManagementServices {

    boolean insertPlayerRecord(Player player);
    Player getPlayerRecord(int id);
    boolean updatePlayerRecord(int id, Player player);
    boolean deletePlayerRecord(int id);
    List<Player> getAllPlayers();


    boolean insertBookingRecord(Bookings bookings);
    Bookings getBookingRecord(int id);
    boolean updateBookingRecord(int id, Bookings bookings);
    boolean deleteBookingRecord(int id);
    List<Bookings> getAllBooking();


    boolean insertMatchRecord(Match match);
    Match getMatchRecord(int id);
    boolean updateMatchRecord(int id, Match match);
    boolean deleteMatchRecord(int id);
    List<Match> getAllMatch();
}
