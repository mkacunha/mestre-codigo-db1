package br.com.mkacunha.datetime.datetimeserver.domain.model;

public class MeetingCalculatedParticipant {

    private String name;

    private String initialDate;

    private String finalDate;


    public MeetingCalculatedParticipant(String name, String initialDate, String finalDate) {
        this.name = name;
        this.initialDate = initialDate;
        this.finalDate = finalDate;
    }

    public String getName() {
        return name;
    }

    public String getInitialDate() {
        return initialDate;
    }

    public String getFinalDate() {
        return finalDate;
    }
}
