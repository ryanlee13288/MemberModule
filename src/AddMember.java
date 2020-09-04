import java.time.LocalDateTime;  // Import the LocalDateTime class
import java.time.format.DateTimeFormatter;  // Import the DateTimeFormatter class

public class AddMember extends Member{

    protected char index = 'M';
    protected int cardNo;
    protected static int regCardNo = 1;
    protected String registerMemberDay;
    protected String expireMemberDate;


    //display current Time
    LocalDateTime Date = LocalDateTime.now();
    DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("MMM dd yyyy");
    String registerDay = Date.format(formatDate);

    protected LocalDateTime expDate = Date.plusYears(3);
    String expireDate = expDate.format(formatDate);


    // convert expireMemberDate from string to Date
    protected LocalDateTime expireMemberDateLCT;

    //set default time
    public void setRegisterMemberDay(String registerMemberDay) {
        LocalDateTime Date = LocalDateTime.parse(registerMemberDay);
        DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("MMM dd yyyy");
        this.registerMemberDay = Date.format(formatDate);

        LocalDateTime expDate = Date.plusYears(3);
        this.expireMemberDate = expDate.format(formatDate);
        this.expireMemberDateLCT = expDate;
    }

    public AddMember(CustomerDetails customerDetails, Card cardDetails) {
        super(customerDetails, cardDetails);
        this.registerMemberDay = registerDay;
        this.expireMemberDate = expireDate;
        this.cardNo = regCardNo++;

        this.expireMemberDateLCT = expDate;
    }

    public void ModifyMember(int orgNo){
        regCardNo--;
        cardNo = orgNo;
    }


    @Override
    public String toString() {
        return super.toString() +String.format("Card.No : %c%05d\nRegister Day : %s\nExpire Date : %s\n\n",index,cardNo,registerMemberDay,expireMemberDate);
    }
}