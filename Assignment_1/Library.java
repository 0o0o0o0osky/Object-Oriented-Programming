package Assignment_1;

import java.util.ArrayList;
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
    ArrayList<String> haveBooks = new ArrayList<String>() {
    };
    Date register_date;

    /* Name and ID are necessary. */
    public Student(String name, int ID) {
        setName(name);
        this.ID = ID;
        register_date = new Date();
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

    public void showBooks() {
        if (haveBooks.size() != 0) {
            String str = "Have: ";
            for (String book_name : haveBooks) {
                str += (book_name + ", ");
            }
            str = str.substring(0, str.length() - 2);
            str += ".";
            System.out.println(str);
        } else
            System.out.println("Have no book.");
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

    public void borrowbook(Book book) {
        System.out.println("I want to borrow this book named " + book.name + ".");
    }

    public void returnBook(Book book) {
        System.out.println("I want to return the book named " + book.name + ".");
    }

    public void lostBook(String book_name) {
        System.out.println("I lost the book named " + book_name + ".");
        haveBooks.remove(book_name);
    }

    public void paidFine() {
        System.out.println("I want to pay the fine.");
    }

}

class Book {
    String name = "Null";
    String ISBN = "Null";
    String author = "Noname";
    String publisher = "Noname";
    String category = "Uncategorized";
    int ID = -1;
    float value = 10; // It determines how much fine it will be paid when lost.
    State bookState = State.Unborrowed;
    private String borrower = "";
    Date recording_date; // The time when the book was recorded in the Library.

    public Book(String name, String ISBN) {
        this.name = name;
        this.ISBN = ISBN;
        recording_date = new Date();
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
        System.out.println("Publisher: " + publisher);
        System.out.println("Value: " + value + "$");
        System.out.println("State: " + bookState);
        System.out.println("Recording date: " + sdf.format(recording_date));
    }
}

class Librarian {
    private String name = "";
    private int ID = 0;
    private int age = 0;
    Date hiredate;

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

    public void lendBook(int studentID, int bookID, HashMap<Integer, Student> Students, HashMap<Integer, Book> Books) {
        Student student = Students.get(studentID);
        if (student.haveBooks.size() >= 10) {
            System.out.println("You have borrowed too many books.");
        } else {
            Book book = Books.get(bookID);
            book.setBorrower(Students.get(studentID).getName());
            book.bookState = State.Borrowed;
            student.haveBooks.add(book.name);
            System.out.println("Here you are.");
        }
    }

    public void setBookLost(int studentID, int bookID, HashMap<Integer, Student> Students,
            HashMap<Integer, Book> Books) {
        Student student = Students.get(studentID);
        Book book = Books.get(bookID);
        book.bookState = State.Lost;
        student.fine += book.value;
        System.out.println(student.getName() + ", how dare you! " + book.value + "$ has been added to your fine.");
    }

    public void collectFine(int studentID, int bookID, HashMap<Integer, Student> Students,
            HashMap<Integer, Book> Books) {
        Student student = Students.get(studentID);
        Book book = Books.get(bookID);
        book.setBorrower("");
        student.fine = 0;
        System.out.println("Don't do that again, " + student.getName() + "!");
    }

    public void receiveBook(int studentID, int bookID, HashMap<Integer, Student> Students,
            HashMap<Integer, Book> Books) {
        Student student = Students.get(studentID);
        Book book = Books.get(bookID);
        student.haveBooks.remove(book.name);
        book.bookState = State.Unborrowed;
        book.setBorrower("");
        System.out.println("Thanks for returning the book.");
    }

    public void updateBook(Book book, HashMap<Integer, Book> Books) {
        Books.put(book.ID, book);
    }

    public void removeBook(int bookID, HashMap<Integer, Book> Books) {
        Books.remove(bookID);
    }
}
