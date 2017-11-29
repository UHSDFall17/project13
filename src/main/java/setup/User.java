package setup;

public class User
{
    private String username, password, name, corporate, answer1, answer2;
    private int question1, question2;

    public User(String un, String pw, String name, String corporate, String q1, String ans1, String q2, String ans2)
    {
        username = un; password = pw; this.name = name; this.corporate = corporate; answer1 = ans1; answer2 = ans2;
        question1 = Integer.parseInt(q1); question2 = Integer.parseInt(q2);
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getCorporate() { return corporate; }

    public int getQuestion1() { return question1; }

    public int getQuestion2() {
        return question2;
    }

    public String getAnswer1() {
        return answer1;
    }

    public String getAnswer2() {
        return answer2;
    }
}
