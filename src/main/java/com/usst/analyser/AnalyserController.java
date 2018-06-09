package com.usst.analyser;

import com.usst.analyser.util.*;
import com.usst.analyser.util.Error;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AnalyserController {
    @CrossOrigin
    @PostMapping("lexicalAnalyser")
    public List<Tuple> getLexicalResult(@RequestBody JsonFormat jsonFormat) {
        return new LexicalAnalysis().analyze(jsonFormat.getText());
    }

    @CrossOrigin
    @PostMapping("syntaxAnalyser")
    public List<Error> getSyntaxResult(@RequestBody JsonFormat jsonFormat) {
        List<Tuple> tuples = new LexicalAnalysis().analyze(jsonFormat.getText());
        if (tuples.size() == 0) {
            return new ArrayList<>();
        }
        List<Error> errors = SyntaxAnalysis.program(tuples);
        if (errors.size() == 0) {
            errors.add(new Error(0, "程序检查完毕，没有语法错误"));
        }
        return errors;
    }
}
