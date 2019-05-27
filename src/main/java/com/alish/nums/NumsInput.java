package com.alish.nums;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
// this class defines input structure for numerical processing
public class NumsInput {
    private List<Integer> data;
}
