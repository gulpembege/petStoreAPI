package pojos;

import java.util.List;

public class PojoPet {

    private int id;
    private PojoCategory category;
    private String name;
    private List<String> photoUrls;
    private List<PojoTag> tags;
    private String status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PojoCategory getCategory() {
        return category;
    }

    public void setCategory(PojoCategory category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getPhotoUrls() {
        return photoUrls;
    }

    public void setPhotoUrls(List<String> photoUrls) {
        this.photoUrls = photoUrls;
    }

    public List<PojoTag> getTags() {
        return tags;
    }

    public void setTags(List<PojoTag> tags) {
        this.tags = tags;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public PojoPet(int id, PojoCategory category, String name, List<String> photoUrls, List<PojoTag> tags, String status) {
        this.id = id;
        this.category = category;
        this.name = name;
        this.photoUrls = photoUrls;
        this.tags = tags;
        this.status = status;
    }

    public PojoPet() {
    }

    @Override
    public String toString() {
        return "PojoPet{" +
                "id=" + id +
                ", category=" + category +
                ", name='" + name + '\'' +
                ", photoUrls=" + photoUrls +
                ", tags=" + tags +
                ", status='" + status + '\'' +
                '}';
    }


}
