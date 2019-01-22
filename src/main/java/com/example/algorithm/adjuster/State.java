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
public class State {
    private int runningNow = 0;
    private List<Integer> history;
    private List<Integer> current = new ArrayList<>();
    private boolean historyUpdated = false;
    @Value("${history.size}")
    private int historySize;

    public long getAdjustment(){
        if (!historyUpdated){
            return 0;
        }
        int past = history.get(0);
        int present = history.get(history.size() - 1);
        historyUpdated = false;
        //return ((present-past)*(present + past) / 1000000);
        //return calculateAdjustmentSlow(present, past)
        return calculateAdjustment(present, past);
        //return calculateAdjustmentFaster(present, past);
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

    private long calculateAdjustmentSlow(int present, int past){
        System.out.println("Present: " + present);
        System.out.println("Past: " + past);
        double sum = present+past;
        double difference = present-past;
        if (difference == 0)
            return 0;
        double linear = (difference*sum/10000000)/3;
        System.out.println("Linear: " + linear);
        double power = sum/10000;
        System.out.println("Power: " + power);
        System.out.println("e^power: " + Math.exp(power));
        double exponential = (Math.abs(difference)/difference)*Math.exp(power);
        System.out.println("Exponential: " + exponential);
        double result = (linear + exponential)/2;
        System.out.println("Result: " + result);
        return (long) result;
    }

    private long calculateAdjustment(int present, int past){
        double sum = present+past;
        double difference = present-past;
        if (difference == 0)
            return 0;
        double linear = (difference*sum/1000000);
        double power = sum/10000;
        double exponential = (Math.abs(difference)/difference)*Math.exp(power);
        double result = (linear + exponential);
        return (long) result;
    }

    private long calculateAdjustmentFast(int present, int past){
        double sum = present+past;
        double difference = present-past;
        if (difference == 0)
            return 0;
        double linear = (difference*sum/1000000);
        double power = sum/5000;
        double exponential = (Math.abs(difference)/difference)*Math.exp(power);
        double result = (linear + exponential);
        return (long) result;
    }

    private long calculateAdjustmentFaster(int present, int past){
        double sum = present+past;
        double difference = present-past;
        if (difference == 0)
            return 0;
        double linear = (difference*sum/1000000);
        double power = sum/2000;
        double exponential = (Math.abs(difference)/difference)*Math.exp(power);
        double result = (linear + exponential);
        return (long) result;
    }
}
