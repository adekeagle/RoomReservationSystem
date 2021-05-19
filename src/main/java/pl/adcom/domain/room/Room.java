package pl.adcom.domain.room;

public class Room {

    private final int number;
    private final BedType[] bedType;

    Room(int number, BedType[] bed) {
        this.number = number;
        this.bedType = bed;
    }

    public String getInfo(){

        StringBuilder bedInfo = new StringBuilder("Rodzanie łóżek w pokoju:\n");

        for (BedType bed : bedType) {
            bedInfo.append("\t").append(bed).append("n");
        }
        
        return String.format("Dodano nowy pokój %d %s", this.number, bedInfo);
    }

}
