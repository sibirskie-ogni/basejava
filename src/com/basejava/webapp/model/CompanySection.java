package com.basejava.webapp.model;

import java.util.List;
import java.util.Objects;

public class CompanySection extends Section{
    private final List<Company> companies;

    public CompanySection(List<Company> organizations) {
        Objects.requireNonNull(organizations, "company must not be null");
        this.companies = organizations;
    }

    public List<Company> getOrganizations() {
        return companies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CompanySection that = (CompanySection) o;

        return companies.equals(that.companies);

    }

    @Override
    public int hashCode() {
        return companies.hashCode();
    }

    @Override
    public String toString() {
        return companies.toString();
    }
}
