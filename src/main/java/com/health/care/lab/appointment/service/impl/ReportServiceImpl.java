package com.health.care.lab.appointment.service.impl;

import com.health.care.lab.appointment.dto.DoctorDto;
import com.health.care.lab.appointment.dto.PatientDto;
import com.health.care.lab.appointment.dto.ReportDto;
import com.health.care.lab.appointment.dto.TechnicianDto;
import com.health.care.lab.appointment.entity.Doctor;
import com.health.care.lab.appointment.entity.Patient;
import com.health.care.lab.appointment.entity.Report;
import com.health.care.lab.appointment.entity.Technician;
import com.health.care.lab.appointment.repository.ReportRepository;
import com.health.care.lab.appointment.service.ReportService;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ReportServiceImpl implements ReportService {

  @Autowired
  private ReportRepository reportRepository;

  @Value("${upload.directory}")
  private String uploadDirectory;

  @Override
  public void uploadFile(MultipartFile file, String name) {
    Path uploadPath = Paths.get(uploadDirectory);
    try {
      if (!Files.exists(uploadPath)) {
        Files.createDirectories(uploadPath);
      }
      Files.copy(file.getInputStream(), uploadPath.resolve(name));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void saveAndUpdateReport(ReportDto reportDto) {

    Report report = setReportDtoData(reportDto);
    if (!ObjectUtils.isEmpty(report)){
      if (reportDto.getId()==null){
        reportRepository.save(report);
      }else {
        report.setId(reportDto.getId());
        reportRepository.save(report);
      }
    }
  }

  @Override
  public ReportDto getReport(String name, String nic, String reportId) {




    return null;
  }

  @Override
  public List<ReportDto> getAllReport() {
    List<Report> reportList = reportRepository.findAll();
    List<ReportDto> reportDtoList = new ArrayList<>();
    if (!CollectionUtils.isEmpty(reportList)){
      for (Report report: reportList) {
        reportDtoList.add(setReportEntityData(report));
      }
    }


    return reportDtoList;
  }

  @Override
  public void deleteReport(Long id) {

  }

  private Report setReportDtoData(ReportDto reportDto){
    Report report = new Report();
    report.setContent(reportDto.getContent());

    Patient patient = new Patient();
    patient.setPatientId(reportDto.getPatient().getPatientId());

    Doctor doctor = new Doctor();
    doctor.setDoctorID(reportDto.getDoctor().getDoctorID());

    Technician technician = new Technician();
    technician.setTechnicianId(reportDto.getTechnician().getTechnicianId());

    report.setPatient(patient);
    report.setDoctor(doctor);
    report.setTechnician(technician);

    return report;

  }

  private ReportDto setReportEntityData(Report report){
    ReportDto reportDto = new ReportDto();
    reportDto.setId(report.getId());
    reportDto.setContent(report.getContent());

    PatientDto patient = new PatientDto();
    patient.setPatientId(report.getPatient().getPatientId());

    DoctorDto doctor = new DoctorDto();
    doctor.setDoctorID(report.getDoctor().getDoctorID());

    TechnicianDto technician = new TechnicianDto();
    technician.setTechnicianId(report.getTechnician().getTechnicianId());

    reportDto.setPatient(patient);
    reportDto.setDoctor(doctor);
    reportDto.setTechnician(technician);

    return reportDto;
  }
}
