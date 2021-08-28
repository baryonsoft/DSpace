package org.dspace.app.util;

import org.apache.commons.lang.StringUtils;

public class SearchFilter {

    private String name;
    private String operator;
    private String value;

    public SearchFilter(final String name, final String operator, final String value) {
        this.name = name;
        this.operator = operator;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getOperator() {
        return operator;
    }

    public String getValue() {
        return value;
    }

    public boolean hasAuthorityOperator() {
        return StringUtils.equals(operator, "authority");
    }

    public String toString() {
        return "SearchFilter{" +
                "name='" + name + '\'' +
                ", operator='" + operator + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
