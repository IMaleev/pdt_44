package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

import java.io.File;

@XStreamAlias("contact")
public class UserData {

    @XStreamOmitField
    private int id = Integer.MAX_VALUE;
    @Expose
    private String firstName;
    @Expose
    private String middleName;
    @Expose
    private String lastName;
    @Expose
    private String nickName;
    @Expose
    private String title;
    @Expose
    private String company;
    @Expose
    private String address;
    @Expose
    private String homePhone;
    @Expose
    private String mobilePhone;
    @Expose
    private String workPhone;
    @Expose
    private String fax;
    @Expose
    private String email1;
    @Expose
    private String email2;
    @Expose
    private String email3;
    @Expose
    private String webSite;
    @Expose
    private String address2;
    @Expose
    private String homePhone2;
    @Expose
    private String notes;
    @Expose
    private String group;
    private String allPhones;
    private String allEmails;
    private File photo;

    public File getPhoto() {
        return photo;
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

    public String getAllPhones() {
        return allPhones;
    }

    public int getId() {
        return id;
    }

    public String getAllEmails() {
        return allEmails;
    }

    public UserData withId(int id) {
        this.id = id;
        return this;
    }

    public UserData withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public UserData withMiddleName(String middleName) {
        this.middleName = middleName;
        return this;
    }

    public UserData withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public UserData withNickName(String nickName) {
        this.nickName = nickName;
        return this;
    }

    public UserData withTitle(String title) {
        this.title = title;
        return this;
    }

    public UserData withCompany(String company) {
        this.company = company;
        return this;
    }

    public UserData withAddress(String address) {
        this.address = address;
        return this;
    }

    public UserData withHomePhone(String homePhone) {
        this.homePhone = homePhone;
        return this;
    }

    public UserData withMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
        return this;
    }

    public UserData withWorkPhone(String workPhone) {
        this.workPhone = workPhone;
        return this;
    }

    public UserData withFax(String fax) {
        this.fax = fax;
        return this;
    }

    public UserData withEmail1(String email1) {
        this.email1 = email1;
        return this;
    }

    public UserData withEmail2(String email2) {
        this.email2 = email2;
        return this;
    }

    public UserData withEmail3(String email3) {
        this.email3 = email3;
        return this;
    }

    public UserData withWebSite(String webSite) {
        this.webSite = webSite;
        return this;
    }

    public UserData withAddress2(String address2) {
        this.address2 = address2;
        return this;
    }

    public UserData withHomePhone2(String homePhone2) {
        this.homePhone2 = homePhone2;
        return this;
    }

    public UserData withNotes(String notes) {
        this.notes = notes;
        return this;
    }

    public UserData withGroup(String group) {
        this.group = group;
        return this;
    }

    public UserData withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;

    }

    public UserData withAllEmails(String allEmails) {
        this.allEmails = allEmails;
        return this;
    }

    public UserData withPhoto(File photo) {
        this.photo = photo;
        return this;
    }

    @Override
    public String toString() {
        return "UserData{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }

        UserData userData = (UserData) o;

        if (id != userData.id) { return false; }
        if (firstName != null ? !firstName.equals(userData.firstName) : userData.firstName != null) { return false; }
        return lastName != null ? lastName.equals(userData.lastName) : userData.lastName == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        return result;
    }
}
