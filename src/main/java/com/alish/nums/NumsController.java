package com.alish.nums;

import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.LinkedList;

@Log
@RestController
public class NumsController {
    // handle empty request
    @RequestMapping("/")
    public ResponseEntity<NumsOutput> emptyGet() {
        NumsOutput out = new NumsOutput();
        out.setOutput("");
        return new ResponseEntity<NumsOutput>(out, HttpStatus.OK);
    }

    // handle valid request (filled with JSON)
    @RequestMapping(value = "/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<NumsOutput> get(@RequestBody NumsInput nums) {
        NumsInput topNums = new NumsInput();
        topNums.setData(new LinkedList<Integer>());
        NumsOutput out = new NumsOutput();
        if (nums != null) {
            nums.getData().stream().forEach(integer -> {
                if (topNums.getData().size() == 0) { // add to empty list
                    log.info("add " + integer + " to an empty list");
                    topNums.getData().add(integer);
                } else if (topNums.getData().size() < 3) { // max list size is 3
                    log.info("add " + integer + " to list " + topNums.getData().toString());
                    topNums.getData().add(integer);
                } else if (integer > topNums.getData().get(0)) { // list already contains three elements
                    log.info("replace " + integer + " with the smallest (first) element of the list [" + topNums.getData().toString() + "]");
                    Collections.sort(topNums.getData()); // ascending sort for the list of highest numbers
                    topNums.getData().remove(0); // remove the smallest element in the list
                    topNums.getData().add(integer); // add the new integer
                }
            });
            log.info("highest numbers list = " + topNums.getData().toString());
            int sum = topNums.getData().stream().mapToInt(integer -> integer * integer).sum();
            if (topNums.getData().size() == 0 || sum == 0) // for sum = 0 the squared root is undefined
                out.setOutput("NaN");
            else
                out.setOutput(String.format("%.2f", Math.sqrt(sum)));

        }
        return new ResponseEntity<NumsOutput>(out, HttpStatus.OK);
    }
}
