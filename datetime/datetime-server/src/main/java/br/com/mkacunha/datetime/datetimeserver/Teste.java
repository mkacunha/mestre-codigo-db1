package br.com.mkacunha.datetime.datetimeserver;

import br.com.mkacunha.datetime.datetimeserver.domain.DateTimeZoneService;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Teste {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");


    public static void main(String[] args) {
        LocalDateTime now = LocalDateTime.now();

        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zonedDateTime = now.atZone(zoneId);


        ZoneId losAngeles = ZoneId.of("America/Los_Angeles");
        ZoneId saoPaulo = ZoneId.of("America/Sao_Paulo");

        ZonedDateTime newZ = zonedDateTime.withZoneSameInstant(losAngeles);



        DateTimeZoneService dateTimeZoneService = new DateTimeZoneService();

        List<ZoneId> zones = new ArrayList<>();
        zones.add(losAngeles);
        zones.add(saoPaulo);


        ZoneId.getAvailableZoneIds().forEach(System.out::println);



        dateTimeZoneService.calculate(LocalDateTime.now(),1, losAngeles, zones);

    }

}
