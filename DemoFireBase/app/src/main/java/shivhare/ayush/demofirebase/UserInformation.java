package shivhare.ayush.demofirebase;

public class UserInformation {
    public String username,profession,company,salary;
    public UserInformation(){

    }

    public UserInformation(String username, String profession, String company, String salary) {
        this.username = username;
        this.profession = profession;
        this.company = company;
        this.salary = salary;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }
}
