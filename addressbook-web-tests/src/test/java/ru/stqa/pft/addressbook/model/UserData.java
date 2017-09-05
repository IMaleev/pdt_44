package ru.stqa.pft.addressbook.model;

public class UserData {
    private int id;
    private final String firstName;
    private final String middleName;
    private final String lastName;
    private final String nickName;
    private final String title;
    private final String company;
    private final String address;
    private final String homePhone;
    private final String mobilePhone;
    private final String workPhone;
    private final String fax;
    private final String email1;
    private final String email2;
    private final String email3;
    private final String webSite;
    private final String address2;
    private final String homePhone2;
    private final String notes;

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }

        UserData userData = (UserData) o;

        if (firstName != null ? !firstName.equals(userData.firstName) : userData.firstName != null) { return false; }
        return lastName != null ? lastName.equals(userData.lastName) : userData.lastName == null;

    }

    @Override
    public int hashCode() {
        int result = firstName != null ? firstName.hashCode() : 0;
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        return result;
    }

    private final String group;

    public UserData(String firstName, String middleName, String lastName, String nickName, String title, String company,
                    String address, String homePhone, String mobilePhone, String workPhone, String fax, String email1,
                    String email2, String email3, String webSite, String address2, String homePhone2, String notes, String group, int id) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.nickName = nickName;
        this.title = title;
        this.company = company;
        this.address = address;
        this.homePhone = homePhone;
        this.mobilePhone = mobilePhone;
        this.workPhone = workPhone;
        this.fax = fax;
        this.email1 = email1;
        this.email2 = email2;
        this.email3 = email3;
        this.webSite = webSite;
        this.address2 = address2;
        this.homePhone2 = homePhone2;
        this.notes = notes;
        this.group = group;
        this.id = id;
    }

    public UserData(String firstName, String middleName, String lastName, String nickName, String title, String company,
                    String address, String homePhone, String mobilePhone, String workPhone, String fax, String email1,
                    String email2, String email3, String webSite, String address2, String homePhone2, String notes, String group) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.nickName = nickName;
        this.title = title;
        this.company = company;
        this.address = address;
        this.homePhone = homePhone;
        this.mobilePhone = mobilePhone;
        this.workPhone = workPhone;
        this.fax = fax;
        this.email1 = email1;
        this.email2 = email2;
        this.email3 = email3;
        this.webSite = webSite;
        this.address2 = address2;
        this.homePhone2 = homePhone2;
        this.notes = notes;
        this.group = group;
        this.id = Integer.MAX_VALUE;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getNickName() {
        return nickName;
    }

    public String getTitle() {
        return title;
    }

    public String getCompany() {
        return company;
    }

    public String getAddress() {
        return address;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public String getWorkPhone() {
        return workPhone;
    }

    public String getFax() {
        return fax;
    }

    public String getEmail1() {
        return email1;
    }

    public String getEmail2() {
        return email2;
    }

    public String getEmail3() {
        return email3;
    }

    public String getWebSite() {
        return webSite;
    }

    public String getAddress2() {
        return address2;
    }

    public String getHomePhone2() {
        return homePhone2;
    }

    public String getNotes() {
        return notes;
    }

    public String getGroup() {
        return group;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "UserData{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

}
