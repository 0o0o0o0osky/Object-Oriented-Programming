package Assignment_1;

import java.util.Date;
import java.util.HashMap;
import java.text.SimpleDateFormat;

enum State {
    Unborrowed, Borrowed, Lost
}

class Student {
    private String name = "";
    private int ID = 0;
    /*
     * You can input your gender whatever you want, even you insist that you are an
     * attack helicopter.
     */
    private String gender = "";
    private int age = 0;
    float fine = 0;
    HashMap<Integer, Book> haveBooks = new HashMap<Integer, Book>();

    /* Name and ID are necessary. */
    public Student(String name, int ID) {
        setName(name);
        this.ID = ID;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getID() {
        return this.ID;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void checkBooks() {
        if (haveBooks.size() != 0) {
            String str = "I have: ";
            for (Book book : haveBooks) {
                str += (book.name) + ", ";
            }
            str = str.substring(0, str.length() - 1);
            str += ".";
            System.out.println(str);
        } else {
            System.out.println("I have no books now!");
        }
    }

    public void introduce() {
        if (getName() != "")
            System.out.println("Hi, my name is " + getName() + ".");
        if (getID() != 0)
            System.out.println("My ID is " + getID() + ".");
        if (getGender() != "")
            System.out.println("I insist that my gender is " + getGender() + ".");
        if (getAge() != 0)
            System.out.println("I'm " + getAge() + " years old.");
    }

    public void askForBook(String bookName) {
        System.out.println("Could you tell me is there a book named " + bookName + "?");
    }

    public void borrowbook(Book book) {
        System.out.println("I want to borrow this book named " + book.name + ".");
    }

    public void returnBook(Book book) {
        System.out.println("I want to return the book named " + book.name + ".");
    }

    public void lostBook(Book book) {

        book.bookState = State.Lost;
        System.out.println("I lost the book named " + book.name + ".");
    }

    public void paidFine() {
        System.out.println("I want to pay the fine.");
    }

}

class Book {
    String name = "Null";
    String ISBN = "Null";
    String author = "Noname";
    String Publisher = "Noname";
    String category = "Uncategorized";
    int ID = -1;
    float value = 10; // It determines how much fine it will be paid when lost.
    State bookState = State.Unborrowed;
    private String borrower = "";
    Date recordingDate; // The time when the book was recorded in the Library.

    public Book(String name, String ISBN) {
        this.name = name;
        this.ISBN = ISBN;
        recordingDate = new Date();
    }

    public String getBorrower() {
        return this.borrower;
    }

    public void setBorrower(String borrower) {
        this.borrower = borrower;
    }

    public void showInfo() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("Name: " + name);
        System.out.println("ISBN: " + ISBN);
        System.out.println("Category: " + category);
        System.out.println("Author: " + author);
        System.out.println("Value: " + value + "$");
        System.out.println("State: " + bookState);
        System.out.println("Recording date: " + sdf.format(recordingDate));
    }
}

class Librarian {
    private String name = "";
    private int ID = 0;
    private int age = 0;
    private Date hiredate;

    public Librarian(String name, int ID) {
        setName(name);
        this.ID = ID;
        hiredate = new Date();
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getID() {
        return this.ID;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    private boolean searchBook(String bookName, ArrayList<Book> booksList) {
        System.out.println("OK. Please wait for a minute.");
        for (Book book : booksList) {
            if (book.name.equals(bookName))
                if (book.bookState != State.Unborrowed) {
                    System.out.println("Sorry, this book now is not available.");
                    return false;
                } else {
                    System.out.println("");
                    return true;
                }
        }
        System.out.println("Sorry, there is no such book as you said in our library.");
        return false;
    }

    public void lendBook(Student student, Book book, ArrayList<Book> booksList) {
        booksList.get(book.ID);
    }

    public void receiveBook(Student student, int index,ArrayList<Book>booksList){
        if(index<0||index>=bookshelf.size())

    }
}
