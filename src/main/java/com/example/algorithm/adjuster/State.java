package com.example.algorithm.adjuster;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Component
class State {
    private int runningNow = 0;
    private List<Integer> history;
    private List<Integer> current = new ArrayList<>();
    private boolean historyUpdated = false;
    @Value("${history.size}")
    private int historySize;

    long getAdjustment(){
        if (!historyUpdated){
            return 0;
        }
        int past = history.get(0);
        int present = history.get(history.size() - 1);
        historyUpdated = false;
        return ((present-past)*(present + past) / 1000000);
    }

    void incrementRunningNow(){
        runningNow += 1;
        current.add(runningNow);
        if (current.size()>historySize){
            history = new ArrayList<>(current);
            historyUpdated = true;
            current.clear();
        }
    }

    void decrementRunningNow(){
        runningNow -= 1;
    }
}
