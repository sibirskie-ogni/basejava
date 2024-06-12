package com.basejava.webapp.model;

import java.util.List;
import java.util.Objects;

public class ListSection extends Section{
    List<String> listSection;

    public ListSection(List<String> listSection){
        Objects.requireNonNull(listSection,"Section must not be null");
        this.listSection=listSection;
    }

    public List<String> getListSection() {
        return listSection;
    }

    @Override
    public int hashCode() {
        return listSection.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        ListSection that = (ListSection) obj;

        return listSection.equals(that.listSection);
    }

    @Override
    public String toString() {
        return listSection.toString();
    }
}
