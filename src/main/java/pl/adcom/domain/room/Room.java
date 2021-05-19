package pl.adcom.domain.room;

public class Room {

    private int number;
    private BedType[] bedType;

    Room(int number, BedType[] bed) {
        this.number = number;
        this.bedType = bed;
    }

    public String getInfo(){

        String bedInfo = "Rodzanie łóżek w pokoju:\n";

        for (BedType bed : bedType) {
            bedInfo = bedInfo + "\t" + bed + "\n";
        }
        
        return String.format("Dodano nowy pokój %d %s", this.number, bedInfo);
    }

}
