import java.time.LocalDate;

public class Transaction{
    int userid;
    int bookid;
    LocalDate issuedate;
    LocalDate duedate;
    LocalDate returndate;
    public Transaction(int userid, int bookid)
    {
        this.userid=userid;
        this.bookid=bookid;
        this.issuedate=LocalDate.now();
        this.duedate=issuedate.plusDays(7);
        this.returndate=null;
    }
}