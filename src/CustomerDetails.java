public class CustomerDetails {
    private String name;
    private String ICNo;
    private char gender;
    private String phoneNo;

    public CustomerDetails() {
    }

    public CustomerDetails(String name, String ICNo, char gender, String phoneNo) {
        this.name = name;
        this.ICNo = ICNo;
        this.gender = gender;
        this.phoneNo = phoneNo;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getICNo() {
        return ICNo;
    }

    public void setICNo(String ICNo) {
        this.ICNo = ICNo;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    @Override
    public String toString() {
        return  "Customer Information\n---------------------------------\n" +
                "Name:" + name +
                "\nIC.No:" + ICNo +
                "\nGender:" + gender +
                "\nPhone.No:" + phoneNo +
                "\n";
    }
}
