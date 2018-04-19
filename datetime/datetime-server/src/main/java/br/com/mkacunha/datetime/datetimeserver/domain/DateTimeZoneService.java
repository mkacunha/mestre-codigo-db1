package br.com.mkacunha.datetime.datetimeserver.domain;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
public class DateTimeZoneService {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public void calculate(LocalDateTime initialDate, long duration ,ZoneId localZone, List<ZoneId> calculateZones){
        ZonedDateTime initialZonedDate = initialDate.atZone(localZone);
        System.out.println(initialZonedDate.format(FORMATTER));
        System.out.println(initialZonedDate.plusHours(duration).format(FORMATTER));

        calculateZones.forEach(zoneId -> {
            ZonedDateTime zonedDateTime = initialZonedDate.withZoneSameInstant(zoneId);
            System.out.println(zonedDateTime.format(FORMATTER));
            System.out.println(zonedDateTime.plusHours(duration).format(FORMATTER));
        });

    }

}
