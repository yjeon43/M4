package TEAM79b.m4.model;

public enum UserTypes {
    NEED_USER("Need User"), DONATING_USER("Donating User"), LOCATION_EMPLOYEE("Location Employee"),
    MANAGER("Manager"), ADMIN("Admin"), DELIVERY_DRIVER("Delivery Driver");

    private String role;

    private UserTypes(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }


}
