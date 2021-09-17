package com.telran.phonebookapi.entity;

public enum Group {
    FAMILY("FAMILY"),
    FRIENDS("FRIENDS"),
    NONE("NONE");

    private final String groupName;

    Group(String groupName) {
        this.groupName = groupName;
    }

    public boolean equalsGroupName(String otherGroup) {
        return groupName.equals(otherGroup);
    }

    public String toString() {
        return this.groupName;
    }
}
