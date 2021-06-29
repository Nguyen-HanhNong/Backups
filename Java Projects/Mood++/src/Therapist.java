@SuppressWarnings("serial")
//By Abby
public class Therapist extends User
{

    private String address;
    private String phoneNum;

    public Therapist(){
        super();
        address = "123 Somewhere";
        phoneNum = "none";
    }

    public Therapist(String fName, String lName, byte age, String add, String phone){
        this.address = add;
        this.phoneNum = phone;
    }

    public String toString(){
        return "Therapist:\n" + super.toString() + "\nAddress: " + address + "\nPhone Number: " + phoneNum;
    }


    public String getAddress(){
        return this.address;
    }


    public String getPhoneNum(){
        return this.phoneNum;
    }

    public void setAddress(String add){
        this.address = add;
    }

    public void setPhoneNum(String phone){
        this.phoneNum = phone;
    }
}