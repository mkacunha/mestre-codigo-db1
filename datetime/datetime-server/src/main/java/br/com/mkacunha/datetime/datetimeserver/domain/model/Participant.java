package br.com.mkacunha.datetime.datetimeserver.domain.model;

import java.time.ZoneId;
import java.time.zone.ZoneRulesException;

import static br.com.mkacunha.datetime.datetimeserver.infrastructure.Preconditions.checkRequiredField;

public class Participant {

    private String name;

    private ZoneId zone;

    private Participant(String name, ZoneId zone) {
        this.name = name;
        this.zone = zone;
    }

    public Participant(Builder builder) {
        this.name = builder.name;
        this.zone = builder.zone;
    }

    public String getName() {
        return name;
    }

    public ZoneId getZone() {
        return zone;
    }

    public static class Builder {

        private String name;

        private ZoneId zone;

        public Participant build(String name, String zone) {
            checkRequiredField(name, "Nome é obrigatório");
            checkRequiredField(zone, "Região é obrigatório");

            this.name = name;
            this.zone = ZoneId.of(zone);


            return new Participant(this);
        }

        private ZoneId toZoneId(String zone) {
            try {
                return ZoneId.of(zone);
            } catch (ZoneRulesException e) {
                throw new IllegalArgumentException("Região informada é inválida");
            }
        }
    }
}
