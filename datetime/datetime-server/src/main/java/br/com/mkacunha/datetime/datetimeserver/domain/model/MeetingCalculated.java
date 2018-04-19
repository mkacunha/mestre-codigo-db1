package br.com.mkacunha.datetime.datetimeserver.domain.model;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class MeetingCalculated {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    private final List<MeetingCalculatedParticipant> participants = new ArrayList<>();

    public void add(String name, ZonedDateTime initialDate, ZonedDateTime finalDate) {
        this.participants.add(new MeetingCalculatedParticipant(name, initialDate.format(FORMATTER), finalDate.format(FORMATTER)));
    }

    public List<MeetingCalculatedParticipant> getParticipants() {
        return participants;
    }
}
