package com.mfi.trellis.models;

import java.util.Date;

import lombok.Data;

@Data
public class BirthdayRange {
    /**
     * Start of operator’s birthday range
     */
    private Date start;

    /**
     * End of operator’s birthday range
     */
    private Date end;
}
