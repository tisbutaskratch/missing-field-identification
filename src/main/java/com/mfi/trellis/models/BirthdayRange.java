package com.mfi.trellis.models;

import java.util.Date;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
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
