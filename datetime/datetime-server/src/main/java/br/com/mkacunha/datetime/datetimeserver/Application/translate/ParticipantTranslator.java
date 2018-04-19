package br.com.mkacunha.datetime.datetimeserver.Application.translate;

import br.com.mkacunha.datetime.datetimeserver.Application.dto.ParticipantDTO;
import br.com.mkacunha.datetime.datetimeserver.domain.model.Participant;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ParticipantTranslator {

    private final Participant.Builder builder = new Participant.Builder();

    public Participant translate(ParticipantDTO dto) {
        if (Objects.nonNull(dto)) {
            return builder.build(dto.getName(), dto.getZone());
        }
        return null;
    }

}
