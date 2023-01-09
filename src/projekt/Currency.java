package projekt;

public class Currency {
    private String code;
    private double buyFor;
    private double sellFor;

    private String exName;
    private int exid;

    public Currency(int exid,String code, double buyFor, double sellFor, String exName) {
        this.code=code;
        this.buyFor=buyFor;
        this.sellFor=sellFor;
        this.exName = exName;
        this.exid=exid;
    }

    public int getExid() {
        return this.exid;
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
        return code+", " + buyFor+", " + sellFor+"Exchange office: "+ exName+" "+exid;
    }
}
