package com.usst.analyser;

import com.usst.analyser.util.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AnalyserController {
    @CrossOrigin
    @PostMapping("lexicalAnalyser")
    public List<Tuple> getLexicalResult(@RequestBody JsonFormat jsonFormat) {
        return new LexicalAnalysis().analyze(jsonFormat.getText());
    }
}
