package projekt;

public class Currency {
    private String code;
    private double buyFor;
    private double sellFor;
    private String address;
    private String exName;


    public Currency(String code, double buyFor, double sellFor, String exName, String address) {
        this.code=code;
        this.buyFor=buyFor;
        this.sellFor=sellFor;
        this.exName = exName;
        this.address=address;
    }

    public String getAddress(){
        return this.address;
    }

    public String getCode(){
        return this.code;
    }
    public double getBuyFor(){
        return this.buyFor;
    }
    public double getSellFor(){
        return this.sellFor;
    }

    public String getExName() {
        return this.exName;
    }

    public String toString(){
        return code+", " + buyFor+", " + sellFor+"Exchange office: "+ exName+" "+address;
    }
}
