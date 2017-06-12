class Email {
  private String email;

  Email() {
    email = null;
  }

  Email(String str) {
    email = str;
  }

  public void setEmail(String e) {
    email = e;
  }

  public String getEmail() {
    return email;
  }

  public String getName() {
     int pos = email.indexOf('@');
     String s = email.substring(0,pos);
     return s;
  }

  public String getCompany() {
     int posFirst = email.indexOf('@');
     int posLast = email.lastIndexOf('.');
     String s = email.substring(posFirst+1,posLast);
     return s;
  }

}