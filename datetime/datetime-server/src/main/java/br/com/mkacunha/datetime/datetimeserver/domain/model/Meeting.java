package br.com.mkacunha.datetime.datetimeserver.domain.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Objects;

import static br.com.mkacunha.datetime.datetimeserver.infrastructure.Preconditions.checkRequiredField;

public class Meeting {

    private LocalDateTime initialDate;

    private long durantion;

    private Participant host;

    private List<Participant> participants;

    public Meeting(Builder builder) {
        this.initialDate = builder.initialDate;
        this.durantion = builder.durantion;
        this.host = builder.host;
        this.participants = builder.participants;
    }

    public LocalDateTime getInitialDate() {
        return initialDate;
    }

    public long getDurantion() {
        return durantion;
    }

    public Participant getHost() {
        return host;
    }

    public List<Participant> getParticipants() {
        return participants;
    }

    public static class Builder {

        private LocalDateTime initialDate;

        private long durantion;

        private Participant host;

        private List<Participant> participants;

        public Meeting build(String initialDate, long durantion, Participant host,List<Participant> participants) {
            checkRequiredField(initialDate, "Data de início da reunião é obrigatório");
            checkRequiredField(durantion, "Duração da reunião é obrigatório");
            checkRequiredField(Objects.nonNull(host), "Anfitrião é obrigatório");
            checkRequiredField(participants, "Participates da reunião é obrigatório");
            this.initialDate = this.toLocalDateTime(initialDate);
            this.durantion = durantion;
            this.host = host;
            this.participants = participants;
            return new Meeting(this);
        }

        private LocalDateTime toLocalDateTime(String initialDate) {
            try {
                return LocalDateTime.parse(initialDate, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
            } catch (DateTimeParseException e) {
                throw new IllegalArgumentException("Data informada é inválida");
            }
        }

    }

    public static void main(String[] args) {
        LocalDateTime.parse("9898989", DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }
}
