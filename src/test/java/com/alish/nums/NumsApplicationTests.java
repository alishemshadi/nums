package com.alish.nums;

import lombok.extern.java.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log
public class NumsApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Test
    public void handlesNoInputJson() {
        NumsController numsController = new NumsController();
        ResponseEntity<NumsOutput> response = numsController.emptyGet();
        assert(response.getStatusCode().value() == HttpStatus.OK.value());
    }

    @Test
    public void handlesEmptyInput() {
        NumsController numsController = new NumsController();
        NumsInput input = new NumsInput();
        input.setData(new ArrayList<Integer>());
        ResponseEntity<NumsOutput> response = numsController.get(input);
        log.info(response.getBody().toString());
        assert(response.getBody().getOutput().equalsIgnoreCase("NaN"));
    }


    @Test
    public void handlesSingleNumberInput() {
        NumsController numsController = new NumsController();
        NumsInput input = new NumsInput();
        ArrayList<Integer> list = new ArrayList();
        list.add(1);
        input.setData(list);
        ResponseEntity<NumsOutput> response = numsController.get(input);
        log.info(response.getBody().toString());
        assert(response.getBody().getOutput().equalsIgnoreCase("1.00"));
    }

    @Test
    public void handlesThreeEqualNumbersInput() {
        NumsController numsController = new NumsController();
        NumsInput input = new NumsInput();
        ArrayList<Integer> list = new ArrayList();
        list.add(3);
        list.add(3);
        list.add(3);
        input.setData(list);
        ResponseEntity<NumsOutput> response = numsController.get(input);
        log.info(response.getBody().toString());
        assert(response.getBody().getOutput().equalsIgnoreCase("5.20"));
    }

    @Test
    public void handlesNegativeInput() {
        NumsController numsController = new NumsController();
        NumsInput input = new NumsInput();
        ArrayList<Integer> list = new ArrayList();
        list.add(-3);
        list.add(3);
        list.add(-3);
        input.setData(list);
        ResponseEntity<NumsOutput> response = numsController.get(input);
        log.info(response.getBody().toString());
        assert(response.getBody().getOutput().equalsIgnoreCase("5.20"));
    }

    @Test
    public void handlesMoreThanThreeNumbersInput() {
        NumsController numsController = new NumsController();
        NumsInput input = new NumsInput();
        ArrayList<Integer> list = new ArrayList();
        list.add(4);
        list.add(4);
        list.add(-4);
        list.add(-4);
        input.setData(list);
        ResponseEntity<NumsOutput> response = numsController.get(input);
        log.info(response.getBody().toString());
        assert(response.getBody().getOutput().equalsIgnoreCase("6.93"));
    }

    @Test
    public void handlesGivenExampleInput() {
        NumsController numsController = new NumsController();
        NumsInput input = new NumsInput();
        ArrayList<Integer> list = new ArrayList();
        list.add(5);
        list.add(4);
        list.add(6);
        list.add(1);
        input.setData(list);
        ResponseEntity<NumsOutput> response = numsController.get(input);
        log.info(response.getBody().toString());
        assert(response.getBody().getOutput().equalsIgnoreCase("8.77"));
    }
}
