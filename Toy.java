
public class Toy implements Comparable<Toy>{
    private int id;
    private String name;
    private int count;
    private int chance;

    public Toy(int id, String name, int count) {
        this.id = id;
        this.name = name;
        this.count = count;
    }

    public Toy(int id, String name, int count, int chance) {
        this.id = id;
        this.name = name;
        this.count = count;
        this.chance = chance;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public int getChance() {
        return chance;
    }

    @Override                                                                       
    public int compareTo(Toy other) {
        if (other == null) {
            return -1; 
        }
        int delta = this.chance - other.chance;
        if (delta != 0) {
            return - delta;
        }  
        return this.name.compareTo(other.name);
    }

    @Override
    public String toString(){
        return this.id + " " + this.name + " " + this.count;
    }
}