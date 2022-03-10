package ContactApp;

public class Contacts {
    private String contactName;
    private String contactNumber;

    public Contacts(String contactName, String contactNumber){
        this.contactName = contactName;
        this.contactNumber = contactNumber;
    }

    public String getContactName(){
        return this.contactName;
    }

    public String getContactNumber(){
        return this.contactNumber;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
}
