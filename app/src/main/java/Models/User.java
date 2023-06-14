package Models;

public class User {
    //User Data
    private String user_id;
    private String nim;
    private String name;
    private String email;
    private String photo;

    public User(String user_id, String nim, String name, String email, String photo) {
        this.user_id = user_id;
        this.nim = nim;
        this.name = name;
        this.email = email;
        this.photo = photo;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
