import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        Library lib=new Library();
        while(true)
        {
            System.out.println("Menu");
            System.out.println("1. Admin Login");
            System.out.println("2. User login");
            System.out.println("3. Register user");
            System.out.println("0. Exit");
            int choice=sc.nextInt();
            switch(choice)
            {
                case 1:
                System.out.println("username: ");
                String uname=sc.next();
                System.out.println("password: ");
                String pass=sc.next();

                if(lib.adminLogin(uname, pass))
                {
                    System.out.println("Admin login successful");
                    while(true)
                    {
                        System.out.println("Admin menu");
                        System.out.println("1. Add book");
                        System.out.println("2. Remove book");
                        System.out.println("3. Update book");
                        System.out.println("4. View books");
                        System.out.println("5. Logout");
                        int ch=sc.nextInt();
                        if(ch==5)break;
                        switch(ch)
                        {
                            case 1:
                            System.out.println("enter id");
                            int id=sc.nextInt();
                            sc.nextLine();

                            System.out.println("enter title");
                            String title=sc.nextLine();

                            System.out.println("enter author");
                            String author=sc.nextLine();

                            System.out.println("enter quantity");
                            int qty=sc.nextInt();

                            lib.addBook(id, title, author, qty);
                            break;

                            case 2:
                            System.out.println("enter book id: ");
                            lib.removeBook(sc.nextInt());
                            break;

                            case 3:
                            System.out.println("enter id");
                            int uid=sc.nextInt();
                            sc.nextLine();

                            System.out.println("enter title");
                            String t=sc.nextLine();

                            System.out.println("enter author");
                            String a=sc.nextLine();

                            System.out.println("enter quantity");
                            int quantity=sc.nextInt();

                            lib.updateBook(uid, t, a, quantity);
                            break;

                            case 4:
                            lib.viewBooks();
                            break;
                        }
                    }

                }
                else
                {
                    System.out.println("invalid admin credentials");
                }
                break;
                case 2:
                System.out.println("enter user id: ");
                int userId=sc.nextInt();

                System.out.println("password: ");
                sc.nextLine();
                String pwd=sc.nextLine();

                User user=lib.userLogin(userId, pwd);

                if(user!=null)
                {
                    System.out.println("welcome "+user.name);
                    while(true)
                    {
                        System.out.println("user menu");
                        System.out.println("1. view books");
                        System.out.println("2. search by title");
                        System.out.println("3. search by author");
                        System.out.println("4. issue book");
                        System.out.println("5. return book");
                        System.out.println("6. logout");

                        int ch=sc.nextInt();
                        if(ch==6)break;
                        switch(ch)
                        {
                            case 1:
                            lib.viewBooks();
                            break;

                            case 2:
                            sc.nextLine();
                            System.out.println("enter title: ");
                            String title=sc.nextLine();
                            lib.searchbyTitle(title);
                            break;

                            case 3:
                            sc.nextLine();
                            System.out.println("enter author: ");
                            String author=sc.nextLine();
                            lib.searchbyAuthor(author);
                            break;

                            case 4:
                            System.out.println("enter book id: ");
                            lib.issueBook(user.id,sc.nextInt());
                            break;

                            case 5:
                            System.out.println("enter book id: ");
                            lib.returnBook(user.id, sc.nextInt());
                            break;
                        }

                    }
                }
                else{
                    System.out.println("invalid login");
                }
                break;

                case 3:
                System.out.println("Enter id: ");
                int id=sc.nextInt();
                sc.nextLine();

                System.out.println("Enter name: ");
                String name=sc.nextLine();

                System.out.println("Enter password: ");
                String password=sc.nextLine();


                lib.registerUser(id,name,password);
                break;

                case 0:
                System.out.println("Exiting...");
                System.exit(0);

            }
        }
    }
}
