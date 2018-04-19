package br.com.mkacunha.datetime.datetimeserver.Application.translate;

import br.com.mkacunha.datetime.datetimeserver.Application.dto.MeetingDTO;
import br.com.mkacunha.datetime.datetimeserver.domain.model.Meeting;
import br.com.mkacunha.datetime.datetimeserver.domain.model.Participant;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.toList;

@Component
public class MeetingTranslator {

    private final ParticipantTranslator participantTranslator;

    private final Meeting.Builder builder = new Meeting.Builder();

    public MeetingTranslator(ParticipantTranslator participantTranslator) {
        this.participantTranslator = participantTranslator;
    }

    public Meeting translate(MeetingDTO dto) {
        if (Objects.nonNull(dto)) {
            List<Participant> participants = dto.getParticipants().stream().map(participantTranslator::translate).collect(toList());
            Participant host = participantTranslator.translate(dto.getHost());
            return builder.build(dto.getInitialDate(), dto.getDurantion(), host, participants);
        }
        return null;
    }

}
