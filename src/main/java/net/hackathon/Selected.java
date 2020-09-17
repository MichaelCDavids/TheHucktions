package net.hackathon;

public class Selected {
    private int player_id;
    private String firstName;
    private String lastName;

    public Selected(int player_id, String firstName, String lastName){
        this.player_id = player_id;
        this.firstName = firstName;
        this.lastName = lastName;
    }
    public int getPlayer_id(){
        return player_id;
    }
    public void setPlayer_id(int player_id){
        this.player_id = player_id;
    }

    public String getFirstName(){
        return firstName;
    }
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public String getLastName(){
        return lastName;
    }
    public void setLastName(String lastName){
        this.lastName = lastName;
    }

}
