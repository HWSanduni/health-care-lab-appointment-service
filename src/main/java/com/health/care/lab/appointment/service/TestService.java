package com.health.care.lab.appointment.service;

import com.health.care.lab.appointment.dto.TestDto;
import java.util.List;

public interface TestService {
  public void saveAndUpdateTest(TestDto testDto);
  public TestDto getTest(String testType,String testId);

  public List<TestDto> getAllTest();

  public void deleteTest(Long id);
}
