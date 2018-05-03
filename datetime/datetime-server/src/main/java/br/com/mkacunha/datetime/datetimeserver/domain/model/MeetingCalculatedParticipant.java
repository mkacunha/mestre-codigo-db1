package br.com.mkacunha.datetime.datetimeserver.domain.model;

public class MeetingCalculatedParticipant {

    private String name;

    private String initialDate;

    private String finalDate;

    private String period;

    public MeetingCalculatedParticipant(String name, String initialDate, String finalDate, String period) {
        this.name = name;
        this.initialDate = initialDate;
        this.finalDate = finalDate;
        this.period = period;
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

    public String getPeriod() {
        return period;
    }
}
