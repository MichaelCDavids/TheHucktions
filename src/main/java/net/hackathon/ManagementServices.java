package net.hackathon;

import net.hackathon.Bookings;
import net.hackathon.Player;
import net.hackathon.Match;

import java.util.List;

public interface ManagementServices {

    boolean insertPlayerRecord(Player player);
    boolean insertMatchRecord(Match match);
    boolean insertBookingRecord(Bookings bookings);
    boolean insertSelectedPlayer(int id);

    List<Player> getAllPlayers();
    Player getPlayerRecord(int id);
    List<Player> getSelectedPlayers();
    List<Bookings> getAllBooking();
    Bookings getBookingRecord(int id);
    List<Match> getAllMatch();
    Match getMatchRecord(int id);

    boolean updatePlayerRecord(int id, Player player);
    boolean updateBookingRecord(int id, Bookings bookings);
    boolean updateMatchRecord(int id, Match match);

    boolean deletePlayerRecord(int id);
    boolean deleteBookingRecord(int id);
    boolean deleteMatchRecord(int id);
    boolean deleteSelectedPlayers();
}
