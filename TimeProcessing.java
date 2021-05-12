/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.TimeZone;

/**
 * This class processes time zone conversions and logical error checks. 
 * Processes time zone conversions and logical error checks.
 * @author Mike Bliss
 */
public class TimeProcessing {
    
    /**
     * Converts EST to UTC. 
     * Converts EST to UTC.
     * @param ldt A local date time object.
     * @return aptDate Returns true or false
     */
    public static String easternToUTC(LocalDateTime ldt) {

        ZoneId estZoneId = ZoneId.of("America/New_York");
        ZonedDateTime estZDT = ZonedDateTime.of(ldt, estZoneId); // Creates ZonedDateTime object
        
        // Convert EST to UTC
        ZoneId utcZID = ZoneId.of("UTC");
        ZonedDateTime utcZDT = ZonedDateTime.ofInstant(estZDT.toInstant(), utcZID);
        
        String aptDate = utcZDT.toLocalDateTime().toString().replaceAll("T", " ");
        
        return aptDate;
    }
    
    /**
     * This method converts a system default LocalDateTime object to EST. 
     * Converts a system default LocalDateTime object to EST using ZonedDateTime objects.
     * 
     * @param ldt LocalDateTime
     * @return localConvertedToEST LocalDateTime object
     */
    public static LocalDateTime localToEST(LocalDateTime ldt) {
        
        // Convert ltd to ZonedDateTime
        ZoneId localZoneId = ZoneId.systemDefault();
        ZonedDateTime localZDT = ZonedDateTime.of(ldt, localZoneId); // Creates ZonedDateTime object
        
        // Convert local to EST
        ZoneId estZID = ZoneId.of("America/New_York");
        ZonedDateTime estZDT = ZonedDateTime.ofInstant(localZDT.toInstant(), estZID);
        
        // Convert estZDT back to LocalDateTime, but now in accordance with EST
        LocalDateTime localConvertedToEST = estZDT.toLocalDateTime();
        
        return localConvertedToEST;
    }
    
    /**
     * Compares user selected time to to EST. 
     * Compares user selected time to EST to determine if selection is outside business hours.
     * @param ldt User selected LocalDateTimem object
     * @return outsideBizHours Returns true or false.
     */
    public static boolean compareToEST(LocalDateTime ldt) {
        boolean outsideBizHours = false;
        
        ZoneId ldtZoneId = ZoneId.systemDefault();
        ZonedDateTime ldtZDT = ZonedDateTime.of(ldt, ldtZoneId);
        
        LocalTime estLDTStart = LocalTime.of(8, 0);
        LocalTime estLDTend = LocalTime.of(22, 0);
        ZoneId estZoneId = ZoneId.of("America/New_York");
        ZonedDateTime estZDT = ldtZDT.withZoneSameInstant(estZoneId);
        LocalTime estTime = estZDT.toLocalTime();
        
        
        
        if(estTime.isBefore(estLDTStart) || estTime.isAfter(estLDTend)){
            outsideBizHours = true;
        }
        
        
        return outsideBizHours;
    }
}
