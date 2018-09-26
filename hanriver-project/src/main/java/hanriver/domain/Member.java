package hanriver.domain;

public class Member {
    private int no;
    private String id;
    private String email;
    private String password;
    private String tel;
    
    @Override
    public String toString() {
        return "Member [no=" + no + ", id=" + id + ", email=" + email + ", password=" + password + ", tel=" + tel + "]";
    }
    public int getNo() {
        return no;
    }
    public void setNo(int no) {
        this.no = no;
    }
    public String getTel() {
        return tel;
    }
    public void setTel(String tel) {
        this.tel = tel;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
