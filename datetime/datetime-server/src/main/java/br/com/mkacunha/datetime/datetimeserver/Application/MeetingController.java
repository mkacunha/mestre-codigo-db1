package br.com.mkacunha.datetime.datetimeserver.Application;

import br.com.mkacunha.datetime.datetimeserver.Application.dto.MeetingDTO;
import br.com.mkacunha.datetime.datetimeserver.Application.translate.MeetingTranslator;
import br.com.mkacunha.datetime.datetimeserver.domain.MeetingService;
import br.com.mkacunha.datetime.datetimeserver.domain.model.Meeting;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("meetings")
public class MeetingController {

    private final MeetingTranslator meetingTranslator;

    private final MeetingService meetingService;

    public MeetingController(MeetingTranslator meetingTranslator, MeetingService meetingService) {
        this.meetingTranslator = meetingTranslator;
        this.meetingService = meetingService;
    }

    @PostMapping("calculate")
    public ResponseEntity calculate(@RequestBody MeetingDTO meeting) {
        Meeting calculate = meetingTranslator.translate(meeting);
        return ResponseEntity.ok(meetingService.calculate(calculate));
    }
}
