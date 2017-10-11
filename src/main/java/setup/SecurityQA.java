package setup;

import java.util.Scanner;

public class SecurityQA{
    Scanner scanner = new Scanner(System.in);

    private String ans1, ans2;
    private int sq1, sq2;

    //array of security questions offered
    private String[] questions = {null, "What is your mother's maiden name?",
            "What is the name of the street that you lived on as a child?",
            "What was the make and model of your first car?",
            "What was the name of your first pet?",
            "In what city or town did your mother and father meet?",
            "What is the last name of your favorite childhood teacher?",
            "What is the first name of the person you first kissed?",
            "What elementary/ primary school did you go to?"};

    public SecurityQA(){}


    public void setSecureQA1() {
        System.out.println("\nSelect Security Question #1");
        for(int i = 1; i <= 4;i++)
            System.out.println("\t" + i + " - " + questions[i]);

        String temp = scanner.nextLine();

        if(temp.isEmpty() || temp.charAt(0) < 49 || temp.charAt(0) > 52){
            System.out.println("Invalid entry.\n");
            setSecureQA1();
        }
        else {
            sq1 = Character.getNumericValue(temp.charAt(0)); //good input, store value
            System.out.println("\nPress 1 to change Security Question #1.");
            System.out.println("Question: " + questions[sq1]);
            System.out.print("Answer: ");
            ans1 = (scanner.nextLine()).toUpperCase();
            if (ans1.equals("1")) {
                setSecureQA1();
            }
        }
    }

    public void setSecureQA2() {
        System.out.println("\nSelect Security Question #2");
        for(int i = 5; i <= 8; i++)
            System.out.println("\t" + i + " - " + questions[i]);

        String temp = scanner.nextLine();

        if(temp.isEmpty() || temp.charAt(0) < 53 || temp.charAt(0) > 56){
            System.out.println("Invalid entry.\n");
            setSecureQA2();
        }
        else {
            sq2 = Character.getNumericValue(temp.charAt(0)); //good input, store value
            System.out.println("\nPress 2 to change Security Question #2.");
            System.out.println("Question: " + questions[sq2]);
            System.out.print("Answer: ");
            ans2 = (scanner.nextLine()).toUpperCase();
            if (ans2.equals("2")) {
                setSecureQA2();
            }
        }
    }

    public String getQuestion(int index){
        return questions[index];
    }

    public int getIndex(int whichQuestion) {
        if(whichQuestion == 1)
            return sq1;
        return sq2;
    }

    public String getAnswer(int answerToWhichQuestion){
        if(answerToWhichQuestion == 1)
            return ans1;
        return ans2;
    }

    public void updateSQA(String email, int oldSQ, String oldAns, int newSQ, String newAns){

    }
}
