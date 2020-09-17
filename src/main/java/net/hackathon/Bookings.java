package net.hackathon;

public class Bookings {
    private int player_id;
    private int card_id;

    private String firstName;
    private String lastName;
    private String fieldPosition;



    public Bookings(int player_id, int card_id){
        this.player_id = player_id;
        this.card_id = card_id;

    }

    public Bookings() {

    }

    public int getPlayer_id(){
        return  player_id;
    }
    public void setPlayer_id(int player_id){
        this.player_id = player_id;
    }

    public int getCard_id(){
        return card_id;
    }
    public void setCard_id(int card_id){
        this.card_id = card_id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public String getFieldPosition() {
        return fieldPosition;
    }

    public void setFieldPosition(String fieldPosition) {
        this.fieldPosition = fieldPosition;
    }
}
