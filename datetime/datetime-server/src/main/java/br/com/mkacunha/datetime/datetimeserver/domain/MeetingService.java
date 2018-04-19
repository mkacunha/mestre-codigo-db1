package br.com.mkacunha.datetime.datetimeserver.domain;

import br.com.mkacunha.datetime.datetimeserver.domain.model.Meeting;
import br.com.mkacunha.datetime.datetimeserver.domain.model.MeetingCalculated;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import static br.com.mkacunha.datetime.datetimeserver.infrastructure.Preconditions.checkRequiredField;
import static java.util.Objects.nonNull;

@Service
public class MeetingService {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public MeetingCalculated calculate(Meeting meeting) {
        checkRequiredField(nonNull(meeting), "Dados da reunião é obrigatório informar");

        MeetingCalculated calculated = new MeetingCalculated();

        ZonedDateTime initialZonedDateTime = meeting.getInitialDate().atZone(meeting.getHost().getZone());
        calculated.add(meeting.getHost().getName(), initialZonedDateTime, initialZonedDateTime.plusHours(meeting.getDurantion()));

        meeting.getParticipants().forEach(participant -> {
            ZonedDateTime zonedDateTime = initialZonedDateTime.withZoneSameInstant(participant.getZone());
            calculated.add(participant.getName(), zonedDateTime, zonedDateTime.plusHours(meeting.getDurantion()));
        });
        return calculated;
    }


}
