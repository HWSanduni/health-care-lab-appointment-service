package com.health.care.lab.appointment.service;

import com.health.care.lab.appointment.dto.ReportDto;
import java.util.List;

public interface ReportService {
  public void saveAndUpdateReport(ReportDto reportDto);
  public ReportDto getReport(String name,String nic,String reportId);
  public List<ReportDto> getAllReport ();
  public void deleteReport(Long id);
}
