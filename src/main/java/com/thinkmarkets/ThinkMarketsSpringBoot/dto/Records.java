package com.thinkmarkets.ThinkMarketsSpringBoot.dto;

import java.io.Serializable;
import java.util.List;

/**
 * DTO class that will handle/transfer data over the network
 */

public class Records implements Serializable {

    private List<PersonDTO> records;

    public List<PersonDTO> getRecords() {
        return records;
    }

    public void setRecords(List<PersonDTO> records) {
        this.records = records;
    }
}
