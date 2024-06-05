public class User {
    protected String Name;
    protected String Email;
    protected String Password;
    
    public User(String name, String email, String password) {
        this.Name = name;
        this.Email = email;
        this.Password = password;
    }
    
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        this.Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        this.Password = password;
    }
}

