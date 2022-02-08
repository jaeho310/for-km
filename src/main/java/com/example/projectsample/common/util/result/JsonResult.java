package com.example.projectsample.common.util.result;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@AllArgsConstructor
@Getter
@ToString
public class JsonResult {
    private boolean success = false;
    private Object result = null;
}
