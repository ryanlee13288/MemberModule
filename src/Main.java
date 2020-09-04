import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class Main {
    public static ArrayList<AddMember> addMembers = new ArrayList<>();
    public static ArrayList<Card> cardTypeList = new ArrayList<>();
    private final static double nMemberDiscount = 0.9;
    private final static double pMemberDiscount = 0.85;

    //for order validate
    public static boolean validMemberNo(int mNo) {
        for (AddMember addMember : addMembers) {
            if (mNo == addMember.cardNo) {
                if (addMember.expireMemberDateLCT.compareTo(LocalDateTime.now()) < 0) {
                    System.out.println("Sry the Member Card already Expired");
                    return false;
                }
                else {
                    return true;
                }
            }

        }
        System.out.println("Sry Invalid Member ID");
        return false;
    }
    //for order using
    public static double discount(int mNo, double totalSales) {
        if (validMemberNo(mNo)) {
            for (AddMember addMember : addMembers) {
                if(addMember.cardNo == mNo){
                    if (addMember.cardType.equals(cardTypeList.get(0))) {
                        return totalSales * nMemberDiscount;
                    } else if (addMember.cardType.equals(cardTypeList.get(1))) {
                        return totalSales * pMemberDiscount;
                    } else {
                        System.out.println("Invalid Member Type, please check customer details");
                    }
                }

            }
        }
        return totalSales;
    }

    private static void displayList() {
        for (AddMember value : addMembers) {
            System.out.print(value);
        }
    }

    public static void memberMenu() {
        System.out.println("\t\tMember details");
        System.out.println("\t\t--------------");
        System.out.println("\t1. <Display Member>");
        System.out.println("\t2. <Add Member>");
        System.out.println("\t3. <Modify Member>");
        System.out.println("\t4. <Delete Member>");
        System.out.println("\t5. <Exit>");
        System.out.print("Please select your choice(1-5): ");
    }


    public static void main(String[] args) {
        int deleteSeq = 1;
        Scanner sc = new Scanner(System.in);

        cardTypeList.add(new Card("Normal Member"));
        cardTypeList.add(new Card("Platinum Member"));

        CustomerDetails customerDetails = new CustomerDetails("Teo Yi Yang", "010616-14-2910", 'M', "012-84937920");
        CustomerDetails customerDetails2 = new CustomerDetails("Lee Jun Yan", "000516-14-2910", 'M', "012-84937920");
        Card cardType = cardTypeList.get(1);
        Card cardType2 = cardTypeList.get(0);
        AddMember dMember = new AddMember(customerDetails, cardType);
        AddMember dMember2 = new AddMember(customerDetails2, cardType2);
        dMember.setRegisterMemberDay("2018-12-30T19:34:50.63");
        dMember2.setRegisterMemberDay("2016-06-30T19:34:50.63");
        addMembers.add(dMember);
        addMembers.add(dMember2);

        int choice;

        do {
            memberMenu();
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    //display all member
                    displayList();
                    break;
                case 2:
                    CustomerDetails newCustomerDetails = new CustomerDetails();

                    System.out.print("Enter Customer Name:");
                    String name = sc.nextLine();
                    //regex name
                    boolean matchesName = Pattern.matches("[a-zA-Z\\u0020]+", name);
                    while (!matchesName) {
                        System.out.print("Invalid Name Format Please Re-Enter Customer Name:");
                        name = sc.nextLine();
                        matchesName = Pattern.matches("[a-zA-Z\\u0020]+", name);
                    }
                    newCustomerDetails.setName(name);

                    System.out.print("Enter Customer NIC:");
                    String IC = sc.nextLine();
                    //regex IC
                    boolean matchICPattern = Pattern.matches("[0-9]{2}(0[1-9]|1[012])(0[1-9]|[12][0-9]|3[01])[-][0-9]{2}[-][0-9]{4}", IC);
                    boolean matchIC = false;
                    for (AddMember member : addMembers) {
                        if (IC.equals(member.customerDetails.getICNo())) {
                            matchIC = true;
                            break;
                        }
                    }
                    while (!matchICPattern || matchIC == true) {
                        System.out.print("Invalid IC format please Re-Enter Customer NIC:");
                        IC = sc.nextLine();
                        matchICPattern = Pattern.matches("[0-9]{2}(0[1-9]|1[012])(0[1-9]|[12][0-9]|3[01])[-][0-9]{2}[-][0-9]{4}", IC);
                        matchIC = false;
                        for (AddMember addMember : addMembers) {
                            if (IC.equals(addMember.customerDetails.getICNo())) {
                                matchIC = true;
                                break;
                            }
                        }
                    }

                    newCustomerDetails.setICNo(IC);

                    boolean error = true;
                    //loop when got error
                    while (error) {
                        try {
                            System.out.print("Enter Customer gender(F/M):");
                            char gender = sc.nextLine().charAt(0);
                            error = false;
                            while (Character.toUpperCase(gender) != 'F' && Character.toUpperCase(gender) != 'M') {
                                System.out.print("Invalid Gender please Re-Enter Gender(F/M):");
                                gender = sc.nextLine().charAt(0);
                                error = false;
                            }
                            newCustomerDetails.setGender(Character.toUpperCase(gender));
                        } catch (Exception e) {
                            System.out.println("Something went wrong.");
                        }
                    }

                    System.out.print("Enter Customer Phone Number:");
                    String phoneNo = sc.nextLine();
                    //regex phone
                    while (!Pattern.matches("[0-9]+", phoneNo)) {
                        System.out.print("Enter Customer Phone Number:");
                        phoneNo = sc.nextLine();
                    }
                    newCustomerDetails.setPhoneNo(phoneNo);

                    //loop member list
                    System.out.println("---------------------------------------------------");
                    for (int x = 0; x < cardTypeList.size(); x++) {
                        System.out.println(x + 1 + ". " + cardTypeList.get(x));
                    }
                    System.out.println("---------------------------------------------------");
                    System.out.println("Please select your Member Type(1 - " + cardTypeList.size() + ")");
                    int cardTypeNo = sc.nextInt();
                    sc.nextLine();
                    while (cardTypeNo != 1 && cardTypeNo != 2) {
                        System.out.println("Please select Valid Member Type(1 - " + cardTypeList.size() + ")");
                        cardTypeNo = sc.nextInt();
                    }
                    cardType = cardTypeList.get(cardTypeNo - 1);

                    //store store details to AddMember Class
                    AddMember newMember = new AddMember(newCustomerDetails, cardType);

                    System.out.println("This is customer Member Card Information: \n" + newMember.toString());

                    System.out.println("Are your want to Add Member(Y/N):");
                    char confirm = sc.nextLine().charAt(0);
                    if (Character.toUpperCase(confirm) == 'Y') {
                        //Add member details to array list
                        addMembers.add(newMember);
                    } else {
                        AddMember.regCardNo--;
                        System.out.println("Member no update, return to main page.....");
                    }

                    break;
                case 3:
                    CustomerDetails modifyCustomerDetails = new CustomerDetails();

                    try {
                        System.out.print("Please Enter the Card Number to Update The Information :M ");
                        int searchNo = sc.nextInt();
                        sc.nextLine();

                        //display the member details is card Number are same
                        for (int x = 0; x < addMembers.size(); x++) {
                            if (searchNo == addMembers.get(x).cardNo) {
                                System.out.println("This is customer Member Card Information:\n" + addMembers.get(x));

                                System.out.print("Are your sure you want to upgrade(Y/N)? : ");
                                char decide = sc.nextLine().charAt(0);

                                if (Character.toUpperCase(decide) == 'Y') {
                                    System.out.println("---------------------------------------------------");
                                    for (int y = 0; y < cardTypeList.size(); y++) {
                                        System.out.println(y + 1 + ". " + cardTypeList.get(y));
                                    }
                                    System.out.println("---------------------------------------------------");
                                    System.out.println("Please select your Member Type(1 - " + cardTypeList.size() + ")");
                                    cardTypeNo = sc.nextInt();
                                    sc.nextLine();
                                    //get card type information
                                    cardType = cardTypeList.get(cardTypeNo - 1);
                                    //if not same card type and it will update to new type, else will not update
                                    if (cardType != addMembers.get(x).cardType) {
                                        //get org customer details
                                        modifyCustomerDetails = addMembers.get(x).customerDetails;
                                        // take the org Number
                                        int orgNo = addMembers.get(x).cardNo;
                                        newMember = new AddMember(modifyCustomerDetails, cardType);
                                        newMember.ModifyMember(orgNo);

                                        addMembers.set(x, newMember);
                                        System.out.println("Member already update their record");
                                    } else {
                                        System.out.println("Same card Type, member didn't update their record");
                                    }

                                } else if (Character.toUpperCase(decide) == 'N') {
                                    System.out.println("Member didn't update their record");
                                } else {
                                    System.out.println("Invalid Key return to the front page");
                                }

                            }
                        }
                    } catch (Exception e) {
                        System.out.println("Something wrong, return to main page.");
                    }

                    break;
                case 4:
                    try {
                        System.out.print("Please Enter Card Number:M ");
                        int searchDeleteNo = sc.nextInt();
                        sc.nextLine();
                        for (int x = 0; x < addMembers.size(); x++) {
                            if (searchDeleteNo == addMembers.get(x).cardNo) {
                                System.out.println("This is customer Member Card Information:\n" + addMembers.get(x));
                                System.out.print("Are your sure you want to Delete(Y/N)? : ");
                                char decide = sc.nextLine().charAt(0);
                                if (Character.toUpperCase(decide) == 'Y') {
                                    //Because when remove array all array will move in front for example like array[5] = [1,2,3,4,5] when remove 2 times become [1,4,5]
                                    //and when choose 5, deleteSeq become 3, and choose the array[3].
                                    if (searchDeleteNo >= deleteSeq) {
                                        addMembers.remove(searchDeleteNo - deleteSeq++);
                                    } else {
                                        addMembers.remove(searchDeleteNo - 1);
                                    }
                                    System.out.println("Member have been delete");
                                } else if (Character.toUpperCase(decide) == 'N') {
                                    System.out.println("No Member delete");
                                } else {
                                    System.out.println("Invalid Key return to the front page");
                                }
                                break;
                            }
                        }
                    } catch (Exception e) {
                        System.out.println("Something went wrong.Go back to front page");
                    }
                    break;
                case 5:
                    System.out.println("Thank you for using, bye bye!");
                    break;
                default:
                    System.out.println("Invalid option, Please Re-Enter");
            }

        } while (choice != 5);

    }
}
