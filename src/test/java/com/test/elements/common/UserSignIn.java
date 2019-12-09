package com.test.elements.common;

public class UserSignIn {

    public String pageHeadingLocator = "body > main > h2";
    public String emailAddressLocator = "#emailAddress";
    public String passwordAddressLocator = "#password";
    public String signInButtonLocator = "#signin-button-submit";


    public String getPageHeadingLocator() {
        return pageHeadingLocator;
    }

    public void setPageHeadingLocator(String pageHeadingLocator) {
        this.pageHeadingLocator = pageHeadingLocator;
    }

    public String getEmailAddressLocator() {
        return emailAddressLocator;
    }

    public void setEmailAddressLocator(String emailAddressLocator) {
        this.emailAddressLocator = emailAddressLocator;
    }

    public String getPasswordAddressLocator() {
        return passwordAddressLocator;
    }

    public void setPasswordAddressLocator(String passwordAddressLocator) {
        this.passwordAddressLocator = passwordAddressLocator;
    }

    public String getSignInButtonLocator() {
        return signInButtonLocator;
    }

    public void setSignInButtonLocator(String signInButtonLocator) {
        this.signInButtonLocator = signInButtonLocator;
    }

}
