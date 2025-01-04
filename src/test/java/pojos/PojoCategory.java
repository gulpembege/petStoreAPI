package pojos;

public class PojoCategory {

    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PojoCategory(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public PojoCategory() {
    }

    @Override
    public String toString() {
        return "PojoCategory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

}
