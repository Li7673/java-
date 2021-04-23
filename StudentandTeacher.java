package Util;

public class StudentandTeacher implements java.io.Serializable{
    private static final long serialVersionUID = 1L;

    private String username;
    private String password;
    private String is_teacher;

    StudentandTeacher(String username,String password,String is_teacher){
        this.username=username;
        this.password=password;
        this.is_teacher=is_teacher;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIs_teacher() {
        return is_teacher;
    }

    public void setIs_teacher(String is_teacher) {
        this.is_teacher = is_teacher;
    }
}
