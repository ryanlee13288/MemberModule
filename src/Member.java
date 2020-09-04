public class Member {
    protected CustomerDetails customerDetails;
//    protected Address homeAddress;
    protected Card cardType;


    public Member(CustomerDetails customerDetails, Card cardDetails) {
        this.customerDetails = customerDetails;
        this.cardType = cardDetails;
    }


    @Override
    public String toString() {
        return customerDetails.toString() + "---------------------------------\nCustomer Card Information\n---------------------------------\n" + cardType.toString();
    }

}
