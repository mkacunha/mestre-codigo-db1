package br.com.mkacunha.datetime.datetimeserver.domain.model;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static java.time.Period.between;

public class MeetingCalculated {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    private final List<MeetingCalculatedParticipant> participants = new ArrayList<>();

    public void add(String name, ZonedDateTime initialDate, ZonedDateTime finalDate) {
        Period between = between(LocalDate.now(initialDate.getZone()), initialDate.toLocalDate());
        String period = String.format("%d day(s), %d month(s) and %d year(s)", between.getDays(), between.getMonths(), between.getYears());
        this.participants.add(new MeetingCalculatedParticipant(name, initialDate.format(FORMATTER), finalDate.format(FORMATTER), period));
    }

    public List<MeetingCalculatedParticipant> getParticipants() {
        return participants;
    }

    public static void main(String[] args) {
        Period between = between(LocalDate.now(), LocalDate.now());

    }
}
