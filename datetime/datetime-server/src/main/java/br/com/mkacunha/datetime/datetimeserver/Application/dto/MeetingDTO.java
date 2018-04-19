package br.com.mkacunha.datetime.datetimeserver.Application.dto;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.nonNull;

public class MeetingDTO {

    private String initialDate;

    private long durantion;

    private ParticipantDTO host;

    private List<ParticipantDTO> participants;

    public String getInitialDate() {
        return initialDate;
    }

    public void setInitialDate(String initialDate) {
        this.initialDate = initialDate;
    }

    public long getDurantion() {
        return durantion;
    }

    public void setDurantion(long durantion) {
        this.durantion = durantion;
    }

    public ParticipantDTO getHost() {
        return host;
    }

    public void setHost(ParticipantDTO host) {
        this.host = host;
    }

    public List<ParticipantDTO> getParticipants() {
        return nonNull(participants) ? participants : new ArrayList<>();
    }

    public void setParticipants(List<ParticipantDTO> participants) {
        this.participants = participants;
    }
}
