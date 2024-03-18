package com.health.care.lab.appointment.service;

import com.health.care.lab.appointment.dto.ReportDto;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface ReportService {

  public  void uploadFile(MultipartFile file,String name);

  public void saveAndUpdateReport(ReportDto reportDto);
  public ReportDto getReport(String name,String nic,String reportId);
  public List<ReportDto> getAllReport ();
  public void deleteReport(Long id);
}
