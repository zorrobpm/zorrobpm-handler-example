package com.zorrodev.bpm.handler.examle;

import com.zorrodev.bpm.contract.job.JobDetailModel;
import com.zorrodev.bpm.contract.model.ProcessVariable;
import com.zorrodev.bpm.handler.JobHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class Handler1 implements JobHandler {

    @Override
    public String getJob() {
        return "job1";
    }

    @Override
    public List<ProcessVariable> handleJob(JobDetailModel model) {
        log.info("Completing job {}", model.getJob());
        return List.of();
    }
}
