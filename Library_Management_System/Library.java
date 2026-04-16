import java.util.*;

public class Library{
    List<Book> books=new ArrayList<>();
    List<User> users=new ArrayList<>();
    List<Transaction> transactions=new ArrayList<>();

    private final String ADMIN_USERNAME="admin";
    private final String ADMIN_PASSWORD="1234";

    public boolean adminLogin(String uname, String pass)
    {
        return uname.equals(ADMIN_USERNAME)&&(pass.equals(ADMIN_PASSWORD));
    }
    public User userLogin(int id, String pass)
    {
        for(User u:users)
        {
            if(u.id==id&&u.password.equals(pass))
            {
                return u;
            }
        }
        return null;
    }
    public void registerUser(int id, String name, String pass)
    {
        for(User u:users)
        {
            if(u.id==id)
            {
                System.out.println("User ID already exists");
                return;
            }
        }
        users.add(new User(id,name,pass));
        System.out.println("User registered successfully");
    }
    public void addBook(int id, String title, String author, int quantity)
    {
        for(Book b:books)
        {
            if(b.id==id)
            {
                System.out.println("Book ID already exists");
                return;
            }
        }
        books.add(new Book(id, title, author, quantity));
        System.out.println("Book added");
    }
    public void removeBook(int id)
    {
        boolean rem=books.removeIf(b->b.id==id);
        if(rem)
        {
            System.out.println("Book removed");
        }
        else{
            System.out.println("Book not found");
        }
        
        
    }
    public void updateBook(int id,String title, String author, int quantity)
    {
        for(Book b:books)
        {
            if(b.id==id)
            {
                b.title=title;
                b.author=author;
                b.quantity=quantity;
                System.out.println("Book updated");
                return;
            }
        }
        System.out.println("Book not found");
    }
    public void viewBooks()
    {
        if(books.isEmpty())
        {
            System.out.println("No books available");
            return;
        }
        for(Book b:books)
        {
            System.out.println(b.id+" - "+b.title+" - "+b.author+" - "+" Available "+b.quantity);
        }
    }
    public void searchbyTitle(String title)
    {
        for(Book b:books)
        {
            if(b.title.toLowerCase().contains(title.toLowerCase()))
            {
                System.out.println(b.id+" - "+b.title+"  Available:  "+b.quantity);
            }
        }
    }
    public void searchbyAuthor(String author)
    {
        for(Book b:books)
        {
            if(b.author.toLowerCase().contains(author.toLowerCase()))
            {
                System.out.println(b.id+" - "+b.title+"  Available: "+b.quantity);
            }
        }
    }
    public void issueBook(int userid, int bookid)
    {

        boolean exists=false;
        for(User u:users)
        {
            if(u.id==userid)
            {
                exists=true;
                break;
            }
        }
        if(!exists)
        {
            System.out.println("User not found");
            return;
        }
        for(Book b:books)
        {
            if(b.id==bookid)
            {
                if(b.quantity>0)
                {
                    b.quantity--;
                    transactions.add(new Transaction(userid, bookid));
                    System.out.println("Book issued");
                }
                else{
                    System.out.println("No copies available");
                }
                return;
            }
        }
        System.out.println("Book not found");
    }
    public void returnBook(int userid,int bookid)
    {
        for(Transaction t: transactions)
        {
            if(t.bookid==bookid&&t.userid==userid&&t.returndate==null)
            {
                t.returndate=java.time.LocalDate.now();
                for(Book b:books)
                {
                    if(b.id==bookid)
                    {
                        b.quantity++;
                        break;
                    }
                }
                long dayslate=java.time.temporal.ChronoUnit.DAYS.between(t.duedate, t.returndate);
                if(dayslate>0)
                {
                    System.out.println("Late return Fine=  Rs "+(dayslate*10));
                }
                else{
                    System.out.println("Returned on time");
                }
                return;
            }
        }
        System.out.println("No record found");
    }

}