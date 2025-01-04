package pojos;

import javax.swing.text.html.HTML;
import java.util.List;

public class PojoTag {

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

    public PojoTag(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public PojoTag() {
    }

    @Override
    public String toString() {
        return "PojoTag{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

}
