package com.health.care.lab.appointment.service;

import com.health.care.lab.appointment.dto.TestResultDto;
import java.util.List;

public interface TestResultService {
  public void saveAndUpdateTestResult(TestResultDto testResultDto);
  public TestResultDto getTestResult(String testResultType,String testResultId);

  public List<TestResultDto> getAllTestResult();

  public void deleteTestResult(Long id);
}
