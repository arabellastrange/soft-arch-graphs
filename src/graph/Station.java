package graph;

public class Station implements Node {

    private int id;
    private String name = null;

    public Station(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Station(int id) {
        this.id = id;
    }

    // effects: returns stationID
    @Override
    public int getId() {
        return id;
    }

    // effects: returns stationName
    public String getName() {
        return name;
    }

    // modifies: this
    //  effects: stationName' = name
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Station)
            return (id == ((Station) o).id);
        return false;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "id = " + id;
    }
}