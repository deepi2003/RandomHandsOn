package org.deepti.tuts.lombok;


public class UseLombok {
    private  TryLombok tryLombok = new TryLombok();

    public String getUserName() {
        tryLombok.setFirstName("Test");
        tryLombok.setLastName("Passed");
        return tryLombok.getFirstName() + " "+ tryLombok.getLastName();
    }
}
