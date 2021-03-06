package pl.adcom.domain.room;

import pl.adcom.domain.guest.Guest;

public class Room {

    private final int id;
    private final int number;
    private final BedType[] beds;

    Room(int id, int number, BedType[] bed) {
        this.id = id;
        this.number = number;
        this.beds = bed;
    }

    public int getId() {
        return id;
    }

    public String getInfo(){

        StringBuilder bedInfo = new StringBuilder("Rodzanie łóżek w pokoju:\n");

        for (BedType bed : beds) {
            bedInfo.append("\t").append(bed).append("n");
        }
        
        return String.format("%d Numer : %d %s", this.id, this.number, bedInfo.toString());
    }

    String toCSV(){
        String[] bedsAsString = new String[this.beds.length];

        for (int i = 0; i < this.beds.length; i++) {
            bedsAsString[i] = this.beds[i].toString();
        }

        String bedTypes = String.join("#", bedsAsString);

        return String.format("%d,%d,%s%s", this.id, this.number, bedTypes, System.getProperty("line.separator"));
    }
}
